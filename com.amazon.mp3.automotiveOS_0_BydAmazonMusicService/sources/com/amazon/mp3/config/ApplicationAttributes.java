package com.amazon.mp3.config;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.os.EnvironmentCompat;
import com.amazon.mp3.config.UpdateCheck;
import com.amazon.mp3.util.Log;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public final class ApplicationAttributes {
    private static final String TAG = "ApplicationAttributes";
    private static volatile ApplicationAttributes sInstance;
    private final Context mContext;

    public String getPlatformName() {
        return "Android Market";
    }

    private ApplicationAttributes(final Context context) {
        this.mContext = context;
    }

    public static ApplicationAttributes getInstance(final Context context) {
        ApplicationAttributes applicationAttributes = sInstance;
        if (applicationAttributes == null) {
            synchronized (ApplicationAttributes.class) {
                applicationAttributes = sInstance;
                if (applicationAttributes == null) {
                    applicationAttributes = new ApplicationAttributes(context);
                    sInstance = applicationAttributes;
                }
            }
        }
        return applicationAttributes;
    }

    public String getVersion() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.warning(TAG, "getVersion threw NameNotFoundException", e);
            return "";
        } catch (RuntimeException e2) {
            Log.warning(TAG, "getVersion threw random RuntimeException", e2);
            return "";
        }
    }

    public String getVersionShort() {
        try {
            UpdateCheck.Version version = UpdateCheck.Version.parse(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionName);
            return String.format(Locale.US, "%d.%d", Integer.valueOf(version.getMajor()), Integer.valueOf(version.getMinor()));
        } catch (PackageManager.NameNotFoundException e) {
            Log.warning(TAG, "getVersionShort threw NameNotFoundException", e);
            return EnvironmentCompat.MEDIA_UNKNOWN;
        } catch (RuntimeException unused) {
            Log.warning(TAG, "getVersionShort threw random RuntimeException");
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }

    public int getVersionCode() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.warning(TAG, "getVersionCode threw NameNotFoundException", e);
            return 0;
        } catch (RuntimeException e2) {
            Log.warning(TAG, "getVersionCode threw random RuntimeException", e2);
            return 0;
        }
    }

    public long getLastUpdateTime() {
        try {
            return this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            Log.warning(TAG, "getLastUpdateTime threw NameNotFoundException", e);
            return 0L;
        } catch (RuntimeException e2) {
            Log.warning(TAG, "getLastUpdateTime threw random RuntimeException", e2);
            return 0L;
        }
    }

    public String toString() {
        return ApplicationAttributes.class.getSimpleName() + " { version: " + getVersion() + ",  versionCode: " + getVersionCode() + ",  versionShort: " + getVersionShort() + ",  platformName: " + getPlatformName() + " }";
    }
}
