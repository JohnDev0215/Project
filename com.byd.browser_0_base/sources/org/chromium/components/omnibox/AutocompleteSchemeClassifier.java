package org.chromium.components.omnibox;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class AutocompleteSchemeClassifier {
    public final long a;

    public AutocompleteSchemeClassifier(long j) {
        this.a = j;
    }

    @CalledByNative
    public long getNativePtr() {
        return this.a;
    }

    public void a() {
    }
}
