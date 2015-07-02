package abfragen;

import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;

public class TextVariable implements TextCreator {
	String key;
	Vector<Pattern> patterns;
	
	
	public TextVariable(String key,Vector<Pattern> patterns) {
		this.key = key;
		this.patterns = patterns;
	}

	@Override
	public String create(HashMap<String, String> hash) {
		return hash.get(key);
	}

	@Override
	public boolean isVisible(HashMap<String, String> hash) {
		String t =  hash.get(key);
		if (t == null) return false;
		
		if (patterns == null) {
			return ! ( t == null || "".equals(t.trim()));
		} else {
			for (Pattern p : patterns) {
				
				if (p.matcher(t).find()) {
					return true;
				}
			}
		}
		return false;
	}

}
