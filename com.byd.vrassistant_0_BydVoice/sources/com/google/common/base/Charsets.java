package com.google.common.base;

import java.nio.charset.Charset;
import org.apache.commons.compress.utils.CharsetNames;

/* JADX INFO: loaded from: classes2.dex */
@ElementTypesAreNonnullByDefault
public final class Charsets {
    public static final Charset US_ASCII = Charset.forName(CharsetNames.US_ASCII);
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName(CharsetNames.UTF_8);
    public static final Charset UTF_16BE = Charset.forName(CharsetNames.UTF_16BE);
    public static final Charset UTF_16LE = Charset.forName(CharsetNames.UTF_16LE);
    public static final Charset UTF_16 = Charset.forName(CharsetNames.UTF_16);

    private Charsets() {
    }
}
