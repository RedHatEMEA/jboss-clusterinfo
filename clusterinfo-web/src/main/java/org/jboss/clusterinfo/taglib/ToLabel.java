package org.jboss.clusterinfo.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ToLabel extends SimpleTagSupport {

	private String string;

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public void doTag() throws JspException, IOException {
		getJspContext().getOut().write(toLabel(string));
	}

	private static String toLabel(String unformatted) {

		if (unformatted == null)
			return "";

		StringBuffer formatted = new StringBuffer();
		char[] unformattedChars = new char[unformatted.length()];
		unformatted.getChars(0, unformatted.length(), unformattedChars, 0);

		for (int i = 0; i < unformattedChars.length; i++) {
			if (i == 0) {
				formatted.append(Character.toUpperCase(unformattedChars[0]));
			} else {
				if (Character.isUpperCase(unformattedChars[i])) {
					if (unformattedChars[i - 1] != ' '
							&& (i == unformattedChars.length - 1 || unformattedChars[i + 1] != ' ')) {
						if ((i < unformattedChars.length - 1 && !Character
								.isUpperCase(unformattedChars[i + 1]))
								|| (!Character
										.isUpperCase(unformattedChars[i - 1]))) {
							formatted.append(" ");
						}
					}
				}
				formatted.append(unformattedChars[i]);
			}
		}

		return formatted.toString();
	}

	public static void main(String[] args) {

		System.out.println(toLabel("requestTest"));
		System.out.println(toLabel("requestTest"));
		System.out.println(toLabel("HTTP"));
		System.out.println(toLabel("HTTP RequestHTTP"));
		System.out.println(toLabel("HTTPRequestHTTP"));

	}
}
