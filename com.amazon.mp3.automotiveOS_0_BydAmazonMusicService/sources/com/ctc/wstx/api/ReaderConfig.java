package com.ctc.wstx.api;

import com.ctc.wstx.api.WstxInputProperties;
import com.ctc.wstx.io.BufferRecycler;
import com.ctc.wstx.util.SymbolTable;
import com.google.android.gms.wearable.WearableStatusCodes;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import javax.xml.stream.XMLResolver;

/* JADX INFO: loaded from: classes5.dex */
public final class ReaderConfig extends CommonConfig {
    static final ThreadLocal<SoftReference<BufferRecycler>> mRecyclerRef;
    static final HashMap<String, Integer> sProperties;
    protected int mConfigFlagMods;
    protected int mConfigFlags;
    BufferRecycler mCurrRecycler;
    XMLResolver mDtdResolver;
    XMLResolver mEntityResolver;
    protected int mInputBufferLen;
    protected final boolean mIsJ2MESubset;
    protected int mMaxAttributeSize;
    protected int mMaxAttributesPerElement;
    protected long mMaxCharacters;
    protected int mMaxChildrenPerElement;
    protected long mMaxElementCount;
    protected int mMaxElementDepth;
    protected long mMaxEntityCount;
    protected int mMaxEntityDepth;
    protected int mMaxTextLength;
    protected int mMinTextSegmentLen;
    protected WstxInputProperties.ParsingMode mParsingMode;
    Object[] mSpecialProperties;
    protected final SymbolTable mSymbols;
    protected boolean mXml11;

    static {
        HashMap<String, Integer> map = new HashMap<>(64);
        sProperties = map;
        map.put("javax.xml.stream.isCoalescing", 1);
        map.put("javax.xml.stream.isNamespaceAware", 2);
        map.put("javax.xml.stream.isReplacingEntityReferences", 3);
        map.put("javax.xml.stream.isSupportingExternalEntities", 4);
        map.put("javax.xml.stream.isValidating", 5);
        map.put("javax.xml.stream.supportDTD", 6);
        map.put("javax.xml.stream.allocator", 7);
        map.put("javax.xml.stream.reporter", 8);
        map.put("javax.xml.stream.resolver", 9);
        map.put("org.codehaus.stax2.internNames", 21);
        map.put("org.codehaus.stax2.internNsUris", 20);
        map.put("http://java.sun.com/xml/stream/properties/report-cdata-event", 22);
        map.put("org.codehaus.stax2.reportPrologWhitespace", 23);
        map.put("org.codehaus.stax2.preserveLocation", 24);
        map.put("org.codehaus.stax2.closeInputSource", 25);
        map.put("org.codehaus.stax2.supportXmlId", 26);
        map.put("org.codehaus.stax2.propDtdOverride", 27);
        map.put("http://javax.xml.XMLConstants/feature/secure-processing", 30);
        map.put("com.ctc.wstx.cacheDTDs", 42);
        map.put("com.ctc.wstx.cacheDTDsByPublicId", 43);
        map.put("com.ctc.wstx.lazyParsing", 44);
        map.put("com.ctc.wstx.treatCharRefsAsEnts", 46);
        map.put("com.ctc.wstx.allowXml11EscapedCharsInXml10", 47);
        map.put("com.ctc.wstx.normalizeLFs", 40);
        map.put("com.ctc.wstx.inputBufferLength", 50);
        map.put("com.ctc.wstx.minTextSegment", 52);
        map.put("com.ctc.wstx.maxAttributesPerElement", 60);
        map.put("com.ctc.wstx.maxAttributeSize", 65);
        map.put("com.ctc.wstx.maxChildrenPerElement", 61);
        map.put("com.ctc.wstx.maxTextLength", 66);
        map.put("com.ctc.wstx.maxElementCount", 62);
        map.put("com.ctc.wstx.maxElementDepth", 63);
        map.put("com.ctc.wstx.maxEntityDepth", 68);
        map.put("com.ctc.wstx.maxEntityCount", 67);
        map.put("com.ctc.wstx.maxCharacters", 64);
        map.put("com.ctc.wstx.customInternalEntities", 53);
        map.put("com.ctc.wstx.dtdResolver", 54);
        map.put("com.ctc.wstx.entityResolver", 55);
        map.put("com.ctc.wstx.undeclaredEntityResolver", 56);
        map.put("com.ctc.wstx.baseURL", 57);
        map.put("com.ctc.wstx.fragmentMode", 58);
        mRecyclerRef = new ThreadLocal<>();
    }

