package com.sun.xml.bind.v2.runtime.output;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import com.sun.xml.bind.v2.runtime.Name;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;

/* JADX INFO: loaded from: classes3.dex */
public final class IndentingUTF8XmlOutput extends UTF8XmlOutput {
    private int depth;
    private final Encoded indent8;
    private boolean seenText;
    private final int unitLen;

    public IndentingUTF8XmlOutput(OutputStream outputStream, String str, Encoded[] encodedArr, CharacterEscapeHandler characterEscapeHandler) {
        super(outputStream, encodedArr, characterEscapeHandler);
        this.depth = 0;
        this.seenText = false;
        if (str != null) {
            Encoded encoded = new Encoded(str);
            Encoded encoded2 = new Encoded();
            this.indent8 = encoded2;
            encoded2.ensureSize(encoded.len * 8);
            this.unitLen = encoded.len;
            for (int i = 0; i < 8; i++) {
                byte[] bArr = encoded.buf;
                byte[] bArr2 = this.indent8.buf;
                int i2 = this.unitLen;
                System.arraycopy(bArr, 0, bArr2, i2 * i, i2);
            }
            return;
        }
        this.indent8 = null;
        this.unitLen = 0;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(int i, String str) throws IOException {
        indentStartTag();
        super.beginStartTag(i, str);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void beginStartTag(Name name) throws IOException {
        indentStartTag();
        super.beginStartTag(name);
    }

    private void indentStartTag() throws IOException {
        closeStartTag();
        if (!this.seenText) {
            printIndent();
        }
        this.depth++;
        this.seenText = false;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(Name name) throws IOException {
        indentEndTag();
        super.endTag(name);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endTag(int i, String str) throws IOException {
        indentEndTag();
        super.endTag(i, str);
    }

    private void indentEndTag() throws IOException {
        this.depth--;
        if (!this.closeStartTagPending && !this.seenText) {
            printIndent();
        }
        this.seenText = false;
    }

    private void printIndent() throws IOException {
        write(10);
        int i = this.depth % 8;
        write(this.indent8.buf, 0, this.unitLen * i);
        for (int i2 = i >> 3; i2 > 0; i2--) {
            this.indent8.write(this);
        }
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(String str, boolean z) throws IOException {
        this.seenText = true;
        super.text(str, z);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void text(Pcdata pcdata, boolean z) throws IOException {
        this.seenText = true;
        super.text(pcdata, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    @Override // com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput, com.sun.xml.bind.v2.runtime.output.XmlOutputAbstractImpl, com.sun.xml.bind.v2.runtime.output.XmlOutput
    public void endDocument(boolean z) throws SAXException, XMLStreamException, IOException {
        write(10);
        super.endDocument(z);
    }
}
