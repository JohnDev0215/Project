package android.os;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.system.ErrnoException;
import java.io.Closeable;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class SharedMemory implements Parcelable, Closeable {
    static {
        throw new NoClassDefFoundError();
    }

    @NonNull
    public static native /* synthetic */ SharedMemory create(@Nullable String str, int i) throws ErrnoException;

    public static native /* synthetic */ void unmap(@NonNull ByteBuffer byteBuffer);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public native /* synthetic */ void close();

    public native /* synthetic */ int getSize();

    @NonNull
    public native /* synthetic */ ByteBuffer mapReadWrite() throws ErrnoException;
}
