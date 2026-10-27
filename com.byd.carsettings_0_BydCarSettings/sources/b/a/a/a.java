package b.a.a;

import b.a.a.j.h;
import b.a.a.j.j.j;
import b.a.a.j.j.k;
import b.a.a.j.j.m;
import b.a.a.j.j.v;
import b.a.a.k.a1;
import b.a.a.k.b1;
import b.a.a.k.d1;
import b.a.a.k.g0;
import b.a.a.k.i0;
import b.a.a.k.j0;
import b.a.a.k.t0;
import b.a.a.n.l;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
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

/* JADX INFO: compiled from: JSON.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements e, b {
    public static final String VERSION = "1.2.73";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ThreadLocal<byte[]> f174b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ThreadLocal<char[]> f175c;
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();
    public static String DEFAULT_TYPE_KEY = "@type";
    public static final b1[] emptyFilters = new b1[0];
    public static String DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<Type, Type> f173a = new ConcurrentHashMap<>(16);
    public static int DEFAULT_PARSER_FEATURE = (((((((Feature.AutoCloseSource.getMask() | 0) | Feature.InternFieldNames.getMask()) | Feature.UseBigDecimal.getMask()) | Feature.AllowUnQuotedFieldNames.getMask()) | Feature.AllowSingleQuotes.getMask()) | Feature.AllowArbitraryCommas.getMask()) | Feature.SortFeidFastMatch.getMask()) | Feature.IgnoreNotMatch.getMask();
    public static int DEFAULT_GENERATE_FEATURE = (((0 | SerializerFeature.QuoteFieldNames.getMask()) | SerializerFeature.SkipTransientField.getMask()) | SerializerFeature.WriteEnumUsingName.getMask()) | SerializerFeature.SortField.getMask();

    static {
        c(b.a.a.n.e.f436a);
        f174b = new ThreadLocal<>();
        f175c = new ThreadLocal<>();
    }

    public static byte[] a(int i) {
        ThreadLocal<byte[]> threadLocal = f174b;
        byte[] bArr = threadLocal.get();
        if (bArr != null) {
            return bArr.length < i ? new byte[i] : bArr;
        }
        if (i > 65536) {
            return new byte[i];
        }
        byte[] bArr2 = new byte[65536];
        threadLocal.set(bArr2);
        return bArr2;
    }

    public static void addMixInAnnotations(Type type, Type type2) {
        if (type == null || type2 == null) {
            return;
        }
        f173a.put(type, type2);
    }

    public static char[] b(int i) {
        ThreadLocal<char[]> threadLocal = f175c;
        char[] cArr = threadLocal.get();
        if (cArr != null) {
            return cArr.length < i ? new char[i] : cArr;
        }
        if (i > 65536) {
            return new char[i];
        }
        char[] cArr2 = new char[65536];
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
        f173a.clear();
    }

    public static Type getMixInAnnotations(Type type) {
        if (type != null) {
            return f173a.get(type);
        }
        return null;
    }

    public static <T> void handleResovleTask(b.a.a.j.a aVar, T t) {
        aVar.x(t);
    }

    public static boolean isValid(String str) {
        if (str != null && str.length() != 0) {
            b.a.a.j.e eVar = new b.a.a.j.e(str);
            try {
                eVar.u();
                int iJ = eVar.j();
                if (iJ != 12) {
                    if (iJ != 14) {
                        switch (iJ) {
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                eVar.u();
                                break;
                            default:
                                return false;
                        }
                    } else {
                        eVar.X0(true);
                    }
                } else {
                    if (eVar.v() == 26) {
                        return false;
                    }
                    eVar.Y0(true);
                }
                return eVar.j() == 20;
            } catch (Exception unused) {
            } finally {
                eVar.close();
            }
        }
        return false;
    }

    public static boolean isValidArray(String str) {
        if (str != null && str.length() != 0) {
            b.a.a.j.e eVar = new b.a.a.j.e(str);
            try {
                eVar.u();
                if (eVar.j() != 14) {
                    return false;
                }
                eVar.X0(true);
                return eVar.j() == 20;
            } catch (Exception unused) {
            } finally {
                eVar.close();
            }
        }
        return false;
    }

    public static boolean isValidObject(String str) {
        if (str != null && str.length() != 0) {
            b.a.a.j.e eVar = new b.a.a.j.e(str);
            try {
                eVar.u();
                if (eVar.j() != 12) {
                    return false;
                }
                if (eVar.v() == 26) {
                    return false;
                }
                eVar.Y0(true);
                return eVar.j() == 20;
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
        return parseArray(str, h.g);
    }

    public static JSONObject parseObject(String str, Feature... featureArr) {
        return (JSONObject) parse(str, featureArr);
    }

    public static void removeMixInAnnotations(Type type) {
        if (type != null) {
            f173a.remove(type);
        }
    }

    public static void setDefaultTypeKey(String str) {
        DEFAULT_TYPE_KEY = str;
        h.g.p.b(str, 0, str.length(), str.hashCode(), true);
    }

    public static Object toJSON(Object obj) {
        return toJSON(obj, a1.f329a);
    }

    public static byte[] toJSONBytes(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static byte[] toJSONBytesWithFastJsonConfig(Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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
            return d1Var.n(charset);
        } finally {
            d1Var.close();
        }
    }

    public static String toJSONString(Object obj) {
        return toJSONString(obj, emptyFilters, new SerializerFeature[0]);
    }

    public static String toJSONStringWithDateFormat(Object obj, String str, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f329a, null, str, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
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

    public static final int writeJSONStringWithFastJsonConfig(OutputStream outputStream, Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) throws IOException {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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
            return d1Var.T(outputStream, charset);
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
        b.a.a.j.a aVar = new b.a.a.j.a(str, hVar);
        b.a.a.j.b bVar = aVar.g;
        if (bVar.j() == 8) {
            bVar.u();
        } else if (bVar.j() != 20) {
            jSONArray = new JSONArray();
            aVar.G(jSONArray);
            aVar.x(jSONArray);
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
        } catch (RuntimeException e2) {
            throw new JSONException("can not cast to JSONObject.", e2);
        }
    }

    public static Object toJSON(Object obj, h hVar) {
        return toJSON(obj, a1.f329a);
    }

    public static byte[] toJSONBytes(Object obj, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f329a, new b1[]{b1Var}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static void writeJSONString(Writer writer, Object obj, int i, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(writer, i, serializerFeatureArr);
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
            for (int i = 0; i < length; i++) {
                jSONArray2.add(toJSON(Array.get(obj, i)));
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
            } catch (Exception e2) {
                throw new JSONException("toJSON error", e2);
            }
        }
        return parse(toJSONString(obj));
    }

    public static byte[] toJSONBytes(Object obj, int i, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f329a, i, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, int i, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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

    public static byte[] toJSONBytes(Object obj, a1 a1Var, int i, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, emptyFilters, i, serializerFeatureArr);
    }

    public <T> T toJavaObject(g gVar) {
        return (T) l.h(this, gVar != null ? gVar.a() : null, h.s());
    }

    public static byte[] toJSONBytes(Object obj, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1.f329a, b1VarArr, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static Object parse(String str, h hVar, int i) {
        if (str == null) {
            return null;
        }
        b.a.a.j.a aVar = new b.a.a.j.a(str, hVar, i);
        Object objZ = aVar.z();
        aVar.x(objZ);
        aVar.close();
        return objZ;
    }

    public static <T> T parseObject(String str, g<T> gVar, Feature... featureArr) {
        return (T) parseObject(str, gVar.f183c, h.g, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, new b1[]{b1Var}, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, obj, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return (T) parseObject(str, cls, h.g, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1[] b1VarArr, int i, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(obj, a1Var, b1VarArr, null, i, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Object obj, int i, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, b.a.a.n.e.f437b, obj, a1.f329a, null, null, i, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Class<T> cls, v vVar, Feature... featureArr) {
        return (T) parseObject(str, cls, h.g, vVar, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        return toJSONBytes(b.a.a.n.e.f437b, obj, a1Var, b1VarArr, str, i, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f329a, new b1[]{b1Var}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, SerializerFeature... serializerFeatureArr) throws IOException {
        return writeJSONString(outputStream, charset, obj, a1.f329a, null, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static <T> T parseObject(String str, Type type, Feature... featureArr) {
        return (T) parseObject(str, type, h.g, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static byte[] toJSONBytes(Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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
            return d1Var.n(charset);
        } finally {
            d1Var.close();
        }
    }

    public static String toJSONString(Object obj, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1.f329a, b1VarArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static final int writeJSONString(OutputStream outputStream, Charset charset, Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) throws IOException {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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
            return d1Var.T(outputStream, charset);
        } finally {
            d1Var.close();
        }
    }

    public static Object parse(String str, int i) {
        return parse(str, h.s(), i);
    }

    public static <T> List<T> parseArray(String str, Class<T> cls) {
        return parseArray(str, cls, h.g);
    }

    public static <T> T parseObject(String str, Type type, v vVar, Feature... featureArr) {
        return (T) parseObject(str, type, h.g, vVar, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, (b1) null, serializerFeatureArr);
    }

    public static Object parse(byte[] bArr, Feature... featureArr) {
        char[] cArrB = b(bArr.length);
        int iF = b.a.a.n.e.f(bArr, 0, bArr.length, cArrB);
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
        b.a.a.j.a aVar = new b.a.a.j.a(str, hVar);
        b.a.a.j.b bVar = aVar.g;
        int iJ = bVar.j();
        if (iJ == 8) {
            bVar.u();
        } else if (iJ != 20 || !bVar.i()) {
            arrayList = new ArrayList();
            aVar.C(cls, arrayList);
            aVar.x(arrayList);
        }
        aVar.close();
        return arrayList;
    }

    public static <T> T parseObject(String str, Type type, int i, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i = Feature.config(i, feature, true);
        }
        b.a.a.j.a aVar = new b.a.a.j.a(str, h.s(), i);
        T t = (T) aVar.O(type);
        aVar.x(t);
        aVar.close();
        return t;
    }

    public static String toJSONString(Object obj, a1 a1Var, b1 b1Var, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, new b1[]{b1Var}, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, b1[] b1VarArr, SerializerFeature... serializerFeatureArr) {
        return toJSONString(obj, a1Var, b1VarArr, null, DEFAULT_GENERATE_FEATURE, serializerFeatureArr);
    }

    public static String toJSONString(Object obj, a1 a1Var, b1[] b1VarArr, String str, int i, SerializerFeature... serializerFeatureArr) {
        d1 d1Var = new d1(null, i, serializerFeatureArr);
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

    public static Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Feature... featureArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        return parse(bArr, i, i2, charsetDecoder, iConfig);
    }

    public static <T> T parseObject(String str, Type type, h hVar, Feature... featureArr) {
        return (T) parseObject(str, type, hVar, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(String str, Type type, h hVar, int i, Feature... featureArr) {
        return (T) parseObject(str, type, hVar, (v) null, i, featureArr);
    }

    public static Object parse(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, int i3) {
        charsetDecoder.reset();
        char[] cArrB = b((int) (((double) i2) * ((double) charsetDecoder.maxCharsPerByte())));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrB);
        b.a.a.n.e.b(charsetDecoder, byteBufferWrap, charBufferWrap);
        b.a.a.j.a aVar = new b.a.a.j.a(cArrB, charBufferWrap.position(), h.s(), i3);
        Object objZ = aVar.z();
        aVar.x(objZ);
        aVar.close();
        return objZ;
    }

    public static <T> T parseObject(String str, Type type, h hVar, v vVar, int i, Feature... featureArr) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (featureArr != null) {
            for (Feature feature : featureArr) {
                i |= feature.mask;
            }
        }
        b.a.a.j.a aVar = new b.a.a.j.a(str, hVar, i);
        if (vVar != null) {
            if (vVar instanceof k) {
                aVar.o().add((k) vVar);
            }
            if (vVar instanceof j) {
                aVar.n().add((j) vVar);
            }
            if (vVar instanceof m) {
                aVar.a0((m) vVar);
            }
        }
        T t = (T) aVar.Q(type, null);
        aVar.x(t);
        aVar.close();
        return t;
    }

    public static List<Object> parseArray(String str, Type[] typeArr) {
        return parseArray(str, typeArr, h.g);
    }

    @Override // b.a.a.e
    public void writeJSONString(Appendable appendable) {
        d1 d1Var = new d1();
        try {
            try {
                new i0(d1Var).H(this);
                appendable.append(d1Var.toString());
            } catch (IOException e2) {
                throw new JSONException(e2.getMessage(), e2);
            }
        } finally {
            d1Var.close();
        }
    }

    public static List<Object> parseArray(String str, Type[] typeArr, h hVar) {
        if (str == null) {
            return null;
        }
        b.a.a.j.a aVar = new b.a.a.j.a(str, hVar);
        Object[] objArrJ = aVar.J(typeArr);
        List<Object> listAsList = objArrJ != null ? Arrays.asList(objArrJ) : null;
        aVar.x(listAsList);
        aVar.close();
        return listAsList;
    }

    public static String toJSONString(Object obj, boolean z) {
        return !z ? toJSONString(obj) : toJSONString(obj, SerializerFeature.PrettyFormat);
    }

    @Override // b.a.a.b
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
        return (T) parseObject(bArr, 0, bArr.length, b.a.a.n.e.f437b, type, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, Charset charset, Type type, Feature... featureArr) {
        return (T) parseObject(bArr, i, i2, charset, type, h.g, null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, Charset charset, Type type, h hVar, v vVar, int i, Feature... featureArr) {
        return (T) parseObject(bArr, 0, bArr.length, charset, type, hVar, vVar, i, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, Charset charset, Type type, h hVar, v vVar, int i3, Feature... featureArr) {
        String str;
        if (charset == null) {
            charset = b.a.a.n.e.f437b;
        }
        if (charset == b.a.a.n.e.f437b) {
            char[] cArrB = b(bArr.length);
            int iF = b.a.a.n.e.f(bArr, i, i2, cArrB);
            if (iF < 0) {
                return null;
            }
            str = new String(cArrB, 0, iF);
        } else {
            if (i2 < 0) {
                return null;
            }
            str = new String(bArr, i, i2, charset);
        }
        return (T) parseObject(str, type, hVar, vVar, i3, featureArr);
    }

    public static <T> T parseObject(byte[] bArr, int i, int i2, CharsetDecoder charsetDecoder, Type type, Feature... featureArr) {
        charsetDecoder.reset();
        char[] cArrB = b((int) (((double) i2) * ((double) charsetDecoder.maxCharsPerByte())));
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, i, i2);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArrB);
        b.a.a.n.e.b(charsetDecoder, byteBufferWrap, charBufferWrap);
        return (T) parseObject(cArrB, charBufferWrap.position(), type, featureArr);
    }

    public static <T> T parseObject(char[] cArr, int i, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int iConfig = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            iConfig = Feature.config(iConfig, feature, true);
        }
        b.a.a.j.a aVar = new b.a.a.j.a(cArr, i, h.s(), iConfig);
        T t = (T) aVar.O(type);
        aVar.x(t);
        aVar.close();
        return t;
    }

    public static <T> T parseObject(InputStream inputStream, Type type, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, b.a.a.n.e.f437b, type, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, charset, type, h.g, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, h hVar, Feature... featureArr) throws IOException {
        return (T) parseObject(inputStream, charset, type, hVar, (v) null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static <T> T parseObject(InputStream inputStream, Charset charset, Type type, h hVar, v vVar, int i, Feature... featureArr) throws IOException {
        if (charset == null) {
            charset = b.a.a.n.e.f437b;
        }
        Charset charset2 = charset;
        byte[] bArrA = a(65536);
        int i2 = 0;
        while (true) {
            int i3 = inputStream.read(bArrA, i2, bArrA.length - i2);
            if (i3 == -1) {
                return (T) parseObject(bArrA, 0, i2, charset2, type, hVar, vVar, i, featureArr);
            }
            i2 += i3;
            if (i2 == bArrA.length) {
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
