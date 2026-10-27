package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.preference.d;
import defpackage.bq;
import defpackage.g50;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pp0;
import defpackage.q11;
import defpackage.sk;
import defpackage.tp2;
import defpackage.wd0;
import defpackage.wp2;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.c;
import org.chromium.chrome.browser.privacy_sandbox.v4.PrivacySandboxSettingsFragmentV4;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PrivacySandboxSettingsBaseFragment extends d implements pp0 {
    public wd0 h0;
    public SettingsLauncher i0;
    public wp2 j0;
    public Callback<Context> k0;

    public static void M0(Context context, SettingsLauncher settingsLauncher, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("privacy-sandbox-referrer", i);
        sk skVar = bq.a;
        settingsLauncher.b(context, N.M09VlOh_("PrivacySandboxSettings4") ? PrivacySandboxSettingsFragmentV4.class : PrivacySandboxSettingsFragmentV3.class, bundle);
    }

    @Override // androidx.preference.d
    public void J0(Bundle bundle, String str) {
        D0();
    }

    public final void N0() {
        int i = this.j.getInt("privacy-sandbox-referrer");
        oj2.U(i, 4, "Settings.PrivacySandbox.PrivacySandboxReferrer");
        if (i == 0) {
            n3.X0("Settings.PrivacySandbox.OpenedFromSettingsParent");
        } else if (i == 1) {
            n3.X0("Settings.PrivacySandbox.OpenedFromCookiesPageToast");
        } else if (i == 3) {
            n3.X0("PageInfo.AdPersonalization.ManageInterestClicked");
        }
    }

    public final void O0(int i, int i2) {
        this.j0.c(tp2.a(N().getString(i), null, 0, i2));
    }

    public void i(SettingsLauncher settingsLauncher) {
        this.i0 = settingsLauncher;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0034. Please report as an issue. */
    @Override // androidx.fragment.app.Fragment
    public boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() != R$id.menu_id_targeted_help) {
            return false;
        }
        sk skVar = bq.a;
        String str = N.M09VlOh_("PrivacySandboxSettings4") ? "https://support.google.com/chrome/?p=ad_privacy" : "https://www.privacysandbox.com";
        g50.a aVar = new g50.a();
        aVar.d();
        g50 g50VarA = aVar.a();
        Uri uri = Uri.parse(str);
        Intent intent = g50VarA.a;
        intent.setData(uri);
        wd0 wd0Var = this.h0;
        Context contextK = K();
        switch (wd0Var.e) {
        }
        Intent intentE = c.e(contextK, intent);
        intentE.setPackage(K().getPackageName());
        intentE.putExtra("com.android.browser.application_id", K().getPackageName());
        q11.a(intentE);
        try {
            K().startActivity(intentE, null);
            return true;
        } catch (ActivityNotFoundException unused) {
            return true;
        }
    }
}
