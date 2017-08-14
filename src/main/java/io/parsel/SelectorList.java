package io.parsel;

import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SelectorList is a collection of Selector classes and finds elements of specific Selector that matches XPath query.
 * 
 * 
 * @author Talha Ashraf
 */


public class SelectorList {
    private final ArrayList<Selector> list;

    public SelectorList(ArrayList<Selector> list) {
        this.list = list;
    }

    public SelectorList xpath(String xpath) {
        ArrayList<Selector> newList = new ArrayList<>();
        for (Selector selector: this.list) {
            newList.addAll(Utils.flatten(selector.nodeset(xpath)));
        }
        return new SelectorList(newList);
    }

    public Boolean[] bool(String xpath) {
        Boolean[] bools = new Boolean[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            bools[i] = selector.bool(xpath);
        }
        return bools;
    }

    public Node[] node(String xpath) {
        Node[] nodes = new Node[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            nodes[i] = selector.node(xpath);
        }
        return nodes;
    }

    public NodeList[] nodeset(String xpath) {
        NodeList[] nodesets = new NodeList[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            nodesets[i] = selector.nodeset(xpath);
        }
        return nodesets;
    }

    public Double[] number(String xpath) {
        Double[] doubles = new Double[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            doubles[i] = selector.number(xpath);
        }
        return doubles;
    }

    public String[] string(String xpath) {
        String[] strings = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            strings[i] = selector.string(xpath);
        }
        return strings;
    }

    public Selector get(int index) {
        return this.list.get(index);
    }

    public SelectorList item(int index) {
        ArrayList<Selector> newList = new ArrayList<>();
        newList.add(this.get(index));
        return new SelectorList(newList);
    }

    public String[] extract() {
        String[] strings = new String[this.size()];
        for (int i = 0; i < this.size(); i++) {
            Selector selector = this.get(i);
            strings[i] = selector.root.getNodeValue();
            if (strings[i] == null) {
                strings[i] = selector.text;
            }
        }
        return strings;
    }

    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String result = "[";
        String prepend_string = null;
        for (Selector selector: this.list) {
            if (prepend_string != null) {
                result += prepend_string;
            }
            result += selector.toString();
            prepend_string = ", ";
        }
        return result + "]";
    }
}

