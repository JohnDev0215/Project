package OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.d;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.f;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.i;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.j;
import com.loostone.libserver.version2.bean.ActivateData;
import com.loostone.libserver.version2.bean.ActivateParams;
import com.loostone.libserver.version2.bean.BaseResponseData;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class b extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c implements f {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.d.b$b, reason: collision with other inner class name */
    public static final class C0009b implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f124a;

        public C0009b(h hVar) {
            this.f124a = hVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            this.f124a.onError(str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            Object objD;
            try {
                objD = j.d(str, ActivateData.class);
            } catch (Exception unused) {
                objD = null;
            }
            if (objD == null) {
                this.f124a.onError("data is null");
                return;
            }
            if (!(objD instanceof BaseResponseData)) {
                this.f124a.onSuccess(objD);
                return;
            }
            BaseResponseData baseResponseData = (BaseResponseData) objD;
            if (baseResponseData.getCode() != 1) {
                this.f124a.a(baseResponseData.getCode(), baseResponseData.getMsg());
            } else if (baseResponseData.getData() == null) {
                this.f124a.onError("no data");
            } else {
                this.f124a.onSuccess(objD);
            }
        }
    }

    public static final class c implements h<ActivateData> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f125a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ActivateParams f126b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ b f127c;

        public c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, ActivateParams activateParams, b bVar2) {
            this.f125a = bVar;
            this.f126b = activateParams;
            this.f127c = bVar2;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            if (i == 0) {
                this.f125a.onError(null);
            } else {
                onError(str);
            }
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(ActivateData activateData) {
            kotlin.jvm.internal.h.d(activateData, "data");
            ActivateData.ActivateItem data = activateData.getData();
            kotlin.jvm.internal.h.b(data);
            String deviceUuid = data.getDeviceUuid();
            if (deviceUuid != null) {
                this.f127c.h(this.f126b.getDeviceId(), "deviceUuid", deviceUuid);
            }
            ActivateData.ActivateItem data2 = activateData.getData();
            kotlin.jvm.internal.h.b(data2);
            String modelUuid = data2.getModelUuid();
            if (modelUuid != null) {
                this.f127c.h(this.f126b.getDeviceId(), "modelUuid", modelUuid);
            }
            ActivateData.ActivateItem data3 = activateData.getData();
            kotlin.jvm.internal.h.b(data3);
            String modelVersion = data3.getModelVersion();
            if (modelVersion != null) {
                this.f127c.h(this.f126b.getDeviceId(), "modelVersion", modelVersion);
            }
            this.f125a.onSuccess(this.f126b.getDeviceId());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            this.f125a.onError(this.f126b.getDeviceId() + " activate error, " + ((Object) str));
        }
    }

    static {
        new a(null);
    }

    public b() {
        super(null, 1);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.f
    public void b(ActivateParams activateParams, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) throws JSONException {
        kotlin.jvm.internal.h.d(activateParams, "params");
        kotlin.jvm.internal.h.d(bVar, "listener");
        JSONObject jSONObjectF = f(activateParams);
        if (jSONObjectF == null) {
            bVar.onError("toJsonObject error");
            return;
        }
        c cVar = new c(bVar, activateParams, this);
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "accessToken", null, 4, null);
        C0009b c0009b = new C0009b(cVar);
        if (strD == null) {
            i.f162a.h(g(), "api/device/manager/activate", jSONObjectF, c0009b);
        } else {
            jSONObjectF.put("header", strD);
            i.f162a.l(g(), "api/device/manager/activate", jSONObjectF, c0009b);
        }
    }
}
