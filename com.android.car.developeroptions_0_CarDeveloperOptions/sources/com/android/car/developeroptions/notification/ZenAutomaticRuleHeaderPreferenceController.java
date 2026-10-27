package com.android.car.developeroptions.notification;

import android.app.AutomaticZenRule;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.service.notification.ZenModeConfig;
import android.util.Slog;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class ZenAutomaticRuleHeaderPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    private EntityHeaderController mController;
    private final PreferenceFragmentCompat mFragment;
    private AutomaticZenRule mRule;

    @Override // com.android.car.developeroptions.notification.AbstractZenModePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "pref_app_header";
    }

    public ZenAutomaticRuleHeaderPreferenceController(Context context, PreferenceFragmentCompat preferenceFragmentCompat, Lifecycle lifecycle) {
        super(context, "pref_app_header", lifecycle);
        this.mFragment = preferenceFragmentCompat;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mRule != null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        PreferenceFragmentCompat preferenceFragmentCompat;
        if (this.mRule == null || (preferenceFragmentCompat = this.mFragment) == null) {
            return;
        }
        LayoutPreference layoutPreference = (LayoutPreference) preference;
        if (this.mController == null) {
            this.mController = EntityHeaderController.newInstance(preferenceFragmentCompat.getActivity(), this.mFragment, layoutPreference.findViewById(R.id.entity_header));
        }
        EntityHeaderController entityHeaderController = this.mController;
        entityHeaderController.setIcon(getIcon());
        entityHeaderController.setLabel(this.mRule.getName());
        entityHeaderController.setPackageName(this.mRule.getOwner().getPackageName());
        entityHeaderController.setUid(this.mContext.getUserId());
        entityHeaderController.setHasAppInfoLink(false);
        entityHeaderController.setButtonActions(2, 0);
        entityHeaderController.done(this.mFragment.getActivity(), this.mContext).findViewById(R.id.entity_header).setVisibility(0);
    }

    private Drawable getIcon() {
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mRule.getOwner().getPackageName(), 0);
            if (applicationInfo.isSystemApp()) {
                if (ZenModeConfig.isValidScheduleConditionId(this.mRule.getConditionId())) {
                    return this.mContext.getDrawable(R.drawable.ic_timelapse);
                }
                if (ZenModeConfig.isValidEventConditionId(this.mRule.getConditionId())) {
                    return this.mContext.getDrawable(R.drawable.ic_event);
                }
            }
            return applicationInfo.loadIcon(packageManager);
        } catch (PackageManager.NameNotFoundException unused) {
            Slog.w("PrefControllerMixin", "Unable to load icon - PackageManager.NameNotFoundException");
            return null;
        }
    }

    protected void onResume(AutomaticZenRule automaticZenRule, String str) {
        this.mRule = automaticZenRule;
    }
}
