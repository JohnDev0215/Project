package defpackage;

import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: ScopeKey.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pe2 {
    public final int a;
    public final WebContents b;
    public final WindowAndroid c;

    public pe2(WebContents webContents, int i) {
        this.a = i;
        this.b = webContents;
        this.c = null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof pe2)) {
            return false;
        }
        pe2 pe2Var = (pe2) obj;
        return this.a == pe2Var.a && this.c == pe2Var.c && this.b == pe2Var.b;
    }

    public final int hashCode() {
        int i = (527 + this.a) * 31;
        WebContents webContents = this.b;
        int iHashCode = (i + (webContents == null ? 0 : webContents.hashCode())) * 31;
        WindowAndroid windowAndroid = this.c;
        return iHashCode + (windowAndroid != null ? windowAndroid.hashCode() : 0);
    }

    public pe2(WindowAndroid windowAndroid) {
        this.a = 0;
        this.c = windowAndroid;
        this.b = null;
    }
}
