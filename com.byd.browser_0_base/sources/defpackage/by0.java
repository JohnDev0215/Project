package defpackage;

import android.text.style.ForegroundColorSpan;
import androidx.fragment.app.FragmentActivity;
import defpackage.iq2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.incognito.reauth.IncognitoReauthSettingSwitchPreference;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.styles.R$color;

/* JADX INFO: compiled from: IncognitoLockSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public final class by0 {
    public final IncognitoReauthSettingSwitchPreference a;
    public boolean b;
    public ny0 c;

    public by0(IncognitoReauthSettingSwitchPreference incognitoReauthSettingSwitchPreference) {
        this.a = incognitoReauthSettingSwitchPreference;
    }

    public final void a(FragmentActivity fragmentActivity) {
        CharSequence charSequenceA;
        if (ny0.b()) {
            if (wy0.a()) {
                charSequenceA = fragmentActivity.getString(R$string.settings_incognito_tab_lock_summary_android_setting_on);
            } else {
                String str = ii2.a;
                charSequenceA = iq2.a(fragmentActivity.getString(R$string.settings_incognito_tab_lock_summary_android_setting_off), new iq2.a(new ForegroundColorSpan(fragmentActivity.getColor(R$color.dialog_color_text_message_info_hint_url)), "<link>", "</link>"));
            }
            IncognitoReauthSettingSwitchPreference incognitoReauthSettingSwitchPreference = this.a;
            incognitoReauthSettingSwitchPreference.R(charSequenceA);
            incognitoReauthSettingSwitchPreference.e0 = wy0.a();
            incognitoReauthSettingSwitchPreference.s();
            boolean zA = oj2.k(Profile.d()).a("incognito.incognito_reauthentication");
            this.b = true;
            incognitoReauthSettingSwitchPreference.Y(zA);
            this.b = false;
        }
    }
}
