package com.amazon.avod.qos;

import com.amazon.avod.media.framework.MediaComponent;
import com.amazon.avod.qos.internal.event.QosEventModelMediaComponent;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
public final class QoSModule_Dagger_ProvideEventModelMediaComponentFactory implements Factory<MediaComponent> {
    private final Provider<QosEventModelMediaComponent> componentProvider;
    private final QoSModule_Dagger module;

    public QoSModule_Dagger_ProvideEventModelMediaComponentFactory(QoSModule_Dagger qoSModule_Dagger, Provider<QosEventModelMediaComponent> provider) {
        this.module = qoSModule_Dagger;
        this.componentProvider = provider;
    }

    @Override // javax.inject.Provider
    public MediaComponent get() {
        return (MediaComponent) Preconditions.checkNotNull(this.module.provideEventModelMediaComponent(this.componentProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MediaComponent> create(QoSModule_Dagger qoSModule_Dagger, Provider<QosEventModelMediaComponent> provider) {
        return new QoSModule_Dagger_ProvideEventModelMediaComponentFactory(qoSModule_Dagger, provider);
    }
}
