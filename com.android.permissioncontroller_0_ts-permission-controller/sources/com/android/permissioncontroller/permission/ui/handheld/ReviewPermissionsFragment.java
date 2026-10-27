package com.android.permissioncontroller.permission.ui.handheld;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteCallback;
import android.os.UserHandle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.model.Permission;
import com.android.permissioncontroller.permission.ui.ManagePermissionsActivity;
import com.android.permissioncontroller.permission.ui.handheld.PermissionPreference;
import com.android.permissioncontroller.permission.utils.ArrayUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes8.dex */
public final class ReviewPermissionsFragment extends PreferenceFragmentCompat implements View.OnClickListener, PermissionPreference.PermissionPreferenceChangeListener, PermissionPreference.PermissionPreferenceOwnerFragment {
    private static final String EXTRA_PACKAGE_INFO = "com.android.permissioncontroller.permission.ui.extra.PACKAGE_INFO";
    private static final String LOG_TAG = ReviewPermissionsFragment.class.getSimpleName();
    private AppPermissions mAppPermissions;
    private Button mCancelButton;
    private Button mContinueButton;
    private PreferenceCategory mCurrentPermissionsCategory;
    private boolean mHasConfirmedRevoke;
    private Button mMoreInfoButton;
    private PreferenceCategory mNewPermissionsCategory;

