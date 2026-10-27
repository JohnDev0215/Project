package com.ts.permission;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.UserHandle;
import android.util.Log;
import com.ts.permission.server.CarPermissionManagerService;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CoreService extends Service {
    private static final int INIT_PERMISSIONS = 1;
    private static final String TAG = "TSPS_CoreService";
    private static final String THREAD_NAME = "carpermission_thread";
    private CarPermissionManagerService mCpms;
    private Handler mHandler;
    private HandlerThread mHandlerThread;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "CoreService onCreate");
        this.mCpms = new CarPermissionManagerService(this);
        HandlerThread handlerThread = new HandlerThread(THREAD_NAME);
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.permission.CoreService.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg != null && msg.what == 1) {
                    CoreService.this.initGrantPermissions();
                }
            }
        };
        registerUserAddReceiver();
        this.mHandler.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGrantPermissions() {
        PackageManager pm = getPackageManager();
        ApplicationInfo coreAppInfo = getApplicationInfo();
        List<PackageInfo> packageInfos = pm.getInstalledPackages(4096);
        if (packageInfos != null && packageInfos.size() > 0) {
            Iterator<PackageInfo> iterator = packageInfos.iterator();
            while (iterator.hasNext()) {
                PackageInfo packageInfo = iterator.next();
                int appId = UserHandle.getAppId(packageInfo.applicationInfo.uid);
                int signatureMatch = pm.checkSignatures(packageInfo.applicationInfo.uid, coreAppInfo.uid);
                if (signatureMatch == 0 && appId < 10000) {
                    iterator.remove();
                }
            }
            Log.i(TAG, "packageInfos" + packageInfos);
            this.mCpms.grantPermissions(packageInfos);
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mCpms;
    }

    private void registerUserAddReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_ADDED");
        UserAddActionReceiver receiver = new UserAddActionReceiver();
        registerReceiver(receiver, intentFilter);
    }

    private class UserAddActionReceiver extends BroadcastReceiver {
        private UserAddActionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null && "android.intent.action.USER_ADDED".equals(intent.getAction())) {
                Log.i(CoreService.TAG, "UserAddActionReceiver-onReceive user addedd broadcast");
                CoreService.this.mHandler.sendEmptyMessage(1);
            }
        }
    }
}
