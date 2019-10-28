package be.afelio.software_academy.jpa.exercise.dvdrental.beans;

import java.util.Objects;

public abstract class Address {
	
	public abstract String getValue();
	public abstract City getCity();

	@Override
	public final int hashCode() {
		return Objects.hash(getCity(), getValue());
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Address))
			return false;
		Address other = (Address) obj;
		return Objects.equals(getCity(), other.getCity()) && Objects.equals(getValue(), other.getValue());
	}
	
	@Override
	public String toString() {
		return "Address [getValue()=" + getValue() + ", getCity()=" + getCity() + "]";
	}
}
