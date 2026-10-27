package com.airbnb.lottie.r.b;

import android.graphics.Path;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.List;

/* JADX INFO: compiled from: ShapeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements m, a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2716b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2717c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.f f2718d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, Path> f2719e;
    private boolean f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f2715a = new Path();
    private b g = new b();

    public q(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.k kVar) {
        this.f2716b = kVar.b();
        this.f2717c = kVar.d();
        this.f2718d = fVar;
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.h, Path> aVarA = kVar.c().a();
        this.f2719e = aVarA;
        aVar.k(aVarA);
        aVarA.a(this);
    }

    private void d() {
        this.f = false;
        this.f2718d.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.b
    public void b() {
        d();
    }

    @Override // com.airbnb.lottie.r.b.c
    public void c(List<c> list, List<c> list2) {
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            if (cVar instanceof s) {
                s sVar = (s) cVar;
                if (sVar.k() == ShapeTrimPath.Type.SIMULTANEOUSLY) {
                    this.g.a(sVar);
                    sVar.d(this);
                }
            }
        }
    }

    @Override // com.airbnb.lottie.r.b.m
    public Path h() {
        if (this.f) {
            return this.f2715a;
        }
        this.f2715a.reset();
        if (this.f2717c) {
            this.f = true;
            return this.f2715a;
        }
        this.f2715a.set(this.f2719e.h());
        this.f2715a.setFillType(Path.FillType.EVEN_ODD);
        this.g.b(this.f2715a);
        this.f = true;
        return this.f2715a;
    }
}
