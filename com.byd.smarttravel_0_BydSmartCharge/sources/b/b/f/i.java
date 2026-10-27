package b.b.f;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends PopupWindow {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f790b;

    static {
        f789a = Build.VERSION.SDK_INT < 21;
    }

    public i(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    public final void a(Context context, AttributeSet attributeSet, int i, int i2) {
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.PopupWindow, i, i2);
        int i3 = R$styleable.PopupWindow_overlapAnchor;
        if (e0VarV.s(i3)) {
            b(e0VarV.a(i3, false));
        }
        setBackgroundDrawable(e0VarV.g(R$styleable.PopupWindow_android_popupBackground));
        e0VarV.w();
    }

    public final void b(boolean z) {
        if (f789a) {
            this.f790b = z;
        } else {
            b.g.k.h.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (f789a && this.f790b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (f789a && this.f790b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (f789a && this.f790b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}
