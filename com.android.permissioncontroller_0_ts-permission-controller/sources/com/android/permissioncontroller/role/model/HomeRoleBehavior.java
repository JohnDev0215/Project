package com.android.permissioncontroller.role.model;

import android.app.role.RoleManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.CollectionUtils;
import com.android.permissioncontroller.role.ui.TwoTargetPreference;
import com.android.permissioncontroller.role.utils.UserUtils;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class HomeRoleBehavior implements RoleBehavior {
    private static final String LOG_TAG = HomeRoleBehavior.class.getSimpleName();

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isAvailableAsUser(Role role, UserHandle user, Context context) {
        return !UserUtils.isWorkProfile(user, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public String getFallbackHolder(Role role, Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = role.getRequiredComponents().get(0).getIntentFilterData().createIntent();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 851968);
        String packageName = null;
        int priority = Integer.MIN_VALUE;
        int resolveInfosSize = resolveInfos.size();
        for (int i = 0; i < resolveInfosSize; i++) {
            ResolveInfo resolveInfo = resolveInfos.get(i);
            if (!isSettingsApplication(resolveInfo.activityInfo.applicationInfo, context)) {
                if (resolveInfo.priority > priority) {
                    packageName = resolveInfo.activityInfo.packageName;
                    priority = resolveInfo.priority;
                } else if (resolveInfo.priority == priority) {
                    packageName = null;
                }
            }
        }
        return packageName;
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isVisibleAsUser(Role role, UserHandle user, Context context) {
        return VisibilityMixin.isVisible("config_showDefaultHome", context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public void preparePreferenceAsUser(Role role, TwoTargetPreference preference, UserHandle user, final Context context) {
        TwoTargetPreference.OnSecondTargetClickListener listener = null;
        RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
        String packageName = (String) CollectionUtils.firstOrNull(roleManager.getRoleHoldersAsUser(role.getName(), user));
        if (packageName != null) {
            final Intent intent = new Intent("android.intent.action.APPLICATION_PREFERENCES").setPackage(packageName).addFlags(268468224);
            PackageManager userPackageManager = UserUtils.getUserContext(context, user).getPackageManager();
            ActivityInfo activityInfo = intent.resolveActivityInfo(userPackageManager, 0);
            if (activityInfo != null && activityInfo.exported) {
                listener = new TwoTargetPreference.OnSecondTargetClickListener() { // from class: com.android.permissioncontroller.role.model.-$$Lambda$HomeRoleBehavior$S5OZpB2P2tqZkX9u99-7C8fBAUc
                    @Override // com.android.permissioncontroller.role.ui.TwoTargetPreference.OnSecondTargetClickListener
                    public final void onSecondTargetClick(TwoTargetPreference twoTargetPreference) {
                        HomeRoleBehavior.lambda$preparePreferenceAsUser$0(context, intent, twoTargetPreference);
                    }
                };
            }
        }
        preference.setOnSecondTargetClickListener(listener);
    }

    static /* synthetic */ void lambda$preparePreferenceAsUser$0(Context context, Intent intent, TwoTargetPreference preference2) {
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e(LOG_TAG, "Cannot start activity for home app preferences", e);
        }
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public boolean isApplicationVisibleAsUser(Role role, ApplicationInfo applicationInfo, UserHandle user, Context context) {
        return !isSettingsApplication(applicationInfo, context);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public void prepareApplicationPreferenceAsUser(Role role, Preference preference, ApplicationInfo applicationInfo, UserHandle user, Context context) {
        boolean missingWorkProfileSupport = isMissingWorkProfileSupport(applicationInfo, context);
        preference.setEnabled(!missingWorkProfileSupport);
        preference.setSummary(missingWorkProfileSupport ? context.getString(R.string.home_missing_work_profile_support) : null);
    }

    private boolean isMissingWorkProfileSupport(ApplicationInfo applicationInfo, Context context) {
        boolean hasWorkProfile = UserUtils.getWorkProfile(context) != null;
        if (!hasWorkProfile) {
            return false;
        }
        boolean isWorkProfileSupported = applicationInfo.targetSdkVersion >= 21;
        return !isWorkProfileSupported;
    }

    private boolean isSettingsApplication(ApplicationInfo applicationInfo, Context context) {
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfo = packageManager.resolveActivity(new Intent("android.settings.SETTINGS"), 851968);
        if (resolveInfo == null || resolveInfo.activityInfo == null) {
            return false;
        }
        return Objects.equals(applicationInfo.packageName, resolveInfo.activityInfo.packageName);
    }

    @Override // com.android.permissioncontroller.role.model.RoleBehavior
    public void onHolderSelectedAsUser(Role role, String packageName, UserHandle user, Context context) {
        Intent intent = new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME").setFlags(268435456);
        context.startActivity(intent);
    }
}
