package org.chromium.content.browser;

import J.N;
import android.util.Pair;
import defpackage.AbstractC1018jt;
import defpackage.InterfaceC1074kt;
import defpackage.QD;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JavascriptInjectorImpl extends AbstractC1018jt implements QD, InterfaceC1074kt {
    public final Set s = new HashSet();
    public final Map t = new HashMap();
    public long u;

    public JavascriptInjectorImpl(WebContents webContents) {
        this.u = N.MaMB25XA(this, webContents, this.s);
    }

    private void onDestroy() {
        this.u = 0L;
    }

    public void a(Object obj, String str, Class cls) {
        if (this.u == 0 || obj == null) {
            return;
        }
        this.t.put(str, new Pair(obj, cls));
        N.Mpa5DCUY(this.u, this, obj, str, cls);
    }
}
