package f3;

import M5.b;
import Y2.k;
import Y2.l;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import d5.C1078G;
import d5.I;
import d5.d0;
import g2.C1289b;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.g;
import h2.t;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/* JADX INFO: renamed from: f3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1255a implements l {
    public final t l = new t();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f14586m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f14587n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final String f14588p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f14589q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f14590r;

    public C1255a(List list) {
        if (list.size() != 1 || (((byte[]) list.get(0)).length != 48 && ((byte[]) list.get(0)).length != 53)) {
            this.f14587n = 0;
            this.o = -1;
            this.f14588p = "sans-serif";
            this.f14586m = false;
            this.f14589q = 0.85f;
            this.f14590r = -1;
            return;
        }
        byte[] bArr = (byte[]) list.get(0);
        this.f14587n = bArr[24];
        this.o = ((bArr[26] & 255) << 24) | ((bArr[27] & 255) << 16) | ((bArr[28] & 255) << 8) | (bArr[29] & 255);
        this.f14588p = "Serif".equals(new String(bArr, 43, bArr.length - 43, StandardCharsets.UTF_8)) ? "serif" : "sans-serif";
        int i7 = bArr[25] * 20;
        this.f14590r = i7;
        boolean z6 = (bArr[0] & 32) != 0;
        this.f14586m = z6;
        if (z6) {
            this.f14589q = AbstractC1343A.h(((bArr[11] & 255) | ((bArr[10] & 255) << 8)) / i7, 0.0f, 0.95f);
        } else {
            this.f14589q = 0.85f;
        }
    }

    public static void a(SpannableStringBuilder spannableStringBuilder, int i7, int i10, int i11, int i12, int i13) {
        if (i7 != i10) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan((i7 >>> 8) | ((i7 & 255) << 24)), i11, i12, i13 | 33);
        }
    }

    public static void b(SpannableStringBuilder spannableStringBuilder, int i7, int i10, int i11, int i12, int i13) {
        if (i7 != i10) {
            int i14 = i13 | 33;
            boolean z6 = (i7 & 1) != 0;
            boolean z10 = (i7 & 2) != 0;
            if (z6) {
                if (z10) {
                    spannableStringBuilder.setSpan(new StyleSpan(3), i11, i12, i14);
                } else {
                    spannableStringBuilder.setSpan(new StyleSpan(1), i11, i12, i14);
                }
            } else if (z10) {
                spannableStringBuilder.setSpan(new StyleSpan(2), i11, i12, i14);
            }
            boolean z11 = (i7 & 4) != 0;
            if (z11) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i11, i12, i14);
            }
            if (z11 || z6 || z10) {
                return;
            }
            spannableStringBuilder.setSpan(new StyleSpan(0), i11, i12, i14);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // Y2.l
    public final void h(byte[] bArr, int i7, int i10, k kVar, g gVar) {
        String strT;
        int i11 = 1;
        t tVar = this.l;
        tVar.F(i7 + i10, bArr);
        tVar.H(i7);
        int i12 = 2;
        int i13 = 0;
        AbstractC1346c.c(tVar.a() >= 2);
        int iB = tVar.B();
        if (iB == 0) {
            strT = "";
        } else {
            int i14 = tVar.f15242b;
            Charset charsetD = tVar.D();
            int i15 = iB - (tVar.f15242b - i14);
            if (charsetD == null) {
                charsetD = StandardCharsets.UTF_8;
            }
            strT = tVar.t(i15, charsetD);
        }
        if (strT.isEmpty()) {
            C1078G c1078g = I.f13384m;
            gVar.accept(new Y2.a(d0.f13415p, -9223372036854775807L, -9223372036854775807L));
            return;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(strT);
        b(spannableStringBuilder, this.f14587n, 0, 0, spannableStringBuilder.length(), 16711680);
        a(spannableStringBuilder, this.o, -1, 0, spannableStringBuilder.length(), 16711680);
        int length = spannableStringBuilder.length();
        String str = this.f14588p;
        if (str != "sans-serif") {
            spannableStringBuilder.setSpan(new TypefaceSpan(str), 0, length, 16711713);
        }
        float fH = this.f14589q;
        while (tVar.a() >= 8) {
            int i16 = tVar.f15242b;
            int iH = tVar.h();
            int iH2 = tVar.h();
            if (iH2 == 1937013100) {
                AbstractC1346c.c(tVar.a() >= i12 ? i11 : i13);
                int iB2 = tVar.B();
                int i17 = i13;
                while (i17 < iB2) {
                    AbstractC1346c.c(tVar.a() >= 12 ? i11 : i13);
                    int iB3 = tVar.B();
                    int iB4 = tVar.B();
                    tVar.I(i12);
                    int iV = tVar.v();
                    tVar.I(i11);
                    int iH3 = tVar.h();
                    if (iB4 > spannableStringBuilder.length()) {
                        StringBuilder sbJ = b.j("Truncating styl end (", iB4, ") to cueText.length() (");
                        sbJ.append(spannableStringBuilder.length());
                        sbJ.append(").");
                        AbstractC1346c.z("Tx3gParser", sbJ.toString());
                        iB4 = spannableStringBuilder.length();
                    }
                    if (iB3 >= iB4) {
                        AbstractC1346c.z("Tx3gParser", "Ignoring styl with start (" + iB3 + ") >= end (" + iB4 + ").");
                    } else {
                        int i18 = iB4;
                        b(spannableStringBuilder, iV, this.f14587n, iB3, i18, 0);
                        a(spannableStringBuilder, iH3, this.o, iB3, i18, 0);
                    }
                    i11 = 1;
                    i17++;
                    i12 = 2;
                    i13 = 0;
                }
            } else if (iH2 == 1952608120 && this.f14586m) {
                i12 = 2;
                AbstractC1346c.c(tVar.a() >= 2 ? i11 : 0);
                fH = AbstractC1343A.h(tVar.B() / this.f14590r, 0.0f, 0.95f);
            } else {
                i12 = 2;
            }
            tVar.H(i16 + iH);
            i13 = 0;
        }
        gVar.accept(new Y2.a(I.B(new C1289b(spannableStringBuilder, null, null, null, fH, 0, 0, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f)), -9223372036854775807L, -9223372036854775807L));
    }

    @Override // Y2.l
    public final int s() {
        return 2;
    }
}
