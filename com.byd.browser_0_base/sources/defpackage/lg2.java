package defpackage;

import J.N;
import android.text.TextUtils;
import java.util.Locale;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: SearchOfflineItemFilter.java */
/* JADX INFO: loaded from: classes.dex */
public final class lg2 extends pq1 {
    public String h;

    public lg2(k80 k80Var) {
        super(k80Var);
        k();
    }

    @Override // defpackage.pq1
    public final boolean j(OfflineItem offlineItem) {
        if (TextUtils.isEmpty(this.h)) {
            return false;
        }
        String strMR6Af3ZS = N.MR6Af3ZS(offlineItem.x.j(), 1);
        if (TextUtils.isEmpty(strMR6Af3ZS) ? false : strMR6Af3ZS.toLowerCase(Locale.getDefault()).contains(this.h)) {
            return false;
        }
        String str = offlineItem.f;
        return !(TextUtils.isEmpty(str) ? false : str.toLowerCase(Locale.getDefault()).contains(this.h));
    }
}
