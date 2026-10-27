package com.android.car.settings.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.car.apps.common.util.Themes;
import com.android.car.settings.R;
import com.android.car.ui.preference.CarUiPreference;
import com.android.settingslib.drawer.CategoryKey;
import com.android.settingslib.drawer.TileUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class ExtraSettingsLoader {
    private static final Logger LOG = new Logger((Class<?>) ExtraSettingsLoader.class);
    private static final String META_DATA_PREFERENCE_CATEGORY = "com.android.settings.category";
    private final Context mContext;
    private PackageManager mPm;
    private Map<Preference, Bundle> mPreferenceBundleMap = new HashMap();

    public ExtraSettingsLoader(Context context) {
        this.mContext = context;
        this.mPm = context.getPackageManager();
    }

    void setPackageManager(PackageManager pm) {
        this.mPm = pm;
    }

    public Map<Preference, Bundle> loadPreferences(Intent intent) {
        Drawable icon;
        List<ResolveInfo> results;
        String str = TileUtils.META_DATA_PREFERENCE_KEYHINT;
        List<ResolveInfo> results2 = this.mPm.queryIntentActivitiesAsUser(intent, 128, ActivityManager.getCurrentUser());
        String extraCategory = intent.getStringExtra(META_DATA_PREFERENCE_CATEGORY);
        for (ResolveInfo resolved : results2) {
            if (resolved.system) {
                String key = null;
                String title = null;
                String summary = null;
                String category = null;
                ActivityInfo activityInfo = resolved.activityInfo;
                Bundle metaData = activityInfo.metaData;
                try {
                    Resources res = this.mPm.getResourcesForApplication(activityInfo.packageName);
                    if (metaData.containsKey(str)) {
                        key = extractMetaDataString(metaData, str, res);
                    }
                    if (!metaData.containsKey(TileUtils.META_DATA_PREFERENCE_TITLE_URI)) {
                        title = extractMetaDataString(metaData, TileUtils.META_DATA_PREFERENCE_TITLE, res);
                        if (TextUtils.isEmpty(title)) {
                            LOG.d("no title.");
                            title = activityInfo.loadLabel(this.mPm).toString();
                        }
                    }
                    if (!metaData.containsKey(TileUtils.META_DATA_PREFERENCE_SUMMARY_URI)) {
                        summary = extractMetaDataString(metaData, TileUtils.META_DATA_PREFERENCE_SUMMARY, res);
                        if (TextUtils.isEmpty(summary)) {
                            LOG.d("no description.");
                        }
                    }
                    category = extractMetaDataString(metaData, META_DATA_PREFERENCE_CATEGORY, res);
                    if (TextUtils.isEmpty(category)) {
                        LOG.d("no category.");
                    }
                } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
                    LOG.d("Couldn't find info", e);
                }
                if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_ICON)) {
                    int iconRes = metaData.getInt(TileUtils.META_DATA_PREFERENCE_ICON);
                    icon = ExtraSettingsUtil.loadDrawableFromPackage(this.mContext, activityInfo.packageName, iconRes);
                } else if (metaData.containsKey(TileUtils.META_DATA_PREFERENCE_ICON_URI)) {
                    icon = null;
                } else {
                    icon = this.mContext.getDrawable(R.drawable.ic_settings_gear);
                    LOG.d("use default icon.");
                }
                String str2 = str;
                Intent extraSettingIntent = new Intent().setClassName(activityInfo.packageName, activityInfo.name);
                if (category == null) {
                    category = CategoryKey.CATEGORY_DEVICE;
                }
                if (TextUtils.equals(extraCategory, category)) {
                    CarUiPreference preference = new CarUiPreference(this.mContext);
                    preference.setTitle(title);
                    preference.setSummary(summary);
                    if (key != null) {
                        preference.setKey(key);
                    }
                    if (icon != null) {
                        preference.setIcon(icon);
                        if (ExtraSettingsUtil.isIconTintable(metaData)) {
                            results = results2;
                            preference.getIcon().setTintList(Themes.getAttrColorStateList(this.mContext, R.attr.iconColor));
                        } else {
                            results = results2;
                        }
                    } else {
                        results = results2;
                    }
                    preference.setIntent(extraSettingIntent);
                    this.mPreferenceBundleMap.put(preference, metaData);
                    str = str2;
                    results2 = results;
                } else {
                    str = str2;
                }
            }
        }
        return this.mPreferenceBundleMap;
    }

    private String extractMetaDataString(Bundle metaData, String key, Resources res) {
        if (metaData.containsKey(key)) {
            if (metaData.get(key) instanceof Integer) {
                return res.getString(metaData.getInt(key));
            }
            return metaData.getString(key);
        }
        return null;
    }
}
