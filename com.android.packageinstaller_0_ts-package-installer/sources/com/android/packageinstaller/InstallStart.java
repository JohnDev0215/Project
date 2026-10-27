package com.android.packageinstaller;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AppGlobals;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.UserInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserManager;
import android.permission.IPermissionManager;
import android.util.Log;
import android.widget.Toast;
import com.ts.installer.CarInstallerManager;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstallStart extends Activity {
    private static final String DOWNLOADS_AUTHORITY = "downloads";
    private static final String LOG_TAG = InstallStart.class.getSimpleName();
    private boolean mAbortInstall = false;
    private IPackageManager mIPackageManager;
    private IPermissionManager mIPermissionManager;
    private UserManager mUserManager;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int sessionId;
        super.onCreate(savedInstanceState);
        this.mIPackageManager = AppGlobals.getPackageManager();
        this.mIPermissionManager = AppGlobals.getPermissionManager();
        this.mUserManager = (UserManager) getSystemService(UserManager.class);
        Intent intent = getIntent();
        String callingPackage = getCallingPackage();
        boolean isSessionInstall = "android.content.pm.action.CONFIRM_INSTALL".equals(intent.getAction());
        if (isSessionInstall) {
            sessionId = intent.getIntExtra("android.content.pm.extra.SESSION_ID", -1);
        } else {
            sessionId = -1;
        }
        if (callingPackage == null && sessionId != -1) {
            PackageInstaller packageInstaller = getPackageManager().getPackageInstaller();
            PackageInstaller.SessionInfo sessionInfo = packageInstaller.getSessionInfo(sessionId);
            callingPackage = sessionInfo != null ? sessionInfo.getInstallerPackageName() : null;
        }
        ApplicationInfo sourceInfo = getSourceInfo(callingPackage);
        int originatingUid = getOriginatingUid(sourceInfo);
        if (isInstallAppDenied(originatingUid, callingPackage)) {
            Log.w(LOG_TAG, "Prohibited to install from unknown source, originatingUid:" + originatingUid + ", callingPackage:" + callingPackage);
            Toast.makeText(this, R.string.ban_install, 0).show();
            setResult(0);
            finish();
            return;
        }
        boolean isTrustedSource = false;
        if (sourceInfo != null && (sourceInfo.privateFlags & 8) != 0) {
            isTrustedSource = intent.getBooleanExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", false);
        }
        if (!isTrustedSource && originatingUid != -1) {
            int targetSdkVersion = PackageUtil.getMaxTargetSdkVersionForUid(this, originatingUid);
            if (targetSdkVersion < 0) {
                Log.w(LOG_TAG, "Cannot get target sdk version for uid " + originatingUid);
                this.mAbortInstall = true;
            } else if (targetSdkVersion >= 26 && !declaresAppOpPermission(originatingUid, "android.permission.REQUEST_INSTALL_PACKAGES")) {
                Log.e(LOG_TAG, "Requesting uid " + originatingUid + " needs to declare permission android.permission.REQUEST_INSTALL_PACKAGES");
                this.mAbortInstall = true;
            }
        }
        if (this.mAbortInstall) {
            setResult(0);
            finish();
            return;
        }
        Intent nextActivity = new Intent(intent);
        nextActivity.setFlags(33554432);
        nextActivity.putExtra("EXTRA_CALLING_PACKAGE", callingPackage);
        nextActivity.putExtra("EXTRA_ORIGINAL_SOURCE_INFO", sourceInfo);
        nextActivity.putExtra("android.intent.extra.ORIGINATING_UID", originatingUid);
        if (isSessionInstall) {
            nextActivity.setClass(this, PackageInstallerActivity.class);
        } else {
            Uri packageUri = intent.getData();
            if (packageUri != null && packageUri.getScheme().equals("content")) {
                nextActivity.setClass(this, InstallStaging.class);
            } else if (packageUri != null && packageUri.getScheme().equals("package")) {
                nextActivity.setClass(this, PackageInstallerActivity.class);
            } else {
                Intent result = new Intent();
                result.putExtra("android.intent.extra.INSTALL_RESULT", -3);
                setResult(1, result);
                nextActivity = null;
            }
        }
        if (nextActivity != null) {
            startActivity(nextActivity);
        }
        finish();
    }

    private boolean isInstallAppDenied(int originatingUid, String callingPackage) {
        String originatingPackage = originatingUid != -1 ? PackageUtil.getPackageNameForUid(this, originatingUid, callingPackage) : null;
        int installPolicy = CarInstallerManager.getInstallerManager().canInstall(originatingPackage);
        return 3 == installPolicy;
    }

    private boolean declaresAppOpPermission(int uid, String permission) {
        try {
            String[] packages = this.mIPermissionManager.getAppOpPermissionPackages(permission);
            if (packages == null) {
                return false;
            }
            List<UserInfo> users = this.mUserManager.getUsers();
            for (String packageName : packages) {
                for (UserInfo user : users) {
                    if (uid == getPackageManager().getPackageUidAsUser(packageName, user.id)) {
                        return true;
                    }
                }
            }
        } catch (RemoteException e) {
        }
        return false;
    }

    private ApplicationInfo getSourceInfo(String callingPackage) {
        if (callingPackage != null) {
            try {
                return getPackageManager().getApplicationInfo(callingPackage, 0);
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }
        return null;
    }

    private int getOriginatingUid(ApplicationInfo sourceInfo) {
        int callingUid;
        int uidFromIntent = getIntent().getIntExtra("android.intent.extra.ORIGINATING_UID", -1);
        if (sourceInfo != null) {
            callingUid = sourceInfo.uid;
        } else {
            try {
                callingUid = ActivityManager.getService().getLaunchedFromUid(getActivityToken());
            } catch (RemoteException e) {
                Log.e(LOG_TAG, "Could not determine the launching uid.");
                this.mAbortInstall = true;
                return -1;
            }
        }
        try {
            if (this.mIPackageManager.checkUidPermission("android.permission.MANAGE_DOCUMENTS", callingUid) == 0) {
                return uidFromIntent;
            }
        } catch (RemoteException e2) {
        }
        if (isSystemDownloadsProvider(callingUid)) {
            return uidFromIntent;
        }
        return callingUid;
    }

    private boolean isSystemDownloadsProvider(int uid) {
        ProviderInfo downloadProviderPackage = getPackageManager().resolveContentProvider(DOWNLOADS_AUTHORITY, 0);
        if (downloadProviderPackage == null) {
            return false;
        }
        ApplicationInfo appInfo = downloadProviderPackage.applicationInfo;
        return appInfo.isSystemApp() && uid == appInfo.uid;
    }
}
