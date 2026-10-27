package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ReportEventResponse implements Comparable<ReportEventResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.ReportEventResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ReportEventResponse reportEventResponse) {
        return reportEventResponse == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof ReportEventResponse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
