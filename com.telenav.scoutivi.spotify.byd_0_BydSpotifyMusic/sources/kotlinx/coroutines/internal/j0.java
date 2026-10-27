package kotlinx.coroutines.internal;

/* JADX INFO: compiled from: ThreadContext.kt */
/* JADX INFO: loaded from: classes.dex */
final class j0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Object[] f7650a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f7651b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final k5.g f7652c;

    public j0(k5.g gVar, int i7) {
        this.f7652c = gVar;
        this.f7650a = new Object[i7];
    }

    public final void a(Object obj) {
        Object[] objArr = this.f7650a;
        int i7 = this.f7651b;
        this.f7651b = i7 + 1;
        objArr[i7] = obj;
    }

    public final k5.g b() {
        return this.f7652c;
    }

    public final void c() {
        this.f7651b = 0;
    }

    public final Object d() {
        Object[] objArr = this.f7650a;
        int i7 = this.f7651b;
        this.f7651b = i7 + 1;
        return objArr[i7];
    }
}
