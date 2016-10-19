package by.tr.epam.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxParser {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        List<Dish> dishList = null;
        Dish currDsh = null;
        String tagContent = null;
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader =
                factory.createXMLStreamReader(new FileInputStream("src/by/tr/epam/xml/menu.xml"));

        while (reader.hasNext()) {
            int event = reader.next();

            switch (event) {
                case XMLStreamConstants.START_ELEMENT:

                    if ("dish".equals(reader.getLocalName())) {
                        currDsh = new Dish();
                    }
                    if ("dishes".equals(reader.getLocalName())) {
                        dishList = new ArrayList<>();
                    }
                    break;


                case XMLStreamConstants.CHARACTERS:
                    tagContent = reader.getText().trim();
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    switch (reader.getLocalName()) {
                        case "dish":
                            dishList.add(currDsh);
                            break;
                        case "photo":
                            currDsh.photo = tagContent;
                            break;
                        case "title":
                            currDsh.title = tagContent;
                            break;
                        case "serving":
                            currDsh.serving = tagContent;
                            break;
                        case "varTitle":
                            currDsh.variationContains.add(tagContent);
                            break;
                        case "price":
                            currDsh.price.add(tagContent);
                            break;
                    }
                    break;
                case XMLStreamConstants.START_DOCUMENT:
                    dishList = new ArrayList<>();
                    break;
            }
        }

        for (Dish emp : dishList) {
            System.out.println(emp);
        }

    }
}

class Dish {
    String photo;
    String title;
    String serving;
    List<String> price = new ArrayList<>();
    List<String> variationContains = new ArrayList<>();

    @Override
    public String toString() {
        return " Photo: " + photo + "\n Title: " + title + "\n Serving: " + serving + "\n Variations: " + variationContains.toString() + "\n Price: " + price.toString() + '\n';
    }
}