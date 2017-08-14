package io.parsel;

import javax.xml.parsers.DocumentBuilder;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Utility functions test.
 * 
 * 
 * @author Talha Ashraf
 */
public class UtilsTest {
    private final String html = "<html>" + 
                                    "<head>" +
                                        "<title>Selector List</title>" +
                                    "</head>" +
                                    "<body>" +
                                        "<p>Talha</p>" +
                                        "<p>Ashraf</p>" +
                                        "<boolean>true</boolean>" +
                                        "<number>25</number>" +
                                    "</body>" +
                                "</html>";
    
    @Test public void testGetDocumentBuilder() {
        assertTrue(Utils.getDocumentBuilder() instanceof DocumentBuilder);
    }
    
    @Test public void testGetNode() {
        assertTrue(Utils.getNode(html) instanceof Node);
    }
    
    @Test public void testNodeToString() {
        Node node = Utils.getNode(html);
        String fromNode = Utils.nodeToString(node).replace("\n", "")
                                                  .replace("\r", "");
        String newHtml = "<html>" + 
                             "<head>" +
                                 "<META http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                                 "<title>Selector List</title>" +
                             "</head>" +
                             "<body>" +
                                 "<p>Talha</p>" +
                                 "<p>Ashraf</p>" +
                                 "<boolean>true</boolean>" +
                                 "<number>25</number>" +
                             "</body>" +
                         "</html>";
        assertEquals(newHtml, fromNode);
    }
    
    @Test public void testFromXpath() {
        Node node = Utils.getNode(html);
        NodeList nodeList = Utils.fromXpath(node, "//body/p");
        assertEquals(2, nodeList.getLength());
    }
    
    @Test public void testFlatten() {
        Node node = Utils.getNode(html);
        NodeList nodeList = Utils.fromXpath(node, "//body/p");
        assertEquals(Utils.flatten(nodeList).size(), nodeList.getLength());
    }
    
    @Test public void testXPercentString() {
        String chunk = Utils.xPercentString("Hi John, how are you doing?", 27);
        assertEquals("Hi John", chunk);
    }
}
