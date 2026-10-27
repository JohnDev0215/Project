package kotlinx.serialization.json.internal;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes7.dex */
public final /* synthetic */ class AbstractJsonTreeEncoder$encodeTaggedInline$1$$ExternalSyntheticBackport4 {
    public static /* synthetic */ long m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        }
        if (j >= 0) {
            return j / j2;
        }
        long j3 = ((j >>> 1) / j2) << 1;
        return j3 + ((long) (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1));
    }
}
