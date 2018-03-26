package fr.iutinfo.skeleton.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UtilisateurTest {

	private void initTableUtilisateur(UtilisateurDao utilisateurDao) {
		utilisateurDao.dropUtilisateurTable();
		utilisateurDao.createUtilisateurTable();
	}

	private void insertSampleUtilisateur(UtilisateurDao utilisateurDao) {
		Utilisateur utilisateur = new Utilisateur("Tom", "Marchand", "enseigne de Tom", "Siret de Tom", "tom.com", "mdptom",
				"1 rue tom", "0123456789", "brasseur");
		utilisateurDao.insert(utilisateur);
		utilisateur = new Utilisateur("Bruce", "Willis", "enseigne de Bruce", "Siret de Bruce", "bruce.com", "mdpbruce",
				"1 rue bruce", "9876543210", "barman");
		utilisateurDao.insert(utilisateur);
		utilisateur = new Utilisateur("Jason", "Statham", "enseigne de Jason", "Siret de Jason", "Jason.com", "mdpjason",
				"1 rue jason", "1346527850", "comiteEnt");
		utilisateurDao.insert(utilisateur);
		utilisateur = new Utilisateur("Chuck", "Norris", "enseigne de Chuck", "Siret de Chuck", "Chuck.com", "mdpchuck",
				"1 rue chuck", "1524986702", "admin");
		utilisateurDao.insert(utilisateur);
	}
	
	private void insertSampleUtilisateur2(UtilisateurDao utilisateurDao) {
		Utilisateur utilisateur = new Utilisateur("prenom2", "nom2", "enseigne2", "numeroSiret2", "@mail2", "password2",
				"2 rue ville", "2000000000", "barman");
		utilisateurDao.insert(utilisateur);
	}

	@Test
	public void should_save_utilisateur_in_db() {
		UtilisateurDao UtilisateurDao = BDDFactory.getDbi().open(UtilisateurDao.class);
		initTableUtilisateur(UtilisateurDao);
		insertSampleUtilisateur(UtilisateurDao);
		assertEquals("tom.com", UtilisateurDao.findById(1).getEmail());
	}

	@Test
	public void should_select_utilisateur_with_name_in_db() {
		UtilisateurDao utilisateurDao = BDDFactory.getDbi().open(UtilisateurDao.class);
		initTableUtilisateur(utilisateurDao);
		insertSampleUtilisateur(utilisateurDao);
		assertEquals(utilisateurDao.findByNom("Marchand").getNom(),"Marchand");
	}

	@Test
	public void should_delete_utilisateur_in_db() {
		UtilisateurDao utilisateurDao = BDDFactory.getDbi().open(UtilisateurDao.class);
		initTableUtilisateur(utilisateurDao);
		insertSampleUtilisateur(utilisateurDao);
		insertSampleUtilisateur2(utilisateurDao);
		utilisateurDao.delete(2);
		boolean test = true;
		if(utilisateurDao.findById(2)==null) {
			test = false;
		}
		assertFalse(test);
	}
	
	@Test
	public void should_list_utilisateurs_in_db() {
		UtilisateurDao utilisateurDao = BDDFactory.getDbi().open(UtilisateurDao.class);
		initTableUtilisateur(utilisateurDao);
		insertSampleUtilisateur(utilisateurDao);
		insertSampleUtilisateur2(utilisateurDao);
		List<Utilisateur> list = utilisateurDao.all();
		assertEquals(list.get(0).getNom(), "Marchand");
		assertEquals(list.get(1).getNom(), "Willis");
	}
}
