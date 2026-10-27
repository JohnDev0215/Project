package com.google.common.hash;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public interface HashFunction {
    int bits();

    HashCode hashString(CharSequence charSequence, Charset charset);

    Hasher newHasher();

    Hasher newHasher(int i);
}
