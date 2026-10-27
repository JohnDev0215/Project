package com.amazon.avod.perf;

import com.amazon.avod.util.Preconditions2;
import com.google.common.collect.ImmutableList;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Extra {
    private final ImmutableList<String> mExtraData;
    private final String mName;

    public Extra(@Nullable String str) {
        this(str, ImmutableList.of());
    }

    public Extra(@Nullable String str, @Nonnull ImmutableList<String> immutableList) {
        Preconditions2.checkNotNullWeakly(str, "name");
        this.mName = str == null ? "null" : str;
        this.mExtraData = immutableList;
    }

    @Nonnull
    public String toString() {
        return String.format(Locale.US, "Extra[%s]", this.mName);
    }
}
