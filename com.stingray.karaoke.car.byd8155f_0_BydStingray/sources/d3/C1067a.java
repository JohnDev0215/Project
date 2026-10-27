package d3;

import Y2.k;
import Y2.l;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import d5.I;
import g2.C1289b;
import h2.AbstractC1346c;
import h2.g;
import h2.t;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: d3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1067a implements l {
    public static final Pattern o = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f13338p = Pattern.compile("\\{\\\\.*?\\}");
    public final StringBuilder l = new StringBuilder();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f13339m = new ArrayList();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final t f13340n = new t();

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static C1289b a(Spanned spanned, String str) {
        byte b10;
        byte b11;
        float f10;
        if (str == null) {
            return new C1289b(spanned, null, null, null, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f);
        }
        switch (str.hashCode()) {
            case -685620710:
                b10 = !str.equals("{\\an1}") ? (byte) -1 : (byte) 0;
                break;
            case -685620679:
                b10 = !str.equals("{\\an2}") ? (byte) -1 : (byte) 6;
                break;
            case -685620648:
                b10 = !str.equals("{\\an3}") ? (byte) -1 : (byte) 3;
                break;
            case -685620617:
                b10 = !str.equals("{\\an4}") ? (byte) -1 : (byte) 1;
                break;
            case -685620586:
                b10 = !str.equals("{\\an5}") ? (byte) -1 : (byte) 7;
                break;
            case -685620555:
                b10 = !str.equals("{\\an6}") ? (byte) -1 : (byte) 4;
                break;
            case -685620524:
                b10 = !str.equals("{\\an7}") ? (byte) -1 : (byte) 2;
                break;
            case -685620493:
                b10 = !str.equals("{\\an8}") ? (byte) -1 : (byte) 8;
                break;
            case -685620462:
                b10 = !str.equals("{\\an9}") ? (byte) -1 : (byte) 5;
                break;
            default:
                b10 = -1;
                break;
        }
        int i7 = (b10 == 0 || b10 == 1 || b10 == 2) ? 0 : (b10 == 3 || b10 == 4 || b10 == 5) ? 2 : 1;
        switch (str.hashCode()) {
            case -685620710:
                b11 = !str.equals("{\\an1}") ? (byte) -1 : (byte) 0;
                break;
            case -685620679:
                b11 = !str.equals("{\\an2}") ? (byte) -1 : (byte) 1;
                break;
            case -685620648:
                b11 = !str.equals("{\\an3}") ? (byte) -1 : (byte) 2;
                break;
            case -685620617:
                b11 = !str.equals("{\\an4}") ? (byte) -1 : (byte) 6;
                break;
            case -685620586:
                b11 = !str.equals("{\\an5}") ? (byte) -1 : (byte) 7;
                break;
            case -685620555:
                b11 = !str.equals("{\\an6}") ? (byte) -1 : (byte) 8;
                break;
            case -685620524:
                b11 = !str.equals("{\\an7}") ? (byte) -1 : (byte) 3;
                break;
            case -685620493:
                b11 = !str.equals("{\\an8}") ? (byte) -1 : (byte) 4;
                break;
            case -685620462:
                b11 = !str.equals("{\\an9}") ? (byte) -1 : (byte) 5;
                break;
            default:
                b11 = -1;
                break;
        }
        int i10 = (b11 == 0 || b11 == 1 || b11 == 2) ? 2 : (b11 == 3 || b11 == 4 || b11 == 5) ? 0 : 1;
        float f11 = 0.92f;
        if (i7 == 0) {
            f10 = 0.08f;
        } else if (i7 == 1) {
            f10 = 0.5f;
        } else {
            if (i7 != 2) {
                throw new IllegalArgumentException();
            }
            f10 = 0.92f;
        }
        if (i10 == 0) {
            f11 = 0.08f;
        } else if (i10 == 1) {
            f11 = 0.5f;
        } else if (i10 != 2) {
            throw new IllegalArgumentException();
        }
        return new C1289b(spanned, null, null, null, f11, 0, i10, f10, i7, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, -3.4028235E38f, false, -16777216, Integer.MIN_VALUE, 0.0f);
    }

    public static long b(Matcher matcher, int i7) {
        String strGroup = matcher.group(i7 + 1);
        long j3 = strGroup != null ? Long.parseLong(strGroup) * 3600000 : 0L;
        String strGroup2 = matcher.group(i7 + 2);
        strGroup2.getClass();
        long j10 = (Long.parseLong(strGroup2) * 60000) + j3;
        String strGroup3 = matcher.group(i7 + 3);
        strGroup3.getClass();
        long j11 = (Long.parseLong(strGroup3) * 1000) + j10;
        String strGroup4 = matcher.group(i7 + 4);
        if (strGroup4 != null) {
            j11 += Long.parseLong(strGroup4);
        }
        return j11 * 1000;
    }

    @Override // Y2.l
    public final void h(byte[] bArr, int i7, int i10, k kVar, g gVar) {
        g gVar2;
        String strI;
        String str;
        g gVar3;
        C1067a c1067a = this;
        g gVar4 = gVar;
        String str2 = "SubripParser";
        t tVar = c1067a.f13340n;
        tVar.F(i7 + i10, bArr);
        tVar.H(i7);
        Charset charsetD = tVar.D();
        if (charsetD == null) {
            charsetD = StandardCharsets.UTF_8;
        }
        long j3 = kVar.f10648a;
        ArrayList arrayList = (j3 == -9223372036854775807L || !kVar.f10649b) ? null : new ArrayList();
        while (true) {
            String strI2 = tVar.i(charsetD);
            if (strI2 == null) {
                break;
            }
            if (strI2.length() != 0) {
                try {
                    Integer.parseInt(strI2);
                    strI = tVar.i(charsetD);
                } catch (NumberFormatException unused) {
                    gVar2 = gVar4;
                    AbstractC1346c.z(str2, "Skipping invalid index: ".concat(strI2));
                }
                if (strI == null) {
                    AbstractC1346c.z(str2, "Unexpected end");
                    break;
                }
                Matcher matcher = o.matcher(strI);
                if (matcher.matches()) {
                    long jB = b(matcher, 1);
                    long jB2 = b(matcher, 6);
                    StringBuilder sb = c1067a.l;
                    sb.setLength(0);
                    ArrayList arrayList2 = c1067a.f13339m;
                    arrayList2.clear();
                    String strI3 = tVar.i(charsetD);
                    while (!TextUtils.isEmpty(strI3)) {
                        if (sb.length() > 0) {
                            sb.append("<br>");
                        }
                        String strTrim = strI3.trim();
                        StringBuilder sb2 = new StringBuilder(strTrim);
                        Matcher matcher2 = f13338p.matcher(strTrim);
                        int i11 = 0;
                        while (matcher2.find()) {
                            String strGroup = matcher2.group();
                            arrayList2.add(strGroup);
                            Matcher matcher3 = matcher2;
                            int iStart = matcher2.start() - i11;
                            int length = strGroup.length();
                            sb2.replace(iStart, iStart + length, "");
                            i11 += length;
                            matcher2 = matcher3;
                            str2 = str2;
                        }
                        sb.append(sb2.toString());
                        strI3 = tVar.i(charsetD);
                        str2 = str2;
                    }
                    String str3 = str2;
                    Spanned spannedFromHtml = Html.fromHtml(sb.toString());
                    int i12 = 0;
                    while (true) {
                        if (i12 >= arrayList2.size()) {
                            str = null;
                            break;
                        }
                        str = (String) arrayList2.get(i12);
                        if (str.matches("\\{\\\\an[1-9]\\}")) {
                            break;
                        } else {
                            i12++;
                        }
                    }
                    if (j3 == -9223372036854775807L || jB >= j3) {
                        Y2.a aVar = new Y2.a(I.B(a(spannedFromHtml, str)), jB, jB2 - jB);
                        gVar3 = gVar;
                        gVar3.accept(aVar);
                    } else {
                        if (arrayList != null) {
                            arrayList.add(new Y2.a(I.B(a(spannedFromHtml, str)), jB, jB2 - jB));
                        }
                        gVar3 = gVar;
                    }
                    gVar4 = gVar3;
                    str2 = str3;
                    c1067a = this;
                } else {
                    gVar2 = gVar4;
                    AbstractC1346c.z(str2, "Skipping invalid timing: ".concat(strI));
                    gVar4 = gVar2;
                    c1067a = this;
                }
            }
        }
        g gVar5 = gVar4;
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                gVar5.accept((Y2.a) it.next());
            }
        }
    }

    @Override // Y2.l
    public final int s() {
        return 1;
    }
}
