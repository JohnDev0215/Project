package org.chromium.android_webview;

import android.content.SharedPreferences;
import defpackage.AbstractC0178Jq;
import defpackage.AbstractC0279Pk;
import defpackage.Bt;
import defpackage.C0212Lo;
import defpackage.C0683dt;
import defpackage.C0735ep;
import defpackage.C0901hn;
import defpackage.Ct;
import defpackage.Is;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwBrowserContext {
    public static AwBrowserContext g;
    public final SharedPreferences a;
    public C0212Lo b;
    public C0735ep c;
    public AwQuotaManagerBridge d;
    public long e;
    public final boolean f;

    public AwBrowserContext(SharedPreferences sharedPreferences, long j, boolean z) {
        this.e = j;
        this.a = sharedPreferences;
        this.f = z;
        if (z) {
            C0683dt c0683dtA = C0683dt.A();
            try {
                C0212Lo.f(Is.a.getSharedPreferences("WebViewChromiumPrefs", 0), this.a);
                c0683dtA.close();
            } catch (Throwable th) {
                try {
                    c0683dtA.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
                throw th;
            }
        }
        Objects.requireNonNull(AbstractC0178Jq.a());
        Ct ct = Ct.i;
        Objects.requireNonNull(ct);
        ThreadUtils.b();
        Is.a.registerComponentCallbacks(new Bt(ct));
        AwContentsLifecycleNotifier.a.f(new C0901hn(this));
    }

    public static AwBrowserContext create(long j, boolean z) {
        C0683dt c0683dtA = C0683dt.A();
        try {
            SharedPreferences sharedPreferences = Is.a.getSharedPreferences("WebViewProfilePrefsDefault", 0);
            c0683dtA.close();
            return new AwBrowserContext(sharedPreferences, j, z);
        } catch (Throwable th) {
            try {
                c0683dtA.close();
            } catch (Throwable th2) {
                AbstractC0279Pk.a.a(th, th2);
            }
            throw th;
        }
    }

    public C0212Lo a() {
        if (this.b == null) {
            this.b = new C0212Lo(this.a);
        }
        return this.b;
    }
}
