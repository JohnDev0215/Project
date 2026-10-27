package e.d0.g;

import e.y;
import java.net.Proxy;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static String a(y yVar, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(yVar.f());
        sb.append(' ');
        if (b(yVar, type)) {
            sb.append(yVar.h());
        } else {
            sb.append(c(yVar.h()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static boolean b(y yVar, Proxy.Type type) {
        return !yVar.e() && type == Proxy.Type.HTTP;
    }

    public static String c(HttpUrl httpUrl) {
        String strH = httpUrl.h();
        String strJ = httpUrl.j();
        if (strJ == null) {
            return strH;
        }
        return strH + '?' + strJ;
    }
}
