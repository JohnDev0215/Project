package g3;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import h2.AbstractC1346c;
import h2.t;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f14844a = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f14845b = Pattern.compile("(\\S+?):(\\S+)");
    public static final Map c;
    public static final Map d;

    static {
        HashMap map = new HashMap();
        map.put("white", Integer.valueOf(Color.rgb(255, 255, 255)));
        map.put("lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        map.put("cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        map.put("red", Integer.valueOf(Color.rgb(255, 0, 0)));
        map.put("yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        map.put("magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        map.put("blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        map.put("black", Integer.valueOf(Color.rgb(0, 0, 0)));
        c = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("bg_white", Integer.valueOf(Color.rgb(255, 255, 255)));
        map2.put("bg_lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        map2.put("bg_cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        map2.put("bg_red", Integer.valueOf(Color.rgb(255, 0, 0)));
        map2.put("bg_yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        map2.put("bg_magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        map2.put("bg_blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        map2.put("bg_black", Integer.valueOf(Color.rgb(0, 0, 0)));
        d = Collections.unmodifiableMap(map2);
    }

    public static void a(String str, e eVar, List list, SpannableStringBuilder spannableStringBuilder, List list2) {
        int i7;
        int length;
        int i10;
        int i11;
        i7 = eVar.f14833b;
        length = spannableStringBuilder.length();
        String str2 = eVar.f14832a;
        str2.getClass();
        i10 = -1;
        switch (str2) {
            case "":
            case "lang":
                break;
            case "b":
                spannableStringBuilder.setSpan(new StyleSpan(1), i7, length, 33);
                break;
            case "c":
                for (String str3 : eVar.d) {
                    Map map = c;
                    if (map.containsKey(str3)) {
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(((Integer) map.get(str3)).intValue()), i7, length, 33);
                    } else {
                        Map map2 = d;
                        if (map2.containsKey(str3)) {
                            spannableStringBuilder.setSpan(new BackgroundColorSpan(((Integer) map2.get(str3)).intValue()), i7, length, 33);
                        }
                    }
                }
                break;
            case "i":
                spannableStringBuilder.setSpan(new StyleSpan(2), i7, length, 33);
                break;
            case "u":
                spannableStringBuilder.setSpan(new UnderlineSpan(), i7, length, 33);
                break;
            case "v":
                spannableStringBuilder.setSpan(new g2.i(eVar.c), i7, length, 33);
                break;
            case "ruby":
                int iC = c(list2, str, eVar);
                ArrayList arrayList = new ArrayList(list.size());
                arrayList.addAll(list);
                Collections.sort(arrayList, d.c);
                int i12 = eVar.f14833b;
                int i13 = 0;
                int length2 = 0;
                while (i13 < arrayList.size()) {
                    if ("rt".equals(((d) arrayList.get(i13)).f14830a.f14832a)) {
                        d dVar = (d) arrayList.get(i13);
                        int iC2 = c(list2, str, dVar.f14830a);
                        if (iC2 == i10) {
                            iC2 = iC != i10 ? iC : 1;
                        }
                        int i14 = dVar.f14830a.f14833b - length2;
                        int i15 = dVar.f14831b - length2;
                        CharSequence charSequenceSubSequence = spannableStringBuilder.subSequence(i14, i15);
                        spannableStringBuilder.delete(i14, i15);
                        spannableStringBuilder.setSpan(new g2.g(charSequenceSubSequence.toString(), iC2), i12, i14, 33);
                        length2 = charSequenceSubSequence.length() + length2;
                        i12 = i14;
                    }
                    i13++;
                    i10 = -1;
                }
                break;
            default:
                return;
        }
        ArrayList arrayListB = b(list2, str, eVar);
        for (int i16 = 0; i16 < arrayListB.size(); i16++) {
            C1293b c1293b = ((f) arrayListB.get(i16)).f14834m;
            int i17 = c1293b.f14824k;
            if (i17 == -1 && c1293b.l == -1) {
                i11 = -1;
            } else {
                i11 = (c1293b.l == 1 ? (char) 2 : (char) 0) | (i17 == 1 ? (char) 1 : (char) 0);
            }
            if (i11 != -1) {
                int i18 = c1293b.f14824k;
                w0.c.D(spannableStringBuilder, new StyleSpan((i18 == -1 && c1293b.l == -1) ? -1 : (i18 == 1 ? 1 : 0) | (c1293b.l == 1 ? 2 : 0)), i7, length);
            }
            if (c1293b.f14823j == 1) {
                spannableStringBuilder.setSpan(new UnderlineSpan(), i7, length, 33);
            }
            if (c1293b.f14820g) {
                if (!c1293b.f14820g) {
                    throw new IllegalStateException("Font color not defined");
                }
                w0.c.D(spannableStringBuilder, new ForegroundColorSpan(c1293b.f14819f), i7, length);
            }
            if (c1293b.f14822i) {
                if (!c1293b.f14822i) {
                    throw new IllegalStateException("Background color not defined.");
                }
                w0.c.D(spannableStringBuilder, new BackgroundColorSpan(c1293b.f14821h), i7, length);
            }
            if (c1293b.f14818e != null) {
                w0.c.D(spannableStringBuilder, new TypefaceSpan(c1293b.f14818e), i7, length);
            }
            int i19 = c1293b.f14825m;
            if (i19 == 1) {
                w0.c.D(spannableStringBuilder, new AbsoluteSizeSpan((int) c1293b.f14826n, true), i7, length);
            } else if (i19 == 2) {
                w0.c.D(spannableStringBuilder, new RelativeSizeSpan(c1293b.f14826n), i7, length);
            } else if (i19 == 3) {
                w0.c.D(spannableStringBuilder, new RelativeSizeSpan(c1293b.f14826n / 100.0f), i7, length);
            }
            if (c1293b.f14827p) {
                spannableStringBuilder.setSpan(new g2.e(), i7, length, 33);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v5, types: [int] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList b(java.util.List r8, java.lang.String r9, g3.e r10) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
        L7:
            int r3 = r8.size()
            if (r2 >= r3) goto L77
            java.lang.Object r3 = r8.get(r2)
            g3.b r3 = (g3.C1293b) r3
            java.lang.String r4 = r10.f14832a
            java.lang.String r5 = r3.f14816a
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3a
            java.lang.String r5 = r3.f14817b
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3a
            java.util.Set r5 = r3.c
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3a
            java.lang.String r5 = r3.d
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3a
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            goto L6a
        L3a:
            java.lang.String r5 = r3.f14816a
            r6 = 1073741824(0x40000000, float:2.0)
            int r5 = g3.C1293b.a(r5, r9, r1, r6)
            java.lang.String r6 = r3.f14817b
            r7 = 2
            int r4 = g3.C1293b.a(r6, r4, r5, r7)
            java.lang.String r5 = r3.d
            java.lang.String r6 = r10.c
            r7 = 4
            int r4 = g3.C1293b.a(r5, r6, r4, r7)
            r5 = -1
            if (r4 == r5) goto L69
            java.util.Set r5 = r3.c
            java.util.Set r6 = r10.d
            boolean r5 = r6.containsAll(r5)
            if (r5 != 0) goto L60
            goto L69
        L60:
            java.util.Set r5 = r3.c
            int r5 = r5.size()
            int r5 = r5 * r7
            int r4 = r4 + r5
            goto L6a
        L69:
            r4 = r1
        L6a:
            if (r4 <= 0) goto L74
            g3.f r5 = new g3.f
            r5.<init>(r4, r3)
            r0.add(r5)
        L74:
            int r2 = r2 + 1
            goto L7
        L77:
            java.util.Collections.sort(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: g3.h.b(java.util.List, java.lang.String, g3.e):java.util.ArrayList");
    }

    public static int c(List list, String str, e eVar) {
        ArrayList arrayListB = b(list, str, eVar);
        for (int i7 = 0; i7 < arrayListB.size(); i7++) {
            int i10 = ((f) arrayListB.get(i7)).f14834m.o;
            if (i10 != -1) {
                return i10;
            }
        }
        return -1;
    }

    public static C1294c d(String str, Matcher matcher, t tVar, ArrayList arrayList) {
        g gVar = new g();
        try {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            gVar.f14835a = i.b(strGroup);
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            gVar.f14836b = i.b(strGroup2);
            String strGroup3 = matcher.group(3);
            strGroup3.getClass();
            e(strGroup3, gVar);
            StringBuilder sb = new StringBuilder();
            tVar.getClass();
            String strI = tVar.i(StandardCharsets.UTF_8);
            while (!TextUtils.isEmpty(strI)) {
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                sb.append(strI.trim());
                strI = tVar.i(StandardCharsets.UTF_8);
            }
            gVar.c = f(str, sb.toString(), arrayList);
            return new C1294c(gVar.a().a(), gVar.f14835a, gVar.f14836b);
        } catch (NumberFormatException unused) {
            AbstractC1346c.z("WebvttCueParser", "Skipping cue with bad header: " + matcher.group());
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void e(String str, g gVar) {
        int i7;
        String strSubstring;
        int i10;
        int i11;
        Matcher matcher = f14845b.matcher(str);
        while (matcher.find()) {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            try {
                if ("line".equals(strGroup)) {
                    g(strGroup2, gVar);
                } else if ("align".equals(strGroup)) {
                    switch (strGroup2) {
                        case "center":
                        case "middle":
                            i7 = 2;
                            break;
                        case "end":
                            i7 = 3;
                            break;
                        case "left":
                            i7 = 4;
                            break;
                        case "right":
                            i7 = 5;
                            break;
                        case "start":
                            i7 = 1;
                            break;
                        default:
                            AbstractC1346c.z("WebvttCueParser", "Invalid alignment value: ".concat(strGroup2));
                            i7 = 2;
                            break;
                    }
                    gVar.d = i7;
                } else if ("position".equals(strGroup)) {
                    int iIndexOf = strGroup2.indexOf(44);
                    if (iIndexOf != -1) {
                        strSubstring = strGroup2.substring(iIndexOf + 1);
                        strSubstring.getClass();
                        switch (strSubstring) {
                            case "line-left":
                            case "start":
                                i10 = 0;
                                break;
                            case "center":
                            case "middle":
                                i10 = 1;
                                break;
                            case "line-right":
                            case "end":
                                i10 = 2;
                                break;
                            default:
                                AbstractC1346c.z("WebvttCueParser", "Invalid anchor value: ".concat(strSubstring));
                                i10 = Integer.MIN_VALUE;
                                break;
                        }
                        gVar.f14841i = i10;
                        strGroup2 = strGroup2.substring(0, iIndexOf);
                    }
                    gVar.f14840h = i.a(strGroup2);
                } else if ("size".equals(strGroup)) {
                    gVar.f14842j = i.a(strGroup2);
                } else if ("vertical".equals(strGroup)) {
                    if (strGroup2.equals("lr")) {
                        i11 = 2;
                    } else if (strGroup2.equals("rl")) {
                        i11 = 1;
                    } else {
                        AbstractC1346c.z("WebvttCueParser", "Invalid 'vertical' value: ".concat(strGroup2));
                        i11 = Integer.MIN_VALUE;
                    }
                    gVar.f14843k = i11;
                } else {
                    AbstractC1346c.z("WebvttCueParser", "Unknown cue setting " + strGroup + ":" + strGroup2);
                }
            } catch (NumberFormatException unused) {
                AbstractC1346c.z("WebvttCueParser", "Skipping bad cue setting: " + matcher.group());
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:70:0x00fb. Please report as an issue. */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.text.SpannedString f(java.lang.String r16, java.lang.String r17, java.util.List r18) {
        /*
            Method dump skipped, instruction units count: 654
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g3.h.f(java.lang.String, java.lang.String, java.util.List):android.text.SpannedString");
    }

    public static void g(String str, g gVar) {
        String strSubstring;
        int i7 = 2;
        int iIndexOf = str.indexOf(44);
        if (iIndexOf != -1) {
            strSubstring = str.substring(iIndexOf + 1);
            strSubstring.getClass();
            switch (strSubstring) {
                case "center":
                case "middle":
                    i7 = 1;
                    break;
                case "end":
                    break;
                case "start":
                    i7 = 0;
                    break;
                default:
                    AbstractC1346c.z("WebvttCueParser", "Invalid anchor value: ".concat(strSubstring));
                    i7 = Integer.MIN_VALUE;
                    break;
            }
            gVar.f14839g = i7;
            str = str.substring(0, iIndexOf);
        }
        if (str.endsWith("%")) {
            gVar.f14837e = i.a(str);
            gVar.f14838f = 0;
        } else {
            gVar.f14837e = Integer.parseInt(str);
            gVar.f14838f = 1;
        }
    }
}
