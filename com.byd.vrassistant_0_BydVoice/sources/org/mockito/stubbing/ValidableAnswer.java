package org.mockito.stubbing;

import org.mockito.Incubating;
import org.mockito.invocation.InvocationOnMock;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface ValidableAnswer {
    void validateFor(InvocationOnMock invocationOnMock);
}
