package com.sun.xml.bind.v2.runtime.unmarshaller;

import com.google.common.base.Ascii;
import com.sun.xml.bind.DatatypeConverterImpl;
import com.sun.xml.bind.v2.runtime.output.Pcdata;
import com.sun.xml.bind.v2.runtime.output.UTF8XmlOutput;
import com.sun.xml.bind.v2.util.ByteArrayOutputStreamEx;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import net.bytebuddy.jar.asm.signature.SignatureVisitor;
import okio.Utf8;

/* JADX INFO: loaded from: classes3.dex */
public final class Base64Data extends Pcdata {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] data;
    private DataHandler dataHandler;
    private int dataLen;
    private String mimeType;

    public void set(byte[] bArr, int i, String str) {
        this.data = bArr;
        this.dataLen = i;
        this.dataHandler = null;
        this.mimeType = str;
    }

    public void set(byte[] bArr, String str) {
        set(bArr, bArr.length, str);
    }

    public void set(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
        this.data = null;
    }

    public DataHandler getDataHandler() {
        if (this.dataHandler == null) {
            this.dataHandler = new DataHandler(new DataSource() { // from class: com.sun.xml.bind.v2.runtime.unmarshaller.Base64Data.1
                @Override // javax.activation.DataSource
                public String getName() {
                    return null;
                }

                @Override // javax.activation.DataSource
                public String getContentType() {
                    return Base64Data.this.getMimeType();
                }

                @Override // javax.activation.DataSource
                public InputStream getInputStream() {
                    return new ByteArrayInputStream(Base64Data.this.data, 0, Base64Data.this.dataLen);
                }

                @Override // javax.activation.DataSource
                public OutputStream getOutputStream() {
                    throw new UnsupportedOperationException();
                }
            });
        }
        return this.dataHandler;
    }

    public byte[] getExact() {
        get();
        int i = this.dataLen;
        byte[] bArr = this.data;
        if (i != bArr.length) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            this.data = bArr2;
        }
        return this.data;
    }

    public InputStream getInputStream() throws IOException {
        DataHandler dataHandler = this.dataHandler;
        if (dataHandler != null) {
            return dataHandler.getInputStream();
        }
        return new ByteArrayInputStream(this.data, 0, this.dataLen);
    }

    public boolean hasData() {
        return this.data != null;
    }

    public byte[] get() {
        if (this.data == null) {
            try {
                ByteArrayOutputStreamEx byteArrayOutputStreamEx = new ByteArrayOutputStreamEx(1024);
                InputStream inputStream = this.dataHandler.getDataSource().getInputStream();
                byteArrayOutputStreamEx.readFrom(inputStream);
                inputStream.close();
                this.data = byteArrayOutputStreamEx.getBuffer();
                this.dataLen = byteArrayOutputStreamEx.size();
            } catch (IOException unused) {
                this.dataLen = 0;
            }
        }
        return this.data;
    }

    public int getDataLen() {
        return this.dataLen;
    }

    public String getMimeType() {
        String str = this.mimeType;
        return str == null ? "application/octet-stream" : str;
    }

    @Override // java.lang.CharSequence
    public int length() {
        get();
        return ((this.dataLen + 2) / 3) * 4;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        int i2 = i % 4;
        int i3 = (i / 4) * 3;
        if (i2 == 0) {
            return DatatypeConverterImpl.encode(this.data[i3] >> 2);
        }
        if (i2 == 1) {
            int i4 = i3 + 1;
            return DatatypeConverterImpl.encode(((this.data[i3] & 3) << 4) | (((i4 < this.dataLen ? this.data[i4] : (byte) 0) >> 4) & 15));
        }
        if (i2 != 2) {
            if (i2 != 3) {
                throw new IllegalStateException();
            }
            int i5 = i3 + 2;
            return i5 < this.dataLen ? DatatypeConverterImpl.encode(this.data[i5] & Utf8.REPLACEMENT_BYTE) : SignatureVisitor.INSTANCEOF;
        }
        int i6 = i3 + 1;
        int i7 = this.dataLen;
        if (i6 >= i7) {
            return SignatureVisitor.INSTANCEOF;
        }
        byte[] bArr = this.data;
        int i8 = i3 + 2;
        return DatatypeConverterImpl.encode(((bArr[i6] & Ascii.SI) << 2) | (((i8 < i7 ? bArr[i8] : (byte) 0) >> 6) & 3));
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        get();
        while (i < i2) {
            sb.append(charAt(i));
            i++;
        }
        return sb;
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata, java.lang.CharSequence
    public String toString() {
        get();
        return DatatypeConverterImpl._printBase64Binary(this.data, 0, this.dataLen);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata
    public void writeTo(char[] cArr, int i) {
        get();
        DatatypeConverterImpl._printBase64Binary(this.data, 0, this.dataLen, cArr, i);
    }

    @Override // com.sun.xml.bind.v2.runtime.output.Pcdata
    public void writeTo(UTF8XmlOutput uTF8XmlOutput) throws IOException {
        get();
        uTF8XmlOutput.text(this.data, this.dataLen);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: javax.xml.stream.XMLStreamException */
    public void writeTo(XMLStreamWriter xMLStreamWriter) throws XMLStreamException, IOException {
        get();
        DatatypeConverterImpl._printBase64Binary(this.data, 0, this.dataLen, xMLStreamWriter);
    }
}
