package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.RestrictTo;
import androidx.appcompat.view.menu.l;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public interface B {
    void a(Menu menu, l.a aVar);

    boolean b();

    void c(CharSequence charSequence);

    void collapseActionView();

    boolean d();

    void e(Window.Callback callback);

    boolean f();

    boolean g();

    Context getContext();

    void h();

    boolean i();

    void j();

    int k();

    void l(int i);

    void m(M m);

    ViewGroup n();

    void o(boolean z);

    int p();

    androidx.core.view.v q(int i, long j);

    void r();

    boolean s();

    void t();

    void u(boolean z);

    void v(int i);
}
