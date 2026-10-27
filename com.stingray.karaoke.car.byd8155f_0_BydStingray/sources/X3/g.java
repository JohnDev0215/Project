package X3;

import N0.q;
import N1.C0420a;
import W3.m;
import a4.C0920a;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class g extends b {

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final Q3.d f10058D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final c f10059E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final R3.g f10060F;

    public g(O3.j jVar, e eVar, c cVar, O3.a aVar) {
        super(jVar, eVar);
        this.f10059E = cVar;
        Q3.d dVar = new Q3.d(jVar, this, new m("__container", eVar.f10037a, false), aVar);
        this.f10058D = dVar;
        dVar.c(Collections.emptyList(), Collections.emptyList());
        q qVar = this.f10008p.f10056x;
        if (qVar != null) {
            this.f10060F = new R3.g(this, this, qVar);
        }
    }

    @Override // X3.b, Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        super.a(rectF, matrix, z6);
        this.f10058D.a(rectF, this.f10007n, z6);
    }

    @Override // X3.b
    public final void h(Canvas canvas, Matrix matrix, int i7, C0920a c0920a) {
        R3.g gVar = this.f10060F;
        if (gVar != null) {
            c0920a = gVar.a(matrix, i7);
        }
        this.f10058D.f(canvas, matrix, i7, c0920a);
    }

    @Override // X3.b
    public final C0420a i() {
        C0420a c0420a = this.f10008p.f10055w;
        return c0420a != null ? c0420a : this.f10059E.f10008p.f10055w;
    }
}
