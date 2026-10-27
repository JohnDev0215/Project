package org.mockito.internal.debugging;

import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;

/* JADX INFO: loaded from: classes4.dex */
public interface FindingsListener {
    void foundStubCalledWithDifferentArgs(Invocation invocation, InvocationMatcher invocationMatcher);

    void foundUnstubbed(InvocationMatcher invocationMatcher);

    void foundUnusedStub(Invocation invocation);
}
