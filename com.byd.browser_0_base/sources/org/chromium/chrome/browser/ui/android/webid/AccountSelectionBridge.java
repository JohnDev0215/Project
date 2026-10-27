package org.chromium.chrome.browser.ui.android.webid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.SystemClock;
import android.text.TextUtils;
import defpackage.ax0;
import defpackage.ji;
import defpackage.m1;
import defpackage.o1;
import defpackage.p1;
import defpackage.r1;
import defpackage.t41;
import defpackage.w1;
import defpackage.xz;
import java.util.Arrays;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.ui.android.webid.data.Account;
import org.chromium.chrome.browser.ui.android.webid.data.ClientIdMetadata;
import org.chromium.chrome.browser.ui.android.webid.data.IdentityProviderMetadata;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class AccountSelectionBridge implements m1 {
    public long a;
    public final o1 b;

    public AccountSelectionBridge(long j, WindowAndroid windowAndroid, ji jiVar) {
        this.a = j;
        this.b = new o1(windowAndroid.i.get(), jiVar, this);
    }

    @CalledByNative
    public static AccountSelectionBridge create(long j, WindowAndroid windowAndroid) {
        ji jiVarA = h.a(windowAndroid);
        if (jiVarA == null) {
            return null;
        }
        return new AccountSelectionBridge(j, windowAndroid, jiVarA);
    }

    @CalledByNative
    public static int getBrandIconIdealSize() {
        return Math.round(xz.a.getResources().getDimension(R$dimen.account_selection_sheet_icon_size) / 0.8f);
    }

    @CalledByNative
    public static int getBrandIconMinimumSize() {
        return Math.round(getBrandIconIdealSize() / Math.max(xz.a.getResources().getDisplayMetrics().density, 1.0f));
    }

    @CalledByNative
    public final void destroy() {
        r1 r1Var = this.b.b;
        if (!r1Var.b) {
            r1Var.b = true;
            t41.f.h(r1Var.u);
            r1Var.h.c(r1Var.i, true);
            r1Var.d();
        }
        this.a = 0L;
    }

    @CalledByNative
    public final void showAccounts(String str, String str2, String str3, Account[] accountArr, IdentityProviderMetadata identityProviderMetadata, ClientIdMetadata clientIdMetadata, boolean z) {
        List listAsList = Arrays.asList(accountArr);
        r1 r1Var = this.b.b;
        r1Var.getClass();
        if (!TextUtils.isEmpty(identityProviderMetadata.c)) {
            r1Var.p = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            new Canvas(r1Var.p).drawColor(0);
        }
        r1Var.s = listAsList.size() == 1 ? (Account) listAsList.get(0) : null;
        r1Var.c(str, str2, str3, listAsList, identityProviderMetadata, clientIdMetadata, z, w1.c);
        r1Var.t = SystemClock.elapsedRealtime();
        String str4 = identityProviderMetadata.c;
        if (TextUtils.isEmpty(str4)) {
            return;
        }
        int brandIconIdealSize = getBrandIconIdealSize();
        r1Var.f.d(new ax0.a(brandIconIdealSize, brandIconIdealSize, 0, new GURL(str4).j(), "WebIDAccountSelection", false), new p1(r1Var, 0));
    }
}
