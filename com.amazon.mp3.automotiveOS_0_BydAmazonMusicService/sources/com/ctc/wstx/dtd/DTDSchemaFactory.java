package com.ctc.wstx.dtd;

import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.api.ValidatorConfig;
import com.ctc.wstx.util.DefaultXmlSymbolTable;
import com.ctc.wstx.util.SymbolTable;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;

/* JADX INFO: loaded from: classes5.dex */
public class DTDSchemaFactory extends XMLValidationSchemaFactory {
    static final SymbolTable mRootSymbols;
    protected final ReaderConfig mReaderConfig;
    protected final ValidatorConfig mSchemaConfig;

    static {
        SymbolTable defaultXmlSymbolTable = DefaultXmlSymbolTable.getInstance();
        mRootSymbols = defaultXmlSymbolTable;
        defaultXmlSymbolTable.setInternStrings(true);
    }

    public DTDSchemaFactory() {
        super("http://www.w3.org/XML/1998/namespace");
        this.mReaderConfig = ReaderConfig.createFullDefaults();
        this.mSchemaConfig = ValidatorConfig.createDefaults();
    }
}
