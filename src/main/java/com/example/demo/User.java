package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	@Id
	private int id;
	private String nome;
	private String password;
	private String role;
	private int vendas;

	public User() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	public int getVendas() {
		return vendas;
	}

	public void setVendas(int vendas) {
		this.vendas = vendas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", password=" + password + ", role=" + role + ", vendas=" + vendas
				+ "]";
	}

	
}
