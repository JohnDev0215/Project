package spi;

import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface up {
    boolean a(@NotNull SSLSocket sSLSocket);

    @Nullable
    String b(@NotNull SSLSocket sSLSocket);

    void c(@NotNull SSLSocket sSLSocket, @Nullable String str, @NotNull List<? extends Protocol> list);

    boolean isSupported();
}
