package defpackage;

import J.N;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Process;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.mk0;
import java.util.HashSet;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.password_manager.PasswordSettingsUpdaterDispatcherBridge;
import org.chromium.chrome.browser.password_manager.PasswordSettingsUpdaterReceiverBridge;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class vy2 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ vy2(int i, mk0.b bVar, mk0 mk0Var, GURL gurl) {
        this.e = 2;
        this.g = mk0Var;
        this.h = gurl;
        this.i = bVar;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.e) {
            case 0:
                TabContentManager tabContentManager = (TabContentManager) this.g;
                int i = this.f;
                Bitmap bitmap = (Bitmap) this.h;
                Callback callback = (Callback) this.i;
                b32 b32Var = TabContentManager.j;
                tabContentManager.getClass();
                TraceEvent.F(i, "GetTabThumbnailFromDisk");
                boolean z = tabContentManager.g;
                if (bitmap == null) {
                    long j = tabContentManager.e;
                    if (j == 0 || !z) {
                        return;
                    }
                    N.MGNfqDdn(j, i, tabContentManager.d(), new wy2(callback, 0));
                    return;
                }
                if (TabContentManager.k.c()) {
                    double width = bitmap.getHeight() == 0 ? 0.0d : (((double) bitmap.getWidth()) * 1.0d) / ((double) bitmap.getHeight());
                    HashSet hashSet = tabContentManager.a;
                    if (!hashSet.contains(Integer.valueOf(i)) && Math.abs(width - tabContentManager.d()) >= 0.01d) {
                        oj2.U(3, 4, "GridTabSwitcher.ThumbnailFetchingResult");
                        if (tabContentManager.e == 0) {
                            callback.onResult(bitmap);
                            return;
                        } else {
                            if (z) {
                                hashSet.add(Integer.valueOf(i));
                                N.MGNfqDdn(tabContentManager.e, i, tabContentManager.d(), callback);
                                return;
                            }
                            return;
                        }
                    }
                }
                oj2.U(0, 4, "GridTabSwitcher.ThumbnailFetchingResult");
                callback.onResult(bitmap);
                return;
            case 1:
                CustomTabsConnection customTabsConnection = (CustomTabsConnection) this.g;
                CustomTabsSessionToken customTabsSessionToken = (CustomTabsSessionToken) this.h;
                int i2 = this.f;
                ft1 ft1Var = (ft1) this.i;
                HashSet hashSet2 = CustomTabsConnection.o;
                customTabsConnection.getClass();
                sk skVar = bq.a;
                if (N.M09VlOh_("CCTPostMessageAPI")) {
                    Uri uri = i2 == Process.myUid() ? Uri.EMPTY : null;
                    if (uri == null) {
                        a aVar = customTabsConnection.c;
                        synchronized (aVar) {
                            aVar.g(customTabsSessionToken, 1, ft1Var, true);
                        }
                        return;
                    } else {
                        a aVar2 = customTabsConnection.c;
                        synchronized (aVar2) {
                            a.e eVar = (a.e) aVar2.c.get(customTabsSessionToken);
                            if (eVar == null) {
                                return;
                            }
                            eVar.d.c(uri);
                            return;
                        }
                    }
                }
                return;
            case 2:
                mk0 mk0Var = (mk0) this.g;
                GURL gurl = (GURL) this.h;
                mk0.b bVar = (mk0.b) this.i;
                uc2 uc2Var = mk0Var.d;
                uc2Var.getClass();
                bVar.c(this.f, uc2Var.b(gurl.j(), false));
                return;
            default:
                PasswordSettingsUpdaterDispatcherBridge passwordSettingsUpdaterDispatcherBridge = (PasswordSettingsUpdaterDispatcherBridge) this.g;
                Exception exc = (Exception) this.h;
                z7 z7Var = (z7) this.i;
                PasswordSettingsUpdaterReceiverBridge passwordSettingsUpdaterReceiverBridge = passwordSettingsUpdaterDispatcherBridge.a;
                passwordSettingsUpdaterReceiverBridge.getClass();
                Object obj = ThreadUtils.a;
                z7Var.Z(exc);
                if (passwordSettingsUpdaterReceiverBridge.a == 0) {
                    return;
                }
                if (exc instanceof vx1) {
                    ((vx1) exc).getClass();
                }
                N.Md$cLCnF(passwordSettingsUpdaterReceiverBridge.a, this.f, 0, 0);
                return;
        }
    }

    public /* synthetic */ vy2(Object obj, int i, Bitmap bitmap, Callback callback, int i2) {
        this.e = i2;
        this.g = obj;
        this.f = i;
        this.h = bitmap;
        this.i = callback;
    }

    public /* synthetic */ vy2(CustomTabsConnection customTabsConnection, CustomTabsSessionToken customTabsSessionToken, int i, ft1 ft1Var) {
        this.e = 1;
        this.g = customTabsConnection;
        this.h = customTabsSessionToken;
        this.f = i;
        this.i = ft1Var;
    }
}
