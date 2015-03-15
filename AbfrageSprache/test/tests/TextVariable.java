package tests;

import java.util.Hashtable;

public class TextVariable implements TextCreator {
	String key;
	
	
	public TextVariable(String key) {
		this.key = key;
	}

	@Override
	public String create(Hashtable<String, String> hash) {
		return hash.get(key);
	}

	@Override
	public boolean isVisible(Hashtable<String, String> hash) {
		String t =  hash.get(key);
		return ! ( t == null || "".equals(t.trim()));
	}

}
