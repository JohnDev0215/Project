package i6;

import c6.c0;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RealResponseBody.kt */
/* JADX INFO: loaded from: classes.dex */
public final class h extends c0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final String f7061g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final long f7062h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final p6.g f7063i;

    public h(String str, long j7, p6.g source) {
        l.e(source, "source");
        this.f7061g = str;
        this.f7062h = j7;
        this.f7063i = source;
    }

    @Override // c6.c0
    public p6.g A() {
        return this.f7063i;
    }

    @Override // c6.c0
    public long z() {
        return this.f7062h;
    }
}
