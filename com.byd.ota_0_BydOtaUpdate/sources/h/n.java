package h;

import e.a0;
import e.b0;
import e.e;
import e.s;
import e.u;
import e.v;
import e.y;
import e.z;
import h.i;
import h.r.p;
import h.r.q;
import h.r.r;
import h.r.t;
import h.r.v;
import h.r.x;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: ServiceMethod.java */
/* JADX INFO: loaded from: classes.dex */
public final class n<R, T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Pattern f5832a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Pattern f5833b = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e.a f5834c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c<R, T> f5835d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HttpUrl f5836e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e<b0, R> f5837f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f5838g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final String f5839h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final s f5840i;
    public final u j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final i<?>[] n;

    /* JADX INFO: compiled from: ServiceMethod.java */
    public static final class a<T, R> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final m f5841a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Method f5842b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Annotation[] f5843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final Annotation[][] f5844d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final Type[] f5845e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public Type f5846f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f5847g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f5848h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public boolean f5849i;
        public boolean j;
        public boolean k;
        public boolean l;
        public String m;
        public boolean n;
        public boolean o;
        public boolean p;
        public String q;
        public s r;
        public u s;
        public Set<String> t;
        public i<?>[] u;
        public e<b0, T> v;
        public c<T, R> w;

        public a(m mVar, Method method) {
            this.f5841a = mVar;
            this.f5842b = method;
            this.f5843c = method.getAnnotations();
            this.f5845e = method.getGenericParameterTypes();
            this.f5844d = method.getParameterAnnotations();
        }

        public n a() {
            c<T, R> cVarB = b();
            this.w = cVarB;
            Type typeA = cVarB.a();
            this.f5846f = typeA;
            if (typeA == l.class || typeA == a0.class) {
                throw d("'" + o.j(this.f5846f).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
            }
            this.v = c();
            for (Annotation annotation : this.f5843c) {
                j(annotation);
            }
            if (this.m == null) {
                throw d("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.n) {
                if (this.p) {
                    throw d("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
                if (this.o) {
                    throw d("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.f5844d.length;
            this.u = new i[length];
            for (int i2 = 0; i2 < length; i2++) {
                Type type = this.f5845e[i2];
                if (o.l(type)) {
                    throw f(i2, "Parameter type must not include a type variable or wildcard: %s", type);
                }
                Annotation[] annotationArr = this.f5844d[i2];
                if (annotationArr == null) {
                    throw f(i2, "No Retrofit annotation found.", new Object[0]);
                }
                this.u[i2] = k(i2, type, annotationArr);
            }
            if (this.q == null && !this.l) {
                throw d("Missing either @%s URL or @Url parameter.", this.m);
            }
            boolean z = this.o;
            if (!z && !this.p && !this.n && this.f5849i) {
                throw d("Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if (z && !this.f5847g) {
                throw d("Form-encoded method must contain at least one @Field.", new Object[0]);
            }
            if (!this.p || this.f5848h) {
                return new n(this);
            }
            throw d("Multipart method must contain at least one @Part.", new Object[0]);
        }

        public final c<T, R> b() {
            Type genericReturnType = this.f5842b.getGenericReturnType();
            if (o.l(genericReturnType)) {
                throw d("Method return type must not include a type variable or wildcard: %s", genericReturnType);
            }
            if (genericReturnType == Void.TYPE) {
                throw d("Service methods cannot return void.", new Object[0]);
            }
            try {
                return (c<T, R>) this.f5841a.b(genericReturnType, this.f5842b.getAnnotations());
            } catch (RuntimeException e2) {
                throw e(e2, "Unable to create call adapter for %s", genericReturnType);
            }
        }

        public final e<b0, T> c() {
            try {
                return this.f5841a.k(this.f5846f, this.f5842b.getAnnotations());
            } catch (RuntimeException e2) {
                throw e(e2, "Unable to create converter for %s", this.f5846f);
            }
        }

        public final RuntimeException d(String str, Object... objArr) {
            return e(null, str, objArr);
        }

        public final RuntimeException e(Throwable th, String str, Object... objArr) {
            return new IllegalArgumentException(String.format(str, objArr) + "\n    for method " + this.f5842b.getDeclaringClass().getSimpleName() + "." + this.f5842b.getName(), th);
        }

        public final RuntimeException f(int i2, String str, Object... objArr) {
            return d(str + " (parameter #" + (i2 + 1) + ")", objArr);
        }

        public final RuntimeException g(Throwable th, int i2, String str, Object... objArr) {
            return e(th, str + " (parameter #" + (i2 + 1) + ")", objArr);
        }

        public final s h(String[] strArr) {
            s.a aVar = new s.a();
            for (String str : strArr) {
                int iIndexOf = str.indexOf(58);
                if (iIndexOf == -1 || iIndexOf == 0 || iIndexOf == str.length() - 1) {
                    throw d("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String strSubstring = str.substring(0, iIndexOf);
                String strTrim = str.substring(iIndexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(strSubstring)) {
                    u uVarC = u.c(strTrim);
                    if (uVarC == null) {
                        throw d("Malformed content type: %s", strTrim);
                    }
                    this.s = uVarC;
                } else {
                    aVar.a(strSubstring, strTrim);
                }
            }
            return aVar.d();
        }

        public final void i(String str, String str2, boolean z) {
            String str3 = this.m;
            if (str3 != null) {
                throw d("Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.m = str;
            this.n = z;
            if (str2.isEmpty()) {
                return;
            }
            int iIndexOf = str2.indexOf(63);
            if (iIndexOf != -1 && iIndexOf < str2.length() - 1) {
                String strSubstring = str2.substring(iIndexOf + 1);
                if (n.f5832a.matcher(strSubstring).find()) {
                    throw d("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", strSubstring);
                }
            }
            this.q = str2;
            this.t = n.b(str2);
        }

        public final void j(Annotation annotation) {
            if (annotation instanceof h.r.b) {
                i("DELETE", ((h.r.b) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.r.f) {
                i("GET", ((h.r.f) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.r.g) {
                i("HEAD", ((h.r.g) annotation).value(), false);
                if (!Void.class.equals(this.f5846f)) {
                    throw d("HEAD method must use Void as response type.", new Object[0]);
                }
                return;
            }
            if (annotation instanceof h.r.n) {
                i("PATCH", ((h.r.n) annotation).value(), true);
                return;
            }
            if (annotation instanceof h.r.o) {
                i("POST", ((h.r.o) annotation).value(), true);
                return;
            }
            if (annotation instanceof p) {
                i("PUT", ((p) annotation).value(), true);
                return;
            }
            if (annotation instanceof h.r.m) {
                i("OPTIONS", ((h.r.m) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.r.h) {
                h.r.h hVar = (h.r.h) annotation;
                i(hVar.method(), hVar.path(), hVar.hasBody());
                return;
            }
            if (annotation instanceof h.r.k) {
                String[] strArrValue = ((h.r.k) annotation).value();
                if (strArrValue.length == 0) {
                    throw d("@Headers annotation is empty.", new Object[0]);
                }
                this.r = h(strArrValue);
                return;
            }
            if (annotation instanceof h.r.l) {
                if (this.o) {
                    throw d("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.p = true;
            } else if (annotation instanceof h.r.e) {
                if (this.p) {
                    throw d("Only one encoding annotation is allowed.", new Object[0]);
                }
                this.o = true;
            }
        }

        public final i<?> k(int i2, Type type, Annotation[] annotationArr) {
            i<?> iVar = null;
            for (Annotation annotation : annotationArr) {
                i<?> iVarL = l(i2, type, annotationArr, annotation);
                if (iVarL != null) {
                    if (iVar != null) {
                        throw f(i2, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                    }
                    iVar = iVarL;
                }
            }
            if (iVar != null) {
                return iVar;
            }
            throw f(i2, "No Retrofit annotation found.", new Object[0]);
        }

        public final i<?> l(int i2, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof x) {
                if (this.l) {
                    throw f(i2, "Multiple @Url method annotations found.", new Object[0]);
                }
                if (this.j) {
                    throw f(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.k) {
                    throw f(i2, "A @Url parameter must not come after a @Query", new Object[0]);
                }
                if (this.q != null) {
                    throw f(i2, "@Url cannot be used with @%s URL", this.m);
                }
                this.l = true;
                if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                    return new i.o();
                }
                throw f(i2, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
            }
            if (annotation instanceof h.r.s) {
                if (this.k) {
                    throw f(i2, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if (this.l) {
                    throw f(i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.q == null) {
                    throw f(i2, "@Path can only be used with relative url on @%s", this.m);
                }
                this.j = true;
                h.r.s sVar = (h.r.s) annotation;
                String strValue = sVar.value();
                m(i2, strValue);
                return new i.j(strValue, this.f5841a.l(type, annotationArr), sVar.encoded());
            }
            if (annotation instanceof t) {
                t tVar = (t) annotation;
                String strValue2 = tVar.value();
                boolean zEncoded = tVar.encoded();
                Class<?> clsJ = o.j(type);
                this.k = true;
                if (!Iterable.class.isAssignableFrom(clsJ)) {
                    return clsJ.isArray() ? new i.k(strValue2, this.f5841a.l(n.a(clsJ.getComponentType()), annotationArr), zEncoded).b() : new i.k(strValue2, this.f5841a.l(type, annotationArr), zEncoded);
                }
                if (type instanceof ParameterizedType) {
                    return new i.k(strValue2, this.f5841a.l(o.i(0, (ParameterizedType) type), annotationArr), zEncoded).c();
                }
                throw f(i2, clsJ.getSimpleName() + " must include generic type (e.g., " + clsJ.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof v) {
                boolean zEncoded2 = ((v) annotation).encoded();
                Class<?> clsJ2 = o.j(type);
                this.k = true;
                if (!Iterable.class.isAssignableFrom(clsJ2)) {
                    return clsJ2.isArray() ? new i.m(this.f5841a.l(n.a(clsJ2.getComponentType()), annotationArr), zEncoded2).b() : new i.m(this.f5841a.l(type, annotationArr), zEncoded2);
                }
                if (type instanceof ParameterizedType) {
                    return new i.m(this.f5841a.l(o.i(0, (ParameterizedType) type), annotationArr), zEncoded2).c();
                }
                throw f(i2, clsJ2.getSimpleName() + " must include generic type (e.g., " + clsJ2.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.r.u) {
                Class<?> clsJ3 = o.j(type);
                if (!Map.class.isAssignableFrom(clsJ3)) {
                    throw f(i2, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type typeK = o.k(type, clsJ3, Map.class);
                if (!(typeK instanceof ParameterizedType)) {
                    throw f(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType = (ParameterizedType) typeK;
                Type typeI = o.i(0, parameterizedType);
                if (String.class == typeI) {
                    return new i.l(this.f5841a.l(o.i(1, parameterizedType), annotationArr), ((h.r.u) annotation).encoded());
                }
                throw f(i2, "@QueryMap keys must be of type String: " + typeI, new Object[0]);
            }
            if (annotation instanceof h.r.i) {
                String strValue3 = ((h.r.i) annotation).value();
                Class<?> clsJ4 = o.j(type);
                if (!Iterable.class.isAssignableFrom(clsJ4)) {
                    return clsJ4.isArray() ? new i.f(strValue3, this.f5841a.l(n.a(clsJ4.getComponentType()), annotationArr)).b() : new i.f(strValue3, this.f5841a.l(type, annotationArr));
                }
                if (type instanceof ParameterizedType) {
                    return new i.f(strValue3, this.f5841a.l(o.i(0, (ParameterizedType) type), annotationArr)).c();
                }
                throw f(i2, clsJ4.getSimpleName() + " must include generic type (e.g., " + clsJ4.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.r.j) {
                Class<?> clsJ5 = o.j(type);
                if (!Map.class.isAssignableFrom(clsJ5)) {
                    throw f(i2, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type typeK2 = o.k(type, clsJ5, Map.class);
                if (!(typeK2 instanceof ParameterizedType)) {
                    throw f(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) typeK2;
                Type typeI2 = o.i(0, parameterizedType2);
                if (String.class == typeI2) {
                    return new i.g(this.f5841a.l(o.i(1, parameterizedType2), annotationArr));
                }
                throw f(i2, "@HeaderMap keys must be of type String: " + typeI2, new Object[0]);
            }
            if (annotation instanceof h.r.c) {
                if (!this.o) {
                    throw f(i2, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                h.r.c cVar = (h.r.c) annotation;
                String strValue4 = cVar.value();
                boolean zEncoded3 = cVar.encoded();
                this.f5847g = true;
                Class<?> clsJ6 = o.j(type);
                if (!Iterable.class.isAssignableFrom(clsJ6)) {
                    return clsJ6.isArray() ? new i.d(strValue4, this.f5841a.l(n.a(clsJ6.getComponentType()), annotationArr), zEncoded3).b() : new i.d(strValue4, this.f5841a.l(type, annotationArr), zEncoded3);
                }
                if (type instanceof ParameterizedType) {
                    return new i.d(strValue4, this.f5841a.l(o.i(0, (ParameterizedType) type), annotationArr), zEncoded3).c();
                }
                throw f(i2, clsJ6.getSimpleName() + " must include generic type (e.g., " + clsJ6.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.r.d) {
                if (!this.o) {
                    throw f(i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class<?> clsJ7 = o.j(type);
                if (!Map.class.isAssignableFrom(clsJ7)) {
                    throw f(i2, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type typeK3 = o.k(type, clsJ7, Map.class);
                if (!(typeK3 instanceof ParameterizedType)) {
                    throw f(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType3 = (ParameterizedType) typeK3;
                Type typeI3 = o.i(0, parameterizedType3);
                if (String.class == typeI3) {
                    e<T, String> eVarL = this.f5841a.l(o.i(1, parameterizedType3), annotationArr);
                    this.f5847g = true;
                    return new i.e(eVarL, ((h.r.d) annotation).encoded());
                }
                throw f(i2, "@FieldMap keys must be of type String: " + typeI3, new Object[0]);
            }
            if (!(annotation instanceof q)) {
                if (!(annotation instanceof r)) {
                    if (!(annotation instanceof h.r.a)) {
                        return null;
                    }
                    if (this.o || this.p) {
                        throw f(i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                    }
                    if (this.f5849i) {
                        throw f(i2, "Multiple @Body method annotations found.", new Object[0]);
                    }
                    try {
                        e<T, z> eVarJ = this.f5841a.j(type, annotationArr, this.f5843c);
                        this.f5849i = true;
                        return new i.c(eVarJ);
                    } catch (RuntimeException e2) {
                        throw g(e2, i2, "Unable to create @Body converter for %s", type);
                    }
                }
                if (!this.p) {
                    throw f(i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.f5848h = true;
                Class<?> clsJ8 = o.j(type);
                if (!Map.class.isAssignableFrom(clsJ8)) {
                    throw f(i2, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type typeK4 = o.k(type, clsJ8, Map.class);
                if (!(typeK4 instanceof ParameterizedType)) {
                    throw f(i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType4 = (ParameterizedType) typeK4;
                Type typeI4 = o.i(0, parameterizedType4);
                if (String.class == typeI4) {
                    Type typeI5 = o.i(1, parameterizedType4);
                    if (v.b.class.isAssignableFrom(o.j(typeI5))) {
                        throw f(i2, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                    }
                    return new i.C0101i(this.f5841a.j(typeI5, annotationArr, this.f5843c), ((r) annotation).encoding());
                }
                throw f(i2, "@PartMap keys must be of type String: " + typeI4, new Object[0]);
            }
            if (!this.p) {
                throw f(i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            }
            q qVar = (q) annotation;
            this.f5848h = true;
            String strValue5 = qVar.value();
            Class<?> clsJ9 = o.j(type);
            if (strValue5.isEmpty()) {
                if (!Iterable.class.isAssignableFrom(clsJ9)) {
                    if (clsJ9.isArray()) {
                        if (v.b.class.isAssignableFrom(clsJ9.getComponentType())) {
                            return i.n.f5798a.b();
                        }
                        throw f(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    if (v.b.class.isAssignableFrom(clsJ9)) {
                        return i.n.f5798a;
                    }
                    throw f(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
                if (type instanceof ParameterizedType) {
                    if (v.b.class.isAssignableFrom(o.j(o.i(0, (ParameterizedType) type)))) {
                        return i.n.f5798a.c();
                    }
                    throw f(i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
                throw f(i2, clsJ9.getSimpleName() + " must include generic type (e.g., " + clsJ9.getSimpleName() + "<String>)", new Object[0]);
            }
            s sVarE = s.e("Content-Disposition", "form-data; name=\"" + strValue5 + "\"", "Content-Transfer-Encoding", qVar.encoding());
            if (!Iterable.class.isAssignableFrom(clsJ9)) {
                if (!clsJ9.isArray()) {
                    if (v.b.class.isAssignableFrom(clsJ9)) {
                        throw f(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new i.h(sVarE, this.f5841a.j(type, annotationArr, this.f5843c));
                }
                Class<?> clsA = n.a(clsJ9.getComponentType());
                if (v.b.class.isAssignableFrom(clsA)) {
                    throw f(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }
                return new i.h(sVarE, this.f5841a.j(clsA, annotationArr, this.f5843c)).b();
            }
            if (type instanceof ParameterizedType) {
                Type typeI6 = o.i(0, (ParameterizedType) type);
                if (v.b.class.isAssignableFrom(o.j(typeI6))) {
                    throw f(i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                }
                return new i.h(sVarE, this.f5841a.j(typeI6, annotationArr, this.f5843c)).c();
            }
            throw f(i2, clsJ9.getSimpleName() + " must include generic type (e.g., " + clsJ9.getSimpleName() + "<String>)", new Object[0]);
        }

        public final void m(int i2, String str) {
            if (!n.f5833b.matcher(str).matches()) {
                throw f(i2, "@Path parameter name must match %s. Found: %s", n.f5832a.pattern(), str);
            }
            if (!this.t.contains(str)) {
                throw f(i2, "URL \"%s\" does not contain \"{%s}\".", this.q, str);
            }
        }
    }

    public n(a<R, T> aVar) {
        this.f5834c = aVar.f5841a.c();
        this.f5835d = aVar.w;
        this.f5836e = aVar.f5841a.a();
        this.f5837f = aVar.v;
        this.f5838g = aVar.m;
        this.f5839h = aVar.q;
        this.f5840i = aVar.r;
        this.j = aVar.s;
        this.k = aVar.n;
        this.l = aVar.o;
        this.m = aVar.p;
        this.n = aVar.u;
    }

    public static Class<?> a(Class<?> cls) {
        return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
    }

    public static Set<String> b(String str) {
        Matcher matcher = f5832a.matcher(str);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (matcher.find()) {
            linkedHashSet.add(matcher.group(1));
        }
        return linkedHashSet;
    }

    public y c(@Nullable Object... objArr) throws IOException {
        k kVar = new k(this.f5838g, this.f5836e, this.f5839h, this.f5840i, this.j, this.k, this.l, this.m);
        i<?>[] iVarArr = this.n;
        int length = objArr != null ? objArr.length : 0;
        if (length == iVarArr.length) {
            for (int i2 = 0; i2 < length; i2++) {
                iVarArr[i2].a(kVar, objArr[i2]);
            }
            return kVar.g();
        }
        throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + iVarArr.length + ")");
    }

    public R d(b0 b0Var) throws IOException {
        return this.f5837f.a(b0Var);
    }
}
