package g4;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class o implements Iterable {
    public final ArrayList l;

    public o(ArrayList arrayList) {
        this.l = arrayList;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }
}
