package a.l.d;

import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class d0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Object f945a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ l0 f946b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f947c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Fragment f948d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f949e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f950f;
    public final /* synthetic */ ArrayList g;
    public final /* synthetic */ Object h;

    public d0(Object obj, l0 l0Var, View view, Fragment fragment, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, Object obj2) {
        this.f945a = obj;
        this.f946b = l0Var;
        this.f947c = view;
        this.f948d = fragment;
        this.f949e = arrayList;
        this.f950f = arrayList2;
        this.g = arrayList3;
        this.h = obj2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Object obj = this.f945a;
        if (obj != null) {
            this.f946b.n(obj, this.f947c);
            this.f950f.addAll(g0.h(this.f946b, this.f945a, this.f948d, this.f949e, this.f947c));
        }
        if (this.g != null) {
            if (this.h != null) {
                ArrayList<View> arrayList = new ArrayList<>();
                arrayList.add(this.f947c);
                this.f946b.o(this.h, this.g, arrayList);
            }
            this.g.clear();
            this.g.add(this.f947c);
        }
    }
}
