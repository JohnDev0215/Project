package spi;

import java.io.IOException;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.connection.RouteException;
import org.jetbrains.annotations.NotNull;
import spi.ag;

/* JADX INFO: loaded from: classes2.dex */
public final class a4 implements ag {
    public static final a4 a = new a4();

    private a4() {
    }

    @Override // spi.ag
    @NotNull
    public final ln a(@NotNull ag.a chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        bm chain2 = (bm) chain;
        vl vlVar = chain2.b;
        Objects.requireNonNull(vlVar);
        Intrinsics.checkNotNullParameter(chain2, "chain");
        synchronized (vlVar) {
            if (!vlVar.l) {
                throw new IllegalStateException("released".toString());
            }
            if (!(!vlVar.k)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (!(!vlVar.j)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            Unit unit = Unit.INSTANCE;
        }
        a9 a9Var = vlVar.f;
        Intrinsics.checkNotNull(a9Var);
        dk client = vlVar.p;
        Objects.requireNonNull(a9Var);
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain2, "chain");
        try {
            y8 y8Var = new y8(vlVar, vlVar.b, a9Var, a9Var.a(chain2.g, chain2.h, chain2.i, client.B, client.f, !Intrinsics.areEqual(chain2.f.c, "GET")).k(client, chain2));
            vlVar.i = y8Var;
            vlVar.n = y8Var;
            synchronized (vlVar) {
                vlVar.j = true;
                vlVar.k = true;
            }
            if (vlVar.m) {
                throw new IOException("Canceled");
            }
            return bm.a(chain2, 0, y8Var, null, 61).b(chain2.f);
        } catch (IOException e) {
            a9Var.c(e);
            throw new RouteException(e);
        } catch (RouteException e2) {
            a9Var.c(e2.getLastConnectException());
            throw e2;
        }
    }
}
