package org.mockito.listeners;

import org.mockito.Incubating;
import org.mockito.verification.VerificationEvent;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface VerificationListener extends MockitoListener {
    void onVerification(VerificationEvent verificationEvent);
}
