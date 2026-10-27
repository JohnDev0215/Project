package org.objenesis;

import org.objenesis.instantiator.ObjectInstantiator;

/* JADX INFO: loaded from: classes4.dex */
public interface Objenesis {
    <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls);

    <T> T newInstance(Class<T> cls);
}
