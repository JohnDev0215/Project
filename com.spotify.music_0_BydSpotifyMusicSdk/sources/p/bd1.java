package p;

import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class bd1 implements io.reactivex.rxjava3.functions.a {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.automotive.auth.b e;

    public /* synthetic */ bd1(com.spotify.automotive.auth.b bVar, int i) {
        this.c = i;
        this.e = bVar;
    }

    @Override // io.reactivex.rxjava3.functions.a
    public final void run() {
        LoginState loginState = LoginState.c;
        int i = this.c;
        com.spotify.automotive.auth.b bVar = this.e;
        switch (i) {
            case 0:
                bVar.d(LoginState.e);
                break;
            case 1:
                bVar.d(loginState);
                break;
            case 2:
                if (((sd1) bVar.d).c == loginState) {
                    Logger.e("Log in started but didn't fully complete, will log out.", new Object[0]);
                    bVar.k = bVar.c.logout(true).subscribe(new bd1(bVar, 3), new ed1(1));
                }
                break;
            default:
                LoginState loginState2 = LoginState.k;
                j jVar = bVar.f;
                jVar.c.dispose();
                jVar.b.a();
                ((sd1) bVar.d).b.onNext(new LoginResponse());
                bVar.d(loginState2);
                break;
        }
    }
}
