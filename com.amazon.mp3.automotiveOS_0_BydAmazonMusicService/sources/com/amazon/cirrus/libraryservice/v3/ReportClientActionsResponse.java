package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class ReportClientActionsResponse implements Comparable<ReportClientActionsResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.ReportClientActionsResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ReportClientActionsResponse reportClientActionsResponse) {
        return reportClientActionsResponse == null ? -1 : 0;
    }

    public boolean equals(Object obj) {
        return obj instanceof ReportClientActionsResponse;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode));
    }
}
