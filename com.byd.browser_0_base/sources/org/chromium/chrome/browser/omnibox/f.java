package org.chromium.chrome.browser.omnibox;

import defpackage.km1;
import defpackage.rj3;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: LocationBarDataProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public interface f {
    int a();

    rj3 b();

    int c(boolean z);

    Tab d();

    boolean e();

    km1 g();

    String getTitle();

    default boolean h() {
        return false;
    }

    boolean isIncognito();

    int j(boolean z, boolean z2);

    void l(a aVar);

    int m();

    String n();

    void o(a aVar);

    int p();

    boolean q();

    int r();

    boolean s();

    /* JADX INFO: compiled from: LocationBarDataProvider.java */
    public interface a {
        default void D() {
        }

        default void I() {
        }

        default void f() {
        }

        default void h() {
        }

        default void j() {
        }

        default void r() {
        }

        default void u() {
        }
    }
}
