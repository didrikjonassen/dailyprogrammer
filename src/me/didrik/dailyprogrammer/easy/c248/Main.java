package me.didrik.dailyprogrammer.easy.c248;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Main {

    Image image;

    public static void main(String[] args) throws IOException {
        File file = new File("src\\me\\didrik\\dailyprogrammer\\easy\\c248\\" + "input.txt");
        Main main = new Main();
        Files.lines(file.toPath()).forEach(main::parseLine);
        File output = new File("src\\me\\didrik\\dailyprogrammer\\easy\\c248\\" + "output.ppm");
        Files.deleteIfExists(output.toPath());
        Files.createFile(output.toPath());
        Files.write(output.toPath(), main.image.toString().getBytes(StandardCharsets.US_ASCII));
    }

    private void parseLine(String line) {
        String[] parts = line.split("\\s");
        int[] ints = parseInts(parts);
        switch (parts[0]) {
            case "point":
                image.drawPoint(ints[0], ints[1], ints[2], ints[3], ints[4]);
                break;
            case "line":
                image.drawLine(ints[0], ints[1], ints[2], ints[3], ints[4], ints[5], ints[6]);
                break;
            case "rect":
                image.drawRectangle(ints[0], ints[1], ints[2], ints[3], ints[4], ints[5], ints[6]);
                break;
            default:
                image = new Image(Integer.parseInt(parts[0]), ints[0]);
        }
    }

    private int[] parseInts(String[] strings) {
        int[] out = new int[strings.length - 1];
        for (int i = 1; i < strings.length; i++) {
            out[i - 1] = Integer.parseInt(strings[i]);
        }
        return out;
    }
}
