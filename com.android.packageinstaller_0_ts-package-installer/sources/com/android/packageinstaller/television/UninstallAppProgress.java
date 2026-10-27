package com.android.packageinstaller.television;

import android.app.Activity;
import android.app.admin.IDevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.IPackageManager;
import android.content.pm.UserInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.Toast;
import com.android.packageinstaller.PackageUtil;
import com.android.packageinstaller.R;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallAppProgress extends Activity {
    private static final String FRAGMENT_TAG = "progress_fragment";
    private static final int QUICK_INSTALL_DELAY_MILLIS = 500;
    private static final String TAG = "UninstallAppProgress";
    private static final int UNINSTALL_COMPLETE = 1;
    private static final int UNINSTALL_IS_SLOW = 2;
    private boolean mAllUsers;
    private ApplicationInfo mAppInfo;
    private IBinder mCallback;
    private boolean mIsViewInitialized;
    private volatile int mResultCode = -1;
    private Handler mHandler = new MessageHandler(this);

    public interface ProgressFragment {
        void setDeviceManagerButtonVisible(boolean z);

        void setUsersButtonVisible(boolean z);

        void showCompletion(CharSequence charSequence);
    }

    private static class MessageHandler extends Handler {
        private final WeakReference<UninstallAppProgress> mActivity;

        public MessageHandler(UninstallAppProgress activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            UninstallAppProgress activity = this.mActivity.get();
            if (activity != null) {
                activity.handleMessage(msg);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMessage(Message msg) {
        String statusText;
        if (isFinishing() || isDestroyed()) {
            return;
        }
        int i = msg.what;
        if (i != 1) {
            if (i == 2) {
                initView();
                return;
            }
            return;
        }
        this.mHandler.removeMessages(2);
        if (msg.arg1 != 1) {
            initView();
        }
        this.mResultCode = msg.arg1;
        String packageName = (String) msg.obj;
        IBinder iBinder = this.mCallback;
        if (iBinder != null) {
            IPackageDeleteObserver2 observer = IPackageDeleteObserver2.Stub.asInterface(iBinder);
            try {
                observer.onPackageDeleted(this.mAppInfo.packageName, this.mResultCode, packageName);
            } catch (RemoteException e) {
            }
            finish();
            return;
        }
        if (getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false)) {
            Intent result = new Intent();
            result.putExtra("android.intent.extra.INSTALL_RESULT", this.mResultCode);
            setResult(this.mResultCode == 1 ? -1 : 1, result);
            finish();
            return;
        }
        int i2 = msg.arg1;
        if (i2 == -4) {
            UserManager userManager = (UserManager) getSystemService("user");
            IPackageManager packageManager = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
            List<UserInfo> users = userManager.getUsers();
            int blockingUserId = -10000;
            int i3 = 0;
            while (true) {
                if (i3 >= users.size()) {
                    break;
                }
                UserInfo user = users.get(i3);
                try {
                    if (packageManager.getBlockUninstallForUser(packageName, user.id)) {
                        blockingUserId = user.id;
                        break;
                    }
                    continue;
                } catch (RemoteException e2) {
                    Log.e(TAG, "Failed to talk to package manager", e2);
                }
                i3++;
            }
            int myUserId = UserHandle.myUserId();
            if (isProfileOfOrSame(userManager, myUserId, blockingUserId)) {
                getProgressFragment().setDeviceManagerButtonVisible(true);
            } else {
                getProgressFragment().setDeviceManagerButtonVisible(false);
                getProgressFragment().setUsersButtonVisible(true);
            }
            if (blockingUserId == 0) {
                statusText = getString(R.string.uninstall_blocked_device_owner);
            } else if (blockingUserId == -10000) {
                Log.d(TAG, "Uninstall failed for " + packageName + " with code " + msg.arg1 + " no blocking user");
                statusText = getString(R.string.uninstall_failed);
            } else {
                String statusText2 = this.mAllUsers ? getString(R.string.uninstall_all_blocked_profile_owner) : getString(R.string.uninstall_blocked_profile_owner);
                statusText = statusText2;
            }
        } else if (i2 == -2) {
            UserManager userManager2 = (UserManager) getSystemService("user");
            IDevicePolicyManager dpm = IDevicePolicyManager.Stub.asInterface(ServiceManager.getService("device_policy"));
            int myUserId2 = UserHandle.myUserId();
            UserInfo otherBlockingUser = null;
            Iterator it = userManager2.getUsers().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                UserInfo user2 = (UserInfo) it.next();
                if (!isProfileOfOrSame(userManager2, myUserId2, user2.id)) {
                    try {
                        if (dpm.packageHasActiveAdmins(packageName, user2.id)) {
                            otherBlockingUser = user2;
                            break;
                        }
                    } catch (RemoteException e3) {
                        Log.e(TAG, "Failed to talk to package manager", e3);
                    }
                }
            }
            if (otherBlockingUser == null) {
                Log.d(TAG, "Uninstall failed because " + packageName + " is a device admin");
                getProgressFragment().setDeviceManagerButtonVisible(true);
                statusText = getString(R.string.uninstall_failed_device_policy_manager);
            } else {
                Log.d(TAG, "Uninstall failed because " + packageName + " is a device admin of user " + otherBlockingUser);
                getProgressFragment().setDeviceManagerButtonVisible(false);
                statusText = String.format(getString(R.string.uninstall_failed_device_policy_manager_of_user), otherBlockingUser.name);
            }
        } else {
            if (i2 == 1) {
                String statusText3 = getString(R.string.uninstall_done);
                Context ctx = getBaseContext();
                Toast.makeText(ctx, statusText3, 1).show();
                setResultAndFinish();
                return;
            }
            Log.d(TAG, "Uninstall failed for " + packageName + " with code " + msg.arg1);
            statusText = getString(R.string.uninstall_failed);
        }
        getProgressFragment().showCompletion(statusText);
    }

    private boolean isProfileOfOrSame(UserManager userManager, int userId, int profileId) {
        if (userId == profileId) {
            return true;
        }
        UserInfo parentUser = userManager.getProfileParent(profileId);
        return parentUser != null && parentUser.id == userId;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Intent intent = getIntent();
        this.mAppInfo = (ApplicationInfo) intent.getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        this.mCallback = intent.getIBinderExtra("android.content.pm.extra.CALLBACK");
        if (icicle != null) {
            this.mResultCode = -1;
            IBinder iBinder = this.mCallback;
            if (iBinder != null) {
                IPackageDeleteObserver2 observer = IPackageDeleteObserver2.Stub.asInterface(iBinder);
                try {
                    observer.onPackageDeleted(this.mAppInfo.packageName, this.mResultCode, (String) null);
                } catch (RemoteException e) {
                }
                finish();
                return;
            }
            setResultAndFinish();
            return;
        }
        this.mAllUsers = intent.getBooleanExtra("android.intent.extra.UNINSTALL_ALL_USERS", false);
        UserHandle user = (UserHandle) intent.getParcelableExtra("android.intent.extra.USER");
        if (user == null) {
            user = Process.myUserHandle();
        }
        PackageDeleteObserver observer2 = new PackageDeleteObserver();
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setStatusBarColor(0);
        getWindow().setNavigationBarColor(0);
        try {
            getPackageManager().deletePackageAsUser(this.mAppInfo.packageName, observer2, this.mAllUsers ? 2 : 0, user.getIdentifier());
        } catch (IllegalArgumentException e2) {
            Log.w(TAG, "Could not find package, not deleting " + this.mAppInfo.packageName, e2);
        }
        Handler handler = this.mHandler;
        handler.sendMessageDelayed(handler.obtainMessage(2), 500L);
    }

    public ApplicationInfo getAppInfo() {
        return this.mAppInfo;
    }

    private class PackageDeleteObserver extends IPackageDeleteObserver.Stub {
        private PackageDeleteObserver() {
        }

        public void packageDeleted(String packageName, int returnCode) {
            Message msg = UninstallAppProgress.this.mHandler.obtainMessage(1);
            msg.arg1 = returnCode;
            msg.obj = packageName;
            UninstallAppProgress.this.mHandler.sendMessage(msg);
        }
    }

    public void setResultAndFinish() {
        setResult(this.mResultCode);
        finish();
    }

    private void initView() {
        if (this.mIsViewInitialized) {
            return;
        }
        this.mIsViewInitialized = true;
        TypedValue attribute = new TypedValue();
        getTheme().resolveAttribute(android.R.attr.windowBackground, attribute, true);
        if (attribute.type >= 28 && attribute.type <= 31) {
            getWindow().setBackgroundDrawable(new ColorDrawable(attribute.data));
        } else {
            getWindow().setBackgroundDrawable(getResources().getDrawable(attribute.resourceId, getTheme()));
        }
        getTheme().resolveAttribute(android.R.attr.navigationBarColor, attribute, true);
        getWindow().setNavigationBarColor(attribute.data);
        getTheme().resolveAttribute(android.R.attr.statusBarColor, attribute, true);
        getWindow().setStatusBarColor(attribute.data);
        boolean isUpdate = (this.mAppInfo.flags & 128) != 0;
        setTitle(isUpdate ? R.string.uninstall_update_title : R.string.uninstall_application_title);
        getFragmentManager().beginTransaction().add(android.R.id.content, new UninstallAppProgressFragment(), FRAGMENT_TAG).commitNowAllowingStateLoss();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent ev) {
        if (ev.getKeyCode() == 4) {
            if (this.mResultCode == -1) {
                return true;
            }
            setResult(this.mResultCode);
        }
        return super.dispatchKeyEvent(ev);
    }

    private ProgressFragment getProgressFragment() {
        return (ProgressFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }
}
