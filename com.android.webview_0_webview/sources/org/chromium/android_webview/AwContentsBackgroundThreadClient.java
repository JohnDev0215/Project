package org.chromium.android_webview;

import defpackage.C0426Xn;
import defpackage.RunnableC0390Vn;
import defpackage.Ts;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AwContentsBackgroundThreadClient {
    private AwWebResourceInterceptResponse shouldInterceptRequestFromNative(String str, boolean z, boolean z2, String str2, String[] strArr, String[] strArr2) {
        try {
            return new AwWebResourceInterceptResponse(a(new C0426Xn(str, z, z2, str2, strArr, strArr2)), false);
        } catch (Throwable th) {
            Ts.a("AwBgThreadClient", "Client raised exception in shouldInterceptRequest. Re-throwing on UI thread.", new Object[0]);
            ThreadUtils.c().post(new RunnableC0390Vn(this, th));
            return new AwWebResourceInterceptResponse(null, true);
        }
    }

    public abstract AwWebResourceResponse a(C0426Xn c0426Xn);
}
