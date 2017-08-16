package io.parsel;

import static org.junit.Assert.*;
import org.junit.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SelectorList class tests.
 * 
 * 
 * @author Talha Ashraf
 */


public class SelectorListTest {
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
        SelectorList paras = selector.xpath("//body").xpath("p");
        assertEquals(2, paras.size());
    }
    
    @Test public void testBool() {
        Selector selector = new Selector(html);
        Boolean[] bools = selector.xpath("//body").bool("boolean");
        assertTrue(bools[0]);
    }
    
    @Test public void testNode() {
        Selector selector = new Selector(html);
        Node[] nodes = selector.xpath("//body").node("p");
        assertEquals("p", nodes[0].getNodeName());
    }
    
    @Test public void testNodeset() {
        Selector selector = new Selector(html);
        NodeList[] nodeLists = selector.xpath("//body").nodeset("p");
        assertEquals(2, nodeLists[0].getLength());
    }
    
    @Test public void testNumber() {
        Selector selector = new Selector(html);
        Double[] numbers = selector.xpath("//body").number("number");
        assertEquals(25, numbers[0].intValue());
    }
    
    @Test public void testString() {
        Selector selector = new Selector(html);
        String[] values = selector.xpath("//body/p").string("string()");
        assertEquals("Talha",  values[0]);
    }
    
    @Test public void testGet() {
        Selector selector = new Selector(html).xpath("//body").get(0);
        assertEquals("body", selector.root.getNodeName());
    }
    
    @Test public void testItem() {
        Selector selector = new Selector(html);
        SelectorList head = selector.xpath("//body | //head").item(1);
        assertEquals(1, head.size());
    }
    
    @Test public void testExtract() {
        Selector selector = new Selector(html);
        SelectorList values = selector.xpath("//body/p/text()");
        assertEquals("Talha", values.extract()[0]);
    }
}
