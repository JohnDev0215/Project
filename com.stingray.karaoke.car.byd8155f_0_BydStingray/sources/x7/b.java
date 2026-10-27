package x7;

import A7.m;
import P8.o;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator, B7.a {
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f23822m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f23823n;

    public b(o oVar) {
        this.f23823n = oVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() throws IOException {
        if (this.l == null && !this.f23822m) {
            String line = ((BufferedReader) this.f23823n.f5481b).readLine();
            this.l = line;
            if (line == null) {
                this.f23822m = true;
            }
        }
        return this.l != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        String str = this.l;
        this.l = null;
        m.c(str);
        return str;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
