package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.password_manager.settings.PasswordSettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class tx1 implements Preference.c, gq {
    public final /* synthetic */ PasswordSettings e;

    @Override // androidx.preference.Preference.c
    public final boolean n(Preference preference, Object obj) {
        int i = PasswordSettings.n0;
        Boolean bool = (Boolean) obj;
        this.e.N0().e("credentials_enable_autosignin", bool.booleanValue());
        oj2.O("PasswordManager.Settings.ToggleAutoSignIn", bool.booleanValue());
        return true;
    }

    @Override // defpackage.mb1
    public final boolean r(Preference preference) {
        int i = PasswordSettings.n0;
        return this.e.N0().d("credentials_enable_service");
    }
}
