package defpackage;

import android.os.Handler;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import java.util.LinkedHashMap;
import java.util.Set;

/* JADX INFO: compiled from: FragmentStrictMode.kt */
/* JADX INFO: loaded from: classes.dex */
public final class tp0 {
    public static final b a = b.c;

    /* JADX INFO: compiled from: FragmentStrictMode.kt */
    public enum a {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_WRONG_NESTED_HIERARCHY,
        /* JADX INFO: Fake field, exist only in values array */
        DETECT_RETAIN_INSTANCE_USAGE,
        /* JADX INFO: Fake field, exist only in values array */
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER
    }

    /* JADX INFO: compiled from: FragmentStrictMode.kt */
    public static final class b {
        public static final b c = new b();
        public final Set<a> a = jh0.e;
        public final LinkedHashMap b = new LinkedHashMap();
    }

    public static b a(Fragment fragment) {
        while (fragment != null) {
            if (fragment.V()) {
                fragment.M();
            }
            fragment = fragment.y;
        }
        return a;
    }

    public static void b(b bVar, ko3 ko3Var) {
        Fragment fragment = ko3Var.e;
        String name = fragment.getClass().getName();
        a aVar = a.PENALTY_LOG;
        Set<a> set = bVar.a;
        set.contains(aVar);
        if (set.contains(a.PENALTY_DEATH)) {
            a9 a9Var = new a9(name, 2, ko3Var);
            if (!fragment.V()) {
                a9Var.run();
                return;
            }
            Handler handler = fragment.M().u.m;
            i21.d(handler, "fragment.parentFragmentManager.host.handler");
            if (i21.a(handler.getLooper(), Looper.myLooper())) {
                a9Var.run();
            } else {
                handler.post(a9Var);
            }
        }
    }

    public static final void c(Fragment fragment, String str) {
        i21.e(fragment, "fragment");
        i21.e(str, "previousFragmentId");
        op0 op0Var = new op0(fragment, str);
        b bVarA = a(fragment);
        if (bVarA.a.contains(a.DETECT_FRAGMENT_REUSE) && d(bVarA, fragment.getClass(), op0.class)) {
            b(bVarA, op0Var);
        }
    }

    public static boolean d(b bVar, Class cls, Class cls2) {
        Set set = (Set) bVar.b.get(cls.getName());
        if (set == null) {
            return true;
        }
        if (i21.a(cls2.getSuperclass(), ko3.class) || !tu.p0(set, cls2.getSuperclass())) {
            return !set.contains(cls2);
        }
        return false;
    }
}
