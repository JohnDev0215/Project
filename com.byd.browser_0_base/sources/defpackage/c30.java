package defpackage;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.preference.Preference;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.f81;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ChromeTabbedActivity;
import org.chromium.chrome.browser.ntp.CardEditActivity;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.search_resumption.SearchResumptionTileView;
import org.chromium.chrome.browser.search_resumption.d;
import org.chromium.chrome.browser.signin.SyncConsentFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.appmenu.a;
import org.chromium.chrome.browser.ui.autofill.a;
import org.chromium.chrome.browser.ui.autofill.b;
import org.chromium.chrome.browser.ui.signin.SyncConsentFragmentBase;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.webapps.bottomsheet.PwaBottomSheetController;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c30 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ c30(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = this.e;
        boolean z = false;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                d30 d30Var = (d30) obj2;
                d30.d((PendingIntent) obj, null, d30Var.e, d30Var.j);
                break;
            case 1:
                int i2 = nm.z;
                ((Callback) ((PropertyModel) obj2).j(t81.z)).onResult(((f81.c) obj).d);
                break;
            case 2:
                g11 g11Var = (g11) obj2;
                Dialog dialog = (Dialog) obj;
                g11Var.getClass();
                if (((CheckBox) dialog.findViewById(R$id.no_more_check)).isChecked()) {
                    SharedPreferencesManager.getInstance().l("Chrome.MultiWindow.CloseWindowSkipConfirm", true);
                }
                dialog.dismiss();
                g11Var.b(g11Var.k);
                break;
            case 3:
                CardEditActivity cardEditActivity = (CardEditActivity) obj2;
                if (view == ((Button) obj)) {
                    int i3 = CardEditActivity.U;
                    cardEditActivity.finish();
                } else if (view == cardEditActivity.T) {
                    int i4 = cardEditActivity.I;
                    if (i4 != 0 && i4 != 2) {
                        cardEditActivity.M1();
                    } else if (cardEditActivity.f69J.size() < 12) {
                        cardEditActivity.K1();
                    } else {
                        Toast.makeText(cardEditActivity, cardEditActivity.getString(R$string.card_full_msg), 0).show();
                    }
                } else if (view == cardEditActivity.S) {
                    n3.l1(null, false);
                    cardEditActivity.finish();
                } else if (view == cardEditActivity.Q) {
                    cardEditActivity.O.setText("");
                } else if (view == cardEditActivity.R) {
                    cardEditActivity.P.setText("");
                }
                break;
            case 4:
                int i5 = CredentialEditFragmentView.o0;
                ((CredentialEntryFragmentViewBase.b) obj).f(((CredentialEditFragmentView) obj2).D().getApplicationContext());
                break;
            case 5:
                ((Tab) ((pq2) ((d.a) obj)).f).b(new LoadUrlParams(((SearchResumptionTileView) obj2).e.j(), 0));
                n3.X0("SearchResumptionModule.NTP.Click");
                break;
            case 6:
                n63 n63Var = (n63) obj2;
                n63Var.a.b();
                ((Dialog) obj).dismiss();
                Context context = n63Var.b;
                if (context != null) {
                    ChromeTabbedActivity chromeTabbedActivity = (ChromeTabbedActivity) context;
                    TabModel tabModelD2 = chromeTabbedActivity.D2();
                    if (tabModelD2 != null && tabModelD2.getCount() == 0) {
                        tabModelD2.c();
                        chromeTabbedActivity.C2().f(19);
                        z = true;
                    }
                    if (!z) {
                        chromeTabbedActivity.J2();
                    }
                }
                break;
            case 7:
                int[] iArr = a.a;
                ((b7) obj2).a((PropertyModel) obj);
                break;
            case 8:
                a.C0117a c0117a = (a.C0117a) obj2;
                org.chromium.chrome.browser.ui.autofill.a aVar = c0117a.C;
                int i6 = aVar.k;
                aVar.k = c0117a.f();
                RecyclerView.f fVar = aVar.e;
                fVar.d(i6, 1, null);
                fVar.d(aVar.k, 1, null);
                s9 s9Var = aVar.h.get(aVar.k);
                b bVar = (b) ((a.b) obj);
                bVar.i = s9Var;
                bVar.h.p(di1.j, bVar.a(s9Var.e));
                break;
            case 9:
                xx2 xx2Var = (xx2) obj2;
                xx2Var.e("Continued");
                String str = xx2Var.a.a;
                xx2Var.h.getClass();
                int i7 = SyncConsentFragment.t0;
                Bundle bundleK0 = SyncConsentFragmentBase.K0(xx2Var.c, str);
                bundleK0.putInt("SyncConsentFragment.PersonalizedPromoAction", 1);
                a8.S((Context) obj, bundleK0);
                break;
            case 10:
                ChosenObjectSettings chosenObjectSettings = (ChosenObjectSettings) obj2;
                int i8 = ChosenObjectSettings.n0;
                ((no) obj).a(((kr) chosenObjectSettings.h0).b);
                chosenObjectSettings.M0();
                break;
            case 11:
                String[] strArr = SingleWebsiteSettings.u0;
                ((SingleWebsiteSettings) obj2).T0((Preference) obj);
                break;
            default:
                PwaBottomSheetController.c cVar = (PwaBottomSheetController.c) obj2;
                cVar.getClass();
                new jx0(cVar.h, (Bitmap) obj).show();
                break;
        }
    }
}
