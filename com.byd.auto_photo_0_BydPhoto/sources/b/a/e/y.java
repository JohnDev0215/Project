package b.a.e;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class y<T> extends PagerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private SparseArray<a> f458a = new SparseArray<>(6);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LinkedList<a> f459b = new LinkedList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected List<T> f460c;

    /* JADX INFO: Access modifiers changed from: protected */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected View f461a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        protected int f462b;

        public a(View view) {
            this.f461a = view;
        }

        public View a() {
            return this.f461a;
        }

        protected int b() {
            return this.f462b;
        }

        protected void c() {
        }
    }

    public void a(int i, T t) {
        if (this.f460c == null) {
            this.f460c = new LinkedList();
        }
        if (i < this.f460c.size()) {
            this.f460c.add(i, t);
        } else {
            this.f460c.add(t);
        }
        this.f458a.clear();
        this.f459b.clear();
        notifyDataSetChanged();
    }

    public List<T> b() {
        return this.f460c;
    }

    public a c(int i) {
        return this.f458a.get(i);
    }

    protected abstract void d(int i, a aVar);

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        a aVar = this.f458a.get(i);
        if (aVar != null) {
            this.f458a.remove(i);
            this.f459b.addLast(aVar);
            aVar.c();
        }
    }

    protected abstract a e();

    public void f() {
        SparseArray<a> sparseArray = this.f458a;
        if (sparseArray != null) {
            sparseArray.clear();
            this.f458a = null;
        }
        LinkedList<a> linkedList = this.f459b;
        if (linkedList != null) {
            linkedList.clear();
            this.f459b = null;
        }
    }

    public boolean g(int i) {
        List<T> list = this.f460c;
        if (list == null || i >= list.size()) {
            return false;
        }
        this.f460c.remove(i);
        this.f458a.clear();
        this.f459b.clear();
        notifyDataSetChanged();
        com.byd.util.w.c("ImageShow adapter remove notify");
        return true;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<T> list = this.f460c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public void h(List<T> list) {
        this.f460c = list;
        notifyDataSetChanged();
    }

    protected abstract void i(a aVar);

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        a aVarRemoveFirst = this.f459b.size() > 0 ? this.f459b.removeFirst() : e();
        d(i, aVarRemoveFirst);
        this.f458a.put(i, aVarRemoveFirst);
        viewGroup.addView(aVarRemoveFirst.f461a);
        aVarRemoveFirst.f462b = i;
        i(aVarRemoveFirst);
        return aVarRemoveFirst.f461a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
