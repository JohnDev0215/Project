package v5;

import h5.d0;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b extends d0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f9687e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int f9688g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f9689h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f9690i;

    public b(int i7, int i8, int i9) {
        this.f9687e = i9;
        this.f9688g = i8;
        boolean z6 = true;
        if (i9 <= 0 ? i7 < i8 : i7 > i8) {
            z6 = false;
        }
        this.f9689h = z6;
        this.f9690i = z6 ? i7 : i8;
    }

    @Override // h5.d0
    public int a() {
        int i7 = this.f9690i;
        if (i7 != this.f9688g) {
            this.f9690i = this.f9687e + i7;
        } else {
            if (!this.f9689h) {
                throw new NoSuchElementException();
            }
            this.f9689h = false;
        }
        return i7;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f9689h;
    }
}
