package b.b.g.l;

import android.app.ActivityManager;
import android.app.IActivityManager;
import android.app.TaskStackListener;
import android.content.ComponentName;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import java.util.List;

/* JADX INFO: compiled from: MainTaskStackListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends TaskStackListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final IActivityManager f875a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ComponentName f876b;

    public a(IActivityManager iActivityManager) {
        this.f875a = iActivityManager;
    }

    public final ComponentName d0() {
        ActivityManager.StackInfo stackInfo;
        try {
            List<ActivityManager.StackInfo> allStackInfos = this.f875a.getAllStackInfos();
            SparseArray sparseArray = new SparseArray();
            for (ActivityManager.StackInfo stackInfo2 : allStackInfos) {
                int i = stackInfo2.displayId;
                if (i != 0) {
                    Log.i("MainTaskStackListener", "displayId != 0 continue -> displayId：" + i);
                } else if (stackInfo2.taskNames.length != 0 && stackInfo2.visible && ((stackInfo = (ActivityManager.StackInfo) sparseArray.get(i)) == null || stackInfo2.position > stackInfo.position)) {
                    sparseArray.put(i, stackInfo2);
                    Log.i("MainTaskStackListener", "updating top task to: " + stackInfo2.topActivity);
                    return stackInfo2.topActivity;
                }
            }
            return null;
        } catch (RemoteException e2) {
            Log.e("MainTaskStackListener", "cannot getTasks", e2);
            return null;
        }
    }

    public ComponentName e0() {
        return this.f876b;
    }

    public void onTaskStackChanged() {
        ComponentName componentNameD0 = d0();
        this.f876b = componentNameD0;
        if (componentNameD0 == null) {
            return;
        }
        b.b.m.c.a.a("MainTaskStackListener", "onTaskStackChanged:" + this.f876b);
    }
}
