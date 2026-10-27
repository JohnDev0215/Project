package c2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byd.systemsettings.system.utils.BydDateUtils;
import f2.h;
import g2.j;
import g2.k;
import g2.m;
import g2.v;
import h2.a1;
import h2.b1;
import h2.d1;
import h2.g0;
import h2.i0;
import h2.j0;
import h2.t0;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import l2.l;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: JSON.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements e, b {
    public static final String VERSION = "1.2.73";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ThreadLocal<byte[]> f2297e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ThreadLocal<char[]> f2298f;
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();
    public static String DEFAULT_TYPE_KEY = "@type";
    public static final b1[] emptyFilters = new b1[0];
    public static String DEFFAULT_DATE_FORMAT = BydDateUtils.DEF_FORMAT;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap<Type, Type> f2296d = new ConcurrentHashMap<>(16);
    public static int DEFAULT_PARSER_FEATURE = (((((((Feature.AutoCloseSource.getMask() | 0) | Feature.InternFieldNames.getMask()) | Feature.UseBigDecimal.getMask()) | Feature.AllowUnQuotedFieldNames.getMask()) | Feature.AllowSingleQuotes.getMask()) | Feature.AllowArbitraryCommas.getMask()) | Feature.SortFeidFastMatch.getMask()) | Feature.IgnoreNotMatch.getMask();
    public static int DEFAULT_GENERATE_FEATURE = (((0 | SerializerFeature.QuoteFieldNames.getMask()) | SerializerFeature.SkipTransientField.getMask()) | SerializerFeature.WriteEnumUsingName.getMask()) | SerializerFeature.SortField.getMask();

    static {
        c(l2.e.f6192a);
        f2297e = new ThreadLocal<>();
        f2298f = new ThreadLocal<>();
    }

    public static byte[] a(int i8) {
        ThreadLocal<byte[]> threadLocal = f2297e;
        byte[] bArr = threadLocal.get();
        if (bArr != null) {
            return bArr.length < i8 ? new byte[i8] : bArr;
        }
        if (i8 > 65536) {
            return new byte[i8];
        }
        byte[] bArr2 = new byte[CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2];
        threadLocal.set(bArr2);
        return bArr2;
    }

    public static void addMixInAnnotations(Type type, Type type2) {
        if (type == null || type2 == null) {
            return;
        }
        f2296d.put(type, type2);
    }

    public static char[] b(int i8) {
        ThreadLocal<char[]> threadLocal = f2298f;
        char[] cArr = threadLocal.get();
        if (cArr != null) {
            return cArr.length < i8 ? new char[i8] : cArr;
        }
        if (i8 > 65536) {
            return new char[i8];
        }
        char[] cArr2 = new char[CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2];
        threadLocal.set(cArr2);
        return cArr2;
    }

    public static void c(Properties properties) {
        String property = properties.getProperty("fastjson.serializerFeatures.MapSortField");
        int mask = SerializerFeature.MapSortField.getMask();
        if ("true".equals(property)) {
            DEFAULT_GENERATE_FEATURE |= mask;
        } else if ("false".equals(property)) {
            DEFAULT_GENERATE_FEATURE &= ~mask;
        }
        if ("true".equals(properties.getProperty("parser.features.NonStringKeyAsString"))) {
            DEFAULT_PARSER_FEATURE |= Feature.NonStringKeyAsString.getMask();
        }
        if ("true".equals(properties.getProperty("parser.features.ErrorOnEnumNotMatch")) || "true".equals(properties.getProperty("fastjson.parser.features.ErrorOnEnumNotMatch"))) {
            DEFAULT_PARSER_FEATURE |= Feature.ErrorOnEnumNotMatch.getMask();
        }
        if ("false".equals(properties.getProperty("fastjson.asmEnable"))) {
            h.s().y(false);
            a1.g().l(false);
        }
    }

    public static void clearMixInAnnotations() {
        f2296d.clear();
    }

    public static Type getMixInAnnotations(Type type) {
        if (type != null) {
            return f2296d.get(type);
        }
        return null;
    }

    public static <T> void handleResovleTask(f2.a aVar, T t7) {
        aVar.L(t7);
    }

    public static boolean isValid(String str) {
        if (str != null && str.length() != 0) {
            f2.e eVar = new f2.e(str);
            try {
                eVar.E();
                int iQ = eVar.q();
                if (iQ != 12) {
                    if (iQ != 14) {
                        switch (iQ) {
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                eVar.E();
                                break;
                            default:
                                return false;
                        }
                    } else {
                        eVar.q1(true);
                    }
                } else {
                    if (eVar.F() == 26) {
                        return false;
                    }
                    eVar.r1(true);
                }
                return eVar.q() == 20;
            } catch (Exception unused) {
            } finally {
                eVar.close();
            }
        }
        return false;
    }

    public static boolean isValidArray(String str) {
        if (str != null && str.length() != 0) {
            f2.e eVar = new f2.e(str);
            try {
                eVar.E();
                if (eVar.q() != 14) {
                    return false;
                }
                eVar.q1(true);
                return eVar.q() == 20;
            } catch (Exception unused) {
            } finally {
                eVar.close();
            }
        }
        return false;
    }

    public static boolean isValidObject(String str) {
        if (str != null && str.length() != 0) {
            f2.e eVar = new f2.e(str);
            try {
                eVar.E();
                if (eVar.q() != 12) {
                    return false;
                }
                if (eVar.F() == 26) {
                    return false;
                }
                eVar.r1(true);
                return eVar.q() == 20;
            } catch (Exception unused) {
            } finally {
                eVar.close();
            }
        }
        return false;
    }

    public static Object parse(String str) {
        return parse(str, DEFAULT_PARSER_FEATURE);
    }

    public static JSONArray parseArray(String str) {
        return parseArray(str, h.f4913g);
    }

    public static JSONObject parseObject(String str, Feature... featureArr) {
        return (JSONObject) parse(str, featureArr);
    }

    public static void removeMixInAnnotations(Type type) {
        if (type != null) {
            f2296d.remove(type);
        }
    }

    public static void setDefaultTypeKey(String str) {
        DEFAULT_TYPE_KEY = str;
        h.f4913g.f4922p.b(str, 0, str.length(), str.hashCode(), true);
    }

    public static Object toJSON(Object obj) {
        return toJSON(obj, a1.f5167a);
    }

    public static byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static byte[] toJSONBytesWithFastJsonConfig(Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            i0 i0Var = new i0(d1Var, a1Var);
            if (str != null && str.length() != 0) {
                i0Var.G(str);
                i0Var.q(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (b1VarArr != null) {
                for (b1 b1Var : b1VarArr) {
                    i0Var.b(b1Var);
                }
            }
            i0Var.H(obj);
            return d1Var.v(charset);
        } finally {
            d1Var.close();
        }
    }

    public static String toJSONString(Object obj) {
        return toJSONString(obj, emptyFilters, new SerializerFeature[0]);
    }

    public static String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f5167a, null, str, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONStringZ(Object obj, a1 a1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, emptyFilters, null, 0, serializerFeatureArr);
    }

    public static <T> T toJavaObject(a aVar, Class<T> cls) {
        return (T) l.f(aVar, cls, h.s());
    }

    public static void writeJSONString(Writer writer, Object obj, SerializerFeature... serializerFeatureArr) {
        writeJSONString(writer, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONStringTo(Object obj, Writer writer, SerializerFeature... serializerFeatureArr) {
        writeJSONString(writer, obj, serializerFeatureArr);
    }

    public static final int writeJSONStringWithFastJsonConfig(OutputStream outputStream, Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) throws IOException {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            i0 i0Var = new i0(d1Var, a1Var);
            if (str != null && str.length() != 0) {
                i0Var.G(str);
                i0Var.q(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (b1VarArr != null) {
                for (b1 b1Var : b1VarArr) {
                    i0Var.b(b1Var);
                }
            }
            i0Var.H(obj);
            return d1Var.l0(outputStream, charset);
        } finally {
            d1Var.close();
        }
    }

    public String toString() {
        return toJSONString();
    }

    public static Object parse(String str, h hVar) {
        return parse(str, hVar, DEFAULT_PARSER_FEATURE);
    }

    public static JSONArray parseArray(String str, h hVar) {
        JSONArray jSONArray = null;
        if (str == null) {
            return null;
        }
        f2.a aVar = new f2.a(str, hVar);
        f2.b bVar = aVar.f4861j;
        if (bVar.q() == 8) {
            bVar.E();
        } else if (bVar.q() != 20) {
            jSONArray = new JSONArray();
            aVar.X(jSONArray);
            aVar.L(jSONArray);
        }
        aVar.close();
        return jSONArray;
    }

    public static JSONObject parseObject(String str) {
        Object obj = parse(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        try {
            return (JSONObject) toJSON(obj);
        } catch (RuntimeException e8) {
            throw new JSONException("can not cast to JSONObject.", e8);
        }
    }

    public static Object toJSON(Object obj, h hVar) {
        return toJSON(obj, a1.f5167a);
    }

    public static byte[] toJSONBytes(Object obj, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f5167a, new b1[]{b1Var}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONString(Writer writer, Object obj, int i8, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(writer, i8, serializerFeatureArr);
        try {
            new i0(d1Var).H(obj);
        } finally {
            d1Var.close();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T toJavaObject(Class<T> cls) {
        return (cls == JSONArray.class || cls == a.class || cls == Collection.class || cls == List.class) ? this : (T) l.f(this, cls, h.s());
    }

    public String toString(SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
        try {
            new i0(d1Var).H(this);
            return d1Var.toString();
        } finally {
            d1Var.close();
        }
    }

    public static Object parse(String str, h hVar, Feature... featureArr) {
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(str, hVar, iConfig);
    }

    public static Object toJSON(Object obj, a1 a1Var) {
        Map map;
        if (obj == null) {
            return null;
        }
        if (obj instanceof a) {
            return obj;
        }
        if (obj instanceof Map) {
            Map map2 = (Map) obj;
            int size = map2.size();
            if (map2 instanceof LinkedHashMap) {
                map = new LinkedHashMap(size);
            } else if (map2 instanceof TreeMap) {
                map = new TreeMap();
            } else {
                map = new HashMap(size);
            }
            JSONObject jSONObject = new JSONObject((Map<String, Object>) map);
            for (Map.Entry entry : map2.entrySet()) {
                jSONObject.put(l.A(entry.getKey()), toJSON(entry.getValue(), a1Var));
            }
            return jSONObject;
        }
        if (obj instanceof Collection) {
            Collection collection = (Collection) obj;
            JSONArray jSONArray = new JSONArray(collection.size());
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                jSONArray.add(toJSON(it.next(), a1Var));
            }
            return jSONArray;
        }
        if (obj instanceof g0) {
            return parse(toJSONString(obj));
        }
        Class<?> cls = obj.getClass();
        if (cls.isEnum()) {
            return ((Enum) obj).name();
        }
        if (cls.isArray()) {
            int length = Array.getLength(obj);
            JSONArray jSONArray2 = new JSONArray(length);
            for (int i8 = 0; i8 < length; i8++) {
                jSONArray2.add(toJSON(Array.get(obj, i8)));
            }
            return jSONArray2;
        }
        if (h.v(cls)) {
            return obj;
        }
        t0 t0VarH = a1Var.h(cls);
        if (t0VarH instanceof j0) {
            j0 j0Var = (j0) t0VarH;
            JSONObject jSONObject2 = new JSONObject();
            try {
                for (Map.Entry<String, Object> entry2 : j0Var.w(obj).entrySet()) {
                    jSONObject2.put(entry2.getKey(), toJSON(entry2.getValue(), a1Var));
                }
                return jSONObject2;
            } catch (Exception e8) {
                throw new JSONException("toJSON error", e8);
            }
        }
        return parse(toJSONString(obj));
    }

    public static byte[] toJSONBytes(Object obj, int i8, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f5167a, i8, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, int i8, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            new i0(d1Var).H(obj);
            return d1Var.toString();
        } finally {
            d1Var.close();
        }
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, emptyFilters, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public <T> T toJavaObject(Type type) {
        return (T) l.h(this, type, h.s());
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, int i8, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, emptyFilters, i8, serializerFeatureArr);
    }

    public <T> T toJavaObject(g gVar) {
        return (T) l.h(this, gVar != null ? gVar.a() : null, h.s());
    }

    public static byte[] toJSONBytes(Object obj, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f5167a, b1VarArr, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static Object parse(String str, h hVar, int i8) {
        if (str == null) {
            return null;
        }
        f2.a aVar = new f2.a(str, hVar, i8);
        Object objN = aVar.N();
        aVar.L(objN);
        aVar.close();
        return objN;
    }

    public static <T> T parseObject(String str, g<T> gVar, Feature... featureArr) {
        return (T) parseObject(str, gVar.f2306c, h.f4913g, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, new b1[]{b1Var}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return (T) parseObject(str, cls, h.f4913g, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1[] b1VarArr, int i8, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, b1VarArr, null, i8, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, int i8, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, l2.e.f6193b, obj, a1.f5167a, null, null, i8, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, v vVar, Feature... featureArr) {
        return (T) parseObject(str, cls, h.f4913g, vVar, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(l2.e.f6193b, obj, a1Var, b1VarArr, str, i8, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f5167a, new b1[]{b1Var}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, charset, obj, a1.f5167a, null, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Type type, Feature... featureArr) {
        return (T) parseObject(str, type, h.f4913g, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            i0 i0Var = new i0(d1Var, a1Var);
            if (str != null && str.length() != 0) {
                i0Var.F(str);
                i0Var.q(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (b1VarArr != null) {
                for (b1 b1Var : b1VarArr) {
                    i0Var.b(b1Var);
                }
            }
            i0Var.H(obj);
            return d1Var.v(charset);
        } finally {
            d1Var.close();
        }
    }

    public static String toJSONString(Object obj, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f5167a, b1VarArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) throws IOException {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            i0 i0Var = new i0(d1Var, a1Var);
            if (str != null && str.length() != 0) {
                i0Var.F(str);
                i0Var.q(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (b1VarArr != null) {
                for (b1 b1Var : b1VarArr) {
                    i0Var.b(b1Var);
                }
            }
            i0Var.H(obj);
            return d1Var.l0(outputStream, charset);
        } finally {
            d1Var.close();
        }
    }

    public static Object parse(String str, int i8) {
        return parse(str, h.s(), i8);
    }

    public static <T> List<T> parseArray(String str, Class<T> cls) {
        return parseArray(str, cls, h.f4913g);
    }

    public static <T> T parseObject(String str, Type type, v vVar, Feature... featureArr) {
        return (T) parseObject(str, type, h.f4913g, vVar, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, (b1) null, serializerFeatureArr);
    }

    public static Object parse(byte[] bArr, Feature... featureArr) {
        char[] cArrB = b(bArr.length);
        int iF = l2.e.f(bArr, 0, bArr.length, cArrB);
        if (iF < 0) {
            return null;
        }
        return parse(new String(cArrB, 0, iF), featureArr);
    }

    public static <T> List<T> parseArray(String str, Class<T> cls, h hVar) {
        ArrayList arrayList = null;
        if (str == null) {
            return null;
        }
        f2.a aVar = new f2.a(str, hVar);
        f2.b bVar = aVar.f4861j;
        int iQ = bVar.q();
        if (iQ == 8) {
            bVar.E();
        } else if (iQ != 20 || !bVar.o()) {
            arrayList = new ArrayList();
            aVar.R(cls, arrayList);
            aVar.L(arrayList);
        }
        aVar.close();
        return arrayList;
    }

    public static <T> T parseObject(String str, Type type, int i8, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i8 = Feature.config(i8, feature, true);
        }
        f2.a aVar = new f2.a(str, h.s(), i8);
        T t7 = (T) aVar.e0(type);
        aVar.L(t7);
        aVar.close();
        return t7;
    }

    public static String toJSONString(Object obj, a1 a1Var, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, new b1[]{b1Var}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, b1VarArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, b1[] b1VarArr, String str, int i8, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i8, serializerFeatureArr);
        try {
            i0 i0Var = new i0(d1Var, a1Var);
            if (str != null && str.length() != 0) {
                i0Var.F(str);
                i0Var.q(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (b1VarArr != null) {
                for (b1 b1Var : b1VarArr) {
                    i0Var.b(b1Var);
                }
            }
            i0Var.H(obj);
            return d1Var.toString();
        } finally {
            d1Var.close();
        }
    }

    public static Object parse(byte[] bArr, int i8, int i9, CharsetDecoder charsetDecoder, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(bArr, i8, i9, charsetDecoder, iConfig);
    }

    public static <T> T parseObject(String str, Type type, h hVar, Feature... featureArr) {
        return (T) parseObject(str, type, hVar, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(String str, Type type, h hVar, int i8, Feature... featureArr) {
        return (T) parseObject(str, type, hVar, (v) null, i8, featureArr);
    }

    public static Object parse(byte[] bArr, int i8, int i9, CharsetDecoder charsetDecoder, int i10) {
        charsetDecoder.reset();
        char[] cArrB = b((int) (((double) i9) * ((double) charsetDecoder.maxCharsPerByte())));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i8, i9);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrB);
        l2.e.b(charsetDecoder, byteBufferWrap, charBufferWrap);
        f2.a aVar = new f2.a(cArrB, charBufferWrap.position(), h.s(), i10);
        Object objN = aVar.N();
        aVar.L(objN);
        aVar.close();
        return objN;
    }

    public static <T> T parseObject(String str, Type type, h hVar, v vVar, int i8, Feature... featureArr) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (featureArr != null) {
            for (Feature feature : featureArr) {
                i8 |= feature.mask;
            }
        }
        f2.a aVar = new f2.a(str, hVar, i8);
        if (vVar != null) {
            if (vVar instanceof k) {
                aVar.w().add((k) vVar);
            }
            if (vVar instanceof j) {
                aVar.v().add((j) vVar);
            }
            if (vVar instanceof m) {
                aVar.t0((m) vVar);
            }
        }
        T t7 = (T) aVar.g0(type, null);
        aVar.L(t7);
        aVar.close();
        return t7;
    }

    public static List<Object> parseArray(String str, Type[] typeArr) {
        return parseArray(str, typeArr, h.f4913g);
    }

    @Override // c2.e
    public void writeJSONString(Appendable appendable) {
        d1 d1Var = new d1();
        try {
            try {
                new i0(d1Var).H(this);
                appendable.append(d1Var.toString());
            } catch (IOException e8) {
                throw new JSONException(e8.getMessage(), e8);
            }
        } finally {
            d1Var.close();
        }
    }

    public static List<Object> parseArray(String str, Type[] typeArr, h hVar) {
        if (str == null) {
            return null;
        }
        f2.a aVar = new f2.a(str, hVar);
        Object[] objArrZ = aVar.Z(typeArr);
        List<Object> listAsList = objArrZ != null ? Arrays.asList(objArrZ) : null;
        aVar.L(listAsList);
        aVar.close();
        return listAsList;
    }

    public static String toJSONString(Object obj, boolean z7) {
        return !z7 ? toJSONString(obj) : toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    @Override // c2.b
    public String toJSONString() {
        d1 d1Var = new d1();
        try {
            new i0(d1Var).H(this);
            return d1Var.toString();
        } finally {
            d1Var.close();
        }
    }

    public static Object parse(String str, Feature... featureArr) {
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(str, iConfig);
    }

    public static <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        return (T) parseObject(bArr, 0, bArr.length, l2.e.f6193b, type, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i8, int i9, Charset charset, Type type, Feature... featureArr) {
        return (T) parseObject(bArr, i8, i9, charset, type, h.f4913g, null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, Charset charset, Type type, h hVar, v vVar, int i8, Feature... featureArr) {
        return (T) parseObject(bArr, 0, bArr.length, charset, type, hVar, vVar, i8, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i8, int i9, Charset charset, Type type, h hVar, v vVar, int i10, Feature... featureArr) {
        String str;
        if (charset == null) {
            charset = l2.e.f6193b;
        }
        if (charset == l2.e.f6193b) {
            char[] cArrB = b(bArr.length);
            int iF = l2.e.f(bArr, i8, i9, cArrB);
            if (iF < 0) {
                return null;
            }
            str = new String(cArrB, 0, iF);
        } else {
            if (i9 < 0) {
                return null;
            }
            str = new String(bArr, i8, i9, charset);
        }
        return (T) parseObject(str, type, hVar, vVar, i10, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i8, int i9, CharsetDecoder charsetDecoder, Type type, Feature... featureArr) {
        charsetDecoder.reset();
        char[] cArrB = b((int) (((double) i9) * ((double) charsetDecoder.maxCharsPerByte())));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i8, i9);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrB);
        l2.e.b(charsetDecoder, byteBufferWrap, charBufferWrap);
        return (T) parseObject(cArrB, charBufferWrap.position(), type, featureArr);
    }

    public static <T> T parseObject(char[] cArr, int i8, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        f2.a aVar = new f2.a(cArr, i8, h.s(), iConfig);
        T t7 = (T) aVar.e0(type);
        aVar.L(t7);
        aVar.close();
        return t7;
    }

    public static <T> T parseObject(InputStream inputStream, Type type, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, l2.e.f6193b, type, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, charset, type, h.f4913g, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, h hVar, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, charset, type, hVar, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, h hVar, v vVar, int i8, Feature... featureArr) throws IOException {
        if (charset == null) {
            charset = l2.e.f6193b;
        }
        Charset charset2 = charset;
        byte[] bArrA = a(CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2);
        int i9 = 0;
        while (true) {
            int i10 = inputStream.read(bArrA, i9, bArrA.length - i9);
            if (i10 == -1) {
                return (T) parseObject(bArrA, 0, i9, charset2, type, hVar, vVar, i8, featureArr);
            }
            i9 += i10;
            if (i9 == bArrA.length) {
                byte[] bArr = new byte[(bArrA.length * 3) / 2];
                System.arraycopy(bArrA, 0, bArr, 0, bArrA.length);
                bArrA = bArr;
            }
        }
    }

    public static <T> T parseObject(String str, Class<T> cls) {
        return (T) parseObject(str, (Class) cls, new Feature[0]);
    }
}
