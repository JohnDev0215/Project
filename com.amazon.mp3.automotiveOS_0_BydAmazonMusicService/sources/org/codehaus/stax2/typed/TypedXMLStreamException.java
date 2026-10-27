package org.codehaus.stax2.typed;

import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;

/* JADX INFO: loaded from: classes7.dex */
public class TypedXMLStreamException extends XMLStreamException {
    protected String mLexical;

    public TypedXMLStreamException(String str, String str2, IllegalArgumentException illegalArgumentException) {
        super(str2, illegalArgumentException);
        this.mLexical = str;
    }

    public TypedXMLStreamException(String str, String str2, Location location, IllegalArgumentException illegalArgumentException) {
        super(str2, location, illegalArgumentException);
        this.mLexical = str;
    }
}
