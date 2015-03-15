package tests;

import java.util.Hashtable;

public class TextConstant implements TextCreator {
	String text;
	
	
	public TextConstant(String text) {
		this.text = text;
	}

	@Override
	public String create(Hashtable<String, String> hash) {
		return text;
	}

	@Override
	public boolean isVisible(Hashtable<String, String> hash) {
		return true;
	}

}
