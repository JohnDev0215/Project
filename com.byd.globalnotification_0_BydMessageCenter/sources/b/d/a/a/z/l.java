package b.d.a.a.z;

import a.b.k.l;
import a.h.k.w;
import android.view.View;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class l implements a.h.k.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f2190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ o f2191b;

    public l(n nVar, o oVar) {
        this.f2190a = nVar;
        this.f2191b = oVar;
    }

    @Override // a.h.k.k
    public w a(View view, w wVar) {
        n nVar = this.f2190a;
        o oVar = this.f2191b;
        int i = oVar.f2192a;
        int i2 = oVar.f2194c;
        int i3 = oVar.f2195d;
        b.d.a.a.p.b bVar = (b.d.a.a.p.b) nVar;
        bVar.f2041b.s = wVar.d();
        boolean zM = l.i.M(view);
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        BottomSheetBehavior bottomSheetBehavior = bVar.f2041b;
        if (bottomSheetBehavior.n) {
            bottomSheetBehavior.r = wVar.a();
            paddingBottom = bVar.f2041b.r + i3;
        }
        if (bVar.f2041b.o) {
            paddingLeft = wVar.b() + (zM ? i2 : i);
        }
        if (bVar.f2041b.p) {
            if (!zM) {
                i = i2;
            }
            paddingRight = wVar.c() + i;
        }
        view.setPadding(paddingLeft, view.getPaddingTop(), paddingRight, paddingBottom);
        if (bVar.f2040a) {
            bVar.f2041b.l = wVar.f844a.f().f723d;
        }
        if (bVar.f2041b.n || bVar.f2040a) {
            bVar.f2041b.S(false);
        }
        return wVar;
    }
}
