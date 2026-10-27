package p;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.fragment.app.strictmode.FragmentReuseViolation;
import androidx.fragment.app.strictmode.FragmentStrictMode$Flag;
import androidx.fragment.app.strictmode.Violation;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class ux0 {
    public static final tx0 a = tx0.c;

    public static tx0 a(ww0 ww0Var) {
        while (ww0Var != null) {
            if (ww0Var.m()) {
                ww0Var.h();
            }
            ww0Var = ww0Var.C;
        }
        return a;
    }

    public static void b(tx0 tx0Var, Violation violation) {
        ww0 ww0VarA = violation.a();
        String name = ww0VarA.getClass().getName();
        FragmentStrictMode$Flag fragmentStrictMode$Flag = FragmentStrictMode$Flag.c;
        Set set = tx0Var.a;
        set.contains(fragmentStrictMode$Flag);
        if (set.contains(FragmentStrictMode$Flag.e)) {
            ua uaVar = new ua(name, 4, violation);
            if (!ww0VarA.m()) {
                uaVar.run();
                return;
            }
            Handler handler = ww0VarA.h().t.s;
            v03.i(handler, "fragment.parentFragmentManager.host.handler");
            if (v03.b(handler.getLooper(), Looper.myLooper())) {
                uaVar.run();
            } else {
                handler.post(uaVar);
            }
        }
    }

    public static void c(Violation violation) {
        if (Log.isLoggable("FragmentManager", 3)) {
            violation.a().getClass();
        }
    }

    public static final void d(ww0 ww0Var, String str) {
        v03.j(str, "previousFragmentId");
        FragmentReuseViolation fragmentReuseViolation = new FragmentReuseViolation(ww0Var, str);
        c(fragmentReuseViolation);
        tx0 tx0VarA = a(ww0Var);
        if (tx0VarA.a.contains(FragmentStrictMode$Flag.k) && e(tx0VarA, ww0Var.getClass(), FragmentReuseViolation.class)) {
            b(tx0VarA, fragmentReuseViolation);
        }
    }

    public static boolean e(tx0 tx0Var, Class cls, Class cls2) {
        Set set = (Set) tx0Var.b.get(cls.getName());
        if (set == null) {
            return true;
        }
        if (v03.b(cls2.getSuperclass(), Violation.class) || !ou.Z(set, cls2.getSuperclass())) {
            return !set.contains(cls2);
        }
        return false;
    }
}
