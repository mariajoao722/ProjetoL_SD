package com.example.demo;

public class FavoritosList {

	private int id;
	private String nome;
	private double preco;
	private String User;
	private String local;
    private int area;
    private String tipodeimovel;
    private String compra_aluga;
    private int disponivel; // 0 disponivel, 1 nao disponivel
	
    
    
    public FavoritosList() {
    	this.id = -1;
		this.nome = "";
		this.preco = 0;
		this.User = "";
		this.local = "";
		this.area = 0;
		this.tipodeimovel = "";
		this.compra_aluga = "";
		this.disponivel = 0;
    }
    
    
    public FavoritosList(int id, String nome, double preco, String User, String local, int area, String tipodeimovel, String compra_aluga, int disponivel) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.User = User;
		this.local = local;
		this.area = area;
		this.tipodeimovel = tipodeimovel;
		this.compra_aluga = compra_aluga;
		this.disponivel = disponivel;
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
	public String getIdUser() {
		return User;
	}
	public void setIdUser(String User) {
		this.User = User;
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
	@Override
	public String toString() {
		return "FavoritosList [id=" + id + ", nome=" + nome + ", preco=" + preco + ", idUser=" + User + ", local="
				+ local + ", area=" + area + ", tipodeimovel=" + tipodeimovel + ", compra_aluga=" + compra_aluga
				+ ", disponivel=" + disponivel + "]";
	}
    
}
