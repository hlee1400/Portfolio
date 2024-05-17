
package csc402;

/*
 * CSC 402 - Mini Project 2
 *
 * Created by: Kenny Davila
 *
 * Completed by: Austen Skopov-Normane and HyeSeung Lee
 */

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.io.FilenameFilter;


public class MP2_Main {

	/*
	 * DO NOT MODIFY UNLESS YOU WANT 0
	 *
	 * This function loads a binary image from the specified file name, and
	 * returns a boolean array representing the image
	 * */
	public static boolean[][] loadImage(String filename){
		File f = new File(filename);
		try {
			BufferedImage img_buff = ImageIO.read(f);
			Raster raster = img_buff.getData();

			int h = img_buff.getHeight();
			int w = img_buff.getWidth();
			int[] pixel = new int[3];
			boolean[][] img_bool = new boolean[h][w];
			for (int y = 0; y < h; y++) {
				for (int x = 0; x < w; x++) {
					raster.getPixel(x, y, pixel);
					img_bool[y][x] = pixel[0] > 128;
				}
			}

			return img_bool;
		} catch (Exception e) {
			System.out.println("Invalid image file");
			return null;
		}
	}

	/*
	 * This function helps you "visualize" a given image as text
	 *
	 * You can modify this to your convenience
	 * */
	public static String boolImgToString(boolean[][] img_bool) {
		StringBuffer buffer = new StringBuffer();
		for (int y = 0; y < img_bool.length; y++) {
			for (int x = 0; x < img_bool[y].length; x++) {
				buffer.append(img_bool[y][x] ? "#" : "-");
			}
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static List<CC> listPixels(boolean[][] img_bool) {
		QuickUnion unionFind = new QuickUnion(img_bool.length * img_bool[0].length);
		List<CC> CCList = new ArrayList<>();

		for(int i = 0; i < img_bool.length; i++) {
			CC foregroundPixels = new CC(img_bool.length, img_bool[0].length);
			for (int j = 0; j < img_bool[0].length; j++) {
				if (img_bool[i][j]) {
					//right
					int neighbor1 = Utility.convertTo1D(i, j, img_bool[0].length);
					foregroundPixels.add1Pixel(j, i);

					if (j + 1 < img_bool[0].length && img_bool[i][j + 1]) {
						int neighbor2 = Utility.convertTo1D(i, j + 1, img_bool[0].length);
						unionFind.union(neighbor1, neighbor2);
					} else {
						CCList.add(foregroundPixels);
						foregroundPixels = new CC(img_bool.length, img_bool[0].length);
					}
					//bottom
					if (i + 1 < img_bool.length && img_bool[i + 1][j]) {
						int neighbor2 = Utility.convertTo1D(i + 1, j, img_bool[0].length);
						unionFind.union(neighbor1, neighbor2);
					}
				}

			}

		}
		for(int i = 0; i < CCList.size(); i++) {
			for(int j = i + 1; j < CCList.size(); j++) {
				if(unionFind.connected(CCList.get(i).pixelList.get(0), CCList.get(j).pixelList.get(0))){
					CCList.get(i).combineCC(CCList.get(j));
					CCList.remove(j);
					j -= 1;
				}
			}
		}
		return CCList;

	}



	public static void main(String[] args) {


		Scanner scan = new Scanner(System.in);
		System.out.println("Please select a mode using their corresponding numbers: Image Analysis Mode (1), Data Collection Mode (2), or Exit Menu (3).");
		int modeChoice = scan.nextInt();
		while (modeChoice != 1 && modeChoice != 2 && modeChoice != 3 ) {
			System.out.println("Please select a valid mode.");
			scan.nextInt();
		}
		if(modeChoice == 1) {
			System.out.println("Please input the directory and the name of the file to analyze:");
			String filePath = scan.next();
			java.io.File file = new File(filePath);
			while (!file.exists()) {
				System.out.println("File does not exist. Please specify a different path: ");
				filePath = scan.next();
				file = new File(filePath);
			}
			long startTimePart1 = System.nanoTime();
			boolean[][] img = loadImage(filePath);
			long endTimePart1 = System.nanoTime();
			if (img == null){
				System.out.println("Could not load the input image");
				return;
			}
			if (img.length <= 100) {
				System.out.println(boolImgToString(img));
			}

			long startTimePart2 = System.nanoTime();
			List<CC> pixelList = listPixels(img);
			long endTimePart2 = System.nanoTime();

			long startTimePart3 = System.nanoTime();
			CCResults ccResults = CCAnalysis.separatingCC(pixelList);
			long endTimePart3 = System.nanoTime();

			long startTimePart4 = System.nanoTime();
			Collections.sort(ccResults.rectangleList);
			Collections.sort(ccResults.triangleList);
			long endTimePart4 = System.nanoTime();


			long part1RunTime = endTimePart1 - startTimePart1;
			System.out.println("Part 1 Execution Time: " + part1RunTime + " nano seconds");

			long part2RunTime = endTimePart2 - startTimePart2;
			System.out.println("Part 2 Execution Time: " + part2RunTime + " nano seconds");

			long part3RunTime = endTimePart3 - startTimePart3;
			System.out.println("Part 3 Execution Time: " + part3RunTime + " nano seconds");

			long part4RunTime = endTimePart4 - startTimePart4;
			System.out.println("Part 4 Execution Time: " + part4RunTime + " nano seconds");

			Collections.sort(ccResults.rectangleList);
			Collections.sort(ccResults.triangleList);
			System.out.println("Sorted Rectangle List: " + ccResults.rectangleList);
			System.out.println("Sorted Triangle List: " + ccResults.triangleList);

		}

		if(modeChoice == 2){
			System.out.println("Please provide a directory path: ");
			Scanner scan1 = new Scanner(System.in);
			String directoryPath = scan1.next();
			File dir = new File(directoryPath);

			FilenameFilter filter = (dir1, name) -> name.endsWith(".png") || name.endsWith(".jpg");

			File[] files = dir.listFiles(filter);

			while(!dir.exists() || !dir.isDirectory() || files == null || files.length == 0){
				System.out.println("Please provide a valid directory path: ");
				directoryPath = scan1.next();
				dir = new File(directoryPath);
				files = dir.listFiles(filter);
			}

			List<DataCollection.ImageAnalysisResult> results = new ArrayList<>();

			for (File file : files) {
				boolean[][] image = loadImage(file.getAbsolutePath()); // Implement loadImage accordingly
				DataCollection.ImageAnalysisResult result = DataCollection.collectDataForImage(file.getName(), image);
				results.add(result);
			}


		}


	}

}





