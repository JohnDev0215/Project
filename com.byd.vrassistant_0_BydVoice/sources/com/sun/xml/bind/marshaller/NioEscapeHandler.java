package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

/* JADX INFO: loaded from: classes3.dex */
public class NioEscapeHandler implements CharacterEscapeHandler {
    private final CharsetEncoder encoder;

    public NioEscapeHandler(String str) {
        this.encoder = Charset.forName(str).newEncoder();
    }

    @Override // com.sun.xml.bind.marshaller.CharacterEscapeHandler
    public void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException {
        int i3 = i2 + i;
        while (i < i3) {
            char c = cArr[i];
            if (c != '\"') {
                if (c == '&') {
                    writer.write("&amp;");
                } else if (c == '<') {
                    writer.write("&lt;");
                } else if (c == '>') {
                    writer.write("&gt;");
                } else if (this.encoder.canEncode(c)) {
                    writer.write(cArr[i]);
                } else {
                    writer.write("&#");
                    writer.write(Integer.toString(cArr[i]));
                    writer.write(59);
                }
            } else if (z) {
                writer.write("&quot;");
            } else {
                writer.write(34);
            }
            i++;
        }
    }
}
