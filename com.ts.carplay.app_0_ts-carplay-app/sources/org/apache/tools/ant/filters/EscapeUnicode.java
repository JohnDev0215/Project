package org.apache.tools.ant.filters;

import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: classes.dex */
public class EscapeUnicode extends BaseParamFilterReader implements ChainableReader {
    private StringBuffer unicodeBuf;

    public EscapeUnicode() {
        this.unicodeBuf = new StringBuffer();
    }

    public EscapeUnicode(Reader in) {
        super(in);
        this.unicodeBuf = new StringBuffer();
    }

    @Override // java.io.FilterReader, java.io.Reader
    public final int read() throws IOException {
        if (!getInitialized()) {
            initialize();
            setInitialized(true);
        }
        if (this.unicodeBuf.length() == 0) {
            int ch = this.in.read();
            if (ch != -1) {
                char achar = (char) ch;
                if (achar >= 128) {
                    this.unicodeBuf = new StringBuffer("u0000");
                    String s = Integer.toHexString(ch);
                    for (int i = 0; i < s.length(); i++) {
                        StringBuffer stringBuffer = this.unicodeBuf;
                        stringBuffer.setCharAt((stringBuffer.length() - s.length()) + i, s.charAt(i));
                    }
                    return 92;
                }
                return ch;
            }
            return ch;
        }
        int ch2 = this.unicodeBuf.charAt(0);
        this.unicodeBuf.deleteCharAt(0);
        return ch2;
    }

    @Override // org.apache.tools.ant.filters.ChainableReader
    public final Reader chain(Reader rdr) {
        EscapeUnicode newFilter = new EscapeUnicode(rdr);
        newFilter.setInitialized(true);
        return newFilter;
    }

    private final void initialize() {
    }
}
