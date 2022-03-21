package com.example.iteratiatask.service;

import com.example.iteratiatask.entity.Currency;
import com.example.iteratiatask.entity.ExchangeRate;
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

    private final Document document;

    public CurrencyASPParserImpl(@Value("${app.cb-page.url}") String address) throws IOException, ParserConfigurationException, SAXException {
        // create DOM document from url
        URL url = new URL(address);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        document = db.parse(url.openStream());
    }

    @Override
    public List<Currency> getAll() {
        List<Currency> resultList = new ArrayList<>();
        // get all currencies records
        NodeList nodes = document.getElementsByTagName("Valute");
        for (int i = 0; i < nodes.getLength(); i++) {
            // get ID for each currency
            Node valuteNode = nodes.item(i);
            String ID = valuteNode.getAttributes().getNamedItem("ID").getNodeValue();
            // create map to pass nodes key-values
            NodeList childNodes = nodes.item(i).getChildNodes();
            Map<String, String> map = getNodeValueMap(childNodes);
            // create new Currency object, init with map values
            Currency currency = new Currency(
                    ID,
                    Integer.parseInt(map.get("NumCode")),
                    map.get("CharCode"),
                    Integer.parseInt(map.get("Nominal")),
                    map.get("Name")
            );
            // add new Currency to list, repeat...
            resultList.add(currency);
        }
        return resultList;
    }

    private Map<String, String> getNodeValueMap(NodeList list) {
        Map<String, String> map = new HashMap<>();
        for (int j = 0; j < list.getLength(); j++) {
            Node node = list.item(j);
            map.put(node.getNodeName(), node.getTextContent());
        }
        return map;
    }

    @Override
    public Currency getByCharCode(String charCode) {
        // get list of elements, return one with the charCode
        List<Currency> currencies = getAll();
        return currencies.stream()
                .filter(e -> e.getCharCode().equals(charCode))
                .findFirst()
                .orElseThrow();

    }

    @Override
    public String getDate() {
        // get root element and retrieve date value
        Node valCursNode = document.getElementsByTagName("ValCurs").item(0);
        return valCursNode.getAttributes().getNamedItem("Date").getNodeValue();
    }

    @Override
    public ExchangeRate getExchangeRateById(String id) {
        String date = getDate();
        Map<String, String> map = new HashMap<>();
        // get all 'Valute' nodes..
        NodeList list = document.getElementsByTagName("Valute");
        for (int i = 0; i < list.getLength(); i++) {
            // find one with the ID
            String idFound = list.item(i).getAttributes().getNamedItem("ID").getNodeValue();
            if (id.equals(idFound)) {
                NodeList childs = list.item(i).getChildNodes();
                // store to map
                map = getNodeValueMap(childs);
            }
        }
        // construct and return new ExchangeRate
        return new ExchangeRate(
                date, Double.parseDouble(map.get("Value").replace(",", "."))
        );
    }

}
