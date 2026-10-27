package f.k0.i;

import f.e0;
import f.x;
import java.net.Proxy;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static String a(e0 e0Var, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(e0Var.f());
        sb.append(' ');
        if (b(e0Var, type)) {
            sb.append(e0Var.i());
        } else {
            sb.append(c(e0Var.i()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    private static boolean b(e0 e0Var, Proxy.Type type) {
        return !e0Var.e() && type == Proxy.Type.HTTP;
    }

    public static String c(x xVar) {
        String strH = xVar.h();
        String strJ = xVar.j();
        if (strJ == null) {
            return strH;
        }
        return strH + '?' + strJ;
    }
}
