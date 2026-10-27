package c6;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX INFO: compiled from: Dns.kt */
/* JADX INFO: loaded from: classes.dex */
public interface q {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f3833b = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q f3832a = new a.C0042a();

    /* JADX INFO: compiled from: Dns.kt */
    public static final class a {

        /* JADX INFO: renamed from: c6.q$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: Dns.kt */
        private static final class C0042a implements q {
            @Override // c6.q
            public List<InetAddress> a(String hostname) throws UnknownHostException {
                kotlin.jvm.internal.l.e(hostname, "hostname");
                try {
                    InetAddress[] allByName = InetAddress.getAllByName(hostname);
                    kotlin.jvm.internal.l.d(allByName, "InetAddress.getAllByName(hostname)");
                    return h5.l.v(allByName);
                } catch (NullPointerException e7) {
                    UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                    unknownHostException.initCause(e7);
                    throw unknownHostException;
                }
            }
        }

        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }
    }

    List<InetAddress> a(String str);
}
