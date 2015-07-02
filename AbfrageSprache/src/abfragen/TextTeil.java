package abfragen;

import java.util.HashMap;
import java.util.Vector;

public class TextTeil implements TextCreator {
	protected Vector<TextCreator> teile = new Vector<>();
	
	public TextTeil() {
	}
	
	public void addTextCreator(TextCreator e) {
		teile.add(e);
	}


	@Override
	public String create(HashMap<String, String> hash) {
		if (!isVisible(hash)) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(TextCreator tc : teile) {
			builder.append(tc.create(hash));
		}
		return builder.toString();
	}

	@Override
	public boolean isVisible(HashMap<String, String> hash) {
		return true;
	}

}
