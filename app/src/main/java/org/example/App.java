package org.example;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        CircularLinkedList<String> monopolyBoard = new CircularLinkedList<>();

        List<String> lines = loadSpaces();
        for (String s : lines) {
            monopolyBoard.append(s);
        }

        System.out.println(monopolyBoard.getCurrent()); // Go
        monopolyBoard.step();
        System.out.println(monopolyBoard.getCurrent()); // Mediterranean Avenue
        monopolyBoard.step();
        monopolyBoard.step();
        monopolyBoard.step();
        System.out.println(monopolyBoard.getCurrent()); // Income Tax

        for (int i = 0; i < 37; i++) monopolyBoard.step();
        System.out.println(monopolyBoard.getCurrent()); // Mediterranean Avenue

        int roll = monopolyBoard.roll2d6AndMove();
        System.out.println("Rolled " + roll + " — now on: " + monopolyBoard.getCurrent());
    }

   private static List<String> loadSpaces() throws IOException {
    try (var br = java.nio.file.Files.newBufferedReader(java.nio.file.Paths.get("../monopoly_spaces.txt"), java.nio.charset.StandardCharsets.UTF_8)) {
            return br.lines().map(String::trim).filter(s -> !s.isEmpty() && !s.startsWith("#")).collect(java.util.stream.Collectors.toList());
        }
    }
}
