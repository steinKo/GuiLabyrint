package test;
import static org.junit.Assert.*;

import org.junit.Test;

import GuiLosning;

public class GuiLosningTest {

	@Test
	public void test() {
		GuiLosning guiLosning = new GuiLosning();
		String lonsinsgtring = new String("(1,1)->(1,2)");
		boolean result[][];
		result = guiLosning.losningStringTilTabell(lonsinsgtring,2,2);
		assertEquals(result[0][0],true);
		assertEquals(result[0][1],false);
		assertEquals(result[1][0],true);
		assertEquals(result[1][1],false);
	}
}
