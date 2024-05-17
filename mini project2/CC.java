package csc402;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CC implements Comparable<CC> {

    //add custom Rectangle class OR built in

    public int sizeRange;
    public List<Integer> pixelList;
    public Rectangle boundingBox;

    public int height;

    public int width;

    public CC(int height, int width) {
        this.sizeRange = 0;
        this.pixelList = new ArrayList<>();
        this.boundingBox = null;
        this.height = height;
        this.width = width;

    }

    public void add1Pixel(int x, int y) {
        int pixel1D = Utility.convertTo1D(y, x, width);
        pixelList.add(pixel1D);
        if(boundingBox == null) {
            boundingBox = new Rectangle(x, y, 1, 1);
        } else {
            boundingBox = boundingBox.union(new Rectangle(x, y, 1, 1));
        }
    }

    public void combineCC(CC cc) {
        pixelList.addAll(cc.pixelList);
        boundingBox = boundingBox.union(cc.boundingBox);
    }

    public int calculateSizeRange() {
        return Math.floorDiv(pixelList.size(), 20);
    }

    public int compareTo(CC otherCC) {
        int sizeRangeOtherCC = otherCC.calculateSizeRange();
        int sizeRangeCC = this.calculateSizeRange();

        if(sizeRangeCC - sizeRangeOtherCC == 0) {
            if (this.boundingBox.x - otherCC.boundingBox.x == 0) {
                return this.boundingBox.y - otherCC.boundingBox.y;
            } return this.boundingBox.x - otherCC.boundingBox.x;
        } return sizeRangeCC - sizeRangeOtherCC;

    }


    public String toString() {
        return String.format("x: %d, y: %d, width: %d, height: %d, size: %d", boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height, calculateSizeRange());
    }
}