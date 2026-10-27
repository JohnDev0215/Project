package i1;

import M5.b;

/* JADX INFO: renamed from: i1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1401a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f15666a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f15667b;
    public int c;
    public float d;

    public final String toString() {
        String strK = android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder(), this.f15666a, ':');
        switch (this.f15667b) {
            case 900:
                StringBuilder sbI = b.i(strK);
                sbI.append(this.c);
                return sbI.toString();
            case 901:
                StringBuilder sbI2 = b.i(strK);
                sbI2.append(this.d);
                return sbI2.toString();
            case 902:
                StringBuilder sbI3 = b.i(strK);
                sbI3.append("#" + ("00000000" + Integer.toHexString(this.c)).substring(r3.length() - 8));
                return sbI3.toString();
            case 903:
                return android.hardware.bydauto.ac.tsimpl.a.h(strK, null);
            default:
                return android.hardware.bydauto.ac.tsimpl.a.h(strK, "????");
        }
    }
}
