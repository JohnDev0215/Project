package com.fasterxml.jackson.module.jaxb.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public class DomElementJsonDeserializer extends StdDeserializer<Element> {
    private static final long serialVersionUID = 1;
    private final DocumentBuilder builder;

    public DomElementJsonDeserializer() {
        super((Class<?>) Element.class);
        try {
            DocumentBuilderFactory documentBuilderFactoryNewInstance = DocumentBuilderFactory.newInstance();
            documentBuilderFactoryNewInstance.setNamespaceAware(true);
            documentBuilderFactoryNewInstance.setExpandEntityReferences(false);
            this.builder = documentBuilderFactoryNewInstance.newDocumentBuilder();
            try {
                documentBuilderFactoryNewInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
            } catch (Exception unused) {
            }
        } catch (ParserConfigurationException unused2) {
            throw new RuntimeException();
        }
    }

    public DomElementJsonDeserializer(DocumentBuilder documentBuilder) {
        super((Class<?>) Element.class);
        this.builder = documentBuilder;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Element deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return fromNode(jsonParser, this.builder.newDocument(), (JsonNode) jsonParser.readValueAsTree());
    }

    protected Element fromNode(JsonParser jsonParser, Document document, JsonNode jsonNode) throws IOException {
        String strAsText = jsonNode.get("namespace") != null ? jsonNode.get("namespace").asText() : null;
        String strAsText2 = jsonNode.get("name") != null ? jsonNode.get("name").asText() : null;
        if (strAsText2 == null) {
            throw JsonMappingException.from(jsonParser, "No name for DOM element was provided in the JSON object.");
        }
        Element elementCreateElementNS = document.createElementNS(strAsText, strAsText2);
        JsonNode jsonNode2 = jsonNode.get("attributes");
        if (jsonNode2 != null && (jsonNode2 instanceof ArrayNode)) {
            Iterator<JsonNode> itElements = jsonNode2.elements();
            while (itElements.hasNext()) {
                JsonNode next = itElements.next();
                String strAsText3 = next.get("namespace") != null ? next.get("namespace").asText() : null;
                String strAsText4 = next.get("name") != null ? next.get("name").asText() : null;
                String strAsText5 = next.get("$") != null ? next.get("$").asText() : null;
                if (strAsText4 != null) {
                    elementCreateElementNS.setAttributeNS(strAsText3, strAsText4, strAsText5);
                }
            }
        }
        JsonNode jsonNode3 = jsonNode.get("children");
        if (jsonNode3 != null && (jsonNode3 instanceof ArrayNode)) {
            Iterator<JsonNode> itElements2 = jsonNode3.elements();
            while (itElements2.hasNext()) {
                JsonNode next2 = itElements2.next();
                String strAsText6 = next2.get("name") != null ? next2.get("name").asText() : null;
                String strAsText7 = next2.get("$") != null ? next2.get("$").asText() : null;
                if (strAsText7 != null) {
                    elementCreateElementNS.appendChild(document.createTextNode(strAsText7));
                } else if (strAsText6 != null) {
                    elementCreateElementNS.appendChild(fromNode(jsonParser, document, next2));
                }
            }
        }
        return elementCreateElementNS;
    }
}
