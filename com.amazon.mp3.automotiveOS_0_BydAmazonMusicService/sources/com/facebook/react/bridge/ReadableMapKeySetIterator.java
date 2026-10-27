package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface ReadableMapKeySetIterator {
    boolean hasNextKey();

    String nextKey();
}
