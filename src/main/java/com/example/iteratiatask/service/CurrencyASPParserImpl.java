package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyASPParserImpl implements CurrencyASPParser {

    @Value("${app.cb-page.url}")
    private String address;

    @Override
    public List<Currency> parseAll() {
        try {
            // create DOM document from url
            URL url = new URL(address);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url.openStream());

            // get root element and retrieve date value
            Node valCursNode = document.getElementsByTagName("ValCurs").item(0);
            String date = valCursNode.getAttributes().getNamedItem("Date").getNodeValue();

            List<Currency> resultList = new ArrayList<>();
            // get all currencies records
            NodeList nodes = document.getElementsByTagName("Valute");
            for (int i = 0; i < nodes.getLength(); i++) {
                // get ID for each currency
                Node valuteNode = nodes.item(i);
                String ID = valuteNode.getAttributes().getNamedItem("ID").getNodeValue();

                NodeList childNodes = nodes.item(i).getChildNodes();
                // create map to pass nodes key-values
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node node = childNodes.item(j);
                    map.put(node.getNodeName(), node.getTextContent());
                }
                // create new Currency object, init with map values
                Currency currency = new Currency(
                        ID,
                        date,
                        Integer.parseInt(map.get("NumCode")),
                        map.get("CharCode"),
                        Integer.parseInt(map.get("Nominal")),
                        map.get("Name"),
                        Double.parseDouble(map.get("Value").replace(",", "."))
                );
                // add new Currency to list, repeat...
                resultList.add(currency);
            }
            return resultList;
        } catch (IOException | SAXException | ParserConfigurationException e) {
            // todo: refactor each exception
            throw new RuntimeException("Unable to parse document: " + e.getMessage());
        }
    }
}
