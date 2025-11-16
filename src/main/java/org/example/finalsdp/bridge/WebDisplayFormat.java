package org.example.finalsdp.bridge;
public class WebDisplayFormat implements DisplayFormat {
    @Override
    public void showHeader(String title) {
        System.out.println("\n<html>");
        System.out.println("  <head><title>" + title + "</title></head>");
        System.out.println("  <body>");
        System.out.println("    <h1>" + title + "</h1>");
    }

    @Override
    public void showContent(String info) {
        System.out.println("    <div class='content'>");
        System.out.println("      <p>" + info + "</p>");
        System.out.println("    </div>");
    }

    @Override
    public void showFooter() {
        System.out.println("  </body>");
        System.out.println("</html>\n");
    }
}