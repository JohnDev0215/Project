package org.mockito.internal.creation.instance;

import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultInstantiatorProvider implements InstantiatorProvider {
    private static final Instantiator INSTANCE = new ObjenesisInstantiator();

    @Override // org.mockito.plugins.InstantiatorProvider
    public Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings) {
        if (mockCreationSettings != null && mockCreationSettings.getConstructorArgs() != null) {
            return new ConstructorInstantiator(mockCreationSettings.getOuterClassInstance() != null, mockCreationSettings.getConstructorArgs());
        }
        return INSTANCE;
    }
}
