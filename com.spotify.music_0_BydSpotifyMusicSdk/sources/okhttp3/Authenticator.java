package okhttp3;

import okhttp3.internal.authenticator.JavaNetAuthenticator;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface Authenticator {
    public static final Authenticator a;

    public static final class Companion {
        public static final /* synthetic */ int a = 0;

        public static final class AuthenticatorNone implements Authenticator {
            @Override // okhttp3.Authenticator
            public final Request s(Route route, Response response) {
                v03.j(response, "response");
                return null;
            }
        }

        static {
            new Companion();
        }

        private Companion() {
        }
    }

    static {
        int i = Companion.a;
        a = new Companion.AuthenticatorNone();
        new JavaNetAuthenticator(0);
    }

    Request s(Route route, Response response);
}
