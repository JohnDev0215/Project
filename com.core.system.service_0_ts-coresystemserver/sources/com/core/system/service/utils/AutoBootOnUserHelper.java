package com.core.system.service.utils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.UserInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Log;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AutoBootOnUserHelper {
    private static final int START_SERVICE_RETRY_MILLIS = 500;
    public static final int USER_FLAG_USER_ALL = 1;
    public static final int USER_FLAG_USER_ALL_EXCLUDE_PROFILEUSER = 4;
    public static final int USER_FLAG_USER_FORGROUND = 2;
    private Context mContext;
    private boolean mDirectBootAware;
    private String mPackageName;
    private BroadcastReceiver mReceiver;
    private String mServiceAction;
    private String mServiceClassName;
    private int mUserFlag;
    private ServiceConnection serviceConnection;
    private static final String TAG = "AutoBootOnUserHelper_" + Process.myPid();
    public static boolean logSwitch = true;

    public AutoBootOnUserHelper(Context context) {
        this(context, 1, false);
    }

    public AutoBootOnUserHelper(Context context, int userFlag, boolean isDirectBootAware) {
        this.mReceiver = new BroadcastReceiver() { // from class: com.core.system.service.utils.AutoBootOnUserHelper.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                AutoBootOnUserHelper.logDebug("AutoBootOnUserHelper, onReceive action:" + action);
                if (action == null) {
                    AutoBootOnUserHelper.logDebug("AutoBootOnUserHelper-onReceive, action is null!");
                    return;
                }
                int userId = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                if (-10000 == userId) {
                    AutoBootOnUserHelper.logDebug("AutoBootOnUserHelper-onReceive, userHandle is null! action:" + action);
                    return;
                }
                AutoBootOnUserHelper.logDebug("AutoBootOnUserHelper-onReceive, userid:" + userId);
                byte b = -1;
                int iHashCode = action.hashCode();
                if (iHashCode != -755112654) {
                    if (iHashCode != 833559602) {
                        if (iHashCode == 959232034 && action.equals("android.intent.action.USER_SWITCHED")) {
                            b = 2;
                        }
                    } else if (action.equals("android.intent.action.USER_UNLOCKED")) {
                        b = 1;
                    }
                } else if (action.equals("android.intent.action.USER_STARTED")) {
                    b = 0;
                }
                if (b == 0) {
                    if (AutoBootOnUserHelper.this.mDirectBootAware) {
                        AutoBootOnUserHelper.this.startServiceByUserFlag(userId);
                    }
                } else if (b == 1 || b == 2) {
                    if (!AutoBootOnUserHelper.this.mDirectBootAware) {
                        AutoBootOnUserHelper.this.startServiceByUserFlag(userId);
                    }
                } else {
                    AutoBootOnUserHelper.logDebug("onReceive, unhandled action:" + action);
                }
            }
        };
        this.serviceConnection = new ServiceConnection() { // from class: com.core.system.service.utils.AutoBootOnUserHelper.2
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        this.mContext = context;
        this.mUserFlag = userFlag;
        this.mDirectBootAware = isDirectBootAware;
    }

    public void startAutoBoot(String packageName, String serviceClassName, String serviceAction) {
        if (!isCurrentUserSystemUser()) {
            logDebug("currentUser not foreground user, uid:" + Process.myUid() + ", pid:" + Process.myPid());
            return;
        }
        this.mPackageName = packageName;
        this.mServiceClassName = serviceClassName;
        this.mServiceAction = serviceAction;
        registerUserActions();
        bootSelfOnStartedUsers();
    }

    public void stopAutoBoot() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public boolean isCurrentUserSystemUser() {
        return Process.myUserHandle().isSystem();
    }

    public boolean isCurrentUserForgroundUser(Context context) {
        return isUserForgroundUser(context, UserHandle.getUserId(Process.myUid()));
    }

    public int getMyUserId() {
        return UserHandle.getUserId(Process.myUid());
    }

    public int getForegroundUserId(Context context) {
        return ActivityManager.getCurrentUser();
    }

    public boolean isUserForgroundUser(Context context, int userId) {
        int currentUserId = ActivityManager.getCurrentUser();
        logDebug("AutoBootOnUserHelper, currentForegroundUserId:" + currentUserId);
        return userId == currentUserId;
    }

    public boolean isSystemUserHeadlessUser() {
        return SystemProperties.getBoolean("ro.fw.mu.headless_system_user", false);
    }

    public boolean isCurrentUserProfiledUser(Context context) {
        return isUserProfileUser(context, UserHandle.getUserId(Process.myUid()));
    }

    public boolean isUserProfileUser(Context context, int userId) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        return userManager.isManagedProfile(userId);
    }

    public boolean isCurrentUserUnlocked(Context context) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        return userManager.isUserUnlocked(Process.myUserHandle());
    }

    public boolean isUserUnlocked(Context context, int userId) {
        UserManager userManager = (UserManager) context.getSystemService("user");
        return userManager.isUserUnlocked(UserHandle.of(userId));
    }

    public boolean isUserRunning(Context context, int userId) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        return activityManager.isUserRunning(userId);
    }

    private void registerUserActions() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_STARTED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        filter.addAction("android.intent.action.USER_SWITCHED");
        this.mContext.registerReceiverForAllUsers(this.mReceiver, filter, null, null);
    }

    private void bootSelfOnStartedUsers() {
        UserManager userManager = (UserManager) this.mContext.getSystemService("user");
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService("activity");
        List<UserInfo> list = userManager.getUsers(false);
        for (UserInfo userInfo : list) {
            int userId = userInfo.id;
            if (!activityManager.isUserRunning(userId)) {
                logDebug("user is not running, do not need to start on it:" + userId);
            } else if (userManager.isUserUnlocked(UserHandle.of(userId)) || this.mDirectBootAware) {
                startServiceByUserFlag(userId);
            } else {
                logDebug("userId is not unlocked:" + userId + ", or not directBootAware:" + this.mDirectBootAware);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startServiceByUserFlag(int userId) {
        int i = this.mUserFlag;
        if (i == 1) {
            lambda$startServiceOnTargetUser$0$AutoBootOnUserHelper(userId);
            return;
        }
        if (i == 2) {
            if (isUserForgroundUser(this.mContext, userId)) {
                lambda$startServiceOnTargetUser$0$AutoBootOnUserHelper(userId);
                return;
            }
            logDebug("this user is not forgrounduser, do not need to start on this user:" + userId);
            return;
        }
        if (i == 4) {
            if (!isUserProfileUser(this.mContext, userId)) {
                lambda$startServiceOnTargetUser$0$AutoBootOnUserHelper(userId);
                return;
            }
            logDebug("this user is managed profile user, do not need to start self on this user:" + userId);
            return;
        }
        logWarn("mUserFlag error, abandon start service, mUserFlag:" + this.mUserFlag + ", packageName:" + this.mPackageName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startServiceOnTargetUser, reason: merged with bridge method [inline-methods] */
    public void lambda$startServiceOnTargetUser$0$AutoBootOnUserHelper(final int userId) {
        Intent intent = new Intent();
        intent.setPackage(this.mPackageName);
        String str = this.mServiceClassName;
        if (str != null) {
            intent.setClassName(this.mPackageName, str);
        }
        if (!TextUtils.isEmpty(this.mServiceAction)) {
            intent.setAction(this.mServiceAction);
        }
        boolean res = false;
        try {
            res = this.mContext.bindServiceAsUser(intent, this.serviceConnection, 1, UserHandle.of(userId));
        } catch (Throwable ex) {
            logWarn("startCoreSystemService On TargetUser error", ex);
        }
        if (!res && isUserRunning(this.mContext, userId)) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.core.system.service.utils.-$$Lambda$AutoBootOnUserHelper$TaDKXdu1r-n9PAlXMGwG2kHTncQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$startServiceOnTargetUser$0$AutoBootOnUserHelper(userId);
                }
            }, 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void logDebug(String msg) {
        if (logSwitch) {
            Log.d(TAG, msg);
        }
    }

    private static void logWarn(String msg) {
        Log.w(TAG, msg);
    }

    private static void logWarn(String msg, Throwable ex) {
        Log.w(TAG, msg, ex);
    }

    private static void setLogSwitch(boolean logSwitchs) {
        logSwitch = logSwitchs;
    }
}
