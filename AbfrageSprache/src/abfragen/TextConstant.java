package abfragen;

import java.util.HashMap;

public class TextConstant implements TextCreator {
	String text;
	
	
	public TextConstant(String text) {
		this.text = text;
	}

	@Override
	public String create(HashMap<String, String> hash) {
		return text;
	}

	@Override
	public boolean isVisible(HashMap<String, String> hash) {
		return true;
	}

}
