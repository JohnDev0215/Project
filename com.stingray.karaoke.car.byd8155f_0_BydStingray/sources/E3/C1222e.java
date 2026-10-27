package e3;

import F1.C0254m;
import N0.q;
import Y2.k;
import Y2.l;
import a.AbstractC0904a;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: renamed from: e3.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1222e implements l {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f14397m = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f14398n = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    public static final Pattern o = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f14399p = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Pattern f14400q = Pattern.compile("^([-+]?\\d+\\.?\\d*?)% ([-+]?\\d+\\.?\\d*?)%$");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Pattern f14401r = Pattern.compile("^([-+]?\\d+\\.?\\d*?)px ([-+]?\\d+\\.?\\d*?)px$");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Pattern f14402s = Pattern.compile("^(\\d+) (\\d+)$");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C1221d f14403t = new C1221d(1, 1, 30.0f);
    public final XmlPullParserFactory l;

    public C1222e() {
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.l = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e10) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e10);
        }
    }

    public static C1224g a(C1224g c1224g) {
        return c1224g == null ? new C1224g() : c1224g;
    }

    public static boolean b(String str) {
        return str.equals("tt") || str.equals("head") || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals("region") || str.equals("metadata") || str.equals("image") || str.equals("data") || str.equals("information");
    }

    public static int c(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "cellResolution");
        if (attributeValue == null) {
            return 15;
        }
        Matcher matcher = f14402s.matcher(attributeValue);
        if (!matcher.matches()) {
            AbstractC1346c.z("TtmlParser", "Ignoring malformed cell resolution: ".concat(attributeValue));
            return 15;
        }
        boolean z6 = true;
        try {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            int i7 = Integer.parseInt(strGroup);
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            int i10 = Integer.parseInt(strGroup2);
            if (i7 == 0 || i10 == 0) {
                z6 = false;
            }
            AbstractC1346c.b("Invalid cell resolution " + i7 + " " + i10, z6);
            return i10;
        } catch (NumberFormatException unused) {
            AbstractC1346c.z("TtmlParser", "Ignoring malformed cell resolution: ".concat(attributeValue));
            return 15;
        }
    }

    public static void e(String str, C1224g c1224g) throws Y2.f {
        Matcher matcher;
        String strGroup;
        int i7 = AbstractC1343A.f15195a;
        String[] strArrSplit = str.split("\\s+", -1);
        int length = strArrSplit.length;
        Pattern pattern = o;
        if (length == 1) {
            matcher = pattern.matcher(str);
        } else {
            if (strArrSplit.length != 2) {
                throw new Y2.f(M5.b.h(new StringBuilder("Invalid number of entries for fontSize: "), strArrSplit.length, "."));
            }
            matcher = pattern.matcher(strArrSplit[1]);
            AbstractC1346c.z("TtmlParser", "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        }
        if (!matcher.matches()) {
            throw new Y2.f(android.hardware.bydauto.ac.tsimpl.a.i("Invalid expression for fontSize: '", str, "'."));
        }
        strGroup = matcher.group(3);
        strGroup.getClass();
        switch (strGroup) {
            case "%":
                c1224g.f14419j = 3;
                break;
            case "em":
                c1224g.f14419j = 2;
                break;
            case "px":
                c1224g.f14419j = 1;
                break;
            default:
                throw new Y2.f(android.hardware.bydauto.ac.tsimpl.a.i("Invalid unit for fontSize: '", strGroup, "'."));
        }
        String strGroup2 = matcher.group(1);
        strGroup2.getClass();
        c1224g.f14420k = Float.parseFloat(strGroup2);
    }

    public static C1221d f(XmlPullParser xmlPullParser) {
        float f10;
        String attributeValue = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRate");
        int i7 = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        String attributeValue2 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "frameRateMultiplier");
        if (attributeValue2 != null) {
            int i10 = AbstractC1343A.f15195a;
            AbstractC1346c.b("frameRateMultiplier doesn't have 2 parts", attributeValue2.split(" ", -1).length == 2);
            f10 = Integer.parseInt(r2[0]) / Integer.parseInt(r2[1]);
        } else {
            f10 = 1.0f;
        }
        C1221d c1221d = f14403t;
        int i11 = c1221d.f14396b;
        String attributeValue3 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "subFrameRate");
        if (attributeValue3 != null) {
            i11 = Integer.parseInt(attributeValue3);
        }
        String attributeValue4 = xmlPullParser.getAttributeValue("http://www.w3.org/ns/ttml#parameter", "tickRate");
        return new C1221d(i11, attributeValue4 != null ? Integer.parseInt(attributeValue4) : c1221d.c, i7 * f10);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:104:0x024d A[LOOP:0: B:3:0x000a->B:104:0x024d, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x024c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void g(org.xmlpull.v1.XmlPullParser r20, java.util.HashMap r21, int r22, F1.C0254m r23, java.util.HashMap r24, java.util.HashMap r25) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 618
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e3.C1222e.g(org.xmlpull.v1.XmlPullParser, java.util.HashMap, int, F1.m, java.util.HashMap, java.util.HashMap):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x00fb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static e3.C1220c i(org.xmlpull.v1.XmlPullParser r21, e3.C1220c r22, java.util.HashMap r23, e3.C1221d r24) throws Y2.f {
        /*
            Method dump skipped, instruction units count: 314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e3.C1222e.i(org.xmlpull.v1.XmlPullParser, e3.c, java.util.HashMap, e3.d):e3.c");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:67:0x00eb. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0302  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x04e0  */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0103  */
    /* JADX WARN: Type inference failed for: r11v120, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r12v14, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v10, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v11 */
    /* JADX WARN: Type inference failed for: r16v2, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v3, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v7 */
    /* JADX WARN: Type inference failed for: r16v8, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r16v9, types: [android.text.Layout$Alignment] */
    /* JADX WARN: Type inference failed for: r1v50, types: [e3.b] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static e3.C1224g j(org.xmlpull.v1.XmlPullParser r19, e3.C1224g r20) {
        /*
            Method dump skipped, instruction units count: 1592
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: e3.C1222e.j(org.xmlpull.v1.XmlPullParser, e3.g):e3.g");
    }

    public static long k(String str, C1221d c1221d) throws Y2.f {
        double d;
        double d10;
        double d11;
        Matcher matcher = f14397m.matcher(str);
        if (matcher.matches()) {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            double d12 = Long.parseLong(strGroup) * 3600;
            matcher.group(2).getClass();
            double d13 = d12 + (Long.parseLong(r13) * 60);
            matcher.group(3).getClass();
            double d14 = d13 + Long.parseLong(r13);
            String strGroup2 = matcher.group(4);
            return (long) ((d14 + (strGroup2 != null ? Double.parseDouble(strGroup2) : 0.0d) + (matcher.group(5) != null ? Long.parseLong(r13) / c1221d.f14395a : 0.0d) + (matcher.group(6) != null ? (Long.parseLong(r13) / ((double) c1221d.f14396b)) / ((double) c1221d.f14395a) : 0.0d)) * 1000000.0d);
        }
        Matcher matcher2 = f14398n.matcher(str);
        if (!matcher2.matches()) {
            throw new Y2.f(M5.b.g("Malformed time expression: ", str));
        }
        String strGroup3 = matcher2.group(1);
        strGroup3.getClass();
        d = Double.parseDouble(strGroup3);
        String strGroup4 = matcher2.group(2);
        strGroup4.getClass();
        switch (strGroup4) {
            case "f":
                d10 = c1221d.f14395a;
                d /= d10;
                return (long) (d * 1000000.0d);
            case "h":
                d11 = 3600.0d;
                break;
            case "m":
                d11 = 60.0d;
                break;
            case "t":
                d10 = c1221d.c;
                d /= d10;
                return (long) (d * 1000000.0d);
            case "ms":
                d10 = 1000.0d;
                d /= d10;
                return (long) (d * 1000000.0d);
            default:
                return (long) (d * 1000000.0d);
        }
        d *= d11;
        return (long) (d * 1000000.0d);
    }

    public static C0254m l(XmlPullParser xmlPullParser) {
        String strS = AbstractC1346c.s(xmlPullParser, "extent");
        if (strS == null) {
            return null;
        }
        Matcher matcher = f14401r.matcher(strS);
        if (!matcher.matches()) {
            AbstractC1346c.z("TtmlParser", "Ignoring non-pixel tts extent: ".concat(strS));
            return null;
        }
        try {
            String strGroup = matcher.group(1);
            strGroup.getClass();
            int i7 = Integer.parseInt(strGroup);
            String strGroup2 = matcher.group(2);
            strGroup2.getClass();
            return new C0254m(i7, Integer.parseInt(strGroup2), 3);
        } catch (NumberFormatException unused) {
            AbstractC1346c.z("TtmlParser", "Ignoring malformed tts extent: ".concat(strS));
            return null;
        }
    }

    @Override // Y2.l
    public final void h(byte[] bArr, int i7, int i10, k kVar, h2.g gVar) {
        AbstractC0904a.T(x(i7, i10, bArr), kVar, gVar);
    }

    @Override // Y2.l
    public final int s() {
        return 1;
    }

    @Override // Y2.l
    public final Y2.d x(int i7, int i10, byte[] bArr) {
        C1221d c1221d;
        try {
            XmlPullParser xmlPullParserNewPullParser = this.l.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            map2.put("", new C1223f("", -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE));
            C0254m c0254mL = null;
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(bArr, i7, i10), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            C1221d c1221dF = f14403t;
            int iC = 15;
            q qVar = null;
            int i11 = 0;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                C1220c c1220c = (C1220c) arrayDeque.peek();
                if (i11 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if ("tt".equals(name)) {
                            c1221dF = f(xmlPullParserNewPullParser);
                            iC = c(xmlPullParserNewPullParser);
                            c0254mL = l(xmlPullParserNewPullParser);
                        }
                        C0254m c0254m = c0254mL;
                        C1221d c1221d2 = c1221dF;
                        int i12 = iC;
                        if (b(name)) {
                            if ("head".equals(name)) {
                                c1221d = c1221d2;
                                g(xmlPullParserNewPullParser, map, i12, c0254m, map2, map3);
                            } else {
                                c1221d = c1221d2;
                                try {
                                    C1220c c1220cI = i(xmlPullParserNewPullParser, c1220c, map2, c1221d);
                                    arrayDeque.push(c1220cI);
                                    if (c1220c != null) {
                                        if (c1220c.f14394m == null) {
                                            c1220c.f14394m = new ArrayList();
                                        }
                                        c1220c.f14394m.add(c1220cI);
                                    }
                                } catch (Y2.f e10) {
                                    AbstractC1346c.A("TtmlParser", "Suppressing parser error", e10);
                                    i11++;
                                }
                            }
                            c1221dF = c1221d;
                        } else {
                            AbstractC1346c.t("TtmlParser", "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                            i11++;
                            c1221dF = c1221d2;
                        }
                        c0254mL = c0254m;
                        iC = i12;
                    } else if (eventType == 4) {
                        c1220c.getClass();
                        C1220c c1220cA = C1220c.a(xmlPullParserNewPullParser.getText());
                        if (c1220c.f14394m == null) {
                            c1220c.f14394m = new ArrayList();
                        }
                        c1220c.f14394m.add(c1220cA);
                    } else if (eventType == 3) {
                        if (xmlPullParserNewPullParser.getName().equals("tt")) {
                            C1220c c1220c2 = (C1220c) arrayDeque.peek();
                            c1220c2.getClass();
                            qVar = new q(c1220c2, map, map2, map3);
                        }
                        arrayDeque.pop();
                    }
                } else if (eventType == 2) {
                    i11++;
                } else if (eventType == 3) {
                    i11--;
                }
                xmlPullParserNewPullParser.next();
            }
            qVar.getClass();
            return qVar;
        } catch (IOException e11) {
            throw new IllegalStateException("Unexpected error when reading input.", e11);
        } catch (XmlPullParserException e12) {
            throw new IllegalStateException("Unable to decode source", e12);
        }
    }
}
