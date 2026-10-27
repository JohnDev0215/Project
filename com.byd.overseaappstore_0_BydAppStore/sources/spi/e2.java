package spi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import spi.ag;
import spi.im;
import spi.ke;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public final class e2 implements ag {
    public final q4 a;

    public e2(@NotNull q4 cookieJar) {
        Intrinsics.checkNotNullParameter(cookieJar, "cookieJar");
        this.a = cookieJar;
    }

    @Override // spi.ag
    @NotNull
    public final ln a(@NotNull ag.a chain) throws IOException {
        boolean z;
        mn mnVar;
        Intrinsics.checkNotNullParameter(chain, "chain");
        bm bmVar = (bm) chain;
        im request = bmVar.f;
        Objects.requireNonNull(request);
        im.a aVar = new im.a(request);
        mm mmVar = request.e;
        if (mmVar != null) {
            mi miVarContentType = mmVar.contentType();
            if (miVarContentType != null) {
                aVar.b("Content-Type", miVarContentType.a);
            }
            long jContentLength = mmVar.contentLength();
            if (jContentLength != -1) {
                aVar.b("Content-Length", String.valueOf(jContentLength));
                aVar.e("Transfer-Encoding");
            } else {
                aVar.b("Transfer-Encoding", "chunked");
                aVar.e("Content-Length");
            }
        }
        int i = 0;
        if (request.b("Host") == null) {
            aVar.b("Host", uu.w(request.b, false));
        }
        if (request.b("Connection") == null) {
            aVar.b("Connection", "Keep-Alive");
        }
        if (request.b("Accept-Encoding") == null && request.b("Range") == null) {
            aVar.b("Accept-Encoding", "gzip");
            z = true;
        } else {
            z = false;
        }
        List<p4> listB = this.a.b(request.b);
        if (true ^ listB.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Object obj : listB) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                p4 p4Var = (p4) obj;
                if (i > 0) {
                    sb.append("; ");
                }
                sb.append(p4Var.a);
                sb.append('=');
                sb.append(p4Var.b);
                i = i2;
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
            aVar.b("Cookie", string);
        }
        if (request.b("User-Agent") == null) {
            aVar.b("User-Agent", "okhttp/4.9.3");
        }
        ln lnVarB = bmVar.b(aVar.a());
        ff.d(this.a, request.b, lnVarB.g);
        ln.a aVar2 = new ln.a(lnVarB);
        Intrinsics.checkNotNullParameter(request, "request");
        aVar2.a = request;
        if (z && StringsKt.h("gzip", ln.B(lnVarB, "Content-Encoding")) && ff.a(lnVarB) && (mnVar = lnVarB.h) != null) {
            de deVar = new de(mnVar.source());
            ke.a aVarC = lnVarB.g.c();
            aVarC.f("Content-Encoding");
            aVarC.f("Content-Length");
            aVar2.d(aVarC.d());
            aVar2.g = new cm(ln.B(lnVarB, "Content-Type"), -1L, b.c(deVar));
        }
        return aVar2.a();
    }
}
