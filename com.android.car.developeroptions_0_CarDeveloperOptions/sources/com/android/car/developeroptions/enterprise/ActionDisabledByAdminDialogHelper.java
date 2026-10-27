package com.android.car.developeroptions.enterprise;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.IconDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Settings$DeviceAdminSettingsActivity;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.specialaccess.deviceadmin.DeviceAdminAdd;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* JADX INFO: loaded from: classes.dex */
public class ActionDisabledByAdminDialogHelper {
    private Activity mActivity;
    private ViewGroup mDialogView;
    RestrictedLockUtils.EnforcedAdmin mEnforcedAdmin;
    private String mRestriction = null;

    public ActionDisabledByAdminDialogHelper(Activity activity) {
        this.mActivity = activity;
    }

    private int getEnforcementAdminUserId(RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        UserHandle userHandle = enforcedAdmin.user;
        if (userHandle == null) {
            return -10000;
        }
        return userHandle.getIdentifier();
    }

    private int getEnforcementAdminUserId() {
        return getEnforcementAdminUserId(this.mEnforcedAdmin);
    }

    public AlertDialog.Builder prepareDialogBuilder(String str, RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        this.mEnforcedAdmin = enforcedAdmin;
        this.mRestriction = str;
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mActivity);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(builder.getContext()).inflate(R.layout.admin_support_details_dialog, (ViewGroup) null);
        this.mDialogView = viewGroup;
        initializeDialogViews(viewGroup, this.mEnforcedAdmin.component, getEnforcementAdminUserId(), this.mRestriction);
        builder.setPositiveButton(R.string.okay, (DialogInterface.OnClickListener) null);
        builder.setView(this.mDialogView);
        maybeSetLearnMoreButton(builder);
        return builder;
    }

    void maybeSetLearnMoreButton(AlertDialog.Builder builder) {
        UserManager userManager = UserManager.get(this.mActivity.getApplicationContext());
        if (userManager.isSameProfileGroup(getEnforcementAdminUserId(this.mEnforcedAdmin), userManager.getUserHandle())) {
            builder.setNeutralButton(R.string.learn_more, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.enterprise.-$$Lambda$ActionDisabledByAdminDialogHelper$KfQOjIjymY0mGVm6RzkyKD54A94
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$maybeSetLearnMoreButton$0$ActionDisabledByAdminDialogHelper(dialogInterface, i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$maybeSetLearnMoreButton$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$maybeSetLearnMoreButton$0$ActionDisabledByAdminDialogHelper(DialogInterface dialogInterface, int i) {
        showAdminPolicies(this.mEnforcedAdmin, this.mActivity);
        this.mActivity.finish();
    }

    private void initializeDialogViews(View view, ComponentName componentName, int i, String str) {
        if (componentName == null) {
            return;
        }
        ImageView imageView = (ImageView) view.requireViewById(R.id.admin_support_icon);
        if (!RestrictedLockUtilsInternal.isAdminInCurrentUserOrProfile(this.mActivity, componentName) || !RestrictedLockUtils.isCurrentUserOrProfile(this.mActivity, i)) {
            imageView.setImageDrawable(this.mActivity.getDrawable(android.R.drawable.ic_info));
            TypedArray typedArrayObtainStyledAttributes = this.mActivity.obtainStyledAttributes(new int[]{android.R.attr.colorAccent});
            imageView.setImageTintList(ColorStateList.valueOf(typedArrayObtainStyledAttributes.getColor(0, 0)));
            typedArrayObtainStyledAttributes.recycle();
            componentName = null;
        } else {
            imageView.setImageDrawable(Utils.getBadgedIcon(IconDrawableFactory.newInstance(this.mActivity), this.mActivity.getPackageManager(), componentName.getPackageName(), i));
        }
        setAdminSupportTitle(view, str);
        setAdminSupportDetails(this.mActivity, view, new RestrictedLockUtils.EnforcedAdmin(componentName, i != -10000 ? UserHandle.of(i) : null));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    void setAdminSupportTitle(View view, String str) {
        TextView textView = (TextView) view.findViewById(R.id.admin_support_dialog_title);
        if (textView == null) {
            return;
        }
        if (str == null) {
            textView.setText(R.string.disabled_by_policy_title);
            return;
        }
        byte b = -1;
        switch (str.hashCode()) {
            case -1040305701:
                if (str.equals("no_sms")) {
                    b = 2;
                }
                break;
            case -932215031:
                if (str.equals("policy_disable_camera")) {
                    b = 3;
                }
                break;
            case 620339799:
                if (str.equals("policy_disable_screen_capture")) {
                    b = 4;
                }
                break;
            case 1416425725:
                if (str.equals("policy_suspend_packages")) {
                    b = 5;
                }
                break;
            case 1950494080:
                if (str.equals("no_outgoing_calls")) {
                    b = 1;
                }
                break;
            case 2135693260:
                if (str.equals("no_adjust_volume")) {
                    b = 0;
                }
                break;
        }
        if (b == 0) {
            textView.setText(R.string.disabled_by_policy_title_adjust_volume);
            return;
        }
        if (b == 1) {
            textView.setText(R.string.disabled_by_policy_title_outgoing_calls);
            return;
        }
        if (b == 2) {
            textView.setText(R.string.disabled_by_policy_title_sms);
            return;
        }
        if (b == 3) {
            textView.setText(R.string.disabled_by_policy_title_camera);
            return;
        }
        if (b == 4) {
            textView.setText(R.string.disabled_by_policy_title_screen_capture);
        } else if (b == 5) {
            textView.setText(R.string.disabled_by_policy_title_suspend_packages);
        } else {
            textView.setText(R.string.disabled_by_policy_title);
        }
    }

    void setAdminSupportDetails(Activity activity, View view, RestrictedLockUtils.EnforcedAdmin enforcedAdmin) {
        if (enforcedAdmin == null || enforcedAdmin.component == null) {
            return;
        }
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) activity.getSystemService("device_policy");
        if (!RestrictedLockUtilsInternal.isAdminInCurrentUserOrProfile(activity, enforcedAdmin.component) || !RestrictedLockUtils.isCurrentUserOrProfile(activity, getEnforcementAdminUserId(enforcedAdmin))) {
            enforcedAdmin.component = null;
            return;
        }
        if (enforcedAdmin.user == null) {
            enforcedAdmin.user = UserHandle.of(UserHandle.myUserId());
        }
        CharSequence shortSupportMessageForUser = UserHandle.isSameApp(Process.myUid(), 1000) ? devicePolicyManager.getShortSupportMessageForUser(enforcedAdmin.component, getEnforcementAdminUserId(enforcedAdmin)) : null;
        if (shortSupportMessageForUser != null) {
            ((TextView) view.findViewById(R.id.admin_support_msg)).setText(shortSupportMessageForUser);
        }
    }

    void showAdminPolicies(RestrictedLockUtils.EnforcedAdmin enforcedAdmin, Activity activity) {
        Intent intent = new Intent();
        if (enforcedAdmin.component != null) {
            intent.setClass(activity, DeviceAdminAdd.class);
            intent.putExtra("android.app.extra.DEVICE_ADMIN", enforcedAdmin.component);
            intent.putExtra("android.app.extra.CALLED_FROM_SUPPORT_DIALOG", true);
            activity.startActivityAsUser(intent, enforcedAdmin.user);
            return;
        }
        intent.setClass(activity, Settings$DeviceAdminSettingsActivity.class);
        intent.addFlags(268435456);
        activity.startActivity(intent);
    }
}
