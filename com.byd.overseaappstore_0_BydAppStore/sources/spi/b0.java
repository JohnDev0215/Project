package spi;

import java.util.Objects;
import javax.net.ssl.SSLSocket;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import spi.c0;
import spi.c6;

/* JADX INFO: loaded from: classes2.dex */
public final class b0 implements c6.a {
    public final /* synthetic */ String a;

    public b0(String str) {
        this.a = str;
    }

    @Override // spi.c6.a
    public final boolean a(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        String name = sslSocket.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "sslSocket.javaClass.name");
        return StringsKt.x(name, this.a + '.');
    }

    @Override // spi.c6.a
    @NotNull
    public final up b(@NotNull SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        c0.a aVar = c0.g;
        Class<?> cls = sslSocket.getClass();
        Objects.requireNonNull(aVar);
        Class<?> superclass = cls;
        while (!Intrinsics.areEqual(superclass.getSimpleName(), "OpenSSLSocketImpl")) {
            superclass = superclass.getSuperclass();
            if (superclass == null) {
                throw new AssertionError("No OpenSSLSocketImpl superclass of socket of type " + cls);
            }
        }
        Intrinsics.checkNotNull(superclass);
        return new c0(superclass);
    }
}
