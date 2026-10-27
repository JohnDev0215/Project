package K3;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0987z;

/* JADX INFO: loaded from: classes.dex */
public final class g extends A9.b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final g f3528n = new g(4);
    public static final f o = new f();

    @Override // A9.b
    public final void M0(InterfaceC0987z interfaceC0987z) {
        if (!(interfaceC0987z instanceof DefaultLifecycleObserver)) {
            throw new IllegalArgumentException((interfaceC0987z + " must implement androidx.lifecycle.DefaultLifecycleObserver.").toString());
        }
        DefaultLifecycleObserver defaultLifecycleObserver = (DefaultLifecycleObserver) interfaceC0987z;
        f fVar = o;
        defaultLifecycleObserver.onCreate(fVar);
        defaultLifecycleObserver.onStart(fVar);
        defaultLifecycleObserver.onResume(fVar);
    }

    @Override // A9.b
    public final EnumC0981t O0() {
        return EnumC0981t.f12007p;
    }

    @Override // A9.b
    public final void V0(InterfaceC0987z interfaceC0987z) {
    }

    @Override // A9.b
    public final String toString() {
        return "coil.request.GlobalLifecycle";
    }
}
