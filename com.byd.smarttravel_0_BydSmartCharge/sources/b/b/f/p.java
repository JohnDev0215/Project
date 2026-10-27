package b.b.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.ScrollingTabContainerView;
import b.b.e.j.g;
import b.b.e.j.m;

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

    void k(int i);

    Menu l();

    void m(int i);

    void n(ScrollingTabContainerView scrollingTabContainerView);

    ViewGroup o();

    void p(boolean z);

    Context q();

    int r();

    b.g.j.x s(int i, long j);

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();

    boolean u();

    void v();

    void w(boolean z);

    void x(int i);
}
