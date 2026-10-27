package androidx.transition;

import java.util.ArrayList;

/* JADX INFO: compiled from: FragmentTransitionSupport.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends g {
    public final /* synthetic */ Object e;
    public final /* synthetic */ ArrayList f;
    public final /* synthetic */ Object g = null;
    public final /* synthetic */ ArrayList h = null;
    public final /* synthetic */ Object i;
    public final /* synthetic */ ArrayList j;
    public final /* synthetic */ d k;

    public e(d dVar, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.k = dVar;
        this.e = obj;
        this.f = arrayList;
        this.i = obj2;
        this.j = arrayList2;
    }

    @Override // androidx.transition.g, androidx.transition.Transition.d
    public final void b(Transition transition) {
        d dVar = this.k;
        Object obj = this.e;
        if (obj != null) {
            dVar.t(obj, this.f, null);
        }
        Object obj2 = this.g;
        if (obj2 != null) {
            dVar.t(obj2, this.h, null);
        }
        Object obj3 = this.i;
        if (obj3 != null) {
            dVar.t(obj3, this.j, null);
        }
    }

    @Override // androidx.transition.Transition.d
    public final void d(Transition transition) {
        transition.C(this);
    }
}
