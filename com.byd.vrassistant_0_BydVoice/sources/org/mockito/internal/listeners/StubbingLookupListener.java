package org.mockito.internal.listeners;

import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* JADX INFO: loaded from: classes4.dex */
public interface StubbingLookupListener {
    void onStubbingLookup(Invocation invocation, MatchableInvocation matchableInvocation);
}
