package com.byd.card.floatcard;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.IProcessObserver;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.view.help.SplitScreenHelpManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TopActivityManager {
    private static final String TAG = "TopActivityChangeListener";
    private static final int TOP_ACTIVITY_CHANGE = 1000;
    private static IProcessObserver mProcessObserver;
    private static TaskListener mTaskListener;
    private static volatile TopActivityManager sInstance;
    private final Context mContext;
    private final Divider mDivider;
    private TopActivityChangeListener mTopChangeListener;
    private volatile boolean setWallPager;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.card.floatcard.TopActivityManager.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1000 && TopActivityManager.this.mTopChangeListener != null) {
                TopActivityManager.this.mTopChangeListener.onTopActivityChanged((ComponentName) msg.getData().getParcelable("top"), false);
            }
        }
    };
    private final IActivityManager mAm = ActivityManager.getService();

    public interface TopActivityCallback {
        void onTopActivityCallback(ComponentName componentName);
    }

    public interface TopActivityChangeListener {
        void onTopActivityChanged(ComponentName componentName, boolean processDied);
    }

    private TopActivityManager(Context context) {
        this.mContext = context;
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(context, Divider.class);
    }

    public static TopActivityManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (TopActivityManager.class) {
                if (sInstance == null) {
                    sInstance = new TopActivityManager(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void registerTopActivityChangeListener(TopActivityChangeListener listener) {
        mTaskListener = new TaskListener(this.mContext, listener);
        mProcessObserver = new ProcessObserver(this.mContext, listener);
        try {
            this.mAm.registerTaskStackListener(mTaskListener);
            this.mAm.registerProcessObserver(mProcessObserver);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void unregisterTopActivityChangeListener() {
        try {
            this.mAm.unregisterTaskStackListener(mTaskListener);
            this.mAm.unregisterProcessObserver(mProcessObserver);
            this.mHandler.removeCallbacksAndMessages(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private class TaskListener extends TaskStackListener {
        private final Context context;

        public TaskListener(Context context, TopActivityChangeListener listener) {
            this.context = context;
            TopActivityManager.this.mTopChangeListener = listener;
        }

        public void onTaskStackChanged() {
            final ComponentName currentTopActivity = TopActivityManager.this.getCurrentTopActivity();
            BydLogHelper.logD(TopActivityManager.TAG, "onTaskStackChanged componentName = " + currentTopActivity);
            Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.TopActivityManager.TaskListener.1
                @Override // java.lang.Runnable
                public void run() {
                    if (TopActivityManager.this.mTopChangeListener != null) {
                        TopActivityManager.this.mTopChangeListener.onTopActivityChanged(currentTopActivity, false);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class ProcessObserver extends IProcessObserver.Stub {
        private final Context context;
        private final TopActivityChangeListener listener;
        private final Runnable updateStatusBarCPAARunnable = new Runnable() { // from class: com.byd.card.floatcard.TopActivityManager.ProcessObserver.1
            @Override // java.lang.Runnable
            public void run() {
                if (TopActivityManager.this.mDivider != null) {
                    TopActivityManager.this.mDivider.updateStatusBarCPAA(true);
                }
            }
        };

        public void onForegroundServicesChanged(int pid, int uid, int fgServiceTypes) {
        }

        public ProcessObserver(Context context, TopActivityChangeListener listener) {
            this.context = context;
            this.listener = listener;
        }

        public void onForegroundActivitiesChanged(int pid, int uid, boolean foregroundActivities) {
            BydLogHelper.logD(TopActivityManager.TAG, String.format("onForegroundActivitiesChanged uid %d pid %d fg %b", Integer.valueOf(uid), Integer.valueOf(pid), Boolean.valueOf(foregroundActivities)));
            TopActivityManager topActivityManager = TopActivityManager.this;
            String packageNameByPid = topActivityManager.getPackageNameByPid(topActivityManager.mContext, pid);
            BydLogHelper.logD(TopActivityManager.TAG, "onForegroundActivitiesChanged pid : " + pid + " , packageName : " + packageNameByPid + " , foregroundActivities : " + foregroundActivities);
            if (foregroundActivities && !"com.android.launcher3".equals(packageNameByPid)) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$TopActivityManager$ProcessObserver$KiAWukLJfhoD5n3dqZ3jt8gDAJE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onForegroundActivitiesChanged$0$TopActivityManager$ProcessObserver();
                    }
                });
            }
            Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) this.context.getSystemService(Constants.PackageInfo.ACTIVITY)).getRunningAppProcesses().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (pid == it.next().pid) {
                    final ComponentName currentTopActivity = TopActivityManager.this.getCurrentTopActivity();
                    if (this.listener != null) {
                        Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$TopActivityManager$ProcessObserver$q9hdd2TAdmkGSdx9UMyYd-sihYk
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$onForegroundActivitiesChanged$1$TopActivityManager$ProcessObserver(currentTopActivity);
                            }
                        });
                    }
                }
            }
            if (TopActivityManager.this.mDivider == null || packageNameByPid == null) {
                return;
            }
            if (packageNameByPid.equals("com.ts.androidauto.app") || packageNameByPid.equals("com.ts.carplay.app")) {
                if (foregroundActivities) {
                    BydLogHelper.logD(TopActivityManager.TAG, "isSplitActive : " + TopActivityManager.this.mDivider.isSplitActive());
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).postDelayed(this.updateStatusBarCPAARunnable, 500L);
                } else {
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).removeCallbacks(this.updateStatusBarCPAARunnable);
                    ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.byd.card.floatcard.-$$Lambda$TopActivityManager$ProcessObserver$waakeMR-O1VpBhS5r3rtv61pc0k
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$onForegroundActivitiesChanged$2$TopActivityManager$ProcessObserver();
                        }
                    });
                }
            }
        }

        public /* synthetic */ void lambda$onForegroundActivitiesChanged$0$TopActivityManager$ProcessObserver() {
            SplitScreenHelpManager.getInstance(TopActivityManager.this.mContext).hideSplitNavView();
        }

        public /* synthetic */ void lambda$onForegroundActivitiesChanged$1$TopActivityManager$ProcessObserver(ComponentName componentName) {
            this.listener.onTopActivityChanged(componentName, false);
        }

        public /* synthetic */ void lambda$onForegroundActivitiesChanged$2$TopActivityManager$ProcessObserver() {
            TopActivityManager.this.mDivider.updateStatusBarCPAA(false);
        }

        public void onProcessDied(int pid, int uid) {
            if (this.listener != null) {
                final ComponentName currentTopActivity = TopActivityManager.this.getCurrentTopActivity();
                BydLogHelper.logD(TopActivityManager.TAG, "onProcessDied finalComponentName = " + currentTopActivity + ", pid = " + pid);
                Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.TopActivityManager.ProcessObserver.2
                    @Override // java.lang.Runnable
                    public void run() {
                        ProcessObserver.this.listener.onTopActivityChanged(currentTopActivity, true);
                    }
                });
            }
        }
    }

    public void getTopActivity(final TopActivityCallback callback) {
        Executors.MODEL_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.TopActivityManager.2
            @Override // java.lang.Runnable
            public void run() {
                final ComponentName currentTopActivity = TopActivityManager.this.getCurrentTopActivity();
                Executors.MAIN_EXECUTOR.execute(new Runnable() { // from class: com.byd.card.floatcard.TopActivityManager.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onTopActivityCallback(currentTopActivity);
                    }
                });
            }
        });
    }

    public ComponentName getCurrentTopActivity() {
        ActivityManager.StackInfo stackInfo;
        try {
            List<ActivityManager.StackInfo> allStackInfos = this.mAm.getAllStackInfos();
            SparseArray sparseArray = new SparseArray();
            for (ActivityManager.StackInfo stackInfo2 : allStackInfos) {
                int i = stackInfo2.displayId;
                if (stackInfo2.taskNames.length != 0 && stackInfo2.visible && i == 0 && ((stackInfo = (ActivityManager.StackInfo) sparseArray.get(i)) == null || stackInfo2.position > stackInfo.position)) {
                    sparseArray.put(i, stackInfo2);
                    BydLogHelper.logD(TAG, "updating top task to: " + stackInfo2.topActivity);
                    return stackInfo2.topActivity;
                }
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "cannot getTasks", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getPackageNameByPid(Context context, int pid) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService(Constants.PackageInfo.ACTIVITY)).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == pid) {
                BydLogHelper.logD("pid : " + pid + " , processName : " + runningAppProcessInfo.processName);
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    public void setWallPager(boolean wallPager) {
        this.setWallPager = wallPager;
    }

    public boolean getWallPager() {
        return this.setWallPager;
    }
}
