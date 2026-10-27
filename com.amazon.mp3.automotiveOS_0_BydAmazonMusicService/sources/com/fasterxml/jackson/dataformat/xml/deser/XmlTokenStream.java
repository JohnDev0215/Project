package com.fasterxml.jackson.dataformat.xml.deser;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.codehaus.stax2.XMLStreamLocation2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.ri.Stax2ReaderAdapter;

/* JADX INFO: loaded from: classes6.dex */
public class XmlTokenStream {
    private static final int REPLAY_END = 2;
    private static final int REPLAY_START_DELAYED = 3;
    private static final int REPLAY_START_DUP = 1;
    public static final int XML_ATTRIBUTE_NAME = 3;
    public static final int XML_ATTRIBUTE_VALUE = 4;
    public static final int XML_DELAYED_START_ELEMENT = 6;
    public static final int XML_END = 8;
    public static final int XML_END_ELEMENT = 2;
    public static final int XML_ROOT_TEXT = 7;
    public static final int XML_START_ELEMENT = 1;
    public static final int XML_TEXT = 5;
    private static final String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    protected int _attributeCount;
    protected int _currentState;
    protected ElementWrapper _currentWrapper;
    protected int _formatFeatures;
    protected String _localName;
    protected String _namespaceURI;
    protected int _nextAttributeIndex;
    protected String _nextLocalName;
    protected String _nextNamespaceURI;
    protected boolean _repeatCurrentToken;
    protected int _repeatElement;
    protected final Object _sourceReference;
    protected boolean _startElementAfterText;
    protected String _textValue;
    protected final XMLStreamReader2 _xmlReader;
    protected boolean _xsiNilFound;

