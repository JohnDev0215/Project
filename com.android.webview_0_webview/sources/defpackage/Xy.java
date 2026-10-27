package defpackage;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Xy implements Iterator {
    public final int s;
    public int t;
    public final /* synthetic */ Yy u;

    public Xy(Yy yy) {
        this.u = yy;
        int size = yy.u.size();
        this.s = size;
        this.t = size - 1;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.t >= 0;
    }

    @Override // java.util.Iterator
    public Object next() {
        List list = this.u.u;
        int i = this.t;
        this.t = i - 1;
        return ((Vy) list.get(i)).a;
    }
}
