package defpackage;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import defpackage.le3;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.history_clusters.b;
import org.chromium.chrome.browser.password_check.CompromisedCredential;
import org.chromium.chrome.browser.password_check.PasswordCheckCoordinator;
import org.chromium.chrome.browser.password_check.PasswordCheckViewDialogFragment;
import org.chromium.chrome.browser.password_check.h;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.password_entry_edit.FederatedCredentialFragmentView;
import org.chromium.chrome.browser.signin.SyncConsentFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;
import org.chromium.components.browser_ui.share.ShareHelper;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;
import org.chromium.components.browser_ui.site_settings.R$style;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class n40 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ n40(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        int i2 = 0;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                CustomTabToolbar.L((CustomTabToolbar) obj2, (CustomTabToolbar.d) obj);
                return;
            case 1:
                u40 u40Var = (u40) obj2;
                m20 m20Var = (m20) obj;
                Tab tab = u40Var.b.b;
                if (tab == null) {
                    return;
                }
                GURL gurlV = tab.v();
                String title = tab.getTitle();
                Intent intent = new Intent();
                intent.setData(Uri.parse(gurlV.j()));
                intent.putExtra("android.intent.extra.SUBJECT", title);
                try {
                    m20Var.e().send(u40Var.e, 0, intent, null, null);
                    break;
                } catch (PendingIntent.CanceledException unused) {
                    Log.e("cr_CustomTabToolbarCoor", "CanceledException while sending pending intent in custom tab");
                }
                n3.X0("CustomTabsCustomActionButtonClick");
                Resources resources = u40Var.d.getResources();
                if (u40Var.a.X() && TextUtils.equals(m20Var.d(), resources.getString(R$string.share))) {
                    ShareHelper.c(0);
                    n3.X0("CustomTabsCustomActionButtonClick.DownloadsUI.Share");
                    return;
                }
                return;
            case 2:
                ((b) obj2).getClass();
                throw null;
            case 3:
                ((Callback) obj2).onResult(new u10((s10) obj, false));
                return;
            case 4:
                g11 g11Var = (g11) obj2;
                g11Var.getClass();
                ((Dialog) obj).dismiss();
                g11Var.e.b(2, g11Var.j);
                return;
            case 5:
                ((PasswordCheckCoordinator.a) ((PropertyModel) obj2).j(h.a.b)).h((CompromisedCredential) obj);
                return;
            case 6:
                ((ClipboardManager) obj).setPrimaryClip(ClipData.newPlainText("password", ((PasswordCheckViewDialogFragment) obj2).q0.getPassword()));
                return;
            case 7:
                int i3 = FederatedCredentialFragmentView.l0;
                ((CredentialEntryFragmentViewBase.b) obj).b(((FederatedCredentialFragmentView) obj2).D().getApplicationContext());
                return;
            case 8:
                ((Callback) ((PropertyModel) obj2).j(le3.a.e)).onResult((Credential) obj);
                return;
            case 9:
                ((Callback) obj2).onResult((Account) obj);
                return;
            case 10:
                xx2 xx2Var = (xx2) obj2;
                xx2Var.e("Continued");
                String str = xx2Var.a.a;
                xx2Var.h.getClass();
                int i4 = SyncConsentFragment.t0;
                Bundle bundle = new Bundle();
                bundle.putInt("SyncConsentFragmentBase.SigninFlowType", 1);
                bundle.putInt("SyncConsentFragmentBase.AccessPoint", xx2Var.c);
                bundle.putString("SyncConsentFragmentBase.AccountName", str);
                bundle.putInt("SyncConsentFragment.PersonalizedPromoAction", 2);
                a8.S((Context) obj, bundle);
                return;
            case 11:
                mb1 mb1Var = (mb1) obj2;
                ChromeImageViewPreference chromeImageViewPreference = (ChromeImageViewPreference) obj;
                if (mb1Var.r(chromeImageViewPreference)) {
                    nb1.h(chromeImageViewPreference.e, org.chromium.components.browser_ui.settings.R$string.managed_by_your_organization);
                    return;
                } else {
                    if (mb1Var.b(chromeImageViewPreference)) {
                        nb1.h(chromeImageViewPreference.e, mb1Var.e() ? org.chromium.components.browser_ui.settings.R$string.managed_by_your_parents : org.chromium.components.browser_ui.settings.R$string.managed_by_your_parent);
                        return;
                    }
                    return;
                }
            default:
                ChosenObjectSettings chosenObjectSettings = (ChosenObjectSettings) obj2;
                int i5 = ChosenObjectSettings.n0;
                AlertDialog.Builder builder = new AlertDialog.Builder(chosenObjectSettings.K(), R$style.ThemeOverlay_BrowserUI_AlertDialog);
                int i6 = org.chromium.components.browser_ui.site_settings.R$string.reset;
                builder.i(i6);
                builder.a.f = (String) obj;
                builder.f(i6, new oo(i2, chosenObjectSettings));
                builder.e(org.chromium.components.browser_ui.site_settings.R$string.cancel, null);
                builder.k();
                return;
        }
    }
}
