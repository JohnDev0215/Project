package com.amazon.ion.system;

import com.amazon.ion.IonCatalog;
import com.amazon.ion.SymbolTable;
import com.amazon.ion.impl._Private_IonTextWriterBuilder;
import com.amazon.ion.impl._Private_Utils;
import com.amazon.ion.system.IonWriterBuilder;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IonTextWriterBuilder extends IonWriterBuilderBase<IonTextWriterBuilder> {
    public static final Charset ASCII = _Private_Utils.ASCII_CHARSET;
    public static final Charset UTF8 = _Private_Utils.UTF8_CHARSET;
    private Charset myCharset;
    private IonWriterBuilder.InitialIvmHandling myInitialIvmHandling;
    private IonWriterBuilder.IvmMinimizing myIvmMinimizing;
    private int myLongStringThreshold;
    private LstMinimizing myLstMinimizing;
    private NewLineType myNewLineType;
    private boolean myTopLevelValuesOnNewLines;

    public enum LstMinimizing {
        LOCALS,
        EVERYTHING
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ IonCatalog getCatalog() {
        return super.getCatalog();
    }

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ SymbolTable[] getImports() {
        return super.getImports();
    }

    public abstract IonTextWriterBuilder immutable();

    public abstract IonTextWriterBuilder mutable();

    @Override // com.amazon.ion.system.IonWriterBuilderBase
    public /* bridge */ /* synthetic */ void setCatalog(IonCatalog ionCatalog) {
        super.setCatalog(ionCatalog);
    }

    public enum NewLineType {
        CRLF("\r\n"),
        LF("\n"),
        PLATFORM_DEPENDENT(System.getProperty("line.separator"));

        private final CharSequence charSequence;

        NewLineType(CharSequence charSequence) {
            this.charSequence = charSequence;
        }

        public CharSequence getCharSequence() {
            return this.charSequence;
        }
    }

    public static IonTextWriterBuilder standard() {
        return _Private_IonTextWriterBuilder.standard();
    }

    protected IonTextWriterBuilder() {
    }

    protected IonTextWriterBuilder(IonTextWriterBuilder ionTextWriterBuilder) {
        super(ionTextWriterBuilder);
        this.myCharset = ionTextWriterBuilder.myCharset;
        this.myInitialIvmHandling = ionTextWriterBuilder.myInitialIvmHandling;
        this.myIvmMinimizing = ionTextWriterBuilder.myIvmMinimizing;
        this.myLstMinimizing = ionTextWriterBuilder.myLstMinimizing;
        this.myLongStringThreshold = ionTextWriterBuilder.myLongStringThreshold;
        this.myNewLineType = ionTextWriterBuilder.myNewLineType;
        this.myTopLevelValuesOnNewLines = ionTextWriterBuilder.myTopLevelValuesOnNewLines;
    }

    public final Charset getCharset() {
        return this.myCharset;
    }

    public void setCharset(Charset charset) {
        mutationCheck();
        if (charset == null || charset.equals(ASCII) || charset.equals(UTF8)) {
            this.myCharset = charset;
            return;
        }
        throw new IllegalArgumentException("Unsupported Charset " + charset);
    }

    public final IonTextWriterBuilder withCharset(Charset charset) {
        IonTextWriterBuilder ionTextWriterBuilderMutable = mutable();
        ionTextWriterBuilderMutable.setCharset(charset);
        return ionTextWriterBuilderMutable;
    }

    public final IonTextWriterBuilder withCharsetAscii() {
        return withCharset(ASCII);
    }

    public final IonWriterBuilder.InitialIvmHandling getInitialIvmHandling() {
        return this.myInitialIvmHandling;
    }

    public final IonWriterBuilder.IvmMinimizing getIvmMinimizing() {
        return this.myIvmMinimizing;
    }

    public final LstMinimizing getLstMinimizing() {
        return this.myLstMinimizing;
    }

    public final int getLongStringThreshold() {
        return this.myLongStringThreshold;
    }

    public void setLongStringThreshold(int i) {
        mutationCheck();
        this.myLongStringThreshold = i;
    }

    public final NewLineType getNewLineType() {
        return this.myNewLineType;
    }

    public void setNewLineType(NewLineType newLineType) {
        mutationCheck();
        this.myNewLineType = newLineType;
    }

    public final boolean getWriteTopLevelValuesOnNewLines() {
        return this.myTopLevelValuesOnNewLines;
    }
}
