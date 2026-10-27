package p8;

import androidx.datastore.preferences.protobuf.C0941d;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class x implements Iterator {
    public final w l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C0941d f20994m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20995n;

    public x(y yVar) {
        w wVar = new w(yVar);
        this.l = wVar;
        this.f20994m = new C0941d(wVar.next());
        this.f20995n = yVar.f20997m;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f20995n > 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.f20994m.hasNext()) {
            this.f20994m = new C0941d(this.l.next());
        }
        this.f20995n--;
        return Byte.valueOf(this.f20994m.a());
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
