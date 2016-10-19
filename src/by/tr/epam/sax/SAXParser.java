package by.tr.epam.sax;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParser {

    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactor = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser parser = parserFactor.newSAXParser();
        SAXHandler handler = new SAXHandler();
        parser.parse(new FileInputStream("src/by/tr/epam/xml/menu.xml"),
                handler);
        for (Dish dch : handler.dishList) {
            System.out.println(dch);
        }
    }
}

class SAXHandler extends DefaultHandler {

    List<Dish> dishList = new ArrayList<>();
    Dish dch = null;
    String content = null;

    @Override
    public void startElement(String uri, String localName,
                             String qName, Attributes attributes)
            throws SAXException {

        switch (qName) {
            case "dish":
                dch = new Dish();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName,
                           String qName) throws SAXException {
        switch (qName) {
            case "dish":
                dishList.add(dch);
                break;
            case "photo":
                dch.photo = content;
                break;
            case "title":
                dch.title = content;
                break;
            case "serving":
                dch.serving = content;
                break;
            case "varTitle":
                dch.variationContains.add(content);
                break;
            case "price":
                dch.price.add(content);
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        content = String.copyValueOf(ch, start, length).trim();
    }
}

class Dish {
    String photo;
    String title;
    String serving;
    List<String> price = new ArrayList<>();
    ;
    List<String> variationContains = new ArrayList<>();

    @Override
    public String toString() {
        return " Photo: " + photo + "\n Title: " + title + "\n Serving: " + serving + "\n Variations: " + variationContains.toString() + "\n Price: " + price.toString() + '\n';
    }
}