package com.android.settingslib;

import android.R;
import android.app.AppGlobals;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.UserInfo;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.TextView;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.RestrictedLockUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedLockUtilsInternal extends RestrictedLockUtils {
    static Proxy sProxy = new Proxy();

    /* JADX INFO: Access modifiers changed from: private */
    interface LockSettingCheck {
        boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i);
    }

    public static Drawable getRestrictedPadlock(Context context) {
        Drawable drawable = context.getDrawable(R.drawable.ic_info);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.config_restrictedIconSize);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.colorAccent});
        int color = typedArrayObtainStyledAttributes.getColor(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        drawable.setTint(color);
        drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
        return drawable;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfRestrictionEnforced(Context context, String str, int i) {
        if (((DevicePolicyManager) context.getSystemService("device_policy")) == null) {
            return null;
        }
        UserManager userManager = UserManager.get(context);
        List userRestrictionSources = userManager.getUserRestrictionSources(str, UserHandle.of(i));
        if (userRestrictionSources.isEmpty()) {
            return null;
        }
        if (userRestrictionSources.size() > 1) {
            return RestrictedLockUtils.EnforcedAdmin.createDefaultEnforcedAdminWithRestriction(str);
        }
        int userRestrictionSource = ((UserManager.EnforcingUser) userRestrictionSources.get(0)).getUserRestrictionSource();
        int identifier = ((UserManager.EnforcingUser) userRestrictionSources.get(0)).getUserHandle().getIdentifier();
        if (userRestrictionSource != 4) {
            if (userRestrictionSource != 2) {
                return null;
            }
            if (identifier == i) {
                return getDeviceOwner(context, str);
            }
            return RestrictedLockUtils.EnforcedAdmin.createDefaultEnforcedAdminWithRestriction(str);
        }
        if (identifier == i) {
            return getProfileOwner(context, str, identifier);
        }
        UserInfo profileParent = userManager.getProfileParent(identifier);
        if (profileParent != null && profileParent.id == i) {
            return getProfileOwner(context, str, identifier);
        }
        return RestrictedLockUtils.EnforcedAdmin.createDefaultEnforcedAdminWithRestriction(str);
    }

    public static boolean hasBaseUserRestriction(Context context, String str, int i) {
        return ((UserManager) context.getSystemService("user")).hasBaseUserRestriction(str, UserHandle.of(i));
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfKeyguardFeaturesDisabled(Context context, final int i, final int i2) {
        LockSettingCheck lockSettingCheck = new LockSettingCheck() { // from class: com.android.settingslib.-$$Lambda$RestrictedLockUtilsInternal$k8iDcwhE4SvyxIn63ehYr-tUNvI
            @Override // com.android.settingslib.RestrictedLockUtilsInternal.LockSettingCheck
            public final boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i3) {
                return RestrictedLockUtilsInternal.lambda$checkIfKeyguardFeaturesDisabled$0(i2, i, devicePolicyManager, componentName, i3);
            }
        };
        if (UserManager.get(context).getUserInfo(i2).isManagedProfile()) {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
            return findEnforcedAdmin(devicePolicyManager.getActiveAdminsAsUser(i2), devicePolicyManager, i2, lockSettingCheck);
        }
        return checkForLockSetting(context, i2, lockSettingCheck);
    }

    static /* synthetic */ boolean lambda$checkIfKeyguardFeaturesDisabled$0(int i, int i2, DevicePolicyManager devicePolicyManager, ComponentName componentName, int i3) {
        int keyguardDisabledFeatures = devicePolicyManager.getKeyguardDisabledFeatures(componentName, i3);
        if (i3 != i) {
            keyguardDisabledFeatures &= 438;
        }
        return (keyguardDisabledFeatures & i2) != 0;
    }

    private static UserHandle getUserHandleOf(int i) {
        if (i == -10000) {
            return null;
        }
        return UserHandle.of(i);
    }

    private static RestrictedLockUtils.EnforcedAdmin findEnforcedAdmin(List<ComponentName> list, DevicePolicyManager devicePolicyManager, int i, LockSettingCheck lockSettingCheck) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = null;
        if (list == null) {
            return null;
        }
        UserHandle userHandleOf = getUserHandleOf(i);
        for (ComponentName componentName : list) {
            if (lockSettingCheck.isEnforcing(devicePolicyManager, componentName, i)) {
                if (enforcedAdmin == null) {
                    enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(componentName, userHandleOf);
                } else {
                    return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                }
            }
        }
        return enforcedAdmin;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfUninstallBlocked(Context context, String str, int i) {
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = checkIfRestrictionEnforced(context, "no_control_apps", i);
        if (enforcedAdminCheckIfRestrictionEnforced != null) {
            return enforcedAdminCheckIfRestrictionEnforced;
        }
        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced2 = checkIfRestrictionEnforced(context, "no_uninstall_apps", i);
        if (enforcedAdminCheckIfRestrictionEnforced2 != null) {
            return enforcedAdminCheckIfRestrictionEnforced2;
        }
        try {
            if (AppGlobals.getPackageManager().getBlockUninstallForUser(str, i)) {
                return RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfApplicationIsSuspended(Context context, String str, int i) {
        try {
            if (AppGlobals.getPackageManager().isPackageSuspendedForUser(str, i)) {
                return RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
            }
            return null;
        } catch (RemoteException | IllegalArgumentException unused) {
            return null;
        }
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfInputMethodDisallowed(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null) {
            return null;
        }
        RestrictedLockUtils.EnforcedAdmin profileOrDeviceOwner = RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
        boolean zIsInputMethodPermittedByAdmin = profileOrDeviceOwner != null ? devicePolicyManager.isInputMethodPermittedByAdmin(profileOrDeviceOwner.component, str, i) : true;
        int managedProfileId = getManagedProfileId(context, i);
        RestrictedLockUtils.EnforcedAdmin profileOrDeviceOwner2 = RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(managedProfileId));
        boolean zIsInputMethodPermittedByAdmin2 = profileOrDeviceOwner2 != null ? devicePolicyManager.isInputMethodPermittedByAdmin(profileOrDeviceOwner2.component, str, managedProfileId) : true;
        if (!zIsInputMethodPermittedByAdmin && !zIsInputMethodPermittedByAdmin2) {
            return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
        }
        if (!zIsInputMethodPermittedByAdmin) {
            return profileOrDeviceOwner;
        }
        if (zIsInputMethodPermittedByAdmin2) {
            return null;
        }
        return profileOrDeviceOwner2;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfRemoteContactSearchDisallowed(Context context, int i) {
        RestrictedLockUtils.EnforcedAdmin profileOwner;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null || (profileOwner = getProfileOwner(context, i)) == null) {
            return null;
        }
        UserHandle userHandleOf = UserHandle.of(i);
        if (devicePolicyManager.getCrossProfileContactsSearchDisabled(userHandleOf) && devicePolicyManager.getCrossProfileCallerIdDisabled(userHandleOf)) {
            return profileOwner;
        }
        return null;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfAccessibilityServiceDisallowed(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null) {
            return null;
        }
        RestrictedLockUtils.EnforcedAdmin profileOrDeviceOwner = RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
        boolean zIsAccessibilityServicePermittedByAdmin = profileOrDeviceOwner != null ? devicePolicyManager.isAccessibilityServicePermittedByAdmin(profileOrDeviceOwner.component, str, i) : true;
        int managedProfileId = getManagedProfileId(context, i);
        RestrictedLockUtils.EnforcedAdmin profileOrDeviceOwner2 = RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(managedProfileId));
        boolean zIsAccessibilityServicePermittedByAdmin2 = profileOrDeviceOwner2 != null ? devicePolicyManager.isAccessibilityServicePermittedByAdmin(profileOrDeviceOwner2.component, str, managedProfileId) : true;
        if (!zIsAccessibilityServicePermittedByAdmin && !zIsAccessibilityServicePermittedByAdmin2) {
            return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
        }
        if (!zIsAccessibilityServicePermittedByAdmin) {
            return profileOrDeviceOwner;
        }
        if (zIsAccessibilityServicePermittedByAdmin2) {
            return null;
        }
        return profileOrDeviceOwner2;
    }

    private static int getManagedProfileId(Context context, int i) {
        for (UserInfo userInfo : ((UserManager) context.getSystemService("user")).getProfiles(i)) {
            if (userInfo.id != i && userInfo.isManagedProfile()) {
                return userInfo.id;
            }
        }
        return -10000;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfAccountManagementDisabled(Context context, String str, int i) {
        if (str == null) {
            return null;
        }
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (!context.getPackageManager().hasSystemFeature("android.software.device_admin") || devicePolicyManager == null) {
            return null;
        }
        String[] accountTypesWithManagementDisabledAsUser = devicePolicyManager.getAccountTypesWithManagementDisabledAsUser(i);
        int length = accountTypesWithManagementDisabledAsUser.length;
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            if (str.equals(accountTypesWithManagementDisabledAsUser[i2])) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            return RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
        }
        return null;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfMeteredDataRestricted(Context context, String str, int i) {
        RestrictedLockUtils.EnforcedAdmin profileOrDeviceOwner = RestrictedLockUtils.getProfileOrDeviceOwner(context, getUserHandleOf(i));
        if (profileOrDeviceOwner != null && ((DevicePolicyManager) context.getSystemService("device_policy")).isMeteredDataDisabledPackageForUser(profileOrDeviceOwner.component, str, i)) {
            return profileOrDeviceOwner;
        }
        return null;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfPasswordQualityIsSet(Context context, int i) {
        $$Lambda$RestrictedLockUtilsInternal$yvS34yJS2kpTNeXUsuaEu8yH1g __lambda_restrictedlockutilsinternal_yvs34yjs2kptnexusuaeu8yh1g = new LockSettingCheck() { // from class: com.android.settingslib.-$$Lambda$RestrictedLockUtilsInternal$yvS34yJS2kpTNeXUsuaEu-8yH1g
            @Override // com.android.settingslib.RestrictedLockUtilsInternal.LockSettingCheck
            public final boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i2) {
                return RestrictedLockUtilsInternal.lambda$checkIfPasswordQualityIsSet$1(devicePolicyManager, componentName, i2);
            }
        };
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = null;
        if (devicePolicyManager == null) {
            return null;
        }
        if (sProxy.isSeparateProfileChallengeEnabled(new LockPatternUtils(context), i)) {
            List<ComponentName> activeAdminsAsUser = devicePolicyManager.getActiveAdminsAsUser(i);
            if (activeAdminsAsUser == null) {
                return null;
            }
            UserHandle userHandleOf = getUserHandleOf(i);
            for (ComponentName componentName : activeAdminsAsUser) {
                if (__lambda_restrictedlockutilsinternal_yvs34yjs2kptnexusuaeu8yh1g.isEnforcing(devicePolicyManager, componentName, i)) {
                    if (enforcedAdmin == null) {
                        enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(componentName, userHandleOf);
                    } else {
                        return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                    }
                }
            }
            return enforcedAdmin;
        }
        return checkForLockSetting(context, i, __lambda_restrictedlockutilsinternal_yvs34yjs2kptnexusuaeu8yh1g);
    }

    static /* synthetic */ boolean lambda$checkIfPasswordQualityIsSet$1(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i) {
        return devicePolicyManager.getPasswordQuality(componentName, i) > 0;
    }

    public static RestrictedLockUtils.EnforcedAdmin checkIfMaximumTimeToLockIsSet(Context context) {
        return checkForLockSetting(context, UserHandle.myUserId(), new LockSettingCheck() { // from class: com.android.settingslib.-$$Lambda$RestrictedLockUtilsInternal$GXYFzBzGab6v5GcOkljXViw5O7I
            @Override // com.android.settingslib.RestrictedLockUtilsInternal.LockSettingCheck
            public final boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i) {
                return RestrictedLockUtilsInternal.lambda$checkIfMaximumTimeToLockIsSet$2(devicePolicyManager, componentName, i);
            }
        });
    }

    static /* synthetic */ boolean lambda$checkIfMaximumTimeToLockIsSet$2(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i) {
        return devicePolicyManager.getMaximumTimeToLock(componentName, i) > 0;
    }

    private static RestrictedLockUtils.EnforcedAdmin checkForLockSetting(Context context, int i, LockSettingCheck lockSettingCheck) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        RestrictedLockUtils.EnforcedAdmin enforcedAdmin = null;
        if (devicePolicyManager == null) {
            return null;
        }
        LockPatternUtils lockPatternUtils = new LockPatternUtils(context);
        for (UserInfo userInfo : UserManager.get(context).getProfiles(i)) {
            List<ComponentName> activeAdminsAsUser = devicePolicyManager.getActiveAdminsAsUser(userInfo.id);
            if (activeAdminsAsUser != null) {
                UserHandle userHandleOf = getUserHandleOf(userInfo.id);
                boolean zIsSeparateProfileChallengeEnabled = sProxy.isSeparateProfileChallengeEnabled(lockPatternUtils, userInfo.id);
                for (ComponentName componentName : activeAdminsAsUser) {
                    if (zIsSeparateProfileChallengeEnabled || !lockSettingCheck.isEnforcing(devicePolicyManager, componentName, userInfo.id)) {
                        if (userInfo.isManagedProfile() && lockSettingCheck.isEnforcing(sProxy.getParentProfileInstance(devicePolicyManager, userInfo), componentName, userInfo.id)) {
                            if (enforcedAdmin == null) {
                                enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(componentName, userHandleOf);
                            } else {
                                return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                            }
                        }
                    } else if (enforcedAdmin == null) {
                        enforcedAdmin = new RestrictedLockUtils.EnforcedAdmin(componentName, userHandleOf);
                    } else {
                        return RestrictedLockUtils.EnforcedAdmin.MULTIPLE_ENFORCED_ADMIN;
                    }
                }
            }
        }
        return enforcedAdmin;
    }

    public static RestrictedLockUtils.EnforcedAdmin getDeviceOwner(Context context) {
        return getDeviceOwner(context, null);
    }

    private static RestrictedLockUtils.EnforcedAdmin getDeviceOwner(Context context, String str) {
        ComponentName deviceOwnerComponentOnAnyUser;
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        if (devicePolicyManager == null || (deviceOwnerComponentOnAnyUser = devicePolicyManager.getDeviceOwnerComponentOnAnyUser()) == null) {
            return null;
        }
        return new RestrictedLockUtils.EnforcedAdmin(deviceOwnerComponentOnAnyUser, str, devicePolicyManager.getDeviceOwnerUser());
    }

    private static RestrictedLockUtils.EnforcedAdmin getProfileOwner(Context context, int i) {
        return getProfileOwner(context, null, i);
    }

    private static RestrictedLockUtils.EnforcedAdmin getProfileOwner(Context context, String str, int i) {
        DevicePolicyManager devicePolicyManager;
        ComponentName profileOwnerAsUser;
        if (i == -10000 || (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) == null || (profileOwnerAsUser = devicePolicyManager.getProfileOwnerAsUser(i)) == null) {
            return null;
        }
        return new RestrictedLockUtils.EnforcedAdmin(profileOwnerAsUser, str, getUserHandleOf(i));
    }

    public static void setMenuItemAsDisabledByAdmin(final Context context, MenuItem menuItem, final RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(menuItem.getTitle());
        removeExistingRestrictedSpans(spannableStringBuilder);
        if (enforcedAdmin != null) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(context.getColor(R$color.disabled_text_color)), 0, spannableStringBuilder.length(), 33);
            spannableStringBuilder.append(" ", new RestrictedLockImageSpan(context), 33);
            menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // from class: com.android.settingslib.RestrictedLockUtilsInternal.1
                @Override // android.view.MenuItem.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem menuItem2) {
                    RestrictedLockUtils.sendShowAdminSupportDetailsIntent(context, enforcedAdmin);
                    return true;
                }
            });
        } else {
            menuItem.setOnMenuItemClickListener(null);
        }
        menuItem.setTitle(spannableStringBuilder);
    }

    private static void removeExistingRestrictedSpans(SpannableStringBuilder spannableStringBuilder) {
        int length = spannableStringBuilder.length();
        for (RestrictedLockImageSpan restrictedLockImageSpan : (RestrictedLockImageSpan[]) spannableStringBuilder.getSpans(length - 1, length, RestrictedLockImageSpan.class)) {
            int spanStart = spannableStringBuilder.getSpanStart(restrictedLockImageSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(restrictedLockImageSpan);
            spannableStringBuilder.removeSpan(restrictedLockImageSpan);
            spannableStringBuilder.delete(spanStart, spanEnd);
        }
        for (ForegroundColorSpan foregroundColorSpan : (ForegroundColorSpan[]) spannableStringBuilder.getSpans(0, length, ForegroundColorSpan.class)) {
            spannableStringBuilder.removeSpan(foregroundColorSpan);
        }
    }

    public static boolean isAdminInCurrentUserOrProfile(Context context, ComponentName componentName) {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy");
        Iterator it = UserManager.get(context).getProfiles(UserHandle.myUserId()).iterator();
        while (it.hasNext()) {
            if (devicePolicyManager.isAdminActiveAsUser(componentName, ((UserInfo) it.next()).id)) {
                return true;
            }
        }
        return false;
    }

    public static void setTextViewAsDisabledByAdmin(Context context, TextView textView, boolean z) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
        removeExistingRestrictedSpans(spannableStringBuilder);
        if (z) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Utils.getDisabled(context, textView.getCurrentTextColor())), 0, spannableStringBuilder.length(), 33);
            textView.setCompoundDrawables(null, null, getRestrictedPadlock(context), null);
            textView.setCompoundDrawablePadding(context.getResources().getDimensionPixelSize(R$dimen.restricted_icon_padding));
        } else {
            textView.setCompoundDrawables(null, null, null, null);
        }
        textView.setText(spannableStringBuilder);
    }

    static class Proxy {
        Proxy() {
        }

        public boolean isSeparateProfileChallengeEnabled(LockPatternUtils lockPatternUtils, int i) {
            return lockPatternUtils.isSeparateProfileChallengeEnabled(i);
        }

        public DevicePolicyManager getParentProfileInstance(DevicePolicyManager devicePolicyManager, UserInfo userInfo) {
            return devicePolicyManager.getParentProfileInstance(userInfo);
        }
    }
}
