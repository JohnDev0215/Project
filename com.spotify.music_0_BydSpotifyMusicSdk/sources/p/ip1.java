package p;

import com.spotify.webapi.service.models.Search;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ResponseBody$Companion$asResponseBody$1;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;

/* JADX INFO: loaded from: classes.dex */
public final class ip1 implements jp {
    public final s32 c;
    public final Object[] e;
    public final Call.Factory k;
    public final h00 l;
    public volatile boolean m;
    public Call n;
    public Throwable o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f135p;

    public ip1(s32 s32Var, Object[] objArr, Call.Factory factory, h00 h00Var) {
        this.c = s32Var;
        this.e = objArr;
        this.k = factory;
        this.l = h00Var;
    }

    @Override // p.jp
    public final void E(pp ppVar) {
        Call call;
        Throwable th;
        synchronized (this) {
            try {
                if (this.f135p) {
                    throw new IllegalStateException("Already executed.");
                }
                this.f135p = true;
                call = this.n;
                th = this.o;
                if (call == null && th == null) {
                    try {
                        Call callA = a();
                        this.n = callA;
                        call = callA;
                    } catch (Throwable th2) {
                        th = th2;
                        bk0.T(th);
                        this.o = th;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        if (th != null) {
            ppVar.a(this, th);
            return;
        }
        if (this.m) {
            call.cancel();
        }
        call.J(new fp1(this, ppVar));
    }

    public final Call a() {
        HttpUrl httpUrlA;
        s32 s32Var = this.c;
        s32Var.getClass();
        Object[] objArr = this.e;
        int length = objArr.length;
        ux2[] ux2VarArr = s32Var.j;
        if (length != ux2VarArr.length) {
            throw new IllegalArgumentException(u4.l(u4.o("Argument count (", length, ") doesn't match expected count ("), ux2VarArr.length, ")"));
        }
        n32 n32Var = new n32(s32Var.c, s32Var.b, s32Var.d, s32Var.e, s32Var.f, s32Var.g, s32Var.h, s32Var.i);
        if (s32Var.k) {
            length--;
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(objArr[i]);
            ux2VarArr[i].c(n32Var, objArr[i]);
        }
        HttpUrl.Builder builder = n32Var.d;
        if (builder != null) {
            httpUrlA = builder.a();
        } else {
            String str = n32Var.c;
            HttpUrl httpUrl = n32Var.b;
            httpUrl.getClass();
            v03.j(str, Search.Type.LINK);
            HttpUrl.Builder builderF = httpUrl.f(str);
            httpUrlA = builderF != null ? builderF.a() : null;
            if (httpUrlA == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + httpUrl + ", Relative: " + n32Var.c);
            }
        }
        RequestBody m32Var = n32Var.k;
        if (m32Var == null) {
            FormBody.Builder builder2 = n32Var.j;
            if (builder2 != null) {
                m32Var = new FormBody(builder2.b, builder2.c);
            } else {
                MultipartBody.Builder builder3 = n32Var.i;
                if (builder3 != null) {
                    ArrayList arrayList2 = builder3.c;
                    if (!(!arrayList2.isEmpty())) {
                        throw new IllegalStateException("Multipart body must have at least one part.".toString());
                    }
                    m32Var = new MultipartBody(builder3.a, builder3.b, Util.y(arrayList2));
                } else if (n32Var.h) {
                    RequestBody.a.getClass();
                    m32Var = RequestBody.Companion.a(new byte[0], null, 0, 0);
                }
            }
        }
        MediaType mediaType = n32Var.g;
        Headers.Builder builder4 = n32Var.f;
        if (mediaType != null) {
            if (m32Var != null) {
                m32Var = new m32(m32Var, mediaType);
            } else {
                builder4.a("Content-Type", mediaType.a);
            }
        }
        Request.Builder builder5 = n32Var.e;
        builder5.getClass();
        builder5.a = httpUrlA;
        builder5.e(builder4.e());
        builder5.f(n32Var.a, m32Var);
        builder5.g(d61.class, new d61(s32Var.a, arrayList));
        RealCall realCallD = this.k.d(builder5.b());
        if (realCallD != null) {
            return realCallD;
        }
        throw new NullPointerException("Call.Factory returned null.");
    }

    @Override // p.jp
    public final u42 b() {
        Call callD;
        synchronized (this) {
            if (this.f135p) {
                throw new IllegalStateException("Already executed.");
            }
            this.f135p = true;
            callD = d();
        }
        if (this.m) {
            callD.cancel();
        }
        return e(callD.b());
    }

    @Override // p.jp
    public final synchronized Request c() {
        try {
        } catch (IOException e) {
            throw new RuntimeException("Unable to create request.", e);
        }
        return d().c();
    }

    @Override // p.jp
    public final void cancel() {
        Call call;
        this.m = true;
        synchronized (this) {
            call = this.n;
        }
        if (call != null) {
            call.cancel();
        }
    }

    public final Object clone() {
        return new ip1(this.c, this.e, this.k, this.l);
    }

    public final Call d() throws IOException {
        Call call = this.n;
        if (call != null) {
            return call;
        }
        Throwable th = this.o;
        if (th != null) {
            if (th instanceof IOException) {
                throw ((IOException) th);
            }
            if (th instanceof RuntimeException) {
                throw ((RuntimeException) th);
            }
            throw ((Error) th);
        }
        try {
            Call callA = a();
            this.n = callA;
            return callA;
        } catch (IOException | Error | RuntimeException e) {
            bk0.T(e);
            this.o = e;
            throw e;
        }
    }

    public final u42 e(Response response) throws IOException {
        ResponseBody responseBody = response.o;
        Response.Builder builderQ = response.q();
        builderQ.g = new hp1(responseBody.c(), responseBody.b());
        Response responseA = builderQ.a();
        int i = responseA.l;
        if (i < 200 || i >= 300) {
            try {
                fo foVar = new fo();
                responseBody.i().d0(foVar);
                MediaType mediaTypeC = responseBody.c();
                long jB = responseBody.b();
                ResponseBody.e.getClass();
                ResponseBody$Companion$asResponseBody$1 responseBody$Companion$asResponseBody$1 = new ResponseBody$Companion$asResponseBody$1(mediaTypeC, jB, foVar);
                if (responseA.i()) {
                    throw new IllegalArgumentException("rawResponse should not be successful response");
                }
                return new u42(responseA, null, responseBody$Companion$asResponseBody$1);
            } finally {
                responseBody.close();
            }
        }
        if (i == 204 || i == 205) {
            responseBody.close();
            if (responseA.i()) {
                return new u42(responseA, null, null);
            }
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        gp1 gp1Var = new gp1(responseBody);
        try {
            Object objConvert = this.l.convert(gp1Var);
            if (responseA.i()) {
                return new u42(responseA, objConvert, null);
            }
            throw new IllegalArgumentException("rawResponse must be successful response");
        } catch (RuntimeException e) {
            IOException iOException = gp1Var.m;
            if (iOException == null) {
                throw e;
            }
            throw iOException;
        }
    }

    @Override // p.jp
    public final boolean i() {
        boolean z = true;
        if (this.m) {
            return true;
        }
        synchronized (this) {
            try {
                Call call = this.n;
                if (call == null || !call.i()) {
                    z = false;
                }
            } finally {
            }
        }
        return z;
    }

    @Override // p.jp
    /* JADX INFO: renamed from: q */
    public final jp clone() {
        return new ip1(this.c, this.e, this.k, this.l);
    }
}
