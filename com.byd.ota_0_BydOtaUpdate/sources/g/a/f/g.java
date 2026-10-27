package g.a.f;

import java.math.BigInteger;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ThreadLocal f5726a = new ThreadLocal();

    public static class a implements PrivilegedAction {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5727a;

        public a(String str) {
            this.f5727a = str;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            Map map = (Map) g.f5726a.get();
            return map != null ? map.get(this.f5727a) : System.getProperty(this.f5727a);
        }
    }

    public static BigInteger b(String str) {
        String strC = c(str);
        if (strC != null) {
            return new BigInteger(strC);
        }
        return null;
    }

    public static String c(String str) {
        return (String) AccessController.doPrivileged(new a(str));
    }

    public static boolean d(String str) {
        try {
            String strC = c(str);
            if (strC != null) {
                return "true".equals(i.g(strC));
            }
        } catch (AccessControlException unused) {
        }
        return false;
    }
}
