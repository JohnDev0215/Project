package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ListView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.aj3;
import defpackage.cb;
import defpackage.db;
import defpackage.g23;
import defpackage.hj;
import defpackage.kq3;
import defpackage.sq3;
import defpackage.ta;
import defpackage.te0;
import defpackage.ua;
import defpackage.vb1;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.autofill.AutofillSuggestion;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes.dex */
public class AutofillPopupBridge implements ta, DialogInterface.OnClickListener {
    public long e;
    public final cb f;
    public AlertDialog g;
    public final Activity h;
    public final WebContentsAccessibilityImpl i;
    public final sq3 j;

    public AutofillPopupBridge(View view, long j, WindowAndroid windowAndroid) {
        this.e = j;
        Activity activity = (Activity) windowAndroid.k().get();
        Tab tabU = g23.u(windowAndroid);
        sq3 sq3Var = null;
        WebContents webContentsC = tabU != null ? tabU.c() : null;
        if (activity == null || webContentsC == null) {
            this.f = null;
            this.h = null;
            this.j = null;
            this.i = null;
            return;
        }
        this.h = activity;
        aj3<vb1> aj3Var = vb1.j;
        aj3<vb1> aj3Var2 = vb1.j;
        org.chromium.base.c cVar = windowAndroid.r;
        zp1 zp1Var = (zp1) aj3Var2.e(cVar);
        zp1 zp1Var2 = (zp1) aj3Var2.e(cVar);
        ViewAndroidDelegate viewAndroidDelegateU = webContentsC.u();
        if (viewAndroidDelegateU != null && viewAndroidDelegateU.getContainerView() != null) {
            sq3Var = new sq3(webContentsC, (zp1) hj.j.e(cVar), zp1Var2);
        }
        this.j = sq3Var;
        this.f = new cb(activity, view, this, sq3Var);
        if (zp1Var != null && zp1Var.f()) {
            ((org.chromium.chrome.browser.keyboard_accessory.a) zp1Var.get()).y();
        }
        this.i = kq3.b(webContentsC);
    }

    @CalledByNative
    public static AutofillPopupBridge create(View view, long j, WindowAndroid windowAndroid) {
        return new AutofillPopupBridge(view, j, windowAndroid);
    }

    @CalledByNative
    public static AutofillSuggestion[] createAutofillSuggestionArray(int i) {
        return new AutofillSuggestion[i];
    }

    @Override // defpackage.ta
    public final void a(int i) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.Mfhlibrm(j, this, i);
    }

    @CalledByNative
    public final void addToAutofillSuggestionArray(AutofillSuggestion[] autofillSuggestionArr, int i, String str, String str2, String str3, String str4, String str5, int i2, boolean z, int i3, boolean z2, boolean z3, boolean z4, GURL gurl) {
        Bitmap bitmapB;
        int i4 = i2 == 0 ? 0 : i2;
        if (gurl == null || !gurl.b) {
            bitmapB = null;
        } else {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            Activity activity = this.h;
            bitmapB = personalDataManagerC.b(d.a(gurl, activity.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_icon_width), activity.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_icon_height)));
        }
        autofillSuggestionArr[i] = new AutofillSuggestion(str, str2, str3, str4, str5, i4, z, i3, z2, z3, z4, null, null, bitmapB);
    }

    @Override // defpackage.ta
    public final void b(int i) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MD76PU5t(j, this, i);
    }

    @Override // defpackage.ta
    public final void c() {
        int iMk31b3DX;
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = this.i;
        if (!webContentsAccessibilityImpl.m() || (iMk31b3DX = N.Mk31b3DX(webContentsAccessibilityImpl.j)) == 0) {
            return;
        }
        webContentsAccessibilityImpl.q(iMk31b3DX);
        webContentsAccessibilityImpl.z(webContentsAccessibilityImpl.s);
    }

    @CalledByNative
    public final void confirmDeletion(String str, String str2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.h, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        AlertController.AlertParams alertParams = builder.a;
        alertParams.d = str;
        alertParams.f = str2;
        builder.e(R$string.cancel, null);
        builder.f(R$string.ok, this);
        AlertDialog alertDialogA = builder.a();
        this.g = alertDialogA;
        alertDialogA.show();
    }

    @Override // defpackage.ta
    public final void d() {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MOHZpjVa(j, this);
    }

    @CalledByNative
    public final void dismiss() {
        this.e = 0L;
        cb cbVar = this.f;
        if (cbVar != null) {
            cbVar.e.k.a();
        }
        AlertDialog alertDialog = this.g;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
        sq3 sq3Var = this.j;
        if (sq3Var != null) {
            sq3Var.f(null);
            sq3Var.e(null);
        }
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = this.i;
        if (webContentsAccessibilityImpl.m()) {
            N.MdET073e(webContentsAccessibilityImpl.j);
            webContentsAccessibilityImpl.v = null;
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MlIbag6_(j, this);
    }

    @CalledByNative
    public final void show(AutofillSuggestion[] autofillSuggestionArr, boolean z) {
        cb cbVar = this.f;
        if (cbVar != null) {
            cbVar.h = new ArrayList(Arrays.asList(autofillSuggestionArr));
            ArrayList arrayList = new ArrayList();
            HashSet hashSet = new HashSet();
            for (AutofillSuggestion autofillSuggestion : autofillSuggestionArr) {
                if (autofillSuggestion.h == -3) {
                    hashSet.add(Integer.valueOf(arrayList.size()));
                } else {
                    arrayList.add(autofillSuggestion);
                }
            }
            cbVar.a(new ua(cbVar.f, arrayList, hashSet));
            te0 te0Var = cbVar.e;
            te0Var.g = z;
            te0Var.b();
            te0Var.m.setOnItemLongClickListener(cbVar);
            te0Var.m.setAccessibilityDelegate(new db(cbVar));
            ListView listView = te0Var.m;
            WebContentsAccessibilityImpl webContentsAccessibilityImpl = this.i;
            if (webContentsAccessibilityImpl.m()) {
                webContentsAccessibilityImpl.v = listView;
                N.MMiqVowe(webContentsAccessibilityImpl.j);
            }
        }
    }

    @CalledByNative
    public final boolean wasSuppressed() {
        return this.f == null;
    }
}
