package com.asaulyuk.view;

public class ViewSupportData {
    public enum BoxType {
        WALL_HORIZONTAL,
        WALL_VERTICAL,
        CELL
    }
    public static class Box {
        public Integer x;
        public Integer y;
        public Integer x1;
        public Integer y1;

        public Box(Integer x, Integer y, Integer width, Integer height) {
            this.x = x;
            this.y = y;
            this.x1 = x + width;
            this.y1 = y + height;
        }

    }

    public Integer x;
    public Integer y;
    public BoxType selectedType;

    public ViewSupportData(Integer x, Integer y, BoxType selectedType) {
        this.x = x;
        this.y = y;
        this.selectedType = selectedType;
    }
}
