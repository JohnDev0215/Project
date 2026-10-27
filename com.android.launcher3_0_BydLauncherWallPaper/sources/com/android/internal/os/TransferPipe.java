package com.android.internal.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Slog;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import libcore.io.IoUtils;

/* JADX INFO: loaded from: classes4.dex */
public class TransferPipe implements Runnable, Closeable {
    static final boolean DEBUG = false;
    static final long DEFAULT_TIMEOUT = 5000;
    static final String TAG = "TransferPipe";
    String mBufferPrefix;
    boolean mComplete;
    long mEndTime;
    String mFailure;
    final ParcelFileDescriptor[] mFds;
    FileDescriptor mOutFd;
    final Thread mThread;

    interface Caller {
        void go(IInterface iInterface, FileDescriptor fileDescriptor, String str, String[] strArr) throws RemoteException;
    }

    public TransferPipe() throws IOException {
        this(null);
    }

    public TransferPipe(String str) throws IOException {
        this(str, TAG);
    }

    protected TransferPipe(String str, String str2) throws IOException {
        this.mThread = new Thread(this, str2);
        this.mFds = ParcelFileDescriptor.createPipe();
        this.mBufferPrefix = str;
    }

    ParcelFileDescriptor getReadFd() {
        return this.mFds[0];
    }

    public ParcelFileDescriptor getWriteFd() {
        return this.mFds[1];
    }

    public void setBufferPrefix(String str) {
        this.mBufferPrefix = str;
    }

    public static void dumpAsync(IBinder iBinder, FileDescriptor fileDescriptor, String[] strArr) throws IOException, RemoteException {
        goDump(iBinder, fileDescriptor, strArr);
    }

