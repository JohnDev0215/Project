package h;

import f.g0;
import f.h0;
import f.i;
import h.y;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: HttpServiceMethod.java */
/* JADX INFO: loaded from: classes.dex */
abstract class k<ResponseT, ReturnT> extends v<ReturnT> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final s f5570a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i.a f5571b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final h<h0, ResponseT> f5572c;

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class a<ResponseT, ReturnT> extends k<ResponseT, ReturnT> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, ReturnT> f5573d;

        a(s sVar, i.a aVar, h<h0, ResponseT> hVar, e<ResponseT, ReturnT> eVar) {
            super(sVar, aVar, hVar);
            this.f5573d = eVar;
        }

        @Override // h.k
        protected ReturnT c(d<ResponseT> dVar, Object[] objArr) {
            return this.f5573d.b(dVar);
        }
    }

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class b<ResponseT> extends k<ResponseT, Object> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, d<ResponseT>> f5574d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final boolean f5575e;

        b(s sVar, i.a aVar, h<h0, ResponseT> hVar, e<ResponseT, d<ResponseT>> eVar, boolean z) {
            super(sVar, aVar, hVar);
            this.f5574d = eVar;
            this.f5575e = z;
        }

        @Override // h.k
        protected Object c(d<ResponseT> dVar, Object[] objArr) {
            d<ResponseT> dVarB = this.f5574d.b(dVar);
            Continuation continuation = (Continuation) objArr[objArr.length - 1];
            try {
                return this.f5575e ? m.b(dVarB, continuation) : m.a(dVarB, continuation);
            } catch (Exception e2) {
                return m.d(e2, continuation);
            }
        }
    }

    /* JADX INFO: compiled from: HttpServiceMethod.java */
    static final class c<ResponseT> extends k<ResponseT, Object> {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final e<ResponseT, d<ResponseT>> f5576d;

        c(s sVar, i.a aVar, h<h0, ResponseT> hVar, e<ResponseT, d<ResponseT>> eVar) {
            super(sVar, aVar, hVar);
            this.f5576d = eVar;
        }

        @Override // h.k
        protected Object c(d<ResponseT> dVar, Object[] objArr) {
            d<ResponseT> dVarB = this.f5576d.b(dVar);
            Continuation continuation = (Continuation) objArr[objArr.length - 1];
            try {
                return m.c(dVarB, continuation);
            } catch (Exception e2) {
                return m.d(e2, continuation);
            }
        }
    }

    k(s sVar, i.a aVar, h<h0, ResponseT> hVar) {
        this.f5570a = sVar;
        this.f5571b = aVar;
        this.f5572c = hVar;
    }

    private static <ResponseT, ReturnT> e<ResponseT, ReturnT> d(u uVar, Method method, Type type, Annotation[] annotationArr) {
        try {
            return (e<ResponseT, ReturnT>) uVar.a(type, annotationArr);
        } catch (RuntimeException e2) {
            throw y.n(method, e2, "Unable to create call adapter for %s", type);
        }
    }

    private static <ResponseT> h<h0, ResponseT> e(u uVar, Method method, Type type) {
        try {
            return uVar.h(type, method.getAnnotations());
        } catch (RuntimeException e2) {
            throw y.n(method, e2, "Unable to create converter for %s", type);
        }
    }

    static <ResponseT, ReturnT> k<ResponseT, ReturnT> f(u uVar, Method method, s sVar) {
        Type genericReturnType;
        boolean z;
        boolean z2 = sVar.k;
        Annotation[] annotations = method.getAnnotations();
        if (z2) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            Type typeF = y.f(0, (ParameterizedType) genericParameterTypes[genericParameterTypes.length - 1]);
            if (y.h(typeF) == t.class && (typeF instanceof ParameterizedType)) {
                typeF = y.g(0, (ParameterizedType) typeF);
                z = true;
            } else {
                z = false;
            }
            genericReturnType = new y.b(null, d.class, typeF);
            annotations = x.a(annotations);
        } else {
            genericReturnType = method.getGenericReturnType();
            z = false;
        }
        e eVarD = d(uVar, method, genericReturnType, annotations);
        Type typeA = eVarD.a();
        if (typeA == g0.class) {
            throw y.m(method, "'" + y.h(typeA).getName() + "' is not a valid response body type. Did you mean ResponseBody?", new Object[0]);
        }
        if (typeA == t.class) {
            throw y.m(method, "Response must include generic type (e.g., Response<String>)", new Object[0]);
        }
        if (sVar.f5669c.equals("HEAD") && !Void.class.equals(typeA)) {
            throw y.m(method, "HEAD method must use Void as response type.", new Object[0]);
        }
        h hVarE = e(uVar, method, typeA);
        i.a aVar = uVar.f5689b;
        return !z2 ? new a(sVar, aVar, hVarE, eVarD) : z ? new c(sVar, aVar, hVarE, eVarD) : new b(sVar, aVar, hVarE, eVarD, false);
    }

    @Override // h.v
    @Nullable
    final ReturnT a(Object[] objArr) {
        return c(new n(this.f5570a, objArr, this.f5571b, this.f5572c), objArr);
    }

    @Nullable
    protected abstract ReturnT c(d<ResponseT> dVar, Object[] objArr);
}
