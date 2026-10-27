package n0;

import E0.InterfaceC0174y;
import T.C0741g;
import h2.AbstractC1347d;

/* JADX INFO: renamed from: n0.Q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1867Q extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f19404A;
    public float B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public float f19405C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public float f19406D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public float f19407E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public float f19408F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public float f19409G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public float f19410H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public long f19411I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public InterfaceC1866P f19412J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f19413K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public long f19414L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public long f19415M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public int f19416N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public S0.p f19417O;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f19418y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public float f19419z;

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        C0.V vB = j3.b(j10);
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0741g(vB, 19, this));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SimpleGraphicsLayerModifier(scaleX=");
        sb.append(this.f19418y);
        sb.append(", scaleY=");
        sb.append(this.f19419z);
        sb.append(", alpha = ");
        sb.append(this.f19404A);
        sb.append(", translationX=");
        sb.append(this.B);
        sb.append(", translationY=");
        sb.append(this.f19405C);
        sb.append(", shadowElevation=");
        sb.append(this.f19406D);
        sb.append(", rotationX=");
        sb.append(this.f19407E);
        sb.append(", rotationY=");
        sb.append(this.f19408F);
        sb.append(", rotationZ=");
        sb.append(this.f19409G);
        sb.append(", cameraDistance=");
        sb.append(this.f19410H);
        sb.append(", transformOrigin=");
        sb.append((Object) C1870U.d(this.f19411I));
        sb.append(", shape=");
        sb.append(this.f19412J);
        sb.append(", clip=");
        sb.append(this.f19413K);
        sb.append(", renderEffect=null, ambientShadowColor=");
        AbstractC1347d.n(this.f19414L, ", spotShadowColor=", sb);
        AbstractC1347d.n(this.f19415M, ", compositingStrategy=", sb);
        sb.append((Object) ("CompositingStrategy(value=" + this.f19416N + ')'));
        sb.append(')');
        return sb.toString();
    }

    @Override // g0.n
    public final boolean w0() {
        return false;
    }
}
