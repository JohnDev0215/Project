package PVUVUPv.PVUVUPv.PVUVUPv.VUUUVPvVV.UUVP;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
import android.os.ServiceManager;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkActivityTaskManager;
import com.byd.dilink.app.am.IDiLinkParceledListSlice;
import com.byd.dilink.app.am.IDiLinkTaskSnapShot;
import com.byd.foundation.annotation.ServiceImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkActivityTaskManager.class, singleton = true)
public class PVUVUPv implements IDiLinkActivityTaskManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IActivityTaskManager f78a = IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkParceledListSlice<ActivityManager.RecentTaskInfo> getRecentTasksByDisplay(int i, int i2, boolean z) {
        throw new UnsupportedTargetPlatformException("RSE 没有 getRecentTasksByDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkTaskSnapShot getTaskCurrentSnapshot(int i) {
        ActivityManager.TaskSnapshot taskCurrentSnapshot = this.f78a.getTaskCurrentSnapshot(i);
        if (taskCurrentSnapshot == null) {
            return null;
        }
        return new a(taskCurrentSnapshot);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public List<ActivityManager.RunningTaskInfo> getTasksByDisplay(int i, boolean z, boolean z2, boolean z3) {
        throw new UnsupportedTargetPlatformException("RSE 没有 getTasksByDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void removePackageActivities(@NonNull String str) {
        this.f78a.removePackageActivities(str);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void swapSplitUpdateEndPkg() {
        throw new UnsupportedTargetPlatformException("RSE 没有 swapSplitUpdateEndPkg 这个函数！");
    }
}
