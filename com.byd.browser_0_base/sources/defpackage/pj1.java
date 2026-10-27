package defpackage;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.chrome.browser.ChromeTabbedActivity;

/* JADX INFO: compiled from: MultiInstanceState.java */
/* JADX INFO: loaded from: classes2.dex */
public final class pj1 implements ApplicationStatus.e {
    public static pj1 i;
    public final nw2<List<ActivityManager.AppTask>> e;
    public final a f;
    public final org.chromium.base.b<b> g = new org.chromium.base.b<>();
    public final SparseBooleanArray h = new SparseBooleanArray();

    /* JADX INFO: compiled from: MultiInstanceState.java */
    public interface a {
    }

    /* JADX INFO: compiled from: MultiInstanceState.java */
    public interface b {
        void a(boolean z);
    }

    public pj1(hs hsVar, pq2 pq2Var) {
        if (ApplicationStatus.h == null) {
            ApplicationStatus.h = new org.chromium.base.b<>();
        }
        ApplicationStatus.h.a(this);
        this.e = hsVar;
        this.f = pq2Var;
    }

    @Override // org.chromium.base.ApplicationStatus.e
    public final void a(int i2, boolean z) {
        boolean z2;
        ComponentName componentName;
        Iterator<ActivityManager.AppTask> it = this.e.get().iterator();
        while (true) {
            if (!it.hasNext()) {
                z2 = false;
                break;
            }
            ActivityManager.RecentTaskInfo recentTaskInfoC = k5.c(it.next());
            if (recentTaskInfoC != null && (componentName = recentTaskInfoC.baseActivity) != null && recentTaskInfoC.id == i2) {
                String className = componentName.getClassName();
                if (TextUtils.equals(className, ChromeTabbedActivity.class.getName()) || TextUtils.equals(className, (String) ((pq2) this.f).f)) {
                    z2 = true;
                    break;
                }
            }
        }
        if (!z2) {
            return;
        }
        SparseBooleanArray sparseBooleanArray = this.h;
        boolean z3 = sparseBooleanArray.size() > 1;
        if (z) {
            sparseBooleanArray.append(i2, true);
        } else {
            sparseBooleanArray.delete(i2);
        }
        boolean z4 = sparseBooleanArray.size() > 1;
        if (z3 == z4) {
            return;
        }
        Iterator<b> it2 = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((b) aVar.next()).a(z4);
            }
        }
    }
}
