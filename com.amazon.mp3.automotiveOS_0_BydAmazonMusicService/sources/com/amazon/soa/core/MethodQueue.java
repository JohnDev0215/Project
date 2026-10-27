package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;

/* JADX INFO: loaded from: classes5.dex */
abstract class MethodQueue {
    public abstract void addMethod(final String ownerId, final Method method);

    MethodQueue() {
    }
}
