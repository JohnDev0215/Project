package f2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX INFO: loaded from: classes.dex */
public interface d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ByteBuffer f14550a = ByteBuffer.allocateDirect(0).order(ByteOrder.nativeOrder());

    ByteBuffer a();

    boolean b();

    b c(b bVar);

    void d();

    void e();

    boolean f();

    void flush();

    void g(ByteBuffer byteBuffer);
}
