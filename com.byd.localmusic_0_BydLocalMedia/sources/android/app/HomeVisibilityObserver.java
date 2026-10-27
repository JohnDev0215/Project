package android.app;

import android.app.ActivityManager;
import android.app.IProcessObserver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class HomeVisibilityObserver {
    private ActivityManager mActivityManager;
    private Context mContext;
    boolean mIsHomeActivityVisible;
    IProcessObserver.Stub mObserver = new IProcessObserver.Stub() { // from class: android.app.HomeVisibilityObserver.1
        @Override // android.app.IProcessObserver
        public void onForegroundServicesChanged(int i, int i2, int i3) {
        }

        @Override // android.app.IProcessObserver
        public void onProcessDied(int i, int i2) {
        }

        @Override // android.app.IProcessObserver
        public void onForegroundActivitiesChanged(int i, int i2, boolean z) {
            boolean zIsHomeActivityVisible = HomeVisibilityObserver.this.isHomeActivityVisible();
            if (HomeVisibilityObserver.this.mIsHomeActivityVisible != zIsHomeActivityVisible) {
                HomeVisibilityObserver.this.mIsHomeActivityVisible = zIsHomeActivityVisible;
                HomeVisibilityObserver homeVisibilityObserver = HomeVisibilityObserver.this;
                homeVisibilityObserver.onHomeVisibilityChanged(homeVisibilityObserver.mIsHomeActivityVisible);
            }
        }
    };

    public abstract void onHomeVisibilityChanged(boolean z);

    void init(Context context, ActivityManager activityManager) {
        this.mContext = context;
        this.mActivityManager = activityManager;
        this.mIsHomeActivityVisible = isHomeActivityVisible();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHomeActivityVisible() {
        String packageName;
        List<ActivityManager.RunningTaskInfo> runningTasks = this.mActivityManager.getRunningTasks(1);
        if (runningTasks == null || runningTasks.isEmpty() || (packageName = runningTasks.get(0).topActivity.getPackageName()) == null) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_MAIN, (Uri) null);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo resolveInfoResolveActivity = this.mContext.getPackageManager().resolveActivity(intent, 65536);
        return resolveInfoResolveActivity != null && packageName.equals(resolveInfoResolveActivity.activityInfo.packageName);
    }
}
