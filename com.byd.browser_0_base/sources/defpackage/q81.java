package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.ui.modelutil.e;

/* JADX INFO: compiled from: ListModelBase.java */
/* JADX INFO: loaded from: classes3.dex */
public class q81<T, P> extends e<P> implements xm2<T> {
    public final ArrayList f = new ArrayList();

    public void A(List list) {
        ArrayList arrayList = this.f;
        int size = arrayList.size();
        int size2 = list.size();
        arrayList.clear();
        arrayList.addAll(list);
        int iMin = Math.min(size, size2);
        if (iMin > 0) {
            q(0, iMin, null);
        }
        if (size2 > size) {
            r(iMin, size2 - size);
        } else if (size2 < size) {
            s(iMin, size - size2);
        }
    }

    public final void clear() {
        if (size() > 0) {
            z(0, size());
        }
    }

    @Override // defpackage.xm2
    public final T get(int i) {
        return (T) this.f.get(i);
    }

    public final int indexOf(Object obj) {
        return this.f.indexOf(obj);
    }

    @Override // defpackage.xm2, java.lang.Iterable
    public final Iterator<T> iterator() {
        return this.f.iterator();
    }

    @Override // defpackage.xm2
    public final int size() {
        return this.f.size();
    }

    public final void t(T t) {
        ArrayList arrayList = this.f;
        arrayList.add(t);
        r(arrayList.size() - 1, 1);
    }

    public void u(int i, xa1 xa1Var) {
        this.f.add(i, xa1Var);
        r(i, 1);
    }

    public void v(xm2<T> xm2Var, int i) {
        Iterator<T> it = xm2Var.iterator();
        int i2 = i;
        while (it.hasNext()) {
            this.f.add(i2, it.next());
            i2++;
        }
        r(i, xm2Var.size());
    }

    public final void w(int i, int i2) {
        ArrayList arrayList = this.f;
        Object objRemove = arrayList.remove(i);
        if (i2 == arrayList.size()) {
            arrayList.add(objRemove);
        } else {
            arrayList.add(i2, objRemove);
        }
        p(i, i2);
    }

    public final void x(xa1 xa1Var) {
        y(this.f.indexOf(xa1Var));
    }

    public T y(int i) {
        T t = (T) this.f.remove(i);
        s(i, 1);
        return t;
    }

    public void z(int i, int i2) {
        this.f.subList(i, i + i2).clear();
        s(i, i2);
    }
}
