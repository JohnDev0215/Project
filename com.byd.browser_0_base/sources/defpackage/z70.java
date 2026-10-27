package defpackage;

import J.N;
import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.browser.customtabs.CustomTabsSessionToken;
import androidx.fragment.app.j;
import androidx.mediarouter.media.o;
import defpackage.am;
import defpackage.fp3;
import defpackage.m60;
import defpackage.r30;
import defpackage.w20;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.ShortcutHelper;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.chrome.browser.ui.favicon.a;
import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.site_settings.f;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class z70 implements am.a, o.b, WebappRegistry.a, fp3.a, w20.c, r30.a, m60.d, FaviconHelper.FaviconImageCallback, RadioButtonWithDescription.a, f.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ z70(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // org.chromium.chrome.browser.webapps.WebappRegistry.a
    public final void a(ds3 ds3Var) {
        Intent intent = (Intent) this.f;
        String str = (String) this.g;
        int i = ShortcutHelper.a.v;
        ms3 ms3VarA = n3.A(intent);
        if (ms3VarA != null) {
            ds3Var.c(ms3VarA);
        }
        Bitmap bitmap = (Bitmap) ShortcutHelper.a.remove(str);
        if (bitmap != null) {
            ShortcutHelper.storeWebappSplashImage(str, bitmap);
        }
    }

    @Override // fp3.a
    public final void c(String str, boolean z) {
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 3:
                Callback callback = (Callback) obj2;
                String str2 = (String) obj;
                if (!z) {
                    str2 = null;
                }
                callback.onResult(str2);
                break;
            default:
                s62 s62Var = (s62) obj2;
                String str3 = (String) obj;
                if (!z) {
                    str3 = "";
                }
                s62Var.b(str3);
                break;
        }
    }

    @Override // r30.a
    public final void d(int i, int i2) {
        ((CustomTabsConnection) this.f).p((CustomTabsSessionToken) this.g, i, i2);
    }

    @Override // org.chromium.components.browser_ui.widget.RadioButtonWithDescription.a
    public final void j(RadioButtonWithDescription radioButtonWithDescription) {
        RadioButtonWithDescription radioButtonWithDescription2 = (RadioButtonWithDescription) this.f;
        PropertyModel propertyModel = (PropertyModel) this.g;
        if (radioButtonWithDescription2.b()) {
            ((Callback) propertyModel.j(h51.e)).onResult((String) propertyModel.j(h51.a));
        }
    }

    @Override // org.chromium.components.browser_ui.site_settings.f.a
    public final void l() {
        AllSiteSettings allSiteSettings = (AllSiteSettings) this.f;
        int[] iArr = (int[]) this.g;
        int i = AllSiteSettings.q0;
        allSiteSettings.getClass();
        int i2 = iArr[0] - 1;
        iArr[0] = i2;
        if (i2 <= 0) {
            allSiteSettings.N0();
        }
    }

    @Override // am.a
    public final void onCancel() {
        Animator animator = (Animator) this.f;
        i21.e((j.b) this.g, "$operation");
        animator.end();
    }

    @Override // org.chromium.chrome.browser.ui.favicon.FaviconHelper.FaviconImageCallback
    public final void onFaviconAvailable(Bitmap bitmap, GURL gurl) {
        Object objA;
        qf1 qf1Var = (qf1) this.f;
        GURL gurl2 = (GURL) this.g;
        if (bitmap != null) {
            objA = a.c(qf1Var.a.getResources(), bitmap);
        } else {
            qf1Var.getClass();
            boolean z = true;
            if (!N.MnXKAgH9(gurl2.j(), true) && !N.Mn0fHwI$(gurl2.j())) {
                z = false;
            }
            Context context = qf1Var.a;
            objA = z ? AppCompatResources.a(context, R$drawable.ic_logo_googleg_24dp) : uh3.e(context, R$drawable.ic_globe_24dp, R$color.default_icon_color_tint_list);
        }
        qf1Var.h.p(wi.j, objA);
    }
}
