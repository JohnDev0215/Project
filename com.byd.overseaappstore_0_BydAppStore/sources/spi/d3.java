package spi;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import spi.ag;
import spi.ln;
import spi.y8;

/* JADX INFO: loaded from: classes2.dex */
public final class d3 implements ag {
    public final boolean a;

    public d3(boolean z) {
        this.a = z;
    }

    @Override // spi.ag
    @NotNull
    public final ln a(@NotNull ag.a chain) throws Throwable {
        ln.a aVarC;
        ln lnVarA;
        boolean z;
        Intrinsics.checkNotNullParameter(chain, "chain");
        bm bmVar = (bm) chain;
        y8 y8Var = bmVar.e;
        Intrinsics.checkNotNull(y8Var);
        im request = bmVar.f;
        mm mmVar = request.e;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Objects.requireNonNull(y8Var);
        Intrinsics.checkNotNullParameter(request, "request");
        try {
            u8 u8Var = y8Var.d;
            vl call = y8Var.c;
            Objects.requireNonNull(u8Var);
            Intrinsics.checkNotNullParameter(call, "call");
            y8Var.f.h(request);
            u8 u8Var2 = y8Var.d;
            vl call2 = y8Var.c;
            Objects.requireNonNull(u8Var2);
            Intrinsics.checkNotNullParameter(call2, "call");
            Intrinsics.checkNotNullParameter(request, "request");
            boolean z2 = true;
            if (!gf.b(request.c) || mmVar == null) {
                y8Var.c.h(y8Var, true, false, null);
                aVarC = null;
            } else {
                if (StringsKt.h("100-continue", request.b("Expect"))) {
                    try {
                        y8Var.f.e();
                        aVarC = y8Var.c(true);
                        y8Var.d();
                        z = false;
                    } catch (IOException e) {
                        y8Var.d.b(y8Var.c, e);
                        y8Var.e(e);
                        throw e;
                    }
                } else {
                    aVarC = null;
                    z = true;
                }
                if (aVarC != null) {
                    y8Var.c.h(y8Var, true, false, null);
                    if (!y8Var.b.j()) {
                        y8Var.f.d().l();
                    }
                } else if (mmVar.isDuplex()) {
                    try {
                        y8Var.f.e();
                        mmVar.writeTo(b.b(y8Var.b(request, true)));
                    } catch (IOException e2) {
                        y8Var.d.b(y8Var.c, e2);
                        y8Var.e(e2);
                        throw e2;
                    }
                } else {
                    k2 k2VarB = b.b(y8Var.b(request, false));
                    mmVar.writeTo(k2VarB);
                    ((tl) k2VarB).close();
                }
                z2 = z;
            }
            if (mmVar == null || !mmVar.isDuplex()) {
                try {
                    y8Var.f.a();
                } catch (IOException e3) {
                    y8Var.d.b(y8Var.c, e3);
                    y8Var.e(e3);
                    throw e3;
                }
            }
            if (aVarC == null) {
                aVarC = y8Var.c(false);
                Intrinsics.checkNotNull(aVarC);
                if (z2) {
                    y8Var.d();
                    z2 = false;
                }
            }
            Objects.requireNonNull(aVarC);
            Intrinsics.checkNotNullParameter(request, "request");
            aVarC.a = request;
            aVarC.e = y8Var.b.d;
            aVarC.k = jCurrentTimeMillis;
            aVarC.l = System.currentTimeMillis();
            ln response = aVarC.a();
            int i = response.e;
            if (i == 100) {
                ln.a aVarC2 = y8Var.c(false);
                Intrinsics.checkNotNull(aVarC2);
                if (z2) {
                    y8Var.d();
                }
                Objects.requireNonNull(aVarC2);
                Intrinsics.checkNotNullParameter(request, "request");
                aVarC2.a = request;
                aVarC2.e = y8Var.b.d;
                aVarC2.k = jCurrentTimeMillis;
                aVarC2.l = System.currentTimeMillis();
                response = aVarC2.a();
                i = response.e;
            }
            Intrinsics.checkNotNullParameter(response, "response");
            u8 u8Var3 = y8Var.d;
            vl call3 = y8Var.c;
            Objects.requireNonNull(u8Var3);
            Intrinsics.checkNotNullParameter(call3, "call");
            Intrinsics.checkNotNullParameter(response, "response");
            if (this.a && i == 101) {
                ln.a aVar = new ln.a(response);
                aVar.g = uu.c;
                lnVarA = aVar.a();
            } else {
                ln.a aVar2 = new ln.a(response);
                Intrinsics.checkNotNullParameter(response, "response");
                try {
                    String strB = ln.B(response, "Content-Type");
                    long jG = y8Var.f.g(response);
                    aVar2.g = new cm(strB, jG, b.c(new y8.b(y8Var, y8Var.f.b(response), jG)));
                    lnVarA = aVar2.a();
                } catch (IOException e4) {
                    y8Var.d.c(y8Var.c, e4);
                    y8Var.e(e4);
                    throw e4;
                }
            }
            if (StringsKt.h("close", lnVarA.b.b("Connection")) || StringsKt.h("close", ln.B(lnVarA, "Connection"))) {
                y8Var.f.d().l();
            }
            if (i == 204 || i == 205) {
                mn mnVar = lnVarA.h;
                if ((mnVar != null ? mnVar.contentLength() : -1L) > 0) {
                    StringBuilder sbJ = d.j("HTTP ", i, " had non-zero Content-Length: ");
                    mn mnVar2 = lnVarA.h;
                    sbJ.append(mnVar2 != null ? Long.valueOf(mnVar2.contentLength()) : null);
                    throw new ProtocolException(sbJ.toString());
                }
            }
            return lnVarA;
        } catch (IOException e5) {
            y8Var.d.b(y8Var.c, e5);
            y8Var.e(e5);
            throw e5;
        }
    }
}
