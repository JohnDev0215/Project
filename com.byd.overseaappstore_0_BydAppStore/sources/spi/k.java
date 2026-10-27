package spi;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k implements sg {
    public final Set<ug> a = Collections.newSetFromMap(new WeakHashMap());
    public boolean b;
    public boolean c;

    @Override // spi.sg
    public final void a(@NonNull ug ugVar) {
        this.a.add(ugVar);
        if (this.c) {
            ugVar.b();
        } else if (this.b) {
            ugVar.onStart();
        } else {
            ugVar.onStop();
        }
    }

    @Override // spi.sg
    public final void b(@NonNull ug ugVar) {
        this.a.remove(ugVar);
    }

    public final void c() {
        this.c = true;
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((ug) it.next()).b();
        }
    }

    public final void d() {
        this.b = true;
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((ug) it.next()).onStart();
        }
    }

    public final void e() {
        this.b = false;
        Iterator it = ((ArrayList) tu.e(this.a)).iterator();
        while (it.hasNext()) {
            ((ug) it.next()).onStop();
        }
    }
}
