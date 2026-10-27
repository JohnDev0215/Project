package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import androidx.browser.customtabs.CustomTabsSessionToken;
import defpackage.mk0;
import defpackage.r30;
import defpackage.z23;
import java.util.LinkedHashMap;
import java.util.Set;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.gesturenav.SideSlideLayout;
import org.chromium.chrome.browser.omnibox.suggestions.base.c;
import org.chromium.chrome.browser.omnibox.suggestions.base.e;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.signin.a;
import org.chromium.components.signin.d;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i40 implements FaviconHelper.FaviconImageCallback, r30.a, SideSlideLayout.d, mk0.b, z23.d, WebsitePreferenceBridge.StorageInfoClearedCallback, d.a {
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object f;

    public /* synthetic */ i40(Object obj, Object obj2) {
        this.e = obj;
        this.f = obj2;
    }

    @Override // mk0.b
    public final void c(int i, Bitmap bitmap) {
        c cVar = (c) this.e;
        PropertyModel propertyModel = (PropertyModel) this.f;
        if (bitmap != null) {
            c.g(propertyModel, e.a.b(cVar.a, bitmap).a());
        } else {
            cVar.getClass();
        }
    }

    @Override // r30.a
    public final void d(int i, int i2) {
        ((CustomTabsConnection) this.e).p((CustomTabsSessionToken) this.f, i, i2);
    }

    @Override // z23.d
    public final void e(int i, int i2, Boolean bool, String str, boolean z, boolean z2) {
        Context context = (Context) this.e;
        Set set = (Set) this.f;
        LinkedHashMap linkedHashMap = g72.c;
        if (!bk3.f(str) || z) {
            g72 g72VarB = g72.b(i2);
            if (z) {
                g72.g = g72VarB;
            }
            int iF = g72VarB.f();
            if (oj2.D(context) && set.contains(Integer.valueOf(iF))) {
                return;
            }
            g72.f.add(g72VarB);
            set.add(Integer.valueOf(iF));
        }
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        l40 l40Var = (l40) this.e;
        GURL gurl2 = (GURL) this.f;
        Tab tab = l40Var.f.b;
        if (tab == null || !gurl2.equals(tab.getUrl())) {
            return;
        }
        l40Var.b(bitmap);
    }

    @Override // org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge.StorageInfoClearedCallback
    public final void onStorageInfoCleared() {
        int[] iArr = (int[]) this.e;
        f.a aVar = (f.a) this.f;
        int i = iArr[0] - 1;
        iArr[0] = i;
        if (i == 0) {
            aVar.l();
        }
    }

    @Override // org.chromium.components.signin.d.a
    public final Object run() {
        ((a) this.e).a.getClass();
        return Boolean.TRUE;
    }
}
