package defpackage;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: OptimizationGuidePushNotificationManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ws1 {
    public static final Set<String> a;
    public static final k11 b;

    static {
        HashSet hashSet = new HashSet(1);
        Object obj = new Object[]{"__overflow"}[0];
        Objects.requireNonNull(obj);
        if (hashSet.add(obj)) {
            a = Collections.unmodifiableSet(hashSet);
            b = new k11(100, "OptimizationGuidePushNotifications", "max_cache_size");
        } else {
            throw new IllegalArgumentException("duplicate element: " + obj);
        }
    }

    public static String a(ou0 ou0Var) {
        return ar.l.b(ou0Var.toString());
    }

    public static boolean b(Set<String> set) {
        return set != null && set.equals(a);
    }
}
