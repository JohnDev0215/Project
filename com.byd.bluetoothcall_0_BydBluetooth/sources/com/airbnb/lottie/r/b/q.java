package com.airbnb.lottie.r.b;

import android.graphics.Path;
import com.airbnb.lottie.model.content.ShapeTrimPath;
import com.airbnb.lottie.r.c.a;
import java.util.List;

/* JADX INFO: compiled from: ShapeContent.java */
/* JADX INFO: loaded from: classes.dex */
public class q implements m, a.InterfaceC0077a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f3103c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.airbnb.lottie.f f3104d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final com.airbnb.lottie.r.c.a<?, Path> f3105e;
    private boolean f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Path f3101a = new Path();
    private b g = new b();

    public q(com.airbnb.lottie.f fVar, com.airbnb.lottie.model.layer.a aVar, com.airbnb.lottie.model.content.k kVar) {
        this.f3102b = kVar.b();
        this.f3103c = kVar.d();
        this.f3104d = fVar;
        com.airbnb.lottie.r.c.a<com.airbnb.lottie.model.content.h, Path> aVarA = kVar.c().a();
        this.f3105e = aVarA;
        aVar.j(aVarA);
        aVarA.a(this);
    }

    private void d() {
        this.f = false;
        this.f3104d.invalidateSelf();
    }

    @Override // com.airbnb.lottie.r.c.a.InterfaceC0077a
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
            return this.f3101a;
        }
        this.f3101a.reset();
        if (this.f3103c) {
            this.f = true;
            return this.f3101a;
        }
        this.f3101a.set(this.f3105e.h());
        this.f3101a.setFillType(Path.FillType.EVEN_ODD);
        this.g.b(this.f3101a);
        this.f = true;
        return this.f3101a;
    }
}
