package p;

import com.google.common.base.Optional;
import com.spotify.automotive.auth.LoginException;
import com.spotify.automotive.auth.LoginState;
import com.spotify.automotive.auth.model.CoreNativeResponseException;
import com.spotify.automotive.auth.model.LoginResponse;
import com.spotify.base.java.logging.Logger;
import com.spotify.cosmos.cosmonaut.TypedResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class gd1 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ int c;
    public final /* synthetic */ com.spotify.automotive.auth.b e;
    public final /* synthetic */ wk2 k;

    public /* synthetic */ gd1(com.spotify.automotive.auth.b bVar, c7 c7Var, int i) {
        this.c = i;
        this.e = bVar;
        this.k = c7Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        int i = this.c;
        wk2 wk2Var = this.k;
        com.spotify.automotive.auth.b bVar = this.e;
        switch (i) {
            case 0:
                bVar.getClass();
                Logger.e("Successfully logged in automatically.", new Object[0]);
                ((c7) wk2Var).a("result", "success");
                LoginResponse loginResponse = (LoginResponse) ((TypedResponse) obj).getBody();
                if (loginResponse != null) {
                    ((sd1) bVar.d).b.onNext(loginResponse);
                }
                return io.reactivex.rxjava3.internal.operators.completable.d.c;
            case 1:
                TypedResponse typedResponse = (TypedResponse) obj;
                bVar.getClass();
                Logger.e("Successfully logged in with credentials.", new Object[0]);
                ((c7) wk2Var).c("bootstrapping");
                LoginResponse loginResponse2 = (LoginResponse) typedResponse.getBody();
                loginResponse2.getClass();
                j jVar = bVar.f;
                Observable map = jVar.a.b.d().subscribeOn(io.reactivex.rxjava3.schedulers.e.c).toObservable().map(new ed1(5)).onErrorReturnItem(Optional.a()).map(new dd1(5, loginResponse2));
                ef2 ef2Var = jVar.b;
                Objects.requireNonNull(ef2Var);
                jVar.c = map.subscribe(new com.spotify.automotive.auth.a(6, ef2Var), new ed1(5));
                LoginResponse loginResponse3 = (LoginResponse) typedResponse.getBody();
                loginResponse3.getClass();
                ((sd1) bVar.d).b.onNext(loginResponse3);
                LoginState loginState = LoginState.e;
                bVar.d(loginState);
                return loginState;
            default:
                Throwable th = (Throwable) obj;
                bVar.getClass();
                Logger.c(th, "Failed to log in with credentials.", new Object[0]);
                c7 c7Var = (c7) wk2Var;
                c7Var.a("result", "error");
                c7Var.c("cosmos-service-request");
                bVar.b(th);
                bVar.d(LoginState.k);
                if (!(th instanceof CoreNativeResponseException)) {
                    return Single.error(new LoginException());
                }
                CoreNativeResponseException coreNativeResponseException = (CoreNativeResponseException) th;
                if (coreNativeResponseException.f()) {
                    lc.n("Failed to log in: network error");
                } else if (coreNativeResponseException.e()) {
                    lc.n("Failed to log in: login region mismatch");
                } else if (coreNativeResponseException.c()) {
                    lc.n("Failed to log in: ap network disabled");
                } else if (coreNativeResponseException.d()) {
                    lc.n("Failed to log in: bad credentials");
                }
                return Single.error(new LoginException(coreNativeResponseException));
        }
    }
}
