package k1;

/* JADX INFO: loaded from: classes.dex */
public final class e extends AbstractC1598c {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f17622p;

    public e(float f10) {
        super(null);
        this.f17622p = f10;
    }

    @Override // k1.AbstractC1598c
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        float fL = l();
        float fL2 = ((e) obj).l();
        return (Float.isNaN(fL) && Float.isNaN(fL2)) || fL == fL2;
    }

    @Override // k1.AbstractC1598c
    public final int hashCode() {
        int iHashCode = super.hashCode() * 31;
        float f10 = this.f17622p;
        return iHashCode + (f10 != 0.0f ? Float.floatToIntBits(f10) : 0);
    }

    @Override // k1.AbstractC1598c
    public final float l() {
        char[] cArr;
        if (Float.isNaN(this.f17622p) && (cArr = this.l) != null && cArr.length >= 1) {
            this.f17622p = Float.parseFloat(d());
        }
        return this.f17622p;
    }

    @Override // k1.AbstractC1598c
    public final int m() {
        char[] cArr;
        if (Float.isNaN(this.f17622p) && (cArr = this.l) != null && cArr.length >= 1) {
            this.f17622p = Integer.parseInt(d());
        }
        return (int) this.f17622p;
    }
}
