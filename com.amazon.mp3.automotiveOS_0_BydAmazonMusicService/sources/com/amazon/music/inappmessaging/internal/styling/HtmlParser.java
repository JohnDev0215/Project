package com.amazon.music.inappmessaging.internal.styling;

import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import java.util.ArrayDeque;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* JADX INFO: loaded from: classes4.dex */
public class HtmlParser implements Html.TagHandler, ContentHandler {
    private final TagHandler handler;
    private ArrayDeque<Boolean> tagStatus = new ArrayDeque<>();
    private Editable text;
    private ContentHandler wrapped;

    public interface TagHandler {
        boolean handleTag(boolean opening, String tag, Editable output, Attributes attributes);
    }

    public static Spanned buildSpannedText(String html, TagHandler handler) {
        return Html.fromHtml("<inject/>" + html, null, new HtmlParser(handler));
    }

    public static String getValue(Attributes attributes, String name) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            if (name.equals(attributes.getLocalName(i))) {
                return attributes.getValue(i);
            }
        }
        return null;
    }

    private HtmlParser(TagHandler handler) {
        this.handler = handler;
    }

    @Override // android.text.Html.TagHandler
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (this.wrapped == null) {
            this.text = output;
            this.wrapped = xmlReader.getContentHandler();
            xmlReader.setContentHandler(this);
            this.tagStatus.addLast(Boolean.FALSE);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        boolean zHandleTag = this.handler.handleTag(true, localName, this.text, attributes);
        this.tagStatus.addLast(Boolean.valueOf(zHandleTag));
        if (zHandleTag) {
            return;
        }
        this.wrapped.startElement(uri, localName, qName, attributes);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!this.tagStatus.removeLast().booleanValue()) {
            this.wrapped.endElement(uri, localName, qName);
        }
        this.handler.handleTag(false, localName, this.text, null);
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        this.wrapped.setDocumentLocator(locator);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        this.wrapped.startDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        this.wrapped.endDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        this.wrapped.startPrefixMapping(prefix, uri);
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String prefix) throws SAXException {
        this.wrapped.endPrefixMapping(prefix);
    }

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.wrapped.characters(ch, start, length);
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        this.wrapped.ignorableWhitespace(ch, start, length);
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String target, String data) throws SAXException {
        this.wrapped.processingInstruction(target, data);
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String name) throws SAXException {
        this.wrapped.skippedEntity(name);
    }
}
