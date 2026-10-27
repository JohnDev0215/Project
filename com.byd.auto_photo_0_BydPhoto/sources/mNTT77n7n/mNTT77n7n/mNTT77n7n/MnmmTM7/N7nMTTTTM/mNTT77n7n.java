package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7.N7nMTTTTM;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.content.pm.ParceledListSlice;
import android.os.ServiceManager;
import android.window.TaskSnapshot;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkActivityTaskManager;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkActivityTaskManager.class, singleton = true)
public class mNTT77n7n implements IDiLinkActivityTaskManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IActivityTaskManager f3143a = IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkParceledListSlice<ActivityManager.RecentTaskInfo> getRecentTasksByDisplay(int i, int i2, boolean z) {
        ParceledListSlice recentTasksByDislay = this.f3143a.getRecentTasksByDislay(i, i2, z);
        if (recentTasksByDislay == null) {
            return null;
        }
        return a.a(recentTasksByDislay);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkTaskSnapShot getTaskCurrentSnapshot(int i) {
        TaskSnapshot taskCurrentSnapshot = this.f3143a.getTaskCurrentSnapshot(i);
        if (taskCurrentSnapshot == null) {
            return null;
        }
        return new b(taskCurrentSnapshot);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public List<ActivityManager.RunningTaskInfo> getTasksByDisplay(int i, boolean z, boolean z2, boolean z3) {
        return this.f3143a.getTasksByDislay(i, z, z2, z3);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void removePackageActivities(@NonNull String str) {
        this.f3143a.removePackageActivities(str);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void swapSplitUpdateEndPkg() {
        throw new UnsupportedTargetPlatformException("DiR 没有这个函数");
    }
}
