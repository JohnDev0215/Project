package com.google.common.hash;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public interface Hasher {
    HashCode hash();

    Hasher putBytes(byte[] bArr, int i, int i2);

    Hasher putString(CharSequence charSequence, Charset charset);
}
