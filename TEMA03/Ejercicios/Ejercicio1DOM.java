package TEMA03.Ejercicios;

//ESTRUCTURA DEL XML (streetwear.xml):
//- <catalogo_streetwear>: Nodo raíz que engloba todo el inventario.
//- <prenda id="...">: Nodo que representa cada artículo de ropa, identificado por un ID único.
//- Etiquetas de texto simple: <tipo>, <marca>, <color>, <talla> y <stock>.
//- Etiquetas con particularidades: 
//     -> <precio>: Incluye el atributo 'moneda' (ej. moneda="EUR").
//     -> <modelo>: Es una etiqueta opcional (solo algunas prendas, como las zapatillas, la tienen).

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Ejercicio1DOM {
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(".\\fichero.xml");

            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList libraryList = doc.getElementsByTagName("library");

            for (int i = 0; i < libraryList.getLength(); i++) {
                Node libraryNode = libraryList.item(i);

                if (libraryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element libraryElement = (Element) libraryNode;

                    String location = libraryElement.getAttribute("location");
                    String name = libraryElement.getElementsByTagName("name").item(0).getTextContent();

                    System.out.println("Biblioteca: " + name + " (" + location + ")");

                    NodeList bookList = libraryElement.getElementsByTagName("book");
                    int totalBooks = 0;

                    for (int j = 0; j < bookList.getLength(); j++) {
                        Node bookNode = bookList.item(j);
                        
                        if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element bookElement = (Element) bookNode;
                            
                            String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                            String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                            String year = bookElement.getElementsByTagName("year").item(0).getTextContent();
                            
                            System.out.println("- " + title + " (" + author + ", " + year + ")");
                            totalBooks++; 
                        }
                    }
                    
                    System.out.println("Total de libros: " + totalBooks);
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
