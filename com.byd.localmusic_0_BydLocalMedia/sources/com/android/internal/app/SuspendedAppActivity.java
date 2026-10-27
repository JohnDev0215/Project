package com.android.internal.app;

import android.Manifest;
import android.app.AppGlobals;
import android.app.KeyguardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.SuspendDialogInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.audio.common.AudioFormat;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Slog;
import com.android.internal.R;
import com.android.internal.app.AlertController;
import com.android.internal.util.ArrayUtils;

/* JADX INFO: loaded from: classes4.dex */
public class SuspendedAppActivity extends AlertActivity implements DialogInterface.OnClickListener {
    public static final String EXTRA_ACTIVITY_OPTIONS = "com.android.internal.app.extra.ACTIVITY_OPTIONS";
    public static final String EXTRA_DIALOG_INFO = "com.android.internal.app.extra.DIALOG_INFO";
    public static final String EXTRA_SUSPENDED_PACKAGE = "com.android.internal.app.extra.SUSPENDED_PACKAGE";
    public static final String EXTRA_SUSPENDING_PACKAGE = "com.android.internal.app.extra.SUSPENDING_PACKAGE";
    public static final String EXTRA_UNSUSPEND_INTENT = "com.android.internal.app.extra.UNSUSPEND_INTENT";
    private static final String PACKAGE_NAME = "com.android.internal.app";
    private static final String TAG = SuspendedAppActivity.class.getSimpleName();
    private Intent mMoreDetailsIntent;
    private int mNeutralButtonAction;
    private IntentSender mOnUnsuspend;
    private Bundle mOptions;
    private PackageManager mPm;
    private SuspendDialogInfo mSuppliedDialogInfo;
    private String mSuspendedPackage;
    private Resources mSuspendingAppResources;
    private String mSuspendingPackage;
    private int mUserId;

    private CharSequence getAppLabel(String str) {
        try {
            return this.mPm.getApplicationInfoAsUser(str, 0, this.mUserId).loadLabel(this.mPm);
        } catch (PackageManager.NameNotFoundException e) {
            Slog.e(TAG, "Package " + str + " not found", e);
            return str;
        }
    }

    private Intent getMoreDetailsActivity() {
        Intent intent = new Intent(Intent.ACTION_SHOW_SUSPENDED_APP_DETAILS).setPackage(this.mSuspendingPackage);
        ResolveInfo resolveInfoResolveActivityAsUser = this.mPm.resolveActivityAsUser(intent, 786432, this.mUserId);
        if (resolveInfoResolveActivityAsUser == null || resolveInfoResolveActivityAsUser.activityInfo == null || !Manifest.permission.SEND_SHOW_SUSPENDED_APP_DETAILS.equals(resolveInfoResolveActivityAsUser.activityInfo.permission)) {
            return null;
        }
        intent.putExtra("android.intent.extra.PACKAGE_NAME", this.mSuspendedPackage).setFlags(AudioFormat.AAC_ADIF);
        return intent;
    }

    private Drawable resolveIcon() {
        Resources resources;
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        int iconResId = suspendDialogInfo != null ? suspendDialogInfo.getIconResId() : 0;
        if (iconResId == 0 || (resources = this.mSuspendingAppResources) == null) {
            return null;
        }
        try {
            return resources.getDrawable(iconResId, getTheme());
        } catch (Resources.NotFoundException unused) {
            Slog.e(TAG, "Could not resolve drawable resource id " + iconResId);
            return null;
        }
    }

    private String resolveTitle() {
        Resources resources;
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        int titleResId = suspendDialogInfo != null ? suspendDialogInfo.getTitleResId() : 0;
        if (titleResId != 0 && (resources = this.mSuspendingAppResources) != null) {
            try {
                return resources.getString(titleResId);
            } catch (Resources.NotFoundException unused) {
                Slog.e(TAG, "Could not resolve string resource id " + titleResId);
            }
        }
        return getString(R.string.app_suspended_title);
    }

    private String resolveDialogMessage() {
        Resources resources;
        CharSequence appLabel = getAppLabel(this.mSuspendedPackage);
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        if (suspendDialogInfo != null) {
            int dialogMessageResId = suspendDialogInfo.getDialogMessageResId();
            String dialogMessage = this.mSuppliedDialogInfo.getDialogMessage();
            if (dialogMessageResId != 0 && (resources = this.mSuspendingAppResources) != null) {
                try {
                    return resources.getString(dialogMessageResId, appLabel);
                } catch (Resources.NotFoundException unused) {
                    Slog.e(TAG, "Could not resolve string resource id " + dialogMessageResId);
                }
            } else if (dialogMessage != null) {
                return String.format(getResources().getConfiguration().getLocales().get(0), dialogMessage, appLabel);
            }
        }
        return getString(R.string.app_suspended_default_message, appLabel, getAppLabel(this.mSuspendingPackage));
    }

    private String resolveNeutralButtonText() {
        int i;
        Resources resources;
        int i2 = this.mNeutralButtonAction;
        if (i2 != 0) {
            if (i2 != 1) {
                Slog.w(TAG, "Unknown neutral button action: " + this.mNeutralButtonAction);
                return null;
            }
            i = R.string.app_suspended_unsuspend_message;
        } else {
            if (this.mMoreDetailsIntent == null) {
                return null;
            }
            i = R.string.app_suspended_more_details;
        }
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        int neutralButtonTextResId = suspendDialogInfo != null ? suspendDialogInfo.getNeutralButtonTextResId() : 0;
        if (neutralButtonTextResId != 0 && (resources = this.mSuspendingAppResources) != null) {
            try {
                return resources.getString(neutralButtonTextResId);
            } catch (Resources.NotFoundException unused) {
                Slog.e(TAG, "Could not resolve string resource id " + neutralButtonTextResId);
            }
        }
        return getString(i);
    }

