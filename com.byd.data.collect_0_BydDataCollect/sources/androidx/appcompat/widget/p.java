package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface p {
    Context a();

    void b(Menu menu, m.a aVar);

    boolean c();

    void collapseActionView();

    boolean d();

    boolean e();

    boolean f();

    void g();

    CharSequence getTitle();

    boolean h();

    void i();

    void j(m.a aVar, g.a aVar2);

    int k();

    void l(int i2);

    Menu m();

    void n(int i2);

    void o(ScrollingTabContainerView scrollingTabContainerView);

    ViewGroup p();

    void q(boolean z);

    int r();

    b.g.k.x s(int i2, long j);

    void setIcon(int i2);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();

    boolean u();

    void v();

    void w(boolean z);

    void x(int i2);
}
