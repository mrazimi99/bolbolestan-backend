package com.ie.bolbolestan.model;

public enum CourseState {
	FINALIZED,
	NON_FINALIZED;

	@Override
	public String toString() {
		if (this == FINALIZED) return "FINALIZED";
		else return "NON_FINALIZED";
	}
}
