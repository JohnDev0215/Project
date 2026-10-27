package org.mockito.internal.verification.api;

import java.util.List;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* JADX INFO: loaded from: classes4.dex */
public interface VerificationDataInOrder {
    List<Invocation> getAllInvocations();

    InOrderContext getOrderingContext();

    MatchableInvocation getWanted();
}