    private ReaderConfig(ReaderConfig readerConfig, boolean z, SymbolTable symbolTable, int i, int i2, int i3, int i4) {
        super(readerConfig);
        this.mMaxAttributesPerElement = 1000;
        this.mMaxAttributeSize = 524288;
        this.mMaxChildrenPerElement = Integer.MAX_VALUE;
        this.mMaxElementDepth = 1000;
        this.mMaxElementCount = Long.MAX_VALUE;
        this.mMaxCharacters = Long.MAX_VALUE;
        this.mMaxTextLength = Integer.MAX_VALUE;
        this.mMaxEntityDepth = 500;
        this.mMaxEntityCount = 100000L;
        this.mParsingMode = WstxInputProperties.PARSING_MODE_DOCUMENT;
        this.mXml11 = false;
        this.mDtdResolver = null;
        this.mEntityResolver = null;
        this.mSpecialProperties = null;
        this.mCurrRecycler = null;
        this.mIsJ2MESubset = z;
        this.mSymbols = symbolTable;
        this.mConfigFlags = i;
        this.mConfigFlagMods = i2;
        this.mInputBufferLen = i3;
        this.mMinTextSegmentLen = i4;
        if (readerConfig != null) {
            this.mMaxAttributesPerElement = readerConfig.mMaxAttributesPerElement;
            this.mMaxAttributeSize = readerConfig.mMaxAttributeSize;
            this.mMaxChildrenPerElement = readerConfig.mMaxChildrenPerElement;
            this.mMaxElementCount = readerConfig.mMaxElementCount;
            this.mMaxElementDepth = readerConfig.mMaxElementDepth;
            this.mMaxCharacters = readerConfig.mMaxCharacters;
            this.mMaxTextLength = readerConfig.mMaxTextLength;
            this.mMaxEntityDepth = readerConfig.mMaxEntityDepth;
            this.mMaxEntityCount = readerConfig.mMaxEntityCount;
        }
        SoftReference<BufferRecycler> softReference = mRecyclerRef.get();
        if (softReference != null) {
            this.mCurrRecycler = softReference.get();
        }
    }

    public static ReaderConfig createFullDefaults() {
        return new ReaderConfig(null, false, null, 2973213, 0, WearableStatusCodes.TARGET_NODE_NOT_CONNECTED, 64);
    }

    public int getDtdCacheSize() {
        return this.mIsJ2MESubset ? 5 : 12;
    }

    public int getConfigFlags() {
        return this.mConfigFlags;
    }

    public int getInputBufferLength() {
        return this.mInputBufferLen;
    }

    public int getShortestReportedTextSegment() {
        return this.mMinTextSegmentLen;
    }

    public int getMaxAttributesPerElement() {
        return this.mMaxAttributesPerElement;
    }

    public int getMaxAttributeSize() {
        return this.mMaxAttributeSize;
    }

    public int getMaxChildrenPerElement() {
        return this.mMaxChildrenPerElement;
    }

    public int getMaxElementDepth() {
        return this.mMaxElementDepth;
    }

    public int getMaxEntityDepth() {
        return this.mMaxEntityDepth;
    }

    public void setConfigFlag(int i) {
        this.mConfigFlags |= i;
        this.mConfigFlagMods = i | this.mConfigFlagMods;
    }

    public void setInputBufferLength(int i) {
        if (i < 8) {
            i = 8;
        }
        this.mInputBufferLen = i;
    }

    public void setShortestReportedTextSegment(int i) {
        this.mMinTextSegmentLen = i;
    }

    public void setMaxAttributesPerElement(int i) {
        this.mMaxAttributesPerElement = i;
    }

    public void setMaxAttributeSize(int i) {
        this.mMaxAttributeSize = i;
    }

    public void setMaxChildrenPerElement(int i) {
        this.mMaxChildrenPerElement = i;
    }

    public void setMaxElementDepth(int i) {
        this.mMaxElementDepth = i;
    }

    public void setMaxTextLength(int i) {
        this.mMaxTextLength = i;
    }

    public void setMaxEntityDepth(int i) {
        this.mMaxEntityDepth = i;
    }
}
