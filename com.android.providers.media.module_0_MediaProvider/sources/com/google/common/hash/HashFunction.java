package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
@Immutable
public interface HashFunction {
    HashCode hashString(CharSequence charSequence, Charset charset);
}
