package g4;

import android.view.View;
import m4.g;

/* JADX INFO: compiled from: Invalidable.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0047b f5084c = new C0047b();

    /* JADX INFO: compiled from: Invalidable.java */
    public interface a {
        void a(boolean z7, boolean z8);
    }

    /* JADX INFO: renamed from: g4.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Invalidable.java */
    public static class C0047b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public a f5085a;
    }

    default void setInvalid(boolean z7) {
        setInvalid(z7, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setInvalid(boolean z7, boolean z8) {
        if (this instanceof View) {
            g.a((View) this, z7);
            a aVar = f5084c.f5085a;
            if (aVar != null) {
                aVar.a(z7, z8);
            }
        }
    }
}
