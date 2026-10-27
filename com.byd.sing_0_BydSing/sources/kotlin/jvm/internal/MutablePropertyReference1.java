package kotlin.jvm.internal;

import kotlin.reflect.g;
import kotlin.reflect.j;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference1 extends MutablePropertyReference implements kotlin.reflect.g {
    public MutablePropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected kotlin.reflect.b computeReflected() {
        return j.c(this);
    }

    public abstract /* synthetic */ V get(T t);

    @Override // kotlin.reflect.j
    public Object getDelegate(Object obj) {
        return ((kotlin.reflect.g) getReflected()).getDelegate(obj);
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public abstract /* synthetic */ void set(T t, V v);

    public MutablePropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference, kotlin.jvm.internal.PropertyReference
    public j.a getGetter() {
        return ((kotlin.reflect.g) getReflected()).getGetter();
    }

    @Override // kotlin.jvm.internal.MutablePropertyReference
    public g.a getSetter() {
        return ((kotlin.reflect.g) getReflected()).getSetter();
    }

    public MutablePropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
