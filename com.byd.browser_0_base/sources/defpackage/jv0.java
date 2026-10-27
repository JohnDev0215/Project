package defpackage;

import android.app.Activity;
import android.net.Uri;
import defpackage.lk1;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.history.BrowsingHistoryBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: HistoryPage.java */
/* JADX INFO: loaded from: classes.dex */
public final class jv0 extends be {
    public gv0 k;
    public final String l;

    public jv0(Activity activity, lk1.b bVar, wp2 wp2Var, boolean z, nw2 nw2Var, String str) {
        super(bVar);
        Uri uri = Uri.parse(str);
        this.k = new gv0(activity, false, wp2Var, z, nw2Var, uri.getPath().contains("journeys"), uri.getQueryParameter("q"), new BrowsingHistoryBridge(Profile.d()));
        this.l = bVar.a().getResources().getString(R$string.menu_history);
        c(this.k.i);
    }

    @Override // defpackage.be, defpackage.jk1
    public final void destroy() {
        this.k.E();
        this.k = null;
        super.destroy();
    }

    @Override // defpackage.jk1
    public final String getTitle() {
        return this.l;
    }

    @Override // defpackage.jk1
    public final String k() {
        return "history";
    }
}
