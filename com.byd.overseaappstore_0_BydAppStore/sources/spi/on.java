package spi;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import spi.nn;

/* JADX INFO: loaded from: classes2.dex */
public final class on implements r, hv {
    public final Set<nn.a> a = new HashSet();
    public boolean b = false;

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<spi.nn$a>] */
    public final void a() {
        ys.a();
        this.b = true;
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((nn.a) it.next()).a();
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashSet, java.util.Set<spi.nn$a>] */
    @Override // spi.r, spi.nn
    public void addOnClearedListener(@NonNull nn.a aVar) {
        ys.a();
        if (this.b) {
            throw new IllegalStateException("There was a race between the call to add/remove an OnClearedListener and onCleared(). This can happen when posting to the Main thread from a background thread, which is not supported.");
        }
        this.a.add(aVar);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.HashSet, java.util.Set<spi.nn$a>] */
    @Override // spi.r, spi.nn
    public void removeOnClearedListener(@NonNull nn.a aVar) {
        ys.a();
        if (this.b) {
            throw new IllegalStateException("There was a race between the call to add/remove an OnClearedListener and onCleared(). This can happen when posting to the Main thread from a background thread, which is not supported.");
        }
        this.a.remove(aVar);
    }
}
