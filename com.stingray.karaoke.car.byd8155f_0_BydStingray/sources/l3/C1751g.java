package l3;

import android.content.Context;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import h2.AbstractC1346c;
import io.sentry.C1489n1;
import java.lang.ref.WeakReference;
import k3.C1650j0;
import k3.C1672v;
import k3.E0;
import k3.RunnableC1637e0;

/* JADX INFO: renamed from: l3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1751g extends MediaBrowser.ConnectionCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ h3.y f18569a;

    public C1751g(h3.y yVar) {
        this.f18569a = yVar;
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnected() {
        InterfaceC1750f interfaceC1750f;
        h3.y yVar = this.f18569a;
        C1752h c1752h = (C1752h) yVar.f15500n;
        int i7 = 0;
        if (c1752h != null) {
            MediaBrowser mediaBrowser = c1752h.f18571b;
            try {
                Bundle extras = mediaBrowser.getExtras();
                if (extras != null) {
                    extras.getInt("extra_service_version", 0);
                    IBinder binder = extras.getBinder("extra_messenger");
                    if (binder != null) {
                        C1489n1 c1489n1 = new C1489n1(29, false);
                        c1489n1.f16624m = new Messenger(binder);
                        c1489n1.f16625n = c1752h.c;
                        c1752h.f18573f = c1489n1;
                        E0 e02 = c1752h.d;
                        Messenger messenger = new Messenger(e02);
                        c1752h.f18574g = messenger;
                        e02.getClass();
                        e02.c = new WeakReference(messenger);
                        try {
                            Context context = c1752h.f18570a;
                            Bundle bundle = new Bundle();
                            bundle.putString("data_package_name", context.getPackageName());
                            bundle.putInt("data_calling_pid", Process.myPid());
                            bundle.putBundle("data_root_hints", (Bundle) c1489n1.f16625n);
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 6;
                            messageObtain.arg1 = 1;
                            messageObtain.setData(bundle);
                            messageObtain.replyTo = messenger;
                            ((Messenger) c1489n1.f16624m).send(messageObtain);
                        } catch (RemoteException unused) {
                            Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
                        }
                    }
                    IBinder binder2 = extras.getBinder("extra_session_binder");
                    int i10 = J.d;
                    if (binder2 == null) {
                        interfaceC1750f = null;
                    } else {
                        IInterface iInterfaceQueryLocalInterface = binder2.queryLocalInterface("android.support.v4.media.session.IMediaSession");
                        if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof InterfaceC1750f)) {
                            C1749e c1749e = new C1749e();
                            c1749e.c = binder2;
                            interfaceC1750f = c1749e;
                        } else {
                            interfaceC1750f = (InterfaceC1750f) iInterfaceQueryLocalInterface;
                        }
                    }
                    if (interfaceC1750f != null) {
                        MediaSession.Token sessionToken = mediaBrowser.getSessionToken();
                        AbstractC1346c.j(sessionToken != null);
                        if (sessionToken == null) {
                            throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
                        }
                        c1752h.f18575h = new O(sessionToken, interfaceC1750f);
                    }
                }
            } catch (IllegalStateException e10) {
                h6.n.i("MediaBrowserCompat", "Unexpected IllegalStateException", e10);
            }
        }
        C1650j0 c1650j0 = (C1650j0) yVar.o;
        C1754j c1754j = c1650j0.f18005i;
        if (c1754j != null) {
            C1752h c1752h2 = c1754j.f18578a;
            if (c1752h2.f18575h == null) {
                MediaSession.Token sessionToken2 = c1752h2.f18571b.getSessionToken();
                AbstractC1346c.j(sessionToken2 != null);
                if (sessionToken2 == null) {
                    throw new IllegalArgumentException("token is not a valid MediaSession.Token object");
                }
                c1752h2.f18575h = new O(sessionToken2, null);
            }
            A2.m mVar = new A2.m(c1650j0, 20, c1752h2.f18575h);
            C1672v c1672v = c1650j0.f18000b;
            c1672v.X0(mVar);
            c1672v.f18137e.post(new RunnableC1637e0(c1650j0, i7));
        }
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionFailed() {
        h3.y yVar = this.f18569a;
        Object obj = yVar.f15500n;
        ((C1650j0) yVar.o).f18000b.a();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionSuspended() {
        h3.y yVar = this.f18569a;
        C1752h c1752h = (C1752h) yVar.f15500n;
        if (c1752h != null) {
            c1752h.f18573f = null;
            c1752h.f18574g = null;
            c1752h.f18575h = null;
            E0 e02 = c1752h.d;
            e02.getClass();
            e02.c = new WeakReference(null);
        }
        ((C1650j0) yVar.o).f18000b.a();
    }
}
