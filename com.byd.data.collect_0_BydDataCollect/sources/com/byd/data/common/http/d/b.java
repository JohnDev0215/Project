package com.byd.data.common.http.d;

import com.byd.data.common.i.f;
import f.a0;
import f.e0;
import f.f0;
import f.g0;
import f.u;
import f.y;

/* JADX INFO: compiled from: HttpParamsInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f3416a = "HttpParamsInterceptor";

    @Override // f.y
    public g0 a(y.a aVar) {
        e0 e0VarA = aVar.a();
        f.g("HttpParamsInterceptor", "begin request-----threadId =" + Thread.currentThread().getId());
        if (e0VarA.f().equalsIgnoreCase("GET") || e0VarA.f().equalsIgnoreCase("HEAD")) {
            e0VarA = e0VarA.g().j(com.byd.data.common.i.j.a.d(e0VarA).c()).a();
        } else {
            f0 f0VarA = e0VarA.a();
            if (f0VarA instanceof u) {
                u.a aVar2 = new u.a();
                u uVar = (u) f0VarA;
                for (int i2 = 0; i2 < uVar.m(); i2++) {
                    aVar2.a(uVar.k(i2), uVar.l(i2));
                }
                if (e0VarA.f().equalsIgnoreCase("POST")) {
                    e0VarA = e0VarA.g().f(uVar).a();
                } else if (e0VarA.f().equalsIgnoreCase("PATCH")) {
                    e0VarA = e0VarA.g().e(uVar).a();
                } else if (e0VarA.f().equalsIgnoreCase("PUT")) {
                    e0VarA = e0VarA.g().g(uVar).a();
                }
            } else if (f0VarA instanceof a0) {
                a0 a0Var = (a0) f0VarA;
                f.g("HttpParamsInterceptor", "MultipartBody type =" + a0Var.n() + ", partSize =" + a0Var.l().size());
            }
        }
        g0 g0VarD = aVar.d(e0VarA);
        f.g("HttpParamsInterceptor", "request is end");
        return g0VarD;
    }
}
