package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class MuseResponse implements Comparable<MuseResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.MuseResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MuseResponse museResponse) {
        return museResponse == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof MuseResponse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
