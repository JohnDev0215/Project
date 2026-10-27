package org.chromium.chrome.browser.password_check;

import android.content.Context;
import android.os.Bundle;
import org.chromium.chrome.browser.password_check.PasswordCheckBridge;
import org.chromium.chrome.browser.password_check.a;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: PasswordCheckImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements a, PasswordCheckBridge.a {
    public final SettingsLauncher c;
    public int f = 0;
    public boolean d = false;
    public boolean e = false;
    public final PasswordCheckBridge a = new PasswordCheckBridge(this);
    public final org.chromium.base.b<a.InterfaceC0101a> b = new org.chromium.base.b<>();

    public e(SettingsLauncher settingsLauncher) {
        this.c = settingsLauncher;
    }

    public final void a(a.InterfaceC0101a interfaceC0101a, boolean z) {
        this.b.a(interfaceC0101a);
        if (z && this.d) {
            interfaceC0101a.c();
        }
        if (z && this.e) {
            interfaceC0101a.e();
        }
    }

    public final void b(Context context, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("password-check-referrer", i);
        this.c.b(context, PasswordCheckFragmentView.class, bundle);
    }
}
