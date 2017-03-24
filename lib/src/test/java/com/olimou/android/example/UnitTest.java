package com.olimou.android.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
	@Test
	public void checkCorrectName() throws Exception {
		GenerateLetters lGenerateLetters = new GenerateLetters();

		assertEquals(lGenerateLetters.generate("A1"), "AA");

		assertEquals(lGenerateLetters.generate("Emerson"), "EM");

		assertEquals(lGenerateLetters.generate("Emerson Moura"), "EM");

		assertEquals(lGenerateLetters.generate("123456"), "");
	}
}