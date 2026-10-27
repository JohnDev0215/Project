package spi;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class um {
    public final Set<jm> a = Collections.newSetFromMap(new WeakHashMap());
    public final Set<jm> b = new HashSet();
    public boolean c;

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    public final boolean a(@Nullable jm jmVar) {
        boolean z = true;
        if (jmVar == null) {
            return true;
        }
        boolean zRemove = this.a.remove(jmVar);
        if (!this.b.remove(jmVar) && !zRemove) {
            z = false;
        }
        if (z) {
            jmVar.clear();
        }
        return z;
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [java.util.HashSet, java.util.Set<spi.jm>] */
    public final void b() {
        for (jm jmVar : (ArrayList) tu.e(this.a)) {
            if (!jmVar.j() && !jmVar.f()) {
                jmVar.clear();
                if (this.c) {
                    this.b.add(jmVar);
                } else {
                    jmVar.i();
                }
            }
        }
    }

    public final String toString() {
        return super.toString() + "{numRequests=" + this.a.size() + ", isPaused=" + this.c + "}";
    }
}
