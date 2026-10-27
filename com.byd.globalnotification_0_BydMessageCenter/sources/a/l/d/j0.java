package a.l.d;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class j0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1007a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Map f1008b;

    public j0(l0 l0Var, ArrayList arrayList, Map map) {
        this.f1007a = arrayList;
        this.f1008b = map;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        int size = this.f1007a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f1007a.get(i);
            String strV = a.h.k.q.v(view);
            if (strV != null) {
                Iterator it = this.f1008b.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (strV.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                view.setTransitionName(str);
            }
        }
    }
}
