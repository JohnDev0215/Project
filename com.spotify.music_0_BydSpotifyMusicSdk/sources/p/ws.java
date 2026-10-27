package p;

import androidx.lifecycle.Lifecycle$Event;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ws {
    public final HashMap a = new HashMap();
    public final Map b;

    public ws(HashMap map) {
        this.b = map;
        for (Map.Entry entry : map.entrySet()) {
            Lifecycle$Event lifecycle$Event = (Lifecycle$Event) entry.getValue();
            List arrayList = (List) this.a.get(lifecycle$Event);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.a.put(lifecycle$Event, arrayList);
            }
            arrayList.add((xs) entry.getKey());
        }
    }

    public static void a(List list, s91 s91Var, Lifecycle$Event lifecycle$Event, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                xs xsVar = (xs) list.get(size);
                xsVar.getClass();
                try {
                    int i = xsVar.a;
                    Method method = xsVar.b;
                    if (i == 0) {
                        method.invoke(obj, new Object[0]);
                    } else if (i == 1) {
                        method.invoke(obj, s91Var);
                    } else if (i == 2) {
                        method.invoke(obj, s91Var, lifecycle$Event);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException("Failed to call observer method", e2.getCause());
                }
            }
        }
    }
}
