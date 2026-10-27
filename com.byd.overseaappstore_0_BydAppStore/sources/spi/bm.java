package spi;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ag;

/* JADX INFO: loaded from: classes2.dex */
public final class bm implements ag.a {
    public int a;

    @NotNull
    public final vl b;
    public final List<ag> c;
    public final int d;

    @Nullable
    public final y8 e;

    @NotNull
    public final im f;
    public final int g;
    public final int h;
    public final int i;

    /* JADX WARN: Multi-variable type inference failed */
    public bm(@NotNull vl call, @NotNull List<? extends ag> interceptors, int i, @Nullable y8 y8Var, @NotNull im request, int i2, int i3, int i4) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(interceptors, "interceptors");
        Intrinsics.checkNotNullParameter(request, "request");
        this.b = call;
        this.c = interceptors;
        this.d = i;
        this.e = y8Var;
        this.f = request;
        this.g = i2;
        this.h = i3;
        this.i = i4;
    }

    public static bm a(bm bmVar, int i, y8 y8Var, im imVar, int i2) {
        if ((i2 & 1) != 0) {
            i = bmVar.d;
        }
        int i3 = i;
        if ((i2 & 2) != 0) {
            y8Var = bmVar.e;
        }
        y8 y8Var2 = y8Var;
        if ((i2 & 4) != 0) {
            imVar = bmVar.f;
        }
        im request = imVar;
        int i4 = (i2 & 8) != 0 ? bmVar.g : 0;
        int i5 = (i2 & 16) != 0 ? bmVar.h : 0;
        int i6 = (i2 & 32) != 0 ? bmVar.i : 0;
        Objects.requireNonNull(bmVar);
        Intrinsics.checkNotNullParameter(request, "request");
        return new bm(bmVar.b, bmVar.c, i3, y8Var2, request, i4, i5, i6);
    }

    @NotNull
    public final ln b(@NotNull im request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        if (!(this.d < this.c.size())) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.a++;
        y8 y8Var = this.e;
        if (y8Var != null) {
            if (!y8Var.e.b(request.b)) {
                StringBuilder sbK = e.k("network interceptor ");
                sbK.append(this.c.get(this.d - 1));
                sbK.append(" must retain the same host and port");
                throw new IllegalStateException(sbK.toString().toString());
            }
            if (!(this.a == 1)) {
                StringBuilder sbK2 = e.k("network interceptor ");
                sbK2.append(this.c.get(this.d - 1));
                sbK2.append(" must call proceed() exactly once");
                throw new IllegalStateException(sbK2.toString().toString());
            }
        }
        bm bmVarA = a(this, this.d + 1, null, request, 58);
        ag agVar = this.c.get(this.d);
        ln lnVarA = agVar.a(bmVarA);
        if (lnVarA == null) {
            throw new NullPointerException("interceptor " + agVar + " returned null");
        }
        if (this.e != null) {
            if (!(this.d + 1 >= this.c.size() || bmVarA.a == 1)) {
                throw new IllegalStateException(("network interceptor " + agVar + " must call proceed() exactly once").toString());
            }
        }
        if (lnVarA.h != null) {
            return lnVarA;
        }
        throw new IllegalStateException(("interceptor " + agVar + " returned a response with no body").toString());
    }
}
