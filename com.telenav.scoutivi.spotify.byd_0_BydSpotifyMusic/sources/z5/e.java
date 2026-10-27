package z5;

import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: DurationUnitJvm.kt */
/* JADX INFO: loaded from: classes.dex */
class e {
    public static final long a(long j7, d sourceUnit, d targetUnit) {
        l.e(sourceUnit, "sourceUnit");
        l.e(targetUnit, "targetUnit");
        return targetUnit.getTimeUnit$kotlin_stdlib().convert(j7, sourceUnit.getTimeUnit$kotlin_stdlib());
    }
}
