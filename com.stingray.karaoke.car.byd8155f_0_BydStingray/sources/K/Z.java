package K;

import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class Z {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Z f3206g = new Z(63, null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC2782k f3207a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC2782k f3208b;
    public final InterfaceC2782k c;
    public final InterfaceC2782k d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InterfaceC2782k f3209e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC2782k f3210f;

    public Z(int i7, InterfaceC2782k interfaceC2782k) {
        interfaceC2782k = (i7 & 16) != 0 ? null : interfaceC2782k;
        this.f3207a = null;
        this.f3208b = null;
        this.c = null;
        this.d = null;
        this.f3209e = interfaceC2782k;
        this.f3210f = null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Z)) {
            return false;
        }
        Z z6 = (Z) obj;
        return this.f3207a == z6.f3207a && this.f3208b == z6.f3208b && this.c == z6.c && this.d == z6.d && this.f3209e == z6.f3209e && this.f3210f == z6.f3210f;
    }

    public final int hashCode() {
        InterfaceC2782k interfaceC2782k = this.f3207a;
        int iHashCode = (interfaceC2782k != null ? interfaceC2782k.hashCode() : 0) * 31;
        InterfaceC2782k interfaceC2782k2 = this.f3208b;
        int iHashCode2 = (iHashCode + (interfaceC2782k2 != null ? interfaceC2782k2.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k3 = this.c;
        int iHashCode3 = (iHashCode2 + (interfaceC2782k3 != null ? interfaceC2782k3.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k4 = this.d;
        int iHashCode4 = (iHashCode3 + (interfaceC2782k4 != null ? interfaceC2782k4.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k5 = this.f3209e;
        int iHashCode5 = (iHashCode4 + (interfaceC2782k5 != null ? interfaceC2782k5.hashCode() : 0)) * 31;
        InterfaceC2782k interfaceC2782k6 = this.f3210f;
        return iHashCode5 + (interfaceC2782k6 != null ? interfaceC2782k6.hashCode() : 0);
    }
}
