package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes3.dex */
public class DumbEscapeHandler implements CharacterEscapeHandler {
    public static final CharacterEscapeHandler theInstance = new DumbEscapeHandler();

    private DumbEscapeHandler() {
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
                } else if (c > 127) {
                    writer.write("&#");
                    writer.write(Integer.toString(cArr[i]));
                    writer.write(59);
                } else {
                    writer.write(c);
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