    public XmlTokenStream(XMLStreamReader xMLStreamReader, Object obj, int i) {
        this._sourceReference = obj;
        this._formatFeatures = i;
        this._xmlReader = Stax2ReaderAdapter.wrapIfNecessary(xMLStreamReader);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public int initialize() throws XMLStreamException {
        if (this._xmlReader.getEventType() != 1) {
            throw new IllegalArgumentException("Invalid XMLStreamReader passed: should be pointing to START_ELEMENT (1), instead got " + this._xmlReader.getEventType());
        }
        this._localName = this._xmlReader.getLocalName();
        this._namespaceURI = this._xmlReader.getNamespaceURI();
        _checkXsiAttributes();
        if (this._xsiNilFound || this._attributeCount > 0) {
            this._currentState = 1;
            return 1;
        }
        String str_collectUntilTag = _collectUntilTag();
        if (str_collectUntilTag == null) {
            this._textValue = null;
            this._startElementAfterText = false;
            this._currentState = 7;
            return 7;
        }
        if (this._xmlReader.getEventType() == 1) {
            if (_allWs(str_collectUntilTag)) {
                this._textValue = null;
                this._currentState = 6;
                return 6;
            }
            this._textValue = str_collectUntilTag;
            this._currentState = 6;
            return 6;
        }
        this._startElementAfterText = false;
        this._textValue = str_collectUntilTag;
        this._currentState = 7;
        return 7;
    }

    public XMLStreamReader2 getXmlReader() {
        return this._xmlReader;
    }

    protected void setFormatFeatures(int i) {
        this._formatFeatures = i;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public int next() throws XMLStreamException {
        if (this._repeatCurrentToken) {
            this._repeatCurrentToken = false;
            return this._currentState;
        }
        if (this._repeatElement != 0) {
            int i_handleRepeatElement = _handleRepeatElement();
            this._currentState = i_handleRepeatElement;
            return i_handleRepeatElement;
        }
        return _next();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void skipEndElement() throws XMLStreamException, IOException {
        int next = next();
        if (next != 2) {
            throw new IOException(String.format("Internal error: Expected END_ELEMENT, got event of type %s", _stateDesc(next)));
        }
    }

    public int getCurrentToken() {
        return this._currentState;
    }

    public String getText() {
        return this._textValue;
    }

    public String getLocalName() {
        return this._localName;
    }

    public String getNamespaceURI() {
        return this._namespaceURI;
    }

    public boolean hasXsiNil() {
        return this._xsiNilFound;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void closeCompletely() throws XMLStreamException {
        this._xmlReader.closeCompletely();
    }

    public void close() throws XMLStreamException {
        this._xmlReader.close();
    }

    public JsonLocation getCurrentLocation() {
        return _extractLocation(this._xmlReader.getLocationInfo().getCurrentLocation());
    }

    public JsonLocation getTokenLocation() {
        return _extractLocation(this._xmlReader.getLocationInfo().getStartLocation());
    }

    protected void repeatStartElement() {
        int i = this._currentState;
        if (i != 1) {
            if (i == 2) {
                return;
            }
            throw new IllegalStateException("Current state not XML_START_ELEMENT but " + _currentStateDesc());
        }
        ElementWrapper elementWrapper = this._currentWrapper;
        if (elementWrapper == null) {
            this._currentWrapper = ElementWrapper.matchingWrapper(null, this._localName, this._namespaceURI);
        } else {
            this._currentWrapper = ElementWrapper.matchingWrapper(elementWrapper.getParent(), this._localName, this._namespaceURI);
        }
        this._repeatElement = 1;
    }

    protected void pushbackCurrentToken() {
        this._repeatCurrentToken = true;
    }

    protected void skipAttributes() {
        int i = this._currentState;
        if (i != 1) {
            if (i == 3) {
                this._attributeCount = 0;
                this._currentState = 1;
            } else {
                if (i == 5 || i == 6) {
                    return;
                }
                throw new IllegalStateException("Current state not XML_START_ELEMENT or XML_ATTRIBUTE_NAME but " + _currentStateDesc());
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int _next() throws XMLStreamException {
        switch (this._currentState) {
            case 1:
                break;
            case 2:
            default:
                int i_skipAndCollectTextUntilTag = _skipAndCollectTextUntilTag();
                if (i_skipAndCollectTextUntilTag == 2) {
                    if (!_allWs(this._textValue)) {
                        this._currentState = 5;
                        return 5;
                    }
                    return _handleEndElement();
                }
                if (i_skipAndCollectTextUntilTag == 8) {
                    close();
                    this._currentState = 8;
                    return 8;
                }
                if (!_allWs(this._textValue)) {
                    this._startElementAfterText = true;
                    this._currentState = 5;
                    return 5;
                }
                return _initStartElement();
            case 3:
                this._currentState = 4;
                return 4;
            case 4:
                this._nextAttributeIndex++;
                break;
            case 5:
                if (this._startElementAfterText) {
                    this._startElementAfterText = false;
                    return _initStartElement();
                }
                return _handleEndElement();
            case 6:
                if (this._textValue == null) {
                    return _initStartElement();
                }
                this._startElementAfterText = true;
                this._currentState = 5;
                return 5;
            case 7:
                close();
                this._currentState = 8;
                return 8;
            case 8:
                return 8;
        }
        if (this._xsiNilFound) {
            this._xsiNilFound = false;
            int i_skipUntilTag = _skipUntilTag();
            if (i_skipUntilTag == 2) {
                return _handleEndElement();
            }
            if (i_skipUntilTag == 8) {
                throw new IllegalStateException("Unexpected end-of-input after null token");
            }
            throw new IllegalStateException("Unexpected START_ELEMENT after null token (inside element with 'xsi:nil' attribute)");
        }
        int i = this._nextAttributeIndex;
        if (i < this._attributeCount) {
            this._localName = this._xmlReader.getAttributeLocalName(i);
            this._namespaceURI = this._xmlReader.getAttributeNamespace(this._nextAttributeIndex);
            this._textValue = this._xmlReader.getAttributeValue(this._nextAttributeIndex);
            this._currentState = 3;
            return 3;
        }
        String str_collectUntilTag = _collectUntilTag();
        if (this._xmlReader.getEventType() == 1) {
            if (_allWs(str_collectUntilTag)) {
                this._startElementAfterText = false;
                return _initStartElement();
            }
            this._startElementAfterText = true;
            this._textValue = str_collectUntilTag;
            this._currentState = 5;
            return 5;
        }
        if (str_collectUntilTag != null) {
            this._startElementAfterText = false;
            this._textValue = str_collectUntilTag;
            this._currentState = 5;
            return 5;
        }
        this._startElementAfterText = false;
        return _handleEndElement();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final String _collectUntilTag() throws XMLStreamException {
        CharSequence sb = null;
        if (this._xmlReader.isEmptyElement()) {
            this._xmlReader.next();
            if (FromXmlParser.Feature.EMPTY_ELEMENT_AS_NULL.enabledIn(this._formatFeatures)) {
                return null;
            }
            return "";
        }
        while (true) {
            int next = this._xmlReader.next();
            if (next == 1) {
                return sb == null ? "" : sb.toString();
            }
            if (next == 2) {
                break;
            }
            if (next != 4) {
                if (next == 8) {
                    break;
                }
                if (next != 12) {
                }
            }
            String str_getText = _getText(this._xmlReader);
            if (sb == null) {
                sb = str_getText;
            } else {
                if (sb instanceof String) {
                    sb = new StringBuilder(sb);
                }
                ((StringBuilder) sb).append(str_getText);
            }
        }
        return sb == null ? "" : sb.toString();
    }

    private final int _skipUntilTag() throws XMLStreamException {
        while (this._xmlReader.hasNext()) {
            int next = this._xmlReader.next();
            if (next == 1 || next == 2 || next == 8) {
                return next;
            }
        }
        throw new IllegalStateException("Expected to find a tag, instead reached end of input");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final int _skipAndCollectTextUntilTag() throws XMLStreamException {
        CharSequence charSequence = null;
        while (this._xmlReader.hasNext()) {
            int next = this._xmlReader.next();
            if (next != 1 && next != 2) {
                if (next != 4) {
                    if (next != 8) {
                        if (next != 12) {
                        }
                    }
                }
                String str_getText = _getText(this._xmlReader);
                CharSequence charSequence2 = str_getText;
                if (charSequence != null) {
                    boolean z = charSequence instanceof String;
                    CharSequence sb = charSequence;
                    if (z) {
                        sb = new StringBuilder(charSequence);
                    }
                    ((StringBuilder) sb).append(str_getText);
                    charSequence2 = sb;
                }
                charSequence = charSequence2;
            }
            this._textValue = charSequence == null ? "" : charSequence.toString();
            return next;
        }
        throw new IllegalStateException("Expected to find a tag, instead reached end of input");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    private final String _getText(XMLStreamReader2 xMLStreamReader2) throws XMLStreamException {
        try {
            return xMLStreamReader2.getText();
        } catch (RuntimeException e) {
            XMLStreamException cause = e.getCause();
            if (cause instanceof XMLStreamException) {
                throw cause;
            }
            throw e;
        }
    }

    private final int _initStartElement() throws XMLStreamException {
        String namespaceURI = this._xmlReader.getNamespaceURI();
        String localName = this._xmlReader.getLocalName();
        _checkXsiAttributes();
        ElementWrapper elementWrapper = this._currentWrapper;
        if (elementWrapper != null) {
            if (elementWrapper.matchesWrapper(localName, namespaceURI)) {
                this._currentWrapper = this._currentWrapper.intermediateWrapper();
            } else {
                this._localName = this._currentWrapper.getWrapperLocalName();
                this._namespaceURI = this._currentWrapper.getWrapperNamespace();
                this._currentWrapper = this._currentWrapper.getParent();
                this._nextLocalName = localName;
                this._nextNamespaceURI = namespaceURI;
                this._repeatElement = 3;
                this._currentState = 2;
                return 2;
            }
        }
        this._localName = localName;
        this._namespaceURI = namespaceURI;
        this._currentState = 1;
        return 1;
    }

    private final void _checkXsiAttributes() {
        int attributeCount = this._xmlReader.getAttributeCount();
        this._attributeCount = attributeCount;
        if (attributeCount >= 1 && "nil".equals(this._xmlReader.getAttributeLocalName(0)) && XSI_NAMESPACE.equals(this._xmlReader.getAttributeNamespace(0))) {
            this._nextAttributeIndex = 1;
            this._xsiNilFound = "true".equals(this._xmlReader.getAttributeValue(0));
        } else {
            this._nextAttributeIndex = 0;
            this._xsiNilFound = false;
        }
    }

    protected int _handleRepeatElement() throws XMLStreamException {
        int i = this._repeatElement;
        this._repeatElement = 0;
        if (i == 1) {
            this._currentWrapper = this._currentWrapper.intermediateWrapper();
            return 1;
        }
        if (i == 2) {
            this._localName = this._xmlReader.getLocalName();
            this._namespaceURI = this._xmlReader.getNamespaceURI();
            ElementWrapper elementWrapper = this._currentWrapper;
            if (elementWrapper != null) {
                this._currentWrapper = elementWrapper.getParent();
            }
            return 2;
        }
        if (i == 3) {
            ElementWrapper elementWrapper2 = this._currentWrapper;
            if (elementWrapper2 != null) {
                this._currentWrapper = elementWrapper2.intermediateWrapper();
            }
            this._localName = this._nextLocalName;
            this._namespaceURI = this._nextNamespaceURI;
            this._nextLocalName = null;
            this._nextNamespaceURI = null;
            return 1;
        }
        throw new IllegalStateException("Unrecognized type to repeat: " + i);
    }

    private final int _handleEndElement() {
        ElementWrapper elementWrapper = this._currentWrapper;
        if (elementWrapper != null) {
            if (elementWrapper.isMatching()) {
                this._repeatElement = 2;
                this._localName = elementWrapper.getWrapperLocalName();
                this._namespaceURI = elementWrapper.getWrapperNamespace();
                this._currentWrapper = this._currentWrapper.getParent();
            } else {
                this._currentWrapper = this._currentWrapper.getParent();
                this._localName = "";
                this._namespaceURI = "";
            }
        } else {
            this._localName = "";
            this._namespaceURI = "";
        }
        this._currentState = 2;
        return 2;
    }

    private JsonLocation _extractLocation(XMLStreamLocation2 xMLStreamLocation2) {
        if (xMLStreamLocation2 == null) {
            return new JsonLocation(this._sourceReference, -1L, -1, -1);
        }
        return new JsonLocation(this._sourceReference, xMLStreamLocation2.getCharacterOffset(), xMLStreamLocation2.getLineNumber(), xMLStreamLocation2.getColumnNumber());
    }

    protected static boolean _allWs(String str) {
        int length = str == null ? 0 : str.length();
        if (length <= 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) > ' ') {
                return false;
            }
        }
        return true;
    }

    protected String _currentStateDesc() {
        return _stateDesc(this._currentState);
    }

    protected String _stateDesc(int i) {
        switch (i) {
            case 1:
                return "XML_START_ELEMENT";
            case 2:
                return "XML_END_ELEMENT";
            case 3:
                return "XML_ATTRIBUTE_NAME";
            case 4:
                return "XML_ATTRIBUTE_VALUE";
            case 5:
                return "XML_TEXT";
            case 6:
                return "XML_START_ELEMENT_DELAYED";
            case 7:
                return "XML_ROOT_TEXT";
            case 8:
                return "XML_END";
            default:
                return "N/A (" + this._currentState + ")";
        }
    }
}
