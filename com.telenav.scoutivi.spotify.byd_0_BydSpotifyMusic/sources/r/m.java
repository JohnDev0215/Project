package r;

import java.util.HashSet;
import s.b;

/* JADX INFO: compiled from: VirtualLayout.java */
/* JADX INFO: loaded from: classes.dex */
public class m extends j {
    private int W0 = 0;
    private int X0 = 0;
    private int Y0 = 0;
    private int Z0 = 0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    private int f9318a1 = 0;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    private int f9319b1 = 0;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private int f9320c1 = 0;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    private int f9321d1 = 0;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    private boolean f9322e1 = false;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    private int f9323f1 = 0;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    private int f9324g1 = 0;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    protected b.a f9325h1 = new b.a();

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    b.InterfaceC0143b f9326i1 = null;

    public int A1() {
        return this.f9321d1;
    }

    public int B1() {
        return this.W0;
    }

    public boolean C1() {
        return this.f9322e1;
    }

    protected void D1(boolean z6) {
        this.f9322e1 = z6;
    }

    @Override // r.j, r.i
    public void a(f fVar) {
        w1();
    }

    public void w1() {
        for (int i7 = 0; i7 < this.V0; i7++) {
            e eVar = this.U0[i7];
            if (eVar != null) {
                eVar.X0(true);
            }
        }
    }

    public boolean x1(HashSet<e> hashSet) {
        for (int i7 = 0; i7 < this.V0; i7++) {
            if (hashSet.contains(this.U0[i7])) {
                return true;
            }
        }
        return false;
    }

    public int y1() {
        return this.X0;
    }

    public int z1() {
        return this.f9320c1;
    }
}
