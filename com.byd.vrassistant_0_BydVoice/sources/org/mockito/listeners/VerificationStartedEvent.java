package org.mockito.listeners;

import org.mockito.Incubating;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface VerificationStartedEvent {
    @Incubating
    Object getMock();

    @Incubating
    void setMock(Object obj);
}
