package org.chromium.chrome.browser;

import android.app.backup.BackupManager;
import android.content.Context;
import defpackage.gx;
import defpackage.ku2;
import defpackage.xz;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* JADX INFO: loaded from: classes.dex */
public class ChromeBackupWatcher {
    public final BackupManager a;

    public class a implements IdentityManager.a {
        public a() {
        }

        @Override // org.chromium.components.signin.identitymanager.IdentityManager.a
        public final void m(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
            ChromeBackupWatcher.this.onBackupPrefsChanged();
        }
    }

    public ChromeBackupWatcher() {
        Context context = xz.a;
        if (context == null) {
            return;
        }
        BackupManager backupManager = new BackupManager(context);
        this.a = backupManager;
        SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
        if (!sharedPreferencesManager.readBoolean("first_backup_done", false)) {
            ku2 ku2VarT = ku2.t();
            try {
                backupManager.dataChanged();
                ku2VarT.close();
                sharedPreferencesManager.l("first_backup_done", true);
            } catch (Throwable th) {
                try {
                    ku2VarT.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        sharedPreferencesManager.a(new gx(4, this));
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        IdentityManager identityManagerV = z7.v(profileD);
        identityManagerV.b.a(new a());
    }

    @CalledByNative
    public static ChromeBackupWatcher createChromeBackupWatcher() {
        return new ChromeBackupWatcher();
    }

    @CalledByNative
    public final void onBackupPrefsChanged() {
        ku2 ku2VarT = ku2.t();
        try {
            this.a.dataChanged();
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }
}
