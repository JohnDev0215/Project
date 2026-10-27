package spi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ss implements ug {
    public final Set<rs<?>> a = Collections.newSetFromMap(new WeakHashMap());

    @Override // spi.ug
    public final void b() {
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((rs) it.next()).b();
        }
    }

    @Override // spi.ug
    public final void onStart() {
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((rs) it.next()).onStart();
        }
    }

    @Override // spi.ug
    public final void onStop() {
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((rs) it.next()).onStop();
        }
    }
}
