package kotlin.jvm.internal;

import p.d81;
import p.j81;
import p.r22;

/* JADX INFO: loaded from: classes.dex */
public abstract class MutablePropertyReference0 extends MutablePropertyReference implements j81 {
    @Override // kotlin.jvm.internal.CallableReference
    public final d81 a() {
        r22.a.getClass();
        return this;
    }

    @Override // p.py0
    public final Object invoke() {
        return get();
    }
}
