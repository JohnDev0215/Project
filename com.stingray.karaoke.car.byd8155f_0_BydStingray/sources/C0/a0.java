package C0;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f882a = c0.b(Float.NaN, Float.NaN);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f883b = 0;

    public static final float a(long j3) {
        if (j3 != f882a) {
            return Float.intBitsToFloat((int) (j3 >> 32));
        }
        l9.d.h0("ScaleFactor is unspecified");
        throw null;
    }

    public static final float b(long j3) {
        if (j3 != f882a) {
            return Float.intBitsToFloat((int) (j3 & 4294967295L));
        }
        l9.d.h0("ScaleFactor is unspecified");
        throw null;
    }
}
