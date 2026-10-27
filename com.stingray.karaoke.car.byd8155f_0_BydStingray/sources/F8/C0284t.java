package F8;

import Q7.InterfaceC0654g;

/* JADX INFO: renamed from: F8.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0284t extends T {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Q7.Q[] f2480b;
    public final O[] c;
    public final boolean d;

    public C0284t(Q7.Q[] qArr, O[] oArr, boolean z6) {
        A7.m.f(qArr, "parameters");
        A7.m.f(oArr, "arguments");
        this.f2480b = qArr;
        this.c = oArr;
        this.d = z6;
    }

    @Override // F8.T
    public final boolean b() {
        return this.d;
    }

    @Override // F8.T
    public final O d(AbstractC0287w abstractC0287w) {
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        Q7.Q q4 = interfaceC0654gN instanceof Q7.Q ? (Q7.Q) interfaceC0654gN : null;
        if (q4 == null) {
            return null;
        }
        int index = q4.getIndex();
        Q7.Q[] qArr = this.f2480b;
        if (index >= qArr.length || !A7.m.a(qArr[index].C(), q4.C())) {
            return null;
        }
        return this.c[index];
    }

    @Override // F8.T
    public final boolean e() {
        return this.c.length == 0;
    }
}
