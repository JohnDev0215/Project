package com.amazon.avod.util;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class EmptyToNullFunction implements Function<String, String> {
    @Override // com.google.common.base.Function
    @Nullable
    public String apply(@Nullable String str) {
        return Strings.emptyToNull(str);
    }
}
