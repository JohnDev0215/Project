package S3;

import C0.InterfaceC0103j;
import C0.a0;
import H7.H;
import T.X;
import a4.AbstractC0922c;
import a4.ChoreographerFrameCallbackC0924e;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import n0.AbstractC1874c;
import n0.InterfaceC1889r;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class j extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ boolean f7932A;
    public final /* synthetic */ boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final /* synthetic */ boolean f7933C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final /* synthetic */ Context f7934D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7935E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final /* synthetic */ X f7936F;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Rect f7937m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0103j f7938n;
    public final /* synthetic */ g0.c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Matrix f7939p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ O3.j f7940q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f7941r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ boolean f7942s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f7943t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f7944u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ O3.a f7945v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Map f7946w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ boolean f7947x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ boolean f7948y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ boolean f7949z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(Rect rect, InterfaceC0103j interfaceC0103j, g0.c cVar, Matrix matrix, O3.j jVar, boolean z6, boolean z10, int i7, int i10, O3.a aVar, Map map, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, Context context, InterfaceC2772a interfaceC2772a, X x8) {
        super(1);
        this.f7937m = rect;
        this.f7938n = interfaceC0103j;
        this.o = cVar;
        this.f7939p = matrix;
        this.f7940q = jVar;
        this.f7941r = z6;
        this.f7942s = z10;
        this.f7943t = i7;
        this.f7944u = i10;
        this.f7945v = aVar;
        this.f7946w = map;
        this.f7947x = z11;
        this.f7948y = z12;
        this.f7949z = z13;
        this.f7932A = z14;
        this.B = z15;
        this.f7933C = z16;
        this.f7934D = context;
        this.f7935E = interfaceC2772a;
        this.f7936F = x8;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        p0.d dVar = (p0.d) obj;
        A7.m.f(dVar, "$this$Canvas");
        InterfaceC1889r interfaceC1889rM = dVar.F().m();
        Rect rect = this.f7937m;
        long jF = h6.j.f(rect.width(), rect.height());
        long jC = H.c(C7.a.W(m0.f.d(dVar.e())), C7.a.W(m0.f.b(dVar.e())));
        long jA = this.f7938n.a(jF, dVar.e());
        long jA2 = this.o.a(H.c((int) (a0.a(jA) * m0.f.d(jF)), (int) (a0.b(jA) * m0.f.b(jF))), jC, dVar.getLayoutDirection());
        Matrix matrix = this.f7939p;
        matrix.reset();
        matrix.preTranslate((int) (jA2 >> 32), (int) (jA2 & 4294967295L));
        matrix.preScale(a0.a(jA), a0.b(jA));
        O3.k kVar = O3.k.l;
        O3.j jVar = this.f7940q;
        HashSet hashSet = jVar.f5124t.f5132a;
        boolean zAdd = this.f7941r ? hashSet.add(kVar) : hashSet.remove(kVar);
        if (jVar.l != null && zAdd) {
            jVar.b();
        }
        jVar.o = this.f7942s;
        jVar.f5116V = this.f7943t;
        jVar.c();
        jVar.f5117W = this.f7944u;
        O3.a aVar = jVar.l;
        O3.a aVar2 = this.f7945v;
        ChoreographerFrameCallbackC0924e choreographerFrameCallbackC0924e = jVar.f5118m;
        if (aVar != aVar2) {
            jVar.f5111Q = true;
            if (choreographerFrameCallbackC0924e.f11107x) {
                choreographerFrameCallbackC0924e.cancel();
                if (!jVar.isVisible()) {
                    jVar.f5115U = 1;
                }
            }
            jVar.l = null;
            jVar.f5127w = null;
            jVar.f5121q = null;
            jVar.f5114T = -3.4028235E38f;
            choreographerFrameCallbackC0924e.f11106w = null;
            choreographerFrameCallbackC0924e.f11104u = -2.1474836E9f;
            choreographerFrameCallbackC0924e.f11105v = 2.1474836E9f;
            jVar.invalidateSelf();
            jVar.l = aVar2;
            jVar.b();
            boolean z6 = choreographerFrameCallbackC0924e.f11106w == null;
            choreographerFrameCallbackC0924e.f11106w = aVar2;
            if (z6) {
                choreographerFrameCallbackC0924e.i(Math.max(choreographerFrameCallbackC0924e.f11104u, aVar2.l), Math.min(choreographerFrameCallbackC0924e.f11105v, aVar2.f5078m));
            } else {
                choreographerFrameCallbackC0924e.i((int) aVar2.l, (int) aVar2.f5078m);
            }
            float f10 = choreographerFrameCallbackC0924e.f11102s;
            choreographerFrameCallbackC0924e.f11102s = 0.0f;
            choreographerFrameCallbackC0924e.f11101r = 0.0f;
            choreographerFrameCallbackC0924e.h((int) f10);
            choreographerFrameCallbackC0924e.f();
            jVar.l(choreographerFrameCallbackC0924e.getAnimatedFraction());
            ArrayList arrayList = jVar.f5120p;
            Iterator it = new ArrayList(arrayList).iterator();
            while (it.hasNext()) {
                O3.i iVar = (O3.i) it.next();
                if (iVar != null) {
                    iVar.run();
                }
                it.remove();
            }
            arrayList.clear();
            aVar2.f5069a.getClass();
            jVar.c();
            Drawable.Callback callback = jVar.getCallback();
            if (callback instanceof ImageView) {
                ImageView imageView = (ImageView) callback;
                imageView.setImageDrawable(null);
                imageView.setImageDrawable(jVar);
            }
        }
        Map map = jVar.f5123s;
        Map map2 = this.f7946w;
        if (map2 != map) {
            jVar.f5123s = map2;
            jVar.invalidateSelf();
        }
        if (this.f7936F.getValue() != null) {
            throw new ClassCastException();
        }
        boolean z10 = jVar.f5129y;
        boolean z11 = this.f7947x;
        if (z10 != z11) {
            jVar.f5129y = z11;
            X3.c cVar = jVar.f5127w;
            if (cVar != null) {
                cVar.l(z11);
            }
        }
        jVar.f5130z = this.f7948y;
        jVar.f5096A = this.f7949z;
        jVar.f5125u = this.f7932A;
        boolean z12 = jVar.f5126v;
        boolean z13 = this.B;
        if (z13 != z12) {
            jVar.f5126v = z13;
            X3.c cVar2 = jVar.f5127w;
            if (cVar2 != null) {
                cVar2.f10027L = z13;
            }
            jVar.invalidateSelf();
        }
        boolean z14 = jVar.B;
        boolean z15 = this.f7933C;
        if (z15 != z14) {
            jVar.B = z15;
            jVar.invalidateSelf();
        }
        U3.f fVarG = jVar.g();
        if (jVar.a(this.f7934D) || fVarG == null) {
            jVar.l(((Number) this.f7935E.a()).floatValue());
        } else {
            jVar.l(fVarG.f9015b);
        }
        jVar.setBounds(0, 0, rect.width(), rect.height());
        Canvas canvasA = AbstractC1874c.a(interfaceC1889rM);
        X3.c cVar3 = jVar.f5127w;
        O3.a aVar3 = jVar.l;
        if (cVar3 != null && aVar3 != null) {
            int i7 = jVar.f5117W;
            if (i7 == 0) {
                i7 = 1;
            }
            boolean z16 = i7 == 2;
            ThreadPoolExecutor threadPoolExecutor = O3.j.f5095Y;
            Semaphore semaphore = jVar.f5112R;
            A2.l lVar = jVar.f5113S;
            if (z16) {
                try {
                    semaphore.acquire();
                    if (jVar.m()) {
                        jVar.l(choreographerFrameCallbackC0924e.a());
                    }
                } catch (InterruptedException unused) {
                    if (z16) {
                        semaphore.release();
                        if (cVar3.f10026K != choreographerFrameCallbackC0924e.a()) {
                        }
                    }
                    return m7.q.f19267a;
                } catch (Throwable th) {
                    if (z16) {
                        semaphore.release();
                        if (cVar3.f10026K != choreographerFrameCallbackC0924e.a()) {
                            threadPoolExecutor.execute(lVar);
                        }
                    }
                    throw th;
                }
            }
            if (jVar.o) {
                try {
                    int i10 = jVar.f5128x;
                    if (jVar.f5097C) {
                        canvasA.save();
                        canvasA.concat(matrix);
                        jVar.i(canvasA, cVar3);
                        canvasA.restore();
                    } else {
                        cVar3.f(canvasA, matrix, i10, null);
                    }
                } catch (Throwable unused2) {
                    AbstractC0922c.f11094a.getClass();
                }
            } else {
                int i11 = jVar.f5128x;
                if (jVar.f5097C) {
                    canvasA.save();
                    canvasA.concat(matrix);
                    jVar.i(canvasA, cVar3);
                    canvasA.restore();
                } else {
                    cVar3.f(canvasA, matrix, i11, null);
                }
            }
            jVar.f5111Q = false;
            if (z16) {
                semaphore.release();
                if (cVar3.f10026K != choreographerFrameCallbackC0924e.a()) {
                    threadPoolExecutor.execute(lVar);
                }
            }
        }
        return m7.q.f19267a;
    }
}
