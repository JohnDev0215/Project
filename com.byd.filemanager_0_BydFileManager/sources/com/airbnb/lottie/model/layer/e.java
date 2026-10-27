package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import com.airbnb.lottie.model.content.j;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: ShapeLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends a {
    private final com.airbnb.lottie.r.b.d x;

    e(com.airbnb.lottie.f fVar, Layer layer) {
        super(fVar, layer);
        com.airbnb.lottie.r.b.d dVar = new com.airbnb.lottie.r.b.d(fVar, this, new j("__container", layer.l(), false));
        this.x = dVar;
        dVar.c(Collections.emptyList(), Collections.emptyList());
    }

    @Override // com.airbnb.lottie.model.layer.a
    protected void F(com.airbnb.lottie.model.d dVar, int i, List<com.airbnb.lottie.model.d> list, com.airbnb.lottie.model.d dVar2) {
        this.x.e(dVar, i, list, dVar2);
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        this.x.a(rectF, this.m, z);
    }

    @Override // com.airbnb.lottie.model.layer.a
    void v(Canvas canvas, Matrix matrix, int i) {
        this.x.g(canvas, matrix, i);
    }
}
