package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.w;

/* JADX INFO: compiled from: HttpServiceMethod.java */
/* JADX INFO: loaded from: classes.dex */
abstract class j<ResponseT, ReturnT> extends t<ReturnT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final q f5217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Call.Factory f5218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final h<ResponseBody, ResponseT> f5219c;

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class a<ResponseT, ReturnT> extends j<ResponseT, ReturnT> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, ReturnT> f5220d;

        a(q qVar, Call.Factory factory, h<ResponseBody, ResponseT> hVar, e<ResponseT, ReturnT> eVar) {
            super(qVar, factory, hVar);
            this.f5220d = eVar;
        }

        @Override // retrofit2.j
        protected ReturnT c(d<ResponseT> dVar, Object[] objArr) {
            return this.f5220d.b(dVar);
        }
    }

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class b<ResponseT> extends j<ResponseT, Object> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, d<ResponseT>> f5221d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f5222e;

        b(q qVar, Call.Factory factory, h<ResponseBody, ResponseT> hVar, e<ResponseT, d<ResponseT>> eVar, boolean z) {
            super(qVar, factory, hVar);
            this.f5221d = eVar;
            this.f5222e = z;
        }

        @Override // retrofit2.j
        protected Object c(d<ResponseT> dVar, Object[] objArr) {
            d<ResponseT> dVarB = this.f5221d.b(dVar);
            kotlin.l.a aVar = (kotlin.l.a) objArr[objArr.length - 1];
            try {
                return this.f5222e ? KotlinExtensions.b(dVarB, aVar) : KotlinExtensions.a(dVarB, aVar);
            } catch (Exception e2) {
                return KotlinExtensions.d(e2, aVar);
            }
        }
    }

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class c<ResponseT> extends j<ResponseT, Object> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, d<ResponseT>> f5223d;

        c(q qVar, Call.Factory factory, h<ResponseBody, ResponseT> hVar, e<ResponseT, d<ResponseT>> eVar) {
            super(qVar, factory, hVar);
            this.f5223d = eVar;
        }

        @Override // retrofit2.j
        protected Object c(d<ResponseT> dVar, Object[] objArr) {
            d<ResponseT> dVarB = this.f5223d.b(dVar);
            kotlin.l.a aVar = (kotlin.l.a) objArr[objArr.length - 1];
            try {
                return KotlinExtensions.c(dVarB, aVar);
            } catch (Exception e2) {
                return KotlinExtensions.d(e2, aVar);
            }
        }
    }

    j(q qVar, Call.Factory factory, h<ResponseBody, ResponseT> hVar) {
        this.f5217a = qVar;
        this.f5218b = factory;
        this.f5219c = hVar;
    }

    private static <ResponseT, ReturnT> e<ResponseT, ReturnT> d(s sVar, Method method, Type type, Annotation[] annotationArr) {
        try {
            return (e<ResponseT, ReturnT>) sVar.a(type, annotationArr);
        } catch (RuntimeException e2) {
            throw w.n(method, e2, "Unable to create call adapter for %s", type);
        }
    }

    private static <ResponseT> h<ResponseBody, ResponseT> e(s sVar, Method method, Type type) {
        try {
            return sVar.h(type, method.getAnnotations());
        } catch (RuntimeException e2) {
            throw w.n(method, e2, "Unable to create converter for %s", type);
        }
    }

    static <ResponseT, ReturnT> j<ResponseT, ReturnT> f(s sVar, Method method, q qVar) {
        Type genericReturnType;
        boolean z;
        boolean z2 = qVar.k;
        Annotation[] annotations = method.getAnnotations();
        if (z2) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Type typeF = w.f(0, (ParameterizedType) genericParameterTypes[genericParameterTypes.length - 1]);
            if (w.h(typeF) == r.class && (typeF instanceof ParameterizedType)) {
                typeF = w.g(0, (ParameterizedType) typeF);
                z = true;
            } else {
                z = false;
            }
            genericReturnType = new w.b(null, d.class, typeF);
            annotations = v.a(annotations);
        } else {
            genericReturnType = method.getGenericReturnType();
            z = false;
        }
        e eVarD = d(sVar, method, genericReturnType, annotations);
        Type typeA = eVarD.a();
        if (typeA == Response.class) {
            throw w.m(method, "'" + w.h(typeA).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }
        if (typeA == r.class) {
            throw w.m(method, "Response must include generic type (e.g., Response<String>)", new Object[0]);
        }
        if (qVar.f5304c.equals("HEAD") && !Void.class.equals(typeA)) {
            throw w.m(method, "HEAD method must use Void as response type.", new Object[0]);
        }
        h hVarE = e(sVar, method, typeA);
        Call.Factory factory = sVar.f5322b;
        return !z2 ? new a(qVar, factory, hVarE, eVarD) : z ? new c(qVar, factory, hVarE, eVarD) : new b(qVar, factory, hVarE, eVarD, false);
    }

    @Override // retrofit2.t
    @Nullable
    final ReturnT a(Object[] objArr) {
        return c(new l(this.f5217a, objArr, this.f5218b, this.f5219c), objArr);
    }

    @Nullable
    protected abstract ReturnT c(d<ResponseT> dVar, Object[] objArr);
}
