package n0;

import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ListenerUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SparseArray<WeakHashMap<View, WeakReference<?>>> f8491a = new SparseArray<>();

    public static <T> T a(View view, T t6, int i7) {
        T t7 = (T) view.getTag(i7);
        view.setTag(i7, t6);
        return t7;
    }
}
