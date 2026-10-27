package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.browser.customtabs.PostMessageService;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ce;
import defpackage.pt3;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillPaymentMethodsFragment;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragmentBasic;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.privacy_sandbox.v4.FledgeFragmentV4;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.searchwidget.SearchActivity;
import org.chromium.chrome.browser.toolbar.adaptive.settings.AdaptiveToolbarPreferenceFragment;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content.browser.input.TextSuggestionHost;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.l;
import org.chromium.ui.modelutil.n;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wd0 implements wr1, Preference.c, a.f, l.b, n.a, k32, gq, ce.a, ClearBrowsingDataFragmentBasic.a, r02, yd3, WebContentsImpl.d {
    public final /* synthetic */ int e;

    public /* synthetic */ wd0(int i) {
        this.e = i;
    }

    @Override // defpackage.r02
    public final q02 a() {
        switch (this.e) {
            case 19:
                return m02.e();
            default:
                if (m02.k == null) {
                    m02.k = new ai1();
                }
                return m02.k;
        }
    }

    @Override // ce.a
    public final boolean f(GURL gurl) {
        Object obj = SearchActivity.q0;
        return false;
    }

    @Override // org.chromium.ui.modelutil.n.a
    public final void h(Object obj, Object obj2) {
        m4 m4Var = (m4) obj;
        j.a(((xa1) obj2).b, m4Var.e, m4Var.y, true);
    }

    @Override // org.chromium.content.browser.webcontents.WebContentsImpl.d
    public final Object i(WebContentsImpl webContentsImpl) {
        switch (this.e) {
            case 24:
                return new GestureListenerManagerImpl(webContentsImpl);
            case 25:
                return new v22();
            default:
                return new TextSuggestionHost(webContentsImpl);
        }
    }

    @Override // org.chromium.ui.modelutil.l.b
    public final Object j(int i, RecyclerView recyclerView) {
        Object jm3Var;
        switch (this.e) {
            case 7:
                int i2 = i81.y;
                switch (i) {
                    case 1:
                        int i3 = rx0.F;
                        return new rx0(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_in_progress_item, (ViewGroup) null));
                    case 2:
                        int i4 = gs0.L;
                        return new gs0(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_generic_item, (ViewGroup) null));
                    case 3:
                        jm3Var = new jm3(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_video_item, (ViewGroup) null));
                        break;
                    case 4:
                        jm3Var = new o9(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_audio, (ViewGroup) null));
                        break;
                    case 5:
                    case 6:
                        jm3Var = new ix0(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_image_item, (ViewGroup) null));
                        break;
                    case 7:
                        return new n50(recyclerView);
                    case 8:
                        jm3Var = new eh2(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_section_header, (ViewGroup) null));
                        break;
                    case 9:
                        jm3Var = new tx0(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_in_progress_video_item, (ViewGroup) null));
                        break;
                    case 10:
                        jm3Var = new sx0(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_in_progress_image_item, (ViewGroup) null));
                        break;
                    case 11:
                        jm3Var = new o32(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_prefetch_article, (ViewGroup) null));
                        break;
                    case 12:
                        jm3Var = new q32(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_prefetch_grouped_item, (ViewGroup) null));
                        break;
                    case 13:
                        jm3Var = new om(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_card_header, (ViewGroup) null));
                        break;
                    case 14:
                        jm3Var = new nm(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_card_footer, (ViewGroup) null));
                        break;
                    case 15:
                        jm3Var = new mm(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_card_divider_top, (ViewGroup) null));
                        break;
                    case 16:
                        jm3Var = new lm(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_card_divider_middle, (ViewGroup) null));
                        break;
                    case 17:
                        jm3Var = new km(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_card_divider_bottom, (ViewGroup) null));
                        break;
                    case 18:
                        jm3Var = new lv1(LayoutInflater.from(recyclerView.getContext()).inflate(R$layout.download_manager_pagination_header, (ViewGroup) null));
                        break;
                    default:
                        return null;
                }
                return jm3Var;
            default:
                if (i == 1) {
                    return new cx1(recyclerView, R$layout.password_check_header_item, new cg(15));
                }
                if (i != 2) {
                    return null;
                }
                return new cx1(recyclerView, R$layout.password_check_compromised_credential_item, new zf(18));
        }
    }

    @Override // defpackage.wr1
    public final pt3 k(View view, pt3 pt3Var) {
        int[] iArr = DrawerLayout.I;
        DrawerLayout drawerLayout = (DrawerLayout) view;
        pt3.i iVar = pt3Var.a;
        boolean z = iVar.j().b > 0;
        drawerLayout.C = pt3Var;
        drawerLayout.D = z;
        drawerLayout.setWillNotDraw(!z && drawerLayout.getBackground() == null);
        drawerLayout.requestLayout();
        return iVar.c();
    }

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        switch (this.e) {
            case 2:
                int i = AutofillPaymentMethodsFragment.h0;
                PersonalDataManager.d().e("autofill.credit_card_fido_auth_enabled", ((Boolean) obj).booleanValue());
                break;
            default:
                int i2 = AdaptiveToolbarPreferenceFragment.j0;
                SharedPreferencesManager.getInstance().m(((Integer) obj).intValue(), "Chrome.AdaptiveToolbarCustomization.Settings");
                break;
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.customtabs.a.f
    public final Object o(a.e eVar) {
        switch (this.e) {
            case 4:
                Context context = xz.a;
                a32 a32Var = eVar.e;
                String str = a32Var.h;
                if (str == null) {
                    throw new IllegalStateException("setPackageName must be called before bindSessionToPostMessageService.");
                }
                Intent intent = new Intent();
                intent.setClassName(str, PostMessageService.class.getName());
                boolean zBindService = context.bindService(intent, a32Var, 1);
                if (!zBindService) {
                    Log.w("PostMessageServConn", "Could not bind to PostMessageService in client.");
                }
                return Boolean.valueOf(zBindService);
            case 5:
                return Boolean.FALSE;
            default:
                return Boolean.FALSE;
        }
    }

    @Override // defpackage.k32
    public final boolean q(f51 f51Var) {
        return f51Var.e;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        int i = FledgeFragmentV4.u0;
        if ("fledge_toggle".equals(preference.p)) {
            return oj2.k(Profile.d()).d("privacy_sandbox.m1.fledge_enabled");
        }
        return false;
    }

    @Override // defpackage.yd3
    public final void c(boolean z) {
    }
}
