package mirrg.boron.swing;

import static mirrg.boron.swing.UtilsColor.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class TestUtilsColor
{

	@Test
	public void test()
	{
		assertEquals(0xFFFFFFFF, fromDoublesRGBA(301, 302, 303, 304));
		assertEquals(0xFFFFFF, fromDoublesRGB(301, 302, 303));
		assertEquals(0x00000000, fromDoublesRGBA(-1, -2, -3, -4));
		assertEquals(0x000000, fromDoublesRGB(-1, -2, -3));
		assertEquals(0x00000000, fromDoublesRGBA(-0.1, -0.2, -0.3, -0.4));
		assertEquals(0x000000, fromDoublesRGB(-0.1, -0.2, -0.3));
		assertEquals(0x03000102, fromDoublesRGBA(0.1, 1.1, 2.1, 3.1));
		assertEquals(0x000102, fromDoublesRGB(0.1, 1.1, 2.1));
		assertEquals(0x04010203, fromDoublesRGBA(0.5, 1.5, 2.5, 3.5));
		assertEquals(0x010203, fromDoublesRGB(0.5, 1.5, 2.5));

		assertEquals(0x04010203, fromIntegerArrayRGBA(new int[] { 1, 2, 3, 4 }));
		assertEquals(0x010203, fromIntegerArrayRGB(new int[] { 1, 2, 3, 4 }));
		assertArrayEquals(new int[] { 1, 2, 3, 4 }, toIntegerArrayRGBA(0x04010203));
		assertArrayEquals(new int[] { 1, 2, 3 }, toIntegerArrayRGB(0x010203));

		assertEquals(0x04010203, fromColorRGBA(new Color(1, 2, 3, 4)));
		assertEquals(0x010203, fromColorRGB(new Color(1, 2, 3)));
		assertEquals(new Color(1, 2, 3, 4), toColorRGBA(0x04010203));
		assertEquals(new Color(1, 2, 3), toColorRGB(0x010203));

		assertEquals(0x04010203, randomRGBA(1, 1, 2, 2, 3, 3, 4, 4));
		assertEquals(0x010203, randomRGB(1, 1, 2, 2, 3, 3));

		assertEquals(0x10203040, ratio(-99999, 0x10203040, 0x50607080));
		assertEquals(0x10203040, ratio(-1, 0x10203040, 0x50607080));
		assertEquals(0x10203040, ratio(-0.0001, 0x10203040, 0x50607080));
		assertEquals(0x10203040, ratio(0, 0x10203040, 0x50607080));
		assertEquals(0x20304050, ratio(0.25, 0x10203040, 0x50607080));
		assertEquals(0x30405060, ratio(0.5, 0x10203040, 0x50607080));
		assertEquals(0x40506070, ratio(0.75, 0x10203040, 0x50607080));
		assertEquals(0x50607080, ratio(1, 0x10203040, 0x50607080));
		assertEquals(0x50607080, ratio(1.00001, 0x10203040, 0x50607080));
		assertEquals(0x50607080, ratio(99999, 0x10203040, 0x50607080));
	}

}
