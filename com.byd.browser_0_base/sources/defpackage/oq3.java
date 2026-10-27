package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.night_mode.settings.ThemeSettingsFragment;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: WebContentsDarkModeMessageController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class oq3 extends ClickableSpan {
    public final Context e;
    public final SettingsLauncher f;

    public oq3(Context context, n3 n3Var) {
        this.e = context;
        this.f = n3Var;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("theme_settings_entry", 2);
        this.f.b(this.e, ThemeSettingsFragment.class, bundle);
    }
}
