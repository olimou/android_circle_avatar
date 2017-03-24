package com.olimou.android.example;

import java.util.Locale;

/**
 * Created by EmersonMoura on 24/03/17.
 */

public class GenerateLetters {
	public String generate(String _name) {
		if (_name == null) {
			return "";
		}

		String lName = "";

		String alphaOnly = _name.replaceAll("[^a-zA-Z ]+", "");

		while (alphaOnly.startsWith(" ")) {
			alphaOnly = alphaOnly.substring(1);
		}

		if (alphaOnly.split(" ").length == 1) {
			String lS = alphaOnly.split(" ")[0];

			if (lS.length() > 1) {
				lName = lS.substring(0, 2);
			} else if (lS.length() > 0) {
				lName = String.format(Locale.getDefault(), "%s%s", lS.substring(0, 1),
						lS.substring(0, 1));
			}
		} else {
			String lS = alphaOnly.split(" ")[0];
			String lS1 = alphaOnly.split(" ")[alphaOnly.split(" ").length - 1];

			if (lS.length() > 0 && lS1.length() > 0) {
				lName = lS.substring(0, 1) + lS1.substring(0, 1);
			}
		}

		lName = lName.toUpperCase();

		return lName;
	}
}
