package com.airbnb.lottie.t.l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.airbnb.lottie.t.k.n;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class f extends a {
    private final com.airbnb.lottie.r.b.d w;

    f(com.airbnb.lottie.f fVar, d dVar) {
        super(fVar, dVar);
        com.airbnb.lottie.r.b.d dVar2 = new com.airbnb.lottie.r.b.d(fVar, this, new n("__container", dVar.l(), false));
        this.w = dVar2;
        dVar2.c(Collections.emptyList(), Collections.emptyList());
    }

    @Override // com.airbnb.lottie.t.l.a
    protected void B(com.airbnb.lottie.t.e eVar, int i, List<com.airbnb.lottie.t.e> list, com.airbnb.lottie.t.e eVar2) {
        this.w.e(eVar, i, list, eVar2);
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        this.w.a(rectF, this.m, z);
    }

    @Override // com.airbnb.lottie.t.l.a
    void r(@NonNull Canvas canvas, Matrix matrix, int i) {
        this.w.f(canvas, matrix, i);
    }
}
