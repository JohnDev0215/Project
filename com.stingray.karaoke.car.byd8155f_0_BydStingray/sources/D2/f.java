package D2;

import d5.AbstractC1074C;
import d5.AbstractC1095q;
import d5.C1078G;
import d5.I;
import d5.d0;
import e2.C1207o;
import e2.C1208p;
import e2.L;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h2.t;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class f implements a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d0 f1166a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1167b;

    public f(int i7, d0 d0Var) {
        this.f1167b = i7;
        this.f1166a = d0Var;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static f c(int i7, t tVar) {
        String str;
        a cVar;
        int i10;
        int i11 = 4;
        AbstractC1095q.e(4, "initialCapacity");
        Object[] objArrCopyOf = new Object[4];
        int i12 = tVar.c;
        int i13 = 0;
        int i14 = -2;
        int i15 = 0;
        while (tVar.a() > 8) {
            int iJ = tVar.j();
            int iJ2 = tVar.f15242b + tVar.j();
            tVar.G(iJ2);
            if (iJ != 1414744396) {
                g gVar = null;
                switch (iJ) {
                    case 1718776947:
                        if (i14 == 2) {
                            tVar.I(i11);
                            int iJ3 = tVar.j();
                            int iJ4 = tVar.j();
                            tVar.I(i11);
                            int iJ5 = tVar.j();
                            switch (iJ5) {
                                case 808802372:
                                case 877677894:
                                case 1145656883:
                                case 1145656920:
                                case 1482049860:
                                case 1684633208:
                                case 2021026148:
                                    str = "video/mp4v-es";
                                    break;
                                case 826496577:
                                case 828601953:
                                case 875967048:
                                    str = "video/avc";
                                    break;
                                case 842289229:
                                    str = "video/mp42";
                                    break;
                                case 859066445:
                                    str = "video/mp43";
                                    break;
                                case 1196444237:
                                case 1735420525:
                                    str = "video/mjpeg";
                                    break;
                                default:
                                    str = null;
                                    break;
                            }
                            if (str == null) {
                                AbstractC1347d.p("Ignoring track with unsupported compression ", iJ5, "StreamFormatChunk");
                            } else {
                                C1207o c1207o = new C1207o();
                                c1207o.f14252t = iJ3;
                                c1207o.f14253u = iJ4;
                                c1207o.f14246m = L.l(str);
                                gVar = new g(new C1208p(c1207o));
                            }
                        } else if (i14 == 1) {
                            int iO = tVar.o();
                            String str2 = iO != 1 ? iO != 85 ? iO != 255 ? iO != 8192 ? iO != 8193 ? null : "audio/vnd.dts" : "audio/ac3" : "audio/mp4a-latm" : "audio/mpeg" : "audio/raw";
                            if (str2 == null) {
                                AbstractC1347d.p("Ignoring track with unsupported format tag ", iO, "StreamFormatChunk");
                            } else {
                                int iO2 = tVar.o();
                                int iJ6 = tVar.j();
                                tVar.I(6);
                                int iU = AbstractC1343A.u(tVar.o());
                                int iO3 = tVar.a() > 0 ? tVar.o() : i13;
                                byte[] bArr = new byte[iO3];
                                tVar.f(i13, iO3, bArr);
                                C1207o c1207o2 = new C1207o();
                                c1207o2.f14246m = L.l(str2);
                                c1207o2.B = iO2;
                                c1207o2.f14228C = iJ6;
                                if ("audio/raw".equals(str2) && iU != 0) {
                                    c1207o2.f14229D = iU;
                                }
                                if ("audio/mp4a-latm".equals(str2) && iO3 > 0) {
                                    c1207o2.f14248p = I.B(bArr);
                                }
                                gVar = new g(new C1208p(c1207o2));
                            }
                        } else {
                            AbstractC1346c.z("StreamFormatChunk", "Ignoring strf box for unsupported track type: " + AbstractC1343A.z(i14));
                        }
                        cVar = gVar;
                        break;
                    case 1751742049:
                        int iJ7 = tVar.j();
                        tVar.I(8);
                        int iJ8 = tVar.j();
                        int iJ9 = tVar.j();
                        tVar.I(i11);
                        tVar.j();
                        tVar.I(12);
                        cVar = new c(iJ7, iJ8, iJ9);
                        break;
                    case 1752331379:
                        int iJ10 = tVar.j();
                        tVar.I(12);
                        tVar.j();
                        int iJ11 = tVar.j();
                        int iJ12 = tVar.j();
                        tVar.I(i11);
                        int iJ13 = tVar.j();
                        int iJ14 = tVar.j();
                        tVar.I(8);
                        cVar = new d(iJ10, iJ11, iJ12, iJ13, iJ14);
                        break;
                    case 1852994675:
                        cVar = new h(tVar.t(tVar.a(), StandardCharsets.UTF_8));
                        break;
                    default:
                        cVar = gVar;
                        break;
                }
            } else {
                cVar = c(tVar.j(), tVar);
            }
            if (cVar != null) {
                if (cVar.b() == 1752331379) {
                    int i16 = ((d) cVar).f1153a;
                    if (i16 == 1935960438) {
                        i14 = 2;
                    } else if (i16 != 1935963489) {
                        if (i16 != 1937012852) {
                            AbstractC1346c.z("AviStreamHeaderChunk", "Found unsupported streamType fourCC: " + Integer.toHexString(i16));
                            i10 = -1;
                        } else {
                            i10 = 3;
                        }
                        i14 = i10;
                    } else {
                        i14 = 1;
                    }
                }
                int i17 = i15 + 1;
                int iG = AbstractC1074C.g(objArrCopyOf.length, i17);
                if (iG > objArrCopyOf.length) {
                    objArrCopyOf = Arrays.copyOf(objArrCopyOf, iG);
                }
                objArrCopyOf[i15] = cVar;
                i15 = i17;
            }
            tVar.H(iJ2);
            tVar.G(i12);
            i11 = 4;
            i13 = 0;
        }
        return new f(i7, I.t(i15, objArrCopyOf));
    }

    public final a a(Class cls) {
        C1078G c1078gListIterator = this.f1166a.listIterator(0);
        while (c1078gListIterator.hasNext()) {
            a aVar = (a) c1078gListIterator.next();
            if (aVar.getClass() == cls) {
                return aVar;
            }
        }
        return null;
    }

    @Override // D2.a
    public final int b() {
        return this.f1167b;
    }
}
