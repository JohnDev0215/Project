package com.byd.data.common.http.d;

import com.byd.data.common.i.f;
import f.a0;
import f.e0;
import f.f0;
import f.g0;
import f.y;
import java.util.Iterator;

/* JADX INFO: compiled from: HeadUrlInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte f3415a;

    public a(byte b2) {
        this.f3415a = b2;
    }

    @Override // f.y
    public g0 a(y.a aVar) {
        f0 f0VarD;
        e0 e0VarA = aVar.a();
        f0 f0VarA = e0VarA.a();
        boolean z = f0VarA instanceof a0;
        f.g("HeadUrlInterceptor", "intercept bodyParam is multi part =" + z + ", type =" + ((int) this.f3415a));
        if (z) {
            a0 a0Var = (a0) f0VarA;
            a0.a aVar2 = new a0.a();
            Iterator<a0.b> it = a0Var.l().iterator();
            while (it.hasNext()) {
                aVar2.c(it.next());
            }
            byte b2 = this.f3415a;
            if (b2 == 1) {
                aVar2.c(com.byd.data.common.i.j.a.f(a0Var.m()));
            } else if (b2 == 2) {
                com.byd.data.common.i.j.a.b(aVar2);
            }
            f0VarD = aVar2.e(a0.f5000e).d();
        } else {
            f0VarD = com.byd.data.common.i.j.a.g(this.f3415a);
        }
        return aVar.d(e0VarA.g().f(f0VarD).a());
    }
}
