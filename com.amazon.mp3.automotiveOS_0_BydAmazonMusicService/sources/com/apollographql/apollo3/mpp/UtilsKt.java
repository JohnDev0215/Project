package com.apollographql.apollo3.mpp;

import kotlin.Metadata;

/* JADX INFO: compiled from: utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0001\u001a\u00020\u0000\u001a\u0006\u0010\u0003\u001a\u00020\u0002\u001a\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"", "currentTimeMillis", "", "assertMainThreadOnNative", "Lcom/apollographql/apollo3/mpp/Platform;", "platform", "apollo-mpp-utils"}, k = 2, mv = {1, 5, 1})
public final class UtilsKt {
    public static final void assertMainThreadOnNative() {
    }

    public static final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static final Platform platform() {
        return Platform.Jvm;
    }
}
