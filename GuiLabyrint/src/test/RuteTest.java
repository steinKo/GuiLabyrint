package test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

import Labyrint;
import Rute;
import main.Koe;

public class RuteTest {

	@Test
	public void test() throws FileNotFoundException {
		File fil = new File("2.in");
		Labyrint labyrint = Labyrint.lesFraFil(fil);
	    Rute rute = Rute.lagRute('.',1, 8, labyrint);
		assertNotNull(rute.labyrint);
		labyrint.losninger = new Koe<String>();
		String utveier = new String("(2,9)");
		rute.gaa(null, utveier);
		assertFalse(labyrint.losninger.erTom());
		
	}
	
	
	

}
