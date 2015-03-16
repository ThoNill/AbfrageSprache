package abfragen;

import java.util.Hashtable;
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
	public String create(Hashtable<String, String> hash) {
		return hash.get(key);
	}

	@Override
	public boolean isVisible(Hashtable<String, String> hash) {
		String t =  hash.get(key);
		if (patterns == null) {
			return ! ( t == null || "".equals(t.trim()));
		} else {
			for (Pattern p : patterns) {
				if (p.matcher(t).matches()) {
					return true;
				}
			}
		}
		return false;
	}

}
