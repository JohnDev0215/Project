package org.chromium.content.browser;

import J.N;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import defpackage.a32;
import defpackage.b80;
import defpackage.bq;
import defpackage.es3;
import defpackage.ke0;
import defpackage.m91;
import defpackage.oj2;
import defpackage.rh3;
import defpackage.sk;
import defpackage.ve3;
import defpackage.w22;
import defpackage.z22;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.content_public.browser.MessagePayload;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class AppWebMessagePort implements MessagePort {
    public long a;
    public a b;
    public volatile boolean c;
    public volatile boolean d;
    public volatile boolean e;

    public static class a extends Handler {
        public final MessagePort.a a;

        public a(MessagePort.a aVar, Handler handler) {
            super(handler == null ? Looper.getMainLooper() : handler.getLooper());
            this.a = aVar;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Bundle bundle;
            GURL gurlG;
            if (message.what != 1) {
                throw new IllegalStateException("undefined message");
            }
            Pair pair = (Pair) message.obj;
            MessagePort.a aVar = this.a;
            MessagePayload messagePayload = (MessagePayload) pair.first;
            z22 z22Var = (z22) ((ke0) aVar).f;
            if (z22Var.d[0].c()) {
                Log.e("cr_PostMessageHandler", "Discarding postMessage as channel has been transferred.");
                return;
            }
            sk skVar = bq.a;
            if (!N.M09VlOh_("CCTPostMessageOrigin") || (gurlG = z22Var.c.y().g()) == null) {
                bundle = null;
            } else {
                String strMpCt7siL = N.MpCt7siL(gurlG.j());
                bundle = new Bundle();
                bundle.putString("androidx.browser.customtabs.POST_MESSAGE_ORIGIN", strMpCt7siL);
            }
            w22 w22Var = z22Var.b;
            messagePayload.a(0);
            String str = messagePayload.b;
            a32 a32Var = (a32) w22Var;
            if (a32Var.g != null) {
                synchronized (a32Var.e) {
                    try {
                        try {
                            a32Var.g.onPostMessage(a32Var.f, str, bundle);
                        } catch (RemoteException unused) {
                        }
                    } finally {
                    }
                }
            }
            oj2.O("CustomTabs.PostMessage.OnMessage", true);
        }
    }

    @CalledByNative
    public AppWebMessagePort(long j) {
        this.a = j;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final void a(MessagePayload messagePayload) {
        if (this.c || this.d) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        this.e = true;
        PostTask.d(rh3.a, new b80(this, 8, messagePayload, null));
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final void b(ke0 ke0Var) {
        if (this.c || this.d) {
            throw new IllegalStateException("Port is already closed or transferred");
        }
        this.e = true;
        PostTask.d(rh3.a, new m91(this, 11, ke0Var, null));
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final boolean c() {
        return this.d;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final void close() {
        if (this.d) {
            throw new IllegalStateException("Port is already transferred");
        }
        if (this.c) {
            return;
        }
        this.c = true;
        PostTask.d(rh3.a, new es3(25, this));
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final boolean d() {
        return this.e;
    }

    public final void finalize() throws Throwable {
        try {
            if (this.a == 0) {
                return;
            }
            PostTask.c(rh3.a, new ve3(27, this));
        } finally {
            super.finalize();
        }
    }

    @CalledByNative
    public final long getNativeObj() {
        Object obj = ThreadUtils.a;
        return this.a;
    }

    @Override // org.chromium.content_public.browser.MessagePort
    public final boolean isClosed() {
        return this.c;
    }

    @CalledByNative
    public final void nativeDestroyed() {
        Object obj = ThreadUtils.a;
        this.a = 0L;
    }

    @CalledByNative
    public final void onMessage(MessagePayload messagePayload, MessagePort[] messagePortArr) {
        Object obj = ThreadUtils.a;
        a aVar = this.b;
        if (aVar != null) {
            aVar.getClass();
            aVar.sendMessage(aVar.obtainMessage(1, Pair.create(messagePayload, messagePortArr)));
        } else if (messagePortArr != null) {
            for (MessagePort messagePort : messagePortArr) {
                messagePort.close();
            }
        }
    }

    @CalledByNative
    public final void setTransferred() {
        this.d = true;
    }
}
