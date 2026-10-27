package spi;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class q2 implements f8<ByteBuffer> {
    @Override // spi.f8
    public final boolean b(@NonNull ByteBuffer byteBuffer, @NonNull File file, @NonNull lk lkVar) throws Throwable {
        try {
            u2.d(byteBuffer, file);
            return true;
        } catch (IOException e) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e);
            }
            return false;
        }
    }
}
