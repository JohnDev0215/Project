package defpackage;

import androidx.lifecycle.LiveData;

/* JADX INFO: compiled from: MutableLiveData.java */
/* JADX INFO: loaded from: classes.dex */
public class zj1<T> extends LiveData<T> {
    @Override // androidx.lifecycle.LiveData
    public void i(T t) {
        LiveData.a("setValue");
        this.g++;
        this.e = t;
        c(null);
    }
}
