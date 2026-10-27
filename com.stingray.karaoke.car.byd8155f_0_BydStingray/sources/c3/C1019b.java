package c3;

import Y2.k;
import Y2.l;
import android.graphics.PointF;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import g2.C1289b;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.g;
import h2.t;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: c3.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1019b implements l {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Pattern f12354r = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C1018a f12355m;
    public LinkedHashMap o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f12357p = -3.4028235E38f;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f12358q = -3.4028235E38f;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t f12356n = new t();

    public C1019b(List list) {
        if (list == null || list.isEmpty()) {
            this.l = false;
            this.f12355m = null;
            return;
        }
        this.l = true;
        String strM = AbstractC1343A.m((byte[]) list.get(0));
        AbstractC1346c.c(strM.startsWith("Format:"));
        C1018a c1018aB = C1018a.b(strM);
        c1018aB.getClass();
        this.f12355m = c1018aB;
        b(new t((byte[]) list.get(1)), StandardCharsets.UTF_8);
    }

    public static int a(long j3, ArrayList arrayList, ArrayList arrayList2) {
        int i7;
        int size = arrayList.size() - 1;
        while (true) {
            if (size < 0) {
                i7 = 0;
                break;
            }
            if (((Long) arrayList.get(size)).longValue() == j3) {
                return size;
            }
            if (((Long) arrayList.get(size)).longValue() < j3) {
                i7 = size + 1;
                break;
            }
            size--;
        }
        arrayList.add(i7, Long.valueOf(j3));
        arrayList2.add(i7, i7 == 0 ? new ArrayList() : new ArrayList((Collection) arrayList2.get(i7 - 1)));
        return i7;
    }

    public static long c(String str) {
        Matcher matcher = f12354r.matcher(str.trim());
        if (!matcher.matches()) {
            return -9223372036854775807L;
        }
        String strGroup = matcher.group(1);
        int i7 = AbstractC1343A.f15195a;
        return (Long.parseLong(matcher.group(4)) * 10000) + (Long.parseLong(matcher.group(3)) * 1000000) + (Long.parseLong(matcher.group(2)) * 60000000) + (Long.parseLong(strGroup) * 3600000000L);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(h2.t r38, java.nio.charset.Charset r39) {
        /*
            Method dump skipped, instruction units count: 834
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c3.C1019b.b(h2.t, java.nio.charset.Charset):void");
    }

    @Override // Y2.l
    public final void h(byte[] bArr, int i7, int i10, k kVar, g gVar) {
        Charset charset;
        C1018a c1018a;
        t tVar;
        int i11;
        float f10;
        int i12;
        Layout.Alignment alignment;
        Layout.Alignment alignment2;
        int i13;
        PointF pointF;
        int i14;
        int i15;
        float f11;
        float f12;
        float f13;
        float f14;
        int i16;
        int i17;
        float f15;
        int i18;
        int i19;
        Integer num;
        int iA;
        int i20;
        C1019b c1019b = this;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        t tVar2 = c1019b.f12356n;
        tVar2.F(i7 + i10, bArr);
        tVar2.H(i7);
        Charset charsetD = tVar2.D();
        if (charsetD == null) {
            charsetD = StandardCharsets.UTF_8;
        }
        boolean z6 = c1019b.l;
        if (!z6) {
            c1019b.b(tVar2, charsetD);
        }
        C1018a c1018aB = z6 ? c1019b.f12355m : null;
        while (true) {
            String strI = tVar2.i(charsetD);
            if (strI == null) {
                long j3 = kVar.f10648a;
                ArrayList arrayList3 = (j3 == -9223372036854775807L || !kVar.f10649b) ? null : new ArrayList();
                for (int i21 = 0; i21 < arrayList.size(); i21++) {
                    List list = (List) arrayList.get(i21);
                    if (!list.isEmpty() || i21 == 0) {
                        if (i21 == arrayList.size() - 1) {
                            throw new IllegalStateException();
                        }
                        long jLongValue = ((Long) arrayList2.get(i21)).longValue();
                        long jLongValue2 = ((Long) arrayList2.get(i21 + 1)).longValue() - ((Long) arrayList2.get(i21)).longValue();
                        if (j3 == -9223372036854775807L || jLongValue >= j3) {
                            gVar.accept(new Y2.a(list, jLongValue, jLongValue2));
                        } else if (arrayList3 != null) {
                            arrayList3.add(new Y2.a(list, jLongValue, jLongValue2));
                        }
                    }
                }
                if (arrayList3 != null) {
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        gVar.accept((Y2.a) it.next());
                    }
                    return;
                }
                return;
            }
            if (strI.startsWith("Format:")) {
                c1018aB = C1018a.b(strI);
            } else if (strI.startsWith("Dialogue:")) {
                if (c1018aB == null) {
                    AbstractC1346c.z("SsaParser", "Skipping dialogue line before complete format: ".concat(strI));
                } else {
                    AbstractC1346c.c(strI.startsWith("Dialogue:"));
                    String strSubstring = strI.substring(9);
                    int i22 = c1018aB.f12353e;
                    String[] strArrSplit = strSubstring.split(",", i22);
                    if (strArrSplit.length != i22) {
                        AbstractC1346c.z("SsaParser", "Skipping dialogue line with fewer columns than format: ".concat(strI));
                    } else {
                        t tVar3 = tVar2;
                        long jC = c(strArrSplit[c1018aB.f12351a]);
                        if (jC == -9223372036854775807L) {
                            AbstractC1346c.z("SsaParser", "Skipping invalid timing: ".concat(strI));
                        } else {
                            long jC2 = c(strArrSplit[c1018aB.f12352b]);
                            if (jC2 == -9223372036854775807L) {
                                AbstractC1346c.z("SsaParser", "Skipping invalid timing: ".concat(strI));
                            } else {
                                LinkedHashMap linkedHashMap = c1019b.o;
                                charset = charsetD;
                                C1022e c1022e = (linkedHashMap == null || (i20 = c1018aB.c) == -1) ? null : (C1022e) linkedHashMap.get(strArrSplit[i20].trim());
                                String str = strArrSplit[c1018aB.d];
                                Matcher matcher = C1021d.f12368a.matcher(str);
                                int i23 = -1;
                                PointF pointF2 = null;
                                while (matcher.find()) {
                                    C1018a c1018a2 = c1018aB;
                                    String strGroup = matcher.group(1);
                                    strGroup.getClass();
                                    try {
                                        PointF pointFA = C1021d.a(strGroup);
                                        if (pointFA != null) {
                                            pointF2 = pointFA;
                                        }
                                    } catch (RuntimeException unused) {
                                    }
                                    try {
                                        Matcher matcher2 = C1021d.d.matcher(strGroup);
                                        if (matcher2.find()) {
                                            String strGroup2 = matcher2.group(1);
                                            strGroup2.getClass();
                                            iA = C1022e.a(strGroup2);
                                        } else {
                                            iA = -1;
                                        }
                                        if (iA != -1) {
                                            i23 = iA;
                                        }
                                    } catch (RuntimeException unused2) {
                                    }
                                    c1018aB = c1018a2;
                                }
                                c1018a = c1018aB;
                                String strReplace = C1021d.f12368a.matcher(str).replaceAll("").replace("\\N", "\n").replace("\\n", "\n").replace("\\h", " ");
                                float f16 = c1019b.f12357p;
                                float f17 = c1019b.f12358q;
                                SpannableString spannableString = new SpannableString(strReplace);
                                if (c1022e != null) {
                                    Integer num2 = c1022e.c;
                                    if (num2 != null) {
                                        tVar = tVar3;
                                        spannableString.setSpan(new ForegroundColorSpan(num2.intValue()), 0, spannableString.length(), 33);
                                    } else {
                                        tVar = tVar3;
                                    }
                                    if (c1022e.f12377j == 3 && (num = c1022e.d) != null) {
                                        spannableString.setSpan(new BackgroundColorSpan(num.intValue()), 0, spannableString.length(), 33);
                                    }
                                    float f18 = c1022e.f12372e;
                                    if (f18 == -3.4028235E38f || f17 == -3.4028235E38f) {
                                        f14 = -3.4028235E38f;
                                        i16 = Integer.MIN_VALUE;
                                    } else {
                                        f14 = f18 / f17;
                                        i16 = 1;
                                    }
                                    boolean z10 = c1022e.f12374g;
                                    boolean z11 = c1022e.f12373f;
                                    if (z11 && z10) {
                                        i17 = i16;
                                        f15 = f14;
                                        i18 = 0;
                                        i19 = 33;
                                        spannableString.setSpan(new StyleSpan(3), 0, spannableString.length(), 33);
                                    } else {
                                        i17 = i16;
                                        f15 = f14;
                                        i18 = 0;
                                        i19 = 33;
                                        if (z11) {
                                            spannableString.setSpan(new StyleSpan(1), 0, spannableString.length(), 33);
                                        } else if (z10) {
                                            spannableString.setSpan(new StyleSpan(2), 0, spannableString.length(), 33);
                                        }
                                    }
                                    if (c1022e.f12375h) {
                                        spannableString.setSpan(new UnderlineSpan(), i18, spannableString.length(), i19);
                                    }
                                    if (c1022e.f12376i) {
                                        spannableString.setSpan(new StrikethroughSpan(), i18, spannableString.length(), i19);
                                    }
                                    i11 = i23;
                                    f10 = f15;
                                    i12 = i17;
                                } else {
                                    tVar = tVar3;
                                    i11 = i23;
                                    f10 = -3.4028235E38f;
                                    i12 = Integer.MIN_VALUE;
                                }
                                int i24 = -1;
                                if (i11 != -1) {
                                    i24 = i11;
                                } else if (c1022e != null) {
                                    i24 = c1022e.f12371b;
                                }
                                switch (i24) {
                                    case 0:
                                    default:
                                        AbstractC1347d.p("Unknown alignment: ", i24, "SsaParser");
                                    case -1:
                                        alignment2 = null;
                                        break;
                                    case 1:
                                    case 4:
                                    case 7:
                                        alignment = Layout.Alignment.ALIGN_NORMAL;
                                        alignment2 = alignment;
                                        break;
                                    case 2:
                                    case 5:
                                    case 8:
                                        alignment = Layout.Alignment.ALIGN_CENTER;
                                        alignment2 = alignment;
                                        break;
                                    case 3:
                                    case 6:
                                    case 9:
                                        alignment = Layout.Alignment.ALIGN_OPPOSITE;
                                        alignment2 = alignment;
                                        break;
                                }
                                int i25 = Integer.MIN_VALUE;
                                switch (i24) {
                                    case 0:
                                    default:
                                        AbstractC1347d.p("Unknown alignment: ", i24, "SsaParser");
                                    case -1:
                                        i13 = Integer.MIN_VALUE;
                                        break;
                                    case 1:
                                    case 4:
                                    case 7:
                                        i13 = 0;
                                        break;
                                    case 2:
                                    case 5:
                                    case 8:
                                        i13 = 1;
                                        break;
                                    case 3:
                                    case 6:
                                    case 9:
                                        i13 = 2;
                                        break;
                                }
                                switch (i24) {
                                    case 0:
                                    default:
                                        AbstractC1347d.p("Unknown alignment: ", i24, "SsaParser");
                                    case -1:
                                        pointF = pointF2;
                                        break;
                                    case 1:
                                    case 2:
                                    case 3:
                                        pointF = pointF2;
                                        i25 = 2;
                                        break;
                                    case 4:
                                    case 5:
                                    case 6:
                                        pointF = pointF2;
                                        i25 = 1;
                                        break;
                                    case 7:
                                    case 8:
                                    case 9:
                                        pointF = pointF2;
                                        i25 = 0;
                                        break;
                                }
                                if (pointF == null || f17 == -3.4028235E38f || f16 == -3.4028235E38f) {
                                    float f19 = 0.95f;
                                    if (i13 != 0) {
                                        i14 = 1;
                                        if (i13 != 1) {
                                            i15 = 2;
                                            f11 = i13 != 2 ? -3.4028235E38f : 0.95f;
                                        } else {
                                            i15 = 2;
                                            f11 = 0.5f;
                                        }
                                    } else {
                                        i14 = 1;
                                        i15 = 2;
                                        f11 = 0.05f;
                                    }
                                    if (i25 == 0) {
                                        f19 = 0.05f;
                                    } else if (i25 == i14) {
                                        f19 = 0.5f;
                                    } else if (i25 != i15) {
                                        f19 = -3.4028235E38f;
                                    }
                                    f12 = f19;
                                    f13 = f11;
                                } else {
                                    float f20 = pointF.x / f16;
                                    f12 = pointF.y / f17;
                                    f13 = f20;
                                }
                                C1289b c1289b = new C1289b(spannableString, alignment2, null, null, f12, 0, i25, f13, i13, i12, f10, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f);
                                int iA2 = a(jC2, arrayList2, arrayList);
                                for (int iA3 = a(jC, arrayList2, arrayList); iA3 < iA2; iA3++) {
                                    ((List) arrayList.get(iA3)).add(c1289b);
                                }
                                c1019b = this;
                                charsetD = charset;
                                c1018aB = c1018a;
                                tVar2 = tVar;
                            }
                        }
                        charset = charsetD;
                        c1018a = c1018aB;
                        tVar = tVar3;
                        c1019b = this;
                        charsetD = charset;
                        c1018aB = c1018a;
                        tVar2 = tVar;
                    }
                }
                charset = charsetD;
                c1018a = c1018aB;
                tVar = tVar2;
                c1019b = this;
                charsetD = charset;
                c1018aB = c1018a;
                tVar2 = tVar;
            } else {
                charset = charsetD;
                c1018a = c1018aB;
                tVar = tVar2;
                c1019b = this;
                charsetD = charset;
                c1018aB = c1018a;
                tVar2 = tVar;
            }
        }
    }

    @Override // Y2.l
    public final int s() {
        return 1;
    }
}
