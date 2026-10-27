package defpackage;

import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: BackupSigninProcessor.java */
/* JADX INFO: loaded from: classes.dex */
public final class oc {
    public static void a() {
        SharedPreferencesManager.getInstance().removeKey("first_run_signin_account_name");
        SharedPreferencesManager.getInstance().l("first_run_signin_complete", true);
    }
}
