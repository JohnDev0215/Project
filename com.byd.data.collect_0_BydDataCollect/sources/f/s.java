package f;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: Dns.java */
/* JADX INFO: loaded from: classes.dex */
public interface s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f5500a = new s() { // from class: f.c
        @Override // f.s
        public final List b(String str) {
            return s.a(str);
        }
    };

    static /* synthetic */ List a(String str) throws UnknownHostException {
        if (str == null) {
            throw new UnknownHostException("hostname == null");
        }
        try {
            return Arrays.asList(InetAddress.getAllByName(str));
        } catch (NullPointerException e2) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + str);
            unknownHostException.initCause(e2);
            throw unknownHostException;
        }
    }

    List<InetAddress> b(String str);
}
