package e.a.a.g;

import a.k.d;
import a.k.f;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f2532a = new SparseIntArray(0);

    /* JADX INFO: renamed from: e.a.a.g.a$a, reason: collision with other inner class name */
    public static class C0060a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SparseArray<String> f2533a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(2);
            f2533a = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    @Override // a.k.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new a.k.r.b.a());
        arrayList.add(new e.a.a.d());
        return arrayList;
    }

    @Override // a.k.d
    public String b(int i) {
        return C0060a.f2533a.get(i);
    }

    @Override // a.k.d
    public ViewDataBinding c(f fVar, View view, int i) {
        if (f2532a.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // a.k.d
    public ViewDataBinding d(f fVar, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || f2532a.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
