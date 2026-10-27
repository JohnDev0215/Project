package org.mockito.session;

import org.mockito.Incubating;
import org.mockito.MockitoSession;
import org.mockito.exceptions.misusing.UnfinishedMockingSessionException;
import org.mockito.quality.Strictness;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface MockitoSessionBuilder {
    @Incubating
    MockitoSessionBuilder initMocks(Object obj);

    @Incubating
    MockitoSession startMocking() throws UnfinishedMockingSessionException;

    @Incubating
    MockitoSessionBuilder strictness(Strictness strictness);
}
