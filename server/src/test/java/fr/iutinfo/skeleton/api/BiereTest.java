package fr.iutinfo.skeleton.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class BiereTest {

	private void initTableBiere(BiereDao biereDao) {
		biereDao.dropBiereTable();
		biereDao.createBiereTable();
	}

	private void insertSampleBiere(BiereDao biereDao) {
		Biere biere= new Biere("Une Bière vraiment pas mal", 15);
//		Biere biere= new Biere("nomDeLaBiere", 1, 1, 12, "forme", "type", "desc", 2, "origine", 10, "amertume");
		biereDao.insert(biere);
	}
	
	private void insertSampleBiere2(BiereDao biereDao) {
		Biere biere= new Biere("nomDeLaBiere2", 2, 1, 12, "forme", "type", "desc", 2, "origine", 10, "amertume");
		biereDao.insert(biere);
	}

	@Test
	public void should_save_biere_in_db() {
		BiereDao BiereDao = BDDFactory.getDbi().open(BiereDao.class);
		initTableBiere(BiereDao);
		insertSampleBiere(BiereDao);
		assertEquals("Une Bière vraiment pas mal", BiereDao.findByBno(1).getNom());
	}

	@Test
	public void should_select_biere_with_name_in_db() {
		BiereDao biereDao = BDDFactory.getDbi().open(BiereDao.class);
		initTableBiere(biereDao);
		insertSampleBiere(biereDao);
		assertEquals(biereDao.findByNom("Une Bière vraiment pas mal").getNom(),"Une Bière vraiment pas mal");
	}
	
	@Test
	public void should_delete_biere_in_db() {
		BiereDao biereDao  = BDDFactory.getDbi().open(BiereDao .class);
		initTableBiere(biereDao);
		insertSampleBiere(biereDao);
		insertSampleBiere2(biereDao);
		biereDao.delete(2);
		boolean test = true;
		if(biereDao.findByBno(2)==null) {
			test = false;
		}
		assertFalse(test);
	}
	
	
	@Test
	public void should_list_biere_in_db() {
		BiereDao biereDao = BDDFactory.getDbi().open(BiereDao.class);
		initTableBiere(biereDao);
		insertSampleBiere(biereDao);
		insertSampleBiere2(biereDao);
		List<Biere> list = biereDao.all();
		assertEquals(list.get(0).getNom(), "Une Bière vraiment pas mal");
		assertEquals(list.get(1).getNom(), "nomDeLaBiere2");
	}
}
