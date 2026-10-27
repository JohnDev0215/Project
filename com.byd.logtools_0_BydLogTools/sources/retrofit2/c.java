package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.h;

/* JADX INFO: compiled from: BuiltInConverters.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f5194a = true;

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class a implements h<ResponseBody, ResponseBody> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final a f5195a = new a();

        a() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResponseBody a(ResponseBody responseBody) throws IOException {
            try {
                return w.a(responseBody);
            } finally {
                responseBody.close();
            }
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class b implements h<RequestBody, RequestBody> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f5196a = new b();

        b() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public RequestBody a(RequestBody requestBody) {
            return requestBody;
        }
    }

    /* JADX INFO: renamed from: retrofit2.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class C0097c implements h<ResponseBody, ResponseBody> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final C0097c f5197a = new C0097c();

        C0097c() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResponseBody a(ResponseBody responseBody) {
            return responseBody;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class d implements h<Object, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final d f5198a = new d();

        d() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public String a(Object obj) {
            return obj.toString();
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class e implements h<ResponseBody, kotlin.j> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final e f5199a = new e();

        e() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public kotlin.j a(ResponseBody responseBody) {
            responseBody.close();
            return kotlin.j.f5109a;
        }
    }

    /* JADX INFO: compiled from: BuiltInConverters.java */
    static final class f implements h<ResponseBody, Void> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final f f5200a = new f();

        f() {
        }

        @Override // retrofit2.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Void a(ResponseBody responseBody) {
            responseBody.close();
            return null;
        }
    }

    c() {
    }

    @Override // retrofit2.h.a
    @Nullable
    public h<?, RequestBody> c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, s sVar) {
        if (RequestBody.class.isAssignableFrom(w.h(type))) {
            return b.f5196a;
        }
        return null;
    }

    @Override // retrofit2.h.a
    @Nullable
    public h<ResponseBody, ?> d(Type type, Annotation[] annotationArr, s sVar) {
        if (type == ResponseBody.class) {
            return w.l(annotationArr, retrofit2.y.w.class) ? C0097c.f5197a : a.f5195a;
        }
        if (type == Void.class) {
            return f.f5200a;
        }
        if (!this.f5194a || type != kotlin.j.class) {
            return null;
        }
        try {
            return e.f5199a;
        } catch (NoClassDefFoundError unused) {
            this.f5194a = false;
            return null;
        }
    }
}
