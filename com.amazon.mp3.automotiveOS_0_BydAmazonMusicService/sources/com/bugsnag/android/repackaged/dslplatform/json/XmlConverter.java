package com.bugsnag.android.repackaged.dslplatform.json;

import androidx.annotation.Nullable;
import com.amazon.music.destination.parser.ParserUtil;
import com.bugsnag.android.repackaged.dslplatform.json.JsonReader;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class XmlConverter {
    static final JsonReader.ReadObject<Element> Reader = new JsonReader.ReadObject<Element>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.XmlConverter.1
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonReader.ReadObject
        @Nullable
        public Element read(JsonReader jsonReader) throws IOException {
            if (jsonReader.wasNull()) {
                return null;
            }
            return XmlConverter.deserialize(jsonReader);
        }
    };
    static final JsonWriter.WriteObject<Element> Writer = new JsonWriter.WriteObject<Element>() { // from class: com.bugsnag.android.repackaged.dslplatform.json.XmlConverter.2
        @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
        public void write(JsonWriter jsonWriter, @Nullable Element element) {
            XmlConverter.serializeNullable(element, jsonWriter);
        }
    };
    private static final DocumentBuilder documentBuilder;

    static {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serializeNullable(@Nullable Element element, JsonWriter jsonWriter) {
        if (element == null) {
            jsonWriter.writeNull();
        } else {
            serialize(element, jsonWriter);
        }
    }

    public static void serialize(Element element, JsonWriter jsonWriter) {
        Document ownerDocument = element.getOwnerDocument();
        DOMImplementationLS dOMImplementationLS = (DOMImplementationLS) ownerDocument.getImplementation();
        LSSerializer lSSerializerCreateLSSerializer = dOMImplementationLS.createLSSerializer();
        LSOutput lSOutputCreateLSOutput = dOMImplementationLS.createLSOutput();
        lSOutputCreateLSOutput.setEncoding(ParserUtil.UTF_8);
        StringWriter stringWriter = new StringWriter();
        lSOutputCreateLSOutput.setCharacterStream(stringWriter);
        lSSerializerCreateLSSerializer.write(ownerDocument, lSOutputCreateLSOutput);
        StringConverter.serialize(stringWriter.toString(), jsonWriter);
    }

    public static Element deserialize(JsonReader jsonReader) throws IOException {
        if (jsonReader.last() == 34) {
            try {
                return documentBuilder.parse(new InputSource(new StringReader(jsonReader.readString()))).getDocumentElement();
            } catch (SAXException e) {
                throw jsonReader.newParseErrorAt("Invalid XML value", 0, e);
            }
        }
        return mapToXml(ObjectConverter.deserializeMap(jsonReader));
    }

    public static Element mapToXml(Map<String, Object> map) throws IOException {
        Set<String> setKeySet = map.keySet();
        if (setKeySet.size() > 1) {
            throw ParsingException.create("Invalid XML. Expecting root element", true);
        }
        String next = setKeySet.iterator().next();
        Document documentCreateDocument = createDocument();
        Element elementCreateElement = documentCreateDocument.createElement(next);
        documentCreateDocument.appendChild(elementCreateElement);
        buildXmlFromHashMap(documentCreateDocument, elementCreateElement, map.get(next));
        return elementCreateElement;
    }

    private static synchronized Document createDocument() {
        try {
        } catch (ParserConfigurationException e) {
            throw new ConfigurationException(e);
        }
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    private static void buildXmlFromHashMap(Document document, Element element, @Nullable Object obj) {
        if (obj instanceof HashMap) {
            for (Map.Entry entry : ((HashMap) obj).entrySet()) {
                String str = (String) entry.getKey();
                if (str.startsWith("@")) {
                    element.setAttribute(str.substring(1), entry.getValue().toString());
                } else if (str.startsWith("#")) {
                    if (str.equals("#text")) {
                        if (entry.getValue() instanceof List) {
                            buildTextNodeList(document, element, (List) entry.getValue());
                        } else {
                            element.appendChild(document.createTextNode(entry.getValue().toString()));
                        }
                    } else if (str.equals("#cdata-section")) {
                        if (entry.getValue() instanceof List) {
                            buildCDataList(document, element, (List) entry.getValue());
                        } else {
                            element.appendChild(document.createCDATASection(entry.getValue().toString()));
                        }
                    } else if (str.equals("#comment")) {
                        if (entry.getValue() instanceof List) {
                            buildCommentList(document, element, (List) entry.getValue());
                        } else {
                            element.appendChild(document.createComment(entry.getValue().toString()));
                        }
                    }
                } else {
                    Element elementCreateElement = document.createElement(str);
                    element.appendChild(elementCreateElement);
                    buildXmlFromHashMap(document, elementCreateElement, entry.getValue());
                }
            }
            return;
        }
        if (obj instanceof List) {
            buildXmlFromJsonArray(document, element, (List) obj);
        } else if (obj != null) {
            element.setTextContent(obj.toString());
        }
    }

    private static void buildTextNodeList(Document document, Node node, List<String> list) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        node.appendChild(document.createTextNode(sb.toString()));
    }

    private static void buildCDataList(Document document, Node node, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            node.appendChild(document.createCDATASection(it.next()));
        }
    }

    private static void buildCommentList(Document document, Node node, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            node.appendChild(document.createComment(it.next()));
        }
    }

    private static void buildXmlFromJsonArray(Document document, Node node, List<Object> list) {
        Node parentNode = node.getParentNode();
        buildXmlFromHashMap(document, (Element) node, list.get(0));
        for (Object obj : list.subList(1, list.size())) {
            Element elementCreateElement = document.createElement(node.getNodeName());
            parentNode.appendChild(elementCreateElement);
            buildXmlFromHashMap(document, elementCreateElement, obj);
        }
    }
}
