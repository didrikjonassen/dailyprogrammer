package me.didrik.dailyprogrammer.easy.c248;

public class Image {
    private int x;
    private int y;
    int[] image;

    public Image(int x, int y) {
        this.x = x;
        this.y = y;
        image = new int[x * y * 3];
    }

    private void setPixel(int x, int y, int red, int green, int blue) {
        int initialPos = (y * this.x + x) * 3;
        image[initialPos++] = red;
        image[initialPos++] = green;
        image[initialPos] = blue;
    }

    public void drawPoint(int red, int green, int blue, int y, int x) {
        setPixel(x, y, red, green, blue);
    }

    public void drawRectangle(int red, int green, int blue, int y, int x, int height, int width) {
        for (int i = y; i < y + height; i++) {
            for (int k = x; k < x + width; k++) {
                setPixel(k, i, red, green, blue);
            }
        }
    }

    public void drawLine(int red, int green, int blue, int y1, int x1, int y2, int x2) {
        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
            if (x1 > x2) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
            for (int i = x1; i <= x2; i++) {
                setPixel(i, (int) Math.round(y1 + (double) (y2 - y1) / (x2 - x1) * (i - x1)), red, green, blue);
            }
        } else {
            if (y1 > y2) {
                int t = x1;
                x1 = x2;
                x2 = t;
                t = y1;
                y1 = y2;
                y2 = t;
            }
            for (int i = y1; i <= y2; i++) {
                setPixel((int) Math.round(x1 + (double) (x2 - x1) / (y2 - y1) * (i - y1)), i, red, green, blue);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(4 * x * y);
        sb.append("P3 ");
        sb.append(x);
        sb.append(" ");
        sb.append(y);
        sb.append(" 255 ");
        for (int i :
                image) {
            sb.append(i);
            sb.append(" ");
        }
        return sb.toString();
    }
}
