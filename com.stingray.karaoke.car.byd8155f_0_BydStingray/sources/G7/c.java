package G7;

import java.util.NoSuchElementException;
import n7.y;

/* JADX INFO: loaded from: classes.dex */
public final class c extends y {
    public final int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2714m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2715n;
    public int o;

    public c(int i7, int i10, int i11) {
        this.l = i11;
        this.f2714m = i10;
        boolean z6 = false;
        if (i11 <= 0 ? i7 >= i10 : i7 <= i10) {
            z6 = true;
        }
        this.f2715n = z6;
        this.o = z6 ? i7 : i10;
    }

    @Override // n7.y
    public final int b() {
        int i7 = this.o;
        if (i7 != this.f2714m) {
            this.o = this.l + i7;
        } else {
            if (!this.f2715n) {
                throw new NoSuchElementException();
            }
            this.f2715n = false;
        }
        return i7;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2715n;
    }
}
