package kotlin.o;

import java.util.NoSuchElementException;
import kotlin.collections.u;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f5135b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f5136c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f5137d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f5138e;

    public b(int i, int i2, int i3) {
        this.f5138e = i3;
        this.f5135b = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.f5136c = z;
        this.f5137d = z ? i : i2;
    }

    @Override // kotlin.collections.u
    public int b() {
        int i = this.f5137d;
        if (i != this.f5135b) {
            this.f5137d = this.f5138e + i;
        } else {
            if (!this.f5136c) {
                throw new NoSuchElementException();
            }
            this.f5136c = false;
        }
        return i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f5136c;
    }
}
