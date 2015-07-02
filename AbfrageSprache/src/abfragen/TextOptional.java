package abfragen;

import java.util.HashMap;
import java.util.Vector;

public class TextOptional extends TextTeil {
	
	public TextOptional() {
	}

	@Override
	public boolean isVisible(HashMap<String, String> hash) {
		boolean visible = true;
		for(TextCreator tc : teile) {
			visible = visible && tc.isVisible(hash);
		}
		return visible;
	}

}
