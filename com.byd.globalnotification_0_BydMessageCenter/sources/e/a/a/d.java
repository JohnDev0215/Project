package e.a.a;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class d extends a.k.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f2526a = new SparseIntArray(0);

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SparseArray<String> f2527a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(2);
            f2527a = sparseArray;
            sparseArray.put(0, "_all");
        }
    }

    @Override // a.k.d
    public List<a.k.d> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new a.k.r.b.a());
        return arrayList;
    }

    @Override // a.k.d
    public String b(int i) {
        return a.f2527a.get(i);
    }

    @Override // a.k.d
    public ViewDataBinding c(a.k.f fVar, View view, int i) {
        if (f2526a.get(i) <= 0 || view.getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // a.k.d
    public ViewDataBinding d(a.k.f fVar, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || f2526a.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
