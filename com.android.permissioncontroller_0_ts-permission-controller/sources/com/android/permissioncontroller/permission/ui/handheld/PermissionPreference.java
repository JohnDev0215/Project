package com.android.permissioncontroller.permission.ui.handheld;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.view.View;
import android.widget.Switch;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.RestrictedLockUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
class PermissionPreference extends MultiTargetSwitchPreference {
    static final int CHANGE_BACKGROUND = 2;
    static final int CHANGE_BOTH = 3;
    static final int CHANGE_FOREGROUND = 1;
    private final PermissionPreferenceChangeListener mCallBacks;
    private final PreferenceFragmentCompat mFragment;
    private final AppPermissionGroup mGroup;
    private final int mOriginalWidgetLayoutRes;

    @Retention(RetentionPolicy.SOURCE)
    @interface ChangeTarget {
    }

    interface PermissionPreferenceChangeListener {
        void hasConfirmDefaultPermissionRevoke();

        void onPreferenceChanged(String str);

        boolean shouldConfirmDefaultPermissionRevoke();
    }

    interface PermissionPreferenceOwnerFragment {
        void onBackgroundAccessChosen(String str, int i);

        void onDenyAnyWay(String str, int i);
    }

    PermissionPreference(PreferenceFragmentCompat fragment, AppPermissionGroup group, PermissionPreferenceChangeListener callbacks) {
        super(fragment.getPreferenceManager().getContext());
        this.mFragment = fragment;
        this.mGroup = group;
        this.mCallBacks = callbacks;
        this.mOriginalWidgetLayoutRes = getWidgetLayoutResource();
        setPersistent(false);
        updateUi();
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

    void updateUi() {
        boolean arePermissionsIndividuallyControlled = Utils.areGroupPermissionsIndividuallyControlled(getContext(), this.mGroup.getName());
        final RestrictedLockUtils.EnforcedAdmin admin = getAdmin();
        setEnabled(true);
        setWidgetLayoutResource(this.mOriginalWidgetLayoutRes);
        setOnPreferenceClickListener(null);
        setSwitchOnClickListener(null);
        setSummary((CharSequence) null);
        setChecked(this.mGroup.areRuntimePermissionsGranted());
        if (isSystemFixed() || isPolicyFullyFixed() || isForegroundDisabledByPolicy()) {
            if (admin != null) {
                setWidgetLayoutResource(R.layout.restricted_icon);
                setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$AQFJAwBiulFQWlhG5Kek5T5D84w
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$0$PermissionPreference(admin, preference);
                    }
                });
            } else {
                setEnabled(false);
            }
            updateSummaryForFixedByPolicyPermissionGroup();
            return;
        }
        if (arePermissionsIndividuallyControlled) {
            setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$gq6OL6NuDHL4mvEDBVE0Z5BC3PE
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return this.f$0.lambda$updateUi$1$PermissionPreference(preference);
                }
            });
            setSwitchOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$s8UySTDC_N09CDPuiInScJtrtJ4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$updateUi$2$PermissionPreference(view);
                }
            });
            updateSummaryForIndividuallyControlledPermissionGroup();
            return;
        }
        if (this.mGroup.hasPermissionWithBackgroundMode()) {
            if (this.mGroup.getBackgroundPermissions() == null) {
                setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$WWGJRD13YqqrgK6d-bOBvMRzXHA
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return this.f$0.lambda$updateUi$3$PermissionPreference(preference, obj);
                    }
                });
                updateSummaryForPermissionGroupWithBackgroundPermission();
                return;
            }
            if (isBackgroundPolicyFixed()) {
                setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$hlfiS6qJqCTeZO7hevTdjfcsBmw
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return this.f$0.lambda$updateUi$4$PermissionPreference(preference, obj);
                    }
                });
                updateSummaryForFixedByPolicyPermissionGroup();
                return;
            } else if (isForegroundPolicyFixed()) {
                setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$TCjQES79OzQoPp5fWuptVyhzY9s
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return this.f$0.lambda$updateUi$5$PermissionPreference(preference, obj);
                    }
                });
                updateSummaryForFixedByPolicyPermissionGroup();
                return;
            } else {
                updateSummaryForPermissionGroupWithBackgroundPermission();
                setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$TeokuU5kx-VlZlXjC1qEahPIggA
                    @Override // androidx.preference.Preference.OnPreferenceClickListener
                    public final boolean onPreferenceClick(Preference preference) {
                        return this.f$0.lambda$updateUi$6$PermissionPreference(preference);
                    }
                });
                setSwitchOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$R1cLR_ZWWLgdDnf-hDLVi8NV0Vo
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$updateUi$7$PermissionPreference(view);
                    }
                });
                return;
            }
        }
        setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$SGed4fnJEZp3rks01mJRPrKH2Tw
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public final boolean onPreferenceChange(Preference preference, Object obj) {
                return this.f$0.lambda$updateUi$8$PermissionPreference(preference, obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$updateUi$0$PermissionPreference(RestrictedLockUtils.EnforcedAdmin admin, Preference v) {
        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), admin);
        return true;
    }

    public /* synthetic */ boolean lambda$updateUi$1$PermissionPreference(Preference pref) {
        showAllPermissions(this.mGroup.getName());
        return false;
    }

    public /* synthetic */ void lambda$updateUi$2$PermissionPreference(View v) {
        Switch switchView = (Switch) v;
        requestChange(switchView.isChecked(), 3);
        updateUi();
    }

    public /* synthetic */ boolean lambda$updateUi$3$PermissionPreference(Preference pref, Object newValue) {
        return requestChange(((Boolean) newValue).booleanValue(), 1);
    }

    public /* synthetic */ boolean lambda$updateUi$4$PermissionPreference(Preference pref, Object newValue) {
        return requestChange(((Boolean) newValue).booleanValue(), 1);
    }

    public /* synthetic */ boolean lambda$updateUi$5$PermissionPreference(Preference pref, Object newValue) {
        return requestChange(((Boolean) newValue).booleanValue(), 2);
    }

    public /* synthetic */ boolean lambda$updateUi$6$PermissionPreference(Preference pref) {
        showBackgroundChooserDialog();
        return true;
    }

    public /* synthetic */ void lambda$updateUi$7$PermissionPreference(View v) {
        Switch switchView = (Switch) v;
        if (switchView.isChecked()) {
            showBackgroundChooserDialog();
        } else {
            requestChange(false, 3);
        }
        updateUi();
    }

    public /* synthetic */ boolean lambda$updateUi$8$PermissionPreference(Preference pref, Object newValue) {
        return requestChange(((Boolean) newValue).booleanValue(), 3);
    }

    private void updateSummaryForIndividuallyControlledPermissionGroup() {
        int resId;
        int revokedCount = 0;
        List<Permission> permissions = this.mGroup.getPermissions();
        int permissionCount = permissions.size();
        for (int i = 0; i < permissionCount; i++) {
            Permission permission = permissions.get(i);
            if (!permission.isGrantedIncludingAppOp()) {
                revokedCount++;
            }
        }
        if (revokedCount == 0) {
            resId = R.string.permission_revoked_none;
        } else if (revokedCount == permissionCount) {
            resId = R.string.permission_revoked_all;
        } else {
            resId = R.string.permission_revoked_count;
        }
        String summary = getContext().getString(resId, Integer.valueOf(revokedCount));
        setSummary(summary);
    }

    private void updateSummaryForPermissionGroupWithBackgroundPermission() {
        AppPermissionGroup backgroundGroup = this.mGroup.getBackgroundPermissions();
        if (!this.mGroup.areRuntimePermissionsGranted()) {
            setSummary(R.string.permission_access_never);
            return;
        }
        if (backgroundGroup == null) {
            setSummary(R.string.permission_access_only_foreground);
        } else if (backgroundGroup.areRuntimePermissionsGranted()) {
            setSummary(R.string.permission_access_always);
        } else {
            setSummary(R.string.permission_access_only_foreground);
        }
    }

    private void updateSummaryForFixedByPolicyPermissionGroup() {
        RestrictedLockUtils.EnforcedAdmin admin = getAdmin();
        AppPermissionGroup backgroundGroup = this.mGroup.getBackgroundPermissions();
        boolean hasAdmin = admin != null;
        if (isSystemFixed()) {
            setSummary(R.string.permission_summary_enabled_system_fixed);
            return;
        }
        if (isForegroundDisabledByPolicy()) {
            if (hasAdmin) {
                setSummary(R.string.disabled_by_admin);
                return;
            } else {
                setSummary(R.string.permission_summary_enforced_by_policy);
                return;
            }
        }
        if (isPolicyFullyFixed()) {
            if (backgroundGroup == null) {
                if (hasAdmin) {
                    setSummary(R.string.enabled_by_admin);
                    return;
                } else {
                    setSummary(R.string.permission_summary_enforced_by_policy);
                    return;
                }
            }
            if (backgroundGroup.areRuntimePermissionsGranted()) {
                if (hasAdmin) {
                    setSummary(R.string.enabled_by_admin);
                    return;
                } else {
                    setSummary(R.string.permission_summary_enforced_by_policy);
                    return;
                }
            }
            if (hasAdmin) {
                setSummary(R.string.permission_summary_enabled_by_admin_foreground_only);
                return;
            } else {
                setSummary(R.string.permission_summary_enabled_by_policy_foreground_only);
                return;
            }
        }
        if (isBackgroundPolicyFixed()) {
            if (backgroundGroup.areRuntimePermissionsGranted()) {
                if (hasAdmin) {
                    setSummary(R.string.permission_summary_enabled_by_admin_background_only);
                    return;
                } else {
                    setSummary(R.string.permission_summary_enabled_by_policy_background_only);
                    return;
                }
            }
            if (hasAdmin) {
                setSummary(R.string.permission_summary_disabled_by_admin_background_only);
                return;
            } else {
                setSummary(R.string.permission_summary_disabled_by_policy_background_only);
                return;
            }
        }
        if (isForegroundPolicyFixed()) {
            if (hasAdmin) {
                setSummary(R.string.permission_summary_enabled_by_admin_foreground_only);
            } else {
                setSummary(R.string.permission_summary_enabled_by_policy_foreground_only);
            }
        }
    }

    private void showAllPermissions(String filterGroup) {
        Fragment frag = AllAppPermissionsFragment.newInstance(this.mGroup.getApp().packageName, filterGroup, UserHandle.getUserHandleForUid(this.mGroup.getApp().applicationInfo.uid));
        this.mFragment.getFragmentManager().beginTransaction().replace(android.R.id.content, frag).addToBackStack("AllPerms").commit();
    }

    private String getAppLabel() {
        return BidiFormatter.getInstance().unicodeWrap(this.mGroup.getApp().applicationInfo.loadSafeLabel(getContext().getPackageManager(), 500.0f, 5).toString());
    }

    private boolean requestChange(boolean requestGrant, int changeTarget) {
        if (LocationUtils.isLocationGroupAndProvider(getContext(), this.mGroup.getName(), this.mGroup.getApp().packageName)) {
            LocationUtils.showLocationDialog(getContext(), getAppLabel());
            return false;
        }
        if (requestGrant) {
            this.mCallBacks.onPreferenceChanged(getKey());
            if ((changeTarget & 1) != 0) {
                this.mGroup.grantRuntimePermissions(true, false);
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
                this.mGroup.getBackgroundPermissions().grantRuntimePermissions(true, false);
            }
        } else {
            boolean requestToRevokeGrantedByDefault = false;
            if ((changeTarget & 1) != 0) {
                requestToRevokeGrantedByDefault = this.mGroup.hasGrantedByDefaultPermission();
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
                requestToRevokeGrantedByDefault |= this.mGroup.getBackgroundPermissions().hasGrantedByDefaultPermission();
            }
            if ((requestToRevokeGrantedByDefault || !this.mGroup.doesSupportRuntimePermissions()) && this.mCallBacks.shouldConfirmDefaultPermissionRevoke()) {
                showDefaultDenyDialog(changeTarget);
                return false;
            }
            this.mCallBacks.onPreferenceChanged(getKey());
            if ((changeTarget & 1) != 0) {
                this.mGroup.revokeRuntimePermissions(false);
            }
            if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
                this.mGroup.getBackgroundPermissions().revokeRuntimePermissions(false);
            }
        }
        updateUi();
        return true;
    }

    private void showDefaultDenyDialog(int changeTarget) {
        if (!this.mFragment.isResumed()) {
            return;
        }
        Bundle args = new Bundle();
        boolean showGrantedByDefaultWarning = false;
        if ((changeTarget & 1) != 0) {
            showGrantedByDefaultWarning = this.mGroup.hasGrantedByDefaultPermission();
        }
        if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
            showGrantedByDefaultWarning |= this.mGroup.getBackgroundPermissions().hasGrantedByDefaultPermission();
        }
        args.putInt(DefaultDenyDialog.MSG, showGrantedByDefaultWarning ? R.string.system_warning : R.string.old_sdk_deny_warning);
        args.putString(DefaultDenyDialog.KEY, getKey());
        args.putInt(DefaultDenyDialog.CHANGE_TARGET, changeTarget);
        DefaultDenyDialog deaultDenyDialog = new DefaultDenyDialog();
        deaultDenyDialog.setArguments(args);
        deaultDenyDialog.show(this.mFragment.getChildFragmentManager().beginTransaction(), "denyDefault");
    }

    private void showBackgroundChooserDialog() {
        if (!this.mFragment.isResumed()) {
            return;
        }
        if (LocationUtils.isLocationGroupAndProvider(getContext(), this.mGroup.getName(), this.mGroup.getApp().packageName)) {
            LocationUtils.showLocationDialog(getContext(), getAppLabel());
            return;
        }
        Bundle args = new Bundle();
        args.putCharSequence(BackgroundAccessChooser.TITLE, Utils.getRequestMessage(getAppLabel(), this.mGroup, getContext(), this.mGroup.getRequest()));
        args.putString(BackgroundAccessChooser.KEY, getKey());
        if (this.mGroup.areRuntimePermissionsGranted()) {
            if (this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                args.putInt(BackgroundAccessChooser.SELECTION, 0);
            } else {
                args.putInt(BackgroundAccessChooser.SELECTION, 1);
            }
        } else {
            args.putInt(BackgroundAccessChooser.SELECTION, 2);
        }
        BackgroundAccessChooser chooserDialog = new BackgroundAccessChooser();
        chooserDialog.setArguments(args);
        chooserDialog.show(this.mFragment.getChildFragmentManager().beginTransaction(), "backgroundChooser");
    }

    void onDenyAnyWay(int changeTarget) {
        this.mCallBacks.onPreferenceChanged(getKey());
        boolean hasDefaultPermissions = false;
        if ((changeTarget & 1) != 0) {
            this.mGroup.revokeRuntimePermissions(false);
            hasDefaultPermissions = this.mGroup.hasGrantedByDefaultPermission();
        }
        if ((changeTarget & 2) != 0 && this.mGroup.getBackgroundPermissions() != null) {
            this.mGroup.getBackgroundPermissions().revokeRuntimePermissions(false);
            hasDefaultPermissions |= this.mGroup.getBackgroundPermissions().hasGrantedByDefaultPermission();
        }
        if (hasDefaultPermissions || !this.mGroup.doesSupportRuntimePermissions()) {
            this.mCallBacks.hasConfirmDefaultPermissionRevoke();
        }
        updateUi();
    }

    void onBackgroundAccessChosen(int choosenItem) {
        AppPermissionGroup backgroundGroup = this.mGroup.getBackgroundPermissions();
        if (choosenItem == 0) {
            requestChange(true, 3);
            return;
        }
        if (choosenItem == 1) {
            if (backgroundGroup.areRuntimePermissionsGranted()) {
                requestChange(false, 2);
            }
            requestChange(true, 1);
        } else if (choosenItem == 2) {
            if (this.mGroup.areRuntimePermissionsGranted() || this.mGroup.getBackgroundPermissions().areRuntimePermissionsGranted()) {
                requestChange(false, 3);
            }
        }
    }

    public static class DefaultDenyDialog extends DialogFragment {
        private static final String MSG = DefaultDenyDialog.class.getName() + ".arg.msg";
        private static final String CHANGE_TARGET = DefaultDenyDialog.class.getName() + ".arg.changeTarget";
        private static final String KEY = DefaultDenyDialog.class.getName() + ".arg.key";

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder b = new AlertDialog.Builder(getContext()).setMessage(getArguments().getInt(MSG)).setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null).setPositiveButton(R.string.grant_dialog_button_deny_anyway, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$DefaultDenyDialog$SeKLG5C9qm0tRE9nxCXwv1S3gto
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$PermissionPreference$DefaultDenyDialog(dialogInterface, i);
                }
            });
            return b.create();
        }

        public /* synthetic */ void lambda$onCreateDialog$0$PermissionPreference$DefaultDenyDialog(DialogInterface dialog, int which) {
            ((PermissionPreferenceOwnerFragment) getParentFragment()).onDenyAnyWay(getArguments().getString(KEY), getArguments().getInt(CHANGE_TARGET));
        }
    }

    public static class BackgroundAccessChooser extends DialogFragment {
        static final int ALWAYS_OPTION = 0;
        static final int FOREGROUND_ONLY_OPTION = 1;
        static final int NEVER_OPTION = 2;
        private static final String TITLE = BackgroundAccessChooser.class.getName() + ".arg.title";
        private static final String KEY = BackgroundAccessChooser.class.getName() + ".arg.key";
        private static final String SELECTION = BackgroundAccessChooser.class.getName() + ".arg.selection";

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder b = new AlertDialog.Builder(getActivity()).setTitle(getArguments().getCharSequence(TITLE)).setSingleChoiceItems(R.array.background_access_chooser_dialog_choices, getArguments().getInt(SELECTION), new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$PermissionPreference$BackgroundAccessChooser$s90IB4ig4dDkZASI17gzy_swiHc
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$0$PermissionPreference$BackgroundAccessChooser(dialogInterface, i);
                }
            });
            return b.create();
        }

        public /* synthetic */ void lambda$onCreateDialog$0$PermissionPreference$BackgroundAccessChooser(DialogInterface dialog, int which) {
            dismissAllowingStateLoss();
            ((PermissionPreferenceOwnerFragment) getParentFragment()).onBackgroundAccessChosen(getArguments().getString(KEY), which);
        }
    }
}
