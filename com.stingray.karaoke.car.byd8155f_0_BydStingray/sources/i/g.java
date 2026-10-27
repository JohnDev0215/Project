package i;

import U8.l;
import h.C1330b;
import h.p;
import java.lang.reflect.InvocationTargetException;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class g extends p {
    public X8.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public InterfaceC2785n f15645e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public t4.e f15646f;

    @Override // h.p
    public final void a() throws IllegalAccessException, InvocationTargetException {
        t4.e eVar = this.f15646f;
        if (eVar != null) {
            eVar.e();
        }
        t4.e eVar2 = this.f15646f;
        if (eVar2 == null) {
            return;
        }
        eVar2.f22462m = false;
    }

    @Override // h.p
    public final void b() throws IllegalAccessException, InvocationTargetException {
        t4.e eVar = this.f15646f;
        if (eVar != null && !eVar.f22462m) {
            eVar.e();
            this.f15646f = null;
        }
        if (this.f15646f == null) {
            this.f15646f = new t4.e(this.d, false, this.f15645e, this);
        }
        t4.e eVar2 = this.f15646f;
        if (eVar2 != null) {
            C7.a.A((l) eVar2.f22463n);
        }
        t4.e eVar3 = this.f15646f;
        if (eVar3 == null) {
            return;
        }
        eVar3.f22462m = false;
    }

    @Override // h.p
    public final void c(C1330b c1330b) {
        super.c(c1330b);
        t4.e eVar = this.f15646f;
        if (eVar != null) {
            ((l) eVar.f22463n).p(c1330b);
        }
    }

    @Override // h.p
    public final void d(C1330b c1330b) throws IllegalAccessException, InvocationTargetException {
        super.d(c1330b);
        t4.e eVar = this.f15646f;
        if (eVar != null) {
            eVar.e();
        }
        if (this.f15131a) {
            this.f15646f = new t4.e(this.d, true, this.f15645e, this);
        }
    }
}
