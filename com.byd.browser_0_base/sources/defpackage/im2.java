package defpackage;

import android.util.Log;
import androidx.preference.Preference;
import defpackage.yr2;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;
import org.chromium.components.messages.b;
import org.chromium.components.page_info.PageInfoCookiesPreference;
import org.chromium.components.payments.a;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.b;
import org.chromium.ui.modelutil.o;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class im2 implements SigninManager.c, p01, yr2.b, b.a, Preference.c, a.b, b.a, o.b {
    public final /* synthetic */ Object e;

    public /* synthetic */ im2(Object obj) {
        this.e = obj;
    }

    @Override // org.chromium.components.messages.b.a
    public final void a(int i, PropertyModel propertyModel) {
        ((org.chromium.components.messages.a) this.e).a(i, propertyModel);
    }

    @Override // defpackage.p01
    public final void b() {
        CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider = (CableAuthenticatorModuleProvider) this.e;
        int i = CableAuthenticatorModuleProvider.a0;
        cableAuthenticatorModuleProvider.getClass();
        Log.e("cr_CableAuthModuleProv", "Failed to install caBLE DFM");
        PostTask.c(rh3.a, new d92(21, cableAuthenticatorModuleProvider));
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        PageInfoCookiesPreference.a aVar = (PageInfoCookiesPreference.a) this.e;
        int i = PageInfoCookiesPreference.p0;
        aVar.b.onResult((Boolean) obj);
        return true;
    }
}
