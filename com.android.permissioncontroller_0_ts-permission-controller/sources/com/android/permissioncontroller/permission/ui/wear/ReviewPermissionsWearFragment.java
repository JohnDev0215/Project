package com.android.permissioncontroller.permission.ui.wear;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.RemoteCallback;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import androidx.preference.TwoStatePreference;
import androidx.wear.ble.view.WearableDialogHelper;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class ReviewPermissionsWearFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    private static final String EXTRA_PACKAGE_INFO = "com.android.permissioncontroller.permission.ui.extra.PACKAGE_INFO";
    private static final int ORDER_ACTION = 100000;
    private static final int ORDER_CURRENT_PERMS = 2;
    private static final int ORDER_NEW_PERMS = 1;
    private static final int ORDER_PERM_OFFSET_START = 100;
    private static final String TAG = "ReviewPermWear";
    private AppPermissions mAppPermissions;
    private boolean mHasConfirmedRevoke;
    private PreferenceCategory mNewPermissionsCategory;

    public static ReviewPermissionsWearFragment newInstance(PackageInfo packageInfo) {
        Bundle arguments = new Bundle();
        arguments.putParcelable(EXTRA_PACKAGE_INFO, packageInfo);
        ReviewPermissionsWearFragment instance = new ReviewPermissionsWearFragment();
        instance.setArguments(arguments);
        instance.setRetainInstance(true);
        return instance;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        PackageInfo packageInfo = (PackageInfo) getArguments().getParcelable(EXTRA_PACKAGE_INFO);
        if (packageInfo == null) {
            activity.finish();
            return;
        }
        AppPermissions appPermissions = new AppPermissions(activity, packageInfo, false, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$ReviewPermissionsWearFragment$Kdmrks4lDR6cFCWKYuKI1ukChy4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onCreatePreferences$0$ReviewPermissionsWearFragment();
            }
        });
        this.mAppPermissions = appPermissions;
        if (appPermissions.getPermissionGroups().isEmpty()) {
            activity.finish();
            return;
        }
        boolean reviewRequired = false;
        Iterator<AppPermissionGroup> it = this.mAppPermissions.getPermissionGroups().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            AppPermissionGroup group = it.next();
            if (group.isReviewRequired()) {
                reviewRequired = true;
                break;
            }
        }
        if (!reviewRequired) {
            activity.finish();
        }
    }

    public /* synthetic */ void lambda$onCreatePreferences$0$ReviewPermissionsWearFragment() {
        getActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mAppPermissions.refresh();
        loadPreferences();
    }

    private void loadPreferences() {
        Preference cachedPreference;
        SwitchPreference preference;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        PreferenceScreen screen = getPreferenceScreen();
        if (screen == null) {
            screen = getPreferenceManager().createPreferenceScreen(getActivity());
            setPreferenceScreen(screen);
        } else {
            screen.removeAll();
        }
        PreferenceGroup currentPermissionsCategory = null;
        PreferenceGroup oldNewPermissionsCategory = this.mNewPermissionsCategory;
        this.mNewPermissionsCategory = null;
        boolean isPackageUpdated = isPackageUpdated();
        int permOrder = 100;
        for (AppPermissionGroup group : this.mAppPermissions.getPermissionGroups()) {
            if (Utils.shouldShowPermission(getContext(), group) && Utils.OS_PKG.equals(group.getDeclaringPackage())) {
                if (oldNewPermissionsCategory == null) {
                    cachedPreference = null;
                } else {
                    cachedPreference = oldNewPermissionsCategory.findPreference(group.getName());
                }
                if (cachedPreference instanceof SwitchPreference) {
                    preference = (SwitchPreference) cachedPreference;
                } else {
                    preference = new SwitchPreference(getActivity());
                    preference.setKey(group.getName());
                    preference.setTitle(group.getLabel());
                    preference.setPersistent(false);
                    preference.setOrder(permOrder);
                    preference.setOnPreferenceChangeListener(this);
                    permOrder++;
                }
                preference.setChecked(group.areRuntimePermissionsGranted());
                if (group.isSystemFixed() || group.isPolicyFixed()) {
                    preference.setEnabled(false);
                } else {
                    preference.setEnabled(true);
                }
                if (group.isReviewRequired()) {
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
                    if (currentPermissionsCategory == null) {
                        currentPermissionsCategory = new PreferenceCategory(activity);
                        currentPermissionsCategory.setTitle(R.string.current_permissions_category);
                        currentPermissionsCategory.setOrder(2);
                        screen.addPreference(currentPermissionsCategory);
                    }
                    currentPermissionsCategory.addPreference(preference);
                }
            }
        }
        addTitlePreferenceToScreen(screen);
        addActionPreferencesToScreen(screen);
    }

    private boolean isPackageUpdated() {
        List<AppPermissionGroup> groups = this.mAppPermissions.getPermissionGroups();
        int groupCount = groups.size();
        for (int i = 0; i < groupCount; i++) {
            AppPermissionGroup group = groups.get(i);
            if (!group.isReviewRequired()) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Log.d(TAG, "onPreferenceChange " + ((Object) preference.getTitle()));
        if (this.mHasConfirmedRevoke) {
            return true;
        }
        if (preference instanceof SwitchPreference) {
            SwitchPreference switchPreference = (SwitchPreference) preference;
            if (!switchPreference.isChecked()) {
                return true;
            }
            showWarnRevokeDialog(switchPreference);
            return false;
        }
        return false;
    }

    private void showWarnRevokeDialog(final SwitchPreference preference) {
        new WearableDialogHelper.DialogBuilder(getContext()).setPositiveIcon(R.drawable.cancel_button).setNegativeIcon(R.drawable.confirm_button).setPositiveButton(R.string.cancel, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.grant_dialog_button_deny_anyway, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$ReviewPermissionsWearFragment$7MnwKJGZtTfaICytthTglbdDFxA
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showWarnRevokeDialog$1$ReviewPermissionsWearFragment(preference, dialogInterface, i);
            }
        }).setMessage(R.string.old_sdk_deny_warning).show();
    }

    public /* synthetic */ void lambda$showWarnRevokeDialog$1$ReviewPermissionsWearFragment(SwitchPreference preference, DialogInterface dialog, int which) {
        preference.setChecked(false);
        this.mHasConfirmedRevoke = true;
    }

    private void confirmPermissionsReview() {
        PreferenceGroup preferenceGroup = this.mNewPermissionsCategory;
        if (preferenceGroup == null) {
            preferenceGroup = getPreferenceScreen();
        }
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            if (preference instanceof TwoStatePreference) {
                TwoStatePreference twoStatePreference = (TwoStatePreference) preference;
                String groupName = preference.getKey();
                AppPermissionGroup group = this.mAppPermissions.getPermissionGroup(groupName);
                if (twoStatePreference.isChecked()) {
                    group.grantRuntimePermissions(true, false);
                } else {
                    group.revokeRuntimePermissions(false);
                }
                group.unsetReviewRequired();
            }
        }
    }

    private void addTitlePreferenceToScreen(PreferenceScreen screen) {
        int labelTemplateResId;
        FragmentActivity activity = getActivity();
        Preference titlePref = new Preference(activity);
        screen.addPreference(titlePref);
        Drawable icon = this.mAppPermissions.getPackageInfo().applicationInfo.loadIcon(activity.getPackageManager());
        titlePref.setIcon(icon);
        String appLabel = this.mAppPermissions.getAppLabel().toString();
        if (isPackageUpdated()) {
            labelTemplateResId = R.string.permission_review_title_template_update;
        } else {
            labelTemplateResId = R.string.permission_review_title_template_install;
        }
        SpannableString message = new SpannableString(Html.fromHtml(getString(labelTemplateResId, appLabel)));
        int appLabelStart = message.toString().indexOf(appLabel, 0);
        int appLabelLength = appLabel.length();
        TypedValue typedValue = new TypedValue();
        activity.getTheme().resolveAttribute(android.R.attr.colorAccent, typedValue, true);
        int color = activity.getColor(typedValue.resourceId);
        if (appLabelStart >= 0) {
            message.setSpan(new ForegroundColorSpan(color), appLabelStart, appLabelStart + appLabelLength, 0);
        }
        titlePref.setTitle(message);
        titlePref.setSelectable(false);
        titlePref.setLayoutResource(R.layout.wear_review_permission_title_pref);
    }

    private void addActionPreferencesToScreen(PreferenceScreen screen) {
        final Activity activity = getActivity();
        Preference cancelPref = new Preference(activity);
        cancelPref.setTitle(R.string.review_button_cancel);
        cancelPref.setOrder(ORDER_ACTION);
        cancelPref.setEnabled(true);
        cancelPref.setLayoutResource(R.layout.wear_review_permission_action_pref);
        cancelPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$ReviewPermissionsWearFragment$g6Fkjsk3l96wMHadTP_wOO-qjTg
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$addActionPreferencesToScreen$2$ReviewPermissionsWearFragment(activity, preference);
            }
        });
        screen.addPreference(cancelPref);
        Preference continuePref = new Preference(activity);
        continuePref.setTitle(R.string.review_button_continue);
        continuePref.setOrder(100001);
        continuePref.setEnabled(true);
        continuePref.setLayoutResource(R.layout.wear_review_permission_action_pref);
        continuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.wear.-$$Lambda$ReviewPermissionsWearFragment$FOLw__IrtfGHrS2UwbDM5ok23YM
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$addActionPreferencesToScreen$3$ReviewPermissionsWearFragment(preference);
            }
        });
        screen.addPreference(continuePref);
    }

    public /* synthetic */ boolean lambda$addActionPreferencesToScreen$2$ReviewPermissionsWearFragment(Activity activity, Preference p) {
        executeCallback(false);
        activity.setResult(0);
        activity.finish();
        return true;
    }

    public /* synthetic */ boolean lambda$addActionPreferencesToScreen$3$ReviewPermissionsWearFragment(Preference p) {
        confirmPermissionsReview();
        executeCallback(true);
        getActivity().finish();
        return true;
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
}
