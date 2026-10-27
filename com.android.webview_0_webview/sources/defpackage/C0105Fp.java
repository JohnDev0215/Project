package defpackage;

import android.os.Build;
import org.chromium.android_webview.AwContents;
import org.chromium.base.TraceEvent;

/* JADX INFO: renamed from: Fp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0105Fp extends AwContents.VisualStateCallback {
    public final /* synthetic */ String a;
    public final /* synthetic */ C0123Gp b;

    public C0105Fp(C0123Gp c0123Gp, String str) {
        this.b = c0123Gp;
        this.a = str;
    }

    @Override // org.chromium.android_webview.AwContents.VisualStateCallback
    public void a(long j) {
        AbstractC0511ao abstractC0511ao = (AbstractC0511ao) this.b.c.get();
        if (abstractC0511ao == null) {
            return;
        }
        String str = this.a;
        U8 u8 = (U8) abstractC0511ao;
        try {
            TraceEvent.x("WebViewContentsClientAdapter.onPageCommitVisible", null);
            if (u8.i.a("VISUAL_STATE_CALLBACK")) {
                MM mm = u8.i;
                mm.a.onPageCommitVisible(u8.e, str);
            } else if (Build.VERSION.SDK_INT >= 23) {
                C1185mt.m(u8.h, u8.e, str);
            }
            AbstractC0229Mo.a(7);
        } finally {
            TraceEvent.A("WebViewContentsClientAdapter.onPageCommitVisible");
        }
    }
}
