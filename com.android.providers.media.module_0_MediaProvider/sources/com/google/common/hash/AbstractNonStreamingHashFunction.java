package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@Immutable
abstract class AbstractNonStreamingHashFunction extends AbstractHashFunction {
    AbstractNonStreamingHashFunction() {
    }

    @Override // com.google.common.hash.HashFunction
    public HashCode hashString(CharSequence charSequence, Charset charset) {
        return hashBytes(charSequence.toString().getBytes(charset));
    }
}
