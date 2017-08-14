package io.parsel;

import org.junit.Test;

import static org.junit.Assert.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Selector class tests.
 * 
 * 
 * @author Talha Ashraf
 */


public class SelectorTest {
    private final String html = "<html>" + 
                                    "<head>" +
                                        "<title>Selector List</title>" +
                                    "</head>" +
                                    "<body>" +
                                        "<p>Talha</p>" +
                                        "<p>Ashraf</p>" +
                                        "<boolean>" +
                                            "true" +
                                        "</boolean>" +
                                        "<number>" +
                                            "25" +
                                        "</number>" +
                                    "</body>" +
                                "</html>";
    
    @Test public void testXpath() {
        Selector selector = new Selector(html);
        SelectorList title = selector.xpath("//title");
        assertEquals(1, title.size());
        SelectorList paras = selector.xpath("//body/p");
        assertEquals(2, paras.size());
    }
    
    @Test public void testBool() {
        Selector selector = new Selector(html);
        Boolean is_true = selector.bool("//boolean");
        assertEquals(true, is_true);
    }
    
    @Test public void testNode() {
        Selector selector = new Selector(html);
        Node node = selector.node("//body");
        assertEquals("body", node.getNodeName());
    }
    
    @Test public void testNodeset() {
        Selector selector = new Selector(html);
        NodeList nodeList = selector.nodeset("//body/p");
        assertEquals(2, nodeList.getLength());
    }
    
    @Test public void testNumber() {
        Selector selector = new Selector(html);
        Double number = selector.number("//body/number");
        assertEquals(25,  number.intValue());
    }
    
    @Test public void testString() {
        Selector selector = new Selector(html);
        String value = selector.string("string(//body/p)");
        assertEquals("Talha",  value);
    }
}
