package com.ctc.wstx.io;

/* JADX INFO: loaded from: classes5.dex */
public final class BufferRecycler {
    private volatile char[] mSmallCBuffer = null;
    private volatile char[] mMediumCBuffer = null;
    private volatile char[] mFullCBuffer = null;
    private volatile byte[] mFullBBuffer = null;
}
