package com.sun.xml.bind.marshaller;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes3.dex */
public class NoEscapeHandler implements CharacterEscapeHandler {
    public static final NoEscapeHandler theInstance = new NoEscapeHandler();

    @Override // com.sun.xml.bind.marshaller.CharacterEscapeHandler
    public void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException {
        writer.write(cArr, i, i2);
    }
}
