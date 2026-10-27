package J2;

import A.s1;
import d5.C1077F;
import d5.C1078G;
import d5.I;
import d5.d0;
import e2.M;
import h2.AbstractC1346c;
import java.io.IOException;
import java.io.StringReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f3062a = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f3063b = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    public static final String[] c = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};

    public static s1 a(String str) throws XmlPullParserException, IOException {
        long j3;
        XmlPullParser xmlPullParserNewPullParser = XmlPullParserFactory.newInstance().newPullParser();
        xmlPullParserNewPullParser.setInput(new StringReader(str));
        xmlPullParserNewPullParser.next();
        if (!AbstractC1346c.v(xmlPullParserNewPullParser, "x:xmpmeta")) {
            throw M.a(null, "Couldn't find xmp metadata");
        }
        C1078G c1078g = I.f13384m;
        d0 d0VarB = d0.f13415p;
        long j10 = -9223372036854775807L;
        loop0: do {
            xmlPullParserNewPullParser.next();
            if (AbstractC1346c.v(xmlPullParserNewPullParser, "rdf:Description")) {
                String[] strArr = f3062a;
                int i7 = 0;
                int i10 = 0;
                while (true) {
                    if (i10 >= 4) {
                        break loop0;
                    }
                    String strS = AbstractC1346c.s(xmlPullParserNewPullParser, strArr[i10]);
                    if (strS == null) {
                        i10++;
                    } else if (Integer.parseInt(strS) == 1) {
                        String[] strArr2 = f3063b;
                        int i11 = 0;
                        while (true) {
                            if (i11 >= 4) {
                                break;
                            }
                            String strS2 = AbstractC1346c.s(xmlPullParserNewPullParser, strArr2[i11]);
                            if (strS2 != null) {
                                j3 = Long.parseLong(strS2);
                                if (j3 == -1) {
                                    break;
                                }
                            } else {
                                i11++;
                            }
                        }
                        j3 = -9223372036854775807L;
                        String[] strArr3 = c;
                        while (true) {
                            if (i7 >= 2) {
                                C1078G c1078g2 = I.f13384m;
                                d0VarB = d0.f13415p;
                                break;
                            }
                            String strS3 = AbstractC1346c.s(xmlPullParserNewPullParser, strArr3[i7]);
                            if (strS3 != null) {
                                d0VarB = I.C(new c(0L, 0L, "image/jpeg"), new c(Long.parseLong(strS3), 0L, "video/mp4"));
                                break;
                            }
                            i7++;
                        }
                        j10 = j3;
                    }
                }
                return null;
            }
            if (AbstractC1346c.v(xmlPullParserNewPullParser, "Container:Directory")) {
                d0VarB = b(xmlPullParserNewPullParser, "Container", "Item");
            } else if (AbstractC1346c.v(xmlPullParserNewPullParser, "GContainer:Directory")) {
                d0VarB = b(xmlPullParserNewPullParser, "GContainer", "GContainerItem");
            }
        } while (!AbstractC1346c.u(xmlPullParserNewPullParser, "x:xmpmeta"));
        if (d0VarB.isEmpty()) {
            return null;
        }
        return new s1(1, j10, d0VarB);
    }

    public static d0 b(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        C1077F c1077fU = I.u();
        String strConcat = str.concat(":Item");
        String strConcat2 = str.concat(":Directory");
        do {
            xmlPullParser.next();
            if (AbstractC1346c.v(xmlPullParser, strConcat)) {
                String strConcat3 = str2.concat(":Mime");
                String strConcat4 = str2.concat(":Semantic");
                String strConcat5 = str2.concat(":Length");
                String strConcat6 = str2.concat(":Padding");
                String strS = AbstractC1346c.s(xmlPullParser, strConcat3);
                String strS2 = AbstractC1346c.s(xmlPullParser, strConcat4);
                String strS3 = AbstractC1346c.s(xmlPullParser, strConcat5);
                String strS4 = AbstractC1346c.s(xmlPullParser, strConcat6);
                if (strS == null || strS2 == null) {
                    return d0.f13415p;
                }
                c1077fU.a(new c(strS3 != null ? Long.parseLong(strS3) : 0L, strS4 != null ? Long.parseLong(strS4) : 0L, strS));
            }
        } while (!AbstractC1346c.u(xmlPullParser, strConcat2));
        return c1077fU.h();
    }
}
