package org.chromium.components.offline_items_collection;

import defpackage.ny;
import defpackage.va2;
import defpackage.wq;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: OfflineContentProvider.java */
/* JADX INFO: loaded from: classes3.dex */
public interface a {

    /* JADX INFO: renamed from: org.chromium.components.offline_items_collection.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: OfflineContentProvider.java */
    public interface InterfaceC0134a {
        void b(List<OfflineItem> list);

        void d(OfflineItem offlineItem, UpdateDelta updateDelta);

        void e(ny nyVar);
    }

    void a(ny nyVar);

    void b(Callback<ArrayList<OfflineItem>> callback);

    void c(ny nyVar);

    void d(ny nyVar, ShareCallback shareCallback);

    void e(wq wqVar, ny nyVar);

    void f(ny nyVar, boolean z);

    void g(ny nyVar, VisualsCallback visualsCallback);

    void h(ny nyVar);

    void i(ny nyVar, String str, va2 va2Var);

    void j(InterfaceC0134a interfaceC0134a);

    void k(InterfaceC0134a interfaceC0134a);
}
