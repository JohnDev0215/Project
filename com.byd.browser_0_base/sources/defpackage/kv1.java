package defpackage;

import android.database.DataSetObservable;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: PagerAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class kv1 {
    public final DataSetObservable a = new DataSetObservable();

    public void a(ViewGroup viewGroup, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public abstract int b();

    public CharSequence c(int i) {
        return null;
    }

    public Object d(int i, ViewGroup viewGroup) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public abstract boolean e(View view, Object obj);
}
