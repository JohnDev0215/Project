package kotlin.q;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b extends kotlin.collections.h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4090b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4091c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4092d;
    private final int e;

    public b(char c2, char c3, int i) {
        this.e = i;
        this.f4090b = c3;
        boolean z = true;
        if (i <= 0 ? kotlin.jvm.internal.h.e(c2, c3) < 0 : kotlin.jvm.internal.h.e(c2, c3) > 0) {
            z = false;
        }
        this.f4091c = z;
        this.f4092d = z ? c2 : c3;
    }

    @Override // kotlin.collections.h
    public char b() {
        int i = this.f4092d;
        if (i != this.f4090b) {
            this.f4092d = this.e + i;
        } else {
            if (!this.f4091c) {
                throw new NoSuchElementException();
            }
            this.f4091c = false;
        }
        return (char) i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f4091c;
    }
}
