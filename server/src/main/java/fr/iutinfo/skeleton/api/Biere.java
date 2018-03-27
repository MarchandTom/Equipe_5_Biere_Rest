package fr.iutinfo.skeleton.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.BiereDto;


public class Biere {
    final static Logger logger = LoggerFactory.getLogger(Biere.class);
    private static Biere anonymous = new Biere("Anonymous", -1,-1,-1,"anonym","","",-1,"",-1,""); 
	private String nom;
	private int uno;
	private int bno;
	private int pno;
	private int prix;
	private String forme;
	private String type;
	private String description;
	private int taille;
	private String origine;
	private int degre;
	private String amertume;
	
	public Biere(String nom, int uno, int pno, int prix, String forme, String type, String description,
			int taille, String origine, int degre, String amertume) {
		this.nom = nom;
		this.uno = uno;
		this.pno = pno;
		this.prix = prix;
		this.forme = forme;
		this.type = type;
		this.description = description;
		this.taille = taille;
		this.origine = origine;
		this.degre = degre;
		this.amertume = amertume;
	}
	
	public Biere(int bno, String nom, int uno, int pno, int prix, String forme, String type, String description,
			int taille, String origine, int degre, String amertume) {
		this.nom = nom;
		this.bno=bno;
		this.uno = uno;
		this.pno = pno;
		this.prix = prix;
		this.forme = forme;
		this.type = type;
		this.description = description;
		this.taille = taille;
		this.origine = origine;
		this.degre = degre;
		this.amertume = amertume;
	}

	public Biere() {
		
	}

	
	public Biere(String nom, int prix) {
		this.nom = nom;
		this.prix = prix;
	}
	
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
	public void setUno(int brno) {
		this.uno = brno;
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
	public boolean isInBiereGroup() { 
        return !(bno == anonymous.getBno()); 
    } 
 
    public boolean isAnonymous() { 
        return this.getBno() == getAnonymousCoffret().getBno(); 
    } 
    public static Biere getAnonymousCoffret() { 
        return anonymous; 
    }
    public void initFromDto(BiereDto dto) { 
        this.setBno(dto.getBno()); 
        this.setNom(dto.getNom());
        this.setUno(dto.getUno());
        this.setBno(dto.getBno());
        this.setPrix(dto.getPrix());
        this.setForme(dto.getForme());
        this.setType(dto.getType());        
        this.setDescription(dto.getDescription()); 
        this.setTaille(dto.getTaille());
        this.setOrigine(dto.getOrigine());
        this.setDegre(dto.getDegre());
        this.setAmertume(dto.getAmertume());
    } 
 
    public BiereDto convertToDto() { 
        BiereDto dto = new BiereDto(); 
        dto.setBno(this.bno); 
        dto.setNom(this.nom);
        dto.setUno(this.uno);
        dto.setBno(this.bno);
        dto.setPrix(this.prix);
        dto.setForme(this.forme);
        dto.setType(this.type);
        dto.setDescription(this.description); 
        dto.setTaille(this.taille);
        dto.setOrigine(this.origine);
        dto.setDegre(this.degre);
        dto.setAmertume(this.amertume); 
        return dto; 
    }

	@Override
	public String toString() {
		return "Biere [bno=" + bno + ", nom=" + nom + ", uno=" + uno + ", pno=" + pno + ", prix=" + prix + ", forme="
				+ forme + ", type=" + type + ", description=" + description + ", taille=" + taille + ", origine="
				+ origine + ", degre=" + degre + ", amertume=" + amertume + "]";
	} 
	
}
