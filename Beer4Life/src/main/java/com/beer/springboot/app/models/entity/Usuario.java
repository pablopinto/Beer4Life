
package com.beer.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class Usuario implements Serializable {
	
	@PrePersist //Metodo para crear Fecha de manera Automatica al introducir en una zona
	public void prePersist() {
		createAt= new Date();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(unique = true, length = 40)
	private String username;
	
	@NotEmpty
	@Column(length = 60)
	private String password;

	private Boolean enabled;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Role> roles;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String name;

	@NotEmpty
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date createAt;
	
//	@OneToMany(mappedBy ="users"  ,fetch =FetchType.LAZY , cascade = CascadeType.ALL)
//	private List<Factura> facturas;
	
//	public Usuario() {
//		facturas = new ArrayList<Factura>();
//	}
	
	private String foto;

	
	//GETTERS AND SETTERS 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
//	public List<Factura> getFacturas() {
//		return facturas;
//	}
//
//	public void setFacturas(List<Factura> facturas) {
//		this.facturas = facturas;
//	}
//	
//	public void addFacturas(Factura factura) {
//		facturas.add(factura);
//	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return name + " " + lastName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
