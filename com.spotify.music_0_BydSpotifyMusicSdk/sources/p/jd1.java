package p;

import com.spotify.automotive.auth.LoginException;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.base.java.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jd1 implements io.reactivex.rxjava3.functions.g {
    public final /* synthetic */ int c;
    public final /* synthetic */ kd1 e;

    public /* synthetic */ jd1(kd1 kd1Var, int i) {
        this.c = i;
        this.e = kd1Var;
    }

    @Override // io.reactivex.rxjava3.functions.g
    public final void accept(Object obj) {
        int i = this.c;
        kd1 kd1Var = this.e;
        switch (i) {
            case 0:
                ((l0) kd1Var.d).getClass();
                kd1Var.a.finish();
                return;
            case 1:
                AAMLoginPasswordActivity aAMLoginPasswordActivity = (AAMLoginPasswordActivity) kd1Var.a;
                aAMLoginPasswordActivity.K.setVisibility(8);
                aAMLoginPasswordActivity.O.setVisibility(0);
                LoginException.LoginError loginErrorA = ((LoginException) ((Throwable) obj)).a();
                Logger.b("Login: Failed to login with error code: %s", loginErrorA);
                aAMLoginPasswordActivity.M.setText("");
                int iA = loginErrorA.a();
                aAMLoginPasswordActivity.J.setVisibility(0);
                aAMLoginPasswordActivity.J.setText(iA);
                kd1Var.f.v(LoginUbiLogger$Page.PASSWORD, js2.a(loginErrorA));
                return;
            default:
                u4.z(obj);
                kd1Var.getClass();
                throw null;
        }
    }
}
