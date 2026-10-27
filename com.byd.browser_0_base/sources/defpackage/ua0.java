package defpackage;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: Builders.common.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ua0<T> extends oe2<T> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater g = AtomicIntegerFieldUpdater.newUpdater(ua0.class, "_decision");
    private volatile /* synthetic */ int _decision;

    @Override // defpackage.oe2, defpackage.AbstractC0197m
    public final void G(Object obj) {
        boolean z;
        while (true) {
            int i = this._decision;
            z = false;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("Already resumed".toString());
                }
            } else if (g.compareAndSet(this, 0, 2)) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        b00 b00VarV = oj2.v(null);
        if (obj instanceof rv) {
            obj = a8.v(((rv) obj).a);
        }
        a8.e0(b00VarV, obj, null);
    }

    @Override // defpackage.oe2, defpackage.r31
    public final void d(Object obj) {
        G(obj);
    }
}
