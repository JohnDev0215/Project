package androidx.lifecycle;

import p.n70;
import p.o70;
import p.p91;
import p.s91;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultLifecycleObserverAdapter implements p91 {
    public final n70 c;
    public final p91 e;

    public DefaultLifecycleObserverAdapter(n70 n70Var, p91 p91Var) {
        v03.j(n70Var, "defaultLifecycleObserver");
        this.c = n70Var;
        this.e = p91Var;
    }

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        int i = o70.a[lifecycle$Event.ordinal()];
        n70 n70Var = this.c;
        switch (i) {
            case 1:
                n70Var.onCreate(s91Var);
                break;
            case 2:
                n70Var.onStart(s91Var);
                break;
            case 3:
                n70Var.onResume(s91Var);
                break;
            case 4:
                n70Var.onPause(s91Var);
                break;
            case 5:
                n70Var.onStop(s91Var);
                break;
            case 6:
                n70Var.onDestroy(s91Var);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        p91 p91Var = this.e;
        if (p91Var != null) {
            p91Var.d(s91Var, lifecycle$Event);
        }
    }
}
