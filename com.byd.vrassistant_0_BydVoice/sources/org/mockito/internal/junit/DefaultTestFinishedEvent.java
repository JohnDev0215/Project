package org.mockito.internal.junit;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultTestFinishedEvent implements TestFinishedEvent {
    private final Object testClassInstance;
    private final Throwable testFailure;
    private final String testMethodName;

    public DefaultTestFinishedEvent(Object obj, String str, Throwable th) {
        this.testClassInstance = obj;
        this.testMethodName = str;
        this.testFailure = th;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public Throwable getFailure() {
        return this.testFailure;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public Object getTestClassInstance() {
        return this.testClassInstance;
    }

    @Override // org.mockito.internal.junit.TestFinishedEvent
    public String getTestMethodName() {
        return this.testMethodName;
    }
}
