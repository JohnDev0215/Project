package defpackage;

import android.content.Context;
import defpackage.hu2;
import java.util.Collection;
import java.util.Iterator;
import org.chromium.chrome.R$string;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: StorageSummaryProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class ju2 implements qq1 {
    public final Context e;
    public final a f;
    public ma0 g;
    public long h;

    /* JADX INFO: compiled from: StorageSummaryProvider.java */
    public interface a {
    }

    public ju2(Context context, pq2 pq2Var, lg2 lg2Var) {
        this.e = context;
        this.f = pq2Var;
        if (lg2Var != null) {
            lg2Var.a(this);
            this.h = a(lg2Var.f);
        }
        new iu2(this).c(i9.e);
    }

    public static long a(Collection collection) {
        Iterator it = collection.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += ((OfflineItem) it.next()).D;
        }
        return j;
    }

    @Override // defpackage.qq1
    public final void b(Collection<OfflineItem> collection) {
        this.h = a(collection) + this.h;
        f();
    }

    @Override // defpackage.qq1
    public final void d(Collection<OfflineItem> collection) {
        this.h -= a(collection);
        f();
    }

    @Override // defpackage.qq1
    public final void e(OfflineItem offlineItem, OfflineItem offlineItem2) {
        long j = this.h - offlineItem.D;
        this.h = j;
        this.h = j + offlineItem2.D;
        if (offlineItem2.A != 0) {
            f();
        }
    }

    public final void f() {
        if (this.g == null) {
            return;
        }
        int i = R$string.download_manager_ui_space_using;
        long j = this.h;
        Context context = this.e;
        ((hu2) ((pq2) this.f).f).a.p(hu2.a.a, context.getString(i, md0.a(context, j), md0.a(context, this.g.d)));
    }
}
