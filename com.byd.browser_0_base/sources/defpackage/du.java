package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.customtabs.IPostMessageService;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.fc;
import defpackage.fp3;
import defpackage.il1;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.browserservices.verification.ChromeOriginVerifier;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.customtabs.b;
import org.chromium.chrome.browser.gesturenav.SideSlideLayout;
import org.chromium.chrome.browser.history.HistoryItemView;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.chrome.browser.webapps.WebApkHandlerDelegate;
import org.chromium.chrome.browser.webapps.WebApkUpdateManager;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.commerce.core.ShoppingService;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class du implements SideSlideLayout.e, FaviconHelper.FaviconImageCallback, LargeIconBridge.LargeIconCallback, ShoppingService.MerchantInfoCallback, fp3.a, WebApkUpdateManager.WebApkUpdateCallback, fc.a {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ du(Object obj, Object obj2) {
        this.e = obj;
        this.f = obj2;
    }

    @Override // org.chromium.components.commerce.core.ShoppingService.MerchantInfoCallback
    public final void a(ShoppingService.MerchantInfo merchantInfo) {
        ag1 ag1Var = (ag1) this.e;
        Callback callback = (Callback) this.f;
        ag1Var.getClass();
        if (!((merchantInfo == null || merchantInfo.c == null || merchantInfo.f || (merchantInfo.a <= 0.0f && !merchantInfo.d)) ? false : true)) {
            merchantInfo = null;
        }
        callback.onResult(merchantInfo);
    }

    @Override // fc.a
    public final void b(boolean z) {
        fk1 fk1Var = (fk1) this.e;
        fc.a aVar = (fc.a) this.f;
        fk1Var.getClass();
        PostTask.d(rh3.a, new ps3(fk1Var, 1, aVar, z));
    }

    @Override // fp3.a
    public final void c(String str, boolean z) {
        ls3 ls3VarC;
        long j;
        long j2;
        boolean z2;
        String str2;
        String str3;
        WebApkHandlerDelegate webApkHandlerDelegate = (WebApkHandlerDelegate) this.e;
        PackageInfo packageInfo = (PackageInfo) this.f;
        if (webApkHandlerDelegate.a == 0 || (ls3VarC = ls3.c(hp3.b(new Intent(), packageInfo.packageName, "", 0, false, false, null, null))) == null) {
            return;
        }
        ds3 ds3VarC = WebappRegistry.b.a.c(ls3VarC.i());
        String str4 = "Not updatable";
        if (ds3VarC != null) {
            SharedPreferences sharedPreferences = ds3VarC.b;
            long j3 = sharedPreferences.getLong("last_check_web_manifest_update_time", 0L);
            long j4 = sharedPreferences.getLong("last_update_request_complete_time", 0L);
            boolean z3 = sharedPreferences.getBoolean("relax_updates", false);
            String string = sharedPreferences.getString("webapk_package_name", null);
            if (!((string == null || string.startsWith("org.chromium.webapk")) ? false : true)) {
                if (sharedPreferences.getBoolean("update_scheduled", false)) {
                    str3 = "Scheduled";
                } else if (sharedPreferences.getBoolean("should_force_update", false)) {
                    str3 = "Pending";
                } else {
                    str3 = (sharedPreferences.getLong("last_update_request_complete_time", 0L) > 0L ? 1 : (sharedPreferences.getLong("last_update_request_complete_time", 0L) == 0L ? 0 : -1)) == 0 ? true : sharedPreferences.getBoolean("did_last_update_request_succeed", false) ? "Succeeded" : "Failed";
                }
                str4 = str3;
            }
            str2 = str4;
            j = j3;
            j2 = j4;
            z2 = z3;
        } else {
            j = 0;
            j2 = 0;
            z2 = false;
            str2 = "Not updatable";
        }
        N.MXibFIFs(webApkHandlerDelegate.a, ls3VarC.p(), ls3VarC.r(), ls3VarC.u(), ls3VarC.i(), ls3VarC.e().d, packageInfo.versionCode, ls3VarC.f().b, ls3VarC.q(), ls3VarC.o(), ls3VarC.n(), ls3VarC.m(), ls3VarC.d(), ls3VarC.f().h, ls3VarC.t(), ls3VarC.b(), j, j2, z2, str, z, str2);
    }

    public final void d(a.e eVar) {
        a aVar = (a) this.e;
        CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) this.f;
        aVar.getClass();
        a.e(eVar);
        aVar.c.remove(customTabsSessionToken);
        a32 a32Var = eVar.e;
        if (a32Var != null) {
            Context context = xz.a;
            IPostMessageService iPostMessageService = a32Var.g;
            if (iPostMessageService != null) {
                if (iPostMessageService != null) {
                    context.unbindService(a32Var);
                    a32Var.g = null;
                }
            }
        }
        ChromeOriginVerifier chromeOriginVerifier = eVar.g;
        if (chromeOriginVerifier != null && chromeOriginVerifier.d.isEmpty()) {
            long j = chromeOriginVerifier.e;
            if (j != 0) {
                N.MCZNrhuL(j, chromeOriginVerifier);
                chromeOriginVerifier.e = 0L;
            }
        }
        a.InterfaceC0076a interfaceC0076a = eVar.c;
        if (interfaceC0076a != null) {
            CustomTabsConnection customTabsConnection = ((b) interfaceC0076a).a;
            customTabsConnection.getClass();
            Object obj = ThreadUtils.a;
            customTabsConnection.a.getClass();
            Callback<CustomTabsSessionToken> callback = customTabsConnection.g;
            if (callback != null) {
                callback.onResult(customTabsSessionToken);
            }
            w40 w40VarF = ((s50) pp.d()).f();
            if (w40VarF.c) {
                w40VarF.b.get().a.remove(customTabsSessionToken);
            }
        }
        aVar.d.delete(eVar.a);
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        Object bitmapDrawable;
        il1 il1Var = (il1) this.e;
        GURL gurl2 = (GURL) this.f;
        ya1 ya1Var = il1Var.e;
        if (ya1Var.size() == 0) {
            return;
        }
        for (int i = 0; i < il1Var.l.b(); i++) {
            if (gurl2.equals(il1Var.l.a(i).b)) {
                if (bitmap != null) {
                    bitmapDrawable = new BitmapDrawable(bitmap);
                } else if (bk3.l(gurl2)) {
                    bitmapDrawable = il1Var.k.h() ? il1Var.h : il1Var.g;
                } else {
                    uc2 uc2Var = il1Var.c;
                    uc2Var.getClass();
                    bitmapDrawable = new BitmapDrawable(uc2Var.b(gurl2.j(), false));
                }
                ya1Var.get(i).b.p(il1.b.a, bitmapDrawable);
            }
        }
    }

    @Override // org.chromium.components.favicon.LargeIconBridge.LargeIconCallback
    public final void onLargeIconAvailable(Bitmap bitmap, int i, boolean z, int i2) {
        HistoryItemView historyItemView = (HistoryItemView) this.e;
        ev0 ev0Var = (ev0) this.f;
        int i3 = HistoryItemView.E;
        if (ev0Var != historyItemView.getItem()) {
            return;
        }
        historyItemView.setStartIconDrawable(org.chromium.chrome.browser.ui.favicon.a.f(bitmap, historyItemView.getItem().c, i, historyItemView.y, historyItemView.getResources(), historyItemView.B));
    }

    @Override // org.chromium.chrome.browser.webapps.WebApkUpdateManager.WebApkUpdateCallback
    public final void onResultFromNative(int i, boolean z) {
        ds3 ds3Var = (ds3) this.e;
        Runnable runnable = (Runnable) this.f;
        ds3Var.b(false);
        ds3Var.b.edit().putBoolean("update_scheduled", false).apply();
        WebApkUpdateManager.g(ds3Var, i, z);
        ds3Var.a();
        runnable.run();
    }
}
