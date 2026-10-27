package p;

import android.text.TextUtils;
import com.spotify.automotive.auth.LoginException;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.LoginCredentials;
import com.spotify.automotive.baseui.login.AAMLoginPasswordActivity;
import com.spotify.automotive.ubi.LoginUbiLogger$Page;
import com.spotify.automotive.ubi.LoginUbiLogger$Target;
import com.spotify.base.java.logging.Logger;
import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class kd1 {
    public final ld1 a;
    public final io.reactivex.rxjava3.disposables.a b = new io.reactivex.rxjava3.disposables.a();
    public final ad1 c;
    public final k0 d;
    public final y3 e;
    public final p01 f;

    public kd1(AAMLoginPasswordActivity aAMLoginPasswordActivity, ad1 ad1Var, l0 l0Var, y3 y3Var, p01 p01Var) {
        this.a = aAMLoginPasswordActivity;
        this.c = ad1Var;
        this.d = l0Var;
        this.e = y3Var;
        this.f = p01Var;
    }

    public final void a() {
        Observable observableError;
        this.f.w(LoginUbiLogger$Page.PASSWORD, LoginUbiLogger$Target.n);
        AAMLoginPasswordActivity aAMLoginPasswordActivity = (AAMLoginPasswordActivity) this.a;
        aAMLoginPasswordActivity.J.setVisibility(8);
        aAMLoginPasswordActivity.J.setText("");
        int i = 0;
        aAMLoginPasswordActivity.K.setVisibility(0);
        aAMLoginPasswordActivity.O.setVisibility(8);
        String strTrim = aAMLoginPasswordActivity.N.getText().toString().trim();
        String string = aAMLoginPasswordActivity.M.getText().toString();
        com.spotify.automotive.auth.b bVar = (com.spotify.automotive.auth.b) this.c;
        bVar.getClass();
        Logger.e("Attempting to log in using username and password.", new Object[0]);
        int i2 = 1;
        if (TextUtils.isEmpty(strTrim) || TextUtils.isEmpty(string)) {
            Logger.b("Username and/or password are null or empty.", new Object[0]);
            bVar.d(LoginState.k);
            observableError = Observable.error(new LoginException(0));
        } else {
            c7 c7VarA = ((b7) bVar.g).a("auth-password_login");
            LoginCredentials.f.getClass();
            v03.j(strTrim, "username");
            v03.j(string, "password");
            LoginCredentials loginCredentials = new LoginCredentials();
            loginCredentials.a = "password";
            loginCredentials.b = strTrim;
            loginCredentials.d = string;
            observableError = bVar.c(loginCredentials, ((sd1) bVar.d).c == LoginState.e, c7VarA);
        }
        this.b.c(observableError.filter(new lj(11)).observeOn(io.reactivex.rxjava3.android.schedulers.c.a()).subscribe(new jd1(this, i), new jd1(this, i2)));
    }
}
