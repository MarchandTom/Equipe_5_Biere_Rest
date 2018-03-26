package fr.iutinfo.skeleton.api;

import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import fr.iutinfo.skeleton.common.dto.UtilisateurDto;

public class Utilisateur {
	
	final static Logger logger = LoggerFactory.getLogger(Utilisateur.class);
	private static Utilisateur anonymous = new Utilisateur("Anonymous", "anonym","","","","","","","");
	
	private int uno;
	private String prenom, nom, enseigne, siret, email, mdp, adresse, tel, type, passwdHash, salt;

	
	public Utilisateur() {
		
	}
	
	
	public Utilisateur(String prenom, String nom, String enseigne, String siret, String email, String mdp, String adresse,
			String tel, String type, String passwdHash, String salt) {
		this.prenom = prenom;
		this.nom = nom;
		this.enseigne = enseigne;
		this.siret = siret;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.tel = tel;
		this.type=type;
		this.passwdHash = passwdHash;
		this.salt = salt;
	}

	
	public Utilisateur(String nom) {
		this.nom = nom;
	}
	
	public Utilisateur(String prenom, String nom, String enseigne, String siret,String email, String mdp, String adresse,
			String tel, String type) {
		this.prenom = prenom;
		this.nom = nom;
		this.enseigne = enseigne;
		this.siret = siret;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.tel = tel;
		this.type = type;
	}

    @Override
    public String toString() {
        return  "uno : " + uno + " | prenom :" + prenom + " | nom : " + nom + " | enseigne : " + enseigne + " | siret : " + siret 
        		+ " | email : " + email + " | ";
    }

/*
	public Barman(int i, String prenom2, String nom2, String enseigne2, String email2, String adresse2, String tel2,
			String passwdHash2) {
		// TODO Auto-generated constructor stub
		this.bano=i;
		this.prenom=prenom2;
		this.nom=nom2;
		this.enseigne=enseigne2;
		this.email=email2;
		this.adresse=adresse2;
		this.tel=tel2;
		setPassword(passwdHash2);
	}*/


	public void setPassword(String password) {
		passwdHash = buildHash(password, getSalt());
		this.mdp = password;
	}

	private String buildHash(String password, String s) {
		Hasher hasher = Hashing.sha256().newHasher();
		hasher.putString(password + s, Charsets.UTF_8);
		return hasher.hash().toString();
	}

	public boolean isGoodPassword(String password) {
		if (isAnonymous()) {
			return false;
		}
		String hash = buildHash(password, getSalt());
		return hash.equals(getPasswdHash());
	}

	public String getPasswdHash() {
		return passwdHash;
	}

	public void setPasswdHash(String passwdHash) {
		this.passwdHash = passwdHash;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEnseigne() {
		return enseigne;
	}

	public void setEnseigne(String enseigne) {
		this.enseigne = enseigne;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSiret() {
		return siret;
	}


	public void setSiret(String siret) {
		this.siret = siret;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getSalt() {
		return salt;
	}


	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	  private String generateSalt() {
	        SecureRandom random = new SecureRandom();
	        Hasher hasher = Hashing.sha256().newHasher();
	        hasher.putLong(random.nextLong());
	        return hasher.hash().toString();
	    }

	public void resetPasswordHash() {
		if (mdp != null && !mdp.isEmpty()) {
			setPassword(getMdp());
		}
	}

	public boolean isInUserGroup() {
		return !(uno == anonymous.getUno());
	}

	
	public boolean isAnonymous() {
		return this.getUno() == getAnonymousUser().getUno();
	}


	public static Utilisateur getAnonymousUser() {
        return anonymous;
    }

	public void initFromDto(UtilisateurDto dto) {
		this.setUno(dto.getUno());
		this.setPrenom(dto.getPrenom());
		this.setNom(dto.getNom());
		this.setEnseigne(dto.getEnseigne());
		this.setSiret(dto.getSiret());
		this.setEmail(dto.getEmail());
		this.setMdp(dto.getMdp());
		this.setAdresse(dto.getAdresse());
		this.setTel(dto.getTel());
		this.setMdp(dto.getMdp());
		this.setSalt(dto.getSalt());
		this.setType(dto.getType());
	}

	public UtilisateurDto convertToDto() {
		UtilisateurDto dto = new UtilisateurDto();
		dto.setUno(this.getUno());
		dto.setPrenom(this.getPrenom());
		dto.setNom(this.getNom());
		dto.setEnseigne(this.getEnseigne());
		dto.setSiret(this.getSiret());
		dto.setEmail(this.getEmail());
		dto.setMdp(this.getMdp());
		dto.setAdresse(this.getAdresse());
		dto.setTel(this.getTel());
		dto.setType(this.getType());
		dto.setMdp(this.getMdp());
		dto.setSalt(this.getSalt());
		dto.setType(this.getType());
		return dto;
	}


	

	
}
