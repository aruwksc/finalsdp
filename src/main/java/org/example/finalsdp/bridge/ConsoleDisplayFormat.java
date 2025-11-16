package org.example.finalsdp.bridge;


public class ConsoleDisplayFormat implements DisplayFormat {
    @Override
    public void showHeader(String title) {
        System.out.println("\n" + repeatChar('=', 45));
        System.out.println("  " + title);
        System.out.println(repeatChar('=', 45));
    }

    @Override
    public void showContent(String info) {
        System.out.println(info);
    }

    @Override
    public void showFooter() {
        System.out.println(repeatChar('=', 45) + "\n");
    }

    private String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
}