package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.sun.xml.bind.WhiteSpaceProcessor;
import com.sun.xml.fastinfoset.stax.StAXDocumentParser;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
final class FastInfosetConnector extends StAXConnector {
    private final Base64Data base64Data;
    private final StringBuilder buffer;
    private final CharSequenceImpl charArray;
    private final StAXDocumentParser fastInfosetStreamReader;
    private boolean textReported;

    public FastInfosetConnector(StAXDocumentParser stAXDocumentParser, XmlVisitor xmlVisitor) {
        super(xmlVisitor);
        this.base64Data = new Base64Data();
        this.buffer = new StringBuilder();
        this.charArray = new CharSequenceImpl();
        stAXDocumentParser.setStringInterning(true);
        this.fastInfosetStreamReader = stAXDocumentParser;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    public void bridge() throws XMLStreamException {
        try {
            int eventType = this.fastInfosetStreamReader.getEventType();
            if (eventType == 7) {
                while (!this.fastInfosetStreamReader.isStartElement()) {
                    eventType = this.fastInfosetStreamReader.next();
                }
            }
            if (eventType != 1) {
                throw new IllegalStateException("The current event is not START_ELEMENT\n but " + eventType);
            }
            handleStartDocument(this.fastInfosetStreamReader.getNamespaceContext());
            int i = 0;
            while (true) {
                if (eventType == 1) {
                    handleStartElement();
                    i++;
                } else if (eventType == 2) {
                    i--;
                    handleEndElement();
                    if (i == 0) {
                        this.fastInfosetStreamReader.next();
                        handleEndDocument();
                        return;
                    }
                } else if ((eventType == 4 || eventType == 6 || eventType == 12) && this.predictor.expectText()) {
                    int iPeekNext = this.fastInfosetStreamReader.peekNext();
                    if (iPeekNext == 2) {
                        processNonIgnorableText();
                    } else if (iPeekNext == 1) {
                        processIgnorableText();
                    } else {
                        handleFragmentedCharacters();
                    }
                }
                eventType = this.fastInfosetStreamReader.next();
            }
        } catch (SAXException e) {
            throw new XMLStreamException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected Location getCurrentLocation() {
        return this.fastInfosetStreamReader.getLocation();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected String getCurrentQName() {
        return this.fastInfosetStreamReader.getNameString();
    }

    private void handleStartElement() throws SAXException {
        processUnreportedText();
        for (int i = 0; i < this.fastInfosetStreamReader.accessNamespaceCount(); i++) {
            this.visitor.startPrefixMapping(this.fastInfosetStreamReader.getNamespacePrefix(i), this.fastInfosetStreamReader.getNamespaceURI(i));
        }
        this.tagName.uri = this.fastInfosetStreamReader.accessNamespaceURI();
        this.tagName.local = this.fastInfosetStreamReader.accessLocalName();
        this.tagName.atts = this.fastInfosetStreamReader.getAttributesHolder();
        this.visitor.startElement(this.tagName);
    }

    private void handleFragmentedCharacters() throws SAXException, XMLStreamException {
        this.buffer.setLength(0);
        this.buffer.append(this.fastInfosetStreamReader.getTextCharacters(), this.fastInfosetStreamReader.getTextStart(), this.fastInfosetStreamReader.getTextLength());
        while (true) {
            int iPeekNext = this.fastInfosetStreamReader.peekNext();
            if (iPeekNext == 1) {
                processBufferedText(true);
                return;
            }
            if (iPeekNext == 2) {
                processBufferedText(false);
                return;
            } else if (iPeekNext == 4 || iPeekNext == 6 || iPeekNext == 12) {
                this.fastInfosetStreamReader.next();
                this.buffer.append(this.fastInfosetStreamReader.getTextCharacters(), this.fastInfosetStreamReader.getTextStart(), this.fastInfosetStreamReader.getTextLength());
            } else {
                this.fastInfosetStreamReader.next();
            }
        }
    }

    private void handleEndElement() throws SAXException {
        processUnreportedText();
        this.tagName.uri = this.fastInfosetStreamReader.accessNamespaceURI();
        this.tagName.local = this.fastInfosetStreamReader.accessLocalName();
        this.visitor.endElement(this.tagName);
        for (int iAccessNamespaceCount = this.fastInfosetStreamReader.accessNamespaceCount() - 1; iAccessNamespaceCount >= 0; iAccessNamespaceCount--) {
            this.visitor.endPrefixMapping(this.fastInfosetStreamReader.getNamespacePrefix(iAccessNamespaceCount));
        }
    }

    private final class CharSequenceImpl implements CharSequence {
        char[] ch;
        int length;
        int start;

        CharSequenceImpl() {
        }

        CharSequenceImpl(char[] cArr, int i, int i2) {
            this.ch = cArr;
            this.start = i;
            this.length = i2;
        }

        public void set() {
            this.ch = FastInfosetConnector.this.fastInfosetStreamReader.getTextCharacters();
            this.start = FastInfosetConnector.this.fastInfosetStreamReader.getTextStart();
            this.length = FastInfosetConnector.this.fastInfosetStreamReader.getTextLength();
        }

        @Override // java.lang.CharSequence
        public final int length() {
            return this.length;
        }

        @Override // java.lang.CharSequence
        public final char charAt(int i) {
            return this.ch[this.start + i];
        }

        @Override // java.lang.CharSequence
        public final CharSequence subSequence(int i, int i2) {
            return FastInfosetConnector.this.new CharSequenceImpl(this.ch, this.start + i, i2 - i);
        }

        @Override // java.lang.CharSequence
        public String toString() {
            return new String(this.ch, this.start, this.length);
        }
    }

    private void processNonIgnorableText() throws SAXException {
        this.textReported = true;
        boolean z = this.fastInfosetStreamReader.getTextAlgorithmBytes() != null;
        if (z && this.fastInfosetStreamReader.getTextAlgorithmIndex() == 1) {
            this.base64Data.set(this.fastInfosetStreamReader.getTextAlgorithmBytesClone(), null);
            this.visitor.text(this.base64Data);
        } else {
            if (z) {
                this.fastInfosetStreamReader.getText();
            }
            this.charArray.set();
            this.visitor.text(this.charArray);
        }
    }

    private void processIgnorableText() throws SAXException {
        boolean z = this.fastInfosetStreamReader.getTextAlgorithmBytes() != null;
        if (z && this.fastInfosetStreamReader.getTextAlgorithmIndex() == 1) {
            this.base64Data.set(this.fastInfosetStreamReader.getTextAlgorithmBytesClone(), null);
            this.visitor.text(this.base64Data);
            this.textReported = true;
            return;
        }
        if (z) {
            this.fastInfosetStreamReader.getText();
        }
        this.charArray.set();
        if (WhiteSpaceProcessor.isWhiteSpace(this.charArray)) {
            return;
        }
        this.visitor.text(this.charArray);
        this.textReported = true;
    }

    private void processBufferedText(boolean z) throws SAXException {
        if (z && WhiteSpaceProcessor.isWhiteSpace(this.buffer)) {
            return;
        }
        this.visitor.text(this.buffer);
        this.textReported = true;
    }

    private void processUnreportedText() throws SAXException {
        if (!this.textReported && this.predictor.expectText()) {
            this.visitor.text("");
        }
        this.textReported = false;
    }
}
