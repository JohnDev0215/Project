package p;

import com.spotify.automotive.auth.LoginException;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nd1 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ pd1 e;

    public /* synthetic */ nd1(pd1 pd1Var, int i) {
        this.c = i;
        this.e = pd1Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        LoginException.LoginError loginErrorA;
        int i = this.c;
        pd1 pd1Var = this.e;
        switch (i) {
            case 0:
                u4.z(obj);
                pd1Var.getClass();
                throw null;
            case 1:
                ((l0) pd1Var.d).getClass();
                pd1Var.a.finish();
                return;
            default:
                Throwable th = (Throwable) obj;
                pd1Var.getClass();
                if (th instanceof LoginException) {
                    loginErrorA = ((LoginException) th).a();
                } else {
                    Logger.c(th, "Unexpected exception while attempting to log in.", new Object[0]);
                    loginErrorA = LoginException.LoginError.UNKNOWN;
                }
                pd1Var.g.v(pd1Var.h, js2.a(loginErrorA));
                ((com.spotify.pinpairing.pinpairingimpl.c) pd1Var.e).d.d();
                md1 md1Var = pd1Var.a;
                md1Var.c(false, false);
                md1Var.e(loginErrorA.a(), true);
                return;
        }
    }
}
