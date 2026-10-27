package defpackage;

import J.N;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import defpackage.t90;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.chromium.base.CollectionUtil;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFragment;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.settings.SpinnerPreference;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class u90 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;
    public final /* synthetic */ Object h;
    public final /* synthetic */ Object i;
    public final /* synthetic */ Object j;
    public final /* synthetic */ Object k;

    public /* synthetic */ u90(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, int i) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
        this.h = obj3;
        this.i = obj4;
        this.j = obj5;
        this.k = obj6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 1;
        int i2 = this.e;
        Object obj = this.k;
        Object obj2 = this.j;
        Object obj3 = this.i;
        Object obj4 = this.h;
        Object obj5 = this.g;
        Object obj6 = this.f;
        switch (i2) {
            case 0:
                v90 v90Var = (v90) obj6;
                v90Var.getClass();
                Bundle bundle = new Bundle();
                bundle.putString("acknowledge.purchaseToken", (String) obj5);
                bundle.putBoolean("acknowledge.makeAvailableAgain", true);
                v90Var.a((Uri) obj3, "acknowledge", bundle, new a2((t90.a) obj4), (Runnable) obj2, (Runnable) obj);
                break;
            default:
                ClearBrowsingDataFragment clearBrowsingDataFragment = (ClearBrowsingDataFragment) obj6;
                Set set = (Set) obj5;
                String[] strArr = (String[]) obj4;
                int[] iArr = (int[]) obj3;
                String[] strArr2 = (String[]) obj2;
                int[] iArr2 = (int[]) obj;
                int i3 = ClearBrowsingDataFragment.o0;
                clearBrowsingDataFragment.getClass();
                Log.i("ClearBrowsingDataFragment", "   clearBrowsingData start");
                clearBrowsingDataFragment.T0();
                if (clearBrowsingDataFragment.D() != null) {
                    r20 r20Var = new r20(clearBrowsingDataFragment.K(), clearBrowsingDataFragment.D().getString(R$string.clear_browsing_data_progress_title));
                    clearBrowsingDataFragment.j0 = r20Var;
                    r20Var.show();
                }
                q8 q8Var = new q8(0);
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    q8Var.add(Integer.valueOf(ClearBrowsingDataFragment.P0(((Integer) it.next()).intValue())));
                }
                if (!q8Var.contains(2)) {
                    i = q8Var.contains(1) ? 2 : 0;
                } else if (q8Var.contains(1)) {
                    i = 3;
                }
                oj2.U(i, 4, "History.ClearBrowsingData.UserDeletedCookieOrCacheFromDialog");
                SpinnerPreference spinnerPreference = (SpinnerPreference) clearBrowsingDataFragment.f("time_period_spinner");
                Spinner spinner = spinnerPreference.S;
                int i4 = ((ClearBrowsingDataFragment.b) (spinner == null ? spinnerPreference.T.getItem(spinnerPreference.U) : spinner.getSelectedItem())).a;
                int[] iArrC = CollectionUtil.c(new ArrayList(q8Var));
                if (strArr == null || strArr.length == 0) {
                    BrowsingDataBridge.b().a(clearBrowsingDataFragment, iArrC, i4);
                } else {
                    BrowsingDataBridge browsingDataBridgeB = BrowsingDataBridge.b();
                    browsingDataBridgeB.a = clearBrowsingDataFragment;
                    N.McYsV35Z(browsingDataBridgeB, Profile.d(), iArrC, i4, strArr, iArr, strArr2, iArr2);
                }
                a8.C().getClass();
                break;
        }
    }
}
