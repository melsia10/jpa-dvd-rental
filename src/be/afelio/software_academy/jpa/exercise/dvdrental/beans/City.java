package be.afelio.software_academy.jpa.exercise.dvdrental.beans;

import java.util.Objects;

public abstract class City {
	
	public abstract String getName();
	public abstract Country getCountry();
	
	@Override
	public int hashCode() {
		return Objects.hash(getCountry(), getName());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof City))
			return false;
		City other = (City) obj;
		return Objects.equals(getCountry(), other.getCountry()) 
				&& Objects.equals(getName(), other.getName());
	}
	
	@Override
	public String toString() {
		return "City [getName()=" + getName() + ", getCountry()=" + getCountry() + "]";
	}
}
