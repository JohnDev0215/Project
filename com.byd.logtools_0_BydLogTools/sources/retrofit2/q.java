package retrofit2;

import java.io.IOException;
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
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import retrofit2.n;
import retrofit2.y.x;
import retrofit2.y.y;

/* JADX INFO: compiled from: RequestFactory.java */
/* JADX INFO: loaded from: classes.dex */
final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Method f5302a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HttpUrl f5303b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String f5304c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Nullable
    private final String f5305d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private final Headers f5306e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private final MediaType f5307f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f5308g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f5309h;
    private final boolean i;
    private final n<?>[] j;
    final boolean k;

    /* JADX INFO: compiled from: RequestFactory.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Pattern f5310a = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final Pattern f5311b = Pattern.compile("[a-zA-Z][a-zA-Z0-9_-]*");

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final s f5312c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final Method f5313d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final Annotation[] f5314e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final Annotation[][] f5315f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final Type[] f5316g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        boolean f5317h;
        boolean i;
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
        Headers u;

        @Nullable
        MediaType v;

        @Nullable
        Set<String> w;

        @Nullable
        n<?>[] x;
        boolean y;

        a(s sVar, Method method) {
            this.f5312c = sVar;
            this.f5313d = method;
            this.f5314e = method.getAnnotations();
            this.f5316g = method.getGenericParameterTypes();
            this.f5315f = method.getParameterAnnotations();
        }

        private static Class<?> a(Class<?> cls) {
            return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
        }

        private Headers c(String[] strArr) {
            Headers.Builder builder = new Headers.Builder();
            for (String str : strArr) {
                int iIndexOf = str.indexOf(58);
                if (iIndexOf == -1 || iIndexOf == 0 || iIndexOf == str.length() - 1) {
                    throw w.m(this.f5313d, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String strSubstring = str.substring(0, iIndexOf);
                String strTrim = str.substring(iIndexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(strSubstring)) {
                    try {
                        this.v = MediaType.get(strTrim);
                    } catch (IllegalArgumentException e2) {
                        throw w.n(this.f5313d, e2, "Malformed content type: %s", strTrim);
                    }
                } else {
                    builder.add(strSubstring, strTrim);
                }
            }
            return builder.build();
        }

        private void d(String str, String str2, boolean z) {
            String str3 = this.p;
            if (str3 != null) {
                throw w.m(this.f5313d, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.p = str;
            this.q = z;
            if (str2.isEmpty()) {
                return;
            }
            int iIndexOf = str2.indexOf(63);
            if (iIndexOf != -1 && iIndexOf < str2.length() - 1) {
                String strSubstring = str2.substring(iIndexOf + 1);
                if (f5310a.matcher(strSubstring).find()) {
                    throw w.m(this.f5313d, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", strSubstring);
                }
            }
            this.t = str2;
            this.w = h(str2);
        }

        private void e(Annotation annotation) {
            if (annotation instanceof retrofit2.y.b) {
                d("DELETE", ((retrofit2.y.b) annotation).value(), false);
                return;
            }
            if (annotation instanceof retrofit2.y.f) {
                d("GET", ((retrofit2.y.f) annotation).value(), false);
                return;
            }
            if (annotation instanceof retrofit2.y.g) {
                d("HEAD", ((retrofit2.y.g) annotation).value(), false);
                return;
            }
            if (annotation instanceof retrofit2.y.n) {
                d("PATCH", ((retrofit2.y.n) annotation).value(), true);
                return;
            }
            if (annotation instanceof retrofit2.y.o) {
                d("POST", ((retrofit2.y.o) annotation).value(), true);
                return;
            }
            if (annotation instanceof retrofit2.y.p) {
                d("PUT", ((retrofit2.y.p) annotation).value(), true);
                return;
            }
            if (annotation instanceof retrofit2.y.m) {
                d("OPTIONS", ((retrofit2.y.m) annotation).value(), false);
                return;
            }
            if (annotation instanceof retrofit2.y.h) {
                retrofit2.y.h hVar = (retrofit2.y.h) annotation;
                d(hVar.method(), hVar.path(), hVar.hasBody());
                return;
            }
            if (annotation instanceof retrofit2.y.k) {
                String[] strArrValue = ((retrofit2.y.k) annotation).value();
                if (strArrValue.length == 0) {
                    throw w.m(this.f5313d, "@Headers annotation is empty.", new Object[0]);
                }
                this.u = c(strArrValue);
                return;
            }
            if (annotation instanceof retrofit2.y.l) {
                if (this.r) {
                    throw w.m(this.f5313d, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.s = true;
            } else if (annotation instanceof retrofit2.y.e) {
                if (this.s) {
                    throw w.m(this.f5313d, "Only one encoding annotation is allowed.", new Object[0]);
                }
                this.r = true;
            }
        }

        @Nullable
        private n<?> f(int i, Type type, @Nullable Annotation[] annotationArr, boolean z) {
            n<?> nVar;
            if (annotationArr != null) {
                nVar = null;
                for (Annotation annotation : annotationArr) {
                    n<?> nVarG = g(i, type, annotationArr, annotation);
                    if (nVarG != null) {
                        if (nVar != null) {
                            throw w.o(this.f5313d, i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                        nVar = nVarG;
                    }
                }
            } else {
                nVar = null;
            }
            if (nVar != null) {
                return nVar;
            }
            if (z) {
                try {
                    if (w.h(type) == kotlin.l.a.class) {
                        this.y = true;
                        return null;
                    }
                } catch (NoClassDefFoundError unused) {
                }
            }
            throw w.o(this.f5313d, i, "No Retrofit annotation found.", new Object[0]);
        }

        @Nullable
        private n<?> g(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof y) {
                j(i, type);
                if (this.o) {
                    throw w.o(this.f5313d, i, "Multiple @Url method annotations found.", new Object[0]);
                }
                if (this.k) {
                    throw w.o(this.f5313d, i, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.l) {
                    throw w.o(this.f5313d, i, "A @Url parameter must not come after a @Query.", new Object[0]);
                }
                if (this.m) {
                    throw w.o(this.f5313d, i, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.n) {
                    throw w.o(this.f5313d, i, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.t != null) {
                    throw w.o(this.f5313d, i, "@Url cannot be used with @%s URL", this.p);
                }
                this.o = true;
                if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                    return new n.p(this.f5313d, i);
                }
                throw w.o(this.f5313d, i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.s) {
                j(i, type);
                if (this.l) {
                    throw w.o(this.f5313d, i, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if (this.m) {
                    throw w.o(this.f5313d, i, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.n) {
                    throw w.o(this.f5313d, i, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.o) {
                    throw w.o(this.f5313d, i, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.t == null) {
                    throw w.o(this.f5313d, i, "@Path can only be used with relative url on @%s", this.p);
                }
                this.k = true;
                retrofit2.y.s sVar = (retrofit2.y.s) annotation;
                String strValue = sVar.value();
                i(i, strValue);
                return new n.k(this.f5313d, i, strValue, this.f5312c.i(type, annotationArr), sVar.encoded());
            }
            if (annotation instanceof retrofit2.y.t) {
                j(i, type);
                retrofit2.y.t tVar = (retrofit2.y.t) annotation;
                String strValue2 = tVar.value();
                boolean zEncoded = tVar.encoded();
                Class<?> clsH = w.h(type);
                this.l = true;
                if (!Iterable.class.isAssignableFrom(clsH)) {
                    return clsH.isArray() ? new n.l(strValue2, this.f5312c.i(a(clsH.getComponentType()), annotationArr), zEncoded).b() : new n.l(strValue2, this.f5312c.i(type, annotationArr), zEncoded);
                }
                if (type instanceof ParameterizedType) {
                    return new n.l(strValue2, this.f5312c.i(w.g(0, (ParameterizedType) type), annotationArr), zEncoded).c();
                }
                throw w.o(this.f5313d, i, clsH.getSimpleName() + " must include generic type (e.g., " + clsH.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.v) {
                j(i, type);
                boolean zEncoded2 = ((retrofit2.y.v) annotation).encoded();
                Class<?> clsH2 = w.h(type);
                this.m = true;
                if (!Iterable.class.isAssignableFrom(clsH2)) {
                    return clsH2.isArray() ? new n.C0099n(this.f5312c.i(a(clsH2.getComponentType()), annotationArr), zEncoded2).b() : new n.C0099n(this.f5312c.i(type, annotationArr), zEncoded2);
                }
                if (type instanceof ParameterizedType) {
                    return new n.C0099n(this.f5312c.i(w.g(0, (ParameterizedType) type), annotationArr), zEncoded2).c();
                }
                throw w.o(this.f5313d, i, clsH2.getSimpleName() + " must include generic type (e.g., " + clsH2.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.u) {
                j(i, type);
                Class<?> clsH3 = w.h(type);
                this.n = true;
                if (!Map.class.isAssignableFrom(clsH3)) {
                    throw w.o(this.f5313d, i, "@QueryMap parameter type must be Map.", new Object[0]);
                }
                Type typeI = w.i(type, clsH3, Map.class);
                if (!(typeI instanceof ParameterizedType)) {
                    throw w.o(this.f5313d, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType = (ParameterizedType) typeI;
                Type typeG = w.g(0, parameterizedType);
                if (String.class == typeG) {
                    return new n.m(this.f5313d, i, this.f5312c.i(w.g(1, parameterizedType), annotationArr), ((retrofit2.y.u) annotation).encoded());
                }
                throw w.o(this.f5313d, i, "@QueryMap keys must be of type String: " + typeG, new Object[0]);
            }
            if (annotation instanceof retrofit2.y.i) {
                j(i, type);
                String strValue3 = ((retrofit2.y.i) annotation).value();
                Class<?> clsH4 = w.h(type);
                if (!Iterable.class.isAssignableFrom(clsH4)) {
                    return clsH4.isArray() ? new n.f(strValue3, this.f5312c.i(a(clsH4.getComponentType()), annotationArr)).b() : new n.f(strValue3, this.f5312c.i(type, annotationArr));
                }
                if (type instanceof ParameterizedType) {
                    return new n.f(strValue3, this.f5312c.i(w.g(0, (ParameterizedType) type), annotationArr)).c();
                }
                throw w.o(this.f5313d, i, clsH4.getSimpleName() + " must include generic type (e.g., " + clsH4.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.j) {
                if (type == Headers.class) {
                    return new n.h(this.f5313d, i);
                }
                j(i, type);
                Class<?> clsH5 = w.h(type);
                if (!Map.class.isAssignableFrom(clsH5)) {
                    throw w.o(this.f5313d, i, "@HeaderMap parameter type must be Map.", new Object[0]);
                }
                Type typeI2 = w.i(type, clsH5, Map.class);
                if (!(typeI2 instanceof ParameterizedType)) {
                    throw w.o(this.f5313d, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType2 = (ParameterizedType) typeI2;
                Type typeG2 = w.g(0, parameterizedType2);
                if (String.class == typeG2) {
                    return new n.g(this.f5313d, i, this.f5312c.i(w.g(1, parameterizedType2), annotationArr));
                }
                throw w.o(this.f5313d, i, "@HeaderMap keys must be of type String: " + typeG2, new Object[0]);
            }
            if (annotation instanceof retrofit2.y.c) {
                j(i, type);
                if (!this.r) {
                    throw w.o(this.f5313d, i, "@Field parameters can only be used with form encoding.", new Object[0]);
                }
                retrofit2.y.c cVar = (retrofit2.y.c) annotation;
                String strValue4 = cVar.value();
                boolean zEncoded3 = cVar.encoded();
                this.f5317h = true;
                Class<?> clsH6 = w.h(type);
                if (!Iterable.class.isAssignableFrom(clsH6)) {
                    return clsH6.isArray() ? new n.d(strValue4, this.f5312c.i(a(clsH6.getComponentType()), annotationArr), zEncoded3).b() : new n.d(strValue4, this.f5312c.i(type, annotationArr), zEncoded3);
                }
                if (type instanceof ParameterizedType) {
                    return new n.d(strValue4, this.f5312c.i(w.g(0, (ParameterizedType) type), annotationArr), zEncoded3).c();
                }
                throw w.o(this.f5313d, i, clsH6.getSimpleName() + " must include generic type (e.g., " + clsH6.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.d) {
                j(i, type);
                if (!this.r) {
                    throw w.o(this.f5313d, i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
                }
                Class<?> clsH7 = w.h(type);
                if (!Map.class.isAssignableFrom(clsH7)) {
                    throw w.o(this.f5313d, i, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                Type typeI3 = w.i(type, clsH7, Map.class);
                if (!(typeI3 instanceof ParameterizedType)) {
                    throw w.o(this.f5313d, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType3 = (ParameterizedType) typeI3;
                Type typeG3 = w.g(0, parameterizedType3);
                if (String.class == typeG3) {
                    h hVarI = this.f5312c.i(w.g(1, parameterizedType3), annotationArr);
                    this.f5317h = true;
                    return new n.e(this.f5313d, i, hVarI, ((retrofit2.y.d) annotation).encoded());
                }
                throw w.o(this.f5313d, i, "@FieldMap keys must be of type String: " + typeG3, new Object[0]);
            }
            if (annotation instanceof retrofit2.y.q) {
                j(i, type);
                if (!this.s) {
                    throw w.o(this.f5313d, i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
                }
                retrofit2.y.q qVar = (retrofit2.y.q) annotation;
                this.i = true;
                String strValue5 = qVar.value();
                Class<?> clsH8 = w.h(type);
                if (strValue5.isEmpty()) {
                    if (!Iterable.class.isAssignableFrom(clsH8)) {
                        if (clsH8.isArray()) {
                            if (MultipartBody.Part.class.isAssignableFrom(clsH8.getComponentType())) {
                                return n.o.f5284a.b();
                            }
                            throw w.o(this.f5313d, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                        if (MultipartBody.Part.class.isAssignableFrom(clsH8)) {
                            return n.o.f5284a;
                        }
                        throw w.o(this.f5313d, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    if (type instanceof ParameterizedType) {
                        if (MultipartBody.Part.class.isAssignableFrom(w.h(w.g(0, (ParameterizedType) type)))) {
                            return n.o.f5284a.c();
                        }
                        throw w.o(this.f5313d, i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                    throw w.o(this.f5313d, i, clsH8.getSimpleName() + " must include generic type (e.g., " + clsH8.getSimpleName() + "<String>)", new Object[0]);
                }
                Headers headersOf = Headers.of("Content-Disposition", "form-data; name=\"" + strValue5 + "\"", "Content-Transfer-Encoding", qVar.encoding());
                if (!Iterable.class.isAssignableFrom(clsH8)) {
                    if (!clsH8.isArray()) {
                        if (MultipartBody.Part.class.isAssignableFrom(clsH8)) {
                            throw w.o(this.f5313d, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        return new n.i(this.f5313d, i, headersOf, this.f5312c.g(type, annotationArr, this.f5314e));
                    }
                    Class<?> clsA = a(clsH8.getComponentType());
                    if (MultipartBody.Part.class.isAssignableFrom(clsA)) {
                        throw w.o(this.f5313d, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new n.i(this.f5313d, i, headersOf, this.f5312c.g(clsA, annotationArr, this.f5314e)).b();
                }
                if (type instanceof ParameterizedType) {
                    Type typeG4 = w.g(0, (ParameterizedType) type);
                    if (MultipartBody.Part.class.isAssignableFrom(w.h(typeG4))) {
                        throw w.o(this.f5313d, i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new n.i(this.f5313d, i, headersOf, this.f5312c.g(typeG4, annotationArr, this.f5314e)).c();
                }
                throw w.o(this.f5313d, i, clsH8.getSimpleName() + " must include generic type (e.g., " + clsH8.getSimpleName() + "<String>)", new Object[0]);
            }
            if (annotation instanceof retrofit2.y.r) {
                j(i, type);
                if (!this.s) {
                    throw w.o(this.f5313d, i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
                }
                this.i = true;
                Class<?> clsH9 = w.h(type);
                if (!Map.class.isAssignableFrom(clsH9)) {
                    throw w.o(this.f5313d, i, "@PartMap parameter type must be Map.", new Object[0]);
                }
                Type typeI4 = w.i(type, clsH9, Map.class);
                if (!(typeI4 instanceof ParameterizedType)) {
                    throw w.o(this.f5313d, i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                ParameterizedType parameterizedType4 = (ParameterizedType) typeI4;
                Type typeG5 = w.g(0, parameterizedType4);
                if (String.class == typeG5) {
                    Type typeG6 = w.g(1, parameterizedType4);
                    if (MultipartBody.Part.class.isAssignableFrom(w.h(typeG6))) {
                        throw w.o(this.f5313d, i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                    }
                    return new n.j(this.f5313d, i, this.f5312c.g(typeG6, annotationArr, this.f5314e), ((retrofit2.y.r) annotation).encoding());
                }
                throw w.o(this.f5313d, i, "@PartMap keys must be of type String: " + typeG5, new Object[0]);
            }
            if (annotation instanceof retrofit2.y.a) {
                j(i, type);
                if (this.r || this.s) {
                    throw w.o(this.f5313d, i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                }
                if (this.j) {
                    throw w.o(this.f5313d, i, "Multiple @Body method annotations found.", new Object[0]);
                }
                try {
                    h hVarG = this.f5312c.g(type, annotationArr, this.f5314e);
                    this.j = true;
                    return new n.c(this.f5313d, i, hVarG);
                } catch (RuntimeException e2) {
                    throw w.p(this.f5313d, e2, i, "Unable to create @Body converter for %s", type);
                }
            }
            if (!(annotation instanceof x)) {
                return null;
            }
            j(i, type);
            Class<?> clsH10 = w.h(type);
            for (int i2 = i - 1; i2 >= 0; i2--) {
                n<?> nVar = this.x[i2];
                if ((nVar instanceof n.q) && ((n.q) nVar).f5287a.equals(clsH10)) {
                    throw w.o(this.f5313d, i, "@Tag type " + clsH10.getName() + " is duplicate of parameter #" + (i2 + 1) + " and would always overwrite its value.", new Object[0]);
                }
            }
            return new n.q(clsH10);
        }

        static Set<String> h(String str) {
            Matcher matcher = f5310a.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        private void i(int i, String str) {
            if (!f5311b.matcher(str).matches()) {
                throw w.o(this.f5313d, i, "@Path parameter name must match %s. Found: %s", f5310a.pattern(), str);
            }
            if (!this.w.contains(str)) {
                throw w.o(this.f5313d, i, "URL \"%s\" does not contain \"{%s}\".", this.t, str);
            }
        }

        private void j(int i, Type type) {
            if (w.j(type)) {
                throw w.o(this.f5313d, i, "Parameter type must not include a type variable or wildcard: %s", type);
            }
        }

        q b() {
            for (Annotation annotation : this.f5314e) {
                e(annotation);
            }
            if (this.p == null) {
                throw w.m(this.f5313d, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
            }
            if (!this.q) {
                if (this.s) {
                    throw w.m(this.f5313d, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
                if (this.r) {
                    throw w.m(this.f5313d, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.f5315f.length;
            this.x = new n[length];
            int i = length - 1;
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 >= length) {
                    break;
                }
                n<?>[] nVarArr = this.x;
                Type type = this.f5316g[i2];
                Annotation[] annotationArr = this.f5315f[i2];
                if (i2 != i) {
                    z = false;
                }
                nVarArr[i2] = f(i2, type, annotationArr, z);
                i2++;
            }
            if (this.t == null && !this.o) {
                throw w.m(this.f5313d, "Missing either @%s URL or @Url parameter.", this.p);
            }
            boolean z2 = this.r;
            if (!z2 && !this.s && !this.q && this.j) {
                throw w.m(this.f5313d, "Non-body HTTP method cannot contain @Body.", new Object[0]);
            }
            if (z2 && !this.f5317h) {
                throw w.m(this.f5313d, "Form-encoded method must contain at least one @Field.", new Object[0]);
            }
            if (!this.s || this.i) {
                return new q(this);
            }
            throw w.m(this.f5313d, "Multipart method must contain at least one @Part.", new Object[0]);
        }
    }

    q(a aVar) {
        this.f5302a = aVar.f5313d;
        this.f5303b = aVar.f5312c.f5323c;
        this.f5304c = aVar.p;
        this.f5305d = aVar.t;
        this.f5306e = aVar.u;
        this.f5307f = aVar.v;
        this.f5308g = aVar.q;
        this.f5309h = aVar.r;
        this.i = aVar.s;
        this.j = aVar.x;
        this.k = aVar.y;
    }

    static q b(s sVar, Method method) {
        return new a(sVar, method).b();
    }

    Request a(Object[] objArr) throws IOException {
        n<?>[] nVarArr = this.j;
        int length = objArr.length;
        if (length != nVarArr.length) {
            throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + nVarArr.length + ")");
        }
        p pVar = new p(this.f5304c, this.f5303b, this.f5305d, this.f5306e, this.f5307f, this.f5308g, this.f5309h, this.i);
        if (this.k) {
            length--;
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(objArr[i]);
            nVarArr[i].a(pVar, objArr[i]);
        }
        return pVar.k().tag(k.class, new k(this.f5302a, arrayList)).build();
    }
}
