package a.l.d;

import a.l.d.g0;
import android.graphics.Rect;
import android.view.View;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class f0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ l0 f959a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ a.e.a f960b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Object f961c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g0.b f962d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ArrayList f963e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f964f;
    public final /* synthetic */ Fragment g;
    public final /* synthetic */ Fragment h;
    public final /* synthetic */ boolean i;
    public final /* synthetic */ ArrayList j;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Rect l;

    public f0(l0 l0Var, a.e.a aVar, Object obj, g0.b bVar, ArrayList arrayList, View view, Fragment fragment, Fragment fragment2, boolean z, ArrayList arrayList2, Object obj2, Rect rect) {
        this.f959a = l0Var;
        this.f960b = aVar;
        this.f961c = obj;
        this.f962d = bVar;
        this.f963e = arrayList;
        this.f964f = view;
        this.g = fragment;
        this.h = fragment2;
        this.i = z;
        this.j = arrayList2;
        this.k = obj2;
        this.l = rect;
    }

    @Override // java.lang.Runnable
    public void run() {
        a.e.a<String, View> aVarE = g0.e(this.f959a, this.f960b, this.f961c, this.f962d);
        if (aVarE != null) {
            this.f963e.addAll(aVarE.values());
            this.f963e.add(this.f964f);
        }
        g0.c(this.g, this.h, this.i, aVarE, false);
        Object obj = this.f961c;
        if (obj != null) {
            this.f959a.v(obj, this.j, this.f963e);
            View viewK = g0.k(aVarE, this.f962d, this.k, this.i);
            if (viewK != null) {
                this.f959a.j(viewK, this.l);
            }
        }
    }
}
