package org.mockito.verification;

import org.mockito.Incubating;
import org.mockito.internal.verification.api.VerificationData;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface VerificationEvent {
    VerificationData getData();

    Object getMock();

    VerificationMode getMode();

    Throwable getVerificationError();
}
