package f2;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import com.bydux.api.common.tools.util.ListUtils;
import g2.a0;
import g2.b0;
import g2.o;
import g2.p;
import g2.q;
import g2.r;
import g2.s;
import g2.t;
import g2.u;
import g2.w;
import g2.x;
import g2.z;
import h2.c0;
import h2.d0;
import h2.e1;
import h2.k0;
import h2.m;
import h2.n;
import h2.o0;
import h2.q0;
import h2.s0;
import h2.x0;
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
import l2.k;
import l2.l;

/* JADX INFO: compiled from: ParserConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f4909c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f4912f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static h f4913g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f4914h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f4915i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static boolean f4916j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static boolean f4917k;
    public List<i2.a> A;
    public volatile List<a> B;
    public boolean C;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final l2.f<Type, t> f4918l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l2.f<Type, l2.f<Type, t>> f4919m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ConcurrentMap<String, Class<?>> f4920n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f4921o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final i f4922p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PropertyNamingStrategy f4923q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ClassLoader f4924r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g2.a f4925s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f4926t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public long[] f4927u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long[] f4928v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long[] f4929w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f4930x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f4931y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f4932z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f4907a = z(l2.e.k("fastjson.parser.deny.internal"));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f4908b = z(l2.e.k("fastjson.parser.deny"));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final boolean f4910d = "true".equals(l2.e.k("fastjson.parser.autoTypeSupport"));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f4911e = "true".equals(l2.e.k("fastjson.parser.safeMode"));

    /* JADX INFO: compiled from: ParserConfig.java */
    public interface a {
        Class<?> a(String str, Class<?> cls, int i8);
    }

    static {
        String[] strArrZ = z(l2.e.k("fastjson.parser.autoTypeAccept"));
        if (strArrZ == null) {
            strArrZ = new String[0];
        }
        f4909c = strArrZ;
        f4912f = new long[]{-9013707057526259810L, -8773806119481270567L, -8421588593326113468L, -8070393259084821111L, -7858127399773263546L, -7043543676283957292L, -6976602508726000783L, -6293031534589903644L, -6081111809668363619L, -5779433778261875721L, -5399450433995651784L, -4540135604787511831L, -4207865850564917696L, -3950343444501679205L, -3714900953609113456L, -3393714734093696063L, -3378497329992063044L, -2631228350337215662L, -2551988546877734201L, -2473987886800209058L, -2265617974881722705L, -1759511109484434299L, -1477946458560579955L, -816725787720647462L, -520183782617964618L, 59775428743665658L, 484499585846206473L, 532945107123976213L, 711449177569584898L, 829148494126372070L, 956883420092542580L, 1233162291719202522L, 1696465274354442213L, 1863557081881630420L, 2238472697200138595L, 2380202963256720577L, 2643099543618286743L, 2793877891138577121L, 3804572268889088203L, 4567982875926242015L, 4784070066737926537L, 4960004821520561233L, 5348524593377618456L, 5454920836284873808L, 5695987590363189151L, 6073645722991901167L, 6114875255374330593L, 6137737446243999215L, 6160752908990493848L, 6939315124833099497L, 7048426940343117278L, 7267793227937552092L, 8331868837379820532L, 8357451534615459155L, 8890227807433646566L, 9166532985682478006L, 9215131087512669423L};
        f4913g = new h();
        f4914h = false;
        f4915i = false;
        f4916j = false;
        f4917k = false;
    }

    public h() {
        this(false);
    }

    public static Method p(Class cls, Class cls2) {
        for (Method method : cls.getMethods()) {
            if (Modifier.isStatic(method.getModifiers()) && method.getReturnType() == cls2 && method.getParameterTypes().length == 1 && ((d2.a) method.getAnnotation(d2.a.class)) != null) {
                return method;
            }
        }
        return null;
    }

    public static Field r(String str, Map<String, Field> map) {
        Field field = map.get(str);
        if (field == null) {
            field = map.get(BydPowerManagerDevice.SEPARATOR_K_V + str);
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
        return f4913g;
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
        if (Arrays.binarySearch(this.f4929w, jI) >= 0) {
            return;
        }
        long[] jArr = this.f4929w;
        int length = jArr.length + 1;
        long[] jArr2 = new long[length];
        jArr2[length - 1] = jI;
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Arrays.sort(jArr2);
        this.f4929w = jArr2;
    }

    public void b(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jI = l.I(str);
        if (Arrays.binarySearch(this.f4928v, jI) >= 0) {
            return;
        }
        long[] jArr = this.f4928v;
        int length = jArr.length + 1;
        long[] jArr2 = new long[length];
        jArr2[length - 1] = jI;
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        Arrays.sort(jArr2);
        this.f4928v = jArr2;
    }

    public void c(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        long jI = l.I(str);
        long[] jArr = this.f4927u;
        if (jArr == null) {
            this.f4927u = new long[]{jI};
            return;
        }
        if (Arrays.binarySearch(jArr, jI) >= 0) {
            return;
        }
        long[] jArr2 = this.f4927u;
        int length = jArr2.length + 1;
        long[] jArr3 = new long[length];
        jArr3[length - 1] = jI;
        System.arraycopy(jArr2, 0, jArr3, 0, jArr2.length);
        Arrays.sort(jArr3);
        this.f4927u = jArr3;
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
        return l(cls) != null ? cls : i(cls.getName(), null, c2.a.DEFAULT_PARSER_FEATURE);
    }

    public Class<?> h(String str, Class<?> cls) {
        return i(str, cls, c2.a.DEFAULT_PARSER_FEATURE);
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
        throw new UnsupportedOperationException("Method not decompiled: f2.h.i(java.lang.String, java.lang.Class, int):java.lang.Class");
    }

    public g2.l j(h hVar, l2.g gVar, l2.c cVar) {
        Class<?> clsDeserializeUsing;
        Class<?> cls = gVar.f6215a;
        Class<?> cls2 = cVar.f6173h;
        d2.b bVarD = cVar.d();
        Class<?> cls3 = null;
        if (bVarD != null && (clsDeserializeUsing = bVarD.deserializeUsing()) != Void.class) {
            cls3 = clsDeserializeUsing;
        }
        return (cls3 == null && (cls2 == List.class || cls2 == ArrayList.class)) ? new g2.c(hVar, cls, cVar) : new g2.f(hVar, cls, cVar);
    }

    public t k(Class<?> cls, Type type) {
        d2.b bVarD;
        Method method;
        g2.a aVar;
        boolean zA = this.f4921o & (!this.f4930x);
        if (zA) {
            d2.d dVar = (d2.d) l.M(cls, d2.d.class);
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
                Class<?> clsF = l2.g.f(cls, dVar);
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
        if (zA && (aVar = this.f4925s) != null && aVar.f5015c.c(cls)) {
            zA = false;
        }
        if (zA) {
            zA = l2.b.a(cls.getSimpleName());
        }
        if (zA) {
            if (cls.isInterface()) {
                zA = false;
            }
            l2.g gVarC = l2.g.c(cls, type, this.f4923q, false, l.f6242b, this.f4931y);
            if (zA && gVarC.f6222h.length > 200) {
                zA = false;
            }
            Constructor<?> constructor = gVarC.f6217c;
            if (zA && constructor == null && !cls.isInterface()) {
                zA = false;
            }
            for (l2.c cVar : gVarC.f6222h) {
                if (!cVar.f6176k) {
                    Class<?> cls2 = cVar.f6173h;
                    if (Modifier.isPublic(cls2.getModifiers()) && ((!cls2.isMemberClass() || Modifier.isStatic(cls2.getModifiers())) && ((cVar.l() == null || l2.b.a(cVar.l().getName())) && (((bVarD = cVar.d()) == null || (l2.b.a(bVarD.name()) && bVarD.format().length() == 0 && bVarD.deserializeUsing() == Void.class && bVarD.parseFeatures().length == 0 && !bVarD.unwrapped())) && (((method = cVar.f6170e) == null || method.getParameterTypes().length <= 1) && (!cls2.isEnum() || (o(cls2) instanceof g2.h))))))) {
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
        l2.g gVarB = l2.g.b(cls, type, this.f4923q);
        try {
            return this.f4925s.v(this, gVarB);
        } catch (JSONException unused2) {
            return new o(this, gVarB);
        } catch (NoSuchMethodException unused3) {
            return new o(this, cls, type);
        } catch (Exception e8) {
            throw new JSONException("create asm deserializer error, " + cls.getName(), e8);
        }
    }

    public t l(Type type) {
        Type mixInAnnotations = c2.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.f4918l.b(type);
        }
        l2.f<Type, t> fVarB = this.f4919m.b(type);
        if (fVarB == null) {
            return null;
        }
        return fVarB.b(mixInAnnotations);
    }

    public ClassLoader m() {
        return this.f4924r;
    }

    public t n(Class<?> cls, Type type) {
        t tVarK;
        t xVar;
        Class<?> clsMappingTo;
        Type type2 = type;
        t tVarL = l(type2);
        if (tVarL == null && (type2 instanceof l2.h)) {
            tVarL = l(c2.g.b((l2.h) type2));
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
        d2.d dVar = (d2.d) l.M(cls, d2.d.class);
        if (dVar != null && (clsMappingTo = dVar.mappingTo()) != Void.class) {
            return n(clsMappingTo, clsMappingTo);
        }
        if ((type2 instanceof WildcardType) || (type2 instanceof TypeVariable) || (type2 instanceof ParameterizedType)) {
            tVarL2 = l(cls);
        }
        if (tVarL2 != null) {
            return tVarL2;
        }
        Iterator<i2.a> it = this.A.iterator();
        while (it.hasNext()) {
            tVarL2 = it.next().a(this, cls);
            if (tVarL2 != null) {
                x(type2, tVarL2);
                return tVarL2;
            }
        }
        String strReplace = cls.getName().replace('$', '.');
        if (strReplace.startsWith("java.awt.") && h2.i.k(cls) && !f4914h) {
            String[] strArr = {"java.awt.Point", "java.awt.Font", "java.awt.Rectangle", "java.awt.Color"};
            for (int i8 = 0; i8 < 4; i8++) {
                try {
                    String str = strArr[i8];
                    if (str.equals(strReplace)) {
                        Type cls2 = Class.forName(str);
                        t tVar = h2.i.f5239a;
                        x(cls2, tVar);
                        return tVar;
                    }
                } catch (Throwable unused) {
                    f4914h = true;
                }
            }
            tVarL2 = h2.i.f5239a;
        }
        if (!f4915i) {
            try {
                if (strReplace.startsWith("java.time.")) {
                    String[] strArr2 = {"java.time.LocalDateTime", "java.time.LocalDate", "java.time.LocalTime", "java.time.ZonedDateTime", "java.time.OffsetDateTime", "java.time.OffsetTime", "java.time.ZoneOffset", "java.time.ZoneRegion", "java.time.ZoneId", "java.time.Period", "java.time.Duration", "java.time.Instant"};
                    for (int i9 = 0; i9 < 12; i9++) {
                        String str2 = strArr2[i9];
                        if (str2.equals(strReplace)) {
                            Type cls3 = Class.forName(str2);
                            t tVar2 = q.f5050a;
                            x(cls3, tVar2);
                            return tVar2;
                        }
                    }
                } else if (strReplace.startsWith("java.util.Optional")) {
                    String[] strArr3 = {"java.util.Optional", "java.util.OptionalDouble", "java.util.OptionalInt", "java.util.OptionalLong"};
                    for (int i10 = 0; i10 < 4; i10++) {
                        String str3 = strArr3[i10];
                        if (str3.equals(strReplace)) {
                            Type cls4 = Class.forName(str3);
                            t tVar3 = u.f5073a;
                            x(cls4, tVar3);
                            return tVar3;
                        }
                    }
                }
            } catch (Throwable unused2) {
                f4915i = true;
            }
        }
        if (!f4916j) {
            try {
                if (strReplace.startsWith("org.joda.time.")) {
                    String[] strArr4 = {"org.joda.time.DateTime", "org.joda.time.LocalDate", "org.joda.time.LocalDateTime", "org.joda.time.LocalTime", "org.joda.time.Instant", "org.joda.time.Period", "org.joda.time.Duration", "org.joda.time.DateTimeZone", "org.joda.time.format.DateTimeFormatter"};
                    for (int i11 = 0; i11 < 9; i11++) {
                        String str4 = strArr4[i11];
                        if (str4.equals(strReplace)) {
                            Type cls5 = Class.forName(str4);
                            tVarL2 = k0.f5262a;
                            x(cls5, tVarL2);
                            return tVarL2;
                        }
                    }
                }
            } catch (Throwable unused3) {
                f4916j = true;
            }
        }
        if (!f4917k && strReplace.startsWith("com.google.common.collect.")) {
            try {
                String[] strArr5 = {"com.google.common.collect.HashMultimap", "com.google.common.collect.LinkedListMultimap", "com.google.common.collect.LinkedHashMultimap", "com.google.common.collect.ArrayListMultimap", "com.google.common.collect.TreeMultimap"};
                for (int i12 = 0; i12 < 5; i12++) {
                    String str5 = strArr5[i12];
                    if (str5.equals(strReplace)) {
                        Type cls6 = Class.forName(str5);
                        tVarL2 = c0.f5191a;
                        x(cls6, tVarL2);
                        return tVarL2;
                    }
                }
            } catch (ClassNotFoundException unused4) {
                f4917k = true;
            }
        }
        if (strReplace.equals("java.nio.ByteBuffer")) {
            tVarL2 = h2.o.f5291a;
            x(cls, tVarL2);
        }
        if (strReplace.equals("java.nio.file.Path")) {
            tVarL2 = q0.f5301b;
            x(cls, tVarL2);
        }
        if (cls == Map.Entry.class) {
            tVarL2 = q0.f5301b;
            x(cls, tVarL2);
        }
        if (strReplace.equals("org.javamoney.moneta.Money")) {
            tVarL2 = j2.a.f5576a;
            x(cls, tVarL2);
        }
        try {
            for (g2.d dVar2 : k.a(g2.d.class, Thread.currentThread().getContextClassLoader())) {
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
            if (this.f4931y) {
                for (Method method : cls.getMethods()) {
                    if (l.r0(method)) {
                        t tVarK2 = k(cls, type2);
                        x(type2, tVarK2);
                        return tVarK2;
                    }
                }
            }
            Class<?> cls7 = (Class) c2.a.getMixInAnnotations(cls);
            d2.d dVar3 = (d2.d) l.M(cls7 != null ? cls7 : cls, d2.d.class);
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
                t gVar = new g2.g(methodP);
                x(cls, gVar);
                return gVar;
            }
            tVarK = q(cls);
        } else if (cls.isArray()) {
            tVarK = s0.f5306a;
        } else if (cls == Set.class || cls == HashSet.class || cls == Collection.class || cls == List.class || cls == ArrayList.class || Collection.class.isAssignableFrom(cls)) {
            tVarK = h2.t.f5307a;
        } else if (Map.class.isAssignableFrom(cls)) {
            tVarK = r.f5071a;
        } else {
            if (Throwable.class.isAssignableFrom(cls)) {
                xVar = new b0(this, cls);
            } else if (w.class.isAssignableFrom(cls)) {
                xVar = new x(cls);
            } else {
                tVarK = cls == InetAddress.class ? q0.f5301b : k(cls, type2);
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
        return p.f5049a;
    }

    public t q(Class<?> cls) {
        return new g2.h(cls);
    }

    public final void t() {
        l2.f<Type, t> fVar = this.f4918l;
        q0 q0Var = q0.f5301b;
        fVar.c(SimpleDateFormat.class, q0Var);
        this.f4918l.c(Timestamp.class, z.f5082b);
        this.f4918l.c(java.sql.Date.class, z.f5081a);
        this.f4918l.c(Time.class, g2.c0.f5027a);
        this.f4918l.c(Date.class, h2.w.f5309a);
        l2.f<Type, t> fVar2 = this.f4918l;
        h2.p pVar = h2.p.f5296a;
        fVar2.c(Calendar.class, pVar);
        this.f4918l.c(XMLGregorianCalendar.class, pVar);
        this.f4918l.c(JSONObject.class, r.f5071a);
        l2.f<Type, t> fVar3 = this.f4918l;
        h2.t tVar = h2.t.f5307a;
        fVar3.c(JSONArray.class, tVar);
        this.f4918l.c(Map.class, r.f5071a);
        this.f4918l.c(HashMap.class, r.f5071a);
        this.f4918l.c(LinkedHashMap.class, r.f5071a);
        this.f4918l.c(TreeMap.class, r.f5071a);
        this.f4918l.c(ConcurrentMap.class, r.f5071a);
        this.f4918l.c(ConcurrentHashMap.class, r.f5071a);
        this.f4918l.c(Collection.class, tVar);
        this.f4918l.c(List.class, tVar);
        this.f4918l.c(ArrayList.class, tVar);
        l2.f<Type, t> fVar4 = this.f4918l;
        p pVar2 = p.f5049a;
        fVar4.c(Object.class, pVar2);
        this.f4918l.c(String.class, e1.f5232a);
        this.f4918l.c(StringBuffer.class, e1.f5232a);
        this.f4918l.c(StringBuilder.class, e1.f5232a);
        l2.f<Type, t> fVar5 = this.f4918l;
        Class cls = Character.TYPE;
        h2.r rVar = h2.r.f5304a;
        fVar5.c(cls, rVar);
        this.f4918l.c(Character.class, rVar);
        l2.f<Type, t> fVar6 = this.f4918l;
        Class cls2 = Byte.TYPE;
        s sVar = s.f5072a;
        fVar6.c(cls2, sVar);
        this.f4918l.c(Byte.class, sVar);
        this.f4918l.c(Short.TYPE, sVar);
        this.f4918l.c(Short.class, sVar);
        this.f4918l.c(Integer.TYPE, d0.f5206a);
        this.f4918l.c(Integer.class, d0.f5206a);
        this.f4918l.c(Long.TYPE, o0.f5295a);
        this.f4918l.c(Long.class, o0.f5295a);
        this.f4918l.c(BigInteger.class, m.f5288c);
        this.f4918l.c(BigDecimal.class, h2.l.f5285c);
        this.f4918l.c(Float.TYPE, h2.b0.f5186a);
        this.f4918l.c(Float.class, h2.b0.f5186a);
        this.f4918l.c(Double.TYPE, sVar);
        this.f4918l.c(Double.class, sVar);
        l2.f<Type, t> fVar7 = this.f4918l;
        Class cls3 = Boolean.TYPE;
        n nVar = n.f5289a;
        fVar7.c(cls3, nVar);
        this.f4918l.c(Boolean.class, nVar);
        this.f4918l.c(Class.class, q0Var);
        this.f4918l.c(char[].class, new h2.q());
        this.f4918l.c(AtomicBoolean.class, nVar);
        this.f4918l.c(AtomicInteger.class, d0.f5206a);
        this.f4918l.c(AtomicLong.class, o0.f5295a);
        l2.f<Type, t> fVar8 = this.f4918l;
        x0 x0Var = x0.f5312a;
        fVar8.c(AtomicReference.class, x0Var);
        this.f4918l.c(WeakReference.class, x0Var);
        this.f4918l.c(SoftReference.class, x0Var);
        this.f4918l.c(UUID.class, q0Var);
        this.f4918l.c(TimeZone.class, q0Var);
        this.f4918l.c(Locale.class, q0Var);
        this.f4918l.c(Currency.class, q0Var);
        this.f4918l.c(Inet4Address.class, q0Var);
        this.f4918l.c(Inet6Address.class, q0Var);
        this.f4918l.c(InetSocketAddress.class, q0Var);
        this.f4918l.c(File.class, q0Var);
        this.f4918l.c(URI.class, q0Var);
        this.f4918l.c(URL.class, q0Var);
        this.f4918l.c(Pattern.class, q0Var);
        this.f4918l.c(Charset.class, q0Var);
        this.f4918l.c(JSONPath.class, q0Var);
        this.f4918l.c(Number.class, sVar);
        l2.f<Type, t> fVar9 = this.f4918l;
        h2.g gVar = h2.g.f5237a;
        fVar9.c(AtomicIntegerArray.class, gVar);
        this.f4918l.c(AtomicLongArray.class, gVar);
        this.f4918l.c(StackTraceElement.class, a0.f5023a);
        this.f4918l.c(Serializable.class, pVar2);
        this.f4918l.c(Cloneable.class, pVar2);
        this.f4918l.c(Comparable.class, pVar2);
        this.f4918l.c(Closeable.class, pVar2);
        this.f4918l.c(c2.c.class, new g2.n());
    }

    public boolean u() {
        return this.f4931y;
    }

    public void x(Type type, t tVar) {
        Type mixInAnnotations = c2.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            this.f4918l.c(type, tVar);
            return;
        }
        l2.f<Type, t> fVarB = this.f4919m.b(type);
        if (fVarB == null) {
            fVarB = new l2.f<>(4);
            this.f4919m.c(type, fVarB);
        }
        fVarB.c(mixInAnnotations, tVar);
    }

    public void y(boolean z7) {
        this.f4921o = z7;
    }

    public h(boolean z7) {
        this(null, null, z7);
    }

    public h(g2.a aVar, ClassLoader classLoader, boolean z7) {
        this.f4918l = new l2.f<>();
        this.f4919m = new l2.f<>(16);
        this.f4920n = new ConcurrentHashMap(16, 0.75f, 1);
        this.f4921o = !l2.b.f6168b;
        this.f4922p = new i(4096);
        this.f4926t = f4910d;
        this.f4931y = false;
        this.f4932z = l.f6242b;
        this.A = new ArrayList();
        this.C = f4911e;
        this.f4928v = new long[]{-9164606388214699518L, -8720046426850100497L, -8649961213709896794L, -8165637398350707645L, -8109300701639721088L, -7966123100503199569L, -7921218830998286408L, -7775351613326101303L, -7768608037458185275L, -7766605818834748097L, -6835437086156813536L, -6316154655839304624L, -6179589609550493385L, -6149093380703242441L, -6025144546313590215L, -5939269048541779808L, -5885964883385605994L, -5764804792063216819L, -5472097725414717105L, -5194641081268104286L, -5076846148177416215L, -4837536971810737970L, -4703320437989596122L, -4608341446948126581L, -4537258998789938600L, -4438775680185074100L, -4314457471973557243L, -4150995715611818742L, -4082057040235125754L, -3975378478825053783L, -3935185854875733362L, -3319207949486691020L, -3077205613010077203L, -3053747177772160511L, -2995060141064716555L, -2825378362173150292L, -2533039401923731906L, -2439930098895578154L, -2378990704010641148L, -2364987994247679115L, -2262244760619952081L, -2192804397019347313L, -2095516571388852610L, -1872417015366588117L, -1650485814983027158L, -1589194880214235129L, -965955008570215305L, -905177026366752536L, -831789045734283466L, -582813228520337988L, -254670111376247151L, -219577392946377768L, -190281065685395680L, -26639035867733124L, -9822483067882491L, 4750336058574309L, 33238344207745342L, 156405680656087946L, 218512992947536312L, 313864100207897507L, 386461436234701831L, 823641066473609950L, 1073634739308289776L, 1153291637701043748L, 1203232727967308606L, 1214780596910349029L, 1459860845934817624L, 1502845958873959152L, 1534439610567445754L, 1698504441317515818L, 1818089308493370394L, 2078113382421334967L, 2164696723069287854L, 2622551729063269307L, 2653453629929770569L, 2660670623866180977L, 2731823439467737506L, 2836431254737891113L, 2930861374593775110L, 3085473968517218653L, 3089451460101527857L, 3114862868117605599L, 3129395579983849527L, 3256258368248066264L, 3452379460455804429L, 3547627781654598988L, 3637939656440441093L, 3688179072722109200L, 3718352661124136681L, 3730752432285826863L, 3794316665763266033L, 4000049462512838776L, 4046190361520671643L, 4147696707147271408L, 4193204392725694463L, 4241163808635564644L, 4254584350247334433L, 4814658433570175913L, 4841947709850912914L, 4904007817188630457L, 5100336081510080343L, 5274044858141538265L, 5347909877633654828L, 5450448828334921485L, 5474268165959054640L, 5545425291794704408L, 5596129856135573697L, 5688200883751798389L, 5751393439502795295L, 5944107969236155580L, 6007332606592876737L, 6280357960959217660L, 6456855723474196908L, 6511035576063254270L, 6534946468240507089L, 6584624952928234050L, 6734240326434096246L, 6742705432718011780L, 6854854816081053523L, 7045245923763966215L, 7123326897294507060L, 7179336928365889465L, 7240293012336844478L, 7347653049056829645L, 7375862386996623731L, 7442624256860549330L, 7617522210483516279L, 7658177784286215602L, 8055461369741094911L, 8389032537095247355L, 8409640769019589119L, 8488266005336625107L, 8537233257283452655L, 8838294710098435315L, 9140390920032557669L, 9140416208800006522L};
        long[] jArr = new long[f4909c.length];
        int i8 = 0;
        while (true) {
            String[] strArr = f4909c;
            if (i8 >= strArr.length) {
                break;
            }
            jArr[i8] = l.I(strArr[i8]);
            i8++;
        }
        Arrays.sort(jArr);
        this.f4929w = jArr;
        this.f4930x = z7;
        if (aVar == null && !l2.b.f6168b) {
            try {
                if (classLoader == null) {
                    aVar = new g2.a(new l2.a());
                } else {
                    aVar = new g2.a(classLoader);
                }
            } catch (ExceptionInInitializerError | NoClassDefFoundError | AccessControlException unused) {
            }
        }
        this.f4925s = aVar;
        if (aVar == null) {
            this.f4921o = false;
        }
        t();
        e(f4908b);
        f(f4907a);
        d(f4909c);
    }
}
