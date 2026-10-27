package r9;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: renamed from: r9.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC2321k extends J, ReadableByteChannel {
    String H(Charset charset);

    InputStream J();

    C2319i c();

    boolean l(long j3);

    long w(C c);

    int x(y yVar);
}
