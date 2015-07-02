package abfragen;

import java.util.HashMap;

public interface TextCreator {
	String create(HashMap<String,String> hash);
	boolean isVisible(HashMap<String,String> hash);
}
