package org.mockito;

import org.mockito.verification.VerificationMode;

/* JADX INFO: loaded from: classes4.dex */
public interface InOrder {
    <T> T verify(T t);

    <T> T verify(T t, VerificationMode verificationMode);

    void verifyNoMoreInteractions();
}
