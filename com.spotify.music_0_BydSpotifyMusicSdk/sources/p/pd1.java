package p;

import android.os.Handler;
import android.text.TextUtils;
import com.spotify.automotive.auth.LoginException;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.LoginCredentials;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public class pd1 implements ys1 {
    public final md1 a;
    public final Handler b;
    public final ad1 c;
    public final k0 d;
    public final zs1 e;
    public final y3 f;
    public final p01 g;
    public final LoginUbiLogger$Page h;
    public final io.reactivex.rxjava3.disposables.a i = new io.reactivex.rxjava3.disposables.a();

    public pd1(md1 md1Var, Handler handler, ad1 ad1Var, l0 l0Var, com.spotify.pinpairing.pinpairingimpl.c cVar, y3 y3Var, p01 p01Var, LoginUbiLogger$Page loginUbiLogger$Page) {
        this.a = md1Var;
        this.b = handler;
        this.c = ad1Var;
        this.d = l0Var;
        this.e = cVar;
        this.f = y3Var;
        this.g = p01Var;
        this.h = loginUbiLogger$Page;
    }

    public final void a() {
        ((com.spotify.pinpairing.pinpairingimpl.c) this.e).c.add(this);
        this.f.getClass();
        this.i.c(Observable.empty().subscribe(new nd1(this, 0), new ed1(11)));
    }

    public final void b(String str) {
        Observable observableC;
        com.spotify.automotive.auth.b bVar = (com.spotify.automotive.auth.b) this.c;
        bVar.getClass();
        Logger.e("Attempting to log in using refresh token: %s", str);
        int i = 1;
        if (TextUtils.isEmpty(str)) {
            Logger.b("Refresh token is null or empty.", new Object[0]);
            bVar.d(LoginState.k);
            observableC = Observable.error(new LoginException(0));
        } else {
            c7 c7VarA = ((b7) bVar.g).a("auth-pin_login");
            LoginCredentials.f.getClass();
            v03.j(str, "refreshToken");
            LoginCredentials loginCredentials = new LoginCredentials();
            loginCredentials.a = "refreshToken";
            loginCredentials.e = str;
            observableC = bVar.c(loginCredentials, ((sd1) bVar.d).c == LoginState.e, c7VarA);
        }
        this.i.c(observableC.filter(new lj(12)).observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new nd1(this, i), new nd1(this, 2)));
        this.b.post(new od1(this, 1));
    }

    public final void c() {
        this.g.x(this.h);
        com.spotify.pinpairing.pinpairingimpl.c cVar = (com.spotify.pinpairing.pinpairingimpl.c) this.e;
        cVar.d.d();
        io.reactivex.rxjava3.disposables.a aVar = cVar.d;
        if (aVar.isDisposed() || aVar.g() != 0) {
            throw new IllegalStateException("Called start() when flow already started.");
        }
        cVar.c();
        this.a.c(true, true);
    }
}
