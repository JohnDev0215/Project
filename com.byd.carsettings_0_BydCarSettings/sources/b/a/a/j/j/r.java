package b.a.a.j.j;

import b.a.a.j.a;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: MapDeserializer.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends e implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static r f298a = new r();

    public static Object j(b.a.a.j.a aVar, Map<Object, Object> map, Type type, Type type2, Object obj) {
        Object objB;
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() != 12 && bVar.j() != 16) {
            throw new JSONException("syntax error, expect {, actual " + bVar.M());
        }
        t tVarO = aVar.i().o(type);
        t tVarO2 = aVar.i().o(type2);
        bVar.F(tVarO.e());
        b.a.a.j.g gVarJ = aVar.j();
        while (bVar.j() != 13) {
            try {
                Object obj2 = null;
                if (bVar.j() == 4 && bVar.S() && !bVar.y(Feature.DisableSpecialKeyDetect)) {
                    bVar.U(4);
                    if (bVar.j() != 4) {
                        throw new JSONException("illegal ref, " + b.a.a.j.f.a(bVar.j()));
                    }
                    String strV = bVar.V();
                    if ("..".equals(strV)) {
                        obj2 = gVarJ.f246b.f245a;
                    } else if ("$".equals(strV)) {
                        b.a.a.j.g gVar = gVarJ;
                        while (true) {
                            b.a.a.j.g gVar2 = gVar.f246b;
                            if (gVar2 == null) {
                                break;
                            }
                            gVar = gVar2;
                        }
                        obj2 = gVar.f245a;
                    } else {
                        aVar.f(new a.C0016a(gVarJ, strV));
                        aVar.b0(1);
                    }
                    bVar.F(13);
                    if (bVar.j() != 13) {
                        throw new JSONException("illegal ref");
                    }
                    bVar.F(16);
                    return obj2;
                }
                if (map.size() == 0 && bVar.j() == 4 && b.a.a.a.DEFAULT_TYPE_KEY.equals(bVar.V()) && !bVar.y(Feature.DisableSpecialKeyDetect)) {
                    bVar.U(4);
                    bVar.F(16);
                    if (bVar.j() == 13) {
                        bVar.u();
                        return map;
                    }
                    bVar.F(tVarO.e());
                }
                if (bVar.j() == 4 && (tVarO instanceof o)) {
                    String strV2 = bVar.V();
                    bVar.u();
                    b.a.a.j.a aVar2 = new b.a.a.j.a(strV2, aVar.i(), aVar.t().q());
                    aVar2.Z(aVar.k());
                    objB = tVarO.b(aVar2, type, null);
                } else {
                    objB = tVarO.b(aVar, type, null);
                }
                if (bVar.j() != 17) {
                    throw new JSONException("syntax error, expect :, actual " + bVar.j());
                }
                bVar.F(tVarO2.e());
                Object objB2 = tVarO2.b(aVar, type2, objB);
                aVar.h(map, objB);
                map.put(objB, objB2);
                if (bVar.j() == 16) {
                    bVar.F(tVarO.e());
                }
            } finally {
                aVar.Y(gVarJ);
            }
        }
        bVar.F(16);
        return map;
    }

    /* JADX WARN: Code restructure failed: missing block: B:94:0x0203, code lost:
    
        return r11;
     */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01c2 A[Catch: all -> 0x022b, TRY_ENTER, TryCatch #0 {all -> 0x022b, blocks: (B:18:0x008f, B:22:0x00a2, B:26:0x00b7, B:51:0x014c, B:53:0x015f, B:55:0x0167, B:57:0x016d, B:61:0x018c, B:63:0x0194, B:65:0x019d, B:68:0x01a4, B:70:0x01b1, B:72:0x01b5, B:73:0x01b8, B:60:0x0184, B:76:0x01c2, B:78:0x01c7, B:79:0x01ca, B:81:0x01d2, B:83:0x01da, B:89:0x01f5, B:82:0x01d6, B:29:0x00ca, B:30:0x00e2, B:33:0x00e7, B:38:0x00fa, B:40:0x0102, B:43:0x0114, B:44:0x012c, B:45:0x012d, B:46:0x0132, B:47:0x0133, B:49:0x013b, B:95:0x0204, B:96:0x0224, B:97:0x0225, B:98:0x022a), top: B:102:0x008f }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map k(b.a.a.j.a r10, java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.reflect.Type r12, java.lang.Object r13, int r14) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.j.r.k(b.a.a.j.a, java.util.Map, java.lang.reflect.Type, java.lang.Object, int):java.util.Map");
    }

    @Override // b.a.a.j.j.t
    public int e() {
        return 12;
    }

    @Override // b.a.a.j.j.e
    public <T> T f(b.a.a.j.a aVar, Type type, Object obj, String str, int i) {
        if (type == JSONObject.class && aVar.q() == null) {
            return (T) aVar.M();
        }
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 8) {
            bVar.F(16);
            return null;
        }
        boolean z = (type instanceof Class) && "java.util.Collections$UnmodifiableMap".equals(((Class) type).getName());
        Map<Object, Object> mapH = (bVar.q() & Feature.OrderedField.mask) != 0 ? h(type, bVar.q()) : g(type);
        b.a.a.j.g gVarJ = aVar.j();
        try {
            aVar.W(gVarJ, mapH, obj);
            Map map = (T) i(aVar, type, obj, mapH, i);
            if (z) {
                map = (T) Collections.unmodifiableMap(map);
            }
            return (T) map;
        } finally {
            aVar.Y(gVarJ);
        }
    }

    public Map<Object, Object> g(Type type) {
        return h(type, b.a.a.a.DEFAULT_GENERATE_FEATURE);
    }

    public Map<Object, Object> h(Type type, int i) {
        if (type == Properties.class) {
            return new Properties();
        }
        if (type == Hashtable.class) {
            return new Hashtable();
        }
        if (type == IdentityHashMap.class) {
            return new IdentityHashMap();
        }
        if (type == SortedMap.class || type == TreeMap.class) {
            return new TreeMap();
        }
        if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
            return new ConcurrentHashMap();
        }
        if (type == Map.class) {
            return (Feature.OrderedField.mask & i) != 0 ? new LinkedHashMap() : new HashMap();
        }
        if (type == HashMap.class) {
            return new HashMap();
        }
        if (type == LinkedHashMap.class) {
            return new LinkedHashMap();
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return EnumMap.class.equals(rawType) ? new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]) : h(rawType, i);
        }
        Class cls = (Class) type;
        if (cls.isInterface()) {
            throw new JSONException("unsupport type " + type);
        }
        if ("java.util.Collections$UnmodifiableMap".equals(cls.getName())) {
            return new HashMap();
        }
        try {
            return (Map) cls.newInstance();
        } catch (Exception e2) {
            throw new JSONException("unsupport type " + type, e2);
        }
    }

    public Object i(b.a.a.j.a aVar, Type type, Object obj, Map map, int i) {
        if (!(type instanceof ParameterizedType)) {
            return aVar.S(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type type2 = parameterizedType.getActualTypeArguments()[0];
        Type type3 = map.getClass().getName().equals("org.springframework.util.LinkedMultiValueMap") ? List.class : parameterizedType.getActualTypeArguments()[1];
        return String.class == type2 ? k(aVar, map, type3, obj, i) : j(aVar, map, type2, type3, obj);
    }
}
