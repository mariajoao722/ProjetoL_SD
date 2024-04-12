package com.example.demo;

public class PropriedadesList {
	private int id;
	private String nome;
	private double preco;
	private String vendedor;
	private String local;
    private int area;
    private String tipodeimovel;
    private String compra_aluga;
    private int disponivel; // 0 disponivel, 1 nao disponivel
    private int propapartamento;
	private int propcasa;
    private int propterreno;
    
    
    
    public PropriedadesList() {
    	this.id = -1;
		this.nome = "";
		this.preco = 0;
		this.vendedor = "";
		this.local = "";
		this.area = 0;
		this.tipodeimovel = "";
		this.compra_aluga = "";
		this.disponivel = 0;
		this.propapartamento = 0;
		this.propcasa = 0;
		this.propterreno = 0;
		
    }
    



	public PropriedadesList(int id, String nome, double preco, String vendedor, String local, int area, String tipodeimovel, String compra_aluga, int disponivel) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.vendedor = vendedor;
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
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
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
		return "PropriedadesList [id=" + id + ", nome=" + nome + ", preco=" + preco + ", vendedor=" + vendedor
				+ ", local=" + local + ", area=" + area + ", tipodeimovel=" + tipodeimovel + ", compra_aluga="
				+ compra_aluga + ", disponivel=" + disponivel + "]";
	}
    
}
