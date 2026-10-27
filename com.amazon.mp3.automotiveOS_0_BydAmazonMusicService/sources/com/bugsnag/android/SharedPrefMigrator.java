package com.bugsnag.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.music.inappmessaging.internal.model.Splash;

/* JADX INFO: compiled from: SharedPrefMigrator.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\rR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/bugsnag/android/SharedPrefMigrator;", "Lcom/bugsnag/android/DeviceIdPersistence;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "deleteLegacyPrefs", "", "hasPrefs", "", "loadDeviceId", "", "requestCreateIfDoesNotExist", "loadUser", "Lcom/bugsnag/android/User;", Splash.PARAMS_DEVICE_ID, "Companion", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class SharedPrefMigrator implements DeviceIdPersistence {
    private final SharedPreferences prefs;

    public SharedPrefMigrator(Context context) {
        this.prefs = context.getSharedPreferences("com.bugsnag.android", 0);
    }

    @Override // com.bugsnag.android.DeviceIdPersistence
    public String loadDeviceId(boolean requestCreateIfDoesNotExist) {
        return this.prefs.getString("install.iud", null);
    }

    public final User loadUser(String deviceId) {
        return new User(this.prefs.getString("user.id", deviceId), this.prefs.getString("user.email", null), this.prefs.getString("user.name", null));
    }

    public final boolean hasPrefs() {
        return this.prefs.contains("install.iud");
    }

    @SuppressLint({"ApplySharedPref"})
    public final void deleteLegacyPrefs() {
        if (hasPrefs()) {
            this.prefs.edit().clear().commit();
        }
    }
}