    public static ReviewPermissionsFragment newInstance(PackageInfo packageInfo) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(EXTRA_PACKAGE_INFO, packageInfo);
        ReviewPermissionsFragment instance = new ReviewPermissionsFragment();
        instance.setArguments(arguments);
        instance.setRetainInstance(true);
        return instance;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        PackageInfo packageInfo = (PackageInfo) getArguments().getParcelable(EXTRA_PACKAGE_INFO);
        if (packageInfo == null) {
            activity.finish();
            return;
        }
        AppPermissions appPermissions = new AppPermissions(activity, packageInfo, false, true, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$ReviewPermissionsFragment$DkzrqvFhzh4WMDv2Y89e841GcB4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreate$0$ReviewPermissionsFragment();
            }
        });
        this.mAppPermissions = appPermissions;
        boolean reviewRequired = false;
        for (AppPermissionGroup group : appPermissions.getPermissionGroups()) {
            if (group.isReviewRequired() || (group.getBackgroundPermissions() != null && group.getBackgroundPermissions().isReviewRequired())) {
                reviewRequired = true;
                break;
            }
        }
        if (!reviewRequired) {
            confirmPermissionsReview();
            executeCallback(true);
            activity.finish();
        }
    }

    public /* synthetic */ void lambda$onCreate$0$ReviewPermissionsFragment() {
        getActivity().finish();
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String s) {
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindUi();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAppPermissions.refresh();
        loadPreferences();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (view == this.mContinueButton) {
            confirmPermissionsReview();
            executeCallback(true);
        } else if (view == this.mCancelButton) {
            executeCallback(false);
            activity.setResult(0);
        } else if (view == this.mMoreInfoButton) {
            Intent intent = new Intent("android.intent.action.MANAGE_APP_PERMISSIONS");
            intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mAppPermissions.getPackageInfo().packageName);
            intent.putExtra("android.intent.extra.USER", UserHandle.getUserHandleForUid(this.mAppPermissions.getPackageInfo().applicationInfo.uid));
            intent.putExtra(ManagePermissionsActivity.EXTRA_ALL_PERMISSIONS, true);
            getActivity().startActivity(intent);
        }
        activity.finish();
    }

    private void grantReviewedPermission(AppPermissionGroup group) {
        String[] permissionsToGrant = null;
        int permissionCount = group.getPermissions().size();
        for (int j = 0; j < permissionCount; j++) {
            Permission permission = group.getPermissions().get(j);
            if (permission.isReviewRequired()) {
                permissionsToGrant = ArrayUtils.appendString(permissionsToGrant, permission.getName());
            }
        }
        if (permissionsToGrant != null) {
            group.grantRuntimePermissions(true, false, permissionsToGrant);
        }
    }

    private void confirmPermissionsReview() {
        String perm;
        int i;
        int i2;
        List<PreferenceGroup> preferenceGroups = new ArrayList<>();
        PreferenceGroup preferenceGroup = this.mNewPermissionsCategory;
        if (preferenceGroup != null) {
            preferenceGroups.add(preferenceGroup);
            preferenceGroups.add(this.mCurrentPermissionsCategory);
        } else {
            PreferenceScreen preferenceScreen = getPreferenceScreen();
            if (preferenceScreen != null) {
                preferenceGroups.add(preferenceScreen);
            }
        }
        int preferenceGroupCount = preferenceGroups.size();
        long changeIdForLogging = new Random().nextLong();
        for (int groupNum = 0; groupNum < preferenceGroupCount; groupNum++) {
            PreferenceGroup preferenceGroup2 = preferenceGroups.get(groupNum);
            int preferenceCount = preferenceGroup2.getPreferenceCount();
            for (int prefNum = 0; prefNum < preferenceCount; prefNum++) {
                Preference preference = preferenceGroup2.getPreference(prefNum);
                if (preference instanceof PermissionReviewPreference) {
                    PermissionReviewPreference permPreference = (PermissionReviewPreference) preference;
                    AppPermissionGroup group = permPreference.getGroup();
                    if (group.isReviewRequired() && !permPreference.wasChanged()) {
                        grantReviewedPermission(group);
                    }
                    logReviewPermissionsFragmentResult(changeIdForLogging, group);
                    AppPermissionGroup backgroundGroup = group.getBackgroundPermissions();
                    if (backgroundGroup != null) {
                        if (backgroundGroup.isReviewRequired() && !permPreference.wasChanged()) {
                            grantReviewedPermission(backgroundGroup);
                        }
                        logReviewPermissionsFragmentResult(changeIdForLogging, backgroundGroup);
                    }
                }
            }
        }
        this.mAppPermissions.persistChanges(true);
        PackageManager pm = getContext().getPackageManager();
        PackageInfo pkg = this.mAppPermissions.getPackageInfo();
        UserHandle user = UserHandle.getUserHandleForUid(pkg.applicationInfo.uid);
        String[] strArr = pkg.requestedPermissions;
        int length = strArr.length;
        int i3 = 0;
        while (i3 < length) {
            String perm2 = strArr[i3];
            try {
                perm = perm2;
                i = i3;
                i2 = length;
            } catch (IllegalArgumentException e) {
                e = e;
                perm = perm2;
                i = i3;
                i2 = length;
            }
            try {
                pm.updatePermissionFlags(perm2, pkg.packageName, 64, 0, user);
            } catch (IllegalArgumentException e2) {
                e = e2;
                Log.e(LOG_TAG, "Cannot unmark " + perm + " requested by " + pkg.packageName + " as review required", e);
            }
            i3 = i + 1;
            length = i2;
        }
    }

    private void logReviewPermissionsFragmentResult(long changeId, AppPermissionGroup group) {
        ArrayList<Permission> permissions = group.getPermissions();
        int numPermissions = permissions.size();
        for (int i = 0; i < numPermissions; i++) {
            Permission permission = permissions.get(i);
            PermissionControllerStatsLog.write(PermissionControllerStatsLog.REVIEW_PERMISSIONS_FRAGMENT_RESULT_REPORTED, changeId, group.getApp().applicationInfo.uid, group.getApp().packageName, permission.getName(), permission.isGrantedIncludingAppOp());
            Log.v(LOG_TAG, "Permission grant via permission review changeId=" + changeId + " uid=" + group.getApp().applicationInfo.uid + " packageName=" + group.getApp().packageName + " permission=" + permission.getName() + " granted=" + permission.isGrantedIncludingAppOp());
        }
    }

    private void bindUi() {
        int labelTemplateResId;
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        Drawable icon = this.mAppPermissions.getPackageInfo().applicationInfo.loadIcon(activity.getPackageManager());
        ImageView iconView = (ImageView) activity.requireViewById(R.id.app_icon);
        iconView.setImageDrawable(icon);
        if (isPackageUpdated()) {
            labelTemplateResId = R.string.permission_review_title_template_update;
        } else {
            labelTemplateResId = R.string.permission_review_title_template_install;
        }
        Spanned message = Html.fromHtml(getString(labelTemplateResId, this.mAppPermissions.getAppLabel()), 0);
        activity.setTitle(message.toString());
        TextView permissionsMessageView = (TextView) activity.requireViewById(R.id.permissions_message);
        permissionsMessageView.setText(message);
        Button button = (Button) getActivity().requireViewById(R.id.continue_button);
        this.mContinueButton = button;
        button.setOnClickListener(this);
        Button button2 = (Button) getActivity().requireViewById(R.id.cancel_button);
        this.mCancelButton = button2;
        button2.setOnClickListener(this);
        if (activity.getPackageManager().arePermissionsIndividuallyControlled()) {
            Button button3 = (Button) getActivity().requireViewById(R.id.permission_more_info_button);
            this.mMoreInfoButton = button3;
            button3.setOnClickListener(this);
            this.mMoreInfoButton.setVisibility(0);
        }
    }

    private PermissionReviewPreference getPreference(String key) {
        PreferenceCategory preferenceCategory;
        PreferenceCategory preferenceCategory2 = this.mNewPermissionsCategory;
        if (preferenceCategory2 != null) {
            PermissionReviewPreference pref = (PermissionReviewPreference) preferenceCategory2.findPreference(key);
            if (pref == null && (preferenceCategory = this.mCurrentPermissionsCategory) != null) {
                return (PermissionReviewPreference) preferenceCategory.findPreference(key);
            }
            return pref;
        }
        return (PermissionReviewPreference) getPreferenceScreen().findPreference(key);
    }

    private void loadPreferences() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        PreferenceScreen screen = getPreferenceScreen();
        if (screen == null) {
            screen = getPreferenceManager().createPreferenceScreen(getContext());
            setPreferenceScreen(screen);
        } else {
            screen.removeAll();
        }
        this.mCurrentPermissionsCategory = null;
        this.mNewPermissionsCategory = null;
        boolean isPackageUpdated = isPackageUpdated();
        for (AppPermissionGroup group : this.mAppPermissions.getPermissionGroups()) {
            if (Utils.shouldShowPermission(getContext(), group) && Utils.OS_PKG.equals(group.getDeclaringPackage())) {
                PermissionReviewPreference preference = getPreference(group.getName());
                if (preference == null) {
                    preference = new PermissionReviewPreference(this, group, this);
                    preference.setKey(group.getName());
                    Drawable icon = Utils.loadDrawable(activity.getPackageManager(), group.getIconPkg(), group.getIconResId());
                    preference.setIcon(Utils.applyTint(getContext(), icon, android.R.attr.colorControlNormal));
                    preference.setTitle(group.getLabel());
                } else {
                    preference.updateUi();
                }
                if (group.isReviewRequired() || (group.getBackgroundPermissions() != null && group.getBackgroundPermissions().isReviewRequired())) {
                    if (!isPackageUpdated) {
                        screen.addPreference(preference);
                    } else {
                        if (this.mNewPermissionsCategory == null) {
                            PreferenceCategory preferenceCategory = new PreferenceCategory(activity);
                            this.mNewPermissionsCategory = preferenceCategory;
                            preferenceCategory.setTitle(R.string.new_permissions_category);
                            this.mNewPermissionsCategory.setOrder(1);
                            screen.addPreference(this.mNewPermissionsCategory);
                        }
                        this.mNewPermissionsCategory.addPreference(preference);
                    }
                } else {
                    if (this.mCurrentPermissionsCategory == null) {
                        PreferenceCategory preferenceCategory2 = new PreferenceCategory(activity);
                        this.mCurrentPermissionsCategory = preferenceCategory2;
                        preferenceCategory2.setTitle(R.string.current_permissions_category);
                        this.mCurrentPermissionsCategory.setOrder(2);
                        screen.addPreference(this.mCurrentPermissionsCategory);
                    }
                    this.mCurrentPermissionsCategory.addPreference(preference);
                }
            }
        }
    }

    private boolean isPackageUpdated() {
        List<AppPermissionGroup> groups = this.mAppPermissions.getPermissionGroups();
        int groupCount = groups.size();
        for (int i = 0; i < groupCount; i++) {
            AppPermissionGroup group = groups.get(i);
            if (!group.isReviewRequired() && (group.getBackgroundPermissions() == null || !group.getBackgroundPermissions().isReviewRequired())) {
                return true;
            }
        }
        return false;
    }

    private void executeCallback(boolean success) {
        IntentSender intent;
        int flagMask;
        int flagValues;
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        if (success && (intent = (IntentSender) activity.getIntent().getParcelableExtra("android.intent.extra.INTENT")) != null) {
            try {
                if (!activity.getIntent().getBooleanExtra("android.intent.extra.RESULT_NEEDED", false)) {
                    flagMask = 0;
                    flagValues = 0;
                } else {
                    flagMask = 33554432;
                    flagValues = 33554432;
                }
                activity.startIntentSenderForResult(intent, -1, null, flagMask, flagValues, 0);
                return;
            } catch (IntentSender.SendIntentException e) {
                return;
            }
        }
        RemoteCallback callback = activity.getIntent().getParcelableExtra("android.intent.extra.REMOTE_CALLBACK");
        if (callback != null) {
            Bundle result = new Bundle();
            result.putBoolean("android.intent.extra.RETURN_RESULT", success);
            callback.sendResult(result);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference.PermissionPreferenceChangeListener
    public boolean shouldConfirmDefaultPermissionRevoke() {
        return !this.mHasConfirmedRevoke;
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference.PermissionPreferenceChangeListener
    public void hasConfirmDefaultPermissionRevoke() {
        this.mHasConfirmedRevoke = true;
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference.PermissionPreferenceChangeListener
    public void onPreferenceChanged(String key) {
        getPreference(key).setChanged();
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference.PermissionPreferenceOwnerFragment
    public void onDenyAnyWay(String key, int changeTarget) {
        getPreference(key).onDenyAnyWay(changeTarget);
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference.PermissionPreferenceOwnerFragment
    public void onBackgroundAccessChosen(String key, int chosenItem) {
        getPreference(key).onBackgroundAccessChosen(chosenItem);
    }

    private static class PermissionReviewPreference extends PermissionPreference {
        private final AppPermissionGroup mGroup;
        private boolean mWasChanged;

        PermissionReviewPreference(PreferenceFragmentCompat fragment, AppPermissionGroup group, PermissionPreference.PermissionPreferenceChangeListener callbacks) {
            super(fragment, group, callbacks);
            this.mGroup = group;
            updateUi();
        }

        AppPermissionGroup getGroup() {
            return this.mGroup;
        }

        void setChanged() {
            this.mWasChanged = true;
            updateUi();
        }

        boolean wasChanged() {
            return this.mWasChanged;
        }

        @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionPreference
        void updateUi() {
            if (this.mGroup == null) {
                return;
            }
            super.updateUi();
            if (isEnabled()) {
                if (this.mGroup.isReviewRequired() && !this.mWasChanged) {
                    setSummary(this.mGroup.getDescription());
                    setCheckedOverride(true);
                } else if (TextUtils.isEmpty(getSummary())) {
                    setSummary(this.mGroup.getDescription());
                }
            }
        }
    }
}
