package org.bouncycastle.util.test;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public interface TestResult {
    Throwable getException();

    boolean isSuccessful();

    String toString();
}
