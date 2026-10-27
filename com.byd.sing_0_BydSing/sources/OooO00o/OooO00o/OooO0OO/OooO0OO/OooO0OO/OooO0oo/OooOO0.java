package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO0oo;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.j;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.i;
import com.android.volley.BuildConfig;
import com.loostone.libserver.version2.bean.BaseResponseData;
import com.loostone.libserver.version2.bean.ParameterClient;
import com.loostone.libserver.version2.bean.TokenData;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class OooOO0 extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c implements j {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Long f112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f113c;

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<ParameterClient> {
        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ParameterClient invoke() {
            return OooOO0.this.n();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f115a;

        public b(h hVar) {
            this.f115a = hVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            this.f115a.onError(str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            Object objD;
            try {
                objD = com.blankj.utilcode.util.j.d(str, TokenData.class);
            } catch (Exception unused) {
                objD = null;
            }
            if (objD == null) {
                this.f115a.onError("data is null");
                return;
            }
            if (!(objD instanceof BaseResponseData)) {
                this.f115a.onSuccess(objD);
                return;
            }
            BaseResponseData baseResponseData = (BaseResponseData) objD;
            if (baseResponseData.getCode() != 1) {
                this.f115a.a(baseResponseData.getCode(), baseResponseData.getMsg());
            } else if (baseResponseData.getData() == null) {
                this.f115a.onError("no data");
            } else {
                this.f115a.onSuccess(objD);
            }
        }
    }

    public static final class c implements h<TokenData> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f116a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ OooOO0 f117b;

        public c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, OooOO0 oooOO0) {
            this.f116a = bVar;
            this.f117b = oooOO0;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            h.a.a(this, i, str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(TokenData tokenData) {
            kotlin.jvm.internal.h.d(tokenData, "data");
            TokenData.TokenItem data = tokenData.getData();
            kotlin.jvm.internal.h.b(data);
            String accessToken = data.getAccessToken();
            if (accessToken != null) {
                this.f117b.h(BuildConfig.FLAVOR, "accessToken", accessToken);
            }
            TokenData.TokenItem data2 = tokenData.getData();
            kotlin.jvm.internal.h.b(data2);
            String validEndTime = data2.getValidEndTime();
            if (validEndTime != null) {
                this.f117b.h(BuildConfig.FLAVOR, "validEndTime", validEndTime);
            }
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar = this.f116a;
            TokenData.TokenItem data3 = tokenData.getData();
            kotlin.jvm.internal.h.b(data3);
            bVar.onSuccess(data3.getAccessToken());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            this.f116a.onError(String.valueOf(str));
        }
    }

    public static final class d implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f118a;

        public d(h hVar) {
            this.f118a = hVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            this.f118a.onError(str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            Object objD;
            try {
                objD = com.blankj.utilcode.util.j.d(str, TokenData.class);
            } catch (Exception unused) {
                objD = null;
            }
            if (objD == null) {
                this.f118a.onError("data is null");
                return;
            }
            if (!(objD instanceof BaseResponseData)) {
                this.f118a.onSuccess(objD);
                return;
            }
            BaseResponseData baseResponseData = (BaseResponseData) objD;
            if (baseResponseData.getCode() != 1) {
                this.f118a.a(baseResponseData.getCode(), baseResponseData.getMsg());
            } else if (baseResponseData.getData() == null) {
                this.f118a.onError("no data");
            } else {
                this.f118a.onSuccess(objD);
            }
        }
    }

    static {
        new a(null);
    }

    public OooOO0() {
        super(null, 1);
        this.f113c = kotlin.d.b(new OooO0o());
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.j
    public void c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) throws JSONException {
        kotlin.jvm.internal.h.d(bVar, "listener");
        c cVar = new c(bVar, this);
        if (m()) {
            k(cVar);
            return;
        }
        Long l = this.f112b;
        kotlin.jvm.internal.h.b(l);
        if (l.longValue() - System.currentTimeMillis() < 12) {
            l(cVar);
        } else {
            bVar.onSuccess(OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "accessToken", null, 4, null));
        }
    }

    public final void k(h<TokenData> hVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("clientId", o().getClientId());
        jSONObject.put("clientSecret", o().getClientSecret());
        jSONObject.put("grantType", o().getGrantType());
        i.f162a.h(g(), "api/system/account/getAccessToken", jSONObject, new b(hVar));
    }

    public final void l(h<TokenData> hVar) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "accessToken", null, 4, null);
        d dVar = new d(hVar);
        if (strD == null) {
            i.f162a.h(g(), "api/system/account/refreshAccessToken", jSONObject, dVar);
        } else {
            jSONObject.put("header", strD);
            i.f162a.l(g(), "api/system/account/refreshAccessToken", jSONObject, dVar);
        }
    }

    public final boolean m() {
        Long lValueOf;
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "validEndTime", null, 4, null);
        if (strD == null) {
            return true;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(strD);
            lValueOf = date == null ? null : Long.valueOf(date.getTime());
        } catch (Exception unused) {
            lValueOf = 0L;
        }
        this.f112b = lValueOf;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Long l = this.f112b;
        kotlin.jvm.internal.h.b(l);
        return jCurrentTimeMillis > l.longValue();
    }

    public final ParameterClient n() {
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.a aVar = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.a.f132a;
        byte[] bArrC = aVar.c();
        Charset charset = kotlin.text.d.f4111a;
        return new ParameterClient(new String(bArrC, charset), new String(aVar.a(), charset), null, 4, null);
    }

    public final ParameterClient o() {
        return (ParameterClient) this.f113c.getValue();
    }
}
