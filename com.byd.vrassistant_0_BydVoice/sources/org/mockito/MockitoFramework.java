package org.mockito;

import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.invocation.InvocationFactory;
import org.mockito.listeners.MockitoListener;
import org.mockito.plugins.MockitoPlugins;

/* JADX INFO: loaded from: classes4.dex */
@Incubating
public interface MockitoFramework {
    @Incubating
    MockitoFramework addListener(MockitoListener mockitoListener) throws RedundantListenerException;

    @Incubating
    InvocationFactory getInvocationFactory();

    @Incubating
    MockitoPlugins getPlugins();

    @Incubating
    MockitoFramework removeListener(MockitoListener mockitoListener);
}
