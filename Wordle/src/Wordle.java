
import javax.swing.SwingUtilities;

import model.WordleModel;
import view.WordleFrame;

public class Wordle implements Runnable {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Wordle());
	}

	@Override
	public void run() {
		new WordleFrame(new WordleModel());
	}

} 
