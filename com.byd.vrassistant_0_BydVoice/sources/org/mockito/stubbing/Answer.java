package org.mockito.stubbing;

import org.mockito.invocation.InvocationOnMock;

/* JADX INFO: loaded from: classes4.dex */
public interface Answer<T> {
    T answer(InvocationOnMock invocationOnMock) throws Throwable;
}
