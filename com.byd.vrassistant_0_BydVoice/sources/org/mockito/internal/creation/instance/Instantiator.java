package org.mockito.internal.creation.instance;

/* JADX INFO: loaded from: classes4.dex */
public interface Instantiator {
    <T> T newInstance(Class<T> cls) throws InstantiationException;
}
