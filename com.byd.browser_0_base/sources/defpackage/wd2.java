package defpackage;

import J.N;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import defpackage.le3;
import defpackage.xx2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.SaveUpdateAddressProfilePrompt;
import org.chromium.chrome.browser.bookmarks.PowerBookmarkShoppingItemRow;
import org.chromium.chrome.browser.feed.webfeed.WebFeedMainMenuItem;
import org.chromium.chrome.browser.language.settings.SelectLanguageFragment;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.chrome.browser.password_entry_edit.CredentialEditFragmentView;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.search_resumption.SearchResumptionModuleView;
import org.chromium.chrome.browser.touch_to_fill.data.WebAuthnCredential;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wd2 implements View.OnClickListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ wd2(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        px0 px0Var;
        int i = this.e;
        int i2 = 5;
        int i3 = 1;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                SaveUpdateAddressProfilePrompt saveUpdateAddressProfilePrompt = (SaveUpdateAddressProfilePrompt) obj2;
                saveUpdateAddressProfilePrompt.getClass();
                saveUpdateAddressProfilePrompt.f.b((qa) obj, new c3(i2, saveUpdateAddressProfilePrompt), new ps(i3));
                break;
            case 1:
                PowerBookmarkShoppingItemRow powerBookmarkShoppingItemRow = (PowerBookmarkShoppingItemRow) obj2;
                Callback callback = (Callback) obj;
                if (!powerBookmarkShoppingItemRow.Q) {
                    powerBookmarkShoppingItemRow.Q = true;
                    oj2.U(powerBookmarkShoppingItemRow.O ? 2 : 1, 3, "PowerBookmarks.BookmarkManager.PriceTrackingEnabled");
                    e32.b(powerBookmarkShoppingItemRow.N, powerBookmarkShoppingItemRow.C, !powerBookmarkShoppingItemRow.O, powerBookmarkShoppingItemRow.R, powerBookmarkShoppingItemRow.getContext().getResources(), callback);
                    break;
                }
                break;
            case 2:
                OfflineItem offlineItem = (OfflineItem) obj2;
                PropertyModel propertyModel = (PropertyModel) obj;
                int i4 = ux0.D;
                int i5 = offlineItem.A;
                if (i5 == 0 || i5 == 1) {
                    ((Callback) propertyModel.j(t81.o)).onResult(offlineItem);
                } else if (i5 == 3 || i5 == 4 || i5 == 5 || i5 == 6) {
                    ((Callback) propertyModel.j(t81.p)).onResult(offlineItem);
                }
                break;
            case 3:
                WebFeedMainMenuItem webFeedMainMenuItem = (WebFeedMainMenuItem) obj2;
                byte[] bArr = (byte[]) obj;
                int i6 = WebFeedMainMenuItem.s;
                webFeedMainMenuItem.getClass();
                N.MVWVyQhp(bArr, false, 1, new gp(webFeedMainMenuItem, 12, bArr));
                ((i7) webFeedMainMenuItem.i).a();
                break;
            case 4:
                int i7 = b.c.C;
                Activity activity = (Activity) ((pq2) ((b.a) obj2)).f;
                int i8 = SelectLanguageFragment.f0;
                Intent intent = new Intent();
                intent.putExtra("SelectLanguageFragment.SelectedLanguage", ((f51) obj).a);
                activity.setResult(-1, intent);
                activity.finish();
                break;
            case 5:
                g11 g11Var = (g11) obj2;
                b11 b11Var = (b11) obj;
                g11Var.getClass();
                int i9 = b11Var.c;
                if (i9 == 1 || i9 == 2) {
                    Toast.c(g11Var.a, R$string.instance_switcher_already_running_foreground, 1).e();
                } else {
                    g11Var.e.b(3, g11Var.j);
                    g11Var.b.onResult(b11Var);
                }
                break;
            case 6:
                int i10 = CredentialEditFragmentView.o0;
                ((CredentialEntryFragmentViewBase.b) obj).b(((CredentialEditFragmentView) obj2).D().getApplicationContext());
                break;
            case 7:
                SearchResumptionModuleView searchResumptionModuleView = (SearchResumptionModuleView) obj2;
                boolean z = !searchResumptionModuleView.g.e;
                searchResumptionModuleView.a(z, false);
                ((Callback) obj).onResult(Boolean.valueOf(z));
                break;
            case 8:
                ((Callback) ((PropertyModel) obj2).j(le3.e.d)).onResult((WebAuthnCredential) obj);
                break;
            case 9:
                xx2 xx2Var = (xx2) obj2;
                xx2Var.getClass();
                SharedPreferencesManager.getInstance().l(xx2Var.e, true);
                xx2Var.e("Dismissed");
                pg pgVar = (pg) ((pq2) ((xx2.a) obj)).f;
                pgVar.a();
                pgVar.k = 0;
                xx2 xx2Var2 = pgVar.j;
                if (xx2Var2 != null && (px0Var = xx2Var2.b) != null) {
                    px0Var.a(null);
                    xx2Var2.b = null;
                }
                pgVar.h.run();
                break;
            default:
                int i11 = ma3.y;
                ((Callback) ((PropertyModel) obj2).j(ea3.a)).onResult((gx0) obj);
                break;
        }
    }
}
