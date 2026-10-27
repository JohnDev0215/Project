package q0;

import android.graphics.Matrix;
import android.graphics.RenderNode;
import h6.j;
import l3.C1764u;
import n0.C1891t;
import p0.C2071b;

/* JADX INFO: renamed from: q0.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2165d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1764u f21139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2071b f21140b;
    public final RenderNode c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Matrix f21141e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f21142f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f21143g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f21144h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f21145i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f21146j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f21147k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f21148m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public long f21149n;
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f21150p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f21151q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f21152r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f21153s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f21154t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f21155u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f21156v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f21157w;

    public C2165d() {
        C1764u c1764u = new C1764u(6);
        C2071b c2071b = new C2071b();
        this.f21139a = c1764u;
        this.f21140b = c2071b;
        RenderNode renderNode = new RenderNode("graphicsLayer");
        this.c = renderNode;
        this.d = 0L;
        renderNode.setClipToBounds(false);
        b(renderNode, 0);
        this.f21143g = 1.0f;
        this.f21144h = 3;
        this.f21145i = 1.0f;
        this.f21146j = 1.0f;
        long j3 = C1891t.f19445b;
        this.f21149n = j3;
        this.o = j3;
        this.f21153s = 8.0f;
        this.f21157w = 0;
    }

    public static void b(RenderNode renderNode, int i7) {
        if (j.j(i7, 1)) {
            renderNode.setUseCompositingLayer(true, null);
            renderNode.setHasOverlappingRendering(true);
        } else if (j.j(i7, 2)) {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(false);
        } else {
            renderNode.setUseCompositingLayer(false, null);
            renderNode.setHasOverlappingRendering(true);
        }
    }

    public final void a() {
        boolean z6 = this.f21154t;
        boolean z10 = false;
        boolean z11 = z6 && !this.f21142f;
        if (z6 && this.f21142f) {
            z10 = true;
        }
        boolean z12 = this.f21155u;
        RenderNode renderNode = this.c;
        if (z11 != z12) {
            this.f21155u = z11;
            renderNode.setClipToBounds(z11);
        }
        if (z10 != this.f21156v) {
            this.f21156v = z10;
            renderNode.setClipToOutline(z10);
        }
    }

    public final void c(boolean z6) {
        this.f21154t = z6;
        a();
    }
}
