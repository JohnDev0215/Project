package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.v2.runtime.unmarshaller.XmlVisitor;
import javax.xml.bind.ValidationEventLocator;
import javax.xml.bind.helpers.ValidationEventLocatorImpl;
import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
abstract class StAXConnector {
    protected final UnmarshallingContext context;
    protected final XmlVisitor.TextPredictor predictor;
    protected final TagName tagName = new TagNameImpl();
    protected final XmlVisitor visitor;

    protected static String fixNull(String str) {
        return str == null ? "" : str;
    }

    public abstract void bridge() throws XMLStreamException;

    protected abstract Location getCurrentLocation();

    protected abstract String getCurrentQName();

    private final class TagNameImpl extends TagName {
        private TagNameImpl() {
        }

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.TagName
        public String getQname() {
            return StAXConnector.this.getCurrentQName();
        }
    }

    protected StAXConnector(XmlVisitor xmlVisitor) {
        this.visitor = xmlVisitor;
        this.context = xmlVisitor.getContext();
        this.predictor = xmlVisitor.getPredictor();
    }

    protected final void handleStartDocument(NamespaceContext namespaceContext) throws SAXException {
        this.visitor.startDocument(new LocatorEx() { // from class: com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector.1
            @Override // com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx
            public ValidationEventLocator getLocation() {
                return new ValidationEventLocatorImpl((Locator) this);
            }

            @Override // org.xml.sax.Locator
            public int getColumnNumber() {
                return StAXConnector.this.getCurrentLocation().getColumnNumber();
            }

            @Override // org.xml.sax.Locator
            public int getLineNumber() {
                return StAXConnector.this.getCurrentLocation().getLineNumber();
            }

            @Override // org.xml.sax.Locator
            public String getPublicId() {
                return StAXConnector.this.getCurrentLocation().getPublicId();
            }

            @Override // org.xml.sax.Locator
            public String getSystemId() {
                return StAXConnector.this.getCurrentLocation().getSystemId();
            }
        }, namespaceContext);
    }

    protected final void handleEndDocument() throws SAXException {
        this.visitor.endDocument();
    }

    protected final String getQName(String str, String str2) {
        return (str == null || str.length() == 0) ? str2 : str + ':' + str2;
    }
}
