package org.chromium.chrome.browser.browserservices;

import android.R;
import android.app.AlertDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.ku2;
import defpackage.nf0;
import defpackage.of0;
import defpackage.p61;
import defpackage.pp;
import defpackage.q11;
import defpackage.rt;
import defpackage.s50;
import defpackage.sp;
import defpackage.tt;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: loaded from: classes.dex */
public class ClearDataDialogActivity extends AppCompatActivity {
    public static final /* synthetic */ int D = 0;

    public final void D1(final boolean z) {
        final boolean zI = q11.i(getIntent(), "org.chromium.chrome.extra.app_uninstalled", false);
        final tt ttVarE = ((s50) pp.d()).e();
        p61<SharedPreferencesManager> p61Var = ttVarE.a;
        sp spVar = ttVarE.b;
        if (z || spVar.f) {
            spVar.f(new Runnable() { // from class: st
                @Override // java.lang.Runnable
                public final void run() {
                    ttVarE.c.getClass();
                    oj2.O(zI ? "TrustedWebActivity.ClearDataDialogOnUninstallAccepted" : "TrustedWebActivity.ClearDataDialogOnClearAppDataAccepted", z);
                }
            });
            return;
        }
        String str = zI ? "twa_dialog_number_of_dismissals_on_uninstall" : "twa_dialog_number_of_dismissals_on_clear_data";
        ku2 ku2VarT = ku2.t();
        try {
            p61Var.get().m(p61Var.get().d(0, str) + 1, str);
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        new AlertDialog.Builder(this, R.style.Theme.DeviceDefault.Light.Dialog.Alert).setTitle(getString(R$string.twa_clear_data_dialog_title, q11.r(getIntent(), "org.chromium.chrome.extra.app_name"))).setMessage(R$string.twa_clear_data_dialog_message).setPositiveButton(R$string.settings, new nf0(1, this)).setNegativeButton(R$string.twa_clear_data_dialog_keep_data, new of0(1, this)).setOnCancelListener(new rt(0, this)).create().show();
    }
}
