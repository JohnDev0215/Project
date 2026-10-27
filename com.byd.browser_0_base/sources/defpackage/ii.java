package defpackage;

import android.view.View;

/* JADX INFO: compiled from: BottomSheetContent.java */
/* JADX INFO: loaded from: classes2.dex */
public interface ii {
    View d();

    void destroy();

    int e();

    default boolean f() {
        return false;
    }

    View g();

    int h();

    int i();

    default int j() {
        return 0;
    }

    default boolean k() {
        return false;
    }

    default aq1<Boolean> l() {
        aq1<Boolean> aq1Var = new aq1<>();
        aq1Var.r(Boolean.FALSE);
        return aq1Var;
    }

    int m();

    default float n() {
        return 0.0f;
    }

    default boolean o() {
        return false;
    }

    int p();

    default boolean q() {
        return true;
    }

    int r();

    default float s() {
        return 0.0f;
    }

    default boolean t() {
        return false;
    }

    boolean u();

    default void a() {
    }
}
