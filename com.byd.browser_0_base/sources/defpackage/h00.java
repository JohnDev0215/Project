package defpackage;

import android.util.Log;
import java.io.File;
import org.chromium.chrome.browser.cookies.CookiesFetcher;

/* JADX INFO: compiled from: CookiesFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class h00 extends ac<Void> {
    @Override // defpackage.i9
    public final Object b() {
        File file = new File(CookiesFetcher.b());
        if (!file.exists() || file.delete()) {
            return null;
        }
        Log.e("cr_CookiesFetcher", "Failed to delete " + file.getName());
        return null;
    }
}
