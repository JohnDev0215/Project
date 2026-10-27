package l2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.byd.systemsettings.system.utils.BydDateUtils;
import com.bydux.api.common.tools.util.ListUtils;
import g2.o;
import g2.t;
import h2.p;
import h2.z0;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: TypeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class l {
    public static volatile Map<Class, String[]> A;
    public static volatile boolean B;
    public static Class<?> D;
    public static Class P;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f6242b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f6243c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static Method f6246f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Method f6248h;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Class<?> f6250j;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static Class<? extends Annotation> f6252l;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static volatile Class f6259s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static volatile boolean f6260t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static volatile boolean f6261u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static volatile Constructor f6262v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static volatile Method f6263w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static volatile Method f6264x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static volatile Method f6265y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static volatile boolean f6266z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f6241a = Pattern.compile("\\.0*$");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f6244d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f6245e = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f6247g = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f6249i = false;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f6251k = false;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static Class<? extends Annotation> f6253m = null;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static boolean f6254n = false;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static Class<? extends Annotation> f6255o = null;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static boolean f6256p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static Method f6257q = null;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static boolean f6258r = false;
    public static ConcurrentMap<String, Class<?>> C = new ConcurrentHashMap(256, 0.75f, 1);
    public static boolean E = false;
    public static Class<? extends Annotation> F = null;
    public static boolean G = false;
    public static volatile Class H = null;
    public static volatile boolean I = false;
    public static volatile Class J = null;
    public static volatile Class K = null;
    public static volatile boolean L = false;
    public static volatile Method M = null;
    public static volatile Field N = null;
    public static volatile Object O = null;

    static {
        f6242b = false;
        f6243c = false;
        P = null;
        try {
            f6242b = "true".equals(e.k("fastjson.compatibleWithJavaBean"));
            f6243c = "true".equals(e.k("fastjson.compatibleWithFieldName"));
        } catch (Throwable unused) {
        }
        try {
            P = Class.forName("java.util.Deque");
        } catch (Throwable unused2) {
        }
        a();
    }

    public static String A(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Class<?> A0(String str, ClassLoader classLoader) {
        return B0(str, classLoader, false);
    }

    public static Timestamp B(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Calendar) {
            return new Timestamp(((Calendar) obj).getTimeInMillis());
        }
        if (obj instanceof Timestamp) {
            return (Timestamp) obj;
        }
        if (obj instanceof Date) {
            return new Timestamp(((Date) obj).getTime());
        }
        long jD0 = obj instanceof BigDecimal ? D0((BigDecimal) obj) : obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (obj instanceof String) {
            String strSubstring = (String) obj;
            if (strSubstring.length() == 0 || "null".equals(strSubstring) || "NULL".equals(strSubstring)) {
                return null;
            }
            if (strSubstring.endsWith(".000000000")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 10);
            } else if (strSubstring.endsWith(".000000")) {
                strSubstring = strSubstring.substring(0, strSubstring.length() - 7);
            }
            if (strSubstring.length() == 29 && strSubstring.charAt(4) == '-' && strSubstring.charAt(7) == '-' && strSubstring.charAt(10) == ' ' && strSubstring.charAt(13) == ':' && strSubstring.charAt(16) == ':' && strSubstring.charAt(19) == '.') {
                return new Timestamp(G0(strSubstring.charAt(0), strSubstring.charAt(1), strSubstring.charAt(2), strSubstring.charAt(3)) - 1900, F0(strSubstring.charAt(5), strSubstring.charAt(6)) - 1, F0(strSubstring.charAt(8), strSubstring.charAt(9)), F0(strSubstring.charAt(11), strSubstring.charAt(12)), F0(strSubstring.charAt(14), strSubstring.charAt(15)), F0(strSubstring.charAt(17), strSubstring.charAt(18)), H0(strSubstring.charAt(20), strSubstring.charAt(21), strSubstring.charAt(22), strSubstring.charAt(23), strSubstring.charAt(24), strSubstring.charAt(25), strSubstring.charAt(26), strSubstring.charAt(27), strSubstring.charAt(28)));
            }
            if (u0(strSubstring)) {
                jD0 = Long.parseLong(strSubstring);
            } else {
                f2.e eVar = new f2.e(strSubstring);
                if (!eVar.k1(false)) {
                    throw new JSONException("can not cast to Timestamp, value : " + strSubstring);
                }
                jD0 = eVar.w0().getTime().getTime();
            }
        }
        if (jD0 >= 0) {
            return new Timestamp(jD0);
        }
        throw new JSONException("can not cast to Timestamp, value : " + obj);
    }

    public static Class<?> B0(String str, ClassLoader classLoader, boolean z7) {
        if (str == null || str.length() == 0 || str.length() > 128) {
            return null;
        }
        Class<?> clsLoadClass = C.get(str);
        if (clsLoadClass != null) {
            return clsLoadClass;
        }
        if (str.charAt(0) == '[') {
            return Array.newInstance(A0(str.substring(1), classLoader), 0).getClass();
        }
        if (str.startsWith("L") && str.endsWith(";")) {
            return A0(str.substring(1, str.length() - 1), classLoader);
        }
        if (classLoader != null) {
            try {
                clsLoadClass = classLoader.loadClass(str);
                if (z7) {
                    C.put(str, clsLoadClass);
                }
                return clsLoadClass;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null && contextClassLoader != classLoader) {
                Class<?> clsLoadClass2 = contextClassLoader.loadClass(str);
                if (z7) {
                    try {
                        C.put(str, clsLoadClass2);
                    } catch (Throwable unused) {
                        clsLoadClass = clsLoadClass2;
                    }
                }
                return clsLoadClass2;
            }
        } catch (Throwable unused2) {
        }
        try {
            clsLoadClass = Class.forName(str);
            if (z7) {
                C.put(str, clsLoadClass);
            }
        } catch (Throwable unused3) {
        }
        return clsLoadClass;
    }

    public static void C(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, Map<String, c> map2, Field[] fieldArr) {
        String strLabel;
        int i8;
        int i9;
        int i10;
        for (Field field : fieldArr) {
            if (!Modifier.isStatic(field.getModifiers())) {
                d2.b bVar = (d2.b) N(field, d2.b.class);
                String name = field.getName();
                if (bVar == null) {
                    strLabel = null;
                    i8 = 0;
                    i9 = 0;
                    i10 = 0;
                } else if (bVar.serialize()) {
                    int iOrdinal = bVar.ordinal();
                    int iOf = SerializerFeature.of(bVar.serialzeFeatures());
                    int iOf2 = Feature.of(bVar.parseFeatures());
                    if (bVar.name().length() != 0) {
                        name = bVar.name();
                    }
                    strLabel = bVar.label().length() != 0 ? bVar.label() : null;
                    i8 = iOrdinal;
                    i9 = iOf;
                    i10 = iOf2;
                }
                if (map == null || (name = map.get(name)) != null) {
                    if (propertyNamingStrategy != null) {
                        name = propertyNamingStrategy.translate(name);
                    }
                    String str = name;
                    if (!map2.containsKey(str)) {
                        map2.put(str, new c(str, null, field, cls, null, i8, i9, i10, null, bVar, strLabel));
                    }
                }
            }
        }
    }

    public static long C0(Number number) {
        return number instanceof BigDecimal ? ((BigDecimal) number).longValueExact() : number.longValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:138:0x02a0, code lost:
    
        if (r2 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x032f, code lost:
    
        if (r11 == null) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:224:0x044e, code lost:
    
        if (r3 == null) goto L265;
     */
    /* JADX WARN: Code restructure failed: missing block: B:245:0x04a8, code lost:
    
        if (r1 == null) goto L267;
     */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0514 A[PHI: r12
  0x0514: PHI (r12v5 java.lang.Class<?>) = (r12v4 java.lang.Class<?>), (r12v10 java.lang.Class<?>), (r12v10 java.lang.Class<?>) binds: [B:265:0x0512, B:228:0x0458, B:238:0x0479] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<l2.c> D(java.lang.Class<?> r40, d2.d r41, java.util.Map<java.lang.String, java.lang.String> r42, java.util.Map<java.lang.String, java.lang.reflect.Field> r43, boolean r44, com.alibaba.fastjson.PropertyNamingStrategy r45) {
        /*
            Method dump skipped, instruction units count: 1339
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.l.D(java.lang.Class, d2.d, java.util.Map, java.util.Map, boolean, com.alibaba.fastjson.PropertyNamingStrategy):java.util.List");
    }

    public static long D0(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0L;
        }
        int iScale = bigDecimal.scale();
        return (iScale < -100 || iScale > 100) ? bigDecimal.longValueExact() : bigDecimal.longValue();
    }

    public static List<c> E(Class<?> cls, Map<String, String> map, boolean z7, PropertyNamingStrategy propertyNamingStrategy) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            C(superclass, map, propertyNamingStrategy, linkedHashMap, superclass.getDeclaredFields());
        }
        return X(cls, z7, linkedHashMap);
    }

    public static ParameterizedType E0(Class<?> cls, Type[] typeArr, Map<TypeVariable, Type> map) {
        int length = typeArr.length;
        Type[] typeArr2 = new Type[length];
        for (int i8 = 0; i8 < length; i8++) {
            typeArr2[i8] = L(typeArr[i8], map);
        }
        return new h(typeArr2, null, cls);
    }

    public static Map<TypeVariable, Type> F(TypeVariable[] typeVariableArr, Type[] typeArr) {
        int length = typeVariableArr.length;
        HashMap map = new HashMap(length);
        for (int i8 = 0; i8 < length; i8++) {
            map.put(typeVariableArr[i8], typeArr[i8]);
        }
        return map;
    }

    public static int F0(char c8, char c9) {
        if (c8 < '0' || c8 > '9' || c9 < '0' || c9 > '9') {
            return -1;
        }
        return ((c8 - '0') * 10) + (c9 - '0');
    }

    public static Collection G(Type type) {
        Class<?> cls;
        Class<?> clsH0 = h0(type);
        if (clsH0 == AbstractCollection.class || clsH0 == Collection.class) {
            return new ArrayList();
        }
        if (clsH0.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (clsH0.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (clsH0.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (clsH0.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (clsH0.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf((Class) (type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class));
        }
        if (clsH0.isAssignableFrom(Queue.class) || ((cls = P) != null && clsH0.isAssignableFrom(cls))) {
            return new LinkedList();
        }
        try {
            return (Collection) clsH0.newInstance();
        } catch (Exception unused) {
            throw new JSONException("create instance error, class " + clsH0.getName());
        }
    }

    public static int G0(char c8, char c9, char c10, char c11) {
        if (c8 < '0' || c8 > '9' || c9 < '0' || c9 > '9' || c10 < '0' || c10 > '9' || c11 < '0' || c11 > '9') {
            return -1;
        }
        return ((c8 - '0') * 1000) + ((c9 - '0') * 100) + ((c10 - '0') * 10) + (c11 - '0');
    }

    public static String H(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        if (str.length() > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        char[] charArray = str.toCharArray();
        charArray[0] = Character.toLowerCase(charArray[0]);
        return new String(charArray);
    }

    public static int H0(char c8, char c9, char c10, char c11, char c12, char c13, char c14, char c15, char c16) {
        if (c8 < '0' || c8 > '9' || c9 < '0' || c9 > '9' || c10 < '0' || c10 > '9' || c11 < '0' || c11 > '9' || c12 < '0' || c12 > '9' || c13 < '0' || c13 > '9' || c14 < '0' || c14 > '9' || c15 < '0' || c15 > '9' || c16 < '0' || c16 > '9') {
            return -1;
        }
        return ((c8 - '0') * 100000000) + ((c9 - '0') * 10000000) + ((c10 - '0') * 1000000) + ((c11 - '0') * 100000) + ((c12 - '0') * 10000) + ((c13 - '0') * 1000) + ((c14 - '0') * 100) + ((c15 - '0') * 10) + (c16 - '0');
    }

    public static long I(String str) {
        long jCharAt = -3750763034362895579L;
        for (int i8 = 0; i8 < str.length(); i8++) {
            jCharAt = (jCharAt ^ ((long) str.charAt(i8))) * 1099511628211L;
        }
        return jCharAt;
    }

    public static double I0(String str) {
        double d8;
        double d9;
        int length = str.length();
        if (length > 10) {
            return Double.parseDouble(str);
        }
        long j8 = 0;
        boolean z7 = false;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            if (cCharAt == '-' && i9 == 0) {
                z7 = true;
            } else if (cCharAt == '.') {
                if (i8 != 0) {
                    return Double.parseDouble(str);
                }
                i8 = (length - i9) - 1;
            } else {
                if (cCharAt < '0' || cCharAt > '9') {
                    return Double.parseDouble(str);
                }
                j8 = (j8 * 10) + ((long) (cCharAt - '0'));
            }
        }
        if (z7) {
            j8 = -j8;
        }
        switch (i8) {
            case 0:
                return j8;
            case 1:
                d8 = j8;
                d9 = 10.0d;
                break;
            case 2:
                d8 = j8;
                d9 = 100.0d;
                break;
            case 3:
                d8 = j8;
                d9 = 1000.0d;
                break;
            case 4:
                d8 = j8;
                d9 = 10000.0d;
                break;
            case 5:
                d8 = j8;
                d9 = 100000.0d;
                break;
            case 6:
                d8 = j8;
                d9 = 1000000.0d;
                break;
            case 7:
                d8 = j8;
                d9 = 1.0E7d;
                break;
            case 8:
                d8 = j8;
                d9 = 1.0E8d;
                break;
            case 9:
                d8 = j8;
                d9 = 1.0E9d;
                break;
            default:
                return Double.parseDouble(str);
        }
        return d8 / d9;
    }

    public static long J(String str) {
        long j8 = -3750763034362895579L;
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (cCharAt != '_' && cCharAt != '-') {
                if (cCharAt >= 'A' && cCharAt <= 'Z') {
                    cCharAt = (char) (cCharAt + ' ');
                }
                j8 = (j8 ^ ((long) cCharAt)) * 1099511628211L;
            }
        }
        return j8;
    }

    public static float J0(String str) {
        float f8;
        float f9;
        int length = str.length();
        if (length >= 10) {
            return Float.parseFloat(str);
        }
        long j8 = 0;
        boolean z7 = false;
        int i8 = 0;
        for (int i9 = 0; i9 < length; i9++) {
            char cCharAt = str.charAt(i9);
            if (cCharAt == '-' && i9 == 0) {
                z7 = true;
            } else if (cCharAt == '.') {
                if (i8 != 0) {
                    return Float.parseFloat(str);
                }
                i8 = (length - i9) - 1;
            } else {
                if (cCharAt < '0' || cCharAt > '9') {
                    return Float.parseFloat(str);
                }
                j8 = (j8 * 10) + ((long) (cCharAt - '0'));
            }
        }
        if (z7) {
            j8 = -j8;
        }
        switch (i8) {
            case 0:
                return j8;
            case 1:
                f8 = j8;
                f9 = 10.0f;
                break;
            case 2:
                f8 = j8;
                f9 = 100.0f;
                break;
            case 3:
                f8 = j8;
                f9 = 1000.0f;
                break;
            case 4:
                f8 = j8;
                f9 = 10000.0f;
                break;
            case 5:
                f8 = j8;
                f9 = 100000.0f;
                break;
            case 6:
                f8 = j8;
                f9 = 1000000.0f;
                break;
            case 7:
                f8 = j8;
                f9 = 1.0E7f;
                break;
            case 8:
                f8 = j8;
                f9 = 1.0E8f;
                break;
            case 9:
                f8 = j8;
                f9 = 1.0E9f;
                break;
            default:
                return Float.parseFloat(str);
        }
        return f8 / f9;
    }

    public static long K(String str) {
        long j8 = -3750763034362895579L;
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (cCharAt >= 'A' && cCharAt <= 'Z') {
                cCharAt = (char) (cCharAt + ' ');
            }
            j8 = (j8 ^ ((long) cCharAt)) * 1099511628211L;
        }
        return j8;
    }

    public static void K0(AccessibleObject accessibleObject) {
        if (f6244d && !accessibleObject.isAccessible()) {
            try {
                accessibleObject.setAccessible(true);
            } catch (AccessControlException unused) {
                f6244d = false;
            }
        }
    }

    public static Type L(Type type, Map<TypeVariable, Type> map) {
        return type instanceof TypeVariable ? map.get(type) : type instanceof ParameterizedType ? E0(h0(type), ((ParameterizedType) type).getActualTypeArguments(), map) : type instanceof GenericArrayType ? new d(L(((GenericArrayType) type).getGenericComponentType(), map)) : type;
    }

    public static short L0(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return (short) 0;
        }
        int iScale = bigDecimal.scale();
        return (iScale < -100 || iScale > 100) ? bigDecimal.shortValueExact() : bigDecimal.shortValue();
    }

    public static <A extends Annotation> A M(Class<?> cls, Class<A> cls2) {
        A a8 = (A) cls.getAnnotation(cls2);
        Type mixInAnnotations = c2.a.getMixInAnnotations(cls);
        Class cls3 = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls3 != null) {
            A a9 = (A) cls3.getAnnotation(cls2);
            Annotation[] annotations = cls3.getAnnotations();
            if (a9 == null && annotations.length > 0) {
                for (Annotation annotation : annotations) {
                    a9 = (A) annotation.annotationType().getAnnotation(cls2);
                    if (a9 != null) {
                        break;
                    }
                }
            }
            if (a9 != null) {
                return a9;
            }
        }
        Annotation[] annotations2 = cls.getAnnotations();
        if (a8 == null && annotations2.length > 0) {
            for (Annotation annotation2 : annotations2) {
                a8 = (A) annotation2.annotationType().getAnnotation(cls2);
                if (a8 != null) {
                    break;
                }
            }
        }
        return a8;
    }

    public static Locale M0(String str) {
        String[] strArrSplit = str.split(BydPowerManagerDevice.SEPARATOR_K_V);
        return strArrSplit.length == 1 ? new Locale(strArrSplit[0]) : strArrSplit.length == 2 ? new Locale(strArrSplit[0], strArrSplit[1]) : new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
    }

    public static <A extends Annotation> A N(Field field, Class<A> cls) {
        A a8;
        A a9 = (A) field.getAnnotation(cls);
        Type mixInAnnotations = c2.a.getMixInAnnotations(field.getDeclaringClass());
        Field declaredField = null;
        Class superclass = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (superclass != null) {
            String name = field.getName();
            while (superclass != null && superclass != Object.class) {
                try {
                    declaredField = superclass.getDeclaredField(name);
                    break;
                } catch (NoSuchFieldException unused) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (declaredField != null && (a8 = (A) declaredField.getAnnotation(cls)) != null) {
                return a8;
            }
        }
        return a9;
    }

    public static Type N0(Type type) {
        if (!f6249i) {
            try {
                f6250j = Class.forName("java.util.Optional");
            } catch (Exception unused) {
            } catch (Throwable th) {
                f6249i = true;
                throw th;
            }
            f6249i = true;
        }
        if (!(type instanceof ParameterizedType)) {
            return type;
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return parameterizedType.getRawType() == f6250j ? parameterizedType.getActualTypeArguments()[0] : type;
    }

    public static <A extends Annotation> A O(Method method, Class<A> cls) {
        A a8;
        A a9 = (A) method.getAnnotation(cls);
        Type mixInAnnotations = c2.a.getMixInAnnotations(method.getDeclaringClass());
        Method declaredMethod = null;
        Class superclass = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (superclass != null) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            while (superclass != null && superclass != Object.class) {
                try {
                    declaredMethod = superclass.getDeclaredMethod(name, parameterTypes);
                    break;
                } catch (NoSuchMethodException unused) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (declaredMethod != null && (a8 = (A) declaredMethod.getAnnotation(cls)) != null) {
                return a8;
            }
        }
        return a9;
    }

    public static Class<?> P(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return P(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            Type type2 = ((TypeVariable) type).getBounds()[0];
            return type2 instanceof Class ? (Class) type2 : P(type2);
        }
        if (!(type instanceof WildcardType)) {
            return Object.class;
        }
        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
        return upperBounds.length == 1 ? P(upperBounds[0]) : Object.class;
    }

    public static Class<?> Q(String str) {
        return C.get(str);
    }

    public static Class<?> R(Type type) {
        if (!(type instanceof ParameterizedType)) {
            return Object.class;
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type2).getUpperBounds();
            if (upperBounds.length == 1) {
                type2 = upperBounds[0];
            }
        }
        if (!(type2 instanceof Class)) {
            throw new JSONException("can not create ASMParser");
        }
        Class<?> cls = (Class) type2;
        if (Modifier.isPublic(cls.getModifiers())) {
            return cls;
        }
        throw new JSONException("can not create ASMParser");
    }

    public static Type S(Class<?> cls) {
        return cls.getName().startsWith("java.") ? Object.class : U(V(cls));
    }

    public static Type T(ParameterizedType parameterizedType) {
        Type rawType = parameterizedType.getRawType();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (rawType == Collection.class) {
            return j0(actualTypeArguments[0]);
        }
        Class cls = (Class) rawType;
        Map<TypeVariable, Type> mapF = F(cls.getTypeParameters(), actualTypeArguments);
        Type typeV = V(cls);
        if (!(typeV instanceof ParameterizedType)) {
            return S((Class) typeV);
        }
        Class<?> clsH0 = h0(typeV);
        Type[] actualTypeArguments2 = ((ParameterizedType) typeV).getActualTypeArguments();
        return actualTypeArguments2.length > 0 ? T(E0(clsH0, actualTypeArguments2, mapF)) : S(clsH0);
    }

    public static Type U(Type type) {
        return type instanceof ParameterizedType ? T((ParameterizedType) type) : type instanceof Class ? S((Class) type) : Object.class;
    }

    public static Type V(Class<?> cls) {
        Type type = null;
        for (Type type2 : cls.getGenericInterfaces()) {
            Class<?> clsH0 = h0(type2);
            if (clsH0 == Collection.class) {
                return type2;
            }
            if (Collection.class.isAssignableFrom(clsH0)) {
                type = type2;
            }
        }
        return type == null ? cls.getGenericSuperclass() : type;
    }

    public static Field W(Class<?> cls, String str, Field[] fieldArr) {
        char cCharAt;
        char cCharAt2;
        for (Field field : fieldArr) {
            String name = field.getName();
            if (str.equals(name)) {
                return field;
            }
            if (str.length() > 2 && (cCharAt = str.charAt(0)) >= 'a' && cCharAt <= 'z' && (cCharAt2 = str.charAt(1)) >= 'A' && cCharAt2 <= 'Z' && str.equalsIgnoreCase(name)) {
                return field;
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass == null || superclass == Object.class) {
            return null;
        }
        return W(superclass, str, superclass.getDeclaredFields());
    }

    public static List<c> X(Class<?> cls, boolean z7, Map<String, c> map) {
        ArrayList arrayList = new ArrayList();
        d2.d dVar = (d2.d) M(cls, d2.d.class);
        String[] strArrOrders = dVar != null ? dVar.orders() : null;
        if (strArrOrders == null || strArrOrders.length <= 0) {
            Iterator<c> it = map.values().iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            if (z7) {
                Collections.sort(arrayList);
            }
        } else {
            LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
            for (c cVar : map.values()) {
                linkedHashMap.put(cVar.f6169d, cVar);
            }
            for (String str : strArrOrders) {
                c cVar2 = (c) linkedHashMap.get(str);
                if (cVar2 != null) {
                    arrayList.add(cVar2);
                    linkedHashMap.remove(str);
                }
            }
            Iterator it2 = linkedHashMap.values().iterator();
            while (it2.hasNext()) {
                arrayList.add((c) it2.next());
            }
        }
        return arrayList;
    }

    public static Type Y(Type type) {
        return (!(type instanceof ParameterizedType) && (type instanceof Class)) ? Y(((Class) type).getGenericSuperclass()) : type;
    }

    public static String[] Z(Class cls) {
        if (f6262v == null && !f6261u) {
            try {
                f6262v = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getConstructor(Class.class);
            } catch (Throwable unused) {
                f6261u = true;
            }
        }
        if (f6262v == null) {
            return null;
        }
        if (f6263w == null && !f6261u) {
            try {
                f6263w = Class.forName("kotlin.reflect.jvm.internal.KClassImpl").getMethod("getConstructors", new Class[0]);
            } catch (Throwable unused2) {
                f6261u = true;
            }
        }
        if (f6264x == null && !f6261u) {
            try {
                f6264x = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
            } catch (Throwable unused3) {
                f6261u = true;
            }
        }
        if (f6265y == null && !f6261u) {
            try {
                f6265y = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable unused4) {
                f6261u = true;
            }
        }
        if (f6266z) {
            return null;
        }
        try {
            Iterator it = ((Iterable) f6263w.invoke(f6262v.newInstance(cls), new Object[0])).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object next = it.next();
                List list = (List) f6264x.invoke(next, new Object[0]);
                if (obj == null || list.size() != 0) {
                    obj = next;
                }
                it.hasNext();
            }
            if (obj == null) {
                return null;
            }
            List list2 = (List) f6264x.invoke(obj, new Object[0]);
            String[] strArr = new String[list2.size()];
            for (int i8 = 0; i8 < list2.size(); i8++) {
                strArr[i8] = (String) f6265y.invoke(list2.get(i8), new Object[0]);
            }
            return strArr;
        } catch (Throwable th) {
            th.printStackTrace();
            f6266z = true;
            return null;
        }
    }

    public static void a() {
        C.put("byte", Byte.TYPE);
        C.put("short", Short.TYPE);
        C.put("int", Integer.TYPE);
        C.put("long", Long.TYPE);
        C.put("float", Float.TYPE);
        C.put("double", Double.TYPE);
        C.put("boolean", Boolean.TYPE);
        C.put("char", Character.TYPE);
        C.put("[byte", byte[].class);
        C.put("[short", short[].class);
        C.put("[int", int[].class);
        C.put("[long", long[].class);
        C.put("[float", float[].class);
        C.put("[double", double[].class);
        C.put("[boolean", boolean[].class);
        C.put("[char", char[].class);
        C.put("[B", byte[].class);
        C.put("[S", short[].class);
        C.put("[I", int[].class);
        C.put("[J", long[].class);
        C.put("[F", float[].class);
        C.put("[D", double[].class);
        C.put("[C", char[].class);
        C.put("[Z", boolean[].class);
        Class<?>[] clsArr = {Object.class, Cloneable.class, z0("java.lang.AutoCloseable"), Exception.class, RuntimeException.class, IllegalAccessError.class, IllegalAccessException.class, IllegalArgumentException.class, IllegalMonitorStateException.class, IllegalStateException.class, IllegalThreadStateException.class, IndexOutOfBoundsException.class, InstantiationError.class, InstantiationException.class, InternalError.class, InterruptedException.class, LinkageError.class, NegativeArraySizeException.class, NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchFieldException.class, NoSuchMethodError.class, NoSuchMethodException.class, NullPointerException.class, NumberFormatException.class, OutOfMemoryError.class, SecurityException.class, StackOverflowError.class, StringIndexOutOfBoundsException.class, TypeNotPresentException.class, VerifyError.class, StackTraceElement.class, HashMap.class, Hashtable.class, TreeMap.class, IdentityHashMap.class, WeakHashMap.class, LinkedHashMap.class, HashSet.class, LinkedHashSet.class, TreeSet.class, ArrayList.class, TimeUnit.class, ConcurrentHashMap.class, AtomicInteger.class, AtomicLong.class, Collections.EMPTY_MAP.getClass(), Boolean.class, Character.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Number.class, String.class, BigDecimal.class, BigInteger.class, BitSet.class, Calendar.class, Date.class, Locale.class, UUID.class, Time.class, java.sql.Date.class, Timestamp.class, SimpleDateFormat.class, JSONObject.class, c2.c.class, JSONArray.class};
        for (int i8 = 0; i8 < 71; i8++) {
            Class<?> cls = clsArr[i8];
            if (cls != null) {
                C.put(cls.getName(), cls);
            }
        }
    }

    public static Constructor a0(Constructor[] constructorArr) {
        return b0(constructorArr, null);
    }

    public static void b(String str, Class<?> cls) {
        C.put(str, cls);
    }

    public static Constructor b0(Constructor[] constructorArr, String[] strArr) {
        Constructor constructor = null;
        for (Constructor constructor2 : constructorArr) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            if ((strArr == null || parameterTypes.length == strArr.length) && ((parameterTypes.length <= 0 || !parameterTypes[parameterTypes.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (constructor == null || constructor.getParameterTypes().length < parameterTypes.length))) {
                constructor = constructor2;
            }
        }
        return constructor;
    }

    public static z0 c(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy) {
        return d(cls, map, propertyNamingStrategy, false);
    }

    public static Annotation[][] c0(Constructor constructor) {
        Annotation[][] parameterAnnotations;
        Constructor declaredConstructor;
        Annotation[][] parameterAnnotations2 = constructor.getParameterAnnotations();
        Type mixInAnnotations = c2.a.getMixInAnnotations(constructor.getDeclaringClass());
        Constructor constructor2 = null;
        Class cls = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (cls != null) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            ArrayList arrayList = new ArrayList(2);
            for (Class<?> enclosingClass = cls.getEnclosingClass(); enclosingClass != null; enclosingClass = enclosingClass.getEnclosingClass()) {
                arrayList.add(enclosingClass);
            }
            int size = arrayList.size();
            Class superclass = cls;
            while (true) {
                if (superclass == null || superclass == Object.class) {
                    break;
                }
                try {
                    if (size != 0) {
                        Class<?>[] clsArr = new Class[parameterTypes.length + size];
                        System.arraycopy(parameterTypes, 0, clsArr, size, parameterTypes.length);
                        for (int i8 = size; i8 > 0; i8--) {
                            int i9 = i8 - 1;
                            clsArr[i9] = (Class) arrayList.get(i9);
                        }
                        declaredConstructor = cls.getDeclaredConstructor(clsArr);
                    } else {
                        declaredConstructor = cls.getDeclaredConstructor(parameterTypes);
                    }
                    constructor2 = declaredConstructor;
                } catch (NoSuchMethodException unused) {
                    size--;
                    superclass = superclass.getSuperclass();
                }
            }
            if (constructor2 != null && (parameterAnnotations = constructor2.getParameterAnnotations()) != null) {
                return parameterAnnotations;
            }
        }
        return parameterAnnotations2;
    }

    public static z0 d(Class<?> cls, Map<String, String> map, PropertyNamingStrategy propertyNamingStrategy, boolean z7) {
        PropertyNamingStrategy propertyNamingStrategy2;
        int i8;
        String[] strArr;
        String str;
        String str2;
        List<c> listE;
        d2.d dVar = (d2.d) M(cls, d2.d.class);
        if (dVar != null) {
            String[] strArrOrders = dVar.orders();
            String strTypeName = dVar.typeName();
            if (strTypeName.length() == 0) {
                strTypeName = null;
            }
            PropertyNamingStrategy propertyNamingStrategyNaming = dVar.naming();
            if (propertyNamingStrategyNaming == PropertyNamingStrategy.CamelCase) {
                propertyNamingStrategyNaming = propertyNamingStrategy;
            }
            int iOf = SerializerFeature.of(dVar.serialzeFeatures());
            String strTypeKey = null;
            for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                d2.d dVar2 = (d2.d) M(superclass, d2.d.class);
                if (dVar2 == null) {
                    break;
                }
                strTypeKey = dVar2.typeKey();
                if (strTypeKey.length() != 0) {
                    break;
                }
            }
            for (Class<?> cls2 : cls.getInterfaces()) {
                d2.d dVar3 = (d2.d) M(cls2, d2.d.class);
                if (dVar3 != null) {
                    strTypeKey = dVar3.typeKey();
                    if (strTypeKey.length() != 0) {
                        break;
                    }
                }
            }
            str2 = (strTypeKey == null || strTypeKey.length() != 0) ? strTypeKey : null;
            strArr = strArrOrders;
            str = strTypeName;
            propertyNamingStrategy2 = propertyNamingStrategyNaming;
            i8 = iOf;
        } else {
            propertyNamingStrategy2 = propertyNamingStrategy;
            i8 = 0;
            strArr = null;
            str = null;
            str2 = null;
        }
        HashMap map2 = new HashMap();
        f2.h.w(cls, map2);
        List<c> listE2 = z7 ? E(cls, map, false, propertyNamingStrategy2) : D(cls, dVar, map, map2, false, propertyNamingStrategy2);
        c[] cVarArr = new c[listE2.size()];
        listE2.toArray(cVarArr);
        if (strArr == null || strArr.length == 0) {
            ArrayList arrayList = new ArrayList(listE2);
            Collections.sort(arrayList);
            listE = arrayList;
        } else {
            listE = z7 ? E(cls, map, true, propertyNamingStrategy2) : D(cls, dVar, map, map2, true, propertyNamingStrategy2);
        }
        c[] cVarArr2 = new c[listE.size()];
        listE.toArray(cVarArr2);
        return new z0(cls, dVar, str, str2, i8, cVarArr, Arrays.equals(cVarArr2, cVarArr) ? cVarArr : cVarArr2);
    }

    public static Annotation[][] d0(Method method) {
        Annotation[][] parameterAnnotations;
        Annotation[][] parameterAnnotations2 = method.getParameterAnnotations();
        Type mixInAnnotations = c2.a.getMixInAnnotations(method.getDeclaringClass());
        Method declaredMethod = null;
        Class superclass = mixInAnnotations instanceof Class ? (Class) mixInAnnotations : null;
        if (superclass != null) {
            String name = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();
            while (superclass != null && superclass != Object.class) {
                try {
                    declaredMethod = superclass.getDeclaredMethod(name, parameterTypes);
                    break;
                } catch (NoSuchMethodException unused) {
                    superclass = superclass.getSuperclass();
                }
            }
            if (declaredMethod != null && (parameterAnnotations = declaredMethod.getParameterAnnotations()) != null) {
                return parameterAnnotations;
            }
        }
        return parameterAnnotations2;
    }

    public static byte e(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return (byte) 0;
        }
        int iScale = bigDecimal.scale();
        return (iScale < -100 || iScale > 100) ? bigDecimal.byteValueExact() : bigDecimal.byteValue();
    }

    public static int e0(Class<?> cls) {
        d2.d dVar = (d2.d) M(cls, d2.d.class);
        if (dVar == null) {
            return 0;
        }
        return Feature.of(dVar.parseFeatures());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T f(Object obj, Class<T> cls, f2.h hVar) {
        Object obj2;
        int i8 = 0;
        if (obj == 0) {
            if (cls == Integer.TYPE) {
                return (T) 0;
            }
            if (cls == Long.TYPE) {
                return (T) 0L;
            }
            if (cls == Short.TYPE) {
                return (T) (short) 0;
            }
            if (cls == Byte.TYPE) {
                return (T) (byte) 0;
            }
            if (cls == Float.TYPE) {
                return (T) Float.valueOf(0.0f);
            }
            if (cls == Double.TYPE) {
                return (T) Double.valueOf(0.0d);
            }
            if (cls == Boolean.TYPE) {
                return (T) Boolean.FALSE;
            }
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        if (cls == obj.getClass()) {
            return obj;
        }
        if (obj instanceof Map) {
            if (cls == Map.class) {
                return obj;
            }
            Map map = (Map) obj;
            return (cls != Object.class || map.containsKey(c2.a.DEFAULT_TYPE_KEY)) ? (T) v(map, cls, hVar) : obj;
        }
        if (cls.isArray()) {
            if (obj instanceof Collection) {
                Collection collection = (Collection) obj;
                T t7 = (T) Array.newInstance(cls.getComponentType(), collection.size());
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    Array.set(t7, i8, f(it.next(), cls.getComponentType(), hVar));
                    i8++;
                }
                return t7;
            }
            if (cls == byte[].class) {
                return (T) m(obj);
            }
        }
        if (cls.isAssignableFrom(obj.getClass())) {
            return obj;
        }
        if (cls == Boolean.TYPE || cls == Boolean.class) {
            return (T) k(obj);
        }
        if (cls == Byte.TYPE || cls == Byte.class) {
            return (T) l(obj);
        }
        if (cls == Character.TYPE || cls == Character.class) {
            return (T) n(obj);
        }
        if (cls == Short.TYPE || cls == Short.class) {
            return (T) x(obj);
        }
        if (cls == Integer.TYPE || cls == Integer.class) {
            return (T) t(obj);
        }
        if (cls == Long.TYPE || cls == Long.class) {
            return (T) w(obj);
        }
        if (cls == Float.TYPE || cls == Float.class) {
            return (T) s(obj);
        }
        if (cls == Double.TYPE || cls == Double.class) {
            return (T) q(obj);
        }
        if (cls == String.class) {
            return (T) A(obj);
        }
        if (cls == BigDecimal.class) {
            return (T) i(obj);
        }
        if (cls == BigInteger.class) {
            return (T) j(obj);
        }
        if (cls == Date.class) {
            return (T) o(obj);
        }
        if (cls == java.sql.Date.class) {
            return (T) y(obj);
        }
        if (cls == Time.class) {
            return (T) z(obj);
        }
        if (cls == Timestamp.class) {
            return (T) B(obj);
        }
        if (cls.isEnum()) {
            return (T) r(obj, cls, hVar);
        }
        if (Calendar.class.isAssignableFrom(cls)) {
            Date dateO = o(obj);
            if (cls == Calendar.class) {
                obj2 = (T) Calendar.getInstance(c2.a.defaultTimeZone, c2.a.defaultLocale);
            } else {
                try {
                    obj2 = (T) ((Calendar) cls.newInstance());
                } catch (Exception e8) {
                    throw new JSONException("can not cast to : " + cls.getName(), e8);
                }
            }
            ((Calendar) obj2).setTime(dateO);
            return (T) obj2;
        }
        String name = cls.getName();
        if (name.equals("javax.xml.datatype.XMLGregorianCalendar")) {
            Date dateO2 = o(obj);
            Calendar calendar = Calendar.getInstance(c2.a.defaultTimeZone, c2.a.defaultLocale);
            calendar.setTime(dateO2);
            return (T) p.f5296a.g(calendar);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (cls == Currency.class) {
                return (T) Currency.getInstance(str);
            }
            if (cls == Locale.class) {
                return (T) M0(str);
            }
            if (name.startsWith("java.time.")) {
                return (T) c2.a.parseObject(c2.a.toJSONString(str), cls);
            }
        }
        if (hVar.l(cls) != null) {
            return (T) c2.a.parseObject(c2.a.toJSONString(obj), cls);
        }
        throw new JSONException("can not cast to : " + cls.getName());
    }

    public static String f0(Map<String, Field> map, String str, String str2, int i8) {
        if (!f6243c || map.containsKey(str2)) {
            return str2;
        }
        String strSubstring = str.substring(i8);
        return map.containsKey(strSubstring) ? strSubstring : str2;
    }

    /* JADX WARN: Type inference failed for: r6v13, types: [T, java.util.Map$Entry] */
    /* JADX WARN: Type inference failed for: r7v1, types: [T, java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v9, types: [T, java.util.HashMap, java.util.Map] */
    public static <T> T g(Object obj, ParameterizedType parameterizedType, f2.h hVar) {
        Type rawType = parameterizedType.getRawType();
        if (rawType == List.class || rawType == ArrayList.class) {
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof List) {
                List list = (List) obj;
                ?? r7 = (T) new ArrayList(list.size());
                for (int i8 = 0; i8 < list.size(); i8++) {
                    Object obj2 = list.get(i8);
                    r7.add(type instanceof Class ? (obj2 == null || obj2.getClass() != JSONObject.class) ? f(obj2, (Class) type, hVar) : ((JSONObject) obj2).toJavaObject((Class) type, hVar, 0) : h(obj2, type, hVar));
                }
                return r7;
            }
        }
        if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == Collection.class || rawType == List.class || rawType == ArrayList.class) {
            Type type2 = parameterizedType.getActualTypeArguments()[0];
            if (obj instanceof Iterable) {
                T t7 = (rawType == Set.class || rawType == HashSet.class) ? (T) new HashSet() : rawType == TreeSet.class ? (T) new TreeSet() : (T) new ArrayList();
                Iterator<T> it = ((Iterable) obj).iterator();
                while (it.hasNext()) {
                    T next = it.next();
                    ((Collection) t7).add(type2 instanceof Class ? (next == null || next.getClass() != JSONObject.class) ? f(next, (Class) type2, hVar) : ((JSONObject) next).toJavaObject((Class) type2, hVar, 0) : h(next, type2, hVar));
                }
                return t7;
            }
        }
        if (rawType == Map.class || rawType == HashMap.class) {
            Type type3 = parameterizedType.getActualTypeArguments()[0];
            Type type4 = parameterizedType.getActualTypeArguments()[1];
            if (obj instanceof Map) {
                ?? r72 = (T) new HashMap();
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    r72.put(h(entry.getKey(), type3, hVar), h(entry.getValue(), type4, hVar));
                }
                return r72;
            }
        }
        if ((obj instanceof String) && ((String) obj).length() == 0) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (actualTypeArguments.length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
            return (T) h(obj, rawType, hVar);
        }
        if (rawType == Map.Entry.class && (obj instanceof Map)) {
            Map map = (Map) obj;
            if (map.size() == 1) {
                ?? r62 = (T) ((Map.Entry) map.entrySet().iterator().next());
                Object value = r62.getValue();
                if (actualTypeArguments.length == 2 && (value instanceof Map)) {
                    r62.setValue(h(value, actualTypeArguments[1], hVar));
                }
                return r62;
            }
        }
        if (rawType instanceof Class) {
            if (hVar == null) {
                hVar = f2.h.f4913g;
            }
            t tVarO = hVar.o(rawType);
            if (tVarO != null) {
                return (T) tVarO.b(new f2.a(c2.a.toJSONString(obj), hVar), parameterizedType, null);
            }
        }
        throw new JSONException("can not cast to : " + parameterizedType);
    }

    public static String g0(String str) {
        return Character.toLowerCase(str.charAt(3)) + str.substring(4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T h(Object obj, Type type, f2.h hVar) {
        if (obj == 0) {
            return null;
        }
        if (type instanceof Class) {
            return (T) f(obj, (Class) type, hVar);
        }
        if (type instanceof ParameterizedType) {
            return (T) g(obj, (ParameterizedType) type, hVar);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    public static Class<?> h0(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return h0(((ParameterizedType) type).getRawType());
        }
        if (!(type instanceof WildcardType)) {
            throw new JSONException("TODO");
        }
        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
        if (upperBounds.length == 1) {
            return h0(upperBounds[0]);
        }
        throw new JSONException("TODO");
    }

    public static BigDecimal i(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return (BigDecimal) obj;
        }
        if (obj instanceof BigInteger) {
            return new BigDecimal((BigInteger) obj);
        }
        String string = obj.toString();
        if (string.length() == 0) {
            return null;
        }
        if ((obj instanceof Map) && ((Map) obj).size() == 0) {
            return null;
        }
        if (string.length() <= 65535) {
            return new BigDecimal(string);
        }
        throw new JSONException("decimal overflow");
    }

    public static d2.b i0(Class<?> cls, Method method) {
        boolean z7;
        d2.b bVar;
        boolean z8;
        d2.b bVar2;
        Class<?>[] interfaces = cls.getInterfaces();
        if (interfaces.length > 0) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (Class<?> cls2 : interfaces) {
                for (Method method2 : cls2.getMethods()) {
                    Class<?>[] parameterTypes2 = method2.getParameterTypes();
                    if (parameterTypes2.length == parameterTypes.length && method2.getName().equals(method.getName())) {
                        int i8 = 0;
                        while (true) {
                            if (i8 >= parameterTypes.length) {
                                z8 = true;
                                break;
                            }
                            if (!parameterTypes2[i8].equals(parameterTypes[i8])) {
                                z8 = false;
                                break;
                            }
                            i8++;
                        }
                        if (z8 && (bVar2 = (d2.b) O(method2, d2.b.class)) != null) {
                            return bVar2;
                        }
                    }
                }
            }
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && Modifier.isAbstract(superclass.getModifiers())) {
            Class<?>[] parameterTypes3 = method.getParameterTypes();
            for (Method method3 : superclass.getMethods()) {
                Class<?>[] parameterTypes4 = method3.getParameterTypes();
                if (parameterTypes4.length == parameterTypes3.length && method3.getName().equals(method.getName())) {
                    int i9 = 0;
                    while (true) {
                        if (i9 >= parameterTypes3.length) {
                            z7 = true;
                            break;
                        }
                        if (!parameterTypes4[i9].equals(parameterTypes3[i9])) {
                            z7 = false;
                            break;
                        }
                        i9++;
                    }
                    if (z7 && (bVar = (d2.b) O(method3, d2.b.class)) != null) {
                        return bVar;
                    }
                }
            }
        }
        return null;
    }

    public static BigInteger j(Object obj) {
        BigDecimal bigDecimal;
        int iScale;
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigInteger) {
            return (BigInteger) obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double)) {
            return BigInteger.valueOf(((Number) obj).longValue());
        }
        if ((obj instanceof BigDecimal) && (iScale = (bigDecimal = (BigDecimal) obj).scale()) > -1000 && iScale < 1000) {
            return bigDecimal.toBigInteger();
        }
        String string = obj.toString();
        if (string.length() == 0 || "null".equals(string) || "NULL".equals(string)) {
            return null;
        }
        if (string.length() <= 65535) {
            return new BigInteger(string);
        }
        throw new JSONException("decimal overflow");
    }

    public static Type j0(Type type) {
        if (!(type instanceof WildcardType)) {
            return type;
        }
        Type[] upperBounds = ((WildcardType) type).getUpperBounds();
        return upperBounds.length > 0 ? upperBounds[0] : Object.class;
    }

    public static Boolean k(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof BigDecimal) {
            return Boolean.valueOf(k0((BigDecimal) obj) == 1);
        }
        if (obj instanceof Number) {
            return Boolean.valueOf(((Number) obj).intValue() == 1);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(str) || "0".equals(str)) {
                return Boolean.FALSE;
            }
            if ("Y".equalsIgnoreCase(str) || "T".equals(str)) {
                return Boolean.TRUE;
            }
            if ("F".equalsIgnoreCase(str) || "N".equals(str)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to boolean, value : " + obj);
    }

    public static int k0(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return 0;
        }
        int iScale = bigDecimal.scale();
        return (iScale < -100 || iScale > 100) ? bigDecimal.intValueExact() : bigDecimal.intValue();
    }

    public static Byte l(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Byte.valueOf(e((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Byte.valueOf(((Number) obj).byteValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(str));
        }
        if (obj instanceof Boolean) {
            return Byte.valueOf(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
        }
        throw new JSONException("can not cast to byte, value : " + obj);
    }

    public static boolean l0(Method method) {
        if (method == null) {
            return false;
        }
        if (f6255o == null && !f6256p) {
            try {
                f6255o = Class.forName("javax.persistence.ManyToMany");
            } catch (Throwable unused) {
                f6256p = true;
            }
        }
        if (f6255o != null) {
            return method.isAnnotationPresent(f6253m) || method.isAnnotationPresent(f6255o);
        }
        return false;
    }

    public static byte[] m(Object obj) {
        if (obj instanceof byte[]) {
            return (byte[]) obj;
        }
        if (obj instanceof String) {
            return e.c((String) obj);
        }
        throw new JSONException("can not cast to byte[], value : " + obj);
    }

    public static boolean m0(Method method) {
        if (method == null) {
            return false;
        }
        if (f6253m == null && !f6254n) {
            try {
                f6253m = Class.forName("javax.persistence.OneToMany");
            } catch (Throwable unused) {
                f6254n = true;
            }
        }
        Class<? extends Annotation> cls = f6253m;
        return cls != null && method.isAnnotationPresent(cls);
    }

    public static Character n(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Character) {
            return (Character) obj;
        }
        if (!(obj instanceof String)) {
            throw new JSONException("can not cast to char, value : " + obj);
        }
        String str = (String) obj;
        if (str.length() == 0) {
            return null;
        }
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw new JSONException("can not cast to char, value : " + obj);
    }

    public static boolean n0(Class cls) {
        if (H == null && !I) {
            try {
                H = Class.forName("java.sql.Clob");
            } catch (Throwable unused) {
                I = true;
            }
        }
        if (H == null) {
            return false;
        }
        return H.isAssignableFrom(cls);
    }

    public static Date o(Object obj) {
        return p(obj, null);
    }

    public static boolean o0(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type genericSuperclass = ((Class) type).getGenericSuperclass();
        return genericSuperclass != Object.class && o0(genericSuperclass);
    }

    public static Date p(Object obj, String str) {
        long j8;
        if (obj == null) {
            return null;
        }
        if (obj instanceof Date) {
            return (Date) obj;
        }
        if (obj instanceof Calendar) {
            return ((Calendar) obj).getTime();
        }
        if (obj instanceof BigDecimal) {
            return new Date(D0((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            long jLongValue = ((Number) obj).longValue();
            if ("unixtime".equals(str)) {
                jLongValue *= 1000;
            }
            return new Date(jLongValue);
        }
        if (obj instanceof String) {
            String strSubstring = (String) obj;
            f2.e eVar = new f2.e(strSubstring);
            try {
                if (eVar.k1(false)) {
                    return eVar.w0().getTime();
                }
                eVar.close();
                if (strSubstring.startsWith("/Date(") && strSubstring.endsWith(")/")) {
                    strSubstring = strSubstring.substring(6, strSubstring.length() - 2);
                }
                if (strSubstring.indexOf(45) > 0 || strSubstring.indexOf(43) > 0 || str != null) {
                    if (str == null) {
                        str = (strSubstring.length() == c2.a.DEFFAULT_DATE_FORMAT.length() || (strSubstring.length() == 22 && c2.a.DEFFAULT_DATE_FORMAT.equals("yyyyMMddHHmmssSSSZ"))) ? c2.a.DEFFAULT_DATE_FORMAT : strSubstring.length() == 10 ? BydDateUtils.FORMAT_DATE : strSubstring.length() == 19 ? BydDateUtils.DEF_FORMAT : (strSubstring.length() == 29 && strSubstring.charAt(26) == ':' && strSubstring.charAt(28) == '0') ? "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" : (strSubstring.length() == 23 && strSubstring.charAt(19) == ',') ? "yyyy-MM-dd HH:mm:ss,SSS" : "yyyy-MM-dd HH:mm:ss.SSS";
                    }
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, c2.a.defaultLocale);
                    simpleDateFormat.setTimeZone(c2.a.defaultTimeZone);
                    try {
                        return simpleDateFormat.parse(strSubstring);
                    } catch (ParseException unused) {
                        throw new JSONException("can not cast to Date, value : " + strSubstring);
                    }
                }
                if (strSubstring.length() == 0) {
                    return null;
                }
                j8 = Long.parseLong(strSubstring);
            } finally {
                eVar.close();
            }
        } else {
            j8 = -1;
        }
        if (j8 != -1) {
            return new Date(j8);
        }
        Class<?> cls = obj.getClass();
        if ("oracle.sql.TIMESTAMP".equals(cls.getName())) {
            if (f6246f == null && !f6245e) {
                try {
                    f6246f = cls.getMethod("toJdbc", new Class[0]);
                } catch (NoSuchMethodException unused2) {
                } catch (Throwable th) {
                    f6245e = true;
                    throw th;
                }
                f6245e = true;
            }
            try {
                return (Date) f6246f.invoke(obj, new Object[0]);
            } catch (Exception e8) {
                throw new JSONException("can not cast oracle.sql.TIMESTAMP to Date", e8);
            }
        }
        if (!"oracle.sql.DATE".equals(cls.getName())) {
            throw new JSONException("can not cast to Date, value : " + obj);
        }
        if (f6248h == null && !f6247g) {
            try {
                f6248h = cls.getMethod("toJdbc", new Class[0]);
            } catch (NoSuchMethodException unused3) {
            } catch (Throwable th2) {
                f6247g = true;
                throw th2;
            }
            f6247g = true;
        }
        try {
            return (Date) f6248h.invoke(obj, new Object[0]);
        } catch (Exception e9) {
            throw new JSONException("can not cast oracle.sql.DATE to Date", e9);
        }
    }

    public static boolean p0(Object obj) {
        if (obj == null) {
            return false;
        }
        if (f6257q == null && !f6258r) {
            try {
                f6257q = Class.forName("org.hibernate.Hibernate").getMethod("isInitialized", Object.class);
            } catch (Throwable unused) {
                f6258r = true;
            }
        }
        Method method = f6257q;
        if (method != null) {
            try {
                return ((Boolean) method.invoke(null, obj)).booleanValue();
            } catch (Throwable unused2) {
            }
        }
        return true;
    }

    public static Double q(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        if (!(obj instanceof String)) {
            if (obj instanceof Boolean) {
                return Double.valueOf(((Boolean) obj).booleanValue() ? 1.0d : 0.0d);
            }
            throw new JSONException("can not cast to double, value : " + obj);
        }
        String string = obj.toString();
        if (string.length() == 0 || "null".equals(string) || "NULL".equals(string)) {
            return null;
        }
        if (string.indexOf(44) != -1) {
            string = string.replaceAll(ListUtils.DEFAULT_JOIN_SEPARATOR, "");
        }
        return Double.valueOf(Double.parseDouble(string));
    }

    public static boolean q0(Class<?> cls, String str) {
        d2.d dVar = (d2.d) M(cls, d2.d.class);
        if (dVar != null) {
            String[] strArrIncludes = dVar.includes();
            if (strArrIncludes.length > 0) {
                for (String str2 : strArrIncludes) {
                    if (str.equals(str2)) {
                        return false;
                    }
                }
                return true;
            }
            for (String str3 : dVar.ignores()) {
                if (str.equals(str3)) {
                    return true;
                }
            }
        }
        if (cls.getSuperclass() == Object.class || cls.getSuperclass() == null) {
            return false;
        }
        return q0(cls.getSuperclass(), str);
    }

    public static <T> T r(Object obj, Class<T> cls, f2.h hVar) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                if (hVar == null) {
                    hVar = f2.h.s();
                }
                t tVarO = hVar.o(cls);
                return tVarO instanceof g2.h ? (T) ((g2.h) tVarO).c(I(str)) : (T) Enum.valueOf(cls, str);
            }
            if (obj instanceof BigDecimal) {
                int iK0 = k0((BigDecimal) obj);
                T[] enumConstants = cls.getEnumConstants();
                if (iK0 < enumConstants.length) {
                    return enumConstants[iK0];
                }
            }
            if (obj instanceof Number) {
                int iIntValue = ((Number) obj).intValue();
                T[] enumConstants2 = cls.getEnumConstants();
                if (iIntValue < enumConstants2.length) {
                    return enumConstants2[iIntValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e8) {
            throw new JSONException("can not cast to : " + cls.getName(), e8);
        }
    }

    public static boolean r0(Method method) {
        if (method == null) {
            return false;
        }
        if (F == null && !G) {
            try {
                F = Class.forName("com.fasterxml.jackson.annotation.JsonCreator");
            } catch (Throwable unused) {
                G = true;
            }
        }
        Class<? extends Annotation> cls = F;
        return cls != null && method.isAnnotationPresent(cls);
    }

    public static Float s(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return Float.valueOf(((Number) obj).floatValue());
        }
        if (!(obj instanceof String)) {
            if (obj instanceof Boolean) {
                return Float.valueOf(((Boolean) obj).booleanValue() ? 1.0f : 0.0f);
            }
            throw new JSONException("can not cast to float, value : " + obj);
        }
        String string = obj.toString();
        if (string.length() == 0 || "null".equals(string) || "NULL".equals(string)) {
            return null;
        }
        if (string.indexOf(44) != -1) {
            string = string.replaceAll(ListUtils.DEFAULT_JOIN_SEPARATOR, "");
        }
        return Float.valueOf(Float.parseFloat(string));
    }

    public static boolean s0(Class cls) {
        if (f6259s == null && !f6260t) {
            try {
                f6259s = Class.forName("kotlin.Metadata");
            } catch (Throwable unused) {
                f6260t = true;
            }
        }
        return f6259s != null && cls.isAnnotationPresent(f6259s);
    }

    public static Integer t(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof BigDecimal) {
            return Integer.valueOf(k0((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            String strReplaceAll = (String) obj;
            if (strReplaceAll.length() == 0 || "null".equals(strReplaceAll) || "NULL".equals(strReplaceAll)) {
                return null;
            }
            if (strReplaceAll.indexOf(44) != -1) {
                strReplaceAll = strReplaceAll.replaceAll(ListUtils.DEFAULT_JOIN_SEPARATOR, "");
            }
            Matcher matcher = f6241a.matcher(strReplaceAll);
            if (matcher.find()) {
                strReplaceAll = matcher.replaceAll("");
            }
            return Integer.valueOf(Integer.parseInt(strReplaceAll));
        }
        if (obj instanceof Boolean) {
            return Integer.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return t(it.next());
            }
        }
        throw new JSONException("can not cast to int, value : " + obj);
    }

    public static boolean t0(Class cls, String str) {
        if (A == null && !B) {
            try {
                HashMap map = new HashMap();
                map.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                A = map;
            } catch (Throwable unused) {
                B = true;
            }
        }
        if (A == null) {
            return false;
        }
        String[] strArr = A.get(cls);
        return strArr != null && Arrays.binarySearch(strArr, str) >= 0;
    }

    public static <T> T u(Object obj, Class<T> cls) {
        return (T) f(obj, cls, f2.h.s());
    }

    public static boolean u0(String str) {
        for (int i8 = 0; i8 < str.length(); i8++) {
            char cCharAt = str.charAt(i8);
            if (cCharAt == '+' || cCharAt == '-') {
                if (i8 != 0) {
                    return false;
                }
            } else if (cCharAt < '0' || cCharAt > '9') {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T v(Map<String, Object> map, Class<T> cls, f2.h hVar) {
        int iIntValueExact = 0;
        try {
            if (cls == StackTraceElement.class) {
                String str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number != null) {
                    iIntValueExact = number instanceof BigDecimal ? ((BigDecimal) number).intValueExact() : number.intValue();
                }
                return (T) new StackTraceElement(str, str2, str3, iIntValueExact);
            }
            Object obj = map.get(c2.a.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (hVar == null) {
                    hVar = f2.h.f4913g;
                }
                Class<?> clsH = hVar.h(str4, null);
                if (clsH == null) {
                    throw new ClassNotFoundException(str4 + " not found");
                }
                if (!clsH.equals(cls)) {
                    return (T) v(map, clsH, hVar);
                }
            }
            if (cls.isInterface()) {
                JSONObject jSONObject = map instanceof JSONObject ? (JSONObject) map : new JSONObject(map);
                if (hVar == null) {
                    hVar = f2.h.s();
                }
                return hVar.l(cls) != null ? (T) c2.a.parseObject(c2.a.toJSONString(jSONObject), cls) : (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, jSONObject);
            }
            if (cls == Locale.class) {
                Object obj2 = map.get("language");
                Object obj3 = map.get("country");
                if (obj2 instanceof String) {
                    String str5 = (String) obj2;
                    if (obj3 instanceof String) {
                        return (T) new Locale(str5, (String) obj3);
                    }
                    if (obj3 == null) {
                        return (T) new Locale(str5);
                    }
                }
            }
            if (cls == String.class && (map instanceof JSONObject)) {
                return (T) map.toString();
            }
            if (cls == c2.a.class && (map instanceof JSONObject)) {
                return map;
            }
            if (cls == LinkedHashMap.class && (map instanceof JSONObject)) {
                T t7 = (T) ((JSONObject) map).getInnerMap();
                if (t7 instanceof LinkedHashMap) {
                    return t7;
                }
                new LinkedHashMap().putAll(t7);
            }
            if (cls.isInstance(map)) {
                return map;
            }
            if (cls == JSONObject.class) {
                return (T) new JSONObject(map);
            }
            if (hVar == null) {
                hVar = f2.h.s();
            }
            t tVarO = hVar.o(cls);
            o oVar = tVarO instanceof o ? (o) tVarO : null;
            if (oVar != null) {
                return (T) oVar.g(map, hVar);
            }
            throw new JSONException("can not get javaBeanDeserializer. " + cls.getName());
        } catch (Exception e8) {
            throw new JSONException(e8.getMessage(), e8);
        }
    }

    public static boolean v0(Class<?> cls) {
        if (D == null && !E) {
            try {
                D = Class.forName("java.nio.file.Path");
            } catch (Throwable unused) {
                E = true;
            }
        }
        Class<?> cls2 = D;
        if (cls2 != null) {
            return cls2.isAssignableFrom(cls);
        }
        return false;
    }

    public static Long w(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Long.valueOf(D0((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Long.valueOf(((Number) obj).longValue());
        }
        if (obj instanceof String) {
            String strReplaceAll = (String) obj;
            if (strReplaceAll.length() == 0 || "null".equals(strReplaceAll) || "NULL".equals(strReplaceAll)) {
                return null;
            }
            if (strReplaceAll.indexOf(44) != -1) {
                strReplaceAll = strReplaceAll.replaceAll(ListUtils.DEFAULT_JOIN_SEPARATOR, "");
            }
            try {
                return Long.valueOf(Long.parseLong(strReplaceAll));
            } catch (NumberFormatException unused) {
                f2.e eVar = new f2.e(strReplaceAll);
                Calendar calendarW0 = eVar.k1(false) ? eVar.w0() : null;
                eVar.close();
                if (calendarW0 != null) {
                    return Long.valueOf(calendarW0.getTimeInMillis());
                }
            }
        }
        if (obj instanceof Map) {
            Map map = (Map) obj;
            if (map.size() == 2 && map.containsKey("andIncrement") && map.containsKey("andDecrement")) {
                Iterator it = map.values().iterator();
                it.next();
                return w(it.next());
            }
        }
        if (obj instanceof Boolean) {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1L : 0L);
        }
        throw new JSONException("can not cast to long, value : " + obj);
    }

    public static boolean w0(Class<?> cls) {
        for (Class<?> cls2 : cls.getInterfaces()) {
            String name = cls2.getName();
            if (name.equals("net.sf.cglib.proxy.Factory") || name.equals("org.springframework.cglib.proxy.Factory") || name.equals("javassist.util.proxy.ProxyObject") || name.equals("org.apache.ibatis.javassist.util.proxy.ProxyObject") || name.equals("org.hibernate.proxy.HibernateProxy")) {
                return true;
            }
        }
        return false;
    }

    public static Short x(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof BigDecimal) {
            return Short.valueOf(L0((BigDecimal) obj));
        }
        if (obj instanceof Number) {
            return Short.valueOf(((Number) obj).shortValue());
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(str));
        }
        if (obj instanceof Boolean) {
            return Short.valueOf(((Boolean) obj).booleanValue() ? (short) 1 : (short) 0);
        }
        throw new JSONException("can not cast to short, value : " + obj);
    }

    public static boolean x0(Method method) {
        if (method == null) {
            return false;
        }
        if (!f6251k) {
            try {
                f6252l = Class.forName("java.beans.Transient");
            } catch (Exception unused) {
            } catch (Throwable th) {
                f6251k = true;
                throw th;
            }
            f6251k = true;
        }
        Class<? extends Annotation> cls = f6252l;
        return (cls == null || O(method, cls) == null) ? false : true;
    }

    public static java.sql.Date y(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof java.sql.Date) {
            return (java.sql.Date) obj;
        }
        if (obj instanceof Date) {
            return new java.sql.Date(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new java.sql.Date(((Calendar) obj).getTimeInMillis());
        }
        long jD0 = obj instanceof BigDecimal ? D0((BigDecimal) obj) : obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str) || "NULL".equals(str)) {
                return null;
            }
            if (u0(str)) {
                jD0 = Long.parseLong(str);
            } else {
                f2.e eVar = new f2.e(str);
                if (!eVar.k1(false)) {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
                jD0 = eVar.w0().getTime().getTime();
            }
        }
        if (jD0 > 0) {
            return new java.sql.Date(jD0);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static boolean y0(Class cls) {
        Annotation annotationM;
        Object objInvoke;
        if (K == null && !L) {
            try {
                K = Class.forName("javax.xml.bind.annotation.XmlAccessorType");
            } catch (Throwable unused) {
                L = true;
            }
        }
        if (K == null || (annotationM = M(cls, K)) == null) {
            return false;
        }
        if (M == null && !L) {
            try {
                M = K.getMethod("value", new Class[0]);
            } catch (Throwable unused2) {
                L = true;
            }
        }
        if (M == null) {
            return false;
        }
        if (L) {
            objInvoke = null;
        } else {
            try {
                objInvoke = M.invoke(annotationM, new Object[0]);
            } catch (Throwable unused3) {
                L = true;
                objInvoke = null;
            }
        }
        if (objInvoke == null) {
            return false;
        }
        if (J == null && !L) {
            try {
                J = Class.forName("javax.xml.bind.annotation.XmlAccessType");
                N = J.getField("FIELD");
                O = N.get(null);
            } catch (Throwable unused4) {
                L = true;
            }
        }
        return objInvoke == O;
    }

    public static Time z(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Time) {
            return (Time) obj;
        }
        if (obj instanceof Date) {
            return new Time(((Date) obj).getTime());
        }
        if (obj instanceof Calendar) {
            return new Time(((Calendar) obj).getTimeInMillis());
        }
        long jD0 = obj instanceof BigDecimal ? D0((BigDecimal) obj) : obj instanceof Number ? ((Number) obj).longValue() : 0L;
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equalsIgnoreCase(str)) {
                return null;
            }
            if (u0(str)) {
                jD0 = Long.parseLong(str);
            } else {
                f2.e eVar = new f2.e(str);
                if (!eVar.k1(false)) {
                    throw new JSONException("can not cast to Timestamp, value : " + str);
                }
                jD0 = eVar.w0().getTime().getTime();
            }
        }
        if (jD0 > 0) {
            return new Time(jD0);
        }
        throw new JSONException("can not cast to Date, value : " + obj);
    }

    public static Class<?> z0(String str) {
        return A0(str, null);
    }
}
