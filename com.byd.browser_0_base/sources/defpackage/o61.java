package defpackage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: compiled from: LayoutViewBuilder.java */
/* JADX INFO: loaded from: classes3.dex */
public final class o61<T extends View> implements za1<T> {
    public final int a;
    public LayoutInflater b;

    public o61(int i) {
        this.a = i;
    }

    @Override // defpackage.za1
    public final T a(ViewGroup viewGroup) {
        if (this.b == null) {
            this.b = LayoutInflater.from(viewGroup.getContext());
        }
        return (T) this.b.inflate(this.a, viewGroup, false);
    }
}
