package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import kotlin.collections.b;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface Dns {
    public static final Dns a;

    public static final class Companion {
        public static final /* synthetic */ int a = 0;

        public static final class DnsSystem implements Dns {
            @Override // okhttp3.Dns
            public final List a(String str) throws UnknownHostException {
                v03.j(str, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(str);
                    v03.i(allByName, "getAllByName(hostname)");
                    return b.w0(allByName);
                } catch (NullPointerException e) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
                    unknownHostException.initCause(e);
                    throw unknownHostException;
                }
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
        a = new Companion.DnsSystem();
    }

    List a(String str);
}
