package com.android.permissioncontroller.permission.ui.auto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.TwoStatePreference;
import com.android.car.ui.AlertDialogBuilder;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.auto.AutoSettingsFrameFragment;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.ui.auto.AutoTwoTargetPreference;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.PackageRemovalMonitor;
import com.android.permissioncontroller.permission.utils.SafetyNetLogger;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.RestrictedLockUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class AutoAppPermissionFragment extends AutoSettingsFrameFragment {
    static final int CHANGE_BACKGROUND = 2;
    static final int CHANGE_BOTH = 3;
    static final int CHANGE_FOREGROUND = 1;
    private static final String LOG_TAG = "AppPermissionFragment";
    private TwoStatePreference mAlwaysPermissionPreference;
    private TwoStatePreference mDenyPermissionPreference;
    private AutoTwoTargetPreference mDetailsPreference;
    private TwoStatePreference mForegroundOnlyPermissionPreference;
    private AppPermissionGroup mGroup;
    private boolean mHasConfirmedRevoke;
    private PackageRemovalMonitor mPackageRemovalMonitor;
    private PackageManager.OnPermissionsChangedListener mPermissionChangeListener;

    @Retention(RetentionPolicy.SOURCE)
    @interface ChangeTarget {
    }

    public static AutoAppPermissionFragment newInstance(String packageName, String permName, String groupName, UserHandle userHandle) {
        AutoAppPermissionFragment fragment = new AutoAppPermissionFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        if (groupName == null) {
            arguments.putString("android.intent.extra.PERMISSION_NAME", permName);
        } else {
            arguments.putString("android.intent.extra.PERMISSION_GROUP_NAME", groupName);
        }
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mHasConfirmedRevoke = false;
        AppPermissionGroup appPermissionGroup = getAppPermissionGroup();
        this.mGroup = appPermissionGroup;
        if (appPermissionGroup == null) {
            requireActivity().setResult(0);
            requireActivity().finish();
        } else {
            setHeaderLabel(requireContext().getString(R.string.app_permission_title, this.mGroup.getFullLabel()));
        }
    }

    private void setResult(int result) {
        Intent intent = new Intent().putExtra("com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED", requireArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME")).putExtra("com.android.permissioncontroller.extra.PERMISSION_RESULT", result);
        requireActivity().setResult(-1, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AppPermissionGroup getAppPermissionGroup() {
        Activity activity = requireActivity();
        Context context = getPreferenceManager().getContext();
        String packageName = requireArguments().getString("android.intent.extra.PACKAGE_NAME");
        String groupName = requireArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        if (groupName == null) {
            groupName = requireArguments().getString("android.intent.extra.PERMISSION_NAME");
        }
        PackageItemInfo groupInfo = Utils.getGroupInfo(groupName, context);
        List<PermissionInfo> groupPermInfos = Utils.getGroupPermissionInfos(groupName, context);
        if (groupInfo == null || groupPermInfos == null) {
            Log.i(LOG_TAG, "Illegal group: " + groupName);
            return null;
        }
        UserHandle userHandle = (UserHandle) requireArguments().getParcelable("android.intent.extra.USER");
        if (userHandle == null) {
            Log.e(LOG_TAG, "User handle is null");
            return null;
        }
        PackageInfo packageInfo = AutoPermissionsUtils.getPackageInfo(activity, packageName, userHandle);
        if (packageInfo == null) {
            Log.i(LOG_TAG, "PackageInfo is null");
            return null;
        }
        AppPermissionGroup group = AppPermissionGroup.create(context, packageInfo, groupInfo, groupPermInfos, false);
        if (group == null || !Utils.shouldShowPermission(context, group)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Illegal group: ");
            sb.append(group == null ? "null" : group.getName());
            Log.i(LOG_TAG, sb.toString());
            return null;
        }
        return group;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferenceScreen(getPreferenceManager().createPreferenceScreen(requireContext()));
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PreferenceScreen screen = getPreferenceScreen();
        screen.addPreference(AutoPermissionsUtils.createHeaderPreference(requireContext(), this.mGroup.getApp().applicationInfo));
        PreferenceGroup permissionSelector = new PreferenceCategory(requireContext());
        permissionSelector.setTitle(getString(R.string.app_permission_header, this.mGroup.getFullLabel()));
        screen.addPreference(permissionSelector);
        SelectedPermissionPreference selectedPermissionPreference = new SelectedPermissionPreference(requireContext());
        this.mAlwaysPermissionPreference = selectedPermissionPreference;
        selectedPermissionPreference.setTitle(R.string.app_permission_button_allow_always);
        permissionSelector.addPreference(this.mAlwaysPermissionPreference);
        SelectedPermissionPreference selectedPermissionPreference2 = new SelectedPermissionPreference(requireContext());
        this.mForegroundOnlyPermissionPreference = selectedPermissionPreference2;
        selectedPermissionPreference2.setTitle(R.string.app_permission_button_allow_foreground);
        permissionSelector.addPreference(this.mForegroundOnlyPermissionPreference);
        SelectedPermissionPreference selectedPermissionPreference3 = new SelectedPermissionPreference(requireContext());
        this.mDenyPermissionPreference = selectedPermissionPreference3;
        selectedPermissionPreference3.setTitle(R.string.app_permission_button_deny);
        permissionSelector.addPreference(this.mDenyPermissionPreference);
        AutoTwoTargetPreference autoTwoTargetPreference = new AutoTwoTargetPreference(requireContext());
        this.mDetailsPreference = autoTwoTargetPreference;
        screen.addPreference(autoTwoTargetPreference);
    }

    @Override // com.android.car.ui.preference.PreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        final Activity activity = requireActivity();
        this.mPermissionChangeListener = new PermissionChangeListener(this.mGroup.getApp().applicationInfo.uid);
        PackageManager pm = activity.getPackageManager();
        pm.addOnPermissionsChangeListener(this.mPermissionChangeListener);
        final String packageName = this.mGroup.getApp().packageName;
        PackageRemovalMonitor packageRemovalMonitor = new PackageRemovalMonitor(requireContext(), packageName) { // from class: com.android.permissioncontroller.permission.ui.auto.AutoAppPermissionFragment.1
            @Override // com.android.permissioncontroller.permission.utils.PackageRemovalMonitor
            public void onPackageRemoved() {
                Log.w(AutoAppPermissionFragment.LOG_TAG, packageName + " was uninstalled");
                activity.setResult(0);
                activity.finish();
            }
        };
        this.mPackageRemovalMonitor = packageRemovalMonitor;
        packageRemovalMonitor.register();
        try {
            activity.createPackageContextAsUser(packageName, 0, this.mGroup.getUser()).getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(LOG_TAG, packageName + " was uninstalled while this activity was stopped", e);
            activity.setResult(0);
            activity.finish();
        }
        this.mGroup = getAppPermissionGroup();
        updateUi();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PackageRemovalMonitor packageRemovalMonitor = this.mPackageRemovalMonitor;
        if (packageRemovalMonitor != null) {
            packageRemovalMonitor.unregister();
            this.mPackageRemovalMonitor = null;
        }
        if (this.mPermissionChangeListener != null) {
            requireActivity().getPackageManager().removeOnPermissionsChangeListener(this.mPermissionChangeListener);
            this.mPermissionChangeListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUi() {
        this.mDetailsPreference.setOnSecondTargetClickListener(null);
        this.mDetailsPreference.setVisible(false);
        if (this.mGroup.areRuntimePermissionsGranted()) {
            if (!this.mGroup.hasPermissionWithBackgroundMode() || (this.mGroup.getBackgroundPermissions() != null && this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted())) {
                setSelectedPermissionState(this.mAlwaysPermissionPreference);
            } else {
                setSelectedPermissionState(this.mForegroundOnlyPermissionPreference);
            }
        } else {
            setSelectedPermissionState(this.mDenyPermissionPreference);
        }
        this.mAlwaysPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$6K_SIK3wMDIzw0iFAhlWAn6I6_8
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$updateUi$0$AutoAppPermissionFragment(preference);
            }
        });
        this.mForegroundOnlyPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$k3x6bQgEuG5zYl5FTQe61qtQmfk
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$updateUi$1$AutoAppPermissionFragment(preference);
            }
        });
        this.mDenyPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$SkrnJt9dxN_Fch4kksfHB-0UZxg
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$updateUi$2$AutoAppPermissionFragment(preference);
            }
        });
        if (this.mGroup.hasPermissionWithBackgroundMode()) {
            if (this.mGroup.getBackgroundPermissions() == null) {
                this.mAlwaysPermissionPreference.setVisible(false);
            } else {
                this.mForegroundOnlyPermissionPreference.setVisible(true);
                this.mAlwaysPermissionPreference.setTitle(R.string.app_permission_button_allow_always);
            }
        } else {
            this.mForegroundOnlyPermissionPreference.setVisible(false);
            this.mAlwaysPermissionPreference.setTitle(R.string.app_permission_button_allow);
        }
        if (isSystemFixed() || isPolicyFullyFixed() || isForegroundDisabledByPolicy()) {
            this.mAlwaysPermissionPreference.setEnabled(false);
            this.mForegroundOnlyPermissionPreference.setEnabled(false);
            this.mDenyPermissionPreference.setEnabled(false);
            final RestrictedLockUtils.EnforcedAdmin admin = getAdmin();
            if (admin != null) {
                this.mDetailsPreference.setWidgetLayoutResource(R.layout.info_preference_widget);
                this.mDetailsPreference.setOnSecondTargetClickListener(new AutoTwoTargetPreference.OnSecondTargetClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$HM5zPQb8vVuUSlePlQLPUAn3Ilw
                    @Override // com.android.permissioncontroller.permission.ui.auto.AutoTwoTargetPreference.OnSecondTargetClickListener
                    public final void onSecondTargetClick(AutoTwoTargetPreference autoTwoTargetPreference) {
                        this.f$0.lambda$updateUi$3$AutoAppPermissionFragment(admin, autoTwoTargetPreference);
                    }
                });
            }
            updateDetailForFixedByPolicyPermissionGroup();
            return;
        }
        if (Utils.areGroupPermissionsIndividuallyControlled(requireContext(), this.mGroup.getName())) {
            this.mDetailsPreference.setWidgetLayoutResource(R.layout.settings_preference_widget);
            this.mDetailsPreference.setOnSecondTargetClickListener(new AutoTwoTargetPreference.OnSecondTargetClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$nz55HrALkqDnVpH1-HSRj_5nfcs
                @Override // com.android.permissioncontroller.permission.ui.auto.AutoTwoTargetPreference.OnSecondTargetClickListener
                public final void onSecondTargetClick(AutoTwoTargetPreference autoTwoTargetPreference) {
                    this.f$0.lambda$updateUi$4$AutoAppPermissionFragment(autoTwoTargetPreference);
                }
            });
            updateDetailForIndividuallyControlledPermissionGroup();
            return;
        }
        if (this.mGroup.hasPermissionWithBackgroundMode()) {
            if (this.mGroup.getBackgroundPermissions() == null) {
                this.mAlwaysPermissionPreference.setEnabled(false);
                this.mDenyPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$ORq1g0JiBKuXWCLfhBWUKEOj8Lw
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$5$AutoAppPermissionFragment(preference);
                    }
                });
                return;
            }
            if (isBackgroundPolicyFixed()) {
                this.mAlwaysPermissionPreference.setEnabled(false);
                setSelectedPermissionState(this.mForegroundOnlyPermissionPreference);
                this.mDenyPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$EaFWfUOxHy2u_vYdNaLf7_GNXXw
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$6$AutoAppPermissionFragment(preference);
                    }
                });
                updateDetailForFixedByPolicyPermissionGroup();
                return;
            }
            if (isForegroundPolicyFixed()) {
                this.mDenyPermissionPreference.setEnabled(false);
                this.mAlwaysPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$8cJ3M9A8ajfIo3PTwpwij-zgjrw
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$7$AutoAppPermissionFragment(preference);
                    }
                });
                this.mForegroundOnlyPermissionPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$INhEd4q_89x4AXdAo5B9aPqD6-k
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$8$AutoAppPermissionFragment(preference);
                    }
                });
                updateDetailForFixedByPolicyPermissionGroup();
            }
        }
    }

    public /* synthetic */ boolean lambda$updateUi$0$AutoAppPermissionFragment(Preference v) {
        setResult(0);
        return requestChange(true, 3);
    }

    public /* synthetic */ boolean lambda$updateUi$1$AutoAppPermissionFragment(Preference v) {
        setResult(1);
        requestChange(false, 2);
        requestChange(true, 1);
        return true;
    }

    public /* synthetic */ boolean lambda$updateUi$2$AutoAppPermissionFragment(Preference v) {
        setResult(2);
        return requestChange(false, 3);
    }

    public /* synthetic */ void lambda$updateUi$3$AutoAppPermissionFragment(RestrictedLockUtils.EnforcedAdmin admin, AutoTwoTargetPreference preference) {
        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), admin);
    }

    public /* synthetic */ void lambda$updateUi$4$AutoAppPermissionFragment(AutoTwoTargetPreference preference) {
        showAllPermissions(this.mGroup.getName());
    }

    public /* synthetic */ boolean lambda$updateUi$5$AutoAppPermissionFragment(Preference v) {
        return requestChange(false, 1);
    }

    public /* synthetic */ boolean lambda$updateUi$6$AutoAppPermissionFragment(Preference v) {
        return requestChange(false, 1);
    }

    public /* synthetic */ boolean lambda$updateUi$7$AutoAppPermissionFragment(Preference v) {
        return requestChange(true, 2);
    }

    public /* synthetic */ boolean lambda$updateUi$8$AutoAppPermissionFragment(Preference v) {
        return requestChange(false, 2);
    }

    private void setSelectedPermissionState(TwoStatePreference permissionState) {
        permissionState.setChecked(true);
        TwoStatePreference twoStatePreference = this.mAlwaysPermissionPreference;
        if (permissionState != twoStatePreference) {
            twoStatePreference.setChecked(false);
        }
        TwoStatePreference twoStatePreference2 = this.mForegroundOnlyPermissionPreference;
        if (permissionState != twoStatePreference2) {
            twoStatePreference2.setChecked(false);
        }
        TwoStatePreference twoStatePreference3 = this.mDenyPermissionPreference;
        if (permissionState != twoStatePreference3) {
            twoStatePreference3.setChecked(false);
        }
    }

    private boolean isSystemFixed() {
        return this.mGroup.isSystemFixed();
    }

    private boolean isForegroundPolicyFixed() {
        return this.mGroup.isPolicyFixed();
    }

    private boolean isBackgroundPolicyFixed() {
        return this.mGroup.getBackgroundPermissions() != null && this.mGroup.getBackgroundPermissions().isPolicyFixed();
    }

    private boolean isPolicyFullyFixed() {
        return isForegroundPolicyFixed() && (this.mGroup.getBackgroundPermissions() == null || isBackgroundPolicyFixed());
    }

    private boolean isForegroundDisabledByPolicy() {
        return isForegroundPolicyFixed() && !this.mGroup.areRuntimePermissionsGranted();
    }

    private RestrictedLockUtils.EnforcedAdmin getAdmin() {
        return RestrictedLockUtils.getProfileOrDeviceOwner(getContext(), this.mGroup.getUser());
    }

    private void updateDetailForIndividuallyControlledPermissionGroup() {
        int resId;
        int revokedCount = 0;
        List<Permission> permissions = this.mGroup.getPermissions();
        for (Permission permission : permissions) {
            if (!permission.isGrantedIncludingAppOp()) {
                revokedCount++;
            }
        }
        if (revokedCount == 0) {
            resId = R.string.permission_revoked_none;
        } else {
            int resId2 = permissions.size();
            if (revokedCount == resId2) {
                resId = R.string.permission_revoked_all;
            } else {
                resId = R.string.permission_revoked_count;
            }
        }
        this.mDetailsPreference.setSummary(getString(resId, Integer.valueOf(revokedCount)));
        this.mDetailsPreference.setVisible(true);
    }

    private void updateDetailForFixedByPolicyPermissionGroup() {
        RestrictedLockUtils.EnforcedAdmin admin = getAdmin();
        AppPermissionGroup backgroundGroup = this.mGroup.getBackgroundPermissions();
        boolean hasAdmin = admin != null;
        if (isSystemFixed()) {
            setDetail(R.string.permission_summary_enabled_system_fixed);
            return;
        }
        if (isForegroundDisabledByPolicy()) {
            if (hasAdmin) {
                setDetail(R.string.disabled_by_admin);
                return;
            } else {
                setDetail(R.string.permission_summary_enforced_by_policy);
                return;
            }
        }
        if (isPolicyFullyFixed()) {
            if (backgroundGroup == null) {
                if (hasAdmin) {
                    setDetail(R.string.enabled_by_admin);
                    return;
                } else {
                    setDetail(R.string.permission_summary_enforced_by_policy);
                    return;
                }
            }
            if (backgroundGroup.areRuntimePermissionsGranted()) {
                if (hasAdmin) {
                    setDetail(R.string.enabled_by_admin);
                    return;
                } else {
                    setDetail(R.string.permission_summary_enforced_by_policy);
                    return;
                }
            }
            if (hasAdmin) {
                setDetail(R.string.permission_summary_enabled_by_admin_foreground_only);
                return;
            } else {
                setDetail(R.string.permission_summary_enabled_by_policy_foreground_only);
                return;
            }
        }
        if (isBackgroundPolicyFixed()) {
            if (backgroundGroup.areRuntimePermissionsGranted()) {
                if (hasAdmin) {
                    setDetail(R.string.permission_summary_enabled_by_admin_background_only);
                    return;
                } else {
                    setDetail(R.string.permission_summary_enabled_by_policy_background_only);
                    return;
                }
            }
            if (hasAdmin) {
                setDetail(R.string.permission_summary_disabled_by_admin_background_only);
                return;
            } else {
                setDetail(R.string.permission_summary_disabled_by_policy_background_only);
                return;
            }
        }
        if (isForegroundPolicyFixed()) {
            if (hasAdmin) {
                setDetail(R.string.permission_summary_enabled_by_admin_foreground_only);
            } else {
                setDetail(R.string.permission_summary_enabled_by_policy_foreground_only);
            }
        }
    }

    private void setDetail(int strId) {
        this.mDetailsPreference.setSummary(strId);
        this.mDetailsPreference.setVisible(true);
    }

    private void showAllPermissions(String filterGroup) {
        Fragment frag = AutoAllAppPermissionsFragment.newInstance(this.mGroup.getApp().packageName, filterGroup, UserHandle.getUserHandleForUid(this.mGroup.getApp().applicationInfo.uid));
        requireFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack("AllPerms").commit();
    }

    private boolean requestChange(boolean requestGrant, int changeTarget) {
        if (LocationUtils.isLocationGroupAndProvider(getContext(), this.mGroup.getName(), this.mGroup.getApp().packageName)) {
            LocationUtils.showLocationDialog(getContext(), Utils.getAppLabel(this.mGroup.getApp().applicationInfo, requireContext()));
            updateUi();
            return false;
        }
        if (requestGrant) {
            if ((changeTarget & 1) != 0) {
                if (!this.mGroup.areRuntimePermissionsGranted()) {
                    SafetyNetLogger.logPermissionToggled(this.mGroup);
                }
                this.mGroup.grantRuntimePermissions(true, false);
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
                if (!this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                    SafetyNetLogger.logPermissionToggled(this.mGroup.getBackgroundPermissions());
                }
                this.mGroup.getBackgroundPermissions().grantRuntimePermissions(true, false);
            }
        } else {
            boolean showDefaultDenyDialog = false;
            if ((changeTarget & 1) != 0 && this.mGroup.areRuntimePermissionsGranted()) {
                showDefaultDenyDialog = this.mGroup.hasGrantedByDefaultPermission() || !this.mGroup.doesSupportRuntimePermissions() || this.mGroup.hasInstallToRuntimeSplit();
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null && this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                AppPermissionGroup bgPerm = this.mGroup.getBackgroundPermissions();
                showDefaultDenyDialog |= bgPerm.hasGrantedByDefaultPermission() || !bgPerm.doesSupportRuntimePermissions() || bgPerm.hasInstallToRuntimeSplit();
            }
            if (showDefaultDenyDialog && !this.mHasConfirmedRevoke) {
                showDefaultDenyDialog(changeTarget);
                updateUi();
                return false;
            }
            if ((changeTarget & 1) != 0 && this.mGroup.areRuntimePermissionsGranted()) {
                if (this.mGroup.areRuntimePermissionsGranted()) {
                    SafetyNetLogger.logPermissionToggled(this.mGroup);
                }
                this.mGroup.revokeRuntimePermissions(false);
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null && this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                if (this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                    SafetyNetLogger.logPermissionToggled(this.mGroup.getBackgroundPermissions());
                }
                this.mGroup.getBackgroundPermissions().revokeRuntimePermissions(false);
            }
        }
        updateUi();
        return true;
    }

    private void showDefaultDenyDialog(int changeTarget) {
        Bundle args = new Bundle();
        boolean showGrantedByDefaultWarning = false;
        if ((changeTarget & 1) != 0) {
            showGrantedByDefaultWarning = this.mGroup.hasGrantedByDefaultPermission();
        }
        if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
            showGrantedByDefaultWarning |= this.mGroup.getBackgroundPermissions().hasGrantedByDefaultPermission();
        }
        args.putInt(DefaultDenyDialog.MSG, showGrantedByDefaultWarning ? R.string.system_warning : R.string.old_sdk_deny_warning);
        args.putInt(DefaultDenyDialog.CHANGE_TARGET, changeTarget);
        DefaultDenyDialog defaultDenyDialog = new DefaultDenyDialog();
        defaultDenyDialog.setArguments(args);
        defaultDenyDialog.setTargetFragment(this, 0);
        defaultDenyDialog.show(requireFragmentManager().beginTransaction(), DefaultDenyDialog.class.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDenyAnyWay(int changeTarget) {
        boolean hasDefaultPermissions = false;
        if ((changeTarget & 1) != 0) {
            if (this.mGroup.areRuntimePermissionsGranted()) {
                SafetyNetLogger.logPermissionToggled(this.mGroup);
            }
            this.mGroup.revokeRuntimePermissions(false);
            hasDefaultPermissions = this.mGroup.hasGrantedByDefaultPermission();
        }
        if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
            if (this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                SafetyNetLogger.logPermissionToggled(this.mGroup.getBackgroundPermissions());
            }
            this.mGroup.getBackgroundPermissions().revokeRuntimePermissions(false);
            hasDefaultPermissions |= this.mGroup.getBackgroundPermissions().hasGrantedByDefaultPermission();
        }
        if (hasDefaultPermissions || !this.mGroup.doesSupportRuntimePermissions()) {
            this.mHasConfirmedRevoke = true;
        }
        updateUi();
    }

    private static class SelectedPermissionPreference extends TwoStatePreference {
        SelectedPermissionPreference(Context context) {
            super(context, null, TypedArrayUtils.getAttr(context, R.attr.preferenceStyle, android.R.attr.preferenceStyle));
            setPersistent(false);
            setLayoutResource(R.layout.car_radio_button_preference);
            setWidgetLayoutResource(R.layout.radio_button_preference_widget);
        }

        @Override // androidx.preference.Preference
        public void onBindViewHolder(PreferenceViewHolder holder) {
            super.onBindViewHolder(holder);
            RadioButton radioButton = (RadioButton) holder.findViewById(R.id.radio_button);
            radioButton.setChecked(isChecked());
        }
    }

    public static class DefaultDenyDialog extends DialogFragment {
        private static final String MSG = DefaultDenyDialog.class.getName() + ".arg.msg";
        private static final String CHANGE_TARGET = DefaultDenyDialog.class.getName() + ".arg.changeTarget";

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AutoAppPermissionFragment fragment = (AutoAppPermissionFragment) getTargetFragment();
            return new AlertDialogBuilder(getContext()).setMessage(requireArguments().getInt(MSG)).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$DefaultDenyDialog$Q1Ld2eh_1PNMlqrjqNrKtDUWhV4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    fragment.updateUi();
                }
            }).setPositiveButton(R.string.grant_dialog_button_deny_anyway, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$AutoAppPermissionFragment$DefaultDenyDialog$qVtVYOs0Anz17x1cnXJRcKrDfBU
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$AutoAppPermissionFragment$DefaultDenyDialog(fragment, dialogInterface, i);
                }
            }).create();
        }

        public /* synthetic */ void lambda$onCreateDialog$1$AutoAppPermissionFragment$DefaultDenyDialog(AutoAppPermissionFragment fragment, DialogInterface dialog, int which) {
            fragment.onDenyAnyWay(requireArguments().getInt(CHANGE_TARGET));
        }
    }

    private class PermissionChangeListener implements PackageManager.OnPermissionsChangedListener {
        private final int mUid;

        PermissionChangeListener(int uid) {
            this.mUid = uid;
        }

        public void onPermissionsChanged(int uid) {
            if (uid == this.mUid) {
                Log.w(AutoAppPermissionFragment.LOG_TAG, "Permissions changed.");
                AutoAppPermissionFragment autoAppPermissionFragment = AutoAppPermissionFragment.this;
                autoAppPermissionFragment.mGroup = autoAppPermissionFragment.getAppPermissionGroup();
                AutoAppPermissionFragment.this.updateUi();
            }
        }
    }
}
