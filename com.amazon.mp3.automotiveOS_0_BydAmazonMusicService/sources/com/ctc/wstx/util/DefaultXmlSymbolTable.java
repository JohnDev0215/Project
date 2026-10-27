package com.ctc.wstx.util;

import com.amazon.identity.auth.device.utils.AccountConstants;

/* JADX INFO: loaded from: classes5.dex */
public final class DefaultXmlSymbolTable {
    static final String mNsPrefixXml;
    static final String mNsPrefixXmlns;
    static final SymbolTable sInstance;

    static {
        SymbolTable symbolTable = new SymbolTable(true, 128);
        sInstance = symbolTable;
        mNsPrefixXml = symbolTable.findSymbol("xml");
        mNsPrefixXmlns = symbolTable.findSymbol("xmlns");
        symbolTable.findSymbol("id");
        symbolTable.findSymbol("name");
        symbolTable.findSymbol("xsd");
        symbolTable.findSymbol("xsi");
        symbolTable.findSymbol(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        symbolTable.findSymbol("soap");
        symbolTable.findSymbol("SOAP-ENC");
        symbolTable.findSymbol("SOAP-ENV");
        symbolTable.findSymbol("Body");
        symbolTable.findSymbol("Envelope");
    }

    public static SymbolTable getInstance() {
        return sInstance.makeChild();
    }
}
