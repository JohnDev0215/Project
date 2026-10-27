package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.keyboard_accessory.d;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: UnusedAppRestrictionsBackportCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class gj3 {
    public final Object a;

    public gj3(int i) {
        if (i == 2) {
            this.a = new HashMap();
        } else if (i != 3) {
            this.a = null;
        } else {
            this.a = new HashMap();
        }
    }

    public final d a(WebContents webContents) {
        if (webContents == null || webContents.isDestroyed()) {
            return new d(null);
        }
        Object obj = this.a;
        d dVar = (d) ((HashMap) obj).get(webContents);
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d(webContents);
        ((HashMap) obj).put(webContents, dVar2);
        return dVar2;
    }
}
