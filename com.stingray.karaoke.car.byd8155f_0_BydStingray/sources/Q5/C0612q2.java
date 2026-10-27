package Q5;

import z7.InterfaceC2772a;

/* JADX INFO: renamed from: Q5.q2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0612q2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f7033b;
    public final String c;
    public final InterfaceC2772a d;

    public C0612q2(String str, String str2, String str3, InterfaceC2772a interfaceC2772a) {
        this.f7032a = str;
        this.f7033b = str2;
        this.c = str3;
        this.d = interfaceC2772a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0612q2)) {
            return false;
        }
        C0612q2 c0612q2 = (C0612q2) obj;
        return A7.m.a(this.f7032a, c0612q2.f7032a) && A7.m.a(this.f7033b, c0612q2.f7033b) && A7.m.a(this.c, c0612q2.c) && A7.m.a(this.d, c0612q2.d);
    }

    public final int hashCode() {
        String str = this.f7032a;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.f7033b;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.c;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        InterfaceC2772a interfaceC2772a = this.d;
        return iHashCode3 + (interfaceC2772a != null ? interfaceC2772a.hashCode() : 0);
    }

    public final String toString() {
        return "SongsEmptyListOptions(emptyTitleText=" + this.f7032a + ", emptyDescriptionText=" + this.f7033b + ", emptyButtonText=" + this.c + ", onEmptyButtonAction=" + this.d + ")";
    }
}
