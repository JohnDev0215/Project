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
    private final DataSetObservable f1920a = new DataSetObservable();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DataSetObserver f1921b;

    @Deprecated
    public void a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void b(ViewGroup viewGroup, int i, Object obj) {
        a(viewGroup, i, obj);
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

    public CharSequence g(int i) {
        return null;
    }

    public float h(int i) {
        return 1.0f;
    }

    @Deprecated
    public Object i(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object j(ViewGroup viewGroup, int i) {
        return i(viewGroup, i);
    }

    public abstract boolean k(View view, Object obj);

    public void l() {
        synchronized (this) {
            DataSetObserver dataSetObserver = this.f1921b;
            if (dataSetObserver != null) {
                dataSetObserver.onChanged();
            }
        }
        this.f1920a.notifyChanged();
    }

    public void m(DataSetObserver dataSetObserver) {
        this.f1920a.registerObserver(dataSetObserver);
    }

    public void n(Parcelable parcelable, ClassLoader classLoader) {
    }

    public Parcelable o() {
        return null;
    }

    @Deprecated
    public void p(View view, int i, Object obj) {
    }

    public void q(ViewGroup viewGroup, int i, Object obj) {
        p(viewGroup, i, obj);
    }

    void r(DataSetObserver dataSetObserver) {
        synchronized (this) {
            this.f1921b = dataSetObserver;
        }
    }

    @Deprecated
    public void s(View view) {
    }

    public void t(ViewGroup viewGroup) {
        s(viewGroup);
    }

    public void u(DataSetObserver dataSetObserver) {
        this.f1920a.unregisterObserver(dataSetObserver);
    }
}
