package defpackage;

import org.chromium.content_public.browser.MessagePayload;
import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: compiled from: PostMessageHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class y22 implements Runnable {
    public final /* synthetic */ String e;
    public final /* synthetic */ z22 f;

    public y22(z22 z22Var, String str) {
        this.f = z22Var;
        this.e = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        z22 z22Var = this.f;
        MessagePort[] messagePortArr = z22Var.d;
        if (messagePortArr == null || messagePortArr[0].isClosed()) {
            return;
        }
        z22Var.d[0].a(new MessagePayload(this.e));
    }
}
