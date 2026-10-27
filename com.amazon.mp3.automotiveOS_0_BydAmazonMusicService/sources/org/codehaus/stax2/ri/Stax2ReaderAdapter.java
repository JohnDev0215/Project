package org.codehaus.stax2.ri;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;
import org.codehaus.stax2.AttributeInfo;
import org.codehaus.stax2.LocationInfo;
import org.codehaus.stax2.XMLStreamLocation2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.ri.typed.StringBase64Decoder;
import org.codehaus.stax2.ri.typed.ValueDecoderFactory;
import org.codehaus.stax2.typed.TypedValueDecoder;
import org.codehaus.stax2.typed.TypedXMLStreamException;

/* JADX INFO: loaded from: classes7.dex */
public class Stax2ReaderAdapter extends StreamReaderDelegate implements XMLStreamReader2, AttributeInfo, LocationInfo {
    protected StringBase64Decoder _base64Decoder;
    protected ValueDecoderFactory _decoderFactory;
    protected int _depth;

    @Override // org.codehaus.stax2.AttributeInfo
    public abstract /* synthetic */ int getAttributeCount();

    @Override // org.codehaus.stax2.XMLStreamReader2
    public final LocationInfo getLocationInfo() {
        return this;
    }

    @Override // org.codehaus.stax2.XMLStreamReader2
    public boolean isEmptyElement() throws XMLStreamException {
        return false;
    }

    protected Stax2ReaderAdapter(XMLStreamReader xMLStreamReader) {
        super(xMLStreamReader);
        this._base64Decoder = null;
        this._depth = 0;
    }

    public static XMLStreamReader2 wrapIfNecessary(XMLStreamReader xMLStreamReader) {
        if (xMLStreamReader instanceof XMLStreamReader2) {
            return (XMLStreamReader2) xMLStreamReader;
        }
        return new Stax2ReaderAdapter(xMLStreamReader);
    }

    public String getElementText() throws XMLStreamException {
        boolean z = getEventType() == 1;
        String elementText = super.getElementText();
        if (z) {
            this._depth--;
        }
        return elementText;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamReader2, org.codehaus.stax2.typed.TypedXMLStreamReader
    public int getElementAsInt() throws XMLStreamException {
        ValueDecoderFactory.IntDecoder intDecoder = _decoderFactory().getIntDecoder();
        getElementAs(intDecoder);
        return intDecoder.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamReader2, org.codehaus.stax2.typed.TypedXMLStreamReader
    public float getElementAsFloat() throws XMLStreamException {
        ValueDecoderFactory.FloatDecoder floatDecoder = _decoderFactory().getFloatDecoder();
        getElementAs(floatDecoder);
        return floatDecoder.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // org.codehaus.stax2.XMLStreamReader2, org.codehaus.stax2.typed.TypedXMLStreamReader
    public double getElementAsDouble() throws XMLStreamException {
        ValueDecoderFactory.DoubleDecoder doubleDecoder = _decoderFactory().getDoubleDecoder();
        getElementAs(doubleDecoder);
        return doubleDecoder.getValue();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void getElementAs(TypedValueDecoder typedValueDecoder) throws XMLStreamException {
        String strTrimSpaces = Stax2Util.trimSpaces(getElementText());
        try {
            if (strTrimSpaces == null) {
                typedValueDecoder.handleEmptyValue();
            } else {
                typedValueDecoder.decode(strTrimSpaces);
            }
        } catch (IllegalArgumentException e) {
            throw _constructTypeException(e, strTrimSpaces);
        }
    }

    @Override // org.codehaus.stax2.XMLStreamReader2
    public int getDepth() {
        if (getEventType() == 2) {
            return this._depth + 1;
        }
        return this._depth;
    }

    @Override // org.codehaus.stax2.XMLStreamReader2
    public void closeCompletely() throws XMLStreamException {
        close();
    }

    @Override // org.codehaus.stax2.AttributeInfo
    public int getIdAttributeIndex() {
        int attributeCount = getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("ID".equals(getAttributeType(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.codehaus.stax2.AttributeInfo
    public int getNotationAttributeIndex() {
        int attributeCount = getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("NOTATION".equals(getAttributeType(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // org.codehaus.stax2.LocationInfo
    public XMLStreamLocation2 getStartLocation() {
        return getCurrentLocation();
    }

    @Override // org.codehaus.stax2.LocationInfo
    public XMLStreamLocation2 getCurrentLocation() {
        return new Stax2LocationAdapter(getLocation());
    }

    protected ValueDecoderFactory _decoderFactory() {
        if (this._decoderFactory == null) {
            this._decoderFactory = new ValueDecoderFactory();
        }
        return this._decoderFactory;
    }

    protected TypedXMLStreamException _constructTypeException(IllegalArgumentException illegalArgumentException, String str) {
        String message = illegalArgumentException.getMessage();
        if (message == null) {
            message = "";
        }
        XMLStreamLocation2 startLocation = getStartLocation();
        if (startLocation == null) {
            return new TypedXMLStreamException(str, message, illegalArgumentException);
        }
        return new TypedXMLStreamException(str, message, startLocation, illegalArgumentException);
    }
}
