package com.amazon.identity.auth.device;

import android.util.Log;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class qd extends rd {
    public static final String d = "com.amazon.identity.auth.device.qd";
    public static final DocumentBuilderFactory e = DocumentBuilderFactory.newInstance();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f518a;
    public final String b;
    public List<rd> c;

    public qd(String str, String str2, rd... rdVarArr) {
        this.f518a = str;
        this.b = str2;
        LinkedList linkedList = new LinkedList();
        this.c = linkedList;
        linkedList.addAll(Arrays.asList(rdVarArr));
    }

    public qd a(String str, String str2) {
        this.c.add(new qd(str, str2, new nd[0]));
        return this;
    }

    public String a() {
        try {
            Document documentNewDocument = e.newDocumentBuilder().newDocument();
            documentNewDocument.appendChild(a(documentNewDocument));
            return a((Node) documentNewDocument);
        } catch (ParserConfigurationException e2) {
            Log.e(ga.a(d), "convertToString: Unable to construct an XML Document since the document factory could not be constructed. Error: " + e2.getMessage());
            return null;
        }
    }

    public static String a(Node node) {
        try {
            Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
            transformerNewTransformer.setOutputProperty("omit-xml-declaration", "no");
            StringWriter stringWriter = new StringWriter();
            transformerNewTransformer.transform(new DOMSource(node), new StreamResult(stringWriter));
            return stringWriter.getBuffer().toString();
        } catch (TransformerConfigurationException e2) {
            Log.e(ga.a(d), "convertDocumentToString: Unable to convert XML Document to text since the transformer could not be constructed. Error: " + e2.getMessage());
            return null;
        } catch (TransformerException e3) {
            Log.e(ga.a(d), "convertDocumentToString: Unable to convert XML Document to text. Error: " + e3.getMessage());
            return null;
        }
    }

    @Override // com.amazon.identity.auth.device.rd
    public void a(Element element) {
        element.appendChild(a(element.getOwnerDocument()));
    }

    public final Element a(Document document) {
        Element elementCreateElement = document.createElement(this.f518a);
        String str = this.b;
        if (str != null) {
            elementCreateElement.setTextContent(str);
        }
        Iterator<rd> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().a(elementCreateElement);
        }
        return elementCreateElement;
    }
}
