package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class KeyStyle {
    private final KeyboardTextsSet mTextsSet;

    public abstract int getFlags(TypedArray a, int index);

    public abstract int getInt(TypedArray a, int index, int defaultValue);

    @Nullable
    public abstract String getString(TypedArray a, int index);

    @Nullable
    public abstract String[] getStringArray(TypedArray a, int index);

    protected KeyStyle(@Nonnull final KeyboardTextsSet textsSet) {
        this.mTextsSet = textsSet;
    }

    @Nullable
    protected String parseString(final TypedArray a, final int index) {
        if (a.hasValue(index)) {
            return this.mTextsSet.resolveTextReference(a.getString(index));
        }
        return null;
    }

    @Nullable
    protected String[] parseStringArray(final TypedArray a, final int index) {
        if (a.hasValue(index)) {
            return MoreKeySpec.splitKeySpecs(this.mTextsSet.resolveTextReference(a.getString(index)));
        }
        return null;
    }
}
