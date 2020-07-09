package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;
import java.time.LocalDate;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable {

	private static final long serialVersionUID = 795149849279154668L;

	private Long id;
	private String name;
	private String surname;
	private LocalDate birthDate;

	public User() {

	}

	public User(Long id, String name, String surname, LocalDate birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		User other = (User) obj;

		if (name == null) {
			if (other.getName() != null)
				return false;
		} else if (!name.equals(other.getName()))
			return false;
		if (surname == null) {
			if (other.getSurname() != null)
				return false;
		} else if (!surname.equals(other.getSurname()))
			return false;
		if (birthDate == null) {
			if (other.getBirthDate() != null)
				return false;
		} else if (!birthDate.equals(other.getBirthDate()))
			return false;
		return true;
	}
}
