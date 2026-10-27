package A7;

import H7.InterfaceC0317b;
import H7.InterfaceC0321f;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends AbstractC0056d implements InterfaceC0060h, InterfaceC0321f {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f461r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f462s;

    public i(int i7) {
        this(i7, 0, null, C0055c.l, null, null);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            return getName().equals(iVar.getName()) && i().equals(iVar.i()) && this.f462s == iVar.f462s && this.f461r == iVar.f461r && m.a(this.f455m, iVar.f455m) && m.a(h(), iVar.h());
        }
        if (!(obj instanceof InterfaceC0321f)) {
            return false;
        }
        InterfaceC0317b interfaceC0317bF = this.l;
        if (interfaceC0317bF == null) {
            interfaceC0317bF = f();
            this.l = interfaceC0317bF;
        }
        return obj.equals(interfaceC0317bF);
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0317b f() {
        return A.f443a.a(this);
    }

    @Override // A7.InterfaceC0060h
    public final int getArity() {
        return this.f461r;
    }

    public final int hashCode() {
        return i().hashCode() + ((getName().hashCode() + (h() == null ? 0 : h().hashCode() * 31)) * 31);
    }

    public final String toString() {
        InterfaceC0317b interfaceC0317bF = this.l;
        if (interfaceC0317bF == null) {
            interfaceC0317bF = f();
            this.l = interfaceC0317bF;
        }
        if (interfaceC0317bF != this) {
            return interfaceC0317bF.toString();
        }
        if ("<init>".equals(getName())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + getName() + " (Kotlin reflection is not available)";
    }

    public i(int i7, Object obj) {
        this(i7, 0, null, obj, null, null);
    }

    public i(int i7, int i10, Class cls, Object obj, String str, String str2) {
        super(obj, cls, str, str2, (i10 & 1) == 1);
        this.f461r = i7;
        this.f462s = 0;
    }
}
