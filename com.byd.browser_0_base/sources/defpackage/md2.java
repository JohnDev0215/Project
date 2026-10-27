package defpackage;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;
import org.chromium.chrome.browser.settings.MainSettings;
import org.chromium.components.browser_ui.settings.SettingsLauncher;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class md2 implements Preference.d {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ md2(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                n3.X0("Settings.SafetyCheck.ManageSafeBrowsing");
                oj2.U(3, 10, "Settings.SafetyCheck.Interactions");
                String name = SafeBrowsingSettingsFragment.class.getName();
                Context context = preference.e;
                int i2 = SafeBrowsingSettingsFragment.l0;
                Bundle bundle = new Bundle();
                bundle.putInt("SafeBrowsingSettingsFragment.AccessPoint", 2);
                context.startActivity(((SettingsLauncher) obj).a(context, name, bundle));
                break;
            case 1:
                od2 od2Var = (od2) obj;
                od2Var.getClass();
                Context context2 = preference.e;
                od2Var.d.getClass();
                a8.R(context2, 32);
                break;
            default:
                int i3 = MainSettings.m0;
                qx1.b(((MainSettings) obj).D(), 0, new n3(), false);
                break;
        }
        return true;
    }
}
