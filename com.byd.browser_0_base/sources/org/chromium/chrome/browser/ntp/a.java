package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import defpackage.bq;
import defpackage.q11;
import defpackage.sk;
import defpackage.yn2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.chrome.browser.site_settings.CookieControlsServiceBridge;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;

/* JADX INFO: compiled from: IncognitoCookieControlsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements CookieControlsServiceBridge.a, CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public CookieControlsServiceBridge e;
    public boolean g;
    public boolean h;
    public boolean j;
    public final org.chromium.base.b<InterfaceC0096a> f = new org.chromium.base.b<>();
    public int i = 0;
    public int k = 0;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.ntp.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IncognitoCookieControlsManager.java */
    public interface InterfaceC0096a {
        void a(int i, boolean z);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        sk skVar = bq.a;
        boolean z2 = true;
        if (!N.M09VlOh_("IncognitoNtpRevamp") ? compoundButton.getId() != R$id.cookie_controls_card_toggle : compoundButton.getId() != R$id.revamped_cookie_controls_card_toggle) {
            z2 = false;
        }
        if (z == this.h || !z2) {
            return;
        }
        N.MIu6BVKt(this.e.a, z);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view.getId() == R$id.cookie_controls_card_managed_icon) {
            Bundle bundle = new Bundle();
            bundle.putString("category", yn2.m(8));
            Context context = view.getContext();
            String name = SingleCategorySettings.class.getName();
            Intent intent = new Intent();
            intent.setClass(context, SettingsActivity.class);
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
                intent.addFlags(67108864);
            }
            intent.putExtra("show_fragment", name);
            intent.putExtra("show_fragment_args", bundle);
            ComponentName componentName = q11.a;
            try {
                context.startActivity(intent, null);
            } catch (ActivityNotFoundException unused) {
            }
        }
    }
}
