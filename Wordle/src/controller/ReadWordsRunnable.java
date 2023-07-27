package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileReader;

import model.WordleModel;

public class ReadWordsRunnable implements Runnable {

	private final static Logger LOGGER =
			Logger.getLogger(ReadWordsRunnable.class.getName());

	private final WordleModel model;

	public ReadWordsRunnable(WordleModel model) {
		LOGGER.setLevel(Level.INFO);

		try {
			FileHandler fileTxt = new FileHandler("./logging.txt");
			LOGGER.addHandler(fileTxt);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.model = model;
	}

	@Override
	public void run() {
		List<String> wordlist;

		try {
			wordlist = createWordList();
			LOGGER.info("Created word list of " + wordlist.size() + " words.");
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
			e.printStackTrace();
			wordlist = new ArrayList<>();
		}

		model.setWordList(wordlist);
		model.generateCurrentWord();
	}

	private List<String> createWordList() throws IOException {

		List<String> wordlist = new ArrayList<>();

		//ClassLoader loader = this.getClass().getClassLoader();
		//InputStream stream = loader.getResourceAsStream(text);
		String dir=System.getProperty("user.home");
		System.out.println(dir);
		dir = dir + "\\Java4thsem\\Project\\Wordle\\resources\\usa.txt";

		File file = new File(dir);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;

		while((str = br.readLine()) != null){
				wordlist.add(str);
		}
		br.close();


/*		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		String line = reader.readLine();
		while (line != null) {
			line = line.trim();
			if (line.length() == minimum) {
				wordlist.add(line);
			}
			line = reader.readLine();
		}
		reader.close();
*/
		return wordlist;
	}
	
}