package b.b.i;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.UserHandle;
import b.b.d.b;
import b.b.f.e;
import b.b.g.p.d;
import b.b.g.p.h;
import b.b.g.q.f.b.g;
import b.b.g.q.f.b.w;
import b.b.m.f.d.d;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.R;
import com.byd.carsettings.model.dilink.BydAppInfo;
import com.byd.carsettings.system.model.BydLanguage;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.systemsettings.SystemSettingsApi;
import com.byd.systemsettings.hotspot.HotspotManager;
import com.byd.systemsettings.wifi2.BydWifiManager;
import java.util.List;
import java.util.Locale;

/* JADX INFO: compiled from: SystemDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b.b.i.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f1925a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static SystemSettingsApi f1926b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f1927c = "com.byd.carsettings.wifi.dialog.state.change";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f1928d = "com.byd.carsettings.hotspot.dialog.state.change";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static String f1929e = "com.byd.carsettings.connect_device.dialog.state.change";

    /* JADX INFO: compiled from: SystemDialog.java */
    public static class a implements b.b.g.p.j.b {
        @Override // b.b.g.p.j.b
        public void c() {
            b.b.m.c.a.g("SystemDialog", "---restoreFactorySettings---onStarted---do restore car devices.");
        }

        @Override // b.b.g.p.j.b
        public void d() {
            b.b.m.c.a.g("SystemDialog", "---restoreFactorySettings---onCompleted---do restore factory.");
            h.t(BydApp.getContext());
        }
    }

    /* JADX INFO: compiled from: SystemDialog.java */
    public static class b implements b.b.m.b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ d.a f1930a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ w f1931b;

        public b(d.a aVar, w wVar) {
            this.f1930a = aVar;
            this.f1931b = wVar;
        }

        @Override // b.b.m.b.a
        public void onBtnClicked(Class<?> cls, PagerBtnType pagerBtnType, Object obj, Object obj2) {
            d.a aVar;
            if (PagerBtnType.BOTTOM_LEFT == pagerBtnType && (aVar = this.f1930a) != null) {
                aVar.onOKClicked();
            }
            b.b.i.c.c(this.f1931b.getClass());
        }
    }

    /* JADX INFO: compiled from: SystemDialog.java */
    public static class c implements b.b.m.e.d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b.b.m.e.a f1932a;

        public c(b.b.m.e.a aVar) {
            this.f1932a = aVar;
        }

        @Override // b.b.m.e.d
        public void onBackPress() {
            b.b.i.c.c(this.f1932a.getClass());
        }
    }

    public static b.b.m.f.d.b A(Context context, String str, String str2, String str3, String str4, d.a aVar) {
        if (context == null) {
            return null;
        }
        b.b.m.c.a.a("SystemDialog", "---showBtnLeftHighlight---");
        g gVar = new g(context);
        gVar.setTitleText(str);
        gVar.e(str2);
        gVar.c(str3);
        gVar.b(str4);
        gVar.setOnOKClickedListener(aVar);
        b.b.i.c.m(context, gVar, false);
        return b.b.i.c.g(g.class);
    }

    public static synchronized void B(Context context) {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.showCpaaSettings();
        }
    }

    public static e C(Context context, b.b.m.b.a aVar) {
        if (!b.b.i.c.b(context, e.class)) {
            b.b.m.c.a.c("SystemDialog", "showChangeCpaaDeviceDialog", "checkDialogLevel return!!");
            return null;
        }
        b.b.m.c.a.a("SystemDialog", "-----showChangeCpaaDeviceDialog----");
        e eVar = new e(context);
        b.b.i.c.l(context, eVar);
        eVar.setBtnClickedCallback(aVar);
        x(eVar);
        return eVar;
    }

    public static synchronized void D(Context context) {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.showHotspotSettings();
        }
    }

    public static synchronized void E(Context context) {
    }

    public static synchronized void F(Context context) {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.showScreensaver();
        }
    }

    public static b.b.m.f.d.b G(Context context, BydAppInfo bydAppInfo, d.a aVar) {
        if (context == null) {
            return null;
        }
        b.b.m.c.a.a("SystemDialog", "---showUninstall---");
        w wVar = new w(context);
        wVar.b(bydAppInfo.getIcon());
        wVar.c(bydAppInfo.getAppName());
        wVar.setBtnClickedCallback(new b(aVar, wVar));
        b.b.i.c.l(context, wVar);
        return b.b.i.c.g(w.class);
    }

    public static synchronized void H(Context context) {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.showWifiSettings();
        }
    }

    public static void n() {
    }

    public static void o() {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.dismissBtSettings();
        }
    }

    public static void p() {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.dismissHotspotSettings();
        }
    }

    public static void q() {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.finishScreensaver();
        }
    }

    public static void r() {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.dismissWifiSettings();
        }
    }

    public static void s(Context context) {
        f1926b = SystemSettingsApi.getInstance(context);
    }

    public static synchronized void t(Context context) {
        b.b.m.c.a.g("SystemDialog", "---restoreFactorySettings---doingRestoreFactory: " + f1925a);
        if (f1925a) {
            return;
        }
        f1925a = true;
        b.b.g.q.d.u.b.p(context, context.getString(R.string.msg_recovering_recovery));
        b.b.g.p.d dVarA = d.g.a();
        dVarA.W(context);
        b.b.g.r.e.d(context);
        BydWifiManager.Singleton.get().init(context);
        b.f.a().s(context);
        HotspotManager.Singleton.get().init(context);
        BydLanguage bydLanguage = null;
        Locale localeV = dVarA.v(context);
        b.b.m.c.a.d("SystemDialog", "---restoreFactorySettings---locale: " + localeV.toString());
        b.b.g.p.m.a.g(context.getApplicationContext());
        List<BydLanguage> listE = b.b.g.p.m.a.e();
        b.b.m.c.a.d("SystemDialog", "---restoreFactorySettings---list: " + listE);
        for (BydLanguage bydLanguage2 : listE) {
            if (bydLanguage2.getLocale().toString().equals(localeV.toString())) {
                bydLanguage = bydLanguage2;
            }
        }
        b.b.m.c.a.d("SystemDialog", "---restoreFactorySettings---target: " + bydLanguage);
        dVarA.u0(context, bydLanguage, new a());
    }

    @SuppressLint({"MissingPermission"})
    public static void u(Context context, boolean z) {
        Intent intent = new Intent(f1929e);
        intent.putExtra("show", z);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    public static void v(Context context, boolean z) {
        Intent intent = new Intent(f1928d);
        intent.putExtra("show", z);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    @SuppressLint({"MissingPermission"})
    public static void w(Context context, boolean z) {
        Intent intent = new Intent(f1927c);
        intent.putExtra("show", z);
        Parcel parcelObtain = Parcel.obtain();
        context.sendBroadcastAsUser(intent, UserHandle.readFromParcel(parcelObtain));
        parcelObtain.recycle();
    }

    public static void x(b.b.m.e.a aVar) {
        if (aVar == null) {
            return;
        }
        aVar.setOnBackPressCallback(new c(aVar));
    }

    public static synchronized void y(Context context) {
        SystemSettingsApi systemSettingsApi = f1926b;
        if (systemSettingsApi != null) {
            systemSettingsApi.showBtSettings();
        }
    }

    public static b.b.m.f.d.b z(Context context, String str, String str2, String str3, d.a aVar) {
        if (context == null) {
            return null;
        }
        b.b.m.c.a.a("SystemDialog", "---showBtnLeftHighlight---");
        g gVar = new g(context);
        gVar.setTitleText(str);
        gVar.e(str2);
        gVar.c(str3);
        gVar.setOnOKClickedListener(aVar);
        b.b.i.c.m(context, gVar, false);
        return b.b.i.c.g(g.class);
    }
}
