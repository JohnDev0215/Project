package android.net.util;

import android.os.Handler;
import android.system.Os;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public abstract class PacketReader extends FdEventsReader<byte[]> {
    protected PacketReader(Handler handler, int i) {
        super(handler, new byte[Math.max(i, 2048)]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.net.util.FdEventsReader
    public int readPacket(FileDescriptor fileDescriptor, byte[] bArr) throws Exception {
        return Os.read(fileDescriptor, bArr, 0, bArr.length);
    }
}
