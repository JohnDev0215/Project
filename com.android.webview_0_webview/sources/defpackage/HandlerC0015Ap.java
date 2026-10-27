package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.chromium.content_public.browser.NavigationController;

/* JADX INFO: renamed from: Ap, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HandlerC0015Ap extends Handler {
    public final /* synthetic */ C0069Dp a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HandlerC0015Ap(C0069Dp c0069Dp, Looper looper) {
        super(looper);
        this.a = c0069Dp;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        NavigationController navigationController = this.a.a.D;
        if (navigationController == null) {
            return;
        }
        int i = message.what;
        if (i == 1) {
            navigationController.f();
        } else if (i == 2) {
            navigationController.d();
        } else {
            StringBuilder sbF = Z7.f("WebContentsDelegateAdapter: unhandled message ");
            sbF.append(message.what);
            throw new IllegalStateException(sbF.toString());
        }
    }
}
