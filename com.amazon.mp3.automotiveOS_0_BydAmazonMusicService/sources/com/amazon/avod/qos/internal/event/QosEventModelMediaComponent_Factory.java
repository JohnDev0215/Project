package com.amazon.avod.qos.internal.event;

import dagger.internal.Factory;

/* JADX INFO: loaded from: classes2.dex */
public final class QosEventModelMediaComponent_Factory implements Factory<QosEventModelMediaComponent> {
    private static final QosEventModelMediaComponent_Factory INSTANCE = new QosEventModelMediaComponent_Factory();

    @Override // javax.inject.Provider
    public QosEventModelMediaComponent get() {
        return new QosEventModelMediaComponent();
    }

    public static Factory<QosEventModelMediaComponent> create() {
        return INSTANCE;
    }
}
