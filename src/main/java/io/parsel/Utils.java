package io.parsel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Basic utility functions used within the project.
 * 
 * 
 * @author Talha Ashraf
 */


public class Utils {
    private static final XPath XPATH = XPathFactory.newInstance().newXPath();

    public static DocumentBuilder getDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch(ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Node getNode(String text) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(text.getBytes());
            return (Node) getDocumentBuilder().parse(bais);
        } catch(IOException|SAXException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static NodeList fromXpath(Node node, String xpath) {
        return (NodeList) fromXpath(node, xpath, XPathConstants.NODESET);
    }

    public static Object fromXpath(Node node, String xpath, QName type) {
        try {
            return XPATH.evaluate(xpath, node, type);
        } catch(XPathExpressionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static ArrayList<Selector> flatten(NodeList nodeList) {
        ArrayList<Selector> listOfSelector = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            listOfSelector.add(new Selector(nodeList.item(i)));
        }
        return listOfSelector;
    }

    public static String xPercentString(String text, int percent) {
        int index = (text.length() / 100) * percent;
        String remove_text = text.substring(index);
        return text.replace(remove_text, "");
    }
}

