package org.chromium.chrome.browser.download.home.empty;

import android.content.Context;
import android.view.View;
import defpackage.eh3;
import defpackage.ih0;
import defpackage.qq1;
import defpackage.rq1;
import java.util.Collection;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.download.home.filter.b;
import org.chromium.components.offline_items_collection.OfflineItem;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: compiled from: EmptyCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements qq1, b.a {
    public final rq1 e;
    public final PropertyModel f;
    public final EmptyView g;
    public boolean h;

    public a(Context context, eh3 eh3Var) {
        PropertyModel propertyModel = new PropertyModel(ih0.c);
        this.f = propertyModel;
        this.e = eh3Var;
        eh3Var.a(this);
        EmptyView emptyView = new EmptyView(context);
        this.g = emptyView;
        j.b(propertyModel, emptyView, new b());
        f();
    }

    @Override // org.chromium.chrome.browser.download.home.filter.b.a
    public final void a(int i) {
        this.h = i == 7;
        f();
    }

    @Override // defpackage.qq1
    public final void b(Collection<OfflineItem> collection) {
        f();
    }

    @Override // defpackage.qq1
    public final void c() {
        f();
    }

    @Override // defpackage.qq1
    public final void d(Collection<OfflineItem> collection) {
        f();
    }

    public final void f() {
        int i;
        rq1 rq1Var = this.e;
        boolean zF = rq1Var.f();
        PropertyModel propertyModel = this.f;
        if (!zF) {
            i = 0;
        } else if (rq1Var.h().isEmpty()) {
            propertyModel.n(ih0.b, this.h ? R$string.download_manager_prefetch_tab_empty : R$string.download_manager_no_downloads);
            i = 1;
        } else {
            i = 2;
        }
        propertyModel.n(ih0.a, i);
    }

    public final View g() {
        return this.g.a;
    }

    @Override // defpackage.qq1
    public final void e(OfflineItem offlineItem, OfflineItem offlineItem2) {
    }
}
