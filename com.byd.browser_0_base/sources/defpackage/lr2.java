package defpackage;

import android.view.ViewGroup;
import com.google.android.material.appbar.AppBarLayout;
import defpackage.l43;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.chrome.features.tasks.TasksView;

/* JADX INFO: compiled from: StartSurface.java */
/* JADX INFO: loaded from: classes2.dex */
public interface lr2 {

    /* JADX INFO: compiled from: StartSurface.java */
    public interface a extends n1.b {
    }

    /* JADX INFO: compiled from: StartSurface.java */
    @Deprecated
    public interface b {
        void a(int i, boolean z);
    }

    /* JADX INFO: compiled from: StartSurface.java */
    public interface c {
        void d();

        void e();

        void f();

        void h();
    }

    n1.c A();

    boolean B();

    int C();

    void D();

    aq1 E();

    void F(int i);

    n1.c G();

    void H(l43.a aVar);

    void I();

    boolean a();

    void addHeaderOffsetChangeListener(AppBarLayout.f fVar);

    void b();

    void c(boolean z);

    void d();

    void destroy();

    void e();

    void g(boolean z);

    void i(ViewGroup viewGroup);

    nw2<Boolean> k();

    ViewGroup n();

    void o();

    void p(l43.a aVar);

    void q(b bVar);

    void r(boolean z);

    void removeHeaderOffsetChangeListener(AppBarLayout.f fVar);

    void s(long j, boolean z);

    void setOnTabSelectingListener(a aVar);

    int t();

    void u(b bVar);

    TasksView v();

    void w();

    @Deprecated
    boolean x();

    void y(int i, int i2);

    void z();
}
