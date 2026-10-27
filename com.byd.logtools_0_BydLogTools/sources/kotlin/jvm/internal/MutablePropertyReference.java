package kotlin.jvm.internal;

import kotlin.reflect.h;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference extends PropertyReference implements kotlin.reflect.h {
    public MutablePropertyReference() {
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public abstract /* synthetic */ h.a<R> getGetter();

    public abstract /* synthetic */ kotlin.reflect.f<R> getSetter();

    public MutablePropertyReference(Object obj) {
        super(obj);
    }
}
