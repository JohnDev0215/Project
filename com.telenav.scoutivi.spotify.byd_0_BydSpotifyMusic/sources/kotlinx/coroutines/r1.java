package kotlinx.coroutines;

import java.util.Objects;
import kotlinx.coroutines.l1;

/* JADX INFO: compiled from: JobSupport.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class r1<J extends l1> extends a0 implements v0, g1 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final J f7715i;

    public r1(J j7) {
        this.f7715i = j7;
    }

    @Override // kotlinx.coroutines.v0
    public void dispose() {
        J j7 = this.f7715i;
        Objects.requireNonNull(j7, "null cannot be cast to non-null type kotlinx.coroutines.JobSupport");
        ((s1) j7).removeNode$kotlinx_coroutines_core(this);
    }

    @Override // kotlinx.coroutines.g1
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.g1
    public w1 p() {
        return null;
    }
}
