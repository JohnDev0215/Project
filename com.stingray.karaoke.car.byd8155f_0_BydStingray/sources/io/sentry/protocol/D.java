package io.sentry.protocol;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import io.sentry.C1482l0;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1452b0;
import io.sentry.K;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC1452b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16655a;

    public /* synthetic */ D(int i7) {
        this.f16655a = i7;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static io.sentry.rrweb.a b(C1482l0 c1482l0, K k10) throws IOException {
        int i7 = 0;
        int i10 = 4;
        c1482l0.g();
        io.sentry.rrweb.a aVar = new io.sentry.rrweb.a();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL = c1482l0.L();
            strL.getClass();
            if (strL.equals("data")) {
                c1482l0.g();
                ConcurrentHashMap concurrentHashMap = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strL2 = c1482l0.L();
                    strL2.getClass();
                    if (strL2.equals("payload")) {
                        c1482l0.g();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                            String strL3 = c1482l0.L();
                            strL3.getClass();
                            int i11 = -1;
                            switch (strL3.hashCode()) {
                                case 3076010:
                                    if (strL3.equals("data")) {
                                        i11 = i7;
                                    }
                                    break;
                                case 3575610:
                                    if (strL3.equals("type")) {
                                        i11 = 1;
                                    }
                                    break;
                                case 50511102:
                                    if (strL3.equals("category")) {
                                        i11 = 2;
                                    }
                                    break;
                                case 55126294:
                                    if (strL3.equals("timestamp")) {
                                        i11 = 3;
                                    }
                                    break;
                                case 102865796:
                                    if (strL3.equals("level")) {
                                        i11 = 4;
                                    }
                                    break;
                                case 954925063:
                                    if (strL3.equals("message")) {
                                        i11 = 5;
                                    }
                                    break;
                            }
                            switch (i11) {
                                case 0:
                                    ConcurrentHashMap concurrentHashMapP = h6.r.p((Map) c1482l0.N());
                                    if (concurrentHashMapP != null) {
                                        aVar.f16832t = concurrentHashMapP;
                                    }
                                    break;
                                case 1:
                                    aVar.f16828p = c1482l0.Q();
                                    break;
                                case 2:
                                    aVar.f16829q = c1482l0.Q();
                                    break;
                                case 3:
                                    aVar.o = c1482l0.m();
                                    break;
                                case 4:
                                    try {
                                        aVar.f16831s = EnumC1495p1.valueOf(c1482l0.P().toUpperCase(Locale.ROOT));
                                    } catch (Exception e10) {
                                        k10.p(EnumC1495p1.DEBUG, e10, "Error when deserializing SentryLevel", new Object[i7]);
                                    }
                                    break;
                                case 5:
                                    aVar.f16830r = c1482l0.Q();
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    c1482l0.R(k10, concurrentHashMap2, strL3);
                                    break;
                            }
                            i7 = 0;
                        }
                        aVar.f16834v = concurrentHashMap2;
                        c1482l0.i();
                    } else if (strL2.equals("tag")) {
                        String strQ = c1482l0.Q();
                        if (strQ == null) {
                            strQ = "";
                        }
                        aVar.f16827n = strQ;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        c1482l0.R(k10, concurrentHashMap, strL2);
                    }
                    i7 = 0;
                }
                aVar.f16835w = concurrentHashMap;
                c1482l0.i();
            } else if (strL.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(i10));
                h6.e.q(cVar, "");
                aVar.l = cVar;
            } else if (strL.equals("timestamp")) {
                aVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL);
            }
            i7 = 0;
        }
        aVar.f16833u = map;
        c1482l0.i();
        return aVar;
    }

    public static io.sentry.rrweb.g c(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        int i7 = 5;
        int i10 = 4;
        c1482l0.g();
        io.sentry.rrweb.g gVar = new io.sentry.rrweb.g();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL2 = c1482l0.L();
            strL2.getClass();
            if (strL2.equals("data")) {
                c1482l0.g();
                HashMap map2 = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    strL = c1482l0.L();
                    strL.getClass();
                    switch (strL) {
                        case "x":
                            gVar.f16839q = c1482l0.q();
                            break;
                        case "y":
                            gVar.f16840r = c1482l0.q();
                            break;
                        case "id":
                            gVar.f16838p = c1482l0.y();
                            break;
                        case "type":
                            gVar.o = (io.sentry.rrweb.f) c1482l0.O(k10, new D(7));
                            break;
                        case "pointerType":
                            gVar.f16841s = c1482l0.y();
                            break;
                        case "pointerId":
                            gVar.f16842t = c1482l0.y();
                            break;
                        default:
                            if (!strL.equals(BYDAutoMultimediaDevice.PARAM_SOURCE)) {
                                if (map2 == null) {
                                    map2 = new HashMap();
                                }
                                c1482l0.R(k10, map2, strL);
                                break;
                            } else {
                                io.sentry.rrweb.d dVar = (io.sentry.rrweb.d) c1482l0.O(k10, new D(i7));
                                h6.e.q(dVar, "");
                                gVar.f16837n = dVar;
                                break;
                            }
                            break;
                    }
                }
                gVar.f16844v = map2;
                c1482l0.i();
            } else if (strL2.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(i10));
                h6.e.q(cVar, "");
                gVar.l = cVar;
            } else if (strL2.equals("timestamp")) {
                gVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL2);
            }
        }
        gVar.f16843u = map;
        c1482l0.i();
        return gVar;
    }

    public static io.sentry.rrweb.i d(C1482l0 c1482l0, K k10) throws IOException {
        c1482l0.g();
        io.sentry.rrweb.i iVar = new io.sentry.rrweb.i();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL = c1482l0.L();
            strL.getClass();
            if (strL.equals("data")) {
                c1482l0.g();
                HashMap map2 = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strL2 = c1482l0.L();
                    strL2.getClass();
                    if (strL2.equals("pointerId")) {
                        iVar.o = c1482l0.y();
                    } else if (strL2.equals("positions")) {
                        iVar.f16848p = c1482l0.C(k10, new D(9));
                    } else if (strL2.equals(BYDAutoMultimediaDevice.PARAM_SOURCE)) {
                        io.sentry.rrweb.d dVar = (io.sentry.rrweb.d) c1482l0.O(k10, new D(5));
                        h6.e.q(dVar, "");
                        iVar.f16837n = dVar;
                    } else {
                        if (map2 == null) {
                            map2 = new HashMap();
                        }
                        c1482l0.R(k10, map2, strL2);
                    }
                }
                iVar.f16850r = map2;
                c1482l0.i();
            } else if (strL.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(4));
                h6.e.q(cVar, "");
                iVar.l = cVar;
            } else if (strL.equals("timestamp")) {
                iVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL);
            }
        }
        iVar.f16849q = map;
        c1482l0.i();
        return iVar;
    }

    public static io.sentry.rrweb.j e(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        c1482l0.g();
        io.sentry.rrweb.j jVar = new io.sentry.rrweb.j();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL2 = c1482l0.L();
            strL2.getClass();
            if (strL2.equals("data")) {
                c1482l0.g();
                AbstractMap concurrentHashMap = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    strL = c1482l0.L();
                    strL.getClass();
                    switch (strL) {
                        case "height":
                            Integer numZ = c1482l0.z();
                            jVar.o = numZ == null ? 0 : numZ.intValue();
                            break;
                        case "href":
                            String strQ = c1482l0.Q();
                            if (strQ == null) {
                                strQ = "";
                            }
                            jVar.f16851n = strQ;
                            break;
                        case "width":
                            Integer numZ2 = c1482l0.z();
                            jVar.f16852p = numZ2 == null ? 0 : numZ2.intValue();
                            break;
                        default:
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap();
                            }
                            c1482l0.R(k10, concurrentHashMap, strL);
                            break;
                    }
                }
                c1482l0.i();
            } else if (strL2.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(4));
                h6.e.q(cVar, "");
                jVar.l = cVar;
            } else if (strL2.equals("timestamp")) {
                jVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL2);
            }
        }
        jVar.f16853q = map;
        c1482l0.i();
        return jVar;
    }

    public static io.sentry.rrweb.k f(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        int i7 = 4;
        c1482l0.g();
        io.sentry.rrweb.k kVar = new io.sentry.rrweb.k();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL2 = c1482l0.L();
            strL2.getClass();
            if (strL2.equals("data")) {
                c1482l0.g();
                ConcurrentHashMap concurrentHashMap = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strL3 = c1482l0.L();
                    strL3.getClass();
                    if (strL3.equals("payload")) {
                        c1482l0.g();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                            strL = c1482l0.L();
                            strL.getClass();
                            switch (strL) {
                                case "description":
                                    kVar.f16855p = c1482l0.Q();
                                    break;
                                case "endTimestamp":
                                    kVar.f16857r = c1482l0.m();
                                    break;
                                case "startTimestamp":
                                    kVar.f16856q = c1482l0.m();
                                    break;
                                case "op":
                                    kVar.o = c1482l0.Q();
                                    break;
                                case "data":
                                    ConcurrentHashMap concurrentHashMapP = h6.r.p((Map) c1482l0.N());
                                    if (concurrentHashMapP == null) {
                                        break;
                                    } else {
                                        kVar.f16858s = concurrentHashMapP;
                                        break;
                                    }
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    c1482l0.R(k10, concurrentHashMap2, strL);
                                    break;
                            }
                        }
                        kVar.f16860u = concurrentHashMap2;
                        c1482l0.i();
                    } else if (strL3.equals("tag")) {
                        String strQ = c1482l0.Q();
                        if (strQ == null) {
                            strQ = "";
                        }
                        kVar.f16854n = strQ;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        c1482l0.R(k10, concurrentHashMap, strL3);
                    }
                }
                kVar.f16861v = concurrentHashMap;
                c1482l0.i();
            } else if (strL2.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(i7));
                h6.e.q(cVar, "");
                kVar.l = cVar;
            } else if (strL2.equals("timestamp")) {
                kVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL2);
            }
        }
        kVar.f16859t = map;
        c1482l0.i();
        return kVar;
    }

    public static io.sentry.rrweb.l g(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        int i7 = 4;
        c1482l0.g();
        io.sentry.rrweb.l lVar = new io.sentry.rrweb.l();
        HashMap map = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            String strL2 = c1482l0.L();
            strL2.getClass();
            if (strL2.equals("data")) {
                c1482l0.g();
                ConcurrentHashMap concurrentHashMap = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strL3 = c1482l0.L();
                    strL3.getClass();
                    if (strL3.equals("payload")) {
                        c1482l0.g();
                        ConcurrentHashMap concurrentHashMap2 = null;
                        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                            strL = c1482l0.L();
                            strL.getClass();
                            switch (strL) {
                                case "duration":
                                    lVar.f16866q = c1482l0.F();
                                    break;
                                case "segmentId":
                                    lVar.o = c1482l0.y();
                                    break;
                                case "height":
                                    Integer numZ = c1482l0.z();
                                    lVar.f16869t = numZ == null ? 0 : numZ.intValue();
                                    break;
                                case "container":
                                    String strQ = c1482l0.Q();
                                    if (strQ == null) {
                                        strQ = "";
                                    }
                                    lVar.f16868s = strQ;
                                    break;
                                case "frameCount":
                                    Integer numZ2 = c1482l0.z();
                                    lVar.f16871v = numZ2 == null ? 0 : numZ2.intValue();
                                    break;
                                case "top":
                                    Integer numZ3 = c1482l0.z();
                                    lVar.f16875z = numZ3 == null ? 0 : numZ3.intValue();
                                    break;
                                case "left":
                                    Integer numZ4 = c1482l0.z();
                                    lVar.f16874y = numZ4 == null ? 0 : numZ4.intValue();
                                    break;
                                case "size":
                                    Long lG = c1482l0.G();
                                    lVar.f16865p = lG == null ? 0L : lG.longValue();
                                    break;
                                case "width":
                                    Integer numZ5 = c1482l0.z();
                                    lVar.f16870u = numZ5 == null ? 0 : numZ5.intValue();
                                    break;
                                case "frameRate":
                                    Integer numZ6 = c1482l0.z();
                                    lVar.f16873x = numZ6 == null ? 0 : numZ6.intValue();
                                    break;
                                case "encoding":
                                    String strQ2 = c1482l0.Q();
                                    if (strQ2 == null) {
                                        strQ2 = "";
                                    }
                                    lVar.f16867r = strQ2;
                                    break;
                                case "frameRateType":
                                    String strQ3 = c1482l0.Q();
                                    if (strQ3 == null) {
                                        strQ3 = "";
                                    }
                                    lVar.f16872w = strQ3;
                                    break;
                                default:
                                    if (concurrentHashMap2 == null) {
                                        concurrentHashMap2 = new ConcurrentHashMap();
                                    }
                                    c1482l0.R(k10, concurrentHashMap2, strL);
                                    break;
                            }
                        }
                        lVar.B = concurrentHashMap2;
                        c1482l0.i();
                    } else if (strL3.equals("tag")) {
                        String strQ4 = c1482l0.Q();
                        if (strQ4 == null) {
                            strQ4 = "";
                        }
                        lVar.f16864n = strQ4;
                    } else {
                        if (concurrentHashMap == null) {
                            concurrentHashMap = new ConcurrentHashMap();
                        }
                        c1482l0.R(k10, concurrentHashMap, strL3);
                    }
                }
                lVar.f16863C = concurrentHashMap;
                c1482l0.i();
            } else if (strL2.equals("type")) {
                io.sentry.rrweb.c cVar = (io.sentry.rrweb.c) c1482l0.O(k10, new D(i7));
                h6.e.q(cVar, "");
                lVar.l = cVar;
            } else if (strL2.equals("timestamp")) {
                lVar.f16836m = c1482l0.F();
            } else {
                if (map == null) {
                    map = new HashMap();
                }
                c1482l0.R(k10, map, strL2);
            }
        }
        lVar.f16862A = map;
        c1482l0.i();
        return lVar;
    }

    @Override // io.sentry.InterfaceC1452b0
    public final Object a(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        String strL2;
        String strL3;
        String strL4;
        int i7 = 2;
        switch (this.f16655a) {
            case 0:
                c1482l0.g();
                E e10 = new E();
                ConcurrentHashMap concurrentHashMap = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    strL = c1482l0.L();
                    strL.getClass();
                    switch (strL) {
                        case "username":
                            e10.f16657n = c1482l0.Q();
                            break;
                        case "id":
                            e10.f16656m = c1482l0.Q();
                            break;
                        case "geo":
                            c1482l0.g();
                            C1502g c1502g = new C1502g();
                            ConcurrentHashMap concurrentHashMap2 = null;
                            while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                                strL2 = c1482l0.L();
                                strL2.getClass();
                                switch (strL2) {
                                    case "region":
                                        c1502g.f16722n = c1482l0.Q();
                                        break;
                                    case "city":
                                        c1502g.l = c1482l0.Q();
                                        break;
                                    case "country_code":
                                        c1502g.f16721m = c1482l0.Q();
                                        break;
                                    default:
                                        if (concurrentHashMap2 == null) {
                                            concurrentHashMap2 = new ConcurrentHashMap();
                                        }
                                        c1482l0.R(k10, concurrentHashMap2, strL2);
                                        break;
                                }
                            }
                            c1502g.o = concurrentHashMap2;
                            c1482l0.i();
                            e10.f16660r = c1502g;
                            break;
                        case "data":
                            e10.f16661s = h6.r.p((Map) c1482l0.N());
                            break;
                        case "name":
                            e10.f16659q = c1482l0.Q();
                            break;
                        case "email":
                            e10.l = c1482l0.Q();
                            break;
                        case "other":
                            ConcurrentHashMap concurrentHashMap3 = e10.f16661s;
                            if (concurrentHashMap3 != null && !concurrentHashMap3.isEmpty()) {
                                break;
                            } else {
                                e10.f16661s = h6.r.p((Map) c1482l0.N());
                                break;
                            }
                            break;
                        case "ip_address":
                            e10.f16658p = c1482l0.Q();
                            break;
                        case "segment":
                            e10.o = c1482l0.Q();
                            break;
                        default:
                            if (concurrentHashMap == null) {
                                concurrentHashMap = new ConcurrentHashMap();
                            }
                            c1482l0.R(k10, concurrentHashMap, strL);
                            break;
                    }
                }
                e10.f16662t = concurrentHashMap;
                c1482l0.i();
                return e10;
            case 1:
                c1482l0.g();
                String strQ = null;
                ArrayList arrayListC = null;
                HashMap map = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    String strL5 = c1482l0.L();
                    strL5.getClass();
                    if (strL5.equals("rendering_system")) {
                        strQ = c1482l0.Q();
                    } else if (strL5.equals("windows")) {
                        arrayListC = c1482l0.C(k10, new D(i7));
                    } else {
                        if (map == null) {
                            map = new HashMap();
                        }
                        c1482l0.R(k10, map, strL5);
                    }
                }
                c1482l0.i();
                F f10 = new F(strQ, arrayListC);
                f10.f16664n = map;
                return f10;
            case 2:
                G g7 = new G();
                c1482l0.g();
                HashMap map2 = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    strL3 = c1482l0.L();
                    strL3.getClass();
                    switch (strL3) {
                        case "rendering_system":
                            g7.l = c1482l0.Q();
                            break;
                        case "identifier":
                            g7.f16666n = c1482l0.Q();
                            break;
                        case "height":
                            g7.f16668q = c1482l0.o();
                            break;
                        case "x":
                            g7.f16669r = c1482l0.o();
                            break;
                        case "y":
                            g7.f16670s = c1482l0.o();
                            break;
                        case "tag":
                            g7.o = c1482l0.Q();
                            break;
                        case "type":
                            g7.f16665m = c1482l0.Q();
                            break;
                        case "alpha":
                            g7.f16672u = c1482l0.o();
                            break;
                        case "width":
                            g7.f16667p = c1482l0.o();
                            break;
                        case "children":
                            g7.f16673v = c1482l0.C(k10, this);
                            break;
                        case "visibility":
                            g7.f16671t = c1482l0.Q();
                            break;
                        default:
                            if (map2 == null) {
                                map2 = new HashMap();
                            }
                            c1482l0.R(k10, map2, strL3);
                            break;
                    }
                }
                c1482l0.i();
                g7.f16674w = map2;
                return g7;
            case 3:
                return b(c1482l0, k10);
            case 4:
                return io.sentry.rrweb.c.values()[c1482l0.y()];
            case 5:
                return io.sentry.rrweb.d.values()[c1482l0.y()];
            case 6:
                return c(c1482l0, k10);
            case 7:
                return io.sentry.rrweb.f.values()[c1482l0.y()];
            case 8:
                return d(c1482l0, k10);
            case 9:
                c1482l0.g();
                io.sentry.rrweb.h hVar = new io.sentry.rrweb.h();
                HashMap map3 = null;
                while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                    strL4 = c1482l0.L();
                    strL4.getClass();
                    switch (strL4) {
                        case "x":
                            hVar.f16845m = c1482l0.q();
                            break;
                        case "y":
                            hVar.f16846n = c1482l0.q();
                            break;
                        case "id":
                            hVar.l = c1482l0.y();
                            break;
                        case "timeOffset":
                            hVar.o = c1482l0.F();
                            break;
                        default:
                            if (map3 == null) {
                                map3 = new HashMap();
                            }
                            c1482l0.R(k10, map3, strL4);
                            break;
                    }
                }
                hVar.f16847p = map3;
                c1482l0.i();
                return hVar;
            case 10:
                return e(c1482l0, k10);
            case 11:
                return f(c1482l0, k10);
            default:
                return g(c1482l0, k10);
        }
    }
}
