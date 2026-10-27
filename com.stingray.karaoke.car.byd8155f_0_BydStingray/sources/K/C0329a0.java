package K;

import x.AbstractC2641j;

/* JADX INFO: renamed from: K.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0329a0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0329a0 f3213g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3214a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Boolean f3215b;
    public final int c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Boolean f3216e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final U0.b f3217f;

    static {
        int i7 = 0;
        f3213g = new C0329a0(null, i7, i7, 127);
    }

    public C0329a0(int i7, Boolean bool, int i10, int i11, Boolean bool2, U0.b bVar) {
        this.f3214a = i7;
        this.f3215b = bool;
        this.c = i10;
        this.d = i11;
        this.f3216e = bool2;
        this.f3217f = bVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0329a0)) {
            return false;
        }
        C0329a0 c0329a0 = (C0329a0) obj;
        return T0.n.a(this.f3214a, c0329a0.f3214a) && A7.m.a(this.f3215b, c0329a0.f3215b) && T0.o.a(this.c, c0329a0.c) && T0.l.a(this.d, c0329a0.d) && A7.m.a(null, null) && A7.m.a(this.f3216e, c0329a0.f3216e) && A7.m.a(this.f3217f, c0329a0.f3217f);
    }

    public final int hashCode() {
        int iHashCode = Integer.hashCode(this.f3214a) * 31;
        Boolean bool = this.f3215b;
        int iB = AbstractC2641j.b(this.d, AbstractC2641j.b(this.c, (iHashCode + (bool != null ? bool.hashCode() : 0)) * 31, 31), 961);
        Boolean bool2 = this.f3216e;
        int iHashCode2 = (iB + (bool2 != null ? bool2.hashCode() : 0)) * 31;
        U0.b bVar = this.f3217f;
        return iHashCode2 + (bVar != null ? bVar.l.hashCode() : 0);
    }

    public final String toString() {
        return "KeyboardOptions(capitalization=" + ((Object) T0.n.b(this.f3214a)) + ", autoCorrectEnabled=" + this.f3215b + ", keyboardType=" + ((Object) T0.o.b(this.c)) + ", imeAction=" + ((Object) T0.l.b(this.d)) + ", platformImeOptions=nullshowKeyboardOnFocus=" + this.f3216e + ", hintLocales=" + this.f3217f + ')';
    }

    public /* synthetic */ C0329a0(Boolean bool, int i7, int i10, int i11) {
        this(-1, (i11 & 2) != 0 ? null : bool, (i11 & 4) != 0 ? 0 : i7, (i11 & 8) != 0 ? -1 : i10, null, null);
    }
}
