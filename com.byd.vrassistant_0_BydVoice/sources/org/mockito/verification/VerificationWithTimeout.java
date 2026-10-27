package org.mockito.verification;

/* JADX INFO: loaded from: classes4.dex */
public interface VerificationWithTimeout extends VerificationMode {
    VerificationMode atLeast(int i);

    VerificationMode atLeastOnce();

    VerificationMode only();

    VerificationMode times(int i);
}
