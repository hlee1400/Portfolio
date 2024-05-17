package csc402;

import java.util.ArrayList;
import java.util.List;

public class CCAnalysis {

    //public static method that does analysis and returns the objects with the results
    public static CCResults separatingCC(List<CC> pixelList) {
        List<CC> rectangleList = new ArrayList<>();
        List<CC> triangleList = new ArrayList<>();

        for(CC cc : pixelList) {

            int pixelSize = cc.pixelList.size();

            double boundingBoxArea = cc.boundingBox.width * cc.boundingBox.height;

            double ratio = pixelSize / boundingBoxArea;

            if (ratio > 0.75) {
                rectangleList.add(cc);
            } else {
                triangleList.add(cc);
            }
        }
        CCResults totalLists = new CCResults(rectangleList, triangleList);
        return totalLists;
    }



}