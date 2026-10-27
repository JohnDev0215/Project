package org.mockito.internal.verification.api;

import org.mockito.invocation.Invocation;

/* JADX INFO: loaded from: classes4.dex */
public interface InOrderContext {
    boolean isVerified(Invocation invocation);

    void markVerified(Invocation invocation);
}
