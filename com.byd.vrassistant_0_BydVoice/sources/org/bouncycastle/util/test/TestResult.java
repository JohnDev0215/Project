package org.bouncycastle.util.test;

/* JADX INFO: loaded from: classes4.dex */
public interface TestResult {
    Throwable getException();

    boolean isSuccessful();

    String toString();
}
