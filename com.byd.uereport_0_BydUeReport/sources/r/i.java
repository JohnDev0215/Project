package r;

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
    public static final boolean f7562a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f7563b;

    static {
        f7562a = Build.VERSION.SDK_INT < 21;
    }

    public i(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        a(context, attributeSet, i8, i9);
    }

    public final void a(Context context, AttributeSet attributeSet, int i8, int i9) {
        e0 e0VarV = e0.v(context, attributeSet, R$styleable.PopupWindow, i8, i9);
        int i10 = R$styleable.PopupWindow_overlapAnchor;
        if (e0VarV.s(i10)) {
            b(e0VarV.a(i10, false));
        }
        setBackgroundDrawable(e0VarV.g(R$styleable.PopupWindow_android_popupBackground));
        e0VarV.w();
    }

    public final void b(boolean z7) {
        if (f7562a) {
            this.f7563b = z7;
        } else {
            r0.h.a(this, z7);
        }
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i8, int i9) {
        if (f7562a && this.f7563b) {
            i9 -= view.getHeight();
        }
        super.showAsDropDown(view, i8, i9);
    }

    @Override // android.widget.PopupWindow
    public void update(View view, int i8, int i9, int i10, int i11) {
        if (f7562a && this.f7563b) {
            i9 -= view.getHeight();
        }
        super.update(view, i8, i9, i10, i11);
    }

    @Override // android.widget.PopupWindow
    public void showAsDropDown(View view, int i8, int i9, int i10) {
        if (f7562a && this.f7563b) {
            i9 -= view.getHeight();
        }
        super.showAsDropDown(view, i8, i9, i10);
    }
}
