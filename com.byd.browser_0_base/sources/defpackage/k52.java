package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.privacy.settings.PrivacySettings;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class k52 implements Preference.d {
    public final /* synthetic */ int e;

    public /* synthetic */ k52(int i) {
        this.e = i;
    }

    @Override // androidx.preference.Preference.d
    public final boolean r(Preference preference) {
        switch (this.e) {
            case 0:
                int i = PrivacySettings.i0;
                n3.X0("Settings.PrivacyGuide.StartPrivacySettings");
                oj2.U(6, 9, "Settings.PrivacyGuide.EntryExit");
                return false;
            default:
                return true;
        }
    }
}
