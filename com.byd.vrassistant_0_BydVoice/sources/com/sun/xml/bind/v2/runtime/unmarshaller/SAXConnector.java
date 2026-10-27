package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.Util;
import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshallerHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class SAXConnector implements UnmarshallerHandler {
    private static final Logger logger = Util.getClassLogger();
    private final UnmarshallingContext context;
    private LocatorEx loc;
    private final XmlVisitor next;
    private final XmlVisitor.TextPredictor predictor;
    private final StringBuilder buffer = new StringBuilder();
    private final TagNameImpl tagName = new TagNameImpl();

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String str, String str2) {
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String str) {
    }

    private static final class TagNameImpl extends TagName {
        String qname;

        private TagNameImpl() {
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.TagName
        public String getQname() {
            return this.qname;
        }
    }

    public SAXConnector(XmlVisitor xmlVisitor, LocatorEx locatorEx) {
        this.next = xmlVisitor;
        this.context = xmlVisitor.getContext();
        this.predictor = xmlVisitor.getPredictor();
        this.loc = locatorEx;
    }

    @Override // javax.xml.bind.UnmarshallerHandler
    public Object getResult() throws IllegalStateException, JAXBException {
        return this.context.getResult();
    }

    public UnmarshallingContext getContext() {
        return this.context;
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator locator) {
        if (this.loc != null) {
            return;
        }
        this.loc = new LocatorExWrapper(locator);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.startDocument");
        }
        this.next.startDocument(this.loc, null);
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.endDocument");
        }
        this.next.endDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String str, String str2) throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.startPrefixMapping: {0}:{1}", new Object[]{str, str2});
        }
        this.next.startPrefixMapping(str, str2);
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String str) throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.endPrefixMapping: {0}", new Object[]{str});
        }
        this.next.endPrefixMapping(str);
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.startElement: {0}:{1}:{2}, attrs: {3}", new Object[]{str, str2, str3, attributes});
        }
        if (str == null || str.length() == 0) {
            str = "";
        }
        if (str2 == null || str2.length() == 0) {
            str2 = str3;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = str2;
        }
        processText(!this.context.getCurrentState().isMixed());
        this.tagName.uri = str;
        this.tagName.local = str2;
        this.tagName.qname = str3;
        this.tagName.atts = attributes;
        this.next.startElement(this.tagName);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String str, String str2, String str3) throws SAXException {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINER)) {
            logger2.log(Level.FINER, "SAXConnector.startElement: {0}:{1}:{2}", new Object[]{str, str2, str3});
        }
        processText(false);
        this.tagName.uri = str;
        this.tagName.local = str2;
        this.tagName.qname = str3;
        this.next.endElement(this.tagName);
    }

    @Override // org.xml.sax.ContentHandler
    public final void characters(char[] cArr, int i, int i2) {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINEST)) {
            logger2.log(Level.FINEST, "SAXConnector.characters: {0}", cArr);
        }
        if (this.predictor.expectText()) {
            this.buffer.append(cArr, i, i2);
        }
    }

    @Override // org.xml.sax.ContentHandler
    public final void ignorableWhitespace(char[] cArr, int i, int i2) {
        Logger logger2 = logger;
        if (logger2.isLoggable(Level.FINEST)) {
            logger2.log(Level.FINEST, "SAXConnector.characters{0}", cArr);
        }
        characters(cArr, i, i2);
    }

    private void processText(boolean z) throws SAXException {
        if (this.predictor.expectText() && (!z || !WhiteSpaceProcessor.isWhiteSpace(this.buffer))) {
            this.next.text(this.buffer);
        }
        this.buffer.setLength(0);
    }
}
