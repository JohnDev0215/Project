package r;

import android.content.Context;
import android.view.View;
import com.stingray.drivemodedetector.R;
import l3.C1764u;
import q.AbstractC2153j;
import q.C2155l;
import q.MenuC2151h;
import q.SubMenuC2161r;

/* JADX INFO: renamed from: r.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2247f extends C2155l {
    public final /* synthetic */ int l = 1;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C2250i f21475m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2247f(C2250i c2250i, Context context, MenuC2151h menuC2151h, View view) {
        super(R.attr.actionOverflowMenuStyle, context, view, menuC2151h, true);
        this.f21475m = c2250i;
        this.f21092f = 8388613;
        C1764u c1764u = c2250i.f21482G;
        this.f21094h = c1764u;
        AbstractC2153j abstractC2153j = this.f21095i;
        if (abstractC2153j != null) {
            abstractC2153j.i(c1764u);
        }
    }

    @Override // q.C2155l
    public final void c() {
        switch (this.l) {
            case 0:
                C2250i c2250i = this.f21475m;
                c2250i.f21479D = null;
                c2250i.getClass();
                super.c();
                break;
            default:
                C2250i c2250i2 = this.f21475m;
                MenuC2151h menuC2151h = c2250i2.f21484n;
                if (menuC2151h != null) {
                    menuC2151h.c(true);
                }
                c2250i2.f21478C = null;
                super.c();
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2247f(C2250i c2250i, Context context, SubMenuC2161r subMenuC2161r, View view) {
        super(R.attr.actionOverflowMenuStyle, context, view, subMenuC2161r, false);
        this.f21475m = c2250i;
        if (!subMenuC2161r.f21116x.d()) {
            View view2 = c2250i.f21488s;
            this.f21091e = view2 == null ? c2250i.f21487r : view2;
        }
        C1764u c1764u = c2250i.f21482G;
        this.f21094h = c1764u;
        AbstractC2153j abstractC2153j = this.f21095i;
        if (abstractC2153j != null) {
            abstractC2153j.i(c1764u);
        }
    }
}
