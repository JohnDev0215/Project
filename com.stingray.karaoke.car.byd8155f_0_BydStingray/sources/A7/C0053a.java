package A7;

import java.io.Serializable;

/* JADX INFO: renamed from: A7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0053a implements InterfaceC0060h, Serializable {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Class f448m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f449n;
    public final String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f450p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f451q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f452r;

    public C0053a(int i7, int i10, Class cls, Object obj, String str, String str2) {
        this.l = obj;
        this.f448m = cls;
        this.f449n = str;
        this.o = str2;
        this.f451q = i7;
        this.f452r = i10 >> 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0053a)) {
            return false;
        }
        C0053a c0053a = (C0053a) obj;
        return this.f450p == c0053a.f450p && this.f451q == c0053a.f451q && this.f452r == c0053a.f452r && m.a(this.l, c0053a.l) && this.f448m.equals(c0053a.f448m) && this.f449n.equals(c0053a.f449n) && this.o.equals(c0053a.o);
    }

    @Override // A7.InterfaceC0060h
    public final int getArity() {
        return this.f451q;
    }

    public final int hashCode() {
        Object obj = this.l;
        return ((((M5.b.c(this.o, M5.b.c(this.f449n, (this.f448m.hashCode() + ((obj != null ? obj.hashCode() : 0) * 31)) * 31, 31), 31) + (this.f450p ? 1231 : 1237)) * 31) + this.f451q) * 31) + this.f452r;
    }

    public final String toString() {
        return A.f443a.j(this);
    }
}
