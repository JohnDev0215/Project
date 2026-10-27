package com.amazon.ion.util;

import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public interface _Private_FastAppendable extends Appendable {
    void appendAscii(char c) throws IOException;

    void appendAscii(CharSequence charSequence) throws IOException;

    void appendAscii(CharSequence charSequence, int i, int i2) throws IOException;

    void appendUtf16(char c) throws IOException;

    void appendUtf16Surrogate(char c, char c2) throws IOException;
}
