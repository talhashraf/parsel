# Parsel
--------
Parsel is a Java library to extract data from HTML/XML using XPath selector. The project is inspired by [Python Parsel library](https://github.com/scrapy/parsel/).

# Example
---------
    import io.parsel.Selector;
    import io.parsel.SelectorList;

    String text = "<html><head><title>Selector List</title></head></html>";
    Selector selector = new Selector(text);
    SelectorList title = selector.xpath("//title/text()");
    System.out.println(title.extract()[0]);
