package androidx.room.util;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: classes2.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class FileUtil {
    @SuppressLint({"LambdaLast"})
    public static void copy(@NonNull ReadableByteChannel readableByteChannel, @NonNull FileChannel fileChannel) throws IOException {
        try {
            fileChannel.transferFrom(readableByteChannel, 0L, Long.MAX_VALUE);
            fileChannel.force(false);
        } finally {
            readableByteChannel.close();
            fileChannel.close();
        }
    }

    private FileUtil() {
    }
}
