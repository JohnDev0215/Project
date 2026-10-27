package org.extra.tools;

import android.app.Fragment;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class c extends Fragment {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<d> f3995a = Collections.newSetFromMap(new WeakHashMap());

    public void a(d dVar) {
        this.f3995a.add(dVar);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        for (d dVar : this.f3995a) {
            if (dVar != null) {
                dVar.onResume();
            }
        }
    }
}