    public static byte[] dumpAsync(IBinder iBinder, String... strArr) throws IOException, RemoteException {
        ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        try {
            dumpAsync(iBinder, parcelFileDescriptorArrCreatePipe[1].getFileDescriptor(), strArr);
            parcelFileDescriptorArrCreatePipe[1].close();
            parcelFileDescriptorArrCreatePipe[1] = null;
            byte[] bArr = new byte[4096];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorArrCreatePipe[0].getFileDescriptor());
                while (true) {
                    try {
                        int i = fileInputStream.read(bArr);
                        if (i != -1) {
                            byteArrayOutputStream.write(bArr, 0, i);
                        } else {
                            fileInputStream.close();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                            return byteArray;
                        }
                    } finally {
                    }
                }
            } finally {
            }
        } finally {
            parcelFileDescriptorArrCreatePipe[0].close();
            IoUtils.closeQuietly(parcelFileDescriptorArrCreatePipe[1]);
        }
    }

    static void go(Caller caller, IInterface iInterface, FileDescriptor fileDescriptor, String str, String[] strArr) throws IOException, RemoteException {
        go(caller, iInterface, fileDescriptor, str, strArr, 5000L);
    }

    static void go(Caller caller, IInterface iInterface, FileDescriptor fileDescriptor, String str, String[] strArr, long j) throws IOException, RemoteException {
        if (iInterface.asBinder() instanceof Binder) {
            try {
                caller.go(iInterface, fileDescriptor, str, strArr);
                return;
            } catch (RemoteException unused) {
                return;
            }
        }
        TransferPipe transferPipe = new TransferPipe();
        try {
            caller.go(iInterface, transferPipe.getWriteFd().getFileDescriptor(), str, strArr);
            transferPipe.go(fileDescriptor, j);
            transferPipe.close();
        } catch (Throwable th) {
            try {
                transferPipe.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    static void goDump(IBinder iBinder, FileDescriptor fileDescriptor, String[] strArr) throws IOException, RemoteException {
        goDump(iBinder, fileDescriptor, strArr, 5000L);
    }

    static void goDump(IBinder iBinder, FileDescriptor fileDescriptor, String[] strArr, long j) throws IOException, RemoteException {
        if (iBinder instanceof Binder) {
            try {
                iBinder.dump(fileDescriptor, strArr);
                return;
            } catch (RemoteException unused) {
                return;
            }
        }
        TransferPipe transferPipe = new TransferPipe();
        try {
            iBinder.dumpAsync(transferPipe.getWriteFd().getFileDescriptor(), strArr);
            transferPipe.go(fileDescriptor, j);
            transferPipe.close();
        } catch (Throwable th) {
            try {
                transferPipe.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void go(FileDescriptor fileDescriptor) throws IOException {
        go(fileDescriptor, 5000L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0039, code lost:
    
        if (r3 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0047, code lost:
    
        throw new java.io.IOException(r2.mFailure);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void go(java.io.FileDescriptor r3, long r4) throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L4b
            r2.mOutFd = r3     // Catch: java.lang.Throwable -> L48
            long r0 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L48
            long r0 = r0 + r4
            r2.mEndTime = r0     // Catch: java.lang.Throwable -> L48
            r3 = 1
            r2.closeFd(r3)     // Catch: java.lang.Throwable -> L48
            java.lang.Thread r3 = r2.mThread     // Catch: java.lang.Throwable -> L48
            r3.start()     // Catch: java.lang.Throwable -> L48
        L13:
            java.lang.String r3 = r2.mFailure     // Catch: java.lang.Throwable -> L48
            if (r3 != 0) goto L39
            boolean r4 = r2.mComplete     // Catch: java.lang.Throwable -> L48
            if (r4 != 0) goto L39
            long r3 = r2.mEndTime     // Catch: java.lang.Throwable -> L48
            long r0 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L48
            long r3 = r3 - r0
            r0 = 0
            int r5 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r5 <= 0) goto L2c
            r2.wait(r3)     // Catch: java.lang.InterruptedException -> L13 java.lang.Throwable -> L48
            goto L13
        L2c:
            java.lang.Thread r3 = r2.mThread     // Catch: java.lang.Throwable -> L48
            r3.interrupt()     // Catch: java.lang.Throwable -> L48
            java.io.IOException r3 = new java.io.IOException     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = "Timeout"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L48
            throw r3     // Catch: java.lang.Throwable -> L48
        L39:
            if (r3 != 0) goto L40
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L48
            r2.kill()
            return
        L40:
            java.io.IOException r3 = new java.io.IOException     // Catch: java.lang.Throwable -> L48
            java.lang.String r4 = r2.mFailure     // Catch: java.lang.Throwable -> L48
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L48
            throw r3     // Catch: java.lang.Throwable -> L48
        L48:
            r3 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L48
            throw r3     // Catch: java.lang.Throwable -> L4b
        L4b:
            r3 = move-exception
            r2.kill()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.os.TransferPipe.go(java.io.FileDescriptor, long):void");
    }

    void closeFd(int i) {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mFds;
        if (parcelFileDescriptorArr[i] != null) {
            try {
                parcelFileDescriptorArr[i].close();
            } catch (IOException unused) {
            }
            this.mFds[i] = null;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        kill();
    }

    public void kill() {
        synchronized (this) {
            closeFd(0);
            closeFd(1);
        }
    }

    protected OutputStream getNewOutputStream() {
        return new FileOutputStream(this.mOutFd);
    }

    @Override // java.lang.Runnable
    public void run() {
        byte[] bArr = new byte[1024];
        synchronized (this) {
            ParcelFileDescriptor readFd = getReadFd();
            if (readFd == null) {
                Slog.w(TAG, "Pipe has been closed...");
                return;
            }
            FileInputStream fileInputStream = new FileInputStream(readFd.getFileDescriptor());
            OutputStream newOutputStream = getNewOutputStream();
            String str = this.mBufferPrefix;
            byte[] bytes = str != null ? str.getBytes() : null;
            boolean z = true;
            while (true) {
                try {
                    int i = fileInputStream.read(bArr);
                    if (i <= 0) {
                        this.mThread.isInterrupted();
                        synchronized (this) {
                            this.mComplete = true;
                            notifyAll();
                        }
                        return;
                    }
                    if (bytes == null) {
                        newOutputStream.write(bArr, 0, i);
                    } else {
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < i) {
                            if (bArr[i2] != 10) {
                                if (i2 > i3) {
                                    newOutputStream.write(bArr, i3, i2 - i3);
                                }
                                if (z) {
                                    newOutputStream.write(bytes);
                                    z = false;
                                }
                                int i4 = i2;
                                do {
                                    i4++;
                                    if (i4 >= i) {
                                        break;
                                    }
                                } while (bArr[i4] != 10);
                                if (i4 < i) {
                                    z = true;
                                }
                                i3 = i2;
                                i2 = i4;
                            }
                            i2++;
                        }
                        if (i > i3) {
                            newOutputStream.write(bArr, i3, i - i3);
                        }
                    }
                } catch (IOException e) {
                    synchronized (this) {
                        this.mFailure = e.toString();
                        notifyAll();
                        return;
                    }
                }
            }
        }
    }
}
