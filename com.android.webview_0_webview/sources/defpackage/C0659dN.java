package defpackage;

import org.chromium.android_webview.AwContents;
import org.chromium.support_lib_boundary.VisualStateCallbackBoundaryInterface;

/* JADX INFO: renamed from: dN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0659dN extends AwContents.VisualStateCallback {
    public final /* synthetic */ VisualStateCallbackBoundaryInterface a;

    public C0659dN(C0714eN c0714eN, VisualStateCallbackBoundaryInterface visualStateCallbackBoundaryInterface) {
        this.a = visualStateCallbackBoundaryInterface;
    }

    @Override // org.chromium.android_webview.AwContents.VisualStateCallback
    public void a(long j) {
        this.a.onComplete(j);
    }
}
