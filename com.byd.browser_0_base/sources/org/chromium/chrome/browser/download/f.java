package org.chromium.chrome.browser.download;

import android.text.TextUtils;
import android.util.Log;
import defpackage.n3;
import defpackage.ny;
import java.util.UUID;
import org.chromium.chrome.browser.profiles.OTRProfileID;

/* JADX INFO: compiled from: DownloadSharedPreferenceEntry.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static final f h = new f(new ny(), -1, null, false, "", false, false);
    public final int a;
    public final OTRProfileID b;
    public final boolean c;
    public final String d;
    public final boolean e;
    public final ny f;
    public final boolean g;

    public f(ny nyVar, int i, OTRProfileID oTRProfileID, boolean z, String str, boolean z2, boolean z3) {
        this.a = i;
        this.b = oTRProfileID;
        this.c = z;
        this.d = str;
        this.e = z2;
        this.f = nyVar == null ? new ny() : nyVar;
        this.g = z3;
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        try {
            return str.equalsIgnoreCase(UUID.fromString(str).toString());
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public static f b(String str) {
        f fVar = h;
        try {
            switch (Integer.parseInt(str.substring(0, str.indexOf(",")))) {
                case 1:
                    String[] strArrSplit = str.split(",", 6);
                    if (strArrSplit.length != 6) {
                        return fVar;
                    }
                    String str2 = strArrSplit[0];
                    String str3 = strArrSplit[1];
                    String str4 = strArrSplit[2];
                    String str5 = strArrSplit[3];
                    String str6 = strArrSplit[4];
                    try {
                        return (Integer.parseInt(str2) == 1 && a(str6)) ? new f(n3.p(str6, false), Integer.parseInt(str3), "1".equals(str4) ? null : OTRProfileID.b, "1".equals(str5), strArrSplit[5], true, false) : fVar;
                    } catch (NumberFormatException unused) {
                        return fVar;
                    }
                case 2:
                    String[] strArrSplit2 = str.split(",", 6);
                    if (strArrSplit2.length != 6) {
                        return fVar;
                    }
                    String str7 = strArrSplit2[0];
                    String str8 = strArrSplit2[1];
                    String str9 = strArrSplit2[2];
                    String str10 = strArrSplit2[3];
                    String str11 = strArrSplit2[4];
                    try {
                        return (Integer.parseInt(str7) == 2 && a(str11)) ? new f(n3.p(str11, false), Integer.parseInt(str8), "1".equals(str9) ? OTRProfileID.b : null, "1".equals(str10), strArrSplit2[5], true, false) : fVar;
                    } catch (NumberFormatException unused2) {
                        return fVar;
                    }
                case 3:
                    String[] strArrSplit3 = str.split(",", 7);
                    if (strArrSplit3.length != 7) {
                        return fVar;
                    }
                    String str12 = strArrSplit3[0];
                    String str13 = strArrSplit3[1];
                    String str14 = strArrSplit3[2];
                    String str15 = strArrSplit3[3];
                    String str16 = strArrSplit3[4];
                    String str17 = strArrSplit3[5];
                    String str18 = strArrSplit3[6];
                    OTRProfileID oTRProfileID = "1".equals(str15) ? OTRProfileID.b : null;
                    boolean zEquals = "1".equals(str16);
                    try {
                        int i = Integer.parseInt(str12);
                        int i2 = Integer.parseInt(str13);
                        int i3 = Integer.parseInt(str14);
                        if (i != 3 || !a(str17)) {
                            return fVar;
                        }
                        if (i3 == 1 || i3 == 2) {
                            return new f(n3.p(str17, i3 == 2), i2, oTRProfileID, zEquals, str18, true, false);
                        }
                        return fVar;
                    } catch (NumberFormatException unused3) {
                        return fVar;
                    }
                case 4:
                    String[] strArrSplit4 = str.split(",", 8);
                    if (strArrSplit4.length != 8) {
                        return fVar;
                    }
                    String str19 = strArrSplit4[0];
                    String str20 = strArrSplit4[1];
                    String str21 = strArrSplit4[2];
                    String str22 = strArrSplit4[3];
                    String str23 = strArrSplit4[4];
                    String str24 = strArrSplit4[5];
                    String str25 = strArrSplit4[6];
                    String str26 = strArrSplit4[7];
                    OTRProfileID oTRProfileID2 = "1".equals(str22) ? OTRProfileID.b : null;
                    boolean zEquals2 = "1".equals(str23);
                    boolean zEquals3 = "1".equals(str24);
                    try {
                        int i4 = Integer.parseInt(str19);
                        int i5 = Integer.parseInt(str20);
                        int i6 = Integer.parseInt(str21);
                        if (i4 != 4 || !a(str25)) {
                            return fVar;
                        }
                        if (i6 == 1 || i6 == 2) {
                            return new f(n3.p(str25, i6 == 2), i5, oTRProfileID2, zEquals2, str26, zEquals3, false);
                        }
                        return fVar;
                    } catch (NumberFormatException unused4) {
                        return fVar;
                    }
                case 5:
                    String[] strArrSplit5 = str.split(",", 8);
                    if (strArrSplit5.length != 8) {
                        return fVar;
                    }
                    String str27 = strArrSplit5[0];
                    String str28 = strArrSplit5[1];
                    String str29 = strArrSplit5[2];
                    String str30 = strArrSplit5[3];
                    try {
                        return (Integer.parseInt(str27) == 5 && a(str30) && !TextUtils.isEmpty(str29)) ? new f(new ny(str29, str30), Integer.parseInt(str28), "1".equals(strArrSplit5[4]) ? OTRProfileID.b : null, "1".equals(strArrSplit5[5]), strArrSplit5[7], "1".equals(strArrSplit5[6]), false) : fVar;
                    } catch (NumberFormatException unused5) {
                        return fVar;
                    }
                case 6:
                    String[] strArrSplit6 = str.split(",", 9);
                    if (strArrSplit6.length != 9) {
                        return fVar;
                    }
                    String str31 = strArrSplit6[0];
                    String str32 = strArrSplit6[1];
                    String str33 = strArrSplit6[2];
                    String str34 = strArrSplit6[3];
                    String str35 = strArrSplit6[4];
                    String str36 = strArrSplit6[5];
                    String str37 = strArrSplit6[6];
                    String str38 = strArrSplit6[7];
                    try {
                        return (Integer.parseInt(str31) != 6 || TextUtils.isEmpty(str34) || TextUtils.isEmpty(str33)) ? fVar : new f(new ny(str33, str34), Integer.parseInt(str32), "1".equals(str35) ? OTRProfileID.b : null, "1".equals(str36), strArrSplit6[8], "1".equals(str37), "1".equals(str38));
                    } catch (NumberFormatException unused6) {
                        return fVar;
                    }
                case 7:
                    String[] strArrSplit7 = str.split(",", 9);
                    if (strArrSplit7.length != 9) {
                        return fVar;
                    }
                    String str39 = strArrSplit7[0];
                    String str40 = strArrSplit7[1];
                    String str41 = strArrSplit7[2];
                    String str42 = strArrSplit7[3];
                    String str43 = strArrSplit7[4];
                    String str44 = strArrSplit7[5];
                    String str45 = strArrSplit7[6];
                    String str46 = strArrSplit7[7];
                    String str47 = strArrSplit7[8];
                    if (TextUtils.isEmpty(str42) || TextUtils.isEmpty(str41)) {
                        return fVar;
                    }
                    if (!"".equals(str43)) {
                        try {
                            oTRProfileIDA = OTRProfileID.a(str43);
                        } catch (IllegalStateException unused7) {
                            oTRProfileIDA = OTRProfileID.b;
                        }
                    }
                    try {
                        return Integer.parseInt(str39) != 7 ? fVar : new f(new ny(str41, str42), Integer.parseInt(str40), oTRProfileIDA, "1".equals(str44), str47, "1".equals(str45), "1".equals(str46));
                    } catch (NumberFormatException unused8) {
                        return fVar;
                    }
                default:
                    return fVar;
            }
        } catch (NumberFormatException unused9) {
            Log.w("cr_DownloadEntry", "Exception while parsing pending download:" + str);
            return fVar;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (!this.f.equals(fVar.f) || !TextUtils.equals(this.d, fVar.d) || this.a != fVar.a) {
            return false;
        }
        OTRProfileID oTRProfileID = OTRProfileID.b;
        OTRProfileID oTRProfileID2 = this.b;
        OTRProfileID oTRProfileID3 = fVar.b;
        return (oTRProfileID2 == null ? oTRProfileID3 == null : oTRProfileID2.equals(oTRProfileID3)) && this.c == fVar.c && this.e == fVar.e && this.g == fVar.g;
    }

    public final int hashCode() {
        OTRProfileID oTRProfileID = this.b;
        return ((this.d.hashCode() + ((this.f.hashCode() + ((((((((1147 + (oTRProfileID != null ? oTRProfileID.hashCode() : 0)) * 37) + (this.c ? 1 : 0)) * 37) + (this.e ? 1 : 0)) * 37) + this.a) * 37)) * 37)) * 37) + (this.g ? 1 : 0);
    }
}
