package OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO;

import OooO00o.OooO00o.OooO0O0.OooO0Oo.OooO00o.OooO00o;
import OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.i;
import com.blankj.utilcode.util.r;
import com.loostone.libserver.version2.bean.UpgradeData;
import com.loostone.libserver.version2.bean.UpgradeParams;
import java.io.File;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.text.o;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0OO extends e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final OooO00o f100b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b f101c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final kotlin.a f102d;

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0OO.OooO.OooO0OO$OooO0OO, reason: collision with other inner class name */
    public static final class C0006OooO0OO extends Lambda implements kotlin.jvm.b.a<String> {
        public C0006OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final String invoke() {
            return OooO0OO.this.u();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b implements h<UpgradeData> {
        public b() {
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void a(int i, String str) {
            h.a.a(this, i, str);
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(UpgradeData upgradeData) {
            kotlin.jvm.internal.h.d(upgradeData, "data");
            OooO0OO oooO0OO = OooO0OO.this;
            UpgradeData.UpgradeList data = upgradeData.getData();
            kotlin.jvm.internal.h.b(data);
            if (oooO0OO.q(data.getVersions().get(0).getFileMd5())) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("FirmwareUpgrade", "already download");
                OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVarW = OooO0OO.this.w();
                if (bVarW == null) {
                    return;
                }
                bVarW.onSuccess(OooO0OO.this.v());
                return;
            }
            OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
            UpgradeData.UpgradeList data2 = upgradeData.getData();
            kotlin.jvm.internal.h.b(data2);
            String fileUrl = data2.getVersions().get(0).getFileUrl();
            kotlin.jvm.internal.h.b(fileUrl);
            bVar.e("FirmwareUpgrade", kotlin.jvm.internal.h.i("downloading firmware... ", fileUrl));
            OooO0OO oooO0OO2 = OooO0OO.this;
            UpgradeData.UpgradeList data3 = upgradeData.getData();
            kotlin.jvm.internal.h.b(data3);
            String fileUrl2 = data3.getVersions().get(0).getFileUrl();
            kotlin.jvm.internal.h.b(fileUrl2);
            oooO0OO2.l(fileUrl2, OooO0OO.this.v(), OooO0OO.this.w());
        }

        @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.h
        public void onError(String str) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVarW = OooO0OO.this.w();
            if (bVarW == null) {
                return;
            }
            bVarW.onError(kotlin.jvm.internal.h.i("firmware ", str));
        }
    }

    static {
        new a(null);
    }

    public OooO0OO(OooO00o oooO00o, OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar) {
        kotlin.jvm.internal.h.d(oooO00o, "cachedDongleDevice");
        this.f100b = oooO00o;
        this.f101c = bVar;
        this.f102d = kotlin.d.b(new C0006OooO0OO());
    }

    @Override // OooO00o.OooO00o.OooO0Oo.OooO0Oo.d.k
    public void a() throws JSONException {
        if (r.b("PuremicConfigOther").d("download_firmware_switch", 0) != 1) {
            OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b bVar = this.f101c;
            if (bVar == null) {
                return;
            }
            bVar.onError("download firmware switch off");
            return;
        }
        UpgradeParams upgradeParams = new UpgradeParams();
        upgradeParams.setUpgradeType("firmware");
        String strA = t().a();
        if (strA == null) {
            strA = BuildConfig.FLAVOR;
        }
        upgradeParams.setDeviceId(strA);
        upgradeParams.setVersion(r(t().k()));
        upgradeParams.setFileType(2);
        j(upgradeParams, new b());
    }

    public final boolean q(String str) {
        File file = new File(v());
        if (file.exists() && file.isFile()) {
            return o.g(str, i.l(file), true);
        }
        return false;
    }

    public final int r(String str) {
        if (str == null) {
            return -1;
        }
        try {
            return Integer.parseInt(str, 16);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public final OooO00o t() {
        return this.f100b;
    }

    public final String u() {
        String absolutePath = OooO00o.OooO00o.OooO0O0.a.f39a.a().getFilesDir().getAbsolutePath();
        File file = new File(absolutePath);
        if (!file.exists()) {
            OooO00o.OooO00o.OooO0O0.b.f41a.e("FirmwareUpgrade", kotlin.jvm.internal.h.i("mkdirs firmwareDir, result = ", Boolean.valueOf(file.mkdirs())));
        }
        return absolutePath + ((Object) File.separator) + "loostone_dongle_firmware.zip";
    }

    public final String v() {
        return (String) this.f102d.getValue();
    }

    public final OooO00o.OooO00o.OooO0Oo.OooO0OO.a.b w() {
        return this.f101c;
    }
}
