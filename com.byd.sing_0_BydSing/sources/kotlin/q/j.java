package kotlin.q;

import java.util.NoSuchElementException;
import kotlin.collections.v;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class j extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final long f4106b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4107c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f4108d;
    private final long e;

    public j(long j, long j2, long j3) {
        this.e = j3;
        this.f4106b = j2;
        boolean z = true;
        if (j3 <= 0 ? j < j2 : j > j2) {
            z = false;
        }
        this.f4107c = z;
        this.f4108d = z ? j : j2;
    }

    @Override // kotlin.collections.v
    public long b() {
        long j = this.f4108d;
        if (j != this.f4106b) {
            this.f4108d = this.e + j;
        } else {
            if (!this.f4107c) {
                throw new NoSuchElementException();
            }
            this.f4107c = false;
        }
        return j;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f4107c;
    }
}
