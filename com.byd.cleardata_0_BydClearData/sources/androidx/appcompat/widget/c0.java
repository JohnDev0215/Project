package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.m;

/* JADX INFO: loaded from: classes.dex */
public interface c0 {
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

    int i();

    void j(int i);

    void k(int i);

    void l(o0 o0Var);

    ViewGroup m();

    void n(boolean z);

    Context o();

    int p();

    a.g.k.x q(int i, long j);

    void r();

    boolean s();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t();

    void u(boolean z);

    void v(int i);
}
