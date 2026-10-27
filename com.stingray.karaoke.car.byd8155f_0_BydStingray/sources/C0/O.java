package C0;

/* JADX INFO: loaded from: classes.dex */
public final class O implements i0, InterfaceC0103j {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final O f865m = new O(0);
    public final /* synthetic */ int l;

    public /* synthetic */ O(int i7) {
        this.l = i7;
    }

    @Override // C0.InterfaceC0103j
    public long a(long j3, long j10) {
        switch (this.l) {
            case 1:
                float fMax = Math.max(m0.f.d(j10) / m0.f.d(j3), m0.f.b(j10) / m0.f.b(j3));
                return c0.b(fMax, fMax);
            case 2:
                float fB = m0.f.b(j10) / m0.f.b(j3);
                return c0.b(fB, fB);
            case 3:
                float fMin = Math.min(m0.f.d(j10) / m0.f.d(j3), m0.f.b(j10) / m0.f.b(j3));
                return c0.b(fMin, fMin);
            default:
                if (m0.f.d(j3) <= m0.f.d(j10) && m0.f.b(j3) <= m0.f.b(j10)) {
                    return c0.b(1.0f, 1.0f);
                }
                float fMin2 = Math.min(m0.f.d(j10) / m0.f.d(j3), m0.f.b(j10) / m0.f.b(j3));
                return c0.b(fMin2, fMin2);
        }
    }

    @Override // C0.i0
    public boolean e(Object obj, Object obj2) {
        return false;
    }

    @Override // C0.i0
    public void i(h0 h0Var) {
        h0Var.clear();
    }

    public String toString() {
        switch (this.l) {
            case 5:
                return "ReusedSlotId";
            default:
                return super.toString();
        }
    }
}
