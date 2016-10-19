package by.tr.epam.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document =
                builder.parse(new FileInputStream("src/by/tr/epam/xml/menu.xml"));

        List<Dish> dishList = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Dish dsh = new Dish();
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);
                    if (cNode instanceof Element) {
                        String content = cNode.getLastChild().
                                getTextContent().trim();
                        switch (cNode.getNodeName()) {
                            case "photo":
                                dsh.photo = content;
                                break;
                            case "title":
                                dsh.title = content;
                                break;
                            case "serving":
                                dsh.serving = content;
                                break;
                            case "varTitle":
                                dsh.variationContains.add(content);
                                break;
                            case "price":
                                dsh.price.add(content);
                                break;
                        }
                    }
                }
                dishList.add(dsh);
            }

        }

        for (Dish dsh : dishList) {
            System.out.println(dsh);
        }

    }
}

class Dish {
    String photo;
    String title;
    String serving;
    List<String> variationContains = new ArrayList<>();
    List<String> price = new ArrayList<>();

    @Override
    public String toString() {
        return " Photo: " + photo + "\n Title: " + title + "\n Serving: " + serving + "\n Variations: " + variationContains.toString() + "\n Price: " + price.toString() + '\n';
    }
}

