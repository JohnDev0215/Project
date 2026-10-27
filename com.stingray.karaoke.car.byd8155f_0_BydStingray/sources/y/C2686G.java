package y;

import android.graphics.Canvas;
import android.graphics.RecordingCanvas;
import android.graphics.RenderNode;
import android.os.Build;
import android.widget.EdgeEffect;
import k0.InterfaceC1593e;
import l3.C1764u;
import n0.AbstractC1874c;
import n0.C1873b;
import n0.InterfaceC1889r;
import p0.C2071b;
import q0.C2164c;

/* JADX INFO: renamed from: y.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2686G extends F0.T implements InterfaceC1593e {
    public final /* synthetic */ int c = 1;
    public final C2709n d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2687H f23836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f23837f;

    public C2686G(C2709n c2709n, C2687H c2687h) {
        this.d = c2709n;
        this.f23836e = c2687h;
    }

    public static boolean C(float f10, EdgeEffect edgeEffect, RecordingCanvas recordingCanvas) {
        if (f10 == 0.0f) {
            return edgeEffect.draw(recordingCanvas);
        }
        int iSave = recordingCanvas.save();
        recordingCanvas.rotate(f10);
        boolean zDraw = edgeEffect.draw(recordingCanvas);
        recordingCanvas.restoreToCount(iSave);
        return zDraw;
    }

    public static boolean D(float f10, long j3, EdgeEffect edgeEffect, Canvas canvas) {
        int iSave = canvas.save();
        canvas.rotate(f10);
        canvas.translate(m0.c.d(j3), m0.c.e(j3));
        boolean zDraw = edgeEffect.draw(canvas);
        canvas.restoreToCount(iSave);
        return zDraw;
    }

    public RenderNode E() {
        RenderNode renderNode = (RenderNode) this.f23837f;
        if (renderNode != null) {
            return renderNode;
        }
        RenderNode renderNode2 = new RenderNode("AndroidEdgeEffectOverscrollEffect");
        this.f23837f = renderNode2;
        return renderNode2;
    }

    @Override // k0.InterfaceC1593e
    public final void a(E0.H h10) {
        float f10;
        boolean zC;
        float f11;
        float fB;
        switch (this.c) {
            case 0:
                C2071b c2071b = h10.l;
                long jE = c2071b.e();
                C2709n c2709n = this.d;
                c2709n.l(jE);
                if (m0.f.e(c2071b.e())) {
                    h10.a();
                    return;
                }
                h10.a();
                c2709n.f23968n.getValue();
                Canvas canvasA = AbstractC1874c.a(c2071b.f20638m.m());
                C2687H c2687h = this.f23836e;
                boolean zF = C2687H.f(c2687h.f23841f);
                D.L l = ((p0) this.f23837f).f23986b;
                boolean zD = zF ? D(270.0f, h6.e.a(-m0.f.b(c2071b.e()), h10.y(l.b(h10.getLayoutDirection()))), c2687h.c(), canvasA) : false;
                if (C2687H.f(c2687h.d)) {
                    zD = D(0.0f, h6.e.a(0.0f, h10.y(l.d())), c2687h.e(), canvasA) || zD;
                }
                if (C2687H.f(c2687h.f23842g)) {
                    zD = D(90.0f, h6.e.a(0.0f, h10.y(l.c(h10.getLayoutDirection())) + (-((float) C7.a.W(m0.f.d(c2071b.e()))))), c2687h.d(), canvasA) || zD;
                }
                if (C2687H.f(c2687h.f23840e)) {
                    zD = D(180.0f, h6.e.a(-m0.f.d(c2071b.e()), (-m0.f.b(c2071b.e())) + h10.y(l.a())), c2687h.b(), canvasA) || zD;
                }
                if (zD) {
                    c2709n.g();
                    return;
                }
                return;
            default:
                C2071b c2071b2 = h10.l;
                long jE2 = c2071b2.e();
                C2709n c2709n2 = this.d;
                c2709n2.l(jE2);
                if (m0.f.e(c2071b2.e())) {
                    h10.a();
                    return;
                }
                c2709n2.f23968n.getValue();
                float fY = h10.y(AbstractC2680A.f23824a);
                Canvas canvasA2 = AbstractC1874c.a(c2071b2.f20638m.m());
                C2687H c2687h2 = this.f23836e;
                boolean z6 = C2687H.f(c2687h2.d) || C2687H.g(c2687h2.f23843h) || C2687H.f(c2687h2.f23840e) || C2687H.g(c2687h2.f23844i);
                boolean z10 = C2687H.f(c2687h2.f23841f) || C2687H.g(c2687h2.f23845j) || C2687H.f(c2687h2.f23842g) || C2687H.g(c2687h2.f23846k);
                if (z6 && z10) {
                    E().setPosition(0, 0, canvasA2.getWidth(), canvasA2.getHeight());
                } else if (z6) {
                    E().setPosition(0, 0, (C7.a.W(fY) * 2) + canvasA2.getWidth(), canvasA2.getHeight());
                } else {
                    if (!z10) {
                        h10.a();
                        return;
                    }
                    E().setPosition(0, 0, canvasA2.getWidth(), (C7.a.W(fY) * 2) + canvasA2.getHeight());
                }
                RecordingCanvas recordingCanvasBeginRecording = E().beginRecording();
                if (C2687H.g(c2687h2.f23845j)) {
                    EdgeEffect edgeEffectA = c2687h2.f23845j;
                    if (edgeEffectA == null) {
                        edgeEffectA = c2687h2.a();
                        c2687h2.f23845j = edgeEffectA;
                    }
                    C(90.0f, edgeEffectA, recordingCanvasBeginRecording);
                    edgeEffectA.finish();
                }
                boolean zF2 = C2687H.f(c2687h2.f23841f);
                C2710o c2710o = C2710o.f23975a;
                if (zF2) {
                    EdgeEffect edgeEffectC = c2687h2.c();
                    zC = C(270.0f, edgeEffectC, recordingCanvasBeginRecording);
                    if (C2687H.g(c2687h2.f23841f)) {
                        float fE = m0.c.e(c2709n2.f());
                        EdgeEffect edgeEffectA2 = c2687h2.f23845j;
                        if (edgeEffectA2 == null) {
                            edgeEffectA2 = c2687h2.a();
                            c2687h2.f23845j = edgeEffectA2;
                        }
                        int i7 = Build.VERSION.SDK_INT;
                        if (i7 >= 31) {
                            fB = c2710o.b(edgeEffectC);
                            f10 = fY;
                        } else {
                            f10 = fY;
                            fB = 0.0f;
                        }
                        float f12 = 1 - fE;
                        if (i7 >= 31) {
                            c2710o.c(edgeEffectA2, fB, f12);
                        } else {
                            edgeEffectA2.onPull(fB, f12);
                        }
                    } else {
                        f10 = fY;
                    }
                } else {
                    f10 = fY;
                    zC = false;
                }
                if (C2687H.g(c2687h2.f23843h)) {
                    EdgeEffect edgeEffectA3 = c2687h2.f23843h;
                    if (edgeEffectA3 == null) {
                        edgeEffectA3 = c2687h2.a();
                        c2687h2.f23843h = edgeEffectA3;
                    }
                    C(180.0f, edgeEffectA3, recordingCanvasBeginRecording);
                    edgeEffectA3.finish();
                }
                if (C2687H.f(c2687h2.d)) {
                    EdgeEffect edgeEffectE = c2687h2.e();
                    boolean z11 = C(0.0f, edgeEffectE, recordingCanvasBeginRecording) || zC;
                    if (C2687H.g(c2687h2.d)) {
                        float fD = m0.c.d(c2709n2.f());
                        EdgeEffect edgeEffectA4 = c2687h2.f23843h;
                        if (edgeEffectA4 == null) {
                            edgeEffectA4 = c2687h2.a();
                            c2687h2.f23843h = edgeEffectA4;
                        }
                        int i10 = Build.VERSION.SDK_INT;
                        float fB2 = i10 >= 31 ? c2710o.b(edgeEffectE) : 0.0f;
                        if (i10 >= 31) {
                            c2710o.c(edgeEffectA4, fB2, fD);
                        } else {
                            edgeEffectA4.onPull(fB2, fD);
                        }
                    }
                    zC = z11;
                }
                if (C2687H.g(c2687h2.f23846k)) {
                    EdgeEffect edgeEffectA5 = c2687h2.f23846k;
                    if (edgeEffectA5 == null) {
                        edgeEffectA5 = c2687h2.a();
                        c2687h2.f23846k = edgeEffectA5;
                    }
                    C(270.0f, edgeEffectA5, recordingCanvasBeginRecording);
                    edgeEffectA5.finish();
                }
                if (C2687H.f(c2687h2.f23842g)) {
                    EdgeEffect edgeEffectD = c2687h2.d();
                    boolean z12 = C(90.0f, edgeEffectD, recordingCanvasBeginRecording) || zC;
                    if (C2687H.g(c2687h2.f23842g)) {
                        float fE2 = m0.c.e(c2709n2.f());
                        EdgeEffect edgeEffectA6 = c2687h2.f23846k;
                        if (edgeEffectA6 == null) {
                            edgeEffectA6 = c2687h2.a();
                            c2687h2.f23846k = edgeEffectA6;
                        }
                        int i11 = Build.VERSION.SDK_INT;
                        float fB3 = i11 >= 31 ? c2710o.b(edgeEffectD) : 0.0f;
                        if (i11 >= 31) {
                            c2710o.c(edgeEffectA6, fB3, fE2);
                        } else {
                            edgeEffectA6.onPull(fB3, fE2);
                        }
                    }
                    zC = z12;
                }
                if (C2687H.g(c2687h2.f23844i)) {
                    EdgeEffect edgeEffectA7 = c2687h2.f23844i;
                    if (edgeEffectA7 == null) {
                        edgeEffectA7 = c2687h2.a();
                        c2687h2.f23844i = edgeEffectA7;
                    }
                    f11 = 0.0f;
                    C(0.0f, edgeEffectA7, recordingCanvasBeginRecording);
                    edgeEffectA7.finish();
                } else {
                    f11 = 0.0f;
                }
                if (C2687H.f(c2687h2.f23840e)) {
                    EdgeEffect edgeEffectB = c2687h2.b();
                    boolean z13 = C(180.0f, edgeEffectB, recordingCanvasBeginRecording) || zC;
                    if (C2687H.g(c2687h2.f23840e)) {
                        float fD2 = m0.c.d(c2709n2.f());
                        EdgeEffect edgeEffectA8 = c2687h2.f23844i;
                        if (edgeEffectA8 == null) {
                            edgeEffectA8 = c2687h2.a();
                            c2687h2.f23844i = edgeEffectA8;
                        }
                        int i12 = Build.VERSION.SDK_INT;
                        float fB4 = i12 >= 31 ? c2710o.b(edgeEffectB) : f11;
                        float f13 = 1 - fD2;
                        if (i12 >= 31) {
                            c2710o.c(edgeEffectA8, fB4, f13);
                        } else {
                            edgeEffectA8.onPull(fB4, f13);
                        }
                    }
                    zC = z13;
                }
                if (zC) {
                    c2709n2.g();
                }
                float f14 = z10 ? f11 : f10;
                if (!z6) {
                    f11 = f10;
                }
                a1.k layoutDirection = h10.getLayoutDirection();
                C1873b c1873b = new C1873b();
                c1873b.f19426a = recordingCanvasBeginRecording;
                long jE3 = c2071b2.e();
                a1.b bVarR = c2071b2.f20638m.r();
                a1.k kVarV = c2071b2.f20638m.v();
                InterfaceC1889r interfaceC1889rM = c2071b2.f20638m.m();
                long jY = c2071b2.f20638m.y();
                h3.y yVar = c2071b2.f20638m;
                C2164c c2164c = (C2164c) yVar.f15500n;
                yVar.J(h10);
                yVar.K(layoutDirection);
                yVar.I(c1873b);
                yVar.L(jE3);
                yVar.f15500n = null;
                c1873b.m();
                try {
                    ((C1764u) c2071b2.f20638m.f15499m).X(f14, f11);
                    try {
                        h10.a();
                        float f15 = -f14;
                        float f16 = -f11;
                        ((C1764u) c2071b2.f20638m.f15499m).X(f15, f16);
                        c1873b.k();
                        h3.y yVar2 = c2071b2.f20638m;
                        yVar2.J(bVarR);
                        yVar2.K(kVarV);
                        yVar2.I(interfaceC1889rM);
                        yVar2.L(jY);
                        yVar2.f15500n = c2164c;
                        E().endRecording();
                        int iSave = canvasA2.save();
                        canvasA2.translate(f15, f16);
                        canvasA2.drawRenderNode(E());
                        canvasA2.restoreToCount(iSave);
                        return;
                    } catch (Throwable th) {
                        ((C1764u) c2071b2.f20638m.f15499m).X(-f14, -f11);
                        throw th;
                    }
                } catch (Throwable th2) {
                    c1873b.k();
                    h3.y yVar3 = c2071b2.f20638m;
                    yVar3.J(bVarR);
                    yVar3.K(kVarV);
                    yVar3.I(interfaceC1889rM);
                    yVar3.L(jY);
                    yVar3.f15500n = c2164c;
                    throw th2;
                }
        }
    }

    public C2686G(C2709n c2709n, C2687H c2687h, p0 p0Var) {
        this.d = c2709n;
        this.f23836e = c2687h;
        this.f23837f = p0Var;
    }
}
