package org.chromium.components.webapps;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import defpackage.bi1;
import defpackage.n1;
import defpackage.w3;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.components.webapps.AppBannerManager;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes3.dex */
public class AddToHomescreenCoordinator {
    public final Context a;
    public final bi1 b;
    public final WindowAndroid c;
    public final WebContents d;

    public AddToHomescreenCoordinator(WebContents webContents, Context context, WindowAndroid windowAndroid, bi1 bi1Var) {
        this.a = context;
        this.c = windowAndroid;
        this.b = bi1Var;
        this.d = webContents;
    }

    public static void b(Context context, WindowAndroid windowAndroid, bi1 bi1Var, WebContents webContents, Bundle bundle) {
        int i = bundle.getInt("AppMenuTitleShown");
        AddToHomescreenCoordinator addToHomescreenCoordinator = new AddToHomescreenCoordinator(webContents, context, windowAndroid, bi1Var);
        if (webContents == null || webContents.s().k()) {
            return;
        }
        long j = addToHomescreenCoordinator.a().e;
        if (j == 0) {
            return;
        }
        N.MH3_t6hY(j, webContents, i);
    }

    @CalledByNative
    public static long initMvcAndReturnMediator(WebContents webContents) {
        bi1 bi1VarN;
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null || (bi1VarN = windowAndroidV.n()) == null) {
            return 0L;
        }
        return new AddToHomescreenCoordinator(webContents, windowAndroidV.i.get(), windowAndroidV, bi1VarN).a().e;
    }

    public final AddToHomescreenMediator a() {
        AppBannerManager appBannerManager;
        PropertyModel propertyModel = new PropertyModel(PropertyModel.e(w3.j));
        AddToHomescreenMediator addToHomescreenMediator = new AddToHomescreenMediator(propertyModel, this.c);
        AppBannerManager.a aVar = AppBannerManager.a;
        WebContents webContents = this.d;
        if (webContents != null) {
            Object obj = ThreadUtils.a;
            appBannerManager = (AppBannerManager) N.MbHcYdCX(webContents);
        } else {
            appBannerManager = null;
        }
        j.b(propertyModel, new c(this.a, this.b, (appBannerManager == null || !(TextUtils.equals("", N.MvBgz9uo(webContents)) ^ true)) ? AppBannerManager.b : AppBannerManager.a, addToHomescreenMediator), new n1(6));
        return addToHomescreenMediator;
    }
}
