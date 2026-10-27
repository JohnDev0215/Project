package v5;

import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
class g {
    public static final void a(boolean z6, Number step) {
        l.e(step, "step");
        if (z6) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + step + '.');
    }
}
