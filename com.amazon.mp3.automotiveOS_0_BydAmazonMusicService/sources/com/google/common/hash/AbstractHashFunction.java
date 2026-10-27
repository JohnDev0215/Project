package com.google.common.hash;

import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractHashFunction implements HashFunction {
    AbstractHashFunction() {
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return newHasher().putString(charSequence, charset).hash();
    }
}
