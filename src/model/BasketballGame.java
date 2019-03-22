package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class BasketballGame {

	public static String ARCHIVE = "./data/scores.txt";

	private ArrayList<Integer> scores;
	private int totalLose;
	private int totalWin;
	private int size;
	/**
	 * [0]: ll [1]: lw [2]: wl [3]: ww
	 */
	private Double[] totalScores;

	public BasketballGame(int size) {
		scores = new ArrayList<Integer>();
		totalScores = new Double[4];
		this.size = size;
		totalLose = 0;
		totalWin = 0;

		try {
			load(ARCHIVE);
		} catch (Exception e) {
			// TODO: handle exception
		}
		consecutives();

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

	private void totalScores() {

		Double ww = 0.0, wl = 0.0, ll = 0.0, lw = 0.0;
		for (int i = 1; i < scores.size() - 1; i++) {
			int actual = scores.get(i);

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

		totalScores[0] = ll;
		totalScores[1] = lw;
		totalScores[2] = wl;
		totalScores[3] = ww;

	}

	private ArrayList<Double> consecutives() {

		ArrayList<Double> consecutives = new ArrayList<Double>();

		totalScores();

		if (size == 2) {

			consecutives.add(totalScores[0] / totalLose);
			consecutives.add(totalScores[1] / totalLose);
			consecutives.add(totalScores[2] / totalWin);
			consecutives.add(totalScores[3] / totalWin);

		} else {

			int www = 0, wwl = 0, wlw = 0, wll = 0, lll = 0, llw = 0, lwl = 0, lww = 0;

			for (int i = 1; i < scores.size() - 2; i++) {
				int actual = scores.get(i);

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

			consecutives.add(lll / totalScores[0]);
			consecutives.add(llw / totalScores[0]);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(lwl / totalScores[1]);
			consecutives.add(lww / totalScores[1]);
			consecutives.add(wll / totalScores[2]);
			consecutives.add(wlw / totalScores[2]);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(0.0);
			consecutives.add(wwl / totalScores[3]);
			consecutives.add(www / totalScores[3]);

		}

		return consecutives;
	}

	public String[][] generateMatrix() {

		String win = "Ganar";
		String loose = "Perder";
		String[][] matrix;
//		DecimalFormat df = new DecimalFormat("#.######");
//		df.setRoundingMode(RoundingMode.CEILING);
		if (size == 2) {
			matrix = new String[3][3];
			int k = 0;
			matrix[0][1] = win;
			matrix[0][2] = loose;
			matrix[1][0] = win;
			matrix[2][0] = loose;

			for (int i = 1; i < matrix.length; i++) {
				for (int j = 1; j < matrix[0].length; j++) {

//					matrix[i][j] = (df.format(consecutives().get(k)));
					matrix[i][j] = ((double)Math.round(consecutives().get(k) * 100d) / 100d)+"";
					k++;
				}
			}

		} else {
			matrix = new String[5][5];

			int k = 0;
			matrix[0][1] = loose + "-" + loose;
			matrix[0][2] = loose + "-" + win;
			matrix[0][3] = win + "-" + loose;
			matrix[0][4] = win + "-" + win;

			matrix[1][0] = loose + "-" + loose;
			matrix[2][0] = loose + "-" + win;
			matrix[3][0] = win + "-" + loose;
			matrix[4][0] = win + "-" + win;

			for (int i = 1; i < matrix.length; i++) {
				for (int j = 1; j < matrix[0].length; j++) {
					
					
					matrix[i][j] = ((double)Math.round(consecutives().get(k) * 100d) / 100d)+"";
					k++;
				}
			}
		}

		return matrix;
	}

	public static void main(String[] args) {
		BasketballGame bg = new BasketballGame(3);
		String[][] a = bg.generateMatrix();


	}

}
