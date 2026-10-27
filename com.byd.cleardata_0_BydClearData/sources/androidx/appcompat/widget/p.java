package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
class p extends PopupWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f1000a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f1001b;

    static {
        f1000a = Build.VERSION.SDK_INT < 21;
    }

    public p(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        v0 v0VarV = v0.v(context, attributeSet, a.a.j.b2, i, i2);
        int i3 = a.a.j.d2;
        if (v0VarV.s(i3)) {
            b(v0VarV.a(i3, false));
        }
        setBackgroundDrawable(v0VarV.g(a.a.j.c2));
        v0VarV.w();
    }

    private void b(boolean z) {
        if (f1000a) {
            this.f1001b = z;
        } else {
            androidx.core.widget.h.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f1000a && this.f1001b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f1000a && this.f1001b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f1000a && this.f1001b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }
}
