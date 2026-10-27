package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import defpackage.k2;

/* JADX INFO: compiled from: ViewShiftingActionBarDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qn3 implements k2.e {
    public final ActionBar a;
    public final View b;
    public final View c;

    public qn3(ActionBar actionBar, View view, View view2) {
        this.a = actionBar;
        this.b = view;
        this.c = view2;
    }

    @Override // k2.e
    public final int a() {
        return ((ViewGroup.MarginLayoutParams) this.b.getLayoutParams()).topMargin;
    }

    @Override // k2.e
    public final void b(int i) {
        View view = this.b;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.topMargin = i;
        view.setLayoutParams(marginLayoutParams);
    }

    @Override // k2.e
    public final void c(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }

    @Override // k2.e
    public final ActionBar d() {
        return this.a;
    }
}
