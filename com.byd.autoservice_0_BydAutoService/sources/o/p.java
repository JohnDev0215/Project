package o;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.ScrollingTabContainerView;
import n.g;
import n.m;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface p {
    void a(Menu menu, m.a aVar);

    boolean b();

    boolean c();

    void collapseActionView();

    boolean d();

    boolean e();

    void f();

    boolean g();

    CharSequence getTitle();

    void h();

    void i(m.a aVar, g.a aVar2);

    int j();

    void k(int i3);

    Menu l();

    void m(int i3);

    void n(ScrollingTabContainerView scrollingTabContainerView);

    ViewGroup o();

    void p(boolean z3);

    Context q();

    int r();

    l0.x s(int i3, long j3);

    void setIcon(int i3);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();

    boolean u();

    void v();

    void w(boolean z3);

    void x(int i3);
}
