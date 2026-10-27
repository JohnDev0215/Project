package androidx.constraintlayout.widget;

import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: SharedValues.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SparseIntArray f1881a = new SparseIntArray();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<Integer, HashSet<WeakReference<a>>> f1882b = new HashMap<>();

    /* JADX INFO: compiled from: SharedValues.java */
    public interface a {
    }

    public void a(int i7, a aVar) {
        HashSet<WeakReference<a>> hashSet = this.f1882b.get(Integer.valueOf(i7));
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.f1882b.put(Integer.valueOf(i7), hashSet);
        }
        hashSet.add(new WeakReference<>(aVar));
    }
}
