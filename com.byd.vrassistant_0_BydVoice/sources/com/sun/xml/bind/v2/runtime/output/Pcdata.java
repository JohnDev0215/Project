package com.sun.xml.bind.v2.runtime.output;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Pcdata implements CharSequence {
    @Override // java.lang.CharSequence
    public abstract String toString();

    public abstract void writeTo(UTF8XmlOutput uTF8XmlOutput) throws IOException;

    public void writeTo(char[] cArr, int i) {
        toString().getChars(0, length(), cArr, i);
    }
}
