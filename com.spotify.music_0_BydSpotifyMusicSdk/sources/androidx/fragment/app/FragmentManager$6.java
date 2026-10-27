package androidx.fragment.app;

import androidx.lifecycle.Lifecycle$Event;
import p.p91;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
class FragmentManager$6 implements p91 {
    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event == Lifecycle$Event.ON_START || lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
            throw null;
        }
    }
}
