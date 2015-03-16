package abfragen;

import java.util.Hashtable;

public interface TextCreator {
	String create(Hashtable<String,String> hash);
	boolean isVisible(Hashtable<String,String> hash);
}
