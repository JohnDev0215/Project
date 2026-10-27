package defpackage;

import java.util.NoSuchElementException;

/* JADX INFO: compiled from: ProgressionIterators.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n11 extends l11 {
    public final int e;
    public final int f;
    public boolean g;
    public int h;

    public n11(int i, int i2, int i3) {
        this.e = i3;
        this.f = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.g = z;
        this.h = z ? i : i2;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.g;
    }

    @Override // defpackage.l11
    public final int nextInt() {
        int i = this.h;
        if (i != this.f) {
            this.h = this.e + i;
        } else {
            if (!this.g) {
                throw new NoSuchElementException();
            }
            this.g = false;
        }
        return i;
    }
}
