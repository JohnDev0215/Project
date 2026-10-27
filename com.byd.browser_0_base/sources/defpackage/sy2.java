package defpackage;

import android.graphics.Bitmap;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.share.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class sy2 implements Callback {
    public final /* synthetic */ int e = 1;
    public final /* synthetic */ int f;
    public final /* synthetic */ boolean g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;

    public /* synthetic */ sy2(fk2 fk2Var, int i, Tab tab, boolean z) {
        this.h = fk2Var;
        this.f = i;
        this.i = tab;
        this.g = z;
    }

    @Override // org.chromium.base.Callback
    public final void onResult(Object obj) {
        int i = this.e;
        boolean z = false;
        Object obj2 = this.i;
        Object obj3 = this.h;
        switch (i) {
            case 0:
                TabContentManager tabContentManager = (TabContentManager) obj3;
                Callback callback = (Callback) obj2;
                Bitmap bitmap = (Bitmap) obj;
                b32 b32Var = TabContentManager.j;
                if (bitmap != null) {
                    tabContentManager.getClass();
                    callback.onResult(bitmap);
                }
                Tab tabById = tabContentManager.getTabById(this.f);
                if (tabById != null) {
                    tabContentManager.b(tabById, this.g, new ty2(callback, 0));
                    break;
                }
                break;
            default:
                fk2 fk2Var = (fk2) obj3;
                int i2 = this.f;
                Tab tab = (Tab) obj2;
                boolean z2 = this.g;
                a aVar = (a) obj;
                fk2Var.getClass();
                if (aVar == null) {
                    WindowAndroid windowAndroidD = tab.D();
                    WebContents webContentsC = tab.c();
                    if (webContentsC != null && webContentsC.y() != null && !tab.getUrl().k() && !tab.m() && !hd2.z1(tab)) {
                        z = true;
                    }
                    if (!z) {
                        fk2Var.e(windowAndroidD, tab.c(), tab.getTitle(), tab.getUrl(), GURL.emptyGURL(), i2, z2);
                    } else {
                        WebContents webContentsC2 = tab.c();
                        String title = tab.getTitle();
                        webContentsC2.y().h(new ek2(fk2Var, tab.getUrl(), tab, windowAndroidD, webContentsC2, title, i2, z2));
                    }
                } else {
                    fk2Var.c(aVar, new jr(false, false, true, null, null, false, false, null, 0), i2);
                }
                break;
        }
    }

    public /* synthetic */ sy2(TabContentManager tabContentManager, Callback callback, int i, boolean z) {
        this.h = tabContentManager;
        this.i = callback;
        this.f = i;
        this.g = z;
    }
}
