package org.chromium.weblayer_private;

import J.N;
import android.content.Intent;
import android.webkit.ValueCallback;
import defpackage.RR;
import defpackage.Z7;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ProfileImpl extends RR {
    public final String a;
    public long b;
    public CookieManagerImpl c;
    public Runnable d;
    public boolean e;
    public boolean f;
    public DownloadCallbackProxy g;
    public List h = new ArrayList();

    public ProfileImpl(String str, Runnable runnable) {
        if (!str.matches("^\\w*$")) {
            throw new IllegalArgumentException(Z7.c("Name can only contain words: ", str));
        }
        this.a = str;
        this.b = N.MKGVVWNd(str, this);
        this.c = new CookieManagerImpl(N.MxyNOHnU(this.b));
        this.d = runnable;
        this.g = new DownloadCallbackProxy(this.a, this.b);
    }

    public static void d(final ValueCallback valueCallback) {
        N.Ms_xftcU(new Callback(valueCallback) { // from class: zQ
            public final ValueCallback a;

            {
                this.a = valueCallback;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                this.a.onReceiveValue((String[]) obj);
            }
        });
    }

    public void c() {
        if (this.e) {
            StringBuilder sbF = Z7.f("Profile being destroyed: ");
            sbF.append(this.a);
            throw new IllegalArgumentException(sbF.toString());
        }
    }

    public void downloadsInitialized() {
        this.f = true;
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            DownloadImpl.v((Intent) it.next());
        }
        this.h.clear();
    }
}
