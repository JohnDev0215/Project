package com.amazon.ion.impl;

import com.amazon.ion.IonType;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.SymbolToken;
import com.amazon.ion.Timestamp;
import com.amazon.ion.util._Private_FastAppendable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes3.dex */
class IonWriterSystemTextMarkup extends IonWriterSystemText {
    private IonType myTypeBeingWritten;

    public IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_FastAppendable _private_fastappendable) {
        this(symbolTable, _private_iontextwriterbuilder, _private_fastappendable, _private_iontextwriterbuilder.getCallbackBuilder());
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_FastAppendable _private_fastappendable, _Private_CallbackBuilder _private_callbackbuilder) {
        this(symbolTable, _private_iontextwriterbuilder, (_Private_MarkupCallback) null);
        _private_callbackbuilder.build(_private_fastappendable);
    }

    private IonWriterSystemTextMarkup(SymbolTable symbolTable, _Private_IonTextWriterBuilder _private_iontextwriterbuilder, _Private_MarkupCallback _private_markupcallback) {
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    void startValue() throws IOException {
        super.startValue();
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    void closeValue() throws IOException {
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    protected void writeFieldNameToken(SymbolToken symbolToken) throws IOException {
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    protected void writeAnnotations(SymbolToken[] symbolTokenArr) throws IOException {
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    protected void writeAnnotationToken(SymbolToken symbolToken) throws IOException {
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText
    protected boolean writeSeparator(boolean z) throws IOException {
        getContainer();
        if (this._pending_separator) {
            throw null;
        }
        boolean zWriteSeparator = super.writeSeparator(z);
        if (this._pending_separator) {
            throw null;
        }
        return zWriteSeparator;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void stepIn(IonType ionType) throws IOException {
        this.myTypeBeingWritten = ionType;
        super.stepIn(ionType);
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void stepOut() throws IOException {
        this.myTypeBeingWritten = getContainer();
        throw null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeBlob(byte[] bArr, int i, int i2) throws IOException {
        this.myTypeBeingWritten = IonType.BLOB;
        super.writeBlob(bArr, i, i2);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeBool(boolean z) throws IOException {
        this.myTypeBeingWritten = IonType.BOOL;
        super.writeBool(z);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeClob(byte[] bArr, int i, int i2) throws IOException {
        this.myTypeBeingWritten = IonType.CLOB;
        super.writeClob(bArr, i, i2);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeDecimal(BigDecimal bigDecimal) throws IOException {
        this.myTypeBeingWritten = IonType.DECIMAL;
        super.writeDecimal(bigDecimal);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeFloat(double d) throws IOException {
        this.myTypeBeingWritten = IonType.FLOAT;
        super.writeFloat(d);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeInt(long j) throws IOException {
        this.myTypeBeingWritten = IonType.INT;
        super.writeInt(j);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeInt(BigInteger bigInteger) throws IOException {
        this.myTypeBeingWritten = IonType.INT;
        super.writeInt(bigInteger);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl._Private_IonWriterBase, com.amazon.ion.IonWriter
    public void writeNull() throws IOException {
        this.myTypeBeingWritten = IonType.NULL;
        super.writeNull();
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeNull(IonType ionType) throws IOException {
        this.myTypeBeingWritten = ionType;
        super.writeNull(ionType);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeString(String str) throws IOException {
        this.myTypeBeingWritten = IonType.STRING;
        super.writeString(str);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(String str) throws IOException {
        this.myTypeBeingWritten = IonType.SYMBOL;
        super.writeSymbolAsIs(str);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.impl.IonWriterSystem
    public void writeSymbolAsIs(int i) throws IOException {
        this.myTypeBeingWritten = IonType.SYMBOL;
        super.writeSymbolAsIs(i);
        this.myTypeBeingWritten = null;
    }

    @Override // com.amazon.ion.impl.IonWriterSystemText, com.amazon.ion.IonWriter
    public void writeTimestamp(Timestamp timestamp) throws IOException {
        this.myTypeBeingWritten = IonType.TIMESTAMP;
        super.writeTimestamp(timestamp);
        this.myTypeBeingWritten = null;
    }
}
