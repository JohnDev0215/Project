package defpackage;

import android.content.Context;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: compiled from: ContextAwareHelper.kt */
/* JADX INFO: loaded from: classes.dex */
public final class az {
    public final CopyOnWriteArraySet a = new CopyOnWriteArraySet();
    public volatile Context b;

    public final void addOnContextAvailableListener(ds1 ds1Var) {
        i21.e(ds1Var, "listener");
        if (this.b != null) {
            ds1Var.a();
        }
        this.a.add(ds1Var);
    }

    public final void removeOnContextAvailableListener(ds1 ds1Var) {
        i21.e(ds1Var, "listener");
        this.a.remove(ds1Var);
    }
}
