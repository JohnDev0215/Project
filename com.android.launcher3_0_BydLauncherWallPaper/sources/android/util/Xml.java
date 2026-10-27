package android.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import libcore.util.XmlObjectFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes3.dex */
public class Xml {
    public static String FEATURE_RELAXED = "http://xmlpull.org/v1/doc/features.html#relaxed";

    private Xml() {
    }

    public static void parse(String str, ContentHandler contentHandler) throws SAXException {
        try {
            XMLReader xMLReaderNewXMLReader = XmlObjectFactory.newXMLReader();
            xMLReaderNewXMLReader.setContentHandler(contentHandler);
            xMLReaderNewXMLReader.parse(new InputSource(new StringReader(str)));
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public static void parse(Reader reader, ContentHandler contentHandler) throws SAXException, IOException {
        XMLReader xMLReaderNewXMLReader = XmlObjectFactory.newXMLReader();
        xMLReaderNewXMLReader.setContentHandler(contentHandler);
        xMLReaderNewXMLReader.parse(new InputSource(reader));
    }

    public static void parse(InputStream inputStream, Encoding encoding, ContentHandler contentHandler) throws SAXException, IOException {
        XMLReader xMLReaderNewXMLReader = XmlObjectFactory.newXMLReader();
        xMLReaderNewXMLReader.setContentHandler(contentHandler);
        InputSource inputSource = new InputSource(inputStream);
        inputSource.setEncoding(encoding.expatName);
        xMLReaderNewXMLReader.parse(inputSource);
    }

    public static XmlPullParser newPullParser() {
        try {
            XmlPullParser xmlPullParserNewXmlPullParser = XmlObjectFactory.newXmlPullParser();
            xmlPullParserNewXmlPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-docdecl", true);
            xmlPullParserNewXmlPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", true);
            return xmlPullParserNewXmlPullParser;
        } catch (XmlPullParserException unused) {
            throw new AssertionError();
        }
    }

    public static XmlSerializer newSerializer() {
        return XmlObjectFactory.newXmlSerializer();
    }

    public enum Encoding {
        US_ASCII("US-ASCII"),
        UTF_8("UTF-8"),
        UTF_16("UTF-16"),
        ISO_8859_1("ISO-8859-1");

        final String expatName;

        Encoding(String str) {
            this.expatName = str;
        }
    }

    public static Encoding findEncodingByName(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return Encoding.UTF_8;
        }
        for (Encoding encoding : Encoding.values()) {
            if (encoding.expatName.equalsIgnoreCase(str)) {
                return encoding;
            }
        }
        throw new UnsupportedEncodingException(str);
    }

    public static AttributeSet asAttributeSet(XmlPullParser xmlPullParser) {
        if (xmlPullParser instanceof AttributeSet) {
            return (AttributeSet) xmlPullParser;
        }
        return new XmlPullAttributes(xmlPullParser);
    }
}
