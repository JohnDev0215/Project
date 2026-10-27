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
public final class a extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c implements f {

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.d.a$a, reason: collision with other inner class name */
    public static final class C0008a {
        public C0008a() {
        }

        public /* synthetic */ C0008a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public static final class b implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f120a;

        public b(h hVar) {
            this.f120a = hVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            this.f120a.onError(str);
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
                this.f120a.onError("data is null");
                return;
            }
            if (!(objD instanceof BaseResponseData)) {
                this.f120a.onSuccess(objD);
                return;
            }
            BaseResponseData baseResponseData = (BaseResponseData) objD;
            if (baseResponseData.getCode() != 1) {
                this.f120a.a(baseResponseData.getCode(), baseResponseData.getMsg());
            } else if (baseResponseData.getData() == null) {
                this.f120a.onError("no data");
            } else {
                this.f120a.onSuccess(objD);
            }
        }
    }

    public static final class c implements h<ActivateData> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f121a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ ActivateParams f122b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ a f123c;

        public c(OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar, ActivateParams activateParams, a aVar) {
            this.f121a = bVar;
            this.f122b = activateParams;
            this.f123c = aVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            h.a.a(this, i, str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(ActivateData activateData) {
            kotlin.jvm.internal.h.d(activateData, "data");
            ActivateData.ActivateItem data = activateData.getData();
            kotlin.jvm.internal.h.b(data);
            String deviceUuid = data.getDeviceUuid();
            if (deviceUuid != null) {
                this.f123c.h(this.f122b.getDeviceId(), "deviceUuid", deviceUuid);
            }
            ActivateData.ActivateItem data2 = activateData.getData();
            kotlin.jvm.internal.h.b(data2);
            String modelUuid = data2.getModelUuid();
            if (modelUuid != null) {
                this.f123c.h(this.f122b.getDeviceId(), "modelUuid", modelUuid);
            }
            ActivateData.ActivateItem data3 = activateData.getData();
            kotlin.jvm.internal.h.b(data3);
            String modelVersion = data3.getModelVersion();
            if (modelVersion != null) {
                this.f123c.h(this.f122b.getDeviceId(), "modelVersion", modelVersion);
            }
            this.f121a.onSuccess(this.f122b.getDeviceId());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            this.f121a.onError(this.f122b.getDeviceId() + " activate error, " + ((Object) str));
        }
    }

    static {
        new C0008a(null);
    }

    public a() {
        super(null, 1);
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.f
    public void b(ActivateParams activateParams, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) throws JSONException {
        kotlin.jvm.internal.h.d(activateParams, "params");
        kotlin.jvm.internal.h.d(bVar, "listener");
        if (OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, activateParams.getDeviceId(), "deviceUuid", null, 4, null) != null) {
            bVar.onSuccess(activateParams.getDeviceId());
            return;
        }
        JSONObject jSONObjectF = f(activateParams);
        if (jSONObjectF == null) {
            bVar.onError("toJsonObject error");
            return;
        }
        c cVar = new c(bVar, activateParams, this);
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "accessToken", null, 4, null);
        b bVar2 = new b(cVar);
        if (strD == null) {
            i.f162a.h(g(), "api/device/manager/activate", jSONObjectF, bVar2);
        } else {
            jSONObjectF.put("header", strD);
            i.f162a.l(g(), "api/device/manager/activate", jSONObjectF, bVar2);
        }
    }
}
