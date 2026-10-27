package p;

import android.database.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class r12 extends Observable {
    public final boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }
}
