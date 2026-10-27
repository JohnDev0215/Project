package defpackage;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: AppCompatPopupWindow.java */
/* JADX INFO: loaded from: classes.dex */
public class k2 extends PopupWindow {
    public static final boolean a;
    public boolean b;

    static {
        a = Build.VERSION.SDK_INT < 21;
    }

    public k2(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i, i2);
    }

    public final void a(Context context, AttributeSet attributeSet, int i, int i2) {
        g3 g3VarV = g3.v(context, attributeSet, R$styleable.PopupWindow, i, i2);
        int i3 = R$styleable.PopupWindow_overlapAnchor;
        if (g3VarV.s(i3)) {
            b(g3VarV.a(i3, false));
        }
        setBackgroundDrawable(g3VarV.g(R$styleable.PopupWindow_android_popupBackground));
        g3VarV.w();
    }

    public final void b(boolean z) {
        if (a) {
            this.b = z;
        } else {
            pa.a(this, z);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i, int i2, int i3, int i4) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.update(view, i, i2, i3, i4);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i, int i2, int i3) {
        if (a && this.b) {
            i2 -= view.getHeight();
        }
        super.showAsDropDown(view, i, i2, i3);
    }
}
