package org.mockito.plugins;

import org.mockito.internal.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;

/* JADX INFO: loaded from: classes4.dex */
public interface InstantiatorProvider {
    Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings);
}
