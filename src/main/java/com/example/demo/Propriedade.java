package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Propriedade {
	
	@Id
	private int id;
	private String nome;
	private double preco;
	private int userid;
	private String local;
    private int area;
    private String tipodeimovel;
    private String compra_aluga;
    private int disponivel; // 0 disponivel, 1 nao disponivel
    private int propapartamento;
	private int propcasa;
    private int propterreno;
    
    public Propriedade() {
    	
    }
    
    
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public int getArea() {
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

	public int getPropapartamento() {
		return propapartamento;
	}

	public void setPropapartamento(int propapartamento) {
		this.propapartamento = propapartamento;
	}



	public int getPropcasa() {
		return propcasa;
	}

	public void setPropcasa(int propcasa) {
		this.propcasa = propcasa;
	}

	public int getPropterreno() {
		return propterreno;
	}

	public void setPropterreno(int propterreno) {
		this.propterreno = propterreno;
	}


	@Override
	public String toString() {
		return "Propriedade [id=" + id + ", nome=" + nome + ", preco=" + preco + ", userid=" + userid + ", local="
				+ local + ", area=" + area + ", tipodeimovel=" + tipodeimovel + ", compra_aluga=" + compra_aluga
				+ ", disponivel=" + disponivel + "]";
	}


}
