package com.epam.rd.autotasks;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class NewPostOffice {
	private final Collection<Box> listBox;
	private static final int COST_KILOGRAM = 5;
	private static final int COST_CUBIC_METER = 100;
	private static final double COEFFICIENT = 0.5;
	
	public NewPostOffice () {
		listBox = new ArrayList<>();
	}
	
	public Collection<Box> getListBox () {
		return new ArrayList<>(listBox);
	}
	
	static BigDecimal calculateCostOfBox (double weight, double volume, int value) {
		BigDecimal costWeight = BigDecimal.valueOf(weight)
				.multiply(BigDecimal.valueOf(COST_KILOGRAM), MathContext.DECIMAL64);
		BigDecimal costVolume = BigDecimal.valueOf(volume)
				.multiply(BigDecimal.valueOf(COST_CUBIC_METER), MathContext.DECIMAL64);
		return costVolume.add(costWeight)
				.add(BigDecimal.valueOf(COEFFICIENT * value), MathContext.DECIMAL64)
				.setScale(13, RoundingMode.HALF_UP); // Ensure precision
	}
	
	public boolean addBox (String sender, String recipient, double weight, double volume, int value) {
		
		if (sender == null || sender.trim().isEmpty() || recipient == null || recipient.trim().isEmpty()) {
			throw new IllegalArgumentException("Sender and recipient cannot be null or empty.");
		}
		if (weight < 0.5 || weight > 20) {
			throw new IllegalArgumentException("Weight must be between 0.5 and 20 kg.");
		}
		
		if (volume <= 0 || volume > 0.25) {
			throw new IllegalArgumentException("Volume must be greater than 0 and less than 0.25 m3.");
		}
		
		if (value <= 0) {
			throw new IllegalArgumentException("Value must be greater than zero.");
		}
		
		Box box = new Box(sender, recipient, weight, volume);
		box.setCost(calculateCostOfBox(weight, volume, value));
		return listBox.add(box);
	}
	
	public Collection<Box> deliveryBoxToRecipient (String recipient) {
		if (recipient == null || recipient.trim().isEmpty()) {
			throw new IllegalArgumentException("Recipient cannot be null or empty.");
		}
		
		Collection<Box> deliveredBoxes = new ArrayList<>();
		Iterator<Box> iterator = listBox.iterator();
		
		while (iterator.hasNext()) {
			Box box = iterator.next();
			if (box.getRecipient().equals(recipient)) {
				deliveredBoxes.add(box);
				iterator.remove();
			}
		}
		return deliveredBoxes;
	}
	
	public void declineCostOfBox(double percent) {
		if (percent < 0) {
			throw new IllegalArgumentException("Percent must be non-negative.");
		}
		BigDecimal multiplier = BigDecimal.valueOf(1 - percent / 100);
		for (Box box : listBox) {
			BigDecimal newCost = box.getCost().multiply(multiplier, MathContext.DECIMAL64);
			box.setCost(newCost);
		}
	}
	
}
