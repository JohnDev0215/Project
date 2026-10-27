package h;

import f.a0;
import f.e0;
import f.w;
import f.z;
import h.p;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: RequestFactory.java */
/* JADX INFO: loaded from: classes.dex */
final class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f5667a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f.x f5668b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f5669c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    private final String f5670d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private final f.w f5671e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private final z f5672f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f5673g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f5674h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final boolean f5675i;
    private final p<?>[] j;
    final boolean k;

    /* JADX INFO: compiled from: RequestFactory.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Pattern f5676a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final Pattern f5677b = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final u f5678c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Method f5679d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Annotation[] f5680e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final Annotation[][] f5681f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final Type[] f5682g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f5683h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        boolean f5684i;
        boolean j;
        boolean k;
        boolean l;
        boolean m;
        boolean n;
        boolean o;

        @Nullable
        String p;
        boolean q;
        boolean r;
        boolean s;

        @Nullable
        String t;

        @Nullable
        f.w u;

        @Nullable
        z v;

        @Nullable
        Set<String> w;

        @Nullable
        p<?>[] x;
        boolean y;

        a(u uVar, Method method) {
            this.f5678c = uVar;
            this.f5679d = method;
            this.f5680e = method.getAnnotations();
            this.f5682g = method.getGenericParameterTypes();
            this.f5681f = method.getParameterAnnotations();
        }

        private static Class<?> a(Class<?> cls) {
            return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
        }

        private f.w c(String[] strArr) {
            w.a aVar = new w.a();
            for (String str : strArr) {
                int iIndexOf = str.indexOf(58);
                if (iIndexOf == -1 || iIndexOf == 0 || iIndexOf == str.length() - 1) {
                    throw y.m(this.f5679d, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String strSubstring = str.substring(0, iIndexOf);
                String strTrim = str.substring(iIndexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(strSubstring)) {
                    try {
                        this.v = z.c(strTrim);
                    } catch (IllegalArgumentException e2) {
                        throw y.n(this.f5679d, e2, "Malformed content type: %s", strTrim);
                    }
                } else {
                    aVar.a(strSubstring, strTrim);
                }
            }
            return aVar.f();
        }

        private void d(String str, String str2, boolean z) {
            String str3 = this.p;
            if (str3 != null) {
                throw y.m(this.f5679d, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.p = str;
            this.q = z;
            if (str2.isEmpty()) {
                return;
            }
            int iIndexOf = str2.indexOf(63);
            if (iIndexOf != -1 && iIndexOf < str2.length() - 1) {
                String strSubstring = str2.substring(iIndexOf + 1);
                if (f5676a.matcher(strSubstring).find()) {
                    throw y.m(this.f5679d, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", strSubstring);
                }
            }
            this.t = str2;
            this.w = h(str2);
        }

        private void e(Annotation annotation) {
            if (annotation instanceof h.a0.b) {
                d("DELETE", ((h.a0.b) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.a0.f) {
                d("GET", ((h.a0.f) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.a0.g) {
                d("HEAD", ((h.a0.g) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.a0.n) {
                d("PATCH", ((h.a0.n) annotation).value(), true);
                return;
            }
            if (annotation instanceof h.a0.o) {
                d("POST", ((h.a0.o) annotation).value(), true);
                return;
            }
            if (annotation instanceof h.a0.p) {
                d("PUT", ((h.a0.p) annotation).value(), true);
                return;
            }
            if (annotation instanceof h.a0.m) {
                d("OPTIONS", ((h.a0.m) annotation).value(), false);
                return;
            }
            if (annotation instanceof h.a0.h) {
                h.a0.h hVar = (h.a0.h) annotation;
                d(hVar.method(), hVar.path(), hVar.hasBody());
                return;
            }
            if (annotation instanceof h.a0.k) {
                String[] strArrValue = ((h.a0.k) annotation).value();
                if (strArrValue.length == 0) {
                    throw y.m(this.f5679d, "@Headers annotation is empty.", new Object[0]);
                }
                this.u = c(strArrValue);
                return;
            }
            if (annotation instanceof h.a0.l) {
                if (this.r) {
                    throw y.m(this.f5679d, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.s = true;
            } else if (annotation instanceof h.a0.e) {
                if (this.s) {
                    throw y.m(this.f5679d, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.r = true;
            }
        }

        @Nullable
        private p<?> f(int i2, Type type, @Nullable Annotation[] annotationArr, boolean z) {
            p<?> pVar;
            if (annotationArr != null) {
                pVar = null;
                for (Annotation annotation : annotationArr) {
                    p<?> pVarG = g(i2, type, annotationArr, annotation);
                    if (pVarG != null) {
                        if (pVar != null) {
                            throw y.o(this.f5679d, i2, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                        pVar = pVarG;
                    }
                }
            } else {
                pVar = null;
            }
            if (pVar != null) {
                return pVar;
            }
            if (z) {
                try {
                    if (y.h(type) == Continuation.class) {
                        this.y = true;
                        return null;
                    }
                } catch (NoClassDefFoundError unused) {
                }
            }
            throw y.o(this.f5679d, i2, "No Retrofit annotation found.", new Object[0]);
        }

        @Nullable
        private p<?> g(int i2, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof h.a0.y) {
                j(i2, type);
                if (this.o) {
                    throw y.o(this.f5679d, i2, "Multiple @Url method annotations found.", new Object[0]);
                }
                if (this.k) {
                    throw y.o(this.f5679d, i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.l) {
                    throw y.o(this.f5679d, i2, "A @Url parameter must not come after a @Query.", new Object[0]);
                }
                if (this.m) {
                    throw y.o(this.f5679d, i2, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.n) {
                    throw y.o(this.f5679d, i2, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.t != null) {
                    throw y.o(this.f5679d, i2, "@Url cannot be used with @%s URL", this.p);
                }
                this.o = true;
                if (type == f.x.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                    return new p.C0107p(this.f5679d, i2);
                }
                throw y.o(this.f5679d, i2, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
            }
            if (annotation instanceof h.a0.s) {
                j(i2, type);
                if (this.l) {
                    throw y.o(this.f5679d, i2, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if (this.m) {
                    throw y.o(this.f5679d, i2, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.n) {
                    throw y.o(this.f5679d, i2, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.o) {
                    throw y.o(this.f5679d, i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.t == null) {
                    throw y.o(this.f5679d, i2, "@Path can only be used with relative url on @%s", this.p);
                }
                this.k = true;
                h.a0.s sVar = (h.a0.s) annotation;
                String strValue = sVar.value();
                i(i2, strValue);
                return new p.k(this.f5679d, i2, strValue, this.f5678c.i(type, annotationArr), sVar.encoded());
            }
            if (annotation instanceof h.a0.t) {
                j(i2, type);
                h.a0.t tVar = (h.a0.t) annotation;
                String strValue2 = tVar.value();
                boolean zEncoded = tVar.encoded();
                Class<?> clsH = y.h(type);
                this.l = true;
                if (!Iterable.class.isAssignableFrom(clsH)) {
                    return clsH.isArray() ? new p.l(strValue2, this.f5678c.i(a(clsH.getComponentType()), annotationArr), zEncoded).b() : new p.l(strValue2, this.f5678c.i(type, annotationArr), zEncoded);
                }
                if (type instanceof ParameterizedType) {
                    return new p.l(strValue2, this.f5678c.i(y.g(0, (ParameterizedType) type), annotationArr), zEncoded).c();
                }
                throw y.o(this.f5679d, i2, clsH.getSimpleName() + " must include generic type (e.g., " + clsH.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.a0.v) {
                j(i2, type);
                boolean zEncoded2 = ((h.a0.v) annotation).encoded();
                Class<?> clsH2 = y.h(type);
                this.m = true;
                if (!Iterable.class.isAssignableFrom(clsH2)) {
                    return clsH2.isArray() ? new p.n(this.f5678c.i(a(clsH2.getComponentType()), annotationArr), zEncoded2).b() : new p.n(this.f5678c.i(type, annotationArr), zEncoded2);
                }
                if (type instanceof ParameterizedType) {
                    return new p.n(this.f5678c.i(y.g(0, (ParameterizedType) type), annotationArr), zEncoded2).c();
                }
                throw y.o(this.f5679d, i2, clsH2.getSimpleName() + " must include generic type (e.g., " + clsH2.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.a0.u) {
                j(i2, type);
                Class<?> clsH3 = y.h(type);
                this.n = true;
                if (!Map.class.isAssignableFrom(clsH3)) {
                    throw y.o(this.f5679d, i2, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type typeI = y.i(type, clsH3, Map.class);
                if (!(typeI instanceof ParameterizedType)) {
                    throw y.o(this.f5679d, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType = (ParameterizedType) typeI;
                Type typeG = y.g(0, parameterizedType);
                if (String.class == typeG) {
                    return new p.m(this.f5679d, i2, this.f5678c.i(y.g(1, parameterizedType), annotationArr), ((h.a0.u) annotation).encoded());
                }
                throw y.o(this.f5679d, i2, "@QueryMap keys must be of type String: " + typeG, new Object[0]);
            }
            if (annotation instanceof h.a0.i) {
                j(i2, type);
                String strValue3 = ((h.a0.i) annotation).value();
                Class<?> clsH4 = y.h(type);
                if (!Iterable.class.isAssignableFrom(clsH4)) {
                    return clsH4.isArray() ? new p.f(strValue3, this.f5678c.i(a(clsH4.getComponentType()), annotationArr)).b() : new p.f(strValue3, this.f5678c.i(type, annotationArr));
                }
                if (type instanceof ParameterizedType) {
                    return new p.f(strValue3, this.f5678c.i(y.g(0, (ParameterizedType) type), annotationArr)).c();
                }
                throw y.o(this.f5679d, i2, clsH4.getSimpleName() + " must include generic type (e.g., " + clsH4.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.a0.j) {
                if (type == f.w.class) {
                    return new p.h(this.f5679d, i2);
                }
                j(i2, type);
                Class<?> clsH5 = y.h(type);
                if (!Map.class.isAssignableFrom(clsH5)) {
                    throw y.o(this.f5679d, i2, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type typeI2 = y.i(type, clsH5, Map.class);
                if (!(typeI2 instanceof ParameterizedType)) {
                    throw y.o(this.f5679d, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) typeI2;
                Type typeG2 = y.g(0, parameterizedType2);
                if (String.class == typeG2) {
                    return new p.g(this.f5679d, i2, this.f5678c.i(y.g(1, parameterizedType2), annotationArr));
                }
                throw y.o(this.f5679d, i2, "@HeaderMap keys must be of type String: " + typeG2, new Object[0]);
            }
            if (annotation instanceof h.a0.c) {
                j(i2, type);
                if (!this.r) {
                    throw y.o(this.f5679d, i2, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                h.a0.c cVar = (h.a0.c) annotation;
                String strValue4 = cVar.value();
                boolean zEncoded3 = cVar.encoded();
                this.f5683h = true;
                Class<?> clsH6 = y.h(type);
                if (!Iterable.class.isAssignableFrom(clsH6)) {
                    return clsH6.isArray() ? new p.d(strValue4, this.f5678c.i(a(clsH6.getComponentType()), annotationArr), zEncoded3).b() : new p.d(strValue4, this.f5678c.i(type, annotationArr), zEncoded3);
                }
                if (type instanceof ParameterizedType) {
                    return new p.d(strValue4, this.f5678c.i(y.g(0, (ParameterizedType) type), annotationArr), zEncoded3).c();
                }
                throw y.o(this.f5679d, i2, clsH6.getSimpleName() + " must include generic type (e.g., " + clsH6.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.a0.d) {
                j(i2, type);
                if (!this.r) {
                    throw y.o(this.f5679d, i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class<?> clsH7 = y.h(type);
                if (!Map.class.isAssignableFrom(clsH7)) {
                    throw y.o(this.f5679d, i2, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type typeI3 = y.i(type, clsH7, Map.class);
                if (!(typeI3 instanceof ParameterizedType)) {
                    throw y.o(this.f5679d, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType3 = (ParameterizedType) typeI3;
                Type typeG3 = y.g(0, parameterizedType3);
                if (String.class == typeG3) {
                    h hVarI = this.f5678c.i(y.g(1, parameterizedType3), annotationArr);
                    this.f5683h = true;
                    return new p.e(this.f5679d, i2, hVarI, ((h.a0.d) annotation).encoded());
                }
                throw y.o(this.f5679d, i2, "@FieldMap keys must be of type String: " + typeG3, new Object[0]);
            }
            if (annotation instanceof h.a0.q) {
                j(i2, type);
                if (!this.s) {
                    throw y.o(this.f5679d, i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                }
                h.a0.q qVar = (h.a0.q) annotation;
                this.f5684i = true;
                String strValue5 = qVar.value();
                Class<?> clsH8 = y.h(type);
                if (strValue5.isEmpty()) {
                    if (!Iterable.class.isAssignableFrom(clsH8)) {
                        if (clsH8.isArray()) {
                            if (a0.b.class.isAssignableFrom(clsH8.getComponentType())) {
                                return p.o.f5649a.b();
                            }
                            throw y.o(this.f5679d, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                        if (a0.b.class.isAssignableFrom(clsH8)) {
                            return p.o.f5649a;
                        }
                        throw y.o(this.f5679d, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    if (type instanceof ParameterizedType) {
                        if (a0.b.class.isAssignableFrom(y.h(y.g(0, (ParameterizedType) type)))) {
                            return p.o.f5649a.c();
                        }
                        throw y.o(this.f5679d, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    throw y.o(this.f5679d, i2, clsH8.getSimpleName() + " must include generic type (e.g., " + clsH8.getSimpleName() + "<String>)", new Object[0]);
                }
                f.w wVarG = f.w.g("Content-Disposition", "form-data; name=\"" + strValue5 + "\"", "Content-Transfer-Encoding", qVar.encoding());
                if (!Iterable.class.isAssignableFrom(clsH8)) {
                    if (!clsH8.isArray()) {
                        if (a0.b.class.isAssignableFrom(clsH8)) {
                            throw y.o(this.f5679d, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        return new p.i(this.f5679d, i2, wVarG, this.f5678c.g(type, annotationArr, this.f5680e));
                    }
                    Class<?> clsA = a(clsH8.getComponentType());
                    if (a0.b.class.isAssignableFrom(clsA)) {
                        throw y.o(this.f5679d, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new p.i(this.f5679d, i2, wVarG, this.f5678c.g(clsA, annotationArr, this.f5680e)).b();
                }
                if (type instanceof ParameterizedType) {
                    Type typeG4 = y.g(0, (ParameterizedType) type);
                    if (a0.b.class.isAssignableFrom(y.h(typeG4))) {
                        throw y.o(this.f5679d, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new p.i(this.f5679d, i2, wVarG, this.f5678c.g(typeG4, annotationArr, this.f5680e)).c();
                }
                throw y.o(this.f5679d, i2, clsH8.getSimpleName() + " must include generic type (e.g., " + clsH8.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof h.a0.r) {
                j(i2, type);
                if (!this.s) {
                    throw y.o(this.f5679d, i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.f5684i = true;
                Class<?> clsH9 = y.h(type);
                if (!Map.class.isAssignableFrom(clsH9)) {
                    throw y.o(this.f5679d, i2, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type typeI4 = y.i(type, clsH9, Map.class);
                if (!(typeI4 instanceof ParameterizedType)) {
                    throw y.o(this.f5679d, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType4 = (ParameterizedType) typeI4;
                Type typeG5 = y.g(0, parameterizedType4);
                if (String.class == typeG5) {
                    Type typeG6 = y.g(1, parameterizedType4);
                    if (a0.b.class.isAssignableFrom(y.h(typeG6))) {
                        throw y.o(this.f5679d, i2, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                    }
                    return new p.j(this.f5679d, i2, this.f5678c.g(typeG6, annotationArr, this.f5680e), ((h.a0.r) annotation).encoding());
                }
                throw y.o(this.f5679d, i2, "@PartMap keys must be of type String: " + typeG5, new Object[0]);
            }
            if (annotation instanceof h.a0.a) {
                j(i2, type);
                if (this.r || this.s) {
                    throw y.o(this.f5679d, i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                }
                if (this.j) {
                    throw y.o(this.f5679d, i2, "Multiple @Body method annotations found.", new Object[0]);
                }
                try {
                    h hVarG = this.f5678c.g(type, annotationArr, this.f5680e);
                    this.j = true;
                    return new p.c(this.f5679d, i2, hVarG);
                } catch (RuntimeException e2) {
                    throw y.p(this.f5679d, e2, i2, "Unable to create @Body converter for %s", type);
                }
            }
            if (!(annotation instanceof h.a0.x)) {
                return null;
            }
            j(i2, type);
            Class<?> clsH10 = y.h(type);
            for (int i3 = i2 - 1; i3 >= 0; i3--) {
                p<?> pVar = this.x[i3];
                if ((pVar instanceof p.q) && ((p.q) pVar).f5652a.equals(clsH10)) {
                    throw y.o(this.f5679d, i2, "@Tag type " + clsH10.getName() + " is duplicate of parameter #" + (i3 + 1) + " and would always overwrite its value.", new Object[0]);
                }
            }
            return new p.q(clsH10);
        }

        static Set<String> h(String str) {
            Matcher matcher = f5676a.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        private void i(int i2, String str) {
            if (!f5677b.matcher(str).matches()) {
                throw y.o(this.f5679d, i2, "@Path parameter name must match %s. Found: %s", f5676a.pattern(), str);
            }
            if (!this.w.contains(str)) {
                throw y.o(this.f5679d, i2, "URL \"%s\" does not contain \"{%s}\".", this.t, str);
            }
        }

        private void j(int i2, Type type) {
            if (y.j(type)) {
                throw y.o(this.f5679d, i2, "Parameter type must not include a type variable or wildcard: %s", type);
            }
        }

        s b() {
            for (Annotation annotation : this.f5680e) {
                e(annotation);
            }
            if (this.p == null) {
                throw y.m(this.f5679d, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.q) {
                if (this.s) {
                    throw y.m(this.f5679d, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
                if (this.r) {
                    throw y.m(this.f5679d, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.f5681f.length;
            this.x = new p[length];
            int i2 = length - 1;
            int i3 = 0;
            while (true) {
                boolean z = true;
                if (i3 >= length) {
                    break;
                }
                p<?>[] pVarArr = this.x;
                Type type = this.f5682g[i3];
                Annotation[] annotationArr = this.f5681f[i3];
                if (i3 != i2) {
                    z = false;
                }
                pVarArr[i3] = f(i3, type, annotationArr, z);
                i3++;
            }
            if (this.t == null && !this.o) {
                throw y.m(this.f5679d, "Missing either @%s URL or @Url parameter.", this.p);
            }
            boolean z2 = this.r;
            if (!z2 && !this.s && !this.q && this.j) {
                throw y.m(this.f5679d, "Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if (z2 && !this.f5683h) {
                throw y.m(this.f5679d, "Form-encoded method must contain at least one @Field.", new Object[0]);
            }
            if (!this.s || this.f5684i) {
                return new s(this);
            }
            throw y.m(this.f5679d, "Multipart method must contain at least one @Part.", new Object[0]);
        }
    }

    s(a aVar) {
        this.f5667a = aVar.f5679d;
        this.f5668b = aVar.f5678c.f5690c;
        this.f5669c = aVar.p;
        this.f5670d = aVar.t;
        this.f5671e = aVar.u;
        this.f5672f = aVar.v;
        this.f5673g = aVar.q;
        this.f5674h = aVar.r;
        this.f5675i = aVar.s;
        this.j = aVar.x;
        this.k = aVar.y;
    }

    static s b(u uVar, Method method) {
        return new a(uVar, method).b();
    }

    e0 a(Object[] objArr) {
        p<?>[] pVarArr = this.j;
        int length = objArr.length;
        if (length != pVarArr.length) {
            throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + pVarArr.length + ")");
        }
        r rVar = new r(this.f5669c, this.f5668b, this.f5670d, this.f5671e, this.f5672f, this.f5673g, this.f5674h, this.f5675i);
        if (this.k) {
            length--;
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(objArr[i2]);
            pVarArr[i2].a(rVar, objArr[i2]);
        }
        return rVar.k().i(l.class, new l(this.f5667a, arrayList)).a();
    }
}
