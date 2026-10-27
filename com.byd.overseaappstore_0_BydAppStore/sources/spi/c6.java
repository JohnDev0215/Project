package spi;

import java.util.List;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class c6 implements up {
    public up a;
    public final a b;

    public interface a {
        boolean a(@NotNull SSLSocket sSLSocket);

        @NotNull
        up b(@NotNull SSLSocket sSLSocket);
    }

    public c6(@NotNull a socketAdapterFactory) {
        Intrinsics.checkNotNullParameter(socketAdapterFactory, "socketAdapterFactory");
        this.b = socketAdapterFactory;
    }

    @Override // spi.up
    public final boolean a(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return this.b.a(sslSocket);
    }

    @Override // spi.up
    @Nullable
    public final String b(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        up upVarD = d(sslSocket);
        if (upVarD != null) {
            return upVarD.b(sslSocket);
        }
        return null;
    }

    @Override // spi.up
    public final void c(@NotNull SSLSocket sslSocket, @Nullable String str, @NotNull List<? extends Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        up upVarD = d(sslSocket);
        if (upVarD != null) {
            upVarD.c(sslSocket, str, protocols);
        }
    }

    public final synchronized up d(SSLSocket sSLSocket) {
        if (this.a == null && this.b.a(sSLSocket)) {
            this.a = this.b.b(sSLSocket);
        }
        return this.a;
    }

    @Override // spi.up
    public final boolean isSupported() {
        return true;
    }
}
