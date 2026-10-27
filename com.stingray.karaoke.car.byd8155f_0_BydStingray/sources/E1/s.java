package e1;

import D.C0130l;
import F0.AbstractC0191a;
import T.C0735d;
import T.C0738e0;
import T.C0754m0;
import T.C0761q;
import T.S;
import android.content.Context;
import android.view.View;
import android.view.Window;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class s extends AbstractC0191a implements u {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Window f13805t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f13806u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f13807v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f13808w;

    public s(Context context, Window window) {
        super(context);
        this.f13805t = window;
        this.f13806u = C0735d.O(q.f13803a, S.f8224q);
    }

    @Override // F0.AbstractC0191a
    public final void a(int i7, C0761q c0761q) {
        int i10;
        c0761q.S(1735448596);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(this) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            ((InterfaceC2785n) this.f13806u.getValue()).invoke(c0761q, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0130l(i7, 7, this);
        }
    }

    @Override // F0.AbstractC0191a
    public final void e(boolean z6, int i7, int i10, int i11, int i12) {
        View childAt;
        super.e(z6, i7, i10, i11, i12);
        if (this.f13807v || (childAt = getChildAt(0)) == null) {
            return;
        }
        this.f13805t.setLayout(childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
    }

    @Override // F0.AbstractC0191a
    public final void f(int i7, int i10) {
        if (this.f13807v) {
            super.f(i7, i10);
            return;
        }
        super.f(View.MeasureSpec.makeMeasureSpec(Math.round(getContext().getResources().getConfiguration().screenWidthDp * getContext().getResources().getDisplayMetrics().density), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(Math.round(getContext().getResources().getConfiguration().screenHeightDp * getContext().getResources().getDisplayMetrics().density), Integer.MIN_VALUE));
    }

    @Override // F0.AbstractC0191a
    public final boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.f13808w;
    }
}
