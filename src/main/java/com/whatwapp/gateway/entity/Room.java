package com.whatwapp.gateway.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column
	private int duel;
	
	@Column
	private int legaue;
	
	@Column
	private int tabela;
	
	@Column
	private int playerno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public int getLegaue() {
		return legaue;
	}

	public void setLegaue(int legaue) {
		this.legaue = legaue;
	}

	public int getplayerno() {
		return playerno;
	}

	public void setplayerno(int playerno) {
		this.playerno = playerno;
	}

	public int getDuel() {
		return duel;
	}

	public void setDuel(int duel) {
		this.duel = duel;
	}

	public int getTabela() {
		return tabela;
	}

	public void setTabela(int tabela) {
		this.tabela = tabela;
	}
	
	
}
