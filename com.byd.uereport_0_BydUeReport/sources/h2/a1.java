package h2;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.File;
import java.io.Serializable;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: SerializeConfig.java */
/* JADX INFO: loaded from: classes.dex */
public class a1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a1 f5167a = new a1();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5168b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5169c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5170d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5171e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f5172f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static boolean f5173g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f5174h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static boolean f5175i = false;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f5176j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public a f5177k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f5178l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public PropertyNamingStrategy f5179m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final l2.f<Type, t0> f5180n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final l2.f<Type, l2.f<Type, t0>> f5181o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f5182p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long[] f5183q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public List<i2.a> f5184r;

    public a1() {
        this(8192);
    }

    public static Member f(Class cls) {
        Field field = null;
        for (Method method : cls.getMethods()) {
            if (method.getReturnType() != Void.class && ((d2.b) method.getAnnotation(d2.b.class)) != null) {
                if (field != null) {
                    return null;
                }
                field = method;
            }
        }
        for (Field field2 : cls.getFields()) {
            if (((d2.b) field2.getAnnotation(d2.b.class)) != null) {
                if (field != null) {
                    return null;
                }
                field = field2;
            }
        }
        return field;
    }

    public static a1 g() {
        return f5167a;
    }

    public final j0 a(z0 z0Var) throws Exception {
        j0 j0VarZ = this.f5177k.z(z0Var);
        int i8 = 0;
        while (true) {
            a0[] a0VarArr = j0VarZ.f5255k;
            if (i8 >= a0VarArr.length) {
                return j0VarZ;
            }
            Class<?> cls = a0VarArr[i8].f5150d.f6173h;
            if (cls.isEnum() && !(h(cls) instanceof y)) {
                j0VarZ.f5200i = false;
            }
            i8++;
        }
    }

    public t0 b(z0 z0Var) {
        Method method;
        d2.d dVar = z0Var.f5323d;
        boolean z7 = false;
        boolean z8 = this.f5176j && !this.f5182p;
        if (dVar != null) {
            Class<?> clsSerializer = dVar.serializer();
            if (clsSerializer != Void.class) {
                try {
                    Object objNewInstance = clsSerializer.newInstance();
                    if (objNewInstance instanceof t0) {
                        return (t0) objNewInstance;
                    }
                } catch (Throwable unused) {
                }
            }
            if (!dVar.asm()) {
                z8 = false;
            }
            if (z8) {
                for (SerializerFeature serializerFeature : dVar.serialzeFeatures()) {
                    if (SerializerFeature.WriteNonStringValueAsString == serializerFeature || SerializerFeature.WriteEnumUsingToString == serializerFeature || SerializerFeature.NotWriteDefaultValue == serializerFeature || SerializerFeature.BrowserCompatible == serializerFeature) {
                        z8 = false;
                        break;
                    }
                }
            }
            if (z8 && dVar.serialzeFilters().length != 0) {
                z8 = false;
            }
        }
        Class<?> cls = z0Var.f5320a;
        if (!Modifier.isPublic(cls.getModifiers())) {
            return new j0(z0Var);
        }
        if ((z8 && this.f5177k.f5138j.c(cls)) || cls == Serializable.class || cls == Object.class) {
            z8 = false;
        }
        if (z8 && !l2.b.a(cls.getSimpleName())) {
            z8 = false;
        }
        if (z8 && z0Var.f5320a.isInterface()) {
            z8 = false;
        }
        if (z8) {
            for (l2.c cVar : z0Var.f5324e) {
                Field field = cVar.f6171f;
                if ((field != null && !field.getType().equals(cVar.f6173h)) || (((method = cVar.f6170e) != null && !method.getReturnType().equals(cVar.f6173h)) || (cVar.f6173h.isEnum() && d(cVar.f6173h) != y.f5313a))) {
                    break;
                }
                d2.b bVarD = cVar.d();
                if (bVarD != null) {
                    String str = bVarD.format();
                    if ((str.length() != 0 && (cVar.f6173h != String.class || !"trim".equals(str))) || !l2.b.a(bVarD.name()) || bVarD.jsonDirect() || bVarD.serializeUsing() != Void.class || bVarD.unwrapped()) {
                        break;
                    }
                    for (SerializerFeature serializerFeature2 : bVarD.serialzeFeatures()) {
                        if (SerializerFeature.WriteNonStringValueAsString == serializerFeature2 || SerializerFeature.WriteEnumUsingToString == serializerFeature2 || SerializerFeature.NotWriteDefaultValue == serializerFeature2 || SerializerFeature.BrowserCompatible == serializerFeature2 || SerializerFeature.WriteClassName == serializerFeature2) {
                            z8 = false;
                            break;
                        }
                    }
                    if (l2.l.m0(method) || l2.l.l0(method) || (bVarD.defaultValue() != null && !"".equals(bVarD.defaultValue()))) {
                        break;
                    }
                }
            }
            z7 = z8;
        } else {
            z7 = z8;
        }
        if (z7) {
            try {
                j0 j0VarA = a(z0Var);
                if (j0VarA != null) {
                    return j0VarA;
                }
            } catch (ClassCastException | ClassFormatError | ClassNotFoundException unused2) {
            } catch (OutOfMemoryError e8) {
                if (e8.getMessage().indexOf("Metaspace") != -1) {
                    throw e8;
                }
            } catch (Throwable th) {
                throw new JSONException("create asm serializer error, verson 1.2.73, class " + cls, th);
            }
        }
        return new j0(z0Var);
    }

    public final t0 c(Class<?> cls) {
        String name = cls.getName();
        if (Arrays.binarySearch(this.f5183q, l2.l.I(name)) < 0) {
            z0 z0VarD = l2.l.d(cls, null, this.f5179m, this.f5182p);
            return (z0VarD.f5324e.length == 0 && Iterable.class.isAssignableFrom(cls)) ? q0.f5301b : b(z0VarD);
        }
        throw new JSONException("not support class : " + name);
    }

    public final t0 d(Type type) {
        Type mixInAnnotations = c2.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.f5180n.b(type);
        }
        l2.f<Type, t0> fVarB = this.f5181o.b(type);
        if (fVarB == null) {
            return null;
        }
        return fVarB.b(mixInAnnotations);
    }

    public t0 e() {
        return y.f5313a;
    }

    public t0 h(Class<?> cls) {
        return i(cls, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:281:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:344:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public h2.t0 i(java.lang.Class<?> r29, boolean r30) throws java.lang.NoSuchMethodException {
        /*
            Method dump skipped, instruction units count: 1242
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h2.a1.i(java.lang.Class, boolean):h2.t0");
    }

    public final void j() {
        k(Boolean.class, n.f5289a);
        k(Character.class, r.f5304a);
        k(Byte.class, d0.f5206a);
        k(Short.class, d0.f5206a);
        k(Integer.class, d0.f5206a);
        k(Long.class, o0.f5295a);
        k(Float.class, b0.f5186a);
        k(Double.class, x.f5310a);
        k(BigDecimal.class, l.f5285c);
        k(BigInteger.class, m.f5288c);
        k(String.class, e1.f5232a);
        k(byte[].class, u0.f5308a);
        k(short[].class, u0.f5308a);
        k(int[].class, u0.f5308a);
        k(long[].class, u0.f5308a);
        k(float[].class, u0.f5308a);
        k(double[].class, u0.f5308a);
        k(boolean[].class, u0.f5308a);
        k(char[].class, u0.f5308a);
        k(Object[].class, s0.f5306a);
        q0 q0Var = q0.f5301b;
        k(Class.class, q0Var);
        k(SimpleDateFormat.class, q0Var);
        k(Currency.class, new q0());
        k(TimeZone.class, q0Var);
        k(InetAddress.class, q0Var);
        k(Inet4Address.class, q0Var);
        k(Inet6Address.class, q0Var);
        k(InetSocketAddress.class, q0Var);
        k(File.class, q0Var);
        e eVar = e.f5230a;
        k(Appendable.class, eVar);
        k(StringBuffer.class, eVar);
        k(StringBuilder.class, eVar);
        f1 f1Var = f1.f5236a;
        k(Charset.class, f1Var);
        k(Pattern.class, f1Var);
        k(Locale.class, f1Var);
        k(URI.class, f1Var);
        k(URL.class, f1Var);
        k(UUID.class, f1Var);
        g gVar = g.f5237a;
        k(AtomicBoolean.class, gVar);
        k(AtomicInteger.class, gVar);
        k(AtomicLong.class, gVar);
        x0 x0Var = x0.f5312a;
        k(AtomicReference.class, x0Var);
        k(AtomicIntegerArray.class, gVar);
        k(AtomicLongArray.class, gVar);
        k(WeakReference.class, x0Var);
        k(SoftReference.class, x0Var);
        k(LinkedList.class, t.f5307a);
    }

    public boolean k(Type type, t0 t0Var) {
        Type mixInAnnotations = c2.a.getMixInAnnotations(type);
        if (mixInAnnotations == null) {
            return this.f5180n.c(type, t0Var);
        }
        l2.f<Type, t0> fVarB = this.f5181o.b(type);
        if (fVarB == null) {
            fVarB = new l2.f<>(4);
            this.f5181o.c(type, fVarB);
        }
        return fVarB.c(mixInAnnotations, t0Var);
    }

    public void l(boolean z7) {
        if (l2.b.f6168b) {
            return;
        }
        this.f5176j = z7;
    }

    public a1(int i8) {
        this(i8, false);
    }

    public a1(int i8, boolean z7) {
        this.f5176j = !l2.b.f6168b;
        this.f5178l = c2.a.DEFAULT_TYPE_KEY;
        this.f5183q = new long[]{4165360493669296979L, 4446674157046724083L};
        this.f5184r = new ArrayList();
        this.f5182p = z7;
        this.f5180n = new l2.f<>(i8);
        this.f5181o = new l2.f<>(16);
        try {
            if (this.f5176j) {
                this.f5177k = new a();
            }
        } catch (Throwable unused) {
            this.f5176j = false;
        }
        j();
    }
}
