package a.l.d;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class k0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Map f1010b;

    public k0(l0 l0Var, ArrayList arrayList, Map map) {
        this.f1009a = arrayList;
        this.f1010b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        int size = this.f1009a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f1009a.get(i);
            view.setTransitionName((String) this.f1010b.get(a.h.k.q.v(view)));
        }
    }
}
