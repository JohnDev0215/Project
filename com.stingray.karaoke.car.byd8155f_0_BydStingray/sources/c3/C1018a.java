package c3;

import H7.H;
import android.text.TextUtils;
import h2.AbstractC1346c;

/* JADX INFO: renamed from: c3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1018a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f12351a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f12352b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f12353e;

    public static C1018a b(String str) {
        AbstractC1346c.c(str.startsWith("Format:"));
        String[] strArrSplit = TextUtils.split(str.substring(7), ",");
        int i7 = -1;
        int i10 = -1;
        int i11 = -1;
        int i12 = -1;
        for (int i13 = 0; i13 < strArrSplit.length; i13++) {
            String strR = H.R(strArrSplit[i13].trim());
            strR.getClass();
            switch (strR) {
                case "end":
                    i10 = i13;
                    break;
                case "text":
                    i11 = i13;
                    break;
                case "start":
                    i7 = i13;
                    break;
                case "style":
                    i12 = i13;
                    break;
            }
        }
        if (i7 == -1 || i10 == -1 || i11 == -1) {
            return null;
        }
        int length = strArrSplit.length;
        C1018a c1018a = new C1018a();
        c1018a.f12351a = i7;
        c1018a.f12352b = i10;
        c1018a.c = i12;
        c1018a.d = i11;
        c1018a.f12353e = length;
        return c1018a;
    }

    public boolean a() {
        int i7 = this.f12351a;
        int i10 = 2;
        if ((i7 & 7) != 0) {
            int i11 = this.d;
            int i12 = this.f12352b;
            if (((i11 > i12 ? 1 : i11 == i12 ? 2 : 4) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & 112) != 0) {
            int i13 = this.d;
            int i14 = this.c;
            if ((((i13 > i14 ? 1 : i13 == i14 ? 2 : 4) << 4) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & 1792) != 0) {
            int i15 = this.f12353e;
            int i16 = this.f12352b;
            if ((((i15 > i16 ? 1 : i15 == i16 ? 2 : 4) << 8) & i7) == 0) {
                return false;
            }
        }
        if ((i7 & 28672) != 0) {
            int i17 = this.f12353e;
            int i18 = this.c;
            if (i17 > i18) {
                i10 = 1;
            } else if (i17 != i18) {
                i10 = 4;
            }
            if (((i10 << 12) & i7) == 0) {
                return false;
            }
        }
        return true;
    }
}
