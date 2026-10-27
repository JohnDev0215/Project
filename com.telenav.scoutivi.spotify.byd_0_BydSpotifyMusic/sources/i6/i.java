package i6;

import c6.u;
import c6.z;
import java.net.Proxy;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: RequestLine.kt */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final i f7064a = new i();

    private i() {
    }

    private final boolean b(z zVar, Proxy.Type type) {
        return !zVar.f() && type == Proxy.Type.HTTP;
    }

    public final String a(z request, Proxy.Type proxyType) {
        l.e(request, "request");
        l.e(proxyType, "proxyType");
        StringBuilder sb = new StringBuilder();
        sb.append(request.g());
        sb.append(' ');
        i iVar = f7064a;
        if (iVar.b(request, proxyType)) {
            sb.append(request.i());
        } else {
            sb.append(iVar.c(request.i()));
        }
        sb.append(" HTTP/1.1");
        String string = sb.toString();
        l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public final String c(u url) {
        l.e(url, "url");
        String strD = url.d();
        String strF = url.f();
        if (strF == null) {
            return strD;
        }
        return strD + '?' + strF;
    }
}
