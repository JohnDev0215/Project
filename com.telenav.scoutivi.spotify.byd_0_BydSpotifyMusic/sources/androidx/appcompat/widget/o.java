package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: AppCompatPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
class o extends PopupWindow {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final boolean f1164b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1165a;

    public o(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
        a(context, attributeSet, i7, i8);
    }

    private void a(Context context, AttributeSet attributeSet, int i7, int i8) {
        s0 s0VarV = s0.v(context, attributeSet, e.j.PopupWindow, i7, i8);
        int i9 = e.j.PopupWindow_overlapAnchor;
        if (s0VarV.s(i9)) {
            b(s0VarV.a(i9, false));
        }
        setBackgroundDrawable(s0VarV.g(e.j.PopupWindow_android_popupBackground));
        s0VarV.w();
    }

    private void b(boolean z6) {
        if (f1164b) {
            this.f1165a = z6;
        } else {
            androidx.core.widget.h.a(this, z6);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i7, int i8) {
        if (f1164b && this.f1165a) {
            i8 -= view.getHeight();
        }
        super.showAsDropDown(view, i7, i8);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i7, int i8, int i9, int i10) {
        if (f1164b && this.f1165a) {
            i8 -= view.getHeight();
        }
        super.update(view, i7, i8, i9, i10);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i7, int i8, int i9) {
        if (f1164b && this.f1165a) {
            i8 -= view.getHeight();
        }
        super.showAsDropDown(view, i7, i8, i9);
    }
}
