package com.amazon.cirrus.libraryservice.v3;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetGenresResponse extends BaseGetResponse {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetGenresResponse");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetResponse, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseGetResponse baseGetResponse) {
        if (baseGetResponse == null) {
            return -1;
        }
        if (baseGetResponse == this) {
            return 0;
        }
        if (baseGetResponse instanceof GetGenresResponse) {
            return super.compareTo(baseGetResponse);
        }
        return 1;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetResponse
    public boolean equals(Object obj) {
        if (obj instanceof GetGenresResponse) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetResponse
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
