package defpackage;

import android.content.Context;
import defpackage.wp2;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: PrivacySandboxSnackbarController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i52 implements wp2.b {
    public final Context e;
    public final SettingsLauncher f;
    public final wp2 g;

    public i52(Context context, wp2 wp2Var, n3 n3Var) {
        Object obj = ThreadUtils.a;
        this.e = context;
        this.g = wp2Var;
        this.f = n3Var;
    }

    @Override // wp2.b
    public final void j(Object obj) {
        PrivacySandboxSettingsBaseFragment.M0(this.e, this.f, 1);
    }

    @Override // wp2.b
    public final void d(Object obj) {
    }
}
