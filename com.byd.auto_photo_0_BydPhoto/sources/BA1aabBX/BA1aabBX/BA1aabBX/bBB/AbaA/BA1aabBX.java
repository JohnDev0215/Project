package BA1aabBX.BA1aabBX.BA1aabBX.bBB.AbaA;

import android.app.ActivityManager;
import android.app.IActivityTaskManager;
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

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkActivityTaskManager.class, singleton = true)
public class BA1aabBX implements IDiLinkActivityTaskManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final IActivityTaskManager f16a = IActivityTaskManager.Stub.asInterface(ServiceManager.getService("activity_task"));

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkParceledListSlice<ActivityManager.RecentTaskInfo> getRecentTasksByDisplay(int i, int i2, boolean z) {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 getRecentTasksByDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public IDiLinkTaskSnapShot getTaskCurrentSnapshot(int i) {
        TaskSnapshot taskCurrentSnapshot = this.f16a.getTaskCurrentSnapshot(i);
        if (taskCurrentSnapshot == null) {
            return null;
        }
        return new b(taskCurrentSnapshot);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public List<ActivityManager.RunningTaskInfo> getTasksByDisplay(int i, boolean z, boolean z2, boolean z3) {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 getTasksByDisplay 这个函数！");
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void removePackageActivities(@NonNull String str) {
        this.f16a.removePackageActivities(str);
    }

    @Override // com.byd.dilink.app.am.IDiLinkActivityTaskManager
    public void swapSplitUpdateEndPkg() {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 swapSplitUpdateEndPkg 这个函数！");
    }
}
