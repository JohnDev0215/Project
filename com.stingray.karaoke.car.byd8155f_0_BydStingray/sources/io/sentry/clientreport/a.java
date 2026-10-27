package io.sentry.clientreport;

import h6.r;
import io.sentry.C1454c;
import io.sentry.C1482l0;
import io.sentry.EnumC1495p1;
import io.sentry.InterfaceC1452b0;
import io.sentry.K;
import io.sentry.protocol.C1496a;
import io.sentry.protocol.C1497b;
import io.sentry.protocol.C1498c;
import io.sentry.protocol.C1501f;
import io.sentry.protocol.EnumC1500e;
import io.sentry.protocol.h;
import io.sentry.protocol.m;
import io.sentry.protocol.p;
import io.sentry.protocol.v;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class a implements InterfaceC1452b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16496a;

    public /* synthetic */ a(int i7) {
        this.f16496a = i7;
    }

    public static C1496a b(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        c1482l0.g();
        C1496a c1496a = new C1496a();
        ConcurrentHashMap concurrentHashMap = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            strL = c1482l0.L();
            strL.getClass();
            switch (strL) {
                case "device_app_hash":
                    c1496a.f16676n = c1482l0.Q();
                    break;
                case "start_type":
                    c1496a.f16682u = c1482l0.Q();
                    break;
                case "view_names":
                    List list = (List) c1482l0.N();
                    if (list == null) {
                        break;
                    } else {
                        c1496a.f16681t = list;
                        break;
                    }
                    break;
                case "app_version":
                    c1496a.f16678q = c1482l0.Q();
                    break;
                case "in_foreground":
                    c1496a.f16683v = c1482l0.j();
                    break;
                case "build_type":
                    c1496a.o = c1482l0.Q();
                    break;
                case "app_identifier":
                    c1496a.l = c1482l0.Q();
                    break;
                case "app_start_time":
                    c1496a.f16675m = c1482l0.k(k10);
                    break;
                case "permissions":
                    c1496a.f16680s = r.p((Map) c1482l0.N());
                    break;
                case "app_name":
                    c1496a.f16677p = c1482l0.Q();
                    break;
                case "app_build":
                    c1496a.f16679r = c1482l0.Q();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    c1482l0.R(k10, concurrentHashMap, strL);
                    break;
            }
        }
        c1496a.f16684w = concurrentHashMap;
        c1482l0.i();
        return c1496a;
    }

    public static C1498c c(C1482l0 c1482l0, K k10) {
        String strL;
        String strL2;
        String strL3;
        C1498c c1498c = new C1498c();
        c1482l0.g();
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            strL = c1482l0.L();
            strL.getClass();
            switch (strL) {
                case "device":
                    c1498c.put("device", d(c1482l0, k10));
                    break;
                case "response":
                    c1482l0.g();
                    p pVar = new p();
                    ConcurrentHashMap concurrentHashMap = null;
                    while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                        strL2 = c1482l0.L();
                        strL2.getClass();
                        switch (strL2) {
                            case "status_code":
                                pVar.f16760n = c1482l0.z();
                                break;
                            case "data":
                                pVar.f16761p = c1482l0.N();
                                break;
                            case "headers":
                                Map map = (Map) c1482l0.N();
                                if (map == null) {
                                    break;
                                } else {
                                    pVar.f16759m = r.p(map);
                                    break;
                                }
                                break;
                            case "cookies":
                                pVar.l = c1482l0.Q();
                                break;
                            case "body_size":
                                pVar.o = c1482l0.G();
                                break;
                            default:
                                if (concurrentHashMap == null) {
                                    concurrentHashMap = new ConcurrentHashMap();
                                }
                                c1482l0.R(k10, concurrentHashMap, strL2);
                                break;
                        }
                    }
                    pVar.f16762q = concurrentHashMap;
                    c1482l0.i();
                    c1498c.d(pVar);
                    break;
                case "os":
                    c1498c.put("os", f(c1482l0, k10));
                    break;
                case "app":
                    c1498c.c(b(c1482l0, k10));
                    break;
                case "gpu":
                    c1498c.put("gpu", e(c1482l0, k10));
                    break;
                case "trace":
                    c1498c.e(C1454c.b(c1482l0, k10));
                    break;
                case "browser":
                    c1482l0.g();
                    C1497b c1497b = new C1497b();
                    ConcurrentHashMap concurrentHashMap2 = null;
                    while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                        String strL4 = c1482l0.L();
                        strL4.getClass();
                        if (strL4.equals("name")) {
                            c1497b.l = c1482l0.Q();
                        } else if (strL4.equals("version")) {
                            c1497b.f16685m = c1482l0.Q();
                        } else {
                            if (concurrentHashMap2 == null) {
                                concurrentHashMap2 = new ConcurrentHashMap();
                            }
                            c1482l0.R(k10, concurrentHashMap2, strL4);
                        }
                    }
                    c1497b.f16686n = concurrentHashMap2;
                    c1482l0.i();
                    c1498c.put("browser", c1497b);
                    break;
                case "runtime":
                    c1482l0.g();
                    v vVar = new v();
                    ConcurrentHashMap concurrentHashMap3 = null;
                    while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
                        strL3 = c1482l0.L();
                        strL3.getClass();
                        switch (strL3) {
                            case "raw_description":
                                vVar.f16778n = c1482l0.Q();
                                break;
                            case "name":
                                vVar.l = c1482l0.Q();
                                break;
                            case "version":
                                vVar.f16777m = c1482l0.Q();
                                break;
                            default:
                                if (concurrentHashMap3 == null) {
                                    concurrentHashMap3 = new ConcurrentHashMap();
                                }
                                c1482l0.R(k10, concurrentHashMap3, strL3);
                                break;
                        }
                    }
                    vVar.o = concurrentHashMap3;
                    c1482l0.i();
                    c1498c.put("runtime", vVar);
                    break;
                default:
                    Object objN = c1482l0.N();
                    if (objN == null) {
                        break;
                    } else {
                        c1498c.put(strL, objN);
                        break;
                    }
                    break;
            }
        }
        c1482l0.i();
        return c1498c;
    }

    public static C1501f d(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        TimeZone timeZone;
        int i7 = 10;
        c1482l0.g();
        C1501f c1501f = new C1501f();
        ConcurrentHashMap concurrentHashMap = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            strL = c1482l0.L();
            strL.getClass();
            switch (strL) {
                case "timezone":
                    switch (c1482l0.l) {
                        case 0:
                            io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) c1482l0.f16608m;
                            if (aVar.P() != io.sentry.vendor.gson.stream.b.NULL) {
                                try {
                                    timeZone = TimeZone.getTimeZone(aVar.N());
                                } catch (Exception e10) {
                                    k10.s(EnumC1495p1.ERROR, "Error when deserializing TimeZone", e10);
                                }
                            } else {
                                aVar.L();
                            }
                            break;
                        default:
                            String str = (String) c1482l0.S();
                            timeZone = str == null ? null : TimeZone.getTimeZone(str);
                            break;
                    }
                    c1501f.f16698K = timeZone;
                    break;
                case "boot_time":
                    if (c1482l0.T() == io.sentry.vendor.gson.stream.b.STRING) {
                        c1501f.f16697J = c1482l0.k(k10);
                        break;
                    } else {
                        break;
                    }
                    break;
                case "simulator":
                    c1501f.f16717w = c1482l0.j();
                    break;
                case "manufacturer":
                    c1501f.f16708m = c1482l0.Q();
                    break;
                case "language":
                    c1501f.f16700M = c1482l0.Q();
                    break;
                case "processor_count":
                    c1501f.f16704Q = c1482l0.z();
                    break;
                case "orientation":
                    c1501f.f16716v = (EnumC1500e) c1482l0.O(k10, new a(i7));
                    break;
                case "battery_temperature":
                    c1501f.f16703P = c1482l0.v();
                    break;
                case "family":
                    c1501f.o = c1482l0.Q();
                    break;
                case "locale":
                    c1501f.f16701N = c1482l0.Q();
                    break;
                case "online":
                    c1501f.f16715u = c1482l0.j();
                    break;
                case "battery_level":
                    c1501f.f16713s = c1482l0.v();
                    break;
                case "model_id":
                    c1501f.f16711q = c1482l0.Q();
                    break;
                case "screen_density":
                    c1501f.f16695H = c1482l0.v();
                    break;
                case "screen_dpi":
                    c1501f.f16696I = c1482l0.z();
                    break;
                case "free_memory":
                    c1501f.f16719y = c1482l0.G();
                    break;
                case "id":
                    c1501f.f16699L = c1482l0.Q();
                    break;
                case "name":
                    c1501f.l = c1482l0.Q();
                    break;
                case "low_memory":
                    c1501f.f16689A = c1482l0.j();
                    break;
                case "archs":
                    List list = (List) c1482l0.N();
                    if (list != null) {
                        String[] strArr = new String[list.size()];
                        list.toArray(strArr);
                        c1501f.f16712r = strArr;
                        break;
                    } else {
                        break;
                    }
                    break;
                case "brand":
                    c1501f.f16709n = c1482l0.Q();
                    break;
                case "model":
                    c1501f.f16710p = c1482l0.Q();
                    break;
                case "cpu_description":
                    c1501f.f16706S = c1482l0.Q();
                    break;
                case "processor_frequency":
                    c1501f.f16705R = c1482l0.o();
                    break;
                case "connection_type":
                    c1501f.f16702O = c1482l0.Q();
                    break;
                case "screen_width_pixels":
                    c1501f.f16693F = c1482l0.z();
                    break;
                case "external_storage_size":
                    c1501f.f16691D = c1482l0.G();
                    break;
                case "storage_size":
                    c1501f.B = c1482l0.G();
                    break;
                case "usable_memory":
                    c1501f.f16720z = c1482l0.G();
                    break;
                case "memory_size":
                    c1501f.f16718x = c1482l0.G();
                    break;
                case "charging":
                    c1501f.f16714t = c1482l0.j();
                    break;
                case "external_free_storage":
                    c1501f.f16692E = c1482l0.G();
                    break;
                case "free_storage":
                    c1501f.f16690C = c1482l0.G();
                    break;
                case "screen_height_pixels":
                    c1501f.f16694G = c1482l0.z();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    c1482l0.R(k10, concurrentHashMap, strL);
                    break;
            }
        }
        c1501f.f16707T = concurrentHashMap;
        c1482l0.i();
        return c1501f;
    }

    public static h e(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        c1482l0.g();
        h hVar = new h();
        ConcurrentHashMap concurrentHashMap = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            strL = c1482l0.L();
            strL.getClass();
            switch (strL) {
                case "npot_support":
                    hVar.f16729t = c1482l0.Q();
                    break;
                case "vendor_id":
                    hVar.f16724n = c1482l0.Q();
                    break;
                case "multi_threaded_rendering":
                    hVar.f16727r = c1482l0.j();
                    break;
                case "id":
                    hVar.f16723m = c1482l0.z();
                    break;
                case "name":
                    hVar.l = c1482l0.Q();
                    break;
                case "vendor_name":
                    hVar.o = c1482l0.Q();
                    break;
                case "version":
                    hVar.f16728s = c1482l0.Q();
                    break;
                case "api_type":
                    hVar.f16726q = c1482l0.Q();
                    break;
                case "memory_size":
                    hVar.f16725p = c1482l0.z();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    c1482l0.R(k10, concurrentHashMap, strL);
                    break;
            }
        }
        hVar.f16730u = concurrentHashMap;
        c1482l0.i();
        return hVar;
    }

    public static m f(C1482l0 c1482l0, K k10) throws IOException {
        String strL;
        c1482l0.g();
        m mVar = new m();
        ConcurrentHashMap concurrentHashMap = null;
        while (c1482l0.T() == io.sentry.vendor.gson.stream.b.NAME) {
            strL = c1482l0.L();
            strL.getClass();
            switch (strL) {
                case "rooted":
                    mVar.f16747q = c1482l0.j();
                    break;
                case "raw_description":
                    mVar.f16745n = c1482l0.Q();
                    break;
                case "name":
                    mVar.l = c1482l0.Q();
                    break;
                case "build":
                    mVar.o = c1482l0.Q();
                    break;
                case "version":
                    mVar.f16744m = c1482l0.Q();
                    break;
                case "kernel_version":
                    mVar.f16746p = c1482l0.Q();
                    break;
                default:
                    if (concurrentHashMap == null) {
                        concurrentHashMap = new ConcurrentHashMap();
                    }
                    c1482l0.R(k10, concurrentHashMap, strL);
                    break;
            }
        }
        mVar.f16748r = concurrentHashMap;
        c1482l0.i();
        return mVar;
    }

    public static IllegalStateException g(String str, K k10) {
        String strI = android.hardware.bydauto.ac.tsimpl.a.i("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strI);
        k10.s(EnumC1495p1.ERROR, strI, illegalStateException);
        return illegalStateException;
    }

    public static IllegalStateException h(String str, K k10) {
        String strI = android.hardware.bydauto.ac.tsimpl.a.i("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strI);
        k10.s(EnumC1495p1.ERROR, strI, illegalStateException);
        return illegalStateException;
    }

    public static IllegalStateException i(String str, K k10) {
        String strI = android.hardware.bydauto.ac.tsimpl.a.i("Missing required field \"", str, "\"");
        IllegalStateException illegalStateException = new IllegalStateException(strI);
        k10.s(EnumC1495p1.ERROR, strI, illegalStateException);
        return illegalStateException;
    }

    /*  JADX ERROR: NullPointerException in pass: SwitchBreakVisitor
        java.lang.NullPointerException
        */
    @Override // io.sentry.InterfaceC1452b0
    public final java.lang.Object a(io.sentry.C1482l0 r37, io.sentry.K r38) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 5022
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.clientreport.a.a(io.sentry.l0, io.sentry.K):java.lang.Object");
    }
}
