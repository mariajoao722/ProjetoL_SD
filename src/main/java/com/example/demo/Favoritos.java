package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Favoritos {
	
	@Id
	private int id;
	
	private String nome;
	private double preco;
	private String local;
	private int idUser;
    private int area;
    private String tipodeimovel;
    private String compra_aluga;
    private int disponivel; // 0 disponivel, 1 nao disponivel
    
    public Favoritos() {
    	
    }

	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public double getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public String getTipodeimovel() {
		return tipodeimovel;
	}
	public void setTipodeimovel(String tipodeimovel) {
		this.tipodeimovel = tipodeimovel;
	}
	public String getCompra_aluga() {
		return compra_aluga;
	}
	public void setCompra_aluga(String compra_aluga) {
		this.compra_aluga = compra_aluga;
	}
	public int getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(int disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "Favoritos [id=" + id + ", nome=" + nome + ", preco=" + preco + ", local=" + local + ", idUser=" + idUser
				+ ", area=" + area + ", tipodeimovel=" + tipodeimovel + ", compra_aluga=" + compra_aluga
				+ ", disponivel=" + disponivel + "]";
	}

    
}
