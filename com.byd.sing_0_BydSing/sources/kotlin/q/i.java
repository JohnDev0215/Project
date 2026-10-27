package kotlin.q;

import kotlin.collections.v;

/* JADX INFO: compiled from: Progressions.kt */
/* JADX INFO: loaded from: classes.dex */
public class i implements Iterable<Long> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f4103b = new a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f4104c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final long f4105d;
    private final long e;

    /* JADX INFO: compiled from: Progressions.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public i(long j, long j2, long j3) {
        if (j3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j3 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.f4104c = j;
        this.f4105d = kotlin.o.a.d(j, j2, j3);
        this.e = j3;
    }

    public final long a() {
        return this.f4104c;
    }

    public final long b() {
        return this.f4105d;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public v iterator() {
        return new j(this.f4104c, this.f4105d, this.e);
    }
}
