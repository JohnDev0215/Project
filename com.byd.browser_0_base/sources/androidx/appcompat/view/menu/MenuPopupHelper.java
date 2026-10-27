package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R;
import androidx.appcompat.view.menu.MenuPresenter;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MenuPopupHelper implements MenuHelper {
    public final Context a;
    public final MenuBuilder b;
    public final boolean c;
    public final int d;
    public final int e;
    public View f;
    public int g;
    public boolean h;
    public MenuPresenter.Callback i;
    public MenuPopup j;
    public PopupWindow.OnDismissListener k;
    public final PopupWindow.OnDismissListener l;

    public static class Api17Impl {
        private Api17Impl() {
        }
    }

    public MenuPopupHelper(Context context, MenuBuilder menuBuilder, View view, boolean z, int i) {
        this(i, 0, context, view, menuBuilder, z);
    }

    public final MenuPopup a() {
        MenuPopup standardMenuPopup;
        if (this.j == null) {
            Context context = this.a;
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getRealSize(point);
            if (Math.min(point.x, point.y) >= context.getResources().getDimensionPixelSize(R.dimen.abc_cascading_menus_min_smallest_width)) {
                standardMenuPopup = new CascadingMenuPopup(this.a, this.f, this.d, this.e, this.c);
            } else {
                standardMenuPopup = new StandardMenuPopup(this.d, this.e, this.a, this.f, this.b, this.c);
            }
            standardMenuPopup.n(this.b);
            standardMenuPopup.setOnDismissListener(this.l);
            standardMenuPopup.p(this.f);
            standardMenuPopup.f(this.i);
            standardMenuPopup.q(this.h);
            standardMenuPopup.r(this.g);
            this.j = standardMenuPopup;
        }
        return this.j;
    }

    public final boolean b() {
        MenuPopup menuPopup = this.j;
        return menuPopup != null && menuPopup.b();
    }

    public void c() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public final void d(boolean z) {
        this.h = z;
        MenuPopup menuPopup = this.j;
        if (menuPopup != null) {
            menuPopup.q(z);
        }
    }

    public final void e(int i, int i2, boolean z, boolean z2) {
        MenuPopup menuPopupA = a();
        menuPopupA.t(z2);
        if (z) {
            int i3 = this.g;
            View view = this.f;
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if ((Gravity.getAbsoluteGravity(i3, om3.e.c(view)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            menuPopupA.s(i);
            menuPopupA.u(i2);
            int i4 = (int) ((this.a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            menuPopupA.e = new Rect(i - i4, i2 - i4, i + i4, i2 + i4);
        }
        menuPopupA.a();
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public MenuPopupHelper(int i, int i2, Context context, View view, MenuBuilder menuBuilder, boolean z) {
        this.g = 8388611;
        this.l = new PopupWindow.OnDismissListener() { // from class: androidx.appcompat.view.menu.MenuPopupHelper.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public final void onDismiss() {
                MenuPopupHelper.this.c();
            }
        };
        this.a = context;
        this.b = menuBuilder;
        this.f = view;
        this.c = z;
        this.d = i;
        this.e = i2;
    }
}
