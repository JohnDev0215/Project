package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.ScrollingTabContainerView;
import defpackage.p1;
import defpackage.v1;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface r2 {
    void a(Menu menu, v1.a aVar);

    boolean b();

    boolean c();

    void collapseActionView();

    boolean d();

    boolean e();

    void f();

    boolean g();

    CharSequence getTitle();

    void h();

    void i(v1.a aVar, p1.a aVar2);

    int j();

    void k(int i);

    Menu l();

    void m(int i);

    void n(ScrollingTabContainerView scrollingTabContainerView);

    ViewGroup o();

    void p(boolean z);

    Context q();

    int r();

    x9 s(int i, long j);

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
