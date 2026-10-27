package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import spi.f4;
import spi.pp;

/* JADX INFO: loaded from: classes.dex */
public final class y5 implements f4 {
    public final Context a;
    public final f4.a b;

    public y5(@NonNull Context context, @NonNull f4.a aVar) {
        this.a = context.getApplicationContext();
        this.b = aVar;
    }

    @Override // spi.ug
    public final void b() {
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashSet, java.util.Set<spi.f4$a>] */
    @Override // spi.ug
    public final void onStart() {
        pp ppVarA = pp.a(this.a);
        f4.a aVar = this.b;
        synchronized (ppVarA) {
            ppVarA.b.add(aVar);
            ppVarA.b();
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [java.util.HashSet, java.util.Set<spi.f4$a>] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.HashSet, java.util.Set<spi.f4$a>] */
    @Override // spi.ug
    public final void onStop() {
        pp ppVarA = pp.a(this.a);
        f4.a aVar = this.b;
        synchronized (ppVarA) {
            ppVarA.b.remove(aVar);
            if (ppVarA.c && ppVarA.b.isEmpty()) {
                pp.c cVar = ppVarA.a;
                cVar.c.get().unregisterNetworkCallback(cVar.d);
                ppVarA.c = false;
            }
        }
    }
}
