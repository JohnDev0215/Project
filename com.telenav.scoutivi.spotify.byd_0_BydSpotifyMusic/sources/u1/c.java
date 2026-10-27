package u1;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ByteBufferEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements n1.d<ByteBuffer> {
    @Override // n1.d
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public boolean b(ByteBuffer byteBuffer, File file, n1.i iVar) throws Throwable {
        try {
            k2.a.e(byteBuffer, file);
            return true;
        } catch (IOException e7) {
            if (Log.isLoggable("ByteBufferEncoder", 3)) {
                Log.d("ByteBufferEncoder", "Failed to write data", e7);
            }
            return false;
        }
    }
}
