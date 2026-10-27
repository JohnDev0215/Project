package androidx.lifecycle;

import android.app.Activity;
import android.app.FragmentManager;
import androidx.lifecycle.W;

/* JADX INFO: loaded from: classes.dex */
public abstract class U {
    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Activity activity, EnumC0980s enumC0980s) {
        A7.m.f(activity, "activity");
        A7.m.f(enumC0980s, "event");
        if (activity instanceof A) {
            A9.b bVarG = ((A) activity).g();
            if (bVarG instanceof C) {
                ((C) bVarG).c1(enumC0980s);
            }
        }
    }

    public static void b(Activity activity) {
        A7.m.f(activity, "activity");
        W.a.Companion.getClass();
        activity.registerActivityLifecycleCallbacks(new W.a());
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new W(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }
}
