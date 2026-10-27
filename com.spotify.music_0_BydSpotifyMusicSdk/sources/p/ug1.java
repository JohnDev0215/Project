package p;

import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$Token;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ug1 implements Runnable {
    public final /* synthetic */ int c;
    public final /* synthetic */ MediaSessionCompat$Token e;
    public final /* synthetic */ kg1 k;

    public /* synthetic */ ug1(ng1 ng1Var, MediaSessionCompat$Token mediaSessionCompat$Token, int i) {
        this.c = i;
        this.k = ng1Var;
        this.e = mediaSessionCompat$Token;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        MediaSessionCompat$Token mediaSessionCompat$Token = this.e;
        kg1 kg1Var = this.k;
        switch (i) {
            case 0:
                Iterator it = ((mb) ((bh1) ((hg1) kg1Var).b).m.values()).iterator();
                while (true) {
                    g41 g41Var = (g41) it;
                    if (!g41Var.hasNext()) {
                        break;
                    } else {
                        jg1 jg1Var = (jg1) g41Var.next();
                        try {
                            wg1 wg1Var = jg1Var.d;
                            hg1 hg1Var = jg1Var.f;
                            wg1Var.c((String) hg1Var.a, mediaSessionCompat$Token, (Bundle) hg1Var.b);
                        } catch (RemoteException unused) {
                            String str = jg1Var.a;
                            g41Var.remove();
                        }
                    }
                }
                break;
            default:
                ng1 ng1Var = (ng1) kg1Var;
                ArrayList arrayList = ng1Var.a;
                if (!arrayList.isEmpty()) {
                    android.support.v4.media.session.b bVarA = mediaSessionCompat$Token.a();
                    if (bVarA != null) {
                        Iterator it2 = arrayList.iterator();
                        while (it2.hasNext()) {
                            uo.b((Bundle) it2.next(), "extra_session_binder", bVarA.asBinder());
                        }
                    }
                    arrayList.clear();
                }
                ng1Var.b.setSessionToken((MediaSession.Token) mediaSessionCompat$Token.e);
                break;
        }
    }
}
