package r;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.ScrollingTabContainerView;
import q.g;
import q.m;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface p {
    void a(Menu menu, m.a aVar);

    boolean b();

    void c();

    void collapseActionView();

    boolean d();

    boolean e();

    boolean f();

    boolean g();

    CharSequence getTitle();

    void h();

    void i(m.a aVar, g.a aVar2);

    void j(int i8);

    void k(ScrollingTabContainerView scrollingTabContainerView);

    ViewGroup l();

    void m(boolean z7);

    Context n();

    boolean o();

    void p(int i8);

    int q();

    Menu r();

    void s(int i8);

    void setIcon(int i8);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    int t();

    o0.x u(int i8, long j8);

    void v();

    void w();

    void x(boolean z7);
}
