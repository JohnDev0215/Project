package androidx.activity.result;

import androidx.lifecycle.Lifecycle$Event;
import p.p91;
import p.s91;

/* JADX INFO: loaded from: classes.dex */
class ActivityResultRegistry$1 implements p91 {
    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (Lifecycle$Event.ON_START.equals(lifecycle$Event) || Lifecycle$Event.ON_STOP.equals(lifecycle$Event) || Lifecycle$Event.ON_DESTROY.equals(lifecycle$Event)) {
            throw null;
        }
    }
}
