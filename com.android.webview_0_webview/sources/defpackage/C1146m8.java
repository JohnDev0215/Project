package defpackage;

import android.webkit.RenderProcessGoneDetail;

/* JADX INFO: renamed from: m8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1146m8 extends RenderProcessGoneDetail {
    public final /* synthetic */ C0427Xo a;

    public C1146m8(C0427Xo c0427Xo) {
        this.a = c0427Xo;
    }

    @Override // android.webkit.RenderProcessGoneDetail
    public boolean didCrash() {
        return this.a.a;
    }

    @Override // android.webkit.RenderProcessGoneDetail
    public int rendererPriorityAtExit() {
        return this.a.b;
    }
}
