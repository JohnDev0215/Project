package com.android.car.developeroptions.applications.specialaccess.deviceadmin;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.app.admin.DeviceAdminInfo;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.EventLog;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AppSecurityPermissions;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class DeviceAdminAdd extends Activity {
    Button mActionButton;
    TextView mAddMsg;
    ImageView mAddMsgExpander;
    CharSequence mAddMsgText;
    boolean mAdding;
    boolean mAddingProfileOwner;
    TextView mAdminDescription;
    ImageView mAdminIcon;
    TextView mAdminName;
    ViewGroup mAdminPolicies;
    boolean mAdminPoliciesInitialized;
    TextView mAdminWarning;
    AppOpsManager mAppOps;
    Button mCancelButton;
    DevicePolicyManager mDPM;
    DeviceAdminInfo mDeviceAdmin;
    Handler mHandler;
    String mProfileOwnerName;
    TextView mProfileOwnerWarning;
    boolean mRefreshing;
    TextView mSupportMessage;
    Button mUninstallButton;
    boolean mWaitingForRemoveMsg;
    private final IBinder mToken = new Binder();
    boolean mAddMsgEllipsized = true;
    boolean mUninstalling = false;
    boolean mIsCalledFromSupportDialog = false;

    /* JADX WARN: Code restructure failed: missing block: B:43:0x0143, code lost:
    
        r9.activityInfo = r2;
        new android.app.admin.DeviceAdminInfo(r12, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x014a, code lost:
    
        r13 = true;
     */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r13) {
        /*
            Method dump skipped, instruction units count: 846
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.applications.specialaccess.deviceadmin.DeviceAdminAdd.onCreate(android.os.Bundle):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPolicyTransparencyDialogIfRequired() {
        RestrictedLockUtils.EnforcedAdmin adminEnforcingCantRemoveProfile;
        if (!isManagedProfile(this.mDeviceAdmin) || !this.mDeviceAdmin.getComponent().equals(this.mDPM.getProfileOwner()) || hasBaseCantRemoveProfileRestriction() || (adminEnforcingCantRemoveProfile = getAdminEnforcingCantRemoveProfile()) == null) {
            return;
        }
        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this, adminEnforcingCantRemoveProfile);
    }

    void addAndFinish() {
        try {
            logSpecialPermissionChange(true, this.mDeviceAdmin.getComponent().getPackageName());
            this.mDPM.setActiveAdmin(this.mDeviceAdmin.getComponent(), this.mRefreshing);
            EventLog.writeEvent(90201, this.mDeviceAdmin.getActivityInfo().applicationInfo.uid);
            unrestrictAppIfPossible(BatteryUtils.getInstance(this));
            setResult(-1);
        } catch (RuntimeException e) {
            Log.w("DeviceAdminAdd", "Exception trying to activate admin " + this.mDeviceAdmin.getComponent(), e);
            if (this.mDPM.isAdminActive(this.mDeviceAdmin.getComponent())) {
                setResult(-1);
            }
        }
        if (this.mAddingProfileOwner) {
            try {
                this.mDPM.setProfileOwner(this.mDeviceAdmin.getComponent(), this.mProfileOwnerName, UserHandle.myUserId());
            } catch (RuntimeException unused) {
                setResult(0);
            }
        }
        finish();
    }

    void unrestrictAppIfPossible(BatteryUtils batteryUtils) {
        batteryUtils.clearForceAppStandby(this.mDeviceAdmin.getComponent().getPackageName());
    }

    void continueRemoveAction(CharSequence charSequence) {
        if (this.mWaitingForRemoveMsg) {
            this.mWaitingForRemoveMsg = false;
            if (charSequence == null) {
                try {
                    ActivityManager.getService().resumeAppSwitches();
                } catch (RemoteException unused) {
                }
                logSpecialPermissionChange(false, this.mDeviceAdmin.getComponent().getPackageName());
                this.mDPM.removeActiveAdmin(this.mDeviceAdmin.getComponent());
                finish();
                return;
            }
            try {
                ActivityManager.getService().stopAppSwitches();
            } catch (RemoteException unused2) {
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("android.app.extra.DISABLE_WARNING", charSequence);
            showDialog(1, bundle);
        }
    }

    void logSpecialPermissionChange(boolean z, String str) {
        FeatureFactory.getFactory(this).getMetricsFeatureProvider().action(0, z ? 766 : 767, 0, str, 0);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mActionButton.setEnabled(true);
        updateInterface();
        this.mAppOps.setUserRestriction(24, true, this.mToken);
        this.mAppOps.setUserRestriction(45, true, this.mToken);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mActionButton.setEnabled(false);
        this.mAppOps.setUserRestriction(24, false, this.mToken);
        this.mAppOps.setUserRestriction(45, false, this.mToken);
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        if (this.mIsCalledFromSupportDialog) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (i == 1) {
            CharSequence charSequence = bundle.getCharSequence("android.app.extra.DISABLE_WARNING");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(charSequence);
            builder.setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.specialaccess.deviceadmin.DeviceAdminAdd.6
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    try {
                        ActivityManager.getService().resumeAppSwitches();
                    } catch (RemoteException unused) {
                    }
                    DeviceAdminAdd deviceAdminAdd = DeviceAdminAdd.this;
                    deviceAdminAdd.mDPM.removeActiveAdmin(deviceAdminAdd.mDeviceAdmin.getComponent());
                    DeviceAdminAdd.this.finish();
                }
            });
            builder.setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null);
            return builder.create();
        }
        return super.onCreateDialog(i, bundle);
    }

    void updateInterface() {
        findViewById(R.id.restricted_icon).setVisibility(8);
        this.mAdminIcon.setImageDrawable(this.mDeviceAdmin.loadIcon(getPackageManager()));
        this.mAdminName.setText(this.mDeviceAdmin.loadLabel(getPackageManager()));
        try {
            this.mAdminDescription.setText(this.mDeviceAdmin.loadDescription(getPackageManager()));
            this.mAdminDescription.setVisibility(0);
        } catch (Resources.NotFoundException unused) {
            this.mAdminDescription.setVisibility(8);
        }
        if (this.mAddingProfileOwner) {
            this.mProfileOwnerWarning.setVisibility(0);
        }
        CharSequence charSequence = this.mAddMsgText;
        if (charSequence != null) {
            this.mAddMsg.setText(charSequence);
            this.mAddMsg.setVisibility(0);
        } else {
            this.mAddMsg.setVisibility(8);
            this.mAddMsgExpander.setVisibility(8);
        }
        if (!this.mRefreshing && !this.mAddingProfileOwner && this.mDPM.isAdminActive(this.mDeviceAdmin.getComponent())) {
            this.mAdding = false;
            boolean zEquals = this.mDeviceAdmin.getComponent().equals(this.mDPM.getProfileOwner());
            boolean zIsManagedProfile = isManagedProfile(this.mDeviceAdmin);
            if (zEquals && zIsManagedProfile) {
                this.mAdminWarning.setText(R.string.admin_profile_owner_message);
                this.mActionButton.setText(R.string.remove_managed_profile_label);
                RestrictedLockUtils.EnforcedAdmin adminEnforcingCantRemoveProfile = getAdminEnforcingCantRemoveProfile();
                boolean zHasBaseCantRemoveProfileRestriction = hasBaseCantRemoveProfileRestriction();
                if (adminEnforcingCantRemoveProfile != null && !zHasBaseCantRemoveProfileRestriction) {
                    findViewById(R.id.restricted_icon).setVisibility(0);
                }
                this.mActionButton.setEnabled(adminEnforcingCantRemoveProfile == null && !zHasBaseCantRemoveProfileRestriction);
            } else if (zEquals || this.mDeviceAdmin.getComponent().equals(this.mDPM.getDeviceOwnerComponentOnCallingUser())) {
                if (zEquals) {
                    this.mAdminWarning.setText(R.string.admin_profile_owner_user_message);
                } else {
                    this.mAdminWarning.setText(R.string.admin_device_owner_message);
                }
                this.mActionButton.setText(R.string.remove_device_admin);
                this.mActionButton.setEnabled(false);
            } else {
                addDeviceAdminPolicies(false);
                this.mAdminWarning.setText(getString(R.string.device_admin_status, new Object[]{this.mDeviceAdmin.getActivityInfo().applicationInfo.loadLabel(getPackageManager())}));
                setTitle(R.string.active_device_admin_msg);
                if (this.mUninstalling) {
                    this.mActionButton.setText(R.string.remove_and_uninstall_device_admin);
                } else {
                    this.mActionButton.setText(R.string.remove_device_admin);
                }
            }
            CharSequence longSupportMessageForUser = this.mDPM.getLongSupportMessageForUser(this.mDeviceAdmin.getComponent(), UserHandle.myUserId());
            if (!TextUtils.isEmpty(longSupportMessageForUser)) {
                this.mSupportMessage.setText(longSupportMessageForUser);
                this.mSupportMessage.setVisibility(0);
                return;
            } else {
                this.mSupportMessage.setVisibility(8);
                return;
            }
        }
        addDeviceAdminPolicies(true);
        this.mAdminWarning.setText(getString(R.string.device_admin_warning, new Object[]{this.mDeviceAdmin.getActivityInfo().applicationInfo.loadLabel(getPackageManager())}));
        if (this.mAddingProfileOwner) {
            setTitle(getText(R.string.profile_owner_add_title));
        } else {
            setTitle(getText(R.string.add_device_admin_msg));
        }
        this.mActionButton.setText(getText(R.string.add_device_admin));
        if (isAdminUninstallable()) {
            this.mUninstallButton.setVisibility(0);
        }
        this.mSupportMessage.setVisibility(8);
        this.mAdding = true;
    }

    private RestrictedLockUtils.EnforcedAdmin getAdminEnforcingCantRemoveProfile() {
        return RestrictedLockUtilsInternal.checkIfRestrictionEnforced(this, "no_remove_managed_profile", getParentUserId());
    }

    private boolean hasBaseCantRemoveProfileRestriction() {
        return RestrictedLockUtilsInternal.hasBaseUserRestriction(this, "no_remove_managed_profile", getParentUserId());
    }

    private int getParentUserId() {
        return UserManager.get(this).getProfileParent(UserHandle.myUserId()).id;
    }

    private void addDeviceAdminPolicies(boolean z) {
        if (this.mAdminPoliciesInitialized) {
            return;
        }
        boolean zIsAdminUser = UserManager.get(this).isAdminUser();
        for (DeviceAdminInfo.PolicyInfo policyInfo : this.mDeviceAdmin.getUsedPolicies()) {
            this.mAdminPolicies.addView(AppSecurityPermissions.getPermissionItemView(this, getText(zIsAdminUser ? policyInfo.label : policyInfo.labelForSecondaryUsers), z ? getText(zIsAdminUser ? policyInfo.description : policyInfo.descriptionForSecondaryUsers) : "", true));
        }
        this.mAdminPoliciesInitialized = true;
    }

    void toggleMessageEllipsis(View view) {
        TextView textView = (TextView) view;
        boolean z = !this.mAddMsgEllipsized;
        this.mAddMsgEllipsized = z;
        textView.setEllipsize(z ? TextUtils.TruncateAt.END : null);
        textView.setMaxLines(this.mAddMsgEllipsized ? getEllipsizedLines() : 15);
        this.mAddMsgExpander.setImageResource(this.mAddMsgEllipsized ? android.R.drawable.clock_hand_hour : android.R.drawable.clock_dial);
    }

    int getEllipsizedLines() {
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        return defaultDisplay.getHeight() > defaultDisplay.getWidth() ? 5 : 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isManagedProfile(DeviceAdminInfo deviceAdminInfo) {
        UserInfo userInfo = UserManager.get(this).getUserInfo(UserHandle.getUserId(deviceAdminInfo.getActivityInfo().applicationInfo.uid));
        if (userInfo != null) {
            return userInfo.isManagedProfile();
        }
        return false;
    }

    private Optional<ComponentName> findAdminWithPackageName(final String str) {
        List<ComponentName> activeAdmins = this.mDPM.getActiveAdmins();
        if (activeAdmins == null) {
            return Optional.empty();
        }
        return activeAdmins.stream().filter(new Predicate() { // from class: com.android.car.developeroptions.applications.specialaccess.deviceadmin.-$$Lambda$DeviceAdminAdd$pExPIg6kOIKoHU2gE0ghzNjstfU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ComponentName) obj).getPackageName().equals(str);
            }
        }).findAny();
    }

    private boolean isAdminUninstallable() {
        return !this.mDeviceAdmin.getActivityInfo().applicationInfo.isSystemApp();
    }
}
