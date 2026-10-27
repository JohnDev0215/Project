package org.mockito.internal.creation.instance;

import org.mockito.internal.configuration.GlobalConfiguration;
import org.objenesis.ObjenesisStd;

/* JADX INFO: loaded from: classes4.dex */
class ObjenesisInstantiator implements Instantiator {
    private final ObjenesisStd objenesis = new ObjenesisStd(new GlobalConfiguration().enableClassCache());

    ObjenesisInstantiator() {
    }

    @Override // org.mockito.internal.creation.instance.Instantiator
    public <T> T newInstance(Class<T> cls) {
        return (T) this.objenesis.newInstance(cls);
    }
}
