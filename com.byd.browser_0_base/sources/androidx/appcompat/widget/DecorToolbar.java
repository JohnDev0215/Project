package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Window;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import defpackage.in3;

/* JADX INFO: loaded from: classes.dex */
public interface DecorToolbar {
    boolean a();

    void b();

    boolean c();

    void collapseActionView();

    void d(MenuBuilder menuBuilder, MenuPresenter.Callback callback);

    boolean e();

    boolean f();

    boolean g();

    Context getContext();

    CharSequence getTitle();

    void h();

    void i();

    boolean j();

    void k(int i);

    void l();

    void m(int i);

    void n();

    in3 o(int i, long j);

    int p();

    void q(int i);

    void r();

    void s();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setTitle(CharSequence charSequence);

    void setVisibility(int i);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);

    void t(boolean z);
}
