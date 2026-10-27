package s2;

import android.view.View;
import q2.e;

/* JADX INFO: compiled from: Invalidable.java */
/* JADX INFO: loaded from: classes.dex */
public interface a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final b f7894b = new b();

    /* JADX INFO: renamed from: s2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Invalidable.java */
    public interface InterfaceC0117a {
        void a(boolean z7, boolean z8);
    }

    /* JADX INFO: compiled from: Invalidable.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public InterfaceC0117a f7895a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default boolean isInvalid() {
        if (this instanceof View) {
            return e.a((View) this);
        }
        return false;
    }

    default void registerInvalidStateChangedCallback(InterfaceC0117a interfaceC0117a) {
        f7894b.f7895a = interfaceC0117a;
    }

    default void setInvalid(boolean z7) {
        setInvalid(z7, false);
    }

    default void unregisterInvalidStateChangedCallback() {
        f7894b.f7895a = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setInvalid(boolean z7, boolean z8) {
        if (this instanceof View) {
            e.b((View) this, z7);
            InterfaceC0117a interfaceC0117a = f7894b.f7895a;
            if (interfaceC0117a != null) {
                interfaceC0117a.a(z7, z8);
            }
        }
    }
}
