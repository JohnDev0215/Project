package com.android.packageinstaller;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.IPackageManager;
import android.content.pm.VersionedPackage;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import com.android.packageinstaller.EventResultPersister;
import com.android.packageinstaller.handheld.ErrorDialogFragment;
import com.android.packageinstaller.handheld.UninstallAlertDialogFragment;
import com.android.packageinstaller.television.ErrorFragment;
import com.android.packageinstaller.television.UninstallAlertFragment;
import com.android.packageinstaller.television.UninstallAppProgress;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallerActivity extends Activity {
    private static final String TAG = "UninstallerActivity";
    private static final String UNINSTALLING_CHANNEL = "uninstalling";
    private DialogInfo mDialogInfo;
    private String mPackageName;

    public static class DialogInfo {
        public ActivityInfo activityInfo;
        public boolean allUsers;
        public ApplicationInfo appInfo;
        public IBinder callback;
        public UserHandle user;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(null);
        try {
            int callingUid = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
            String callingPackage = getPackageNameForUid(callingUid);
            if (callingPackage == null) {
                Log.e(TAG, "Package not found for originating uid " + callingUid);
                setResult(1);
                finish();
                return;
            }
            AppOpsManager appOpsManager = (AppOpsManager) getSystemService("appops");
            if (appOpsManager.noteOpNoThrow("android:request_delete_packages", callingUid, callingPackage) != 0) {
                Log.e(TAG, "Install from uid " + callingUid + " disallowed by AppOps");
                setResult(1);
                finish();
                return;
            }
            if (PackageUtil.getMaxTargetSdkVersionForUid(this, callingUid) >= 28 && AppGlobals.getPackageManager().checkUidPermission("android.permission.REQUEST_DELETE_PACKAGES", callingUid) != 0 && AppGlobals.getPackageManager().checkUidPermission("android.permission.DELETE_PACKAGES", callingUid) != 0) {
                Log.e(TAG, "Uid " + callingUid + " does not have android.permission.REQUEST_DELETE_PACKAGES or android.permission.DELETE_PACKAGES");
                setResult(1);
                finish();
                return;
            }
            Intent intent = getIntent();
            Uri packageUri = intent.getData();
            if (packageUri == null) {
                Log.e(TAG, "No package URI in intent");
                showAppNotFound();
                return;
            }
            String encodedSchemeSpecificPart = packageUri.getEncodedSchemeSpecificPart();
            this.mPackageName = encodedSchemeSpecificPart;
            if (encodedSchemeSpecificPart == null) {
                Log.e(TAG, "Invalid package name in URI: " + packageUri);
                showAppNotFound();
                return;
            }
            IPackageManager pm = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
            DialogInfo dialogInfo = new DialogInfo();
            this.mDialogInfo = dialogInfo;
            dialogInfo.allUsers = intent.getBooleanExtra("android.intent.extra.UNINSTALL_ALL_USERS", false);
            if (this.mDialogInfo.allUsers && !UserManager.get(this).isAdminUser()) {
                Log.e(TAG, "Only admin user can request uninstall for all users");
                showUserIsNotAllowed();
                return;
            }
            this.mDialogInfo.user = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
            if (this.mDialogInfo.user == null) {
                this.mDialogInfo.user = Process.myUserHandle();
            } else {
                UserManager userManager = (UserManager) getSystemService("user");
                List<UserHandle> profiles = userManager.getUserProfiles();
                if (!profiles.contains(this.mDialogInfo.user)) {
                    Log.e(TAG, "User " + Process.myUserHandle() + " can't request uninstall for user " + this.mDialogInfo.user);
                    showUserIsNotAllowed();
                    return;
                }
            }
            this.mDialogInfo.callback = intent.getIBinderExtra("android.content.pm.extra.CALLBACK");
            try {
                this.mDialogInfo.appInfo = pm.getApplicationInfo(this.mPackageName, 4194304, this.mDialogInfo.user.getIdentifier());
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to get packageName. Package manager is dead?");
            }
            if (this.mDialogInfo.appInfo == null) {
                Log.e(TAG, "Invalid packageName: " + this.mPackageName);
                showAppNotFound();
                return;
            }
            String className = packageUri.getFragment();
            if (className != null) {
                try {
                    this.mDialogInfo.activityInfo = pm.getActivityInfo(new ComponentName(this.mPackageName, className), 0, this.mDialogInfo.user.getIdentifier());
                } catch (RemoteException e2) {
                    Log.e(TAG, "Unable to get className. Package manager is dead?");
                }
            }
            showConfirmationDialog();
        } catch (RemoteException e3) {
            Log.e(TAG, "Could not determine the launching uid.");
            setResult(1);
            finish();
        }
    }

    public DialogInfo getDialogInfo() {
        return this.mDialogInfo;
    }

    private void showConfirmationDialog() {
        if (isTv()) {
            showContentFragment(new UninstallAlertFragment(), 0, 0);
        } else {
            showDialogFragment(new UninstallAlertDialogFragment(), 0, 0);
        }
    }

    private void showAppNotFound() {
        if (isTv()) {
            showContentFragment(new ErrorFragment(), R.string.app_not_found_dlg_title, R.string.app_not_found_dlg_text);
        } else {
            showDialogFragment(new ErrorDialogFragment(), R.string.app_not_found_dlg_title, R.string.app_not_found_dlg_text);
        }
    }

    private void showUserIsNotAllowed() {
        if (isTv()) {
            showContentFragment(new ErrorFragment(), R.string.user_is_not_allowed_dlg_title, R.string.user_is_not_allowed_dlg_text);
        } else {
            showDialogFragment(new ErrorDialogFragment(), 0, R.string.user_is_not_allowed_dlg_text);
        }
    }

    private void showGenericError() {
        if (isTv()) {
            showContentFragment(new ErrorFragment(), R.string.generic_error_dlg_title, R.string.generic_error_dlg_text);
        } else {
            showDialogFragment(new ErrorDialogFragment(), 0, R.string.generic_error_dlg_text);
        }
    }

    private boolean isTv() {
        return (getResources().getConfiguration().uiMode & 15) == 4;
    }

    private void showContentFragment(Fragment fragment, int title, int text) {
        Bundle args = new Bundle();
        args.putInt("com.android.packageinstaller.arg.title", title);
        args.putInt("com.android.packageinstaller.arg.text", text);
        fragment.setArguments(args);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragment).commit();
    }

    private void showDialogFragment(DialogFragment fragment, int title, int text) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        Bundle args = new Bundle();
        if (title != 0) {
            args.putInt("com.android.packageinstaller.arg.title", title);
        }
        args.putInt("com.android.packageinstaller.arg.text", text);
        fragment.setArguments(args);
        fragment.show(ft, "dialog");
    }

    public void startUninstallProgress(boolean keepData) {
        boolean returnResult = getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false);
        CharSequence label = this.mDialogInfo.appInfo.loadSafeLabel(getPackageManager());
        if (!isTv()) {
            if (returnResult || this.mDialogInfo.callback != null || getCallingActivity() != null) {
                Intent newIntent = new Intent(this, (Class<?>) UninstallUninstalling.class);
                newIntent.putExtra("android.intent.extra.USER", this.mDialogInfo.user);
                newIntent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", this.mDialogInfo.allUsers);
                newIntent.putExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO, this.mDialogInfo.appInfo);
                newIntent.putExtra("com.android.packageinstaller.extra.APP_LABEL", label);
                newIntent.putExtra("com.android.packageinstaller.extra.KEEP_DATA", keepData);
                newIntent.putExtra("android.content.pm.extra.CALLBACK", this.mDialogInfo.callback);
                if (returnResult) {
                    newIntent.putExtra("android.intent.extra.RETURN_RESULT", true);
                }
                if (returnResult || getCallingActivity() != null) {
                    newIntent.addFlags(33554432);
                }
                startActivity(newIntent);
                return;
            }
            try {
                int uninstallId = UninstallEventReceiver.getNewId(this);
                Intent broadcastIntent = new Intent(this, (Class<?>) UninstallFinish.class);
                broadcastIntent.setFlags(268435456);
                broadcastIntent.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", this.mDialogInfo.allUsers);
                broadcastIntent.putExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO, this.mDialogInfo.appInfo);
                broadcastIntent.putExtra("com.android.packageinstaller.extra.APP_LABEL", label);
                broadcastIntent.putExtra("com.android.packageinstaller.extra.UNINSTALL_ID", uninstallId);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, uninstallId, broadcastIntent, 134217728);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
                NotificationChannel uninstallingChannel = new NotificationChannel(UNINSTALLING_CHANNEL, getString(R.string.uninstalling_notification_channel), 1);
                notificationManager.createNotificationChannel(uninstallingChannel);
                Notification uninstallingNotification = new Notification.Builder(this, UNINSTALLING_CHANNEL).setSmallIcon(R.drawable.ic_remove).setProgress(0, 1, true).setContentTitle(getString(R.string.uninstalling_app, new Object[]{label})).setOngoing(true).build();
                notificationManager.notify(uninstallId, uninstallingNotification);
                try {
                    Log.i(TAG, "Uninstalling extras=" + broadcastIntent.getExtras());
                    int flags = this.mDialogInfo.allUsers ? 2 : 0;
                    ActivityThread.getPackageManager().getPackageInstaller().uninstall(new VersionedPackage(this.mDialogInfo.appInfo.packageName, -1), getPackageName(), flags | (keepData ? 1 : 0), pendingIntent.getIntentSender(), this.mDialogInfo.user.getIdentifier());
                    return;
                } catch (Exception e) {
                    notificationManager.cancel(uninstallId);
                    Log.e(TAG, "Cannot start uninstall", e);
                    showGenericError();
                    return;
                }
            } catch (EventResultPersister.OutOfIdsException e2) {
                showGenericError();
                return;
            }
        }
        Intent newIntent2 = new Intent("android.intent.action.VIEW");
        newIntent2.putExtra("android.intent.extra.USER", this.mDialogInfo.user);
        newIntent2.putExtra("android.intent.extra.UNINSTALL_ALL_USERS", this.mDialogInfo.allUsers);
        newIntent2.putExtra("android.content.pm.extra.CALLBACK", this.mDialogInfo.callback);
        newIntent2.putExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO, this.mDialogInfo.appInfo);
        if (returnResult) {
            newIntent2.putExtra("android.intent.extra.RETURN_RESULT", true);
            newIntent2.addFlags(33554432);
        }
        newIntent2.setClass(this, UninstallAppProgress.class);
        startActivity(newIntent2);
    }

    public void dispatchAborted() {
        DialogInfo dialogInfo = this.mDialogInfo;
        if (dialogInfo != null && dialogInfo.callback != null) {
            IPackageDeleteObserver2 observer = IPackageDeleteObserver2.Stub.asInterface(this.mDialogInfo.callback);
            try {
                observer.onPackageDeleted(this.mPackageName, -5, "Cancelled by user");
            } catch (RemoteException e) {
            }
        }
    }

    private String getPackageNameForUid(int sourceUid) {
        String[] packagesForUid = getPackageManager().getPackagesForUid(sourceUid);
        if (packagesForUid == null) {
            return null;
        }
        return packagesForUid[0];
    }
}
