package com.mgrobelak.blogrest.entities;

/**
 * @author Marcin Grobelak
 */

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

@NamedQueries({ @NamedQuery(name = "getUsers", query = "SELECT u FROM User u") })

@XmlRootElement
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 795149849279154668L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String surname;

	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private List<Post> posts = new ArrayList<>();

	public User() {

	}

	public User(Long id, String name, String surname, LocalDate birthDate) {
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
