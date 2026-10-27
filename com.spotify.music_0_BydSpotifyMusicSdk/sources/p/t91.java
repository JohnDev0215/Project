package p;

import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.Lifecycle$State;

/* JADX INFO: loaded from: classes.dex */
public final class t91 {
    public Lifecycle$State a;
    public p91 b;

    public final void a(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        Lifecycle$State lifecycle$StateA = lifecycle$Event.a();
        Lifecycle$State lifecycle$State = this.a;
        v03.j(lifecycle$State, "state1");
        if (lifecycle$StateA.compareTo(lifecycle$State) < 0) {
            lifecycle$State = lifecycle$StateA;
        }
        this.a = lifecycle$State;
        this.b.d(s91Var, lifecycle$Event);
        this.a = lifecycle$StateA;
    }
}
