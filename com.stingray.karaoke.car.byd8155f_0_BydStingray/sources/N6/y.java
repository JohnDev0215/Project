package n6;

import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public final class y extends AbstractC1961B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f20148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f20149b;
    public final Integer c;
    public final Integer d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f20150e;

    public y(long j3, long j10, Integer num, Integer num2) {
        this.f20148a = j3;
        this.f20149b = j10;
        this.c = num;
        this.d = num2;
        this.f20150e = j10 - j3;
    }

    @Override // n6.AbstractC1961B
    public final long a() {
        return this.f20149b;
    }

    @Override // n6.AbstractC1961B
    public final G7.a b(Long l) {
        Integer num = this.d;
        Integer num2 = this.c;
        if (num2 == null) {
            float fIntValue = num != null ? num.intValue() : 0.0f;
            float f10 = 6;
            return new G7.a(fIntValue - f10, fIntValue + f10);
        }
        if (num == null) {
            float fIntValue2 = num2.intValue();
            float f11 = 6;
            return new G7.a(fIntValue2 - f11, fIntValue2 + f11);
        }
        float fA = AbstractC1347d.a(num.intValue(), num2.intValue(), Math.min((l.longValue() - this.f20148a) / (this.f20150e / 2.0f), 1.0f), num2.intValue());
        float f12 = 6;
        return new G7.a(fA - f12, fA + f12);
    }

    @Override // n6.AbstractC1961B
    public final long c() {
        return this.f20148a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        return this.f20148a == yVar.f20148a && this.f20149b == yVar.f20149b && A7.m.a(this.c, yVar.c) && A7.m.a(this.d, yVar.d);
    }

    public final int hashCode() {
        int iC = AbstractC1347d.c(Long.hashCode(this.f20148a) * 31, 31, this.f20149b);
        Integer num = this.c;
        int iHashCode = (iC + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.d;
        return iHashCode + (num2 != null ? num2.hashCode() : 0);
    }

    public final String toString() {
        return "MidiGap(startTick=" + this.f20148a + ", endTick=" + this.f20149b + ", previousNotePitch=" + this.c + ", nextNotePitch=" + this.d + ")";
    }
}
