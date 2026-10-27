package kotlin.q;

import java.util.NoSuchElementException;
import kotlin.collections.u;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class g extends u {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4100b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4101c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4102d;
    private final int e;

    public g(int i, int i2, int i3) {
        this.e = i3;
        this.f4100b = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.f4101c = z;
        this.f4102d = z ? i : i2;
    }

    @Override // kotlin.collections.u
    public int b() {
        int i = this.f4102d;
        if (i != this.f4100b) {
            this.f4102d = this.e + i;
        } else {
            if (!this.f4101c) {
                throw new NoSuchElementException();
            }
            this.f4101c = false;
        }
        return i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f4101c;
    }
}
