package fr.iutinfo.skeleton.common.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BiereDto {
	final static Logger logger = LoggerFactory.getLogger(BiereDto.class);
    private int bno;
	private String nom;
	private int uno;
	private int pno;
	private int prix;
	private String forme;
	private String type;
	private String description;
	private int taille;
	private String origine;
	private int degre;
	private String amertume;
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTaille() {
		return taille;
	}
	public void setTaille(int taille) {
		this.taille = taille;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}
	public int getDegre() {
		return degre;
	}
	public void setDegre(int degre) {
		this.degre = degre;
	}
	public String getAmertume() {
		return amertume;
	}
	public void setAmertume(String amertume) {
		this.amertume = amertume;
	}
	
	
}

