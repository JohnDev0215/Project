package com.airbnb.lottie.v;

import android.graphics.PointF;
import com.airbnb.lottie.v.k0.c;

/* JADX INFO: loaded from: classes.dex */
public class y implements j0<PointF> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final y f962a = new y();

    private y() {
    }

    @Override // com.airbnb.lottie.v.j0
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public PointF a(com.airbnb.lottie.v.k0.c cVar, float f) {
        c.b bVarW = cVar.w();
        if (bVarW != c.b.BEGIN_ARRAY && bVarW != c.b.BEGIN_OBJECT) {
            if (bVarW == c.b.NUMBER) {
                PointF pointF = new PointF(((float) cVar.r()) * f, ((float) cVar.r()) * f);
                while (cVar.p()) {
                    cVar.A();
                }
                return pointF;
            }
            throw new IllegalArgumentException("Cannot convert json to point. Next token is " + bVarW);
        }
        return p.e(cVar, f);
    }
}
