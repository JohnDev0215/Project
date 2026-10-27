package org.apache.http;

import com.amazon.music.destination.parser.ParserUtil;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes7.dex */
public final class Consts {
    public static final Charset UTF_8 = Charset.forName(ParserUtil.UTF_8);
    public static final Charset ASCII = Charset.forName("US-ASCII");
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");

    private Consts() {
    }
}
