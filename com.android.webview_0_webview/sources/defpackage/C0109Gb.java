package defpackage;

import android.webkit.SslErrorHandler;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: Gb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0109Gb extends SslErrorHandler {
    public final /* synthetic */ Callback a;

    public C0109Gb(C0252Ob c0252Ob, Callback callback) {
        this.a = callback;
    }

    @Override // android.webkit.SslErrorHandler
    public void cancel() {
        this.a.a(Boolean.FALSE);
    }

    @Override // android.webkit.SslErrorHandler
    public void proceed() {
        this.a.a(Boolean.TRUE);
    }
}
