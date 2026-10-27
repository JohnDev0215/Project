package com.ecarx.xui.adaptapi.policy;

import android.app.ActivityManagerNative;
import android.app.IActivityWatcher;
import android.content.Intent;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class ActivityWatcherPolicy implements IActivityWatcherPolicy {
    public static final String TAG = "ActivityWatcherPolicy";
    private IActivityWatcherCallback ecarx_CallBack;
    private Watcher mWatcher = new Watcher();

    private class Watcher extends IActivityWatcher.Stub {
        private Watcher() {
        }

        public void startActivity(Intent intent) throws RemoteException {
            Log.d(ActivityWatcherPolicy.TAG, "startActivity");
            ActivityWatcherPolicy.this.ecarx_CallBack.startActivity(intent);
        }

        public void activityStarting(String str, String str2, Intent intent) throws RemoteException {
            Log.d(ActivityWatcherPolicy.TAG, "activityStarting activityName = " + str2);
            ActivityWatcherPolicy.this.ecarx_CallBack.activityStarting(str, str2, intent);
        }

        public void activityOnResume(String str, String str2, Intent intent) throws RemoteException {
            Log.d(ActivityWatcherPolicy.TAG, "activityOnResume activityName = " + str2);
            ActivityWatcherPolicy.this.ecarx_CallBack.activityOnResume(str, str2, intent);
        }
    }

    @Override // com.ecarx.xui.adaptapi.policy.IActivityWatcherPolicy
    public void registerActivityWatcher(IActivityWatcherCallback iActivityWatcherCallback) {
        Log.d(TAG, "registerActivityWatcher");
        if (iActivityWatcherCallback != null) {
            try {
                this.ecarx_CallBack = iActivityWatcherCallback;
                ActivityManagerNative.getDefault().setActivityWatcher(this.mWatcher);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                return;
            }
        }
        Log.d(TAG, "callback == null");
    }

    @Override // com.ecarx.xui.adaptapi.policy.IActivityWatcherPolicy
    public void unregisterActivityWatcher(IActivityWatcherCallback iActivityWatcherCallback) {
        Log.d(TAG, "unregisterActivityWatcher");
    }
}
