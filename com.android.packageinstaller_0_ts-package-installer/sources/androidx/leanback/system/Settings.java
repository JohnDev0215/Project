package androidx.leanback.system;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Build;
import androidx.leanback.widget.ShadowOverlayContainer;

/* JADX INFO: loaded from: classes.dex */
public class Settings {
    private static final String ACTION_PARTNER_CUSTOMIZATION = "android.support.v17.leanback.action.PARTNER_CUSTOMIZATION";
    private static final boolean DEBUG = false;
    public static final String OUTLINE_CLIPPING_DISABLED = "OUTLINE_CLIPPING_DISABLED";
    public static final String PREFER_STATIC_SHADOWS = "PREFER_STATIC_SHADOWS";
    private static final String TAG = "Settings";
    private static Settings sInstance;
    private boolean mOutlineClippingDisabled;
    private boolean mPreferStaticShadows;

    public static Settings getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new Settings(context);
        }
        return sInstance;
    }

    private Settings(Context context) throws PackageManager.NameNotFoundException {
        Customizations customizations = getCustomizations(context);
        generateSetting(customizations, context);
    }

    public boolean preferStaticShadows() {
        return this.mPreferStaticShadows;
    }

    public boolean isOutlineClippingDisabled() {
        return this.mOutlineClippingDisabled;
    }

    public boolean getBoolean(String key) {
        return getOrSetBoolean(key, DEBUG, DEBUG);
    }

    public void setBoolean(String key, boolean value) {
        getOrSetBoolean(key, true, value);
    }

    boolean getOrSetBoolean(String key, boolean set, boolean value) {
        if (key.compareTo(PREFER_STATIC_SHADOWS) == 0) {
            if (!set) {
                return this.mPreferStaticShadows;
            }
            this.mPreferStaticShadows = value;
            return value;
        }
        if (key.compareTo(OUTLINE_CLIPPING_DISABLED) == 0) {
            if (!set) {
                return this.mOutlineClippingDisabled;
            }
            this.mOutlineClippingDisabled = value;
            return value;
        }
        throw new IllegalArgumentException("Invalid key");
    }

    private void generateSetting(Customizations customizations, Context context) {
        if (ShadowOverlayContainer.supportsDynamicShadow()) {
            this.mPreferStaticShadows = DEBUG;
            if (customizations != null) {
                this.mPreferStaticShadows = customizations.getBoolean("leanback_prefer_static_shadows", DEBUG);
            }
        } else {
            this.mPreferStaticShadows = true;
        }
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            boolean zIsLowRamDevice = activityManager.isLowRamDevice();
            this.mOutlineClippingDisabled = zIsLowRamDevice;
            if (customizations != null) {
                this.mOutlineClippingDisabled = customizations.getBoolean("leanback_outline_clipping_disabled", zIsLowRamDevice);
                return;
            }
            return;
        }
        this.mOutlineClippingDisabled = true;
    }

    static class Customizations {
        String mPackageName;
        Resources mResources;

        public Customizations(Resources resources, String packageName) {
            this.mResources = resources;
            this.mPackageName = packageName;
        }

        public boolean getBoolean(String resourceName, boolean defaultValue) {
            int resId = this.mResources.getIdentifier(resourceName, "bool", this.mPackageName);
            return resId > 0 ? this.mResources.getBoolean(resId) : defaultValue;
        }
    }

    private Customizations getCustomizations(Context context) throws PackageManager.NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        Intent intent = new Intent(ACTION_PARTNER_CUSTOMIZATION);
        Resources resources = null;
        String packageName = null;
        for (ResolveInfo info : pm.queryBroadcastReceivers(intent, 0)) {
            packageName = info.activityInfo.packageName;
            if (packageName != null && isSystemApp(info)) {
                try {
                    resources = pm.getResourcesForApplication(packageName);
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
            if (resources != null) {
                break;
            }
        }
        if (resources == null) {
            return null;
        }
        return new Customizations(resources, packageName);
    }

    private static boolean isSystemApp(ResolveInfo info) {
        if (info.activityInfo == null || (info.activityInfo.applicationInfo.flags & 1) == 0) {
            return DEBUG;
        }
        return true;
    }
}
