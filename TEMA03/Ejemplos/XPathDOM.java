package TEMA03.Ejemplos;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathDOM {
    public static void main(String[] args)  {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(".\\fichero.xml");
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "/libraries/library";
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);

            for(int i=0;i<nodeList.getLength();i++){
                Node nNode = nodeList.item(i);
                System.out.println("\nElemento Actual:" + nNode.getNodeName());
                System.out.println("\nElemento Padre:" + nNode.getParentNode());

                if(nNode.getNodeType()== Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    System.out.println("Nombre :" +eElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Ciudad :" +eElement.getAttribute("location"));
                    System.out.println("");

                    String bookExpression = "books/book";
                    NodeList bookNodeList = (NodeList) xPath.compile(bookExpression).evaluate(nNode, XPathConstants.NODESET);

                    for (int j = 0; j < bookNodeList.getLength(); j++) {
                        Node bookNode = bookNodeList.item(j);
                        if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element bookElement = (Element) bookNode;
                            System.out.println("Título del Libro: " + bookElement.getElementsByTagName("title").item(0).getTextContent());
                            System.out.println("Autor del Libro: " + bookElement.getElementsByTagName("author").item(0).getTextContent());
                            System.out.println("Género del Libro: " + bookElement.getElementsByTagName("genre").item(0).getTextContent());
                            System.out.println("Año del Libro: " + bookElement.getElementsByTagName("year").item(0).getTextContent());
                            System.out.println("-----");
                        }
                    }
                    System.out.println("==========");
                }
            }

        } catch (ParserConfigurationException e) {
      System.err.println("Error de configuración del analizador XML: PARSER CONFIGURATION EXCEPTION " + e.getMessage());
    } catch (IOException e) {
      System.err.println("Error de lectura del archivo XML: " + e.getMessage());
    } catch (XPathExpressionException e) {
      System.err.println("Error en la expresión XPath: " + e.getMessage());
    } catch (SAXException e) {
      System.err.println(
        "Error SAX durante el análisis XML: " + e.getMessage());
    }
    }
}
