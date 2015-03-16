package abfragen;

import java.util.Hashtable;
import java.util.Vector;

public class TextOptional extends TextTeil {
	
	public TextOptional() {
	}

	@Override
	public boolean isVisible(Hashtable<String, String> hash) {
		boolean visible = true;
		for(TextCreator tc : teile) {
			visible = visible && tc.isVisible(hash);
		}
		return visible;
	}

}
