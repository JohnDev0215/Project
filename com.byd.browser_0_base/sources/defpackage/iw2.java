package defpackage;

import J.N;
import defpackage.dq1;
import java.util.ArrayList;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.DeletedPageInfo;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;

/* JADX INFO: compiled from: SuggestionsOfflineModelObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class iw2<T extends dq1> extends OfflinePageBridge.a implements x80 {
    public final OfflinePageBridge a;

    /* JADX INFO: compiled from: SuggestionsOfflineModelObserver.java */
    public class a implements Callback<OfflinePageItem> {
        public final /* synthetic */ dq1 e;

        public a(b bVar, dq1 dq1Var) {
            this.e = dq1Var;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.chromium.base.Callback
        public final void onResult(OfflinePageItem offlinePageItem) {
            iw2.this.e(this.e, offlinePageItem);
        }
    }

    /* JADX INFO: compiled from: SuggestionsOfflineModelObserver.java */
    public static class b {
        public final void a(boolean z) {
            throw null;
        }
    }

    public iw2(OfflinePageBridge offlinePageBridge) {
        this.a = offlinePageBridge;
        offlinePageBridge.c.a(this);
    }

    @Override // org.chromium.chrome.browser.offlinepages.OfflinePageBridge.a
    public final void a() {
        f();
    }

    @Override // org.chromium.chrome.browser.offlinepages.OfflinePageBridge.a
    public final void b(DeletedPageInfo deletedPageInfo) {
        for (dq1 dq1Var : d()) {
            dq1Var.a();
            Long lB = dq1Var.b();
            if (lB != null && lB.longValue() == deletedPageInfo.a) {
                g(dq1Var, null);
            }
        }
    }

    @Override // org.chromium.chrome.browser.offlinepages.OfflinePageBridge.a
    public final void c() {
        f();
    }

    public abstract ArrayList d();

    public abstract void e(T t, OfflinePageItem offlinePageItem);

    public final void f() {
        for (dq1 dq1Var : d()) {
            dq1Var.a();
            g(dq1Var, null);
        }
    }

    public final void g(T t, b bVar) {
        OfflinePageBridge offlinePageBridge = this.a;
        if (offlinePageBridge.b) {
            N.MR_37z77(offlinePageBridge.a, offlinePageBridge, t.getUrl(), 0, new a(bVar, t));
        } else if (bVar != null) {
            bVar.a(false);
        }
    }

    @Override // defpackage.x80
    public final void onDestroy() {
        this.a.c.c(this);
    }
}
