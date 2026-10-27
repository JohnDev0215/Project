package org.mockito.internal.junit;

/* JADX INFO: loaded from: classes4.dex */
public interface TestFinishedEvent {
    Throwable getFailure();

    Object getTestClassInstance();

    String getTestMethodName();
}
