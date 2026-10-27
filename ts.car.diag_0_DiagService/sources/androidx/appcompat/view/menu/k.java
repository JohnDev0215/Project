package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.R$dimen;
import androidx.appcompat.view.menu.l;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f236a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f237b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f238c;
    private final int d;
    private final int e;
    private View f;
    private int g;
    private boolean h;
    private l.a i;
    private j j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            k.this.e();
        }
    }

    public k(@NonNull Context context, @NonNull f fVar, @NonNull View view, boolean z, @AttrRes int i) {
        this(context, fVar, view, z, i, 0);
    }

    @NonNull
    private j a() {
        Display defaultDisplay = ((WindowManager) this.f236a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        j cascadingMenuPopup = Math.min(point.x, point.y) >= this.f236a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new CascadingMenuPopup(this.f236a, this.f, this.d, this.e, this.f238c) : new p(this.f236a, this.f237b, this.f, this.d, this.e, this.f238c);
        cascadingMenuPopup.l(this.f237b);
        cascadingMenuPopup.u(this.l);
        cascadingMenuPopup.p(this.f);
        cascadingMenuPopup.h(this.i);
        cascadingMenuPopup.r(this.h);
        cascadingMenuPopup.s(this.g);
        return cascadingMenuPopup;
    }

    private void l(int i, int i2, boolean z, boolean z2) {
        j jVarC = c();
        jVarC.v(z2);
        if (z) {
            if ((androidx.core.view.d.a(this.g, ViewCompat.q(this.f)) & 7) == 5) {
                i -= this.f.getWidth();
            }
            jVarC.t(i);
            jVarC.w(i2);
            int i3 = (int) ((this.f236a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            jVarC.q(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        jVarC.f();
    }

    public void b() {
        if (d()) {
            this.j.dismiss();
        }
    }

    @NonNull
    public j c() {
        if (this.j == null) {
            this.j = a();
        }
        return this.j;
    }

    public boolean d() {
        j jVar = this.j;
        return jVar != null && jVar.b();
    }

    protected void e() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void f(@NonNull View view) {
        this.f = view;
    }

    public void g(boolean z) {
        this.h = z;
        j jVar = this.j;
        if (jVar != null) {
            jVar.r(z);
        }
    }

    public void h(int i) {
        this.g = i;
    }

    public void i(@Nullable PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public void j(@Nullable l.a aVar) {
        this.i = aVar;
        j jVar = this.j;
        if (jVar != null) {
            jVar.h(aVar);
        }
    }

    public void k() {
        if (!m()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean m() {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(0, 0, false, false);
        return true;
    }

    public boolean n(int i, int i2) {
        if (d()) {
            return true;
        }
        if (this.f == null) {
            return false;
        }
        l(i, i2, true, true);
        return true;
    }

    public k(@NonNull Context context, @NonNull f fVar, @NonNull View view, boolean z, @AttrRes int i, @StyleRes int i2) {
        this.g = 8388611;
        this.l = new a();
        this.f236a = context;
        this.f237b = fVar;
        this.f = view;
        this.f238c = z;
        this.d = i;
        this.e = i2;
    }
}
