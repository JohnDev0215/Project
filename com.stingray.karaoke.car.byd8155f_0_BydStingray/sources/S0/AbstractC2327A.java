package s0;

import q0.C2162a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: s0.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2327A {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C2162a f21954a;

    public abstract void a(p0.d dVar);

    public InterfaceC2782k b() {
        return this.f21954a;
    }

    public final void c() {
        InterfaceC2782k interfaceC2782kB = b();
        if (interfaceC2782kB != null) {
            interfaceC2782kB.b(this);
        }
    }

    public void d(C2162a c2162a) {
        this.f21954a = c2162a;
    }
}
