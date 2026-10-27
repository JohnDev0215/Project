package p;

import android.view.View;
import androidx.lifecycle.Lifecycle$Event;
import com.google.android.material.behavior.SwipeDismissBehavior;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class tb2 implements Runnable {
    public final /* synthetic */ int c = 0;
    public boolean e;
    public final Object k;
    public final Object l;

    public tb2(androidx.lifecycle.a aVar, Lifecycle$Event lifecycle$Event) {
        v03.j(aVar, "registry");
        v03.j(lifecycle$Event, "event");
        this.k = aVar;
        this.l = lifecycle$Event;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.l;
        switch (i) {
            case 0:
                if (!this.e) {
                    ((androidx.lifecycle.a) obj).f((Lifecycle$Event) obj2);
                    this.e = true;
                }
                break;
            default:
                du2 du2Var = ((SwipeDismissBehavior) obj2).a;
                if (du2Var != null && du2Var.f()) {
                    WeakHashMap weakHashMap = zt2.a;
                    it2.m((View) obj, this);
                    break;
                }
                break;
        }
    }

    public tb2(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z) {
        this.l = swipeDismissBehavior;
        this.k = view;
        this.e = z;
    }
}
