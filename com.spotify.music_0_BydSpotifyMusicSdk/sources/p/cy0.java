package p;

import android.view.View;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class cy0 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ ArrayList e;
    public final /* synthetic */ ArrayList k;
    public final /* synthetic */ ArrayList l;
    public final /* synthetic */ ArrayList m;

    public cy0(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.c = i;
        this.e = arrayList;
        this.k = arrayList2;
        this.l = arrayList3;
        this.m = arrayList4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i = 0; i < this.c; i++) {
            View view = (View) this.e.get(i);
            String str = (String) this.k.get(i);
            WeakHashMap weakHashMap = zt2.a;
            ot2.v(view, str);
            ot2.v((View) this.l.get(i), (String) this.m.get(i));
        }
    }
}
