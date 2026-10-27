package defpackage;

import android.view.View;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: FragmentTransitionImpl.java */
/* JADX INFO: loaded from: classes.dex */
public final class aq0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ ArrayList g;
    public final /* synthetic */ ArrayList h;
    public final /* synthetic */ ArrayList i;

    public aq0(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.e = i;
        this.f = arrayList;
        this.g = arrayList2;
        this.h = arrayList3;
        this.i = arrayList4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (int i = 0; i < this.e; i++) {
            View view = (View) this.f.get(i);
            String str = (String) this.g.get(i);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            om3.i.n(view, str);
            om3.i.n((View) this.h.get(i), (String) this.i.get(i));
        }
    }
}
