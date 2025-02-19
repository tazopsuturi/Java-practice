package com.epam.rd.autotasks;


public enum Direction {
	N(0), NE(45), E(90), SE(135), S(180), SW(225), W(270), NW(315);
	
	Direction (final int degrees) {
		this.degrees = degrees;
	}
	
	private int degrees;
	
	public static Direction ofDegrees (int degrees) {
		int normalized = (degrees % 360 + 360) % 360;
		for (Direction dir : Direction.values()) {
			if (dir.degrees == normalized) {
				return dir;
			}
		}
		return null;
	}
	
	public static Direction closestToDegrees (int degrees) {
		int normalized = (degrees % 360 + 360) % 360;
		Direction closest = null;
		int minDiff = Integer.MAX_VALUE;
		
		for (Direction dir : Direction.values()) {
			int dirDegrees = dir.degrees;
			int diff = Math.abs(normalized - dirDegrees);
			diff = Math.min(diff, 360 - diff);
			if (diff < minDiff) {
				minDiff = diff;
				closest = dir;
			} else if (diff == minDiff) {
				if (Math.abs(normalized - dirDegrees) < Math.abs(normalized - closest.degrees)) {
					closest = dir;
				}
			}
		}
		return closest;
	}
	
	public Direction opposite () {
		int oppositeDegrees = (this.degrees + 180) % 360;
		for (Direction dir : Direction.values()) {
			if (dir.degrees == oppositeDegrees) {
				return dir;
			}
		}
		throw new IllegalStateException("Opposite direction not found");
	}
	
	public int differenceDegreesTo (Direction direction) {
		int a = this.degrees;
		int b = direction.degrees;
		int diff = Math.abs(a - b);
		return Math.min(diff, 360 - diff);
	}
}