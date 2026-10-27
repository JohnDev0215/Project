package okhttp3.internal.cache2;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.IOException;
import java.nio.channels.FileChannel;
import kotlin.jvm.internal.i;
import okio.Buffer;

/* JADX INFO: compiled from: FileOperator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class FileOperator {
    private final FileChannel fileChannel;

    public FileOperator(FileChannel fileChannel) {
        i.c(fileChannel, "fileChannel");
        this.fileChannel = fileChannel;
    }

    public final void read(long j, Buffer buffer, long j2) throws IOException {
        i.c(buffer, "sink");
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferTo = this.fileChannel.transferTo(j, j2, buffer);
            j += jTransferTo;
            j2 -= jTransferTo;
        }
    }

    public final void write(long j, Buffer buffer, long j2) throws IOException {
        i.c(buffer, BYDAutoMultimediaDevice.PARAM_SOURCE);
        if (j2 < 0 || j2 > buffer.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            long jTransferFrom = this.fileChannel.transferFrom(buffer, j, j2);
            j += jTransferFrom;
            j2 -= jTransferFrom;
        }
    }
}
