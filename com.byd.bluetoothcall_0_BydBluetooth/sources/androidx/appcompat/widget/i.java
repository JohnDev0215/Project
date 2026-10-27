package androidx.appcompat.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: AppCompatPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
class i extends PopupWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f613a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f614b;

    static {
        f613a = Build.VERSION.SDK_INT < 21;
    }

    public i(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    private void a(Context context, AttributeSet attributeSet, int i, int i2) {
        e0 e0VarV = e0.v(context, attributeSet, b.a.j.d2, i, i2);
        int i3 = b.a.j.f2;
        if (e0VarV.s(i3)) {
            b(e0VarV.a(i3, false));
        }
        setBackgroundDrawable(e0VarV.g(b.a.j.e2));
        e0VarV.w();
    }

    private void b(boolean z) {
        if (f613a) {
            this.f614b = z;
        } else {
            androidx.core.widget.h.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f613a && this.f614b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f613a && this.f614b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f613a && this.f614b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}
