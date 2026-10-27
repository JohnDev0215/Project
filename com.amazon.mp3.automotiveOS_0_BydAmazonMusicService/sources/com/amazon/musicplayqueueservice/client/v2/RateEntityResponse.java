package com.amazon.musicplayqueueservice.client.v2;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RateEntityResponse implements Comparable<RateEntityResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.RateEntityResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RateEntityResponse rateEntityResponse) {
        return rateEntityResponse == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof RateEntityResponse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
