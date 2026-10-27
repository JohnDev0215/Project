package android.net.util;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public abstract class FdEventsReader<BufferType> {
    private final BufferType mBuffer;
    private FileDescriptor mFd;
    private final Handler mHandler;
    private long mPacketsReceived;
    private final MessageQueue mQueue;

    protected abstract FileDescriptor createFd();

    protected void handlePacket(BufferType buffertype, int i) {
    }

    protected void logError(String str, Exception exc) {
    }

    protected void onStart() {
    }

    protected void onStop() {
    }

    protected abstract int readPacket(FileDescriptor fileDescriptor, BufferType buffertype) throws Exception;

    protected static void closeFd(FileDescriptor fileDescriptor) {
        try {
            SocketUtils.closeSocket(fileDescriptor);
        } catch (IOException unused) {
        }
    }

    protected FdEventsReader(Handler handler, BufferType buffertype) {
        this.mHandler = handler;
        this.mQueue = handler.getLooper().getQueue();
        this.mBuffer = buffertype;
    }

    protected MessageQueue getMessageQueue() {
        return this.mQueue;
    }

    public boolean start() {
        if (!onCorrectThread()) {
            throw new IllegalStateException("start() called from off-thread");
        }
        return createAndRegisterFd();
    }

    public void stop() {
        if (!onCorrectThread()) {
            throw new IllegalStateException("stop() called from off-thread");
        }
        unregisterAndDestroyFd();
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    private boolean createAndRegisterFd() {
        if (this.mFd != null) {
            return true;
        }
        try {
            this.mFd = createFd();
        } catch (Exception e) {
            logError("Failed to create socket: ", e);
            closeFd(this.mFd);
            this.mFd = null;
        }
        if (this.mFd == null) {
            return false;
        }
        getMessageQueue().addOnFileDescriptorEventListener(this.mFd, 5, new MessageQueue.OnFileDescriptorEventListener() { // from class: android.net.util.-$$Lambda$FdEventsReader$t47DgB7XhHMFh6zjMyOAmdKEeDo
            @Override // android.os.MessageQueue.OnFileDescriptorEventListener
            public final int onFileDescriptorEvents(FileDescriptor fileDescriptor, int i) {
                return this.f$0.lambda$createAndRegisterFd$0$FdEventsReader(fileDescriptor, i);
            }
        });
        onStart();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createAndRegisterFd$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ int lambda$createAndRegisterFd$0$FdEventsReader(FileDescriptor fileDescriptor, int i) {
        if (isRunning() && handleInput()) {
            return 5;
        }
        unregisterAndDestroyFd();
        return 0;
    }

    private boolean isRunning() {
        FileDescriptor fileDescriptor = this.mFd;
        return fileDescriptor != null && fileDescriptor.valid();
    }

    private boolean handleInput() {
        while (isRunning()) {
            try {
                int packet = readPacket(this.mFd, this.mBuffer);
                if (packet < 1) {
                    if (!isRunning()) {
                        return false;
                    }
                    logError("Socket closed, exiting", null);
                    return false;
                }
                this.mPacketsReceived++;
                try {
                    handlePacket(this.mBuffer, packet);
                } catch (Exception e) {
                    logError("handlePacket error: ", e);
                    Log.wtf(FdEventsReader.class.getSimpleName(), "Error handling packet", e);
                }
            } catch (ErrnoException e2) {
                int i = e2.errno;
                if (i == OsConstants.EAGAIN) {
                    return true;
                }
                if (i != OsConstants.EINTR) {
                    if (!isRunning()) {
                        return false;
                    }
                    logError("readPacket error: ", e2);
                    return false;
                }
            } catch (Exception e3) {
                if (!isRunning()) {
                    return false;
                }
                logError("readPacket error: ", e3);
                return false;
            }
        }
        return false;
    }

    private void unregisterAndDestroyFd() {
        if (this.mFd == null) {
            return;
        }
        getMessageQueue().removeOnFileDescriptorEventListener(this.mFd);
        closeFd(this.mFd);
        this.mFd = null;
        onStop();
    }

    private boolean onCorrectThread() {
        return this.mHandler.getLooper() == Looper.myLooper();
    }
}
