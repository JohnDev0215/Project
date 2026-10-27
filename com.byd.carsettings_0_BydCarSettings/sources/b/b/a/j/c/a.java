package b.b.a.j.c;

import android.view.View;
import b.b.a.h.k;

/* JADX INFO: compiled from: Invalidable.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {
    public static final b C = new b();

    /* JADX INFO: renamed from: b.b.a.j.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Invalidable.java */
    public interface InterfaceC0023a {
        default void e(boolean z, boolean z2) {
        }

        default void l(View view, boolean z, boolean z2) {
        }
    }

    /* JADX INFO: compiled from: Invalidable.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InterfaceC0023a f551a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean a() {
        if (this instanceof View) {
            return k.a((View) this);
        }
        return false;
    }

    default void b() {
        C.f551a = null;
    }

    default void c(InterfaceC0023a interfaceC0023a) {
        C.f551a = interfaceC0023a;
    }

    default void setInvalid(boolean z) {
        setInvalid(z, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setInvalid(boolean z, boolean z2) {
        if (this instanceof View) {
            View view = (View) this;
            k.f(view, z);
            b bVar = C;
            InterfaceC0023a interfaceC0023a = bVar.f551a;
            if (interfaceC0023a != null) {
                interfaceC0023a.e(z, z2);
                bVar.f551a.l(view, z, z2);
            }
        }
    }
}
