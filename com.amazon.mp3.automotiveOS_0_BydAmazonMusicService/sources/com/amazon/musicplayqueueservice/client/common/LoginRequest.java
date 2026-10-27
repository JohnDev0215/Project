package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LoginRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.LoginRequest");

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (abstractClientRequest instanceof LoginRequest) {
            return super.compareTo(abstractClientRequest);
        }
        return 1;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (obj instanceof LoginRequest) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
