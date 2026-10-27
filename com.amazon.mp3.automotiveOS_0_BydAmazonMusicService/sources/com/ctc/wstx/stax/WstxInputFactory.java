package com.ctc.wstx.stax;

import com.ctc.wstx.api.ReaderConfig;
import com.ctc.wstx.util.DefaultXmlSymbolTable;
import com.ctc.wstx.util.SymbolTable;
import javax.xml.stream.util.XMLEventAllocator;
import org.codehaus.stax2.XMLInputFactory2;

/* JADX INFO: loaded from: classes5.dex */
public class WstxInputFactory extends XMLInputFactory2 {
    static final SymbolTable mRootSymbols;
    protected XMLEventAllocator mAllocator = null;
    private SymbolTable mSymbols = mRootSymbols;
    protected final ReaderConfig mConfig = ReaderConfig.createFullDefaults();

    static {
        SymbolTable defaultXmlSymbolTable = DefaultXmlSymbolTable.getInstance();
        mRootSymbols = defaultXmlSymbolTable;
        defaultXmlSymbolTable.setInternStrings(true);
    }
}
