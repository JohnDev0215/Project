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
    private static final boolean f638a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f639b;

    static {
        f638a = Build.VERSION.SDK_INT < 21;
    }

    public i(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        a(context, attributeSet, i2, i3);
    }

    private void a(Context context, AttributeSet attributeSet, int i2, int i3) {
        f0 f0VarV = f0.v(context, attributeSet, b.a.j.PopupWindow, i2, i3);
        int i4 = b.a.j.PopupWindow_overlapAnchor;
        if (f0VarV.s(i4)) {
            b(f0VarV.a(i4, false));
        }
        setBackgroundDrawable(f0VarV.g(b.a.j.PopupWindow_android_popupBackground));
        f0VarV.w();
    }

    private void b(boolean z) {
        if (f638a) {
            this.f639b = z;
        } else {
            androidx.core.widget.h.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3) {
        if (f638a && this.f639b) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i2, int i3, int i4, int i5) {
        if (f638a && this.f639b) {
            i3 -= view.getHeight();
        }
        super.update(view, i2, i3, i4, i5);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i2, int i3, int i4) {
        if (f638a && this.f639b) {
            i3 -= view.getHeight();
        }
        super.showAsDropDown(view, i2, i3, i4);
    }
}
