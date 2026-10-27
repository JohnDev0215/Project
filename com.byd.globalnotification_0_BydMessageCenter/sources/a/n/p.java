package a.n;

import androidx.lifecycle.LiveData;

/* JADX INFO: loaded from: classes.dex */
public class p<T> extends LiveData<T> {
    @Override // androidx.lifecycle.LiveData
    public void h(T t) {
        LiveData.a("setValue");
        this.f1575f++;
        this.f1573d = t;
        c(null);
    }

    public void i(T t) {
        boolean z;
        synchronized (this.f1570a) {
            z = this.f1574e == LiveData.j;
            this.f1574e = t;
        }
        if (z) {
            a.c.a.a.a.c().f418a.b(this.i);
        }
    }
}
