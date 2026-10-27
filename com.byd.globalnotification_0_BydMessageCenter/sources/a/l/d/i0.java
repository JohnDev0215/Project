package a.l.d;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class i0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f997a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f998b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ArrayList f999c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1000d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f1001e;

    public i0(l0 l0Var, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f997a = i;
        this.f998b = arrayList;
        this.f999c = arrayList2;
        this.f1000d = arrayList3;
        this.f1001e = arrayList4;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f997a; i++) {
            a.h.k.q.f0((View) this.f998b.get(i), (String) this.f999c.get(i));
            ((View) this.f1000d.get(i)).setTransitionName((String) this.f1001e.get(i));
        }
    }
}
