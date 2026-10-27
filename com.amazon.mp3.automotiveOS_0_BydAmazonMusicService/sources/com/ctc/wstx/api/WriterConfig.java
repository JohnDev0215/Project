package com.ctc.wstx.api;

import com.ctc.wstx.io.BufferRecycler;
import com.ctc.wstx.util.DataUtil;
import java.lang.ref.SoftReference;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public final class WriterConfig extends CommonConfig {
    static final ThreadLocal<SoftReference<BufferRecycler>> mRecyclerRef;
    static final HashMap<String, Integer> sProperties;
    protected int mConfigFlags;
    BufferRecycler mCurrRecycler;
    final boolean mIsJ2MESubset;
    Object[] mSpecialProperties;

    static {
        HashMap<String, Integer> map = new HashMap<>(8);
        sProperties = map;
        map.put("javax.xml.stream.isRepairingNamespaces", DataUtil.Integer(1));
        map.put("javax.xml.stream.isNamespaceAware", DataUtil.Integer(4));
        map.put("org.codehaus.stax2.automaticEmptyElements", DataUtil.Integer(2));
        map.put("org.codehaus.stax2.autoCloseOutput", DataUtil.Integer(3));
        map.put("org.codehaus.stax2.automaticNsPrefix", DataUtil.Integer(5));
        map.put("org.codehaus.stax2.textEscaper", DataUtil.Integer(6));
        map.put("org.codehaus.stax2.attrValueEscaper", DataUtil.Integer(7));
        map.put("javax.xml.stream.reporter", DataUtil.Integer(8));
        map.put("com.ctc.wstx.useDoubleQuotesInXmlDecl", DataUtil.Integer(10));
        map.put("com.ctc.wstx.outputCDataAsText", DataUtil.Integer(11));
        map.put("com.ctc.wstx.copyDefaultAttrs", DataUtil.Integer(12));
        map.put("com.ctc.wstx.outputEscapeCr", DataUtil.Integer(13));
        map.put("com.ctc.wstx.addSpaceAfterEmptyElem", DataUtil.Integer(14));
        map.put("com.ctc.wstx.automaticEndElements", DataUtil.Integer(15));
        map.put("com.ctc.wstx.outputInvalidCharHandler", DataUtil.Integer(21));
        map.put("com.ctc.wstx.outputEmptyElementHandler", DataUtil.Integer(22));
        map.put("com.ctc.wstx.outputValidateStructure", DataUtil.Integer(16));
        map.put("com.ctc.wstx.outputValidateContent", DataUtil.Integer(17));
        map.put("com.ctc.wstx.outputValidateAttr", DataUtil.Integer(18));
        map.put("com.ctc.wstx.outputValidateNames", DataUtil.Integer(19));
        map.put("com.ctc.wstx.outputFixContent", DataUtil.Integer(20));
        map.put("com.ctc.wstx.outputUnderlyingStream", DataUtil.Integer(30));
        map.put("com.ctc.wstx.outputUnderlyingStream", DataUtil.Integer(30));
        mRecyclerRef = new ThreadLocal<>();
    }

    private WriterConfig(WriterConfig writerConfig, boolean z, int i, Object[] objArr) {
        super(writerConfig);
        this.mCurrRecycler = null;
        this.mIsJ2MESubset = z;
        this.mConfigFlags = i;
        this.mSpecialProperties = objArr;
        SoftReference<BufferRecycler> softReference = mRecyclerRef.get();
        if (softReference != null) {
            this.mCurrRecycler = softReference.get();
        }
    }

    public static WriterConfig createFullDefaults() {
        return new WriterConfig(null, false, 933, null);
    }

    public int getConfigFlags() {
        return this.mConfigFlags;
    }
}
