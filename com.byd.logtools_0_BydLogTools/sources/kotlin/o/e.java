package kotlin.o;

import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: Ranges.kt */
/* JADX INFO: loaded from: classes.dex */
class e {
    public static final void a(boolean z, Number number) {
        i.c(number, "step");
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + number + '.');
    }
}
