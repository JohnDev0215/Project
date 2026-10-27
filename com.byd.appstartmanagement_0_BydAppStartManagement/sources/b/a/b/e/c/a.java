package b.a.b.e.c;

import android.view.View;
import b.a.b.c.d;

/* JADX INFO: compiled from: Invalidable.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f186a = new b();

    /* JADX INFO: renamed from: b.a.b.e.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Invalidable.java */
    public interface InterfaceC0018a {
        default void a(boolean z, boolean z2) {
        }

        default void b(View view, boolean z, boolean z2) {
        }
    }

    /* JADX INFO: compiled from: Invalidable.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InterfaceC0018a f187a;
    }

    default void setInvalid(boolean z) {
        setInvalid(z, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setInvalid(boolean z, boolean z2) {
        if (this instanceof View) {
            View view = (View) this;
            d.a(view, z);
            b bVar = f186a;
            InterfaceC0018a interfaceC0018a = bVar.f187a;
            if (interfaceC0018a != null) {
                interfaceC0018a.a(z, z2);
                bVar.f187a.b(view, z, z2);
            }
        }
    }
}
