package be.afelio.software_academy.jpa.exercise.dvdrental.beans;

import java.util.Date;
import java.util.Objects;

public abstract class Rental {
	
	public abstract String getFilmTitle();
	public abstract String getCustomerEmail();
	public abstract Date getRentalDate();
	public abstract Date getReturnDate();
	
	@Override
	public final int hashCode() {
		return Objects.hash(getCustomerEmail(), getFilmTitle(), getRentalDate(), getReturnDate());
	}
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rental))
			return false;
		Rental other = (Rental) obj;
		return Objects.equals(getCustomerEmail(), other.getCustomerEmail())
				&& Objects.equals(getFilmTitle(), other.getFilmTitle())
				&& Objects.equals(getRentalDate(), other.getRentalDate()) 
				&& Objects.equals(getReturnDate(), other.getReturnDate());
	}
	@Override
	public String toString() {
		return "Rental [getFilmTitle()=" + getFilmTitle() + ", getCustomerEmail()=" + getCustomerEmail()
				+ ", getRentalDate()=" + getRentalDate() + ", getReturnDate()=" + getReturnDate() + "]";
	}
	
}
