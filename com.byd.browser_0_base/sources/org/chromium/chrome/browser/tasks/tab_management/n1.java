package org.chromium.chrome.browser.tasks.tab_management;

import android.graphics.Rect;
import android.view.ViewGroup;
import defpackage.aq1;
import defpackage.nw2;
import defpackage.vb;
import org.chromium.chrome.browser.tasks.tab_management.q1;

/* JADX INFO: compiled from: TabSwitcher.java */
/* JADX INFO: loaded from: classes2.dex */
public interface n1 {

    /* JADX INFO: compiled from: TabSwitcher.java */
    public interface b {
        void t(int i, long j);
    }

    /* JADX INFO: compiled from: TabSwitcher.java */
    public interface d {
        void d();

        void e();

        void k();

        void s();
    }

    boolean a();

    void d();

    a e();

    nw2<Boolean> k();

    p1 m();

    c s();

    void setOnTabSelectingListener(b bVar);

    /* JADX INFO: compiled from: TabSwitcher.java */
    public interface c {
        long b();

        int c();

        void g(Runnable runnable);

        Rect n(boolean z);

        void p();

        int r();

        boolean t();

        default void i() {
        }
    }

    /* JADX INFO: compiled from: TabSwitcher.java */
    public interface a extends vb {
        aq1 E0();

        int L();

        void Z0(boolean z);

        boolean a();

        void b1(q1.a aVar);

        void g(boolean z);

        default ViewGroup n() {
            return null;
        }

        void t1();

        void u0(d dVar);

        void v0();

        boolean w();

        @Deprecated
        boolean w1();

        void z0(long j);

        default void i(ViewGroup viewGroup) {
        }
    }
}
