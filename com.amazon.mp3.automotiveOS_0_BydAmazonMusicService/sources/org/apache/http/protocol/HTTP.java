package org.apache.http.protocol;

import java.nio.charset.Charset;
import org.apache.http.Consts;

/* JADX INFO: loaded from: classes7.dex */
public final class HTTP {
    public static final Charset DEF_CONTENT_CHARSET = Consts.ISO_8859_1;
    public static final Charset DEF_PROTOCOL_CHARSET = Consts.ASCII;

    public static boolean isWhitespace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }

    private HTTP() {
    }
}
