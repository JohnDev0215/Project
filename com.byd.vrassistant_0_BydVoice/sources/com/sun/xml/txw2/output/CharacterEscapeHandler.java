package com.sun.xml.txw2.output;

import java.io.IOException;
import java.io.Writer;

/* JADX INFO: loaded from: classes3.dex */
public interface CharacterEscapeHandler {
    void escape(char[] cArr, int i, int i2, boolean z, Writer writer) throws IOException;
}
