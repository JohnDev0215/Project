package com.google.common.base;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class Charsets {
    public static final Charset UTF_8;

    static {
        Charset.forName("US-ASCII");
        Charset.forName("ISO-8859-1");
        UTF_8 = Charset.forName("UTF-8");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        Charset.forName("UTF-16");
    }
}
