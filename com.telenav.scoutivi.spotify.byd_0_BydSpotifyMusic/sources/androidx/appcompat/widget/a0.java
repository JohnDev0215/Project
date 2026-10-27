package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.view.menu.j;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface a0 {
    void a(Menu menu, j.a aVar);

    boolean b();

    boolean c();

    void collapseActionView();

    boolean d();

    boolean e();

    void f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    void h();

    int i();

    void j(int i7);

    void k(int i7);

    void l(l0 l0Var);

    ViewGroup m();

    void n(boolean z6);

    int o();

    i0.y p(int i7, long j7);

    void q();

    boolean r();

    void s();

    void setIcon(int i7);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t(boolean z6);

    void u(int i7);
}
