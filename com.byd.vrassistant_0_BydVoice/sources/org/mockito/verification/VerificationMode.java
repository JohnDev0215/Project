package org.mockito.verification;

import org.mockito.internal.verification.api.VerificationData;

/* JADX INFO: loaded from: classes4.dex */
public interface VerificationMode {
    VerificationMode description(String str);

    void verify(VerificationData verificationData);
}
