package kotlin.jvm.internal;

import kotlin.reflect.KMutableProperty;

/* JADX INFO: loaded from: classes8.dex */
public abstract class MutablePropertyReference extends PropertyReference implements KMutableProperty {
    public MutablePropertyReference() {
    }

    public MutablePropertyReference(Object receiver) {
        super(receiver);
    }
}
