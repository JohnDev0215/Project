package org.chromium.chrome.browser.feed;

import androidx.recyclerview.widget.RecyclerView;
import defpackage.al0;
import defpackage.c3;
import defpackage.z7;
import defpackage.zv0;
import java.util.ArrayList;

/* JADX INFO: compiled from: Stream.java */
/* JADX INFO: loaded from: classes.dex */
public interface g {

    /* JADX INFO: compiled from: Stream.java */
    public interface a {
        void a(ArrayList arrayList);
    }

    int a();

    void b(RecyclerView recyclerView, f fVar, z7 z7Var, zv0 zv0Var, al0 al0Var, int i);

    void c(a aVar);

    void d(int i);

    void e(c3 c3Var);

    default boolean f() {
        return false;
    }

    void g(boolean z);

    void h(a aVar);

    /* JADX INFO: compiled from: Stream.java */
    public interface b {
        default void a() {
        }

        default void b() {
        }

        default void c() {
        }
    }

    default void destroy() {
    }
}
