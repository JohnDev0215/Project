package p;

import android.content.Context;
import android.content.Intent;
import android.media.browse.MediaBrowser;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import android.view.KeyEvent;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class tf1 extends MediaBrowser.ConnectionCallback {
    public final /* synthetic */ gu0 a;

    public tf1(gu0 gu0Var) {
        this.a = gu0Var;
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnected() {
        gu0 gu0Var = this.a;
        uf1 uf1Var = (uf1) gu0Var.b;
        if (uf1Var != null) {
            MediaBrowser mediaBrowser = uf1Var.b;
            try {
                Bundle extras = mediaBrowser.getExtras();
                if (extras != null) {
                    extras.getInt("extra_service_version", 0);
                    IBinder iBinderA = uo.a(extras, "extra_messenger");
                    if (iBinderA != null) {
                        uf1Var.f = new g90(iBinderA, uf1Var.c);
                        sf1 sf1Var = uf1Var.d;
                        Messenger messenger = new Messenger(sf1Var);
                        uf1Var.g = messenger;
                        sf1Var.getClass();
                        sf1Var.b = new WeakReference(messenger);
                        try {
                            g90 g90Var = uf1Var.f;
                            Context context = uf1Var.a;
                            Messenger messenger2 = uf1Var.g;
                            g90Var.getClass();
                            Bundle bundle = new Bundle();
                            bundle.putString("data_package_name", context.getPackageName());
                            bundle.putInt("data_calling_pid", Process.myPid());
                            bundle.putBundle("data_root_hints", (Bundle) g90Var.k);
                            Message messageObtain = Message.obtain();
                            messageObtain.what = 6;
                            messageObtain.arg1 = 1;
                            messageObtain.setData(bundle);
                            messageObtain.replyTo = messenger2;
                            ((Messenger) g90Var.e).send(messageObtain);
                        } catch (RemoteException unused) {
                        }
                    }
                    android.support.v4.media.session.b bVarG0 = android.support.v4.media.session.a.g0(uo.a(extras, "extra_session_binder"));
                    if (bVarG0 != null) {
                        MediaSession.Token sessionToken = mediaBrowser.getSessionToken();
                        uf1Var.h = sessionToken != null ? new MediaSessionCompat$Token(sessionToken, bVarG0) : null;
                    }
                }
            } catch (IllegalStateException e) {
                Log.e("MediaBrowserCompat", "Unexpected IllegalStateException", e);
            }
        }
        Context context2 = (Context) gu0Var.c;
        wf1 wf1Var = ((cg1) gu0Var.f).a;
        if (wf1Var.h == null) {
            MediaSession.Token sessionToken2 = wf1Var.b.getSessionToken();
            wf1Var.h = sessionToken2 != null ? new MediaSessionCompat$Token(sessionToken2, null) : null;
        }
        kk0 kk0Var = new kk0(context2, wf1Var.h);
        KeyEvent keyEvent = (KeyEvent) ((Intent) gu0Var.d).getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        ((eh1) kk0Var.e).p(keyEvent);
        gu0Var.h();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionFailed() {
        gu0 gu0Var = this.a;
        Object obj = gu0Var.b;
        if (((uf1) obj) != null) {
            ((uf1) obj).getClass();
        }
        gu0Var.h();
    }

    @Override // android.media.browse.MediaBrowser.ConnectionCallback
    public final void onConnectionSuspended() {
        gu0 gu0Var = this.a;
        uf1 uf1Var = (uf1) gu0Var.b;
        if (uf1Var != null) {
            uf1Var.f = null;
            uf1Var.g = null;
            uf1Var.h = null;
            sf1 sf1Var = uf1Var.d;
            sf1Var.getClass();
            sf1Var.b = new WeakReference(null);
        }
        gu0Var.h();
    }
}
