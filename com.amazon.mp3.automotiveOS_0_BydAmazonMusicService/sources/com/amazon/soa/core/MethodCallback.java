package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;

/* JADX INFO: loaded from: classes5.dex */
public interface MethodCallback {
    void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener onMethodExecutedListener);
}
