package androidx.constraintlayout.widget;

import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: SharedValues.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public final HashMap<Integer, HashSet<WeakReference<a>>> a;

    /* JADX INFO: compiled from: SharedValues.java */
    public interface a {
    }

    public b() {
        new SparseIntArray();
        this.a = new HashMap<>();
    }

    public final void a(int i, a aVar) {
        HashMap<Integer, HashSet<WeakReference<a>>> map = this.a;
        HashSet<WeakReference<a>> hashSet = map.get(Integer.valueOf(i));
        if (hashSet == null) {
            hashSet = new HashSet<>();
            map.put(Integer.valueOf(i), hashSet);
        }
        hashSet.add(new WeakReference<>(aVar));
    }
}
