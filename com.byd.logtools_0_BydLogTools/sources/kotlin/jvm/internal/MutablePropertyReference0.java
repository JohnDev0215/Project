package kotlin.jvm.internal;

import kotlin.reflect.g;
import kotlin.reflect.i;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements kotlin.reflect.g {
    public MutablePropertyReference0() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected kotlin.reflect.b computeReflected() {
        return j.c(this);
    }

    public abstract /* synthetic */ R get();

    @Override // kotlin.reflect.i
    public Object getDelegate() {
        return ((kotlin.reflect.g) getReflected()).getDelegate();
    }

    @Override // kotlin.jvm.b.a
    public Object invoke() {
        return get();
    }

    public abstract /* synthetic */ void set(R r);

    public MutablePropertyReference0(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public i.a getGetter() {
        return ((kotlin.reflect.g) getReflected()).getGetter();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public g.a getSetter() {
        return ((kotlin.reflect.g) getReflected()).getSetter();
    }
}
