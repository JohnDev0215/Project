package com.google.common.hash;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes6.dex */
public interface HashFunction {
    HashCode hashString(CharSequence charSequence, Charset charset);

    Hasher newHasher();
}
