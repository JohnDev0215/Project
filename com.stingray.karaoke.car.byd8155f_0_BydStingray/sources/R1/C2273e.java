package r1;

import B2.C0064d;
import F1.C0254m;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import p1.InterfaceC2075c;

/* JADX INFO: renamed from: r1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2273e implements InterfaceC2075c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f21652a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f21653b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f21654e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f21655f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f21656g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f21657h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f21658i;

    public C2273e(x6.e eVar) {
        A7.m.f(eVar, "timingConfig");
        this.f21657h = new C0064d(eVar);
        this.f21658i = new C0254m(7);
    }

    public static boolean e(int i7, int i10, int i11) {
        if (i7 == i10) {
            return true;
        }
        int mode = View.MeasureSpec.getMode(i7);
        return View.MeasureSpec.getMode(i10) == 1073741824 && (mode == Integer.MIN_VALUE || mode == 0) && i11 == View.MeasureSpec.getSize(i10);
    }

    @Override // p1.InterfaceC2075c
    public void a() {
        ConstraintLayout constraintLayout = (ConstraintLayout) this.f21657h;
        int childCount = constraintLayout.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            constraintLayout.getChildAt(i7);
        }
        int size = constraintLayout.f11702m.size();
        if (size > 0) {
            for (int i10 = 0; i10 < size; i10++) {
                ((AbstractC2270b) constraintLayout.f11702m.get(i10)).getClass();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01d0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0127  */
    @Override // p1.InterfaceC2075c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(o1.d r18, p1.C2074b r19) {
        /*
            Method dump skipped, instruction units count: 691
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r1.C2273e.b(o1.d, p1.b):void");
    }

    public int c() {
        return this.f21654e + this.d;
    }

    public int d() {
        return this.f21656g + this.f21655f;
    }

    public String toString() {
        switch (this.f21652a) {
            case 1:
                return "{" + ((C0064d) this.f21657h) + "FadeInStartTime: " + this.c + ", " + ((C0254m) this.f21658i) + "FadeInDuration: " + this.f21653b + ", LineColoringStartTime: " + this.f21656g + ", LineColoringDuration: " + this.f21655f + ", FadeOutStartTime: " + this.f21654e + ", FadeOutDuration: " + this.d + "}";
            default:
                return super.toString();
        }
    }

    public C2273e(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.f21658i = constraintLayout;
        this.f21657h = constraintLayout2;
    }
}
