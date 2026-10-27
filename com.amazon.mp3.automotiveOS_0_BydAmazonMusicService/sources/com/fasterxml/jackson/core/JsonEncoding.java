package com.fasterxml.jackson.core;

import com.amazon.music.destination.parser.ParserUtil;

/* JADX INFO: loaded from: classes6.dex */
public enum JsonEncoding {
    UTF8(ParserUtil.UTF_8, false, 8),
    UTF16_BE("UTF-16BE", true, 16),
    UTF16_LE("UTF-16LE", false, 16),
    UTF32_BE("UTF-32BE", true, 32),
    UTF32_LE("UTF-32LE", false, 32);

    private final boolean _bigEndian;
    private final int _bits;
    private final String _javaName;

    JsonEncoding(String str, boolean z, int i) {
        this._javaName = str;
        this._bigEndian = z;
        this._bits = i;
    }

    public String getJavaName() {
        return this._javaName;
    }

    public boolean isBigEndian() {
        return this._bigEndian;
    }

    public int bits() {
        return this._bits;
    }
}
