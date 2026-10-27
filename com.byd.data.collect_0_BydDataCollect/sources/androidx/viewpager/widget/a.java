package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: PagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final DataSetObservable f2091a = new DataSetObservable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DataSetObserver f2092b;

    @Deprecated
    public void a(View view, int i2, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void b(ViewGroup viewGroup, int i2, Object obj) {
        a(viewGroup, i2, obj);
    }

    @Deprecated
    public void c(View view) {
    }

    public void d(ViewGroup viewGroup) {
        c(viewGroup);
    }

    public abstract int e();

    public int f(Object obj) {
        return -1;
    }

    public CharSequence g(int i2) {
        return null;
    }

    public float h(int i2) {
        return 1.0f;
    }

    @Deprecated
    public Object i(View view, int i2) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object j(ViewGroup viewGroup, int i2) {
        return i(viewGroup, i2);
    }

    public abstract boolean k(View view, Object obj);

    public void l(DataSetObserver dataSetObserver) {
        this.f2091a.registerObserver(dataSetObserver);
    }

    public void m(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable n() {
        return null;
    }

    @Deprecated
    public void o(View view, int i2, Object obj) {
    }

    public void p(ViewGroup viewGroup, int i2, Object obj) {
        o(viewGroup, i2, obj);
    }

    void q(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f2092b = dataSetObserver;
        }
    }

    @Deprecated
    public void r(View view) {
    }

    public void s(ViewGroup viewGroup) {
        r(viewGroup);
    }

    public void t(DataSetObserver dataSetObserver) {
        this.f2091a.unregisterObserver(dataSetObserver);
    }
}
