package kotlin.q;

/* JADX INFO: compiled from: Progressions.kt */
/* JADX INFO: loaded from: classes.dex */
public class a implements Iterable<Character> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0130a f4087b = new C0130a(null);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final char f4088c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final char f4089d;
    private final int e;

    /* JADX INFO: renamed from: kotlin.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Progressions.kt */
    public static final class C0130a {
        private C0130a() {
        }

        public /* synthetic */ C0130a(kotlin.jvm.internal.f fVar) {
            this();
        }
    }

    public a(char c2, char c3, int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (i == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
        this.f4088c = c2;
        this.f4089d = (char) kotlin.o.a.c(c2, c3, i);
        this.e = i;
    }

    public final char a() {
        return this.f4088c;
    }

    public final char b() {
        return this.f4089d;
    }

    @Override // java.lang.Iterable
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public kotlin.collections.h iterator() {
        return new b(this.f4088c, this.f4089d, this.e);
    }
}
