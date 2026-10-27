package com.android.car.developeroptions.applications.defaultapps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.GearPreference;
import com.android.settingslib.TwoTargetPreference;
import com.android.settingslib.applications.DefaultAppInfo;
import com.android.settingslib.core.AbstractPreferenceController;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultAppPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    protected final PackageManager mPackageManager;
    protected int mUserId;
    protected final UserManager mUserManager;

    protected abstract DefaultAppInfo getDefaultAppInfo();

    protected abstract Intent getSettingIntent(DefaultAppInfo defaultAppInfo);

    public DefaultAppPreferenceController(Context context) {
        super(context);
        this.mPackageManager = context.getPackageManager();
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mUserId = UserHandle.myUserId();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        DefaultAppInfo defaultAppInfo = getDefaultAppInfo();
        CharSequence defaultAppLabel = getDefaultAppLabel();
        if (preference instanceof TwoTargetPreference) {
            ((TwoTargetPreference) preference).setIconSize(1);
        }
        if (!TextUtils.isEmpty(defaultAppLabel)) {
            preference.setSummary(defaultAppLabel);
            Utils.setSafeIcon(preference, getDefaultAppIcon());
        } else {
            Log.d("DefaultAppPrefControl", "No default app");
            preference.setSummary(R.string.app_list_preference_none);
            preference.setIcon((Drawable) null);
        }
        mayUpdateGearIcon(defaultAppInfo, preference);
    }

    private void mayUpdateGearIcon(DefaultAppInfo defaultAppInfo, Preference preference) {
        if (preference instanceof GearPreference) {
            final Intent settingIntent = getSettingIntent(defaultAppInfo);
            if (settingIntent != null) {
                ((GearPreference) preference).setOnGearClickListener(new GearPreference.OnGearClickListener() { // from class: com.android.car.developeroptions.applications.defaultapps.-$$Lambda$DefaultAppPreferenceController$2GxlHzi8vDU0W4PhcZdLnPozQMU
                    @Override // com.android.car.developeroptions.widget.GearPreference.OnGearClickListener
                    public final void onGearClick(GearPreference gearPreference) {
                        this.f$0.lambda$mayUpdateGearIcon$0$DefaultAppPreferenceController(settingIntent, gearPreference);
                    }
                });
            } else {
                ((GearPreference) preference).setOnGearClickListener(null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$mayUpdateGearIcon$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$mayUpdateGearIcon$0$DefaultAppPreferenceController(Intent intent, GearPreference gearPreference) {
        startActivity(intent);
    }

    protected void startActivity(Intent intent) {
        this.mContext.startActivity(intent);
    }

    public Drawable getDefaultAppIcon() {
        DefaultAppInfo defaultAppInfo;
        if (isAvailable() && (defaultAppInfo = getDefaultAppInfo()) != null) {
            return defaultAppInfo.loadIcon();
        }
        return null;
    }

    public CharSequence getDefaultAppLabel() {
        DefaultAppInfo defaultAppInfo;
        if (isAvailable() && (defaultAppInfo = getDefaultAppInfo()) != null) {
            return defaultAppInfo.loadLabel();
        }
        return null;
    }
}
