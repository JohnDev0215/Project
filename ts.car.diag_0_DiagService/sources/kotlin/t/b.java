package kotlin.t;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.B;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u00058\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u00020\u00058\u0000@\u0000¢\u0006\u0006\n\u0004\b\n\u0010\tR\u0016\u0010\u000b\u001a\u00020\u00028\u0000@\u0000X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\u00058\u0000@\u0000X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\t¨\u0006\u0013"}, d2 = {"Lkotlin/t/b;", "Lkotlin/collections/B;", "Lkotlin/Boolean;", "hasNext", "()Z", "Lkotlin/Int;", "b", "()I", "e", "I", "f", "d", "Z", "c", "p0", "p1", "p2", "<init>", "(III)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public final class b extends B {
    private final int c;
    private boolean d;
    private int e;
    private final int f;

    public b(int i, int i2, int i3) {
        this.f = i3;
        this.c = i2;
        boolean z = true;
        if (i3 <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.d = z;
        this.e = z ? i : this.c;
    }

    @Override // kotlin.collections.B
    public int b() {
        int i = this.e;
        if (i != this.c) {
            this.e = this.f + i;
        } else {
            if (!this.d) {
                throw new NoSuchElementException();
            }
            this.d = false;
        }
        return i;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.d;
    }
}
