package csc402;


import java.util.*;


public class DataCollection {

    public static class ImageAnalysisResult {
        int resolution;
        int ccCount;
        long averageExecutionTime;

        String imageName;
        public ImageAnalysisResult(String imageName, int resolution, int ccCount, long averageExecutionTime) {
            this.resolution = resolution;
            this.ccCount = ccCount;
            this.averageExecutionTime = averageExecutionTime;
            this.imageName = imageName;

        }
    }


    public static ImageAnalysisResult collectDataForImage(String imageName, boolean[][] img) {
        int resolution = img.length * img[0].length;
        long totalExecutionTime = 0;
        int totalCCCount = 0;
        for (int i = 0; i < 5; i++) {
            long startTime = System.nanoTime();
            List<CC> pixelList = MP2_Main.listPixels(img);
            long endTime = System.nanoTime();
            totalExecutionTime += (endTime - startTime);
            totalCCCount += pixelList.size();
        }
        long averageExecutionTime = totalExecutionTime / 5;
        int averageCCCount = totalCCCount / 5;

        System.out.println("Image: " + imageName);
        System.out.println("Resolution: " + resolution);
        System.out.println("CC Count: " + averageCCCount);
        System.out.println("Average Execution Time: " + averageExecutionTime + " nanoseconds");

        return new ImageAnalysisResult(imageName, resolution, averageCCCount, averageExecutionTime);
    }


}
