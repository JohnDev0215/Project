package b.a.a.j;

import b.a.a.j.j.a0;
import b.a.a.j.j.b0;
import b.a.a.j.j.o;
import b.a.a.j.j.p;
import b.a.a.j.j.q;
import b.a.a.j.j.r;
import b.a.a.j.j.s;
import b.a.a.j.j.t;
import b.a.a.j.j.u;
import b.a.a.j.j.w;
import b.a.a.j.j.x;
import b.a.a.j.j.z;
import b.a.a.k.c0;
import b.a.a.k.d0;
import b.a.a.k.e1;
import b.a.a.k.k0;
import b.a.a.k.m;
import b.a.a.k.n;
import b.a.a.k.o0;
import b.a.a.k.q0;
import b.a.a.k.s0;
import b.a.a.k.x0;
import b.a.a.n.k;
import b.a.a.n.l;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.bydux.api.common.tools.util.ListUtils;
import java.io.Closeable;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessControlException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import javax.xml.datatype.XMLGregorianCalendar;

/* JADX INFO: compiled from: ParserConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f253c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f256f;
    public static h g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public List<b.a.a.l.a> A;
    public volatile List<a> B;
    public boolean C;
    public final b.a.a.n.f<Type, t> l;
    public final b.a.a.n.f<Type, b.a.a.n.f<Type, t>> m;
    public final ConcurrentMap<String, Class<?>> n;
    public boolean o;
    public final i p;
    public PropertyNamingStrategy q;
    public ClassLoader r;
    public b.a.a.j.j.a s;
    public boolean t;
    public long[] u;
    public long[] v;
    public long[] w;
    public final boolean x;
    public boolean y;
    public boolean z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f251a = z(b.a.a.n.e.k("fastjson.parser.deny.internal"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f252b = z(b.a.a.n.e.k("fastjson.parser.deny"));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f254d = "true".equals(b.a.a.n.e.k("fastjson.parser.autoTypeSupport"));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f255e = "true".equals(b.a.a.n.e.k("fastjson.parser.safeMode"));

    /* JADX INFO: compiled from: ParserConfig.java */
    public interface a {
        Class<?> a(String str, Class<?> cls, int i);
    }

    static {
        String[] strArrZ = z(b.a.a.n.e.k("fastjson.parser.autoTypeAccept"));
        if (strArrZ == null) {
            strArrZ = new String[0];
        }
        f253c = strArrZ;
        f256f = new long[]{-9013707057526259810L, -8773806119481270567L, -8421588593326113468L, -8070393259084821111L, -7858127399773263546L, -7043543676283957292L, -6976602508726000783L, -6293031534589903644L, -6081111809668363619L, -5779433778261875721L, -5399450433995651784L, -4540135604787511831L, -4207865850564917696L, -3950343444501679205L, -3714900953609113456L, -3393714734093696063L, -3378497329992063044L, -2631228350337215662L, -2551988546877734201L, -2473987886800209058L, -2265617974881722705L, -1759511109484434299L, -1477946458560579955L, -816725787720647462L, -520183782617964618L, 59775428743665658L, 484499585846206473L, 532945107123976213L, 711449177569584898L, 829148494126372070L, 956883420092542580L, 1233162291719202522L, 1696465274354442213L, 1863557081881630420L, 2238472697200138595L, 2380202963256720577L, 2643099543618286743L, 2793877891138577121L, 3804572268889088203L, 4567982875926242015L, 4784070066737926537L, 4960004821520561233L, 5348524593377618456L, 5454920836284873808L, 5695987590363189151L, 6073645722991901167L, 6114875255374330593L, 6137737446243999215L, 6160752908990493848L, 6939315124833099497L, 7048426940343117278L, 7267793227937552092L, 8331868837379820532L, 8357451534615459155L, 8890227807433646566L, 9166532985682478006L, 9215131087512669423L};
        g = new h();
        h = false;
        i = false;
        j = false;
        k = false;
    }

    public h() {
        this(false);
    }

    public static Method p(Class cls, Class cls2) {
        for (Method method : cls.getMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getReturnType() == cls2 && method.getParameterTypes().length == 1 && ((b.a.a.h.a) method.getAnnotation(b.a.a.h.a.class)) != null) {
                return method;
            }
        }
        return null;
    }

    public static Field r(String str, Map<String, Field> map) {
        Field field = map.get(str);
        if (field == null) {
            field = map.get("_" + str);
        }
        if (field == null) {
            field = map.get("m_" + str);
        }
        if (field != null) {
            return field;
        }
        char cCharAt = str.charAt(0);
        if (cCharAt >= 'a' && cCharAt <= 'z') {
            char[] charArray = str.toCharArray();
            charArray[0] = (char) (charArray[0] - ' ');
            field = map.get(new String(charArray));
        }
        if (str.length() <= 2) {
            return field;
        }
        char cCharAt2 = str.charAt(1);
        if (str.length() <= 2 || cCharAt < 'a' || cCharAt > 'z' || cCharAt2 < 'A' || cCharAt2 > 'Z') {
            return field;
        }
        for (Map.Entry<String, Field> entry : map.entrySet()) {
            if (str.equalsIgnoreCase(entry.getKey())) {
                return entry.getValue();
            }
        }
        return field;
    }

    public static h s() {
        return g;
    }

    public static boolean v(Class<?> cls) {
        return cls.isPrimitive() || cls == Boolean.class || cls == Character.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == BigInteger.class || cls == BigDecimal.class || cls == String.class || cls == Date.class || cls == java.sql.Date.class || cls == Time.class || cls == Timestamp.class || cls.isEnum();
    }

    public static void w(Class<?> cls, Map<String, Field> map) {
        for (Field field : cls.getDeclaredFields()) {
            String name = field.getName();
            if (!map.containsKey(name)) {
                map.put(name, field);
            }
        }
        if (cls.getSuperclass() == null || cls.getSuperclass() == Object.class) {
            return;
        }
        w(cls.getSuperclass(), map);
    }

    public static String[] z(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public void a(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jI = l.I(str);
        if (Arrays.binarySearch(this.w, jI) >= 0) {
            return;
        }
        long[] jArr = this.w;
        int length = jArr.length + 1;
        long[] jArr2 = new long[length];
        jArr2[length - 1] = jI;
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Arrays.sort(jArr2);
        this.w = jArr2;
    }

    public void b(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jI = l.I(str);
        if (Arrays.binarySearch(this.v, jI) >= 0) {
            return;
        }
        long[] jArr = this.v;
        int length = jArr.length + 1;
        long[] jArr2 = new long[length];
        jArr2[length - 1] = jI;
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Arrays.sort(jArr2);
        this.v = jArr2;
    }

    public void c(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jI = l.I(str);
        long[] jArr = this.u;
        if (jArr == null) {
            this.u = new long[]{jI};
            return;
        }
        if (Arrays.binarySearch(jArr, jI) >= 0) {
            return;
        }
        long[] jArr2 = this.u;
        int length = jArr2.length + 1;
        long[] jArr3 = new long[length];
        jArr3[length - 1] = jI;
        System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
        Arrays.sort(jArr3);
        this.u = jArr3;
    }

    public final void d(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            a(str);
        }
    }

    public final void e(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            b(str);
        }
    }

    public final void f(String[] strArr) {
        if (strArr == null) {
            return;
        }
        for (String str : strArr) {
            c(str);
        }
    }

    public Class<?> g(Class cls) {
        return l(cls) != null ? cls : i(cls.getName(), null, b.a.a.a.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> h(String str, Class<?> cls) {
        return i(str, cls, b.a.a.a.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARN: Removed duplicated region for block: B:164:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x030e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0055  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Class<?> i(java.lang.String r24, java.lang.Class<?> r25, int r26) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1029
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.a.a.j.h.i(java.lang.String, java.lang.Class, int):java.lang.Class");
    }

    public b.a.a.j.j.l j(h hVar, b.a.a.n.g gVar, b.a.a.n.c cVar) {
        Class<?> clsDeserializeUsing;
        Class<?> cls = gVar.f448a;
        Class<?> cls2 = cVar.f433e;
        b.a.a.h.b bVarD = cVar.d();
        Class<?> cls3 = null;
        if (bVarD != null && (clsDeserializeUsing = bVarD.deserializeUsing()) != Void.class) {
            cls3 = clsDeserializeUsing;
        }
        return (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) ? new b.a.a.j.j.c(hVar, cls, cVar) : new b.a.a.j.j.f(hVar, cls, cVar);
    }

    public t k(Class<?> cls, Type type) {
        b.a.a.h.b bVarD;
        Method method;
        b.a.a.j.j.a aVar;
        boolean zA = this.o & (!this.x);
        if (zA) {
            b.a.a.h.d dVar = (b.a.a.h.d) l.M(cls, b.a.a.h.d.class);
            if (dVar != null) {
                Class<?> clsDeserializer = dVar.deserializer();
                if (clsDeserializer != Void.class) {
                    try {
                        Object objNewInstance = clsDeserializer.newInstance();
                        if (objNewInstance instanceof t) {
                            return (t) objNewInstance;
                        }
                    } catch (Throwable unused) {
                    }
                }
                zA = dVar.asm() && dVar.parseFeatures().length == 0;
            }
            if (zA) {
                Class<?> clsF = b.a.a.n.g.f(cls, dVar);
                if (clsF == null) {
                    clsF = cls;
                }
                while (true) {
                    if (!Modifier.isPublic(clsF.getModifiers())) {
                        zA = false;
                        break;
                    }
                    clsF = clsF.getSuperclass();
                    if (clsF == Object.class || clsF == null) {
                        break;
                    }
                }
            }
        }
        if (cls.getTypeParameters().length != 0) {
            zA = false;
        }
        if (zA && (aVar = this.s) != null && aVar.f261c.c(cls)) {
            zA = false;
        }
        if (zA) {
            zA = b.a.a.n.b.a(cls.getSimpleName());
        }
        if (zA) {
            if (cls.isInterface()) {
                zA = false;
            }
            b.a.a.n.g gVarC = b.a.a.n.g.c(cls, type, this.q, false, l.f463b, this.y);
            if (zA && gVarC.h.length > 200) {
                zA = false;
            }
            Constructor<?> constructor = gVarC.f450c;
            if (zA && constructor == null && !cls.isInterface()) {
                zA = false;
            }
            for (b.a.a.n.c cVar : gVarC.h) {
                if (!cVar.h) {
                    Class<?> cls2 = cVar.f433e;
                    if (Modifier.isPublic(cls2.getModifiers()) && ((!cls2.isMemberClass() || Modifier.isStatic(cls2.getModifiers())) && ((cVar.l() == null || b.a.a.n.b.a(cVar.l().getName())) && (((bVarD = cVar.d()) == null || (b.a.a.n.b.a(bVarD.name()) && bVarD.format().length() == 0 && bVarD.deserializeUsing() == Void.class && bVarD.parseFeatures().length == 0 && !bVarD.unwrapped())) && (((method = cVar.f430b) == null || method.getParameterTypes().length <= 1) && (!cls2.isEnum() || (o(cls2) instanceof b.a.a.j.j.h))))))) {
                    }
                }
                zA = false;
                break;
            }
        }
        if (zA && cls.isMemberClass() && !Modifier.isStatic(cls.getModifiers())) {
            zA = false;
        }
        if (!((zA && l.y0(cls)) ? false : zA)) {
            return new o(this, cls, type);
        }
        b.a.a.n.g gVarB = b.a.a.n.g.b(cls, type, this.q);
        try {
            return this.s.v(this, gVarB);
        } catch (JSONException unused2) {
            return new o(this, gVarB);
        } catch (NoSuchMethodException unused3) {
            return new o(this, cls, type);
        } catch (Exception e2) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e2);
        }
    }

    public t l(Type type) {
        Type mixInAnnotations = b.a.a.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.l.b(type);
        }
        b.a.a.n.f<Type, t> fVarB = this.m.b(type);
        if (fVarB == null) {
            return null;
        }
        return fVarB.b(mixInAnnotations);
    }

    public ClassLoader m() {
        return this.r;
    }

    public t n(Class<?> cls, Type type) {
        t tVarK;
        t xVar;
        Class<?> clsMappingTo;
        Type type2 = type;
        t tVarL = l(type2);
        if (tVarL == null && (type2 instanceof b.a.a.n.h)) {
            tVarL = l(b.a.a.g.b((b.a.a.n.h) type2));
        }
        if (tVarL != null) {
            return tVarL;
        }
        if (type2 == null) {
            type2 = cls;
        }
        t tVarL2 = l(type2);
        if (tVarL2 != null) {
            return tVarL2;
        }
        b.a.a.h.d dVar = (b.a.a.h.d) l.M(cls, b.a.a.h.d.class);
        if (dVar != null && (clsMappingTo = dVar.mappingTo()) != Void.class) {
            return n(clsMappingTo, clsMappingTo);
        }
        if ((type2 instanceof WildcardType) || (type2 instanceof TypeVariable) || (type2 instanceof ParameterizedType)) {
            tVarL2 = l(cls);
        }
        if (tVarL2 != null) {
            return tVarL2;
        }
        Iterator<b.a.a.l.a> it = this.A.iterator();
        while (it.hasNext()) {
            tVarL2 = it.next().a(this, cls);
            if (tVarL2 != null) {
                x(type2, tVarL2);
                return tVarL2;
            }
        }
        String strReplace = cls.getName().replace('$', '.');
        if (strReplace.startsWith("java.awt.") && b.a.a.k.i.k(cls) && !h) {
            String[] strArr = {"java.awt.Point", "java.awt.Font", "java.awt.Rectangle", "java.awt.Color"};
            for (int i2 = 0; i2 < 4; i2++) {
                try {
                    String str = strArr[i2];
                    if (str.equals(strReplace)) {
                        Type cls2 = Class.forName(str);
                        t tVar = b.a.a.k.i.f369a;
                        x(cls2, tVar);
                        return tVar;
                    }
                } catch (Throwable unused) {
                    h = true;
                }
            }
            tVarL2 = b.a.a.k.i.f369a;
        }
        if (!i) {
            try {
                if (strReplace.startsWith("java.time.")) {
                    String[] strArr2 = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.ZoneId", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                    for (int i3 = 0; i3 < 12; i3++) {
                        String str2 = strArr2[i3];
                        if (str2.equals(strReplace)) {
                            Type cls3 = Class.forName(str2);
                            t tVar2 = q.f292a;
                            x(cls3, tVar2);
                            return tVar2;
                        }
                    }
                } else if (strReplace.startsWith("java.util.Optional")) {
                    String[] strArr3 = {"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"};
                    for (int i4 = 0; i4 < 4; i4++) {
                        String str3 = strArr3[i4];
                        if (str3.equals(strReplace)) {
                            Type cls4 = Class.forName(str3);
                            t tVar3 = u.f300a;
                            x(cls4, tVar3);
                            return tVar3;
                        }
                    }
                }
            } catch (Throwable unused2) {
                i = true;
            }
        }
        if (!j) {
            try {
                if (strReplace.startsWith("org.joda.time.")) {
                    String[] strArr4 = {"org.joda.time.DateTime", "org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.format.DateTimeFormatter"};
                    for (int i5 = 0; i5 < 9; i5++) {
                        String str4 = strArr4[i5];
                        if (str4.equals(strReplace)) {
                            Type cls5 = Class.forName(str4);
                            tVarL2 = k0.f376a;
                            x(cls5, tVarL2);
                            return tVarL2;
                        }
                    }
                }
            } catch (Throwable unused3) {
                j = true;
            }
        }
        if (!k && strReplace.startsWith("com.google.common.collect.")) {
            try {
                String[] strArr5 = {"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"};
                for (int i6 = 0; i6 < 5; i6++) {
                    String str5 = strArr5[i6];
                    if (str5.equals(strReplace)) {
                        Type cls6 = Class.forName(str5);
                        tVarL2 = c0.f341a;
                        x(cls6, tVarL2);
                        return tVarL2;
                    }
                }
            } catch (ClassNotFoundException unused4) {
                k = true;
            }
        }
        if (strReplace.equals("java.nio.ByteBuffer")) {
            tVarL2 = b.a.a.k.o.f390a;
            x(cls, tVarL2);
        }
        if (strReplace.equals("java.nio.file.Path")) {
            tVarL2 = q0.f398b;
            x(cls, tVarL2);
        }
        if (cls == Map.Entry.class) {
            tVarL2 = q0.f398b;
            x(cls, tVarL2);
        }
        if (strReplace.equals("org.javamoney.moneta.Money")) {
            tVarL2 = b.a.a.m.a.a.f423a;
            x(cls, tVarL2);
        }
        try {
            for (b.a.a.j.j.d dVar2 : k.a(b.a.a.j.j.d.class, Thread.currentThread().getContextClassLoader())) {
                Iterator<Type> it2 = dVar2.a().iterator();
                while (it2.hasNext()) {
                    x(it2.next(), dVar2);
                }
            }
        } catch (Exception unused5) {
        }
        if (tVarL2 == null) {
            tVarL2 = l(type2);
        }
        if (tVarL2 != null) {
            return tVarL2;
        }
        if (cls.isEnum()) {
            if (this.y) {
                for (Method method : cls.getMethods()) {
                    if (l.r0(method)) {
                        t tVarK2 = k(cls, type2);
                        x(type2, tVarK2);
                        return tVarK2;
                    }
                }
            }
            Class<?> cls7 = (Class) b.a.a.a.getMixInAnnotations(cls);
            b.a.a.h.d dVar3 = (b.a.a.h.d) l.M(cls7 != null ? cls7 : cls, b.a.a.h.d.class);
            if (dVar3 != null) {
                try {
                    t tVar4 = (t) dVar3.deserializer().newInstance();
                    x(cls, tVar4);
                    return tVar4;
                } catch (Throwable unused6) {
                }
            }
            Method methodP = null;
            if (cls7 != null) {
                Method methodP2 = p(cls7, cls);
                if (methodP2 != null) {
                    try {
                        methodP = cls.getMethod(methodP2.getName(), methodP2.getParameterTypes());
                    } catch (Exception unused7) {
                    }
                }
            } else {
                methodP = p(cls, cls);
            }
            if (methodP != null) {
                t gVar = new b.a.a.j.j.g(methodP);
                x(cls, gVar);
                return gVar;
            }
            tVarK = q(cls);
        } else if (cls.isArray()) {
            tVarK = s0.f403a;
        } else if (cls == Set.class || cls == HashSet.class || cls == Collection.class || cls == List.class || cls == ArrayList.class || Collection.class.isAssignableFrom(cls)) {
            tVarK = b.a.a.k.t.f404a;
        } else if (Map.class.isAssignableFrom(cls)) {
            tVarK = r.f298a;
        } else {
            if (Throwable.class.isAssignableFrom(cls)) {
                xVar = new b0(this, cls);
            } else if (w.class.isAssignableFrom(cls)) {
                xVar = new x(cls);
            } else {
                tVarK = cls == InetAddress.class ? q0.f398b : k(cls, type2);
            }
            tVarK = xVar;
        }
        x(type2, tVarK);
        return tVarK;
    }

    public t o(Type type) {
        t tVarL = l(type);
        if (tVarL != null) {
            return tVarL;
        }
        if (type instanceof Class) {
            return n((Class) type, type);
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            return rawType instanceof Class ? n((Class) rawType, type) : o(rawType);
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return o(upperBounds[0]);
            }
        }
        return p.f291a;
    }

    public t q(Class<?> cls) {
        return new b.a.a.j.j.h(cls);
    }

    public final void t() {
        b.a.a.n.f<Type, t> fVar = this.l;
        q0 q0Var = q0.f398b;
        fVar.c(SimpleDateFormat.class, q0Var);
        this.l.c(Timestamp.class, z.f307b);
        this.l.c(java.sql.Date.class, z.f306a);
        this.l.c(Time.class, b.a.a.j.j.c0.f273a);
        this.l.c(Date.class, b.a.a.k.w.f406a);
        b.a.a.n.f<Type, t> fVar2 = this.l;
        b.a.a.k.p pVar = b.a.a.k.p.f395a;
        fVar2.c(Calendar.class, pVar);
        this.l.c(XMLGregorianCalendar.class, pVar);
        this.l.c(JSONObject.class, r.f298a);
        b.a.a.n.f<Type, t> fVar3 = this.l;
        b.a.a.k.t tVar = b.a.a.k.t.f404a;
        fVar3.c(JSONArray.class, tVar);
        this.l.c(Map.class, r.f298a);
        this.l.c(HashMap.class, r.f298a);
        this.l.c(LinkedHashMap.class, r.f298a);
        this.l.c(TreeMap.class, r.f298a);
        this.l.c(ConcurrentMap.class, r.f298a);
        this.l.c(ConcurrentHashMap.class, r.f298a);
        this.l.c(Collection.class, tVar);
        this.l.c(List.class, tVar);
        this.l.c(ArrayList.class, tVar);
        b.a.a.n.f<Type, t> fVar4 = this.l;
        p pVar2 = p.f291a;
        fVar4.c(Object.class, pVar2);
        this.l.c(String.class, e1.f362a);
        this.l.c(StringBuffer.class, e1.f362a);
        this.l.c(StringBuilder.class, e1.f362a);
        b.a.a.n.f<Type, t> fVar5 = this.l;
        Class cls = Character.TYPE;
        b.a.a.k.r rVar = b.a.a.k.r.f401a;
        fVar5.c(cls, rVar);
        this.l.c(Character.class, rVar);
        b.a.a.n.f<Type, t> fVar6 = this.l;
        Class cls2 = Byte.TYPE;
        s sVar = s.f299a;
        fVar6.c(cls2, sVar);
        this.l.c(Byte.class, sVar);
        this.l.c(Short.TYPE, sVar);
        this.l.c(Short.class, sVar);
        this.l.c(Integer.TYPE, d0.f353a);
        this.l.c(Integer.class, d0.f353a);
        this.l.c(Long.TYPE, o0.f394a);
        this.l.c(Long.class, o0.f394a);
        this.l.c(BigInteger.class, m.f387c);
        this.l.c(BigDecimal.class, b.a.a.k.l.f384c);
        this.l.c(Float.TYPE, b.a.a.k.b0.f336a);
        this.l.c(Float.class, b.a.a.k.b0.f336a);
        this.l.c(Double.TYPE, sVar);
        this.l.c(Double.class, sVar);
        b.a.a.n.f<Type, t> fVar7 = this.l;
        Class cls3 = Boolean.TYPE;
        n nVar = n.f388a;
        fVar7.c(cls3, nVar);
        this.l.c(Boolean.class, nVar);
        this.l.c(Class.class, q0Var);
        this.l.c(char[].class, new b.a.a.k.q());
        this.l.c(AtomicBoolean.class, nVar);
        this.l.c(AtomicInteger.class, d0.f353a);
        this.l.c(AtomicLong.class, o0.f394a);
        b.a.a.n.f<Type, t> fVar8 = this.l;
        x0 x0Var = x0.f409a;
        fVar8.c(AtomicReference.class, x0Var);
        this.l.c(WeakReference.class, x0Var);
        this.l.c(SoftReference.class, x0Var);
        this.l.c(UUID.class, q0Var);
        this.l.c(TimeZone.class, q0Var);
        this.l.c(Locale.class, q0Var);
        this.l.c(Currency.class, q0Var);
        this.l.c(Inet4Address.class, q0Var);
        this.l.c(Inet6Address.class, q0Var);
        this.l.c(InetSocketAddress.class, q0Var);
        this.l.c(File.class, q0Var);
        this.l.c(URI.class, q0Var);
        this.l.c(URL.class, q0Var);
        this.l.c(Pattern.class, q0Var);
        this.l.c(Charset.class, q0Var);
        this.l.c(JSONPath.class, q0Var);
        this.l.c(Number.class, sVar);
        b.a.a.n.f<Type, t> fVar9 = this.l;
        b.a.a.k.g gVar = b.a.a.k.g.f367a;
        fVar9.c(AtomicIntegerArray.class, gVar);
        this.l.c(AtomicLongArray.class, gVar);
        this.l.c(StackTraceElement.class, a0.f269a);
        this.l.c(Serializable.class, pVar2);
        this.l.c(Cloneable.class, pVar2);
        this.l.c(Comparable.class, pVar2);
        this.l.c(Closeable.class, pVar2);
        this.l.c(b.a.a.c.class, new b.a.a.j.j.n());
    }

    public boolean u() {
        return this.y;
    }

    public void x(Type type, t tVar) {
        Type mixInAnnotations = b.a.a.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            this.l.c(type, tVar);
            return;
        }
        b.a.a.n.f<Type, t> fVarB = this.m.b(type);
        if (fVarB == null) {
            fVarB = new b.a.a.n.f<>(4);
            this.m.c(type, fVarB);
        }
        fVarB.c(mixInAnnotations, tVar);
    }

    public void y(boolean z) {
        this.o = z;
    }

    public h(boolean z) {
        this(null, null, z);
    }

    public h(b.a.a.j.j.a aVar, ClassLoader classLoader, boolean z) {
        this.l = new b.a.a.n.f<>();
        this.m = new b.a.a.n.f<>(16);
        this.n = new ConcurrentHashMap(16, 0.75f, 1);
        this.o = !b.a.a.n.b.f428b;
        this.p = new i(4096);
        this.t = f254d;
        this.y = false;
        this.z = l.f463b;
        this.A = new ArrayList();
        this.C = f255e;
        this.v = new long[]{-9164606388214699518L, -8720046426850100497L, -8649961213709896794L, -8165637398350707645L, -8109300701639721088L, -7966123100503199569L, -7921218830998286408L, -7775351613326101303L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6316154655839304624L, -6179589609550493385L, -6149093380703242441L, -6025144546313590215L, -5939269048541779808L, -5885964883385605994L, -5764804792063216819L, -5472097725414717105L, -5194641081268104286L, -5076846148177416215L, -4837536971810737970L, -4703320437989596122L, -4608341446948126581L, -4537258998789938600L, -4438775680185074100L, -4314457471973557243L, -4150995715611818742L, -4082057040235125754L, -3975378478825053783L, -3935185854875733362L, -3319207949486691020L, -3077205613010077203L, -3053747177772160511L, -2995060141064716555L, -2825378362173150292L, -2533039401923731906L, -2439930098895578154L, -2378990704010641148L, -2364987994247679115L, -2262244760619952081L, -2192804397019347313L, -2095516571388852610L, -1872417015366588117L, -1650485814983027158L, -1589194880214235129L, -965955008570215305L, -905177026366752536L, -831789045734283466L, -582813228520337988L, -254670111376247151L, -219577392946377768L, -190281065685395680L, -26639035867733124L, -9822483067882491L, 4750336058574309L, 33238344207745342L, 156405680656087946L, 218512992947536312L, 313864100207897507L, 386461436234701831L, 823641066473609950L, 1073634739308289776L, 1153291637701043748L, 1203232727967308606L, 1214780596910349029L, 1459860845934817624L, 1502845958873959152L, 1534439610567445754L, 1698504441317515818L, 1818089308493370394L, 2078113382421334967L, 2164696723069287854L, 2622551729063269307L, 2653453629929770569L, 2660670623866180977L, 2731823439467737506L, 2836431254737891113L, 2930861374593775110L, 3085473968517218653L, 3089451460101527857L, 3114862868117605599L, 3129395579983849527L, 3256258368248066264L, 3452379460455804429L, 3547627781654598988L, 3637939656440441093L, 3688179072722109200L, 3718352661124136681L, 3730752432285826863L, 3794316665763266033L, 4000049462512838776L, 4046190361520671643L, 4147696707147271408L, 4193204392725694463L, 4241163808635564644L, 4254584350247334433L, 4814658433570175913L, 4841947709850912914L, 4904007817188630457L, 5100336081510080343L, 5274044858141538265L, 5347909877633654828L, 5450448828334921485L, 5474268165959054640L, 5545425291794704408L, 5596129856135573697L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6007332606592876737L, 6280357960959217660L, 6456855723474196908L, 6511035576063254270L, 6534946468240507089L, 6584624952928234050L, 6734240326434096246L, 6742705432718011780L, 6854854816081053523L, 7045245923763966215L, 7123326897294507060L, 7179336928365889465L, 7240293012336844478L, 7347653049056829645L, 7375862386996623731L, 7442624256860549330L, 7617522210483516279L, 7658177784286215602L, 8055461369741094911L, 8389032537095247355L, 8409640769019589119L, 8488266005336625107L, 8537233257283452655L, 8838294710098435315L, 9140390920032557669L, 9140416208800006522L};
        long[] jArr = new long[f253c.length];
        int i2 = 0;
        while (true) {
            String[] strArr = f253c;
            if (i2 >= strArr.length) {
                break;
            }
            jArr[i2] = l.I(strArr[i2]);
            i2++;
        }
        Arrays.sort(jArr);
        this.w = jArr;
        this.x = z;
        if (aVar == null && !b.a.a.n.b.f428b) {
            try {
                if (classLoader == null) {
                    aVar = new b.a.a.j.j.a(new b.a.a.n.a());
                } else {
                    aVar = new b.a.a.j.j.a(classLoader);
                }
            } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
            }
        }
        this.s = aVar;
        if (aVar == null) {
            this.o = false;
        }
        t();
        e(f252b);
        f(f251a);
        d(f253c);
    }
}
