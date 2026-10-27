package defpackage;

import J.N;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import java.io.IOException;
import java.util.Objects;
import org.chromium.net.HttpNegotiateAuthenticator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CK implements AccountManagerCallback {
    public final DK a;
    public final /* synthetic */ HttpNegotiateAuthenticator b;

    public CK(HttpNegotiateAuthenticator httpNegotiateAuthenticator, DK dk) {
        this.b = httpNegotiateAuthenticator;
        this.a = dk;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture accountManagerFuture) {
        int i = -9;
        try {
            Bundle bundle = (Bundle) accountManagerFuture.getResult();
            if (bundle.containsKey("intent")) {
                Context context = Is.a;
                context.registerReceiver(new BK(this, context), new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
                return;
            }
            HttpNegotiateAuthenticator httpNegotiateAuthenticator = this.b;
            DK dk = this.a;
            Objects.requireNonNull(httpNegotiateAuthenticator);
            httpNegotiateAuthenticator.a = bundle.getBundle("spnegoContext");
            int i2 = bundle.getInt("spnegoResult", 1);
            if (i2 != 0) {
                switch (i2) {
                    case 2:
                        i = -3;
                        break;
                    case 3:
                        i = -342;
                        break;
                    case C0571bs.IS_GZIP_COMPRESSED_FIELD_NUMBER /* 4 */:
                        i = -320;
                        break;
                    case C0571bs.SEED_DATA_FIELD_NUMBER /* 5 */:
                        i = -338;
                        break;
                    case C0571bs.DATE_FIELD_NUMBER /* 6 */:
                        i = -339;
                        break;
                    case 7:
                        i = -341;
                        break;
                    case 8:
                        i = -344;
                        break;
                    case 9:
                        i = -329;
                        break;
                }
            } else {
                i = 0;
            }
            N.M0s8NeYn(dk.a, httpNegotiateAuthenticator, i, bundle.getString("authtoken"));
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            Ts.f("net_auth", "ERR_UNEXPECTED: Error while attempting to obtain a token.", e);
            N.M0s8NeYn(this.a.a, this.b, -9, null);
        }
    }
}
