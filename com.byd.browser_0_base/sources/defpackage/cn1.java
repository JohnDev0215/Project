package defpackage;

import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.R$string;
import org.chromium.components.permissions.nfc.NfcSystemLevelSetting;

/* JADX INFO: compiled from: NfcCategory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cn1 extends yn2 {
    public cn1(Profile profile) {
        super(13, "", profile);
    }

    @Override // defpackage.yn2
    public final boolean e() {
        return NfcSystemLevelSetting.isNfcSystemLevelSettingEnabled();
    }

    @Override // defpackage.yn2
    public final Intent h() {
        Intent intent = new Intent("android.settings.NFC_SETTINGS");
        if (intent.resolveActivity(xz.a.getPackageManager()) == null) {
            return null;
        }
        return intent;
    }

    @Override // defpackage.yn2
    public final String i(Context context) {
        return context.getResources().getString(R$string.android_nfc_off_globally);
    }

    @Override // defpackage.yn2
    public final String j(Context context) {
        return context.getResources().getString(R$string.android_nfc_unsupported);
    }

    @Override // defpackage.yn2
    public final boolean o() {
        return NfcSystemLevelSetting.isNfcAccessPossible();
    }
}
