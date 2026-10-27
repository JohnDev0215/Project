package com.core.system.service.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserManager;
import com.ts.car.sdk.core.Logger;

/* JADX INFO: loaded from: classes.dex */
public class UserUnlockUtil {
    private static UserUnlockUtil mInstance = new UserUnlockUtil();
    private Context mContext;
    private boolean mIsUserUnLocked = false;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.core.system.service.utils.UserUnlockUtil.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.USER_UNLOCKED".equals(action)) {
                Logger.debug("Intent.ACTION_USER_UNLOCKED");
                UserUnlockUtil.this.mIsUserUnLocked = true;
                if (UserUnlockUtil.this.mUserUnlockListener != null) {
                    UserUnlockUtil.this.mUserUnlockListener.onUserUnlocked();
                }
            }
        }
    };
    private UserUnlockListener mUserUnlockListener;

    private UserUnlockUtil() {
    }

    public static UserUnlockUtil getInstance() {
        return mInstance;
    }

    public void init(Context context, UserUnlockListener userUnlockListener) {
        this.mContext = context;
        this.mUserUnlockListener = userUnlockListener;
        UserManager userManager = (UserManager) context.getSystemService("user");
        this.mIsUserUnLocked = userManager.isUserUnlocked();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.USER_UNLOCKED");
        this.mContext.registerReceiver(this.mReceiver, filter);
    }

    public boolean isUserUnlock() {
        return this.mIsUserUnLocked;
    }
}
