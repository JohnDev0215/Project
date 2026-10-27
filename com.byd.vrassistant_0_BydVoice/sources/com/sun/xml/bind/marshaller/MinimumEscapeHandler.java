package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes3.dex */
public class MinimumEscapeHandler implements CharacterEscapeHandler {
    public static final CharacterEscapeHandler theInstance = new MinimumEscapeHandler();

    private MinimumEscapeHandler() {
    }

    @Override // com.sun.xml.bind.marshaller.CharacterEscapeHandler
    public void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException {
        int i3 = i2 + i;
        int i4 = i;
        while (i < i3) {
            char c = cArr[i];
            if (c == '&' || c == '<' || c == '>' || c == '\r' || ((c == '\n' && z) || (c == '\"' && z))) {
                if (i != i4) {
                    writer.write(cArr, i4, i - i4);
                }
                i4 = i + 1;
                char c2 = cArr[i];
                if (c2 == '\n' || c2 == '\r') {
                    writer.write("&#");
                    writer.write(Integer.toString(c));
                    writer.write(59);
                } else if (c2 == '\"') {
                    writer.write("&quot;");
                } else if (c2 == '&') {
                    writer.write("&amp;");
                } else if (c2 == '<') {
                    writer.write("&lt;");
                } else if (c2 == '>') {
                    writer.write("&gt;");
                } else {
                    throw new IllegalArgumentException("Cannot escape: '" + c + "'");
                }
            }
            i++;
        }
        if (i4 != i3) {
            writer.write(cArr, i4, i3 - i4);
        }
    }
}
