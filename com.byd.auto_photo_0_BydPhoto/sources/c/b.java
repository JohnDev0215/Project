package c;

import java.io.Closeable;
import java.io.Flushable;
import java.nio.channels.WritableByteChannel;

/* JADX INFO: loaded from: classes2.dex */
public interface b extends Closeable, Flushable, WritableByteChannel {
    b a(String str, int i, int i2);

    b e(int i);

    b h(String str);
}
