package org.chromium.chrome.browser.tabmodel;

import defpackage.t13;
import defpackage.w03;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: loaded from: classes2.dex */
public interface TabModel extends w03 {
    boolean a(Tab tab);

    @Deprecated
    void b();

    void c();

    void d();

    void destroy();

    void e(boolean z);

    Profile f();

    Tab g(int i, boolean z);

    boolean h(Tab tab, Tab tab2, boolean z, boolean z2);

    void i(Tab tab, int i, int i2, int i3);

    boolean isActiveModel();

    void j(t13 t13Var);

    w03 k();

    void l(t13 t13Var);

    boolean m(Tab tab, boolean z, boolean z2);

    void n(int i);

    void o(Tab tab);

    void p(List list);

    void q(int i, int i2, boolean z);

    void s(int i, int i2);

    void setActive(boolean z);

    boolean u();

    void v();

    boolean w(int i);

    void x(int i);
}
