package p;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class gx1 {
    public static final SparseArray a = new SparseArray();
    public static final HashMap b;

    static {
        HashMap map = new HashMap();
        b = map;
        map.put(Priority.c, 0);
        map.put(Priority.e, 1);
        map.put(Priority.k, 2);
        for (Priority priority : map.keySet()) {
            a.append(((Integer) b.get(priority)).intValue(), priority);
        }
    }

    public static int a(Priority priority) {
        Integer num = (Integer) b.get(priority);
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }

    public static Priority b(int i) {
        Priority priority = (Priority) a.get(i);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException(u4.e("Unknown Priority for value ", i));
    }
}
