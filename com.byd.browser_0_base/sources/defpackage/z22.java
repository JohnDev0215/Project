package defpackage;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import org.chromium.components.content_relationship_verification.OriginVerifier;
import org.chromium.content_public.browser.MessagePayload;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PostMessageHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class z22 implements OriginVerifier.a {
    public final ke0 a = new ke0(5, this);
    public final w22 b;
    public WebContents c;
    public MessagePort[] d;
    public Uri e;

    public z22(d50 d50Var) {
        this.b = d50Var;
    }

    @Override // org.chromium.components.content_relationship_verification.OriginVerifier.a
    public final void a(String str, ft1 ft1Var, boolean z, Boolean bool) {
        if (z) {
            c(Uri.parse("android-app://" + ft1Var.a.getHost() + "/" + str));
        }
    }

    public final void b() {
        MessagePort[] messagePortArr = this.d;
        if (messagePortArr == null) {
            return;
        }
        messagePortArr[0].close();
        this.d = null;
        this.c = null;
        Context context = xz.a;
        a32 a32Var = (a32) this.b;
        if (a32Var.g != null) {
            context.unbindService(a32Var);
            a32Var.g = null;
        }
    }

    public final void c(Uri uri) {
        this.e = uri;
        WebContents webContents = this.c;
        if (webContents == null || webContents.isDestroyed()) {
            return;
        }
        d(this.c);
    }

    public final void d(WebContents webContents) {
        MessagePort[] messagePortArrH0 = webContents.h0();
        this.d = messagePortArrH0;
        messagePortArrH0[0].b(this.a);
        webContents.c0(new MessagePayload(""), this.e.toString(), new MessagePort[]{this.d[1]});
        a32 a32Var = (a32) this.b;
        a32Var.i = true;
        if (a32Var.g == null) {
            return;
        }
        synchronized (a32Var.e) {
            try {
                try {
                    a32Var.g.onMessageChannelReady(a32Var.f, null);
                } catch (RemoteException unused) {
                }
            } finally {
            }
        }
    }
}
