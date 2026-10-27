package com.sun.xml.bind.v2.runtime.unmarshaller;

import java.net.URL;
import javax.xml.bind.ValidationEventLocator;
import org.w3c.dom.Node;
import org.xml.sax.Locator;

/* JADX INFO: loaded from: classes3.dex */
public interface LocatorEx extends Locator {
    ValidationEventLocator getLocation();

    public static final class Snapshot implements LocatorEx, ValidationEventLocator {
        private final int columnNumber;
        private final int lineNumber;
        private final Node node;
        private final Object object;
        private final int offset;
        private final String publicId;
        private final String systemId;
        private final URL url;

        @Override // com.sun.xml.bind.v2.runtime.unmarshaller.LocatorEx
        public ValidationEventLocator getLocation() {
            return this;
        }

        public Snapshot(LocatorEx locatorEx) {
            this.columnNumber = locatorEx.getColumnNumber();
            this.lineNumber = locatorEx.getLineNumber();
            this.systemId = locatorEx.getSystemId();
            this.publicId = locatorEx.getPublicId();
            ValidationEventLocator location = locatorEx.getLocation();
            this.offset = location.getOffset();
            this.url = location.getURL();
            this.object = location.getObject();
            this.node = location.getNode();
        }

        @Override // javax.xml.bind.ValidationEventLocator
        public Object getObject() {
            return this.object;
        }

        @Override // javax.xml.bind.ValidationEventLocator
        public Node getNode() {
            return this.node;
        }

        @Override // javax.xml.bind.ValidationEventLocator
        public int getOffset() {
            return this.offset;
        }

        @Override // javax.xml.bind.ValidationEventLocator
        public URL getURL() {
            return this.url;
        }

        @Override // org.xml.sax.Locator, javax.xml.bind.ValidationEventLocator
        public int getColumnNumber() {
            return this.columnNumber;
        }

        @Override // org.xml.sax.Locator, javax.xml.bind.ValidationEventLocator
        public int getLineNumber() {
            return this.lineNumber;
        }

        @Override // org.xml.sax.Locator
        public String getSystemId() {
            return this.systemId;
        }

        @Override // org.xml.sax.Locator
        public String getPublicId() {
            return this.publicId;
        }
    }
}
