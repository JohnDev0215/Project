package A7;

import H7.InterfaceC0317b;
import y7.C2749a;

/* JADX INFO: loaded from: classes.dex */
public abstract class u extends AbstractC0056d implements H7.w {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f464r;

    public u(Object obj, Class cls, String str, String str2, int i7) {
        super(obj, cls, str, str2, (i7 & 1) == 1);
        this.f464r = (i7 & 2) == 2;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof u) {
            u uVar = (u) obj;
            return h().equals(uVar.h()) && this.o.equals(uVar.o) && this.f457p.equals(uVar.f457p) && m.a(this.f455m, uVar.f455m);
        }
        if (obj instanceof H7.w) {
            return obj.equals(j());
        }
        return false;
    }

    public final int hashCode() {
        return this.f457p.hashCode() + M5.b.c(this.o, h().hashCode() * 31, 31);
    }

    public final InterfaceC0317b j() {
        if (this.f464r) {
            return this;
        }
        InterfaceC0317b interfaceC0317bF = this.l;
        if (interfaceC0317bF == null) {
            interfaceC0317bF = f();
            this.l = interfaceC0317bF;
        }
        return interfaceC0317bF;
    }

    public final H7.w l() {
        if (this.f464r) {
            throw new UnsupportedOperationException("Kotlin reflection is not yet supported for synthetic Java properties. Please follow/upvote https://youtrack.jetbrains.com/issue/KT-55980");
        }
        InterfaceC0317b interfaceC0317bJ = j();
        if (interfaceC0317bJ != this) {
            return (H7.w) interfaceC0317bJ;
        }
        throw new C2749a("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
    }

    public final String toString() {
        InterfaceC0317b interfaceC0317bJ = j();
        return interfaceC0317bJ != this ? interfaceC0317bJ.toString() : android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder("property "), this.o, " (Kotlin reflection is not available)");
    }
}
