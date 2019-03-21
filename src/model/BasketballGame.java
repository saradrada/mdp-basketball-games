package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class BasketballGame {

	public static String ARCHIVE = "./data/scores.txt";

	private ArrayList<Integer> scores;
	private int totalLose;
	private int totalWin;

	public BasketballGame() {
		scores = new ArrayList<Integer>();
		totalLose = 0;
		totalWin = 0;

		try {
			load(ARCHIVE);
		} catch (Exception e) {
			// TODO: handle exception
		}
		consecutives(3);

	}

	private void load(String archive) throws IOException, ParseException {
		String line;
		int score = -1;
		FileReader file = new FileReader(archive);
		BufferedReader br = new BufferedReader(file);
		line = br.readLine();

		while (line != null && !line.equals("")) {
			score = Integer.parseInt(line);
			scores.add(score);
			if (score == 0) {
				totalLose++;
			} else {
				totalWin++;
			}
			line = br.readLine();
		}
		br.close();

	}

	private ArrayList<Integer> consecutives(int size) {

		ArrayList<Integer> consecutives = new ArrayList<Integer>();
		int actual;
		if (size == 2) {
			int ww = 0, wl = 0, ll = 0, lw = 0;
			for (int i = 1; i < scores.size() - 1; i++) {
				actual = scores.get(i);

				switch (actual) {
				case 1:

					if (scores.get(i + 1) == 1) {
						ww++;
					} else {
						wl++;
					}

					break;
				case 0:
					if (scores.get(i + 1) == 1) {
						lw++;
					} else {
						ll++;
					}
					break;
				}
			}
			consecutives.add(ww);
			consecutives.add(wl);
			consecutives.add(ll);
			consecutives.add(lw);
		} else {

			int www = 0, wwl = 0, wlw = 0, wll = 0, lll = 0, llw = 0, lwl = 0, lww = 0;

			for (int i = 1; i < scores.size() - 2; i++) {
				actual = scores.get(i);

				switch (actual) {

				case 1:
					switch (scores.get(i + 1)) {
					case 1:
						if (scores.get(i + 2) == 1) {
							www++;
						} else {
							wwl++;
						}

						break;

					case 0:
						if (scores.get(i + 2) == 1) {
							wlw++;
						} else {
							wll++;
						}
						break;
					}
					break;
				case 0:

					switch (scores.get(i + 1)) {
					case 1:
						if (scores.get(i + 2) == 1) {
							lww++;
						} else {
							lwl++;
						}

						break;

					case 0:
						if (scores.get(i + 2) == 1) {
							llw++;
						} else {
							lll++;
						}
						break;
					}
					break;
				}
			}
			consecutives.add(www);
			consecutives.add(wwl);
			consecutives.add(wlw);
			consecutives.add(wll);
			consecutives.add(lll);
			consecutives.add(llw);
			consecutives.add(lwl);
			consecutives.add(lww);
		}

		return consecutives;
	}

	public static void main(String[] args) {
		BasketballGame bg = new BasketballGame();

	}

}
