package org.objenesis.instantiator.basic;

import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/* JADX INFO: loaded from: classes4.dex */
@Instantiator(Typology.NOT_COMPLIANT)
public class AccessibleInstantiator<T> extends ConstructorInstantiator<T> {
    public AccessibleInstantiator(Class<T> cls) {
        super(cls);
        if (this.constructor != null) {
            this.constructor.setAccessible(true);
        }
    }
}
