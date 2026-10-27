package androidx.mediarouter.media;

import android.util.SparseArray;
import androidx.mediarouter.media.h;
import androidx.mediarouter.media.o;

/* JADX INFO: compiled from: RegisteredMediaRouteProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {
    public final /* synthetic */ o.a e;

    public n(o.a aVar) {
        this.e = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SparseArray<h.c> sparseArray = this.e.h;
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            sparseArray.valueAt(i).a(null, null);
        }
        sparseArray.clear();
    }
}
