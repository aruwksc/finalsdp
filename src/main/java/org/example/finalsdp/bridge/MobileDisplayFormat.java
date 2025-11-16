package org.example.finalsdp.bridge;


public class MobileDisplayFormat implements DisplayFormat {
    @Override
    public void showHeader(String title) {
        System.out.println("\n[MOBILE SCREEN]");
        System.out.println("+--------------------------------------------+");
        System.out.println("| " + centerString(title, 42) + " |");
        System.out.println("+--------------------------------------------+");
    }

    @Override
    public void showContent(String info) {
        String[] lines = info.split("\n");
        for (String line : lines) {
            if (line.length() > 42) {
                System.out.println("| " + line.substring(0, 42) + " |");
            } else {
                System.out.println("| " + padRight(line, 42) + " |");
            }
        }
    }

    @Override
    public void showFooter() {
        System.out.println("+--------------------------------------------+\n");
    }

    private String centerString(String text, int width) {
        int padding = (width - text.length()) / 2;
        return padLeft("", padding) + text + padLeft("", width - text.length() - padding);
    }

    private String padLeft(String str, int length) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.insert(0, ' ');
        }
        return sb.toString();
    }

    private String padRight(String str, int length) {
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}