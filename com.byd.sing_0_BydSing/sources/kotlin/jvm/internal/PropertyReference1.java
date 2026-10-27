package kotlin.jvm.internal;

import kotlin.reflect.j;

/* JADX INFO: loaded from: classes.dex */
public abstract class PropertyReference1 extends PropertyReference implements kotlin.reflect.j {
    public PropertyReference1() {
    }

    @Override // kotlin.jvm.internal.CallableReference
    protected kotlin.reflect.b computeReflected() {
        return j.d(this);
    }

    public abstract /* synthetic */ V get(T t);

    @Override // kotlin.reflect.j
    public Object getDelegate(Object obj) {
        return ((kotlin.reflect.j) getReflected()).getDelegate(obj);
    }

    public Object invoke(Object obj) {
        return get(obj);
    }

    public PropertyReference1(Object obj) {
        super(obj);
    }

    @Override // kotlin.jvm.internal.PropertyReference
    public j.a getGetter() {
        return ((kotlin.reflect.j) getReflected()).getGetter();
    }

    public PropertyReference1(Object obj, Class cls, String str, String str2, int i) {
        super(obj, cls, str, str2, i);
    }
}
