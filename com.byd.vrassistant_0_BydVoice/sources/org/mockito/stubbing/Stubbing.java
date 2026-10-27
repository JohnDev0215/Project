package org.mockito.stubbing;

import org.mockito.NotExtensible;
import org.mockito.invocation.Invocation;

/* JADX INFO: loaded from: classes4.dex */
@NotExtensible
public interface Stubbing extends Answer {
    Invocation getInvocation();

    boolean wasUsed();
}
