package GGW.GGW.GGW.W4H.hGHGh;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.content.pm.ParceledListSlice;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.window.TaskSnapshot;
import androidx.annotation.NonNull;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkActivityTaskManager;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkActivityTaskManager.class, singleton = true)
public class GGW implements IDiLinkActivityTaskManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IActivityTaskManager f49a = IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkParceledListSlice<ActivityManager.RecentTaskInfo> getRecentTasksByDisplay(int i, int i2, boolean z) {
        ParceledListSlice recentTasksByDislay = this.f49a.getRecentTasksByDislay(i, i2, z);
        if (recentTasksByDislay == null) {
            return null;
        }
        return b.a(recentTasksByDislay);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkTaskSnapShot getTaskCurrentSnapshot(int i) {
        TaskSnapshot taskCurrentSnapshot = this.f49a.getTaskCurrentSnapshot(i);
        if (taskCurrentSnapshot == null) {
            return null;
        }
        return new c(taskCurrentSnapshot);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public List<ActivityManager.RunningTaskInfo> getTasksByDisplay(int i, boolean z, boolean z2, boolean z3) {
        return this.f49a.getTasksByDislay(i, z, z2, z3);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void removePackageActivities(@NonNull String str) {
        this.f49a.removePackageActivities(str);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void swapSplitUpdateEndPkg() throws RemoteException {
        throw new RemoteException("Di6.0 没有这个函数");
    }
}
