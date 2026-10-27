package p;

import com.google.common.base.Optional;
import com.spotify.automotive.httpwebgategson.TokenResponse;
import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.httpwebgate.WebgateTokenProvider;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class u00 implements WebgateTokenProvider {
    public final x42 a;
    public final t00 b;

    public u00(t00 t00Var) {
        this.a = new x42(t00Var);
        this.b = t00Var;
    }

    public static String a(Optional optional) throws WebgateTokenProvider.WebgateTokenException {
        if (optional.d()) {
            TokenResponse tokenResponse = (TokenResponse) optional.c();
            int i = tokenResponse.f;
            if (i > 0) {
                String str = String.format(Locale.ENGLISH, "Token endpoint responded with an error: %d, %s", Integer.valueOf(i), tokenResponse.g);
                Logger.b(str, new Object[0]);
                throw new WebgateTokenProvider.WebgateTokenException(str);
            }
            String str2 = tokenResponse.b;
            if (str2 != null) {
                return str2;
            }
        }
        throw new WebgateTokenProvider.WebgateTokenException("Token response not present.");
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final void onSessionStart() {
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final void onStart() {
        this.b.d.set(false);
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final void onStop() {
        t00 t00Var = this.b;
        t00Var.d.set(true);
        t00Var.a.onNext(mq2.a);
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final String requestAccessToken(int i) {
        Optional optionalA;
        x42 x42Var = this.a;
        synchronized (x42Var) {
            if (!((Optional) x42Var.k).d() || ((TokenResponse) ((Optional) x42Var.k).c()).b == null) {
                optionalA = ((t00) x42Var.e).a(i, false);
                x42Var.k = optionalA;
            } else {
                optionalA = (Optional) x42Var.k;
            }
        }
        return a(optionalA);
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final void reset() {
        if (((Optional) this.a.k).d()) {
            t00 t00Var = this.b;
            t00Var.a.onNext(mq2.a);
            x42 x42Var = this.a;
            synchronized (x42Var) {
                x42Var.k = Optional.a();
            }
        }
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final void resetAndStopServingTokens() {
        reset();
    }

    @Override // com.spotify.connectivity.httpwebgate.WebgateTokenProvider
    public final String requestAccessToken(int i, boolean z) {
        Optional optionalA;
        x42 x42Var = this.a;
        synchronized (x42Var) {
            optionalA = ((t00) x42Var.e).a(i, true);
            x42Var.k = optionalA;
        }
        return a(optionalA);
    }
}
