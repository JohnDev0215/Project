package g2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import f2.a;
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
    public static r f5071a = new r();

    public static Object j(f2.a aVar, Map<Object, Object> map, Type type, Type type2, Object obj) {
        Object objB;
        f2.b bVar = aVar.f4861j;
        if (bVar.q() != 12 && bVar.q() != 16) {
            throw new JSONException("syntax error, expect {, actual " + bVar.c0());
        }
        t tVarO = aVar.o().o(type);
        t tVarO2 = aVar.o().o(type2);
        bVar.V(tVarO.e());
        f2.g gVarQ = aVar.q();
        while (bVar.q() != 13) {
            try {
                Object obj2 = null;
                if (bVar.q() == 4 && bVar.k0() && !bVar.M(Feature.DisableSpecialKeyDetect)) {
                    bVar.m0(4);
                    if (bVar.q() != 4) {
                        throw new JSONException("illegal ref, " + f2.f.a(bVar.q()));
                    }
                    String strN0 = bVar.n0();
                    if ("..".equals(strN0)) {
                        obj2 = gVarQ.f4902b.f4901a;
                    } else if ("$".equals(strN0)) {
                        f2.g gVar = gVarQ;
                        while (true) {
                            f2.g gVar2 = gVar.f4902b;
                            if (gVar2 == null) {
                                break;
                            }
                            gVar = gVar2;
                        }
                        obj2 = gVar.f4901a;
                    } else {
                        aVar.j(new a.C0039a(gVarQ, strN0));
                        aVar.u0(1);
                    }
                    bVar.V(13);
                    if (bVar.q() != 13) {
                        throw new JSONException("illegal ref");
                    }
                    bVar.V(16);
                    return obj2;
                }
                if (map.size() == 0 && bVar.q() == 4 && c2.a.DEFAULT_TYPE_KEY.equals(bVar.n0()) && !bVar.M(Feature.DisableSpecialKeyDetect)) {
                    bVar.m0(4);
                    bVar.V(16);
                    if (bVar.q() == 13) {
                        bVar.E();
                        return map;
                    }
                    bVar.V(tVarO.e());
                }
                if (bVar.q() == 4 && (tVarO instanceof o)) {
                    String strN02 = bVar.n0();
                    bVar.E();
                    f2.a aVar2 = new f2.a(strN02, aVar.o(), aVar.C().x());
                    aVar2.s0(aVar.r());
                    objB = tVarO.b(aVar2, type, null);
                } else {
                    objB = tVarO.b(aVar, type, null);
                }
                if (bVar.q() != 17) {
                    throw new JSONException("syntax error, expect :, actual " + bVar.q());
                }
                bVar.V(tVarO2.e());
                Object objB2 = tVarO2.b(aVar, type2, objB);
                aVar.n(map, objB);
                map.put(objB, objB2);
                if (bVar.q() == 16) {
                    bVar.V(tVarO.e());
                }
            } finally {
                aVar.r0(gVarQ);
            }
        }
        bVar.V(16);
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
    public static java.util.Map k(f2.a r10, java.util.Map<java.lang.String, java.lang.Object> r11, java.lang.reflect.Type r12, java.lang.Object r13, int r14) {
        /*
            Method dump skipped, instruction units count: 560
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: g2.r.k(f2.a, java.util.Map, java.lang.reflect.Type, java.lang.Object, int):java.util.Map");
    }

    @Override // g2.t
    public int e() {
        return 12;
    }

    @Override // g2.e
    public <T> T f(f2.a aVar, Type type, Object obj, String str, int i8) {
        if (type == JSONObject.class && aVar.x() == null) {
            return (T) aVar.c0();
        }
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 8) {
            bVar.V(16);
            return null;
        }
        boolean z7 = (type instanceof Class) && "java.util.Collections$UnmodifiableMap".equals(((Class) type).getName());
        Map<Object, Object> mapH = (bVar.x() & Feature.OrderedField.mask) != 0 ? h(type, bVar.x()) : g(type);
        f2.g gVarQ = aVar.q();
        try {
            aVar.o0(gVarQ, mapH, obj);
            Map map = (T) i(aVar, type, obj, mapH, i8);
            if (z7) {
                map = (T) Collections.unmodifiableMap(map);
            }
            return (T) map;
        } finally {
            aVar.r0(gVarQ);
        }
    }

    public Map<Object, Object> g(Type type) {
        return h(type, c2.a.DEFAULT_GENERATE_FEATURE);
    }

    public Map<Object, Object> h(Type type, int i8) {
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
            return (Feature.OrderedField.mask & i8) != 0 ? new LinkedHashMap() : new HashMap();
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
            return EnumMap.class.equals(rawType) ? new EnumMap((Class) parameterizedType.getActualTypeArguments()[0]) : h(rawType, i8);
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
        } catch (Exception e8) {
            throw new JSONException("unsupport type " + type, e8);
        }
    }

    public Object i(f2.a aVar, Type type, Object obj, Map map, int i8) {
        if (!(type instanceof ParameterizedType)) {
            return aVar.k0(map, obj);
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type type2 = parameterizedType.getActualTypeArguments()[0];
        Type type3 = map.getClass().getName().equals("org.springframework.util.LinkedMultiValueMap") ? List.class : parameterizedType.getActualTypeArguments()[1];
        return String.class == type2 ? k(aVar, map, type3, obj, i8) : j(aVar, map, type2, type3, obj);
    }
}
