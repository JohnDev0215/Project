package io.sentry.android.replay;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class r extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f16422m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ s f16423n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(s sVar, int i7) {
        super(0);
        this.f16422m = i7;
        this.f16423n = sVar;
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, m7.f] */
    @Override // z7.InterfaceC2772a
    public final Object a() {
        switch (this.f16422m) {
            case 0:
                Matrix matrix = new Matrix();
                u uVar = this.f16423n.l;
                matrix.preScale(uVar.c, uVar.d);
                return matrix;
            default:
                return new Canvas((Bitmap) this.f16423n.f16429s.getValue());
        }
    }
}
