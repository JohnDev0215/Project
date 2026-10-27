package com.sun.xml.bind.v2.runtime.unmarshaller;

import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/* JADX INFO: loaded from: classes3.dex */
final class StAXEventConnector extends StAXConnector {
    private final AttributesImpl attrs;
    private final StringBuilder buffer;
    private XMLEvent event;
    private boolean seenText;
    private final XMLEventReader staxEventReader;

    public StAXEventConnector(XMLEventReader xMLEventReader, XmlVisitor xmlVisitor) {
        super(xmlVisitor);
        this.attrs = new AttributesImpl();
        this.buffer = new StringBuilder();
        this.staxEventReader = xMLEventReader;
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    public void bridge() throws XMLStreamException {
        XMLEvent xMLEventNextEvent;
        try {
            XMLEvent xMLEventPeek = this.staxEventReader.peek();
            this.event = xMLEventPeek;
            if (!xMLEventPeek.isStartDocument() && !this.event.isStartElement()) {
                throw new IllegalStateException();
            }
            do {
                xMLEventNextEvent = this.staxEventReader.nextEvent();
                this.event = xMLEventNextEvent;
            } while (!xMLEventNextEvent.isStartElement());
            handleStartDocument(this.event.asStartElement().getNamespaceContext());
            int i = 0;
            while (true) {
                int eventType = this.event.getEventType();
                if (eventType == 1) {
                    handleStartElement(this.event.asStartElement());
                    i++;
                } else if (eventType == 2) {
                    i--;
                    handleEndElement(this.event.asEndElement());
                    if (i == 0) {
                        handleEndDocument();
                        this.event = null;
                        return;
                    }
                } else if (eventType == 4 || eventType == 6 || eventType == 12) {
                    handleCharacters(this.event.asCharacters());
                }
                this.event = this.staxEventReader.nextEvent();
            }
        } catch (SAXException e) {
            throw new XMLStreamException(e);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected Location getCurrentLocation() {
        return this.event.getLocation();
    }

    @Override // com.sun.xml.bind.v2.runtime.unmarshaller.StAXConnector
    protected String getCurrentQName() {
        QName name;
        if (this.event.isEndElement()) {
            name = this.event.asEndElement().getName();
        } else {
            name = this.event.asStartElement().getName();
        }
        return getQName(name.getPrefix(), name.getLocalPart());
    }

    private void handleCharacters(Characters characters) throws SAXException, XMLStreamException {
        XMLEvent xMLEventPeek;
        if (!this.predictor.expectText()) {
            return;
        }
        this.seenText = true;
        while (true) {
            xMLEventPeek = this.staxEventReader.peek();
            if (!isIgnorable(xMLEventPeek)) {
                break;
            } else {
                this.staxEventReader.nextEvent();
            }
        }
        if (isTag(xMLEventPeek)) {
            this.visitor.text(characters.getData());
            return;
        }
        this.buffer.append(characters.getData());
        while (true) {
            XMLEvent xMLEventPeek2 = this.staxEventReader.peek();
            if (isIgnorable(xMLEventPeek2)) {
                this.staxEventReader.nextEvent();
            } else if (isTag(xMLEventPeek2)) {
                this.visitor.text(this.buffer);
                this.buffer.setLength(0);
                return;
            } else {
                this.buffer.append(xMLEventPeek2.asCharacters().getData());
                this.staxEventReader.nextEvent();
            }
        }
    }

    private boolean isTag(XMLEvent xMLEvent) {
        int eventType = xMLEvent.getEventType();
        return eventType == 1 || eventType == 2;
    }

    private boolean isIgnorable(XMLEvent xMLEvent) {
        int eventType = xMLEvent.getEventType();
        return eventType == 5 || eventType == 3;
    }

    private void handleEndElement(EndElement endElement) throws SAXException {
        if (!this.seenText && this.predictor.expectText()) {
            this.visitor.text("");
        }
        QName name = endElement.getName();
        this.tagName.uri = fixNull(name.getNamespaceURI());
        this.tagName.local = name.getLocalPart();
        this.visitor.endElement(this.tagName);
        Iterator namespaces = endElement.getNamespaces();
        while (namespaces.hasNext()) {
            this.visitor.endPrefixMapping(fixNull(((Namespace) namespaces.next()).getPrefix()));
        }
        this.seenText = false;
    }

    private void handleStartElement(StartElement startElement) throws SAXException {
        Iterator namespaces = startElement.getNamespaces();
        while (namespaces.hasNext()) {
            Namespace namespace = (Namespace) namespaces.next();
            this.visitor.startPrefixMapping(fixNull(namespace.getPrefix()), fixNull(namespace.getNamespaceURI()));
        }
        QName name = startElement.getName();
        this.tagName.uri = fixNull(name.getNamespaceURI());
        String localPart = name.getLocalPart();
        this.tagName.uri = fixNull(name.getNamespaceURI());
        this.tagName.local = localPart;
        this.tagName.atts = getAttributes(startElement);
        this.visitor.startElement(this.tagName);
        this.seenText = false;
    }

    private Attributes getAttributes(StartElement startElement) {
        this.attrs.clear();
        Iterator attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = (Attribute) attributes.next();
            QName name = attribute.getName();
            String strFixNull = fixNull(name.getNamespaceURI());
            String localPart = name.getLocalPart();
            String prefix = name.getPrefix();
            this.attrs.addAttribute(strFixNull, localPart, (prefix == null || prefix.length() == 0) ? localPart : prefix + ':' + localPart, attribute.getDTDType(), attribute.getValue());
        }
        return this.attrs;
    }
}
