package com.core.system.service.utils;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import com.ts.car.sdk.core.Logger;

/* JADX INFO: loaded from: classes.dex */
public class UserSwitchUtil {
    private AutoBootOnUserHelper mAutoBootOnUserHelper;
    private Context mContext;
    private UserSwitchListener mUserSwitchListener;
    private int mPrevCurrentUser = -10000;
    private int mCurrentUser = -10000;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.core.system.service.utils.UserSwitchUtil.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                Logger.warning("UserSwitchUtil-onReceive, got null intent");
                return;
            }
            String action = intent.getAction();
            byte b = -1;
            int iHashCode = action.hashCode();
            if (iHashCode != 833559602) {
                if (iHashCode == 959232034 && action.equals("android.intent.action.USER_SWITCHED")) {
                    b = 0;
                }
            } else if (action.equals("android.intent.action.USER_UNLOCKED")) {
                b = 1;
            }
            if (b != 0) {
                if (b == 1) {
                    int unlockedUserId = intent.getIntExtra("android.intent.extra.user_handle", -10000);
                    if (Process.myUserHandle().hashCode() == unlockedUserId && UserSwitchUtil.this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(UserSwitchUtil.this.mContext)) {
                        if (UserSwitchUtil.this.mCurrentUser != unlockedUserId) {
                            UserSwitchUtil.this.mCurrentUser = unlockedUserId;
                            UserSwitchUtil.this.mUserSwitchListener.onUserSwitched(UserSwitchUtil.this.mPrevCurrentUser, UserSwitchUtil.this.mCurrentUser);
                            UserSwitchUtil userSwitchUtil = UserSwitchUtil.this;
                            userSwitchUtil.mPrevCurrentUser = userSwitchUtil.mCurrentUser;
                            return;
                        }
                        Logger.debug("UserSwitchUtil-onReceive, mCurrentUser is equal with unlockedUserId. mCurrentUser:" + UserSwitchUtil.this.mCurrentUser);
                        return;
                    }
                    return;
                }
                Logger.warning("UserSwitchUtil-onReceive unexpected action:" + action);
                return;
            }
            Logger.debug("UserSwitchUtil-onReceive, action：" + action);
            int userId = intent.getIntExtra("android.intent.extra.user_handle", -10000);
            if (-10000 != userId) {
                if (UserSwitchUtil.this.mCurrentUser != userId) {
                    UserSwitchUtil.this.mCurrentUser = userId;
                    if (UserSwitchUtil.this.mUserSwitchListener != null) {
                        UserSwitchUtil.this.mUserSwitchListener.onUserSwitched(UserSwitchUtil.this.mPrevCurrentUser, UserSwitchUtil.this.mCurrentUser);
                        UserSwitchUtil userSwitchUtil2 = UserSwitchUtil.this;
                        userSwitchUtil2.mPrevCurrentUser = userSwitchUtil2.mCurrentUser;
                        return;
                    }
                    return;
                }
                Logger.debug("UserSwitchUtil-onReceive, mCurrentUser is equal with switched userId. mCurrentUser:" + UserSwitchUtil.this.mCurrentUser);
                return;
            }
            Logger.warning("UserSwitchUtil-onReceive, userHandle is null! action:" + action);
        }
    };

    public UserSwitchUtil(Context context) {
        this.mContext = context;
        this.mAutoBootOnUserHelper = new AutoBootOnUserHelper(this.mContext);
    }

    public void init(UserSwitchListener userSwitchListener) {
        this.mUserSwitchListener = userSwitchListener;
        this.mCurrentUser = ActivityManager.getCurrentUser();
        Logger.debug("UserSwitchUtil, currentUserId:" + this.mCurrentUser);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_SWITCHED");
        filter.addAction("android.intent.action.USER_UNLOCKED");
        this.mContext.registerReceiverForAllUsers(this.mReceiver, filter, null, null);
        if (this.mAutoBootOnUserHelper.isCurrentUserForgroundUser(this.mContext) && this.mAutoBootOnUserHelper.isCurrentUserUnlocked(this.mContext)) {
            this.mUserSwitchListener.onUserSwitched(this.mPrevCurrentUser, this.mCurrentUser);
        }
    }
}
