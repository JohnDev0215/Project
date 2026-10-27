package OooO00o.OooO00o.OooO0Oo.OooO0OO.b;

import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.e;
import android.content.Context;
import android.hardware.bydauto.TsNormalNum;
import android.os.Handler;
import android.os.Looper;
import com.android.volley.BuildConfig;
import com.google.gson.Gson;
import com.loostone.libserver.version1.entity.request.BaseRequest;
import com.loostone.libserver.version1.entity.response.ResponseData;
import com.loostone.libserver.version1.net.NetParam;
import com.loostone.libserver.version1.net.NetRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Long f77a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f79c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f80d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f78b = getClass().getSimpleName();
    public Boolean f = Boolean.FALSE;
    public long g = 0;
    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.c.b e = i();

    public class a implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a f81a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a f82b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ BaseRequest f83c;

        public a(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar, OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a aVar2, BaseRequest baseRequest) {
            this.f81a = aVar;
            this.f82b = aVar2;
            this.f83c = baseRequest;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar = this.f81a;
            if (aVar != null) {
                aVar.onError(str);
            }
            this.f82b.c(str);
            this.f82b.d(false);
            this.f82b.b(System.currentTimeMillis());
            b.this.c(this.f82b);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            this.f82b.c(str);
            this.f82b.b(System.currentTimeMillis());
            ResponseData responseDataA = ((OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a) b.this.e).a(str);
            if (responseDataA.getUuid() != null && responseDataA.getUuid().equals(this.f83c.getUuid())) {
                this.f82b.d(true);
                b.this.c(this.f82b);
                OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar = this.f81a;
                if (aVar != null) {
                    aVar.a(responseDataA);
                    return;
                }
                return;
            }
            OooO00o.OooO00o.OooO0O0.b.f41a.b(b.this.f78b, "----->无效的请求");
            responseDataA.setState("-000002");
            try {
                responseDataA.setMsg(URLEncoder.encode("无效的请求", "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                OooO00o.OooO00o.OooO0O0.b.f41a.d(b.this.f78b, e.getMessage());
            }
            this.f82b.d(true);
            b.this.c(this.f82b);
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar2 = this.f81a;
            if (aVar2 != null) {
                aVar2.a(responseDataA);
            }
        }
    }

    static {
        new Handler(Looper.getMainLooper());
        f77a = 86400000L;
    }

    public b(Context context, String str) {
        this.f80d = context.getApplicationContext();
        this.f79c = str;
    }

    public Context b() {
        return this.f80d;
    }

    public void c(OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a aVar) {
        if (this.f.booleanValue()) {
            c.c(OooO00o.OooO00o.OooO0O0.a.f39a.a(), aVar.h(), new Gson().toJson(aVar));
        }
    }

    public void d(Boolean bool) {
        this.f = bool;
        this.g = f77a.longValue();
    }

    public void e(Boolean bool, long j) {
        this.f = bool;
        this.g = j;
    }

    public void f(String str, BaseRequest baseRequest, int i, int i2, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar) {
        baseRequest.set_time(System.currentTimeMillis() + BuildConfig.FLAVOR);
        baseRequest.setUuid(UUID.randomUUID().toString());
        baseRequest.setMac(e.b(b()));
        NetParam netParam = new NetParam();
        netParam.param = ((OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a) this.e).c(baseRequest);
        netParam.requestTag = this.f79c;
        netParam.url = str + "?des=2";
        netParam.isRawRequest = true;
        netParam.header = ((OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a) this.e).b();
        netParam.retry = i;
        netParam.timeout = i2;
        OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a aVar2 = new OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a();
        aVar2.e(str);
        NetRequest.request(this.f80d, netParam, new a(aVar, aVar2, baseRequest));
    }

    public void g(String str, BaseRequest baseRequest, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.a aVar) {
        String strA;
        if (this.f.booleanValue() && (strA = c.a(OooO00o.OooO00o.OooO0O0.a.f39a.a(), str)) != null && !strA.equals(BuildConfig.FLAVOR)) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a aVar2 = (OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a) new Gson().fromJson(strA, OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e.a.class);
            if (System.currentTimeMillis() - aVar2.g() < this.g && aVar != null && aVar2.f()) {
                aVar.a(((OooO00o.OooO00o.OooO0Oo.OooO0OO.c.a) h()).a(aVar2.a()));
                return;
            }
        }
        f(str, baseRequest, 3, TsNormalNum.THOUSAND_FIVE, aVar);
    }

    public OooO00o.OooO00o.OooO0Oo.OooO0OO.c.b h() {
        return this.e;
    }

    public abstract OooO00o.OooO00o.OooO0Oo.OooO0OO.c.b i();
}
