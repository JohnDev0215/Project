package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO;

import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.k;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.f.i;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.j;
import com.loostone.libserver.version2.bean.BaseResponseData;
import com.loostone.libserver.version2.bean.UpgradeData;
import com.loostone.libserver.version2.bean.UpgradeParams;
import java.util.List;
import kotlin.jvm.internal.f;
import kotlin.text.o;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public abstract class e extends OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c implements k {

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b implements OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h f108a;

        public b(h hVar) {
            this.f108a = hVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onError(String str) {
            this.f108a.onError(str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b
        public void onSuccess(String str) {
            Object objD;
            try {
                objD = j.d(str, UpgradeData.class);
            } catch (Exception unused) {
                objD = null;
            }
            if (objD == null) {
                this.f108a.onError("data is null");
                return;
            }
            if (!(objD instanceof BaseResponseData)) {
                this.f108a.onSuccess(objD);
                return;
            }
            BaseResponseData baseResponseData = (BaseResponseData) objD;
            if (baseResponseData.getCode() != 1) {
                this.f108a.a(baseResponseData.getCode(), baseResponseData.getMsg());
            } else if (baseResponseData.getData() == null) {
                this.f108a.onError("no data");
            } else {
                this.f108a.onSuccess(objD);
            }
        }
    }

    public static final class c implements h<UpgradeData> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h<UpgradeData> f109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ UpgradeParams f110b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ e f111c;

        public c(h<UpgradeData> hVar, UpgradeParams upgradeParams, e eVar) {
            this.f109a = hVar;
            this.f110b = upgradeParams;
            this.f111c = eVar;
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            h.a.a(this, i, str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(UpgradeData upgradeData) {
            kotlin.jvm.internal.h.d(upgradeData, "data");
            UpgradeData.UpgradeList data = upgradeData.getData();
            kotlin.jvm.internal.h.b(data);
            List<UpgradeData.UpgradeItem> versions = data.getVersions();
            boolean z = true;
            if (versions.size() < 1) {
                this.f109a.onError("versions is null");
                return;
            }
            String fileUrl = versions.get(0).getFileUrl();
            String strJ = fileUrl == null ? null : o.j(fileUrl, "\\", BuildConfig.FLAVOR, false, 4, null);
            if (strJ != null && strJ.length() != 0) {
                z = false;
            }
            if (z) {
                this.f109a.onError("fileUrl is null");
                return;
            }
            int versionCode = versions.get(0).getVersionCode();
            if (versionCode > this.f110b.getVersion()) {
                this.f111c.h(this.f110b.getUpgradeType(), "net_config_version", String.valueOf(versionCode));
                h<UpgradeData> hVar = this.f109a;
                UpgradeData.UpgradeList data2 = upgradeData.getData();
                kotlin.jvm.internal.h.b(data2);
                data2.getVersions().get(0).setFileUrl(strJ);
                hVar.onSuccess(upgradeData);
                return;
            }
            this.f109a.onError("netVersion: " + versionCode + ", local version: " + this.f110b.getVersion());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            this.f109a.onError("upgrade " + ((Object) str) + ", local version: " + this.f110b.getVersion());
        }
    }

    static {
        new a(null);
    }

    public e() {
        super(null, 1);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void j(UpgradeParams upgradeParams, h<UpgradeData> hVar) throws JSONException {
        kotlin.jvm.internal.h.d(upgradeParams, "params");
        kotlin.jvm.internal.h.d(hVar, "listener");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("packageName", g().getPackageName());
        jSONObject.put("versionCode", upgradeParams.getVersion());
        jSONObject.put("fileType", upgradeParams.getFileType());
        jSONArray.put(jSONObject);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("deviceUuid", OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, upgradeParams.getDeviceId(), "deviceUuid", null, 4, null));
        jSONObject2.put("modelUuid", OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, upgradeParams.getDeviceId(), "modelUuid", null, 4, null));
        jSONObject2.put("modelVersion", OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, upgradeParams.getDeviceId(), "modelVersion", null, 4, null));
        jSONObject2.put("packageVersions", jSONArray);
        c cVar = new c(hVar, upgradeParams, this);
        String strD = OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, BuildConfig.FLAVOR, "accessToken", null, 4, null);
        b bVar = new b(cVar);
        if (strD == null) {
            i.f162a.h(g(), "api/device/manager/checkDeviceNewVersion", jSONObject2, bVar);
        } else {
            jSONObject2.put("header", strD);
            i.f162a.l(g(), "api/device/manager/checkDeviceNewVersion", jSONObject2, bVar);
        }
    }

    public final void k(String str, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(str, "url");
        i.f162a.f(g(), str, bVar);
    }

    public final void l(String str, String str2, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(str, "url");
        kotlin.jvm.internal.h.d(str2, "downloadPath");
        i.f162a.g(g(), str, str2, bVar);
    }

    public final boolean m(String str, int i) {
        if (str != null) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            Long lValueOf = Long.valueOf(str);
            kotlin.jvm.internal.h.c(lValueOf, "valueOf(time)");
            if (jCurrentTimeMillis - lValueOf.longValue() <= ((long) i) * 3600000) {
                return false;
            }
        }
        return true;
    }

    public final void n(UpgradeParams upgradeParams, h<UpgradeData> hVar) throws JSONException {
        kotlin.jvm.internal.h.d(upgradeParams, "params");
        kotlin.jvm.internal.h.d(hVar, "listener");
        if (!m(OooO00o.OooO00o.OooO0Oo.OooO0Oo.a.c.d(this, upgradeParams.getUpgradeType(), "UpgradeTime", null, 4, null), upgradeParams.getPeriod())) {
            hVar.onError("time not need update");
        } else {
            h(upgradeParams.getUpgradeType(), "UpgradeTime", String.valueOf(System.currentTimeMillis()));
            j(upgradeParams, hVar);
        }
    }
}
