package c.a.g.f;

import android.text.TextUtils;
import c.a.b.d.k;
import c.a.g.h.d;
import com.byd.repository.bean.response.ResponseLoginInfo;
import com.byd.repository.bean.response.ResponseStatusCodeInfo;
import com.byd.repository.exception.ApiException;
import com.byd.repository.exception.InvalidTokenException;
import com.byd.repository.exception.NoRegisterException;
import d.a.g;
import d.a.h;
import d.a.o.e;
import e.u;
import e.z;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import retrofit2.HttpException;

/* JADX INFO: compiled from: GcyTokenDealFunction.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements e<g<Throwable>, h<?>> {
    public volatile g<String> E3;
    public c.a.b.c.a F3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2681c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2682d;
    public int s;
    public int x;
    public volatile g<String> y;

    /* JADX INFO: compiled from: GcyTokenDealFunction.java */
    public class a implements d<ResponseStatusCodeInfo, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2683a;

        public a(String str) {
            this.f2683a = str;
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.this.k();
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseStatusCodeInfo responseStatusCodeInfo) {
            c cVar = c.this;
            cVar.g(cVar.y, this.f2683a);
        }
    }

    /* JADX INFO: compiled from: GcyTokenDealFunction.java */
    public class b implements d<ResponseLoginInfo, String> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f2685a;

        public b(String str) {
            this.f2685a = str;
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str, Throwable th) {
            c.this.k();
            if (th instanceof NoRegisterException) {
                c cVar = c.this;
                cVar.h(cVar.E3, this.f2685a);
            }
        }

        @Override // c.a.g.h.d
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public void b(ResponseLoginInfo responseLoginInfo) {
            k.j("user_token", responseLoginInfo.token);
        }
    }

    /* JADX INFO: renamed from: c.a.g.f.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: GcyTokenDealFunction.java */
    public static class C0052c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static c f2687a = new c(null);
    }

    public /* synthetic */ c(a aVar) {
        this();
    }

    public static c i() {
        return C0052c.f2687a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: l, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ h m(Throwable th) throws Exception {
        int i2;
        boolean z = false;
        if ((this.x > 0 || ((th instanceof IOException) && !(th instanceof ApiException))) && (i2 = this.f2682d) < this.f2681c) {
            int i3 = i2 + 1;
            this.f2682d = i3;
            this.s = (i3 * 1000) + 100;
            return g.m(1).c(this.s, TimeUnit.MICROSECONDS);
        }
        boolean z2 = th instanceof NoRegisterException;
        boolean z3 = (th instanceof HttpException) && 401 == ((HttpException) th).code();
        int i4 = this.x;
        if ((i4 == 0 && (z3 || (th instanceof InvalidTokenException))) || (i4 == 1 && z2)) {
            z = true;
        }
        if (!z) {
            k();
            return g.f(th);
        }
        this.x = i4 + 1;
        String strJ = j();
        if (TextUtils.isEmpty(strJ)) {
            k();
            return g.f(new ApiException("-1", "Get vin or iccid error, vin or iccid is null"));
        }
        if (z2) {
            this.E3 = c.a.g.e.h.d().a("register", z.d(u.c("application/json;charset=utf-8"), strJ)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(i());
            if (this.E3 != null) {
                h(this.E3, strJ);
                return this.E3;
            }
            k();
            return g.f(new ApiException("-1", "auto register error"));
        }
        k.k("user_token");
        this.y = c.a.g.e.h.d().a("loginIn", z.d(u.c("application/json;charset=utf-8"), strJ)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(i());
        if (this.y != null) {
            g(this.y, strJ);
            return this.y;
        }
        k();
        return g.f(new ApiException("-1", "auto login error"));
    }

    @Override // d.a.o.e
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public h<?> apply(g<Throwable> gVar) throws Exception {
        return gVar.h(new e() { // from class: c.a.g.f.a
            @Override // d.a.o.e
            public final Object apply(Object obj) {
                return this.f2678c.m((Throwable) obj);
            }
        });
    }

    public final void g(g<String> gVar, String str) {
        if (gVar == null) {
            gVar = c.a.g.e.h.d().a("loginIn", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(i());
        }
        gVar.a(new c.a.g.h.e(ResponseLoginInfo.class, new b(str)));
    }

    public final void h(g<String> gVar, String str) {
        if (gVar == null) {
            gVar = c.a.g.e.h.d().a("register", z.d(u.c("application/json;charset=utf-8"), str)).n(c.a.g.f.b.b()).u(d.a.s.a.b()).o(d.a.s.a.b()).q(i());
        }
        gVar.a(new c.a.g.h.e(ResponseStatusCodeInfo.class, new a(str)));
    }

    public final String j() {
        HashMap map = new HashMap(4);
        map.put("vin", this.F3.c());
        map.put("iccid", this.F3.E());
        map.put("uuid", this.F3.B());
        return c.a.g.l.h.f(map);
    }

    public final void k() {
        this.f2681c = 3;
        this.f2682d = 0;
        this.s = 0;
        this.x = 0;
    }

    public c() {
        this.f2681c = 3;
        this.f2682d = 0;
        this.s = 0;
        this.x = 0;
        this.F3 = c.a.b.c.a.k();
        k();
    }
}
