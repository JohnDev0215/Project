package f.b0.g;

import f.w;
import java.net.Proxy;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: RequestLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {
    public static String a(w wVar, Proxy.Type type) {
        StringBuilder sb = new StringBuilder();
        sb.append(wVar.f());
        sb.append(' ');
        if (b(wVar, type)) {
            sb.append(wVar.h());
        } else {
            sb.append(c(wVar.h()));
        }
        sb.append(" HTTP/1.1");
        return sb.toString();
    }

    public static boolean b(w wVar, Proxy.Type type) {
        return !wVar.e() && type == Proxy.Type.HTTP;
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
