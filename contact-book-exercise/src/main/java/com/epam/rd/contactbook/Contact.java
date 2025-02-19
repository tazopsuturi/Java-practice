package com.epam.rd.contactbook;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private String phoneNumber;
    private final Email[] emails = new Email[3];
    private final Social[] socials = new Social[5];
    private int emailCount = 0;
    private int socialCount = 0;
    
    public Contact(String contactName) {
        if (contactName == null || contactName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = contactName;
    }
    
    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            newName = name;
        }
        this.name = newName;
    }
    
    public Email addEmail(String localPart, String domain) {
        if (emailCount < 3) {
            Email email = new Email(localPart, domain);
            emails[emailCount++] = email;
            return email;
        }
        return null;
    }
    
    public Email addEpamEmail(String firstname, String lastname) {
        if (firstname == null || firstname.isBlank() || lastname == null || lastname.isBlank()) {
            throw new IllegalArgumentException("Firstname and lastname cannot be empty");
        }
        if (emailCount < 3) {
            Email epamEmail = new Email(firstname + "_" + lastname, "epam.com") {
                @Override
                public String getTitle() {
                    return "Epam Email";
                }
            };
            emails[emailCount++] = epamEmail;
            return epamEmail;
        }
        return null;
    }
    
    public ContactInfo addPhoneNumber(int code, String number) {
        if (phoneNumber == null) {
            phoneNumber = "+" + code + " " + number;
            return new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }
                
                @Override
                public String getValue() {
                    return phoneNumber;
                }
            };
        }
        return null;
    }
    
    public Social addTwitter(String twitterId) {
        return addSocialMedia("Twitter", twitterId);
    }
    
    public Social addInstagram(String instagramId) {
        return addSocialMedia("Instagram", instagramId);
    }
    
    public Social addSocialMedia(String title, String id) {
        if (socialCount < 5) {
            Social social = new Social(title, id);
            socials[socialCount++] = social;
            return social;
        }
        return null;
    }
    
    public ContactInfo[] getInfo() {
        int totalInfoCount = 1 + (phoneNumber != null ? 1 : 0) + emailCount + socialCount;
        ContactInfo[] contactInfoArray = new ContactInfo[totalInfoCount];
        
        contactInfoArray[0] = new NameContactInfo();
        
        int index = 1;
        if (phoneNumber != null) {
            contactInfoArray[index++] = new ContactInfo() {
                @Override
                public String getTitle() {
                    return "Tel";
                }
                
                @Override
                public String getValue() {
                    return phoneNumber;
                }
            };
        }
        
        // Add emails
        for (int i = 0; i < emailCount; i++) {
            contactInfoArray[index++] = emails[i];
        }
        
        // Add social media
        for (int i = 0; i < socialCount; i++) {
            contactInfoArray[index++] = socials[i];
        }
        
        return contactInfoArray;
    }
    
    private class NameContactInfo implements ContactInfo {
        @Override
        public String getTitle() {
            return "Name";
        }
        
        @Override
        public String getValue() {
            return name;
        }
    }
    
    public static class Email implements ContactInfo {
        private final String value;
        private final boolean isEpamEmail;
        
        public Email(String localPart, String domain) {
            this.value = localPart + "@" + domain;
            this.isEpamEmail = "epam.com".equals(domain);
        }
        
        @Override
        public String getTitle() {
            if (isEpamEmail){
                return "Epam Email";
            }
            return "Email";
        }
        
        @Override
        public String getValue() {
            return value;
        }
    }
    
    public static class Social implements ContactInfo {
        private final String title;
        private final String value;
        
        public Social(String title, String value) {
            this.title = title;
            this.value = value;
        }
        
        @Override
        public String getTitle() {
            return title;
        }
        
        @Override
        public String getValue() {
            return value;
        }
    }
}
