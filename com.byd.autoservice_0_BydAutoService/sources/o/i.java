package o;

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
    public static final boolean f4303a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f4304b;

    static {
        f4303a = Build.VERSION.SDK_INT < 21;
    }

    public i(Context context, AttributeSet attributeSet, int i3, int i4) {
        super(context, attributeSet, i3, i4);
        a(context, attributeSet, i3, i4);
    }

    public final void a(Context context, AttributeSet attributeSet, int i3, int i4) {
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.PopupWindow, i3, i4);
        int i5 = R$styleable.PopupWindow_overlapAnchor;
        if (e0VarV.s(i5)) {
            b(e0VarV.a(i5, false));
        }
        setBackgroundDrawable(e0VarV.g(R$styleable.PopupWindow_android_popupBackground));
        e0VarV.w();
    }

    public final void b(boolean z3) {
        if (f4303a) {
            this.f4304b = z3;
        } else {
            n0.h.a(this, z3);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i3, int i4) {
        if (f4303a && this.f4304b) {
            i4 -= view.getHeight();
        }
        super.showAsDropDown(view, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i3, int i4, int i5, int i6) {
        if (f4303a && this.f4304b) {
            i4 -= view.getHeight();
        }
        super.update(view, i3, i4, i5, i6);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i3, int i4, int i5) {
        if (f4303a && this.f4304b) {
            i4 -= view.getHeight();
        }
        super.showAsDropDown(view, i3, i4, i5);
    }
}