    @Override // com.android.internal.app.AlertActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPm = getPackageManager();
        getWindow().setType(2008);
        Intent intent = getIntent();
        this.mOptions = intent.getBundleExtra(EXTRA_ACTIVITY_OPTIONS);
        int intExtra = intent.getIntExtra(Intent.EXTRA_USER_ID, -1);
        this.mUserId = intExtra;
        if (intExtra < 0) {
            Slog.wtf(TAG, "Invalid user: " + this.mUserId);
            finish();
            return;
        }
        this.mSuspendedPackage = intent.getStringExtra(EXTRA_SUSPENDED_PACKAGE);
        this.mSuspendingPackage = intent.getStringExtra(EXTRA_SUSPENDING_PACKAGE);
        this.mSuppliedDialogInfo = (SuspendDialogInfo) intent.getParcelableExtra(EXTRA_DIALOG_INFO);
        this.mOnUnsuspend = (IntentSender) intent.getParcelableExtra(EXTRA_UNSUSPEND_INTENT);
        if (this.mSuppliedDialogInfo != null) {
            try {
                this.mSuspendingAppResources = this.mPm.getResourcesForApplicationAsUser(this.mSuspendingPackage, this.mUserId);
            } catch (PackageManager.NameNotFoundException e) {
                Slog.e(TAG, "Could not find resources for " + this.mSuspendingPackage, e);
            }
        }
        SuspendDialogInfo suspendDialogInfo = this.mSuppliedDialogInfo;
        int neutralButtonAction = suspendDialogInfo != null ? suspendDialogInfo.getNeutralButtonAction() : 0;
        this.mNeutralButtonAction = neutralButtonAction;
        this.mMoreDetailsIntent = neutralButtonAction == 0 ? getMoreDetailsActivity() : null;
        AlertController.AlertParams alertParams = this.mAlertParams;
        alertParams.mIcon = resolveIcon();
        alertParams.mTitle = resolveTitle();
        alertParams.mMessage = resolveDialogMessage();
        alertParams.mPositiveButtonText = getString(17039370);
        alertParams.mNeutralButtonText = resolveNeutralButtonText();
        alertParams.mNeutralButtonListener = this;
        alertParams.mPositiveButtonListener = this;
        requestDismissKeyguardIfNeeded(alertParams.mMessage);
        setupAlert();
    }

    private void requestDismissKeyguardIfNeeded(CharSequence charSequence) {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KeyguardManager.class);
        if (keyguardManager.isKeyguardLocked()) {
            keyguardManager.requestDismissKeyguard(this, charSequence, new KeyguardManager.KeyguardDismissCallback() { // from class: com.android.internal.app.SuspendedAppActivity.1
                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissError() {
                    Slog.e(SuspendedAppActivity.TAG, "Error while dismissing keyguard. Keeping the dialog visible.");
                }

                @Override // android.app.KeyguardManager.KeyguardDismissCallback
                public void onDismissCancelled() {
                    Slog.w(SuspendedAppActivity.TAG, "Keyguard dismiss was cancelled. Finishing.");
                    SuspendedAppActivity.this.finish();
                }
            });
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -3) {
            int i2 = this.mNeutralButtonAction;
            if (i2 == 0) {
                Intent intent = this.mMoreDetailsIntent;
                if (intent != null) {
                    startActivityAsUser(intent, this.mOptions, UserHandle.of(this.mUserId));
                } else {
                    Slog.wtf(TAG, "Neutral button should not have existed!");
                }
            } else if (i2 == 1) {
                try {
                    if (ArrayUtils.contains(AppGlobals.getPackageManager().setPackagesSuspendedAsUser(new String[]{this.mSuspendedPackage}, false, null, null, null, this.mSuspendingPackage, this.mUserId), this.mSuspendedPackage)) {
                        Slog.e(TAG, "Could not unsuspend " + this.mSuspendedPackage);
                    } else {
                        sendBroadcastAsUser(new Intent().setAction(Intent.ACTION_PACKAGE_UNSUSPENDED_MANUALLY).putExtra("android.intent.extra.PACKAGE_NAME", this.mSuspendedPackage).setPackage(this.mSuspendingPackage).addFlags(16777216), UserHandle.of(this.mUserId));
                        IntentSender intentSender = this.mOnUnsuspend;
                        if (intentSender != null) {
                            try {
                                intentSender.sendIntent(this, 0, null, null, null);
                            } catch (IntentSender.SendIntentException e) {
                                Slog.e(TAG, "Error while starting intent " + this.mOnUnsuspend, e);
                            }
                        }
                    }
                } catch (RemoteException e2) {
                    Slog.e(TAG, "Can't talk to system process", e2);
                }
            } else {
                Slog.e(TAG, "Unexpected action on neutral button: " + this.mNeutralButtonAction);
            }
        }
        finish();
    }

    public static Intent createSuspendedAppInterceptIntent(String str, String str2, SuspendDialogInfo suspendDialogInfo, Bundle bundle, IntentSender intentSender, int i) {
        return new Intent().setClassName("android", SuspendedAppActivity.class.getName()).putExtra(EXTRA_SUSPENDED_PACKAGE, str).putExtra(EXTRA_DIALOG_INFO, suspendDialogInfo).putExtra(EXTRA_SUSPENDING_PACKAGE, str2).putExtra(EXTRA_UNSUSPEND_INTENT, intentSender).putExtra(EXTRA_ACTIVITY_OPTIONS, bundle).putExtra(Intent.EXTRA_USER_ID, i).setFlags(276824064);
    }
}
