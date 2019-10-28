package be.afelio.software_academy.jpa.exercise.dvdrental.beans;

import java.util.Objects;

public abstract class Country {
	
	public abstract String getName();

	@Override
	public final int hashCode() {
		return Objects.hash(getName());
	}

	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Country))
			return false;
		Country other = (Country) obj;
		return Objects.equals(getName(), other.getName());
	}

	@Override
	public String toString() {
		return "Country [getName()=" + getName() + "]";
	}
	
	
}
