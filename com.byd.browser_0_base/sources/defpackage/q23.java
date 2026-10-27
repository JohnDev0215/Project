package defpackage;

import java.io.File;
import java.util.List;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* JADX INFO: compiled from: TabPersistencePolicy.java */
/* JADX INFO: loaded from: classes2.dex */
public interface q23 {
    void a(a33 a33Var);

    void b(TabContentManager tabContentManager);

    void c(boolean z);

    String d();

    void destroy();

    void e();

    boolean f();

    boolean g(oi2 oi2Var);

    void h(int i);

    List<String> j();

    boolean l();

    default boolean m() {
        return false;
    }

    File n();

    void o();

    default void k(oi2 oi2Var) {
    }

    default void i(b33 b33Var, int i) {
    }
}
