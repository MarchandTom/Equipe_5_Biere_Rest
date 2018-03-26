package fr.iutinfo.skeleton.api;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.Test;

public class LastTest {

	@Test
	public void testBiereRestant() throws InterruptedException, SQLException {
		try {
			if (AllTest.tableExist("bieres")) {
				BDDFactory.getDbi().open(BiereDao.class).dropBiereTable();
				assertFalse(AllTest.tableExist("bieres"));
				Thread.sleep(1000);
				new BiereResource();
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try{
				BDDFactory.getDbi().open(BiereDao.class).createBiereTable();;
				e.printStackTrace();
			}catch(Exception LOL) {fail(LOL.getMessage());}
			assertTrue("CA n'eXISTE PAS !",AllTest.tableExist("bieres"));
			
		}
	}

}
