package p;

import android.content.Context;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig$Flag;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class i72 implements vl0 {
    public final /* synthetic */ int a;
    public final xy1 b;

    public /* synthetic */ i72(xy1 xy1Var, int i) {
        this.a = i;
        this.b = xy1Var;
    }

    @Override // p.xy1
    public final Object get() {
        int i = this.a;
        xy1 xy1Var = this.b;
        switch (i) {
            case 0:
                yt ytVar = (yt) xy1Var.get();
                x42 x42Var = new x42(5);
                Priority priority = Priority.c;
                v4 v4Var = new v4(11);
                Set setEmptySet = Collections.emptySet();
                if (setEmptySet == null) {
                    throw new NullPointerException("Null flags");
                }
                v4Var.l = setEmptySet;
                v4Var.e = 30000L;
                v4Var.k = 86400000L;
                ((Map) x42Var.k).put(priority, v4Var.d());
                Priority priority2 = Priority.k;
                v4 v4Var2 = new v4(11);
                Set setEmptySet2 = Collections.emptySet();
                if (setEmptySet2 == null) {
                    throw new NullPointerException("Null flags");
                }
                v4Var2.l = setEmptySet2;
                v4Var2.e = 1000L;
                v4Var2.k = 86400000L;
                ((Map) x42Var.k).put(priority2, v4Var2.d());
                Priority priority3 = Priority.e;
                v4 v4Var3 = new v4(11);
                Set setEmptySet3 = Collections.emptySet();
                if (setEmptySet3 == null) {
                    throw new NullPointerException("Null flags");
                }
                v4Var3.l = setEmptySet3;
                v4Var3.e = 86400000L;
                v4Var3.k = 86400000L;
                Set setUnmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(SchedulerConfig$Flag.e)));
                if (setUnmodifiableSet == null) {
                    throw new NullPointerException("Null flags");
                }
                v4Var3.l = setUnmodifiableSet;
                ((Map) x42Var.k).put(priority3, v4Var3.d());
                x42Var.e = ytVar;
                if (ytVar == null) {
                    throw new NullPointerException("missing required property: clock");
                }
                if (((Map) x42Var.k).keySet().size() < Priority.values().length) {
                    throw new IllegalStateException("Not all priorities have been configured");
                }
                Map map = (Map) x42Var.k;
                x42Var.k = new HashMap();
                return new ni((yt) x42Var.e, map);
            default:
                String packageName = ((Context) xy1Var.get()).getPackageName();
                if (packageName != null) {
                    return packageName;
                }
                throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
        }
    }
}
