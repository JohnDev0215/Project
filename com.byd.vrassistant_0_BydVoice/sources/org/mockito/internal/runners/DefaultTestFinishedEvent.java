package org.mockito.internal.runners;

import org.mockito.internal.junit.TestFinishedEvent;

/* JADX INFO: loaded from: classes4.dex */
class DefaultTestFinishedEvent implements TestFinishedEvent {
    private final Throwable failure;
    private final Object testClassInstance;
    private final String testMethodName;

    DefaultTestFinishedEvent(Object obj, String str, Throwable th) {
        this.testClassInstance = obj;
        this.testMethodName = str;
        this.failure = th;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public Object getTestClassInstance() {
        return this.testClassInstance;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public String getTestMethodName() {
        return this.testMethodName;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public Throwable getFailure() {
        return this.failure;
    }
}
