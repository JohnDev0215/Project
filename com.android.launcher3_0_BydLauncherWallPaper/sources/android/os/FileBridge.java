package android.os;

import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteOrder;
import libcore.io.IoBridge;
import libcore.io.IoUtils;
import libcore.io.Memory;
import libcore.io.Streams;
import libcore.util.ArrayUtils;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class FileBridge extends Thread {
    private static final int CMD_CLOSE = 3;
    private static final int CMD_FSYNC = 2;
    private static final int CMD_WRITE = 1;
    private static final int MSG_LENGTH = 8;
    private static final String TAG = "FileBridge";
    private ParcelFileDescriptor mClient;
    private volatile boolean mClosed;
    private ParcelFileDescriptor mServer;
    private ParcelFileDescriptor mTarget;

    public FileBridge() {
        try {
            ParcelFileDescriptor[] parcelFileDescriptorArrCreateSocketPair = ParcelFileDescriptor.createSocketPair(OsConstants.SOCK_STREAM);
            this.mServer = parcelFileDescriptorArrCreateSocketPair[0];
            this.mClient = parcelFileDescriptorArrCreateSocketPair[1];
        } catch (IOException unused) {
            throw new RuntimeException("Failed to create bridge");
        }
    }

    public boolean isClosed() {
        return this.mClosed;
    }

    public void forceClose() {
        IoUtils.closeQuietly(this.mTarget);
        IoUtils.closeQuietly(this.mServer);
        this.mClosed = true;
    }

    public void setTargetFile(ParcelFileDescriptor parcelFileDescriptor) {
        this.mTarget = parcelFileDescriptor;
    }

    public ParcelFileDescriptor getClientSocket() {
        return this.mClient;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                try {
                    if (IoBridge.read(this.mServer.getFileDescriptor(), bArr, 0, 8) != 8) {
                        break;
                    }
                    int iPeekInt = Memory.peekInt(bArr, 0, ByteOrder.BIG_ENDIAN);
                    if (iPeekInt == 1) {
                        int iPeekInt2 = Memory.peekInt(bArr, 4, ByteOrder.BIG_ENDIAN);
                        while (iPeekInt2 > 0) {
                            int i = IoBridge.read(this.mServer.getFileDescriptor(), bArr, 0, Math.min(8192, iPeekInt2));
                            if (i == -1) {
                                throw new IOException("Unexpected EOF; still expected " + iPeekInt2 + " bytes");
                            }
                            IoBridge.write(this.mTarget.getFileDescriptor(), bArr, 0, i);
                            iPeekInt2 -= i;
                        }
                    } else if (iPeekInt == 2) {
                        Os.fsync(this.mTarget.getFileDescriptor());
                        IoBridge.write(this.mServer.getFileDescriptor(), bArr, 0, 8);
                    } else if (iPeekInt == 3) {
                        Os.fsync(this.mTarget.getFileDescriptor());
                        this.mTarget.close();
                        this.mClosed = true;
                        IoBridge.write(this.mServer.getFileDescriptor(), bArr, 0, 8);
                        break;
                    }
                } catch (ErrnoException | IOException e) {
                    Log.wtf(TAG, "Failed during bridge", e);
                }
            } finally {
                forceClose();
            }
        }
    }

    public static class FileBridgeOutputStream extends OutputStream {
        private final FileDescriptor mClient;
        private final ParcelFileDescriptor mClientPfd;
        private final byte[] mTemp = new byte[8];

        public FileBridgeOutputStream(ParcelFileDescriptor parcelFileDescriptor) {
            this.mClientPfd = parcelFileDescriptor;
            this.mClient = parcelFileDescriptor.getFileDescriptor();
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                writeCommandAndBlock(3, "close()");
            } finally {
                IoUtils.closeQuietly(this.mClientPfd);
            }
        }

        public void fsync() throws IOException {
            writeCommandAndBlock(2, "fsync()");
        }

        private void writeCommandAndBlock(int i, String str) throws IOException {
            Memory.pokeInt(this.mTemp, 0, i, ByteOrder.BIG_ENDIAN);
            IoBridge.write(this.mClient, this.mTemp, 0, 8);
            if (IoBridge.read(this.mClient, this.mTemp, 0, 8) != 8 || Memory.peekInt(this.mTemp, 0, ByteOrder.BIG_ENDIAN) != i) {
                throw new IOException("Failed to execute " + str + " across bridge");
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            ArrayUtils.throwsIfOutOfBounds(bArr.length, i, i2);
            Memory.pokeInt(this.mTemp, 0, 1, ByteOrder.BIG_ENDIAN);
            Memory.pokeInt(this.mTemp, 4, i2, ByteOrder.BIG_ENDIAN);
            IoBridge.write(this.mClient, this.mTemp, 0, 8);
            IoBridge.write(this.mClient, bArr, i, i2);
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            Streams.writeSingleByte(this, i);
        }
    }
}
