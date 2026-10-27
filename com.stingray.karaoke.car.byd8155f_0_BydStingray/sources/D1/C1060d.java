package d1;

import C0.InterfaceC0108o;
import C0.K;
import C0.L;
import C0.M;
import E0.F;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import n7.v;

/* JADX INFO: renamed from: d1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1060d implements K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ o f13284a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ F f13285b;

    public C1060d(o oVar, F f10) {
        this.f13284a = oVar;
        this.f13285b = f10;
    }

    @Override // C0.K
    public final int a(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        o oVar = this.f13284a;
        ViewGroup.LayoutParams layoutParams = oVar.getLayoutParams();
        A7.m.c(layoutParams);
        oVar.measure(iMakeMeasureSpec, i.k(oVar, 0, i7, layoutParams.height));
        return oVar.getMeasuredWidth();
    }

    @Override // C0.K
    public final int d(InterfaceC0108o interfaceC0108o, List list, int i7) {
        o oVar = this.f13284a;
        ViewGroup.LayoutParams layoutParams = oVar.getLayoutParams();
        A7.m.c(layoutParams);
        oVar.measure(i.k(oVar, 0, i7, layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
        return oVar.getMeasuredHeight();
    }

    @Override // C0.K
    public final L h(M m10, List list, long j3) {
        o oVar = this.f13284a;
        int childCount = oVar.getChildCount();
        v vVar = v.l;
        if (childCount == 0) {
            return m10.m0(a1.a.j(j3), a1.a.i(j3), vVar, C1057a.o);
        }
        if (a1.a.j(j3) != 0) {
            oVar.getChildAt(0).setMinimumWidth(a1.a.j(j3));
        }
        if (a1.a.i(j3) != 0) {
            oVar.getChildAt(0).setMinimumHeight(a1.a.i(j3));
        }
        int iJ = a1.a.j(j3);
        int iH = a1.a.h(j3);
        ViewGroup.LayoutParams layoutParams = oVar.getLayoutParams();
        A7.m.c(layoutParams);
        int iK = i.k(oVar, iJ, iH, layoutParams.width);
        int i7 = a1.a.i(j3);
        int iG = a1.a.g(j3);
        ViewGroup.LayoutParams layoutParams2 = oVar.getLayoutParams();
        A7.m.c(layoutParams2);
        oVar.measure(iK, i.k(oVar, i7, iG, layoutParams2.height));
        return m10.m0(oVar.getMeasuredWidth(), oVar.getMeasuredHeight(), vVar, new C1058b(oVar, this.f13285b, 1));
    }

    @Override // C0.K
    public final int i(InterfaceC0108o interfaceC0108o, List list, int i7) {
        o oVar = this.f13284a;
        ViewGroup.LayoutParams layoutParams = oVar.getLayoutParams();
        A7.m.c(layoutParams);
        oVar.measure(i.k(oVar, 0, i7, layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
        return oVar.getMeasuredHeight();
    }

    @Override // C0.K
    public final int j(InterfaceC0108o interfaceC0108o, List list, int i7) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        o oVar = this.f13284a;
        ViewGroup.LayoutParams layoutParams = oVar.getLayoutParams();
        A7.m.c(layoutParams);
        oVar.measure(iMakeMeasureSpec, i.k(oVar, 0, i7, layoutParams.height));
        return oVar.getMeasuredWidth();
    }
}
