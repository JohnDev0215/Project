package com.airbnb.lottie.r.b;

import android.graphics.Path;
import com.airbnb.lottie.r.c.a;
import com.airbnb.lottie.t.k.q;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class q implements m, a.InterfaceC0025a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f760c;
    private final com.airbnb.lottie.f d;
    private final com.airbnb.lottie.r.c.a<?, Path> e;
    private boolean f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f758a = new Path();
    private b g = new b();

    public q(com.airbnb.lottie.f fVar, com.airbnb.lottie.t.l.a aVar, com.airbnb.lottie.t.k.o oVar) {
        this.f759b = oVar.b();
        this.f760c = oVar.d();
        this.d = fVar;
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.t.k.l, Path> aVarA = oVar.c().a();
        this.e = aVarA;
        aVar.h(aVarA);
        aVarA.a(this);
    }

    private void d() {
        this.f = false;
        this.d.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0025a
    public void b() {
        d();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.i() == q.a.SIMULTANEOUSLY) {
                    this.g.a(sVar);
                    sVar.d(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path getPath() {
        if (this.f) {
            return this.f758a;
        }
        this.f758a.reset();
        if (!this.f760c) {
            this.f758a.set(this.e.h());
            this.f758a.setFillType(Path.FillType.EVEN_ODD);
            this.g.b(this.f758a);
        }
        this.f = true;
        return this.f758a;
    }
}
