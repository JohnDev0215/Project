package coil.compose;

import A3.c;
import A3.e;
import A3.j;
import C0.C0102i;
import C0.InterfaceC0103j;
import E0.H;
import K3.h;
import K3.i;
import S8.AbstractC0727y;
import S8.E;
import S8.l0;
import T.C0730a0;
import T.C0735d;
import T.C0738e0;
import T.InterfaceC0769u0;
import T.S;
import V8.AbstractC0828q;
import V8.f0;
import X8.m;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Trace;
import com.google.accompanist.drawablepainter.DrawablePainter;
import h6.q;
import kotlin.Metadata;
import m0.f;
import n0.C1877f;
import n0.C1883l;
import n7.AbstractC1985B;
import p0.C2071b;
import r0.AbstractC2268a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0003¨\u0006\u0004"}, d2 = {"Lcoil/compose/AsyncImagePainter;", "Lr0/a;", "LT/u0;", "A3/f", "coil-compose-base_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AsyncImagePainter extends AbstractC2268a implements InterfaceC0769u0 {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final c f12629F = new c(1);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f12630A;
    public boolean B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0738e0 f12631C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final C0738e0 f12632D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C0738e0 f12633E;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public X8.c f12634q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final f0 f12635r = AbstractC0828q.c(new f(0));

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0738e0 f12636s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0730a0 f12637t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f12638u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public A3.f f12639v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public AbstractC2268a f12640w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public InterfaceC2782k f12641x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC2782k f12642y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC0103j f12643z;

    public AsyncImagePainter(i iVar, z3.i iVar2) {
        S s6 = S.f8224q;
        this.f12636s = C0735d.O(null, s6);
        this.f12637t = C0735d.L(1.0f);
        this.f12638u = C0735d.O(null, s6);
        e eVar = e.f386a;
        this.f12639v = eVar;
        this.f12641x = f12629F;
        this.f12643z = C0102i.f903b;
        this.f12630A = 1;
        this.f12631C = C0735d.O(eVar, s6);
        this.f12632D = C0735d.O(iVar, s6);
        this.f12633E = C0735d.O(iVar2, s6);
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
        Trace.beginSection("AsyncImagePainter.onRemembered");
        try {
            if (this.f12634q == null) {
                l0 l0VarC = AbstractC0727y.c();
                Z8.e eVar = E.f8084a;
                X8.c cVarA = AbstractC0727y.a(q.x(l0VarC, m.f10573a.f8932q));
                this.f12634q = cVarA;
                Object obj = this.f12640w;
                InterfaceC0769u0 interfaceC0769u0 = obj instanceof InterfaceC0769u0 ? (InterfaceC0769u0) obj : null;
                if (interfaceC0769u0 != null) {
                    interfaceC0769u0.a();
                }
                if (this.B) {
                    h hVarA = i.a((i) this.f12632D.getValue());
                    hVarA.f3530b = ((z3.i) this.f12633E.getValue()).f24399b;
                    hVarA.f3541q = null;
                    Drawable drawable = hVarA.a().f3563z.f3520j;
                    K3.c cVar = N3.c.f4574a;
                    k(new AsyncImagePainter$State$Loading(drawable != null ? j(drawable) : null));
                } else {
                    AbstractC0727y.w(cVarA, null, null, new j(this, null), 3);
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f12637t.j(f10);
        return true;
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
        X8.c cVar = this.f12634q;
        if (cVar != null) {
            AbstractC0727y.d(cVar, null);
        }
        this.f12634q = null;
        Object obj = this.f12640w;
        InterfaceC0769u0 interfaceC0769u0 = obj instanceof InterfaceC0769u0 ? (InterfaceC0769u0) obj : null;
        if (interfaceC0769u0 != null) {
            interfaceC0769u0.c();
        }
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        X8.c cVar = this.f12634q;
        if (cVar != null) {
            AbstractC0727y.d(cVar, null);
        }
        this.f12634q = null;
        Object obj = this.f12640w;
        InterfaceC0769u0 interfaceC0769u0 = obj instanceof InterfaceC0769u0 ? (InterfaceC0769u0) obj : null;
        if (interfaceC0769u0 != null) {
            interfaceC0769u0.d();
        }
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f12638u.setValue(c1883l);
        return true;
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        AbstractC2268a abstractC2268a = (AbstractC2268a) this.f12636s.getValue();
        if (abstractC2268a != null) {
            return abstractC2268a.h();
        }
        return 9205357640488583168L;
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) {
        C2071b c2071b = h10.l;
        f fVar = new f(c2071b.e());
        f0 f0Var = this.f12635r;
        f0Var.getClass();
        f0Var.m(null, fVar);
        AbstractC2268a abstractC2268a = (AbstractC2268a) this.f12636s.getValue();
        if (abstractC2268a != null) {
            abstractC2268a.g(h10, c2071b.e(), this.f12637t.i(), (C1883l) this.f12638u.getValue());
        }
    }

    public final AbstractC2268a j(Drawable drawable) {
        return drawable instanceof BitmapDrawable ? AbstractC1985B.a(new C1877f(((BitmapDrawable) drawable).getBitmap()), this.f12630A) : new DrawablePainter(drawable.mutate());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k(A3.f r14) {
        /*
            r13 = this;
            A3.f r0 = r13.f12639v
            z7.k r1 = r13.f12641x
            java.lang.Object r14 = r1.b(r14)
            A3.f r14 = (A3.f) r14
            r13.f12639v = r14
            T.e0 r1 = r13.f12631C
            r1.setValue(r14)
            boolean r1 = r14 instanceof coil.compose.AsyncImagePainter$State$Success
            r2 = 0
            if (r1 == 0) goto L1c
            r1 = r14
            coil.compose.AsyncImagePainter$State$Success r1 = (coil.compose.AsyncImagePainter$State$Success) r1
            K3.p r1 = r1.f12645a
            goto L25
        L1c:
            boolean r1 = r14 instanceof coil.compose.AsyncImagePainter$State$Error
            if (r1 == 0) goto L63
            r1 = r14
            coil.compose.AsyncImagePainter$State$Error r1 = (coil.compose.AsyncImagePainter$State$Error) r1
            K3.e r1 = r1.f12644a
        L25:
            K3.i r3 = r1.b()
            M3.e r3 = r3.f3546g
            A3.o r4 = A3.p.f397a
            M3.f r3 = r3.a(r4, r1)
            boolean r4 = r3 instanceof M3.b
            if (r4 == 0) goto L63
            r0.a r4 = r0.getPainter()
            boolean r5 = r0 instanceof coil.compose.AsyncImagePainter$State$Loading
            if (r5 == 0) goto L3f
            r7 = r4
            goto L40
        L3f:
            r7 = r2
        L40:
            r0.a r8 = r14.getPainter()
            C0.j r9 = r13.f12643z
            M3.b r3 = (M3.b) r3
            boolean r4 = r1 instanceof K3.p
            if (r4 == 0) goto L56
            K3.p r1 = (K3.p) r1
            boolean r1 = r1.f3582g
            if (r1 != 0) goto L53
            goto L56
        L53:
            r1 = 0
        L54:
            r11 = r1
            goto L58
        L56:
            r1 = 1
            goto L54
        L58:
            coil.compose.CrossfadePainter r1 = new coil.compose.CrossfadePainter
            boolean r12 = r3.d
            int r10 = r3.c
            r6 = r1
            r6.<init>(r7, r8, r9, r10, r11, r12)
            goto L64
        L63:
            r1 = r2
        L64:
            if (r1 == 0) goto L67
            goto L6b
        L67:
            r0.a r1 = r14.getPainter()
        L6b:
            r13.f12640w = r1
            T.e0 r3 = r13.f12636s
            r3.setValue(r1)
            X8.c r1 = r13.f12634q
            if (r1 == 0) goto La1
            r0.a r1 = r0.getPainter()
            r0.a r3 = r14.getPainter()
            if (r1 == r3) goto La1
            r0.a r0 = r0.getPainter()
            boolean r1 = r0 instanceof T.InterfaceC0769u0
            if (r1 == 0) goto L8b
            T.u0 r0 = (T.InterfaceC0769u0) r0
            goto L8c
        L8b:
            r0 = r2
        L8c:
            if (r0 == 0) goto L91
            r0.d()
        L91:
            r0.a r0 = r14.getPainter()
            boolean r1 = r0 instanceof T.InterfaceC0769u0
            if (r1 == 0) goto L9c
            r2 = r0
            T.u0 r2 = (T.InterfaceC0769u0) r2
        L9c:
            if (r2 == 0) goto La1
            r2.a()
        La1:
            z7.k r13 = r13.f12642y
            if (r13 == 0) goto La8
            r13.b(r14)
        La8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.compose.AsyncImagePainter.k(A3.f):void");
    }
}
