package org.mockito.listeners;

import org.mockito.invocation.DescribedInvocation;

/* JADX INFO: loaded from: classes4.dex */
public interface MethodInvocationReport {
    DescribedInvocation getInvocation();

    String getLocationOfStubbing();

    Object getReturnedValue();

    Throwable getThrowable();

    boolean threwException();
}
