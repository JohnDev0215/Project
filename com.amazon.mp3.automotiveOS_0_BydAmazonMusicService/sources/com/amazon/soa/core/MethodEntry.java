package com.amazon.soa.core;

import SOACoreInterface.v1_0.Method;

/* JADX INFO: loaded from: classes5.dex */
class MethodEntry {
    public final Method method;
    public final String ownerId;
    private MethodStatus status = MethodStatus.QUEUED;
    private String timestamp;

    public enum MethodStatus {
        QUEUED,
        PROCESSING
    }

    MethodEntry(final String ownerId, final Method method, final String timestamp) {
        this.ownerId = ownerId;
        this.method = method;
        this.timestamp = timestamp;
    }

    public void setStatus(final MethodStatus status) {
        this.status = status;
    }
}
