package org.objenesis.strategy;

import org.objenesis.instantiator.ObjectInstantiator;

/* JADX INFO: loaded from: classes4.dex */
public interface InstantiatorStrategy {
    <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls);
}
