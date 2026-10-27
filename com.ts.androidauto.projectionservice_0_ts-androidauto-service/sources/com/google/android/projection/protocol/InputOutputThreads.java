package com.google.android.projection.protocol;

import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.google.android.projection.protocol.Transport;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
class InputOutputThreads {
    private static final boolean DBG_TRACE = false;
    private static final int INPUT_OUTPUT_THREAD_PRIORITY = -19;
    private static final int MAX_FRAME_SIZE = 65544;
    private static final String TAG = "AHU.GAL_RECEIVER";
    private static final int THREAD_INTERRUPT_AWAIT_TIMEOUT_MS = 250;
    private static final int THREAD_TEAR_DOWN_TIMEOUT_MS = 2500;
    private static final boolean VERBOSE_LOGGING_ENABLED = false;
    private final FrameParser mFrameParser;
    private final Handler mHandler;
    private volatile ReaderThread mReaderThread;
    private volatile WriterThread mWriterThread;
    private Runnable stopTimeout;
    private final AtomicReference<Transport.StopReason> mStopRequested = new AtomicReference<>();
    private final AtomicBoolean mFlushingMessages = new AtomicBoolean(false);
    private final AtomicBoolean mThreadsTerminatedCalled = new AtomicBoolean();
    private final CountDownLatch mStopProgress = new CountDownLatch(2);
    private final Object stopRequestAndFlushLock = new Object();

    public interface FrameParser {
        int dequeueOutgoingFrame(byte[] bArr);

        void enqueueIncomingFrame(byte[] bArr, int i);

        int getFrameSizeToRead(byte[] bArr, int i);

        void onTerminating(Runnable runnable);

        void onThreadsTerminated(Transport.StopReason stopReason);
    }

    public InputOutputThreads(Handler handler, FrameParser frameParser) {
        this.mHandler = handler;
        this.mFrameParser = frameParser;
    }

    public void start(Transport transport) {
        this.mReaderThread = new ReaderThread(transport);
        this.mWriterThread = new WriterThread(transport);
        this.mReaderThread.start();
        this.mWriterThread.start();
    }

    private Runnable getStopTimeout() {
        Runnable runnable;
        synchronized (this) {
            if (this.stopTimeout == null) {
                this.stopTimeout = new Runnable() { // from class: com.google.android.projection.protocol.-$$Lambda$InputOutputThreads$8WaLrpw4YQHpg25NrRstdArMoWE
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$getStopTimeout$0$InputOutputThreads();
                    }
                };
            }
            runnable = this.stopTimeout;
        }
        return runnable;
    }

    public /* synthetic */ void lambda$getStopTimeout$0$InputOutputThreads() {
        boolean unresponsive;
        if (this.mStopProgress.getCount() != 0) {
            Log.w("AHU.GAL_RECEIVER", "Threads did not stop in 2500 ms");
            this.mFlushingMessages.set(false);
            this.mReaderThread.interrupt();
            this.mWriterThread.interrupt();
            try {
                unresponsive = !this.mStopProgress.await(250L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                unresponsive = true;
            }
            if (unresponsive) {
                Log.w("AHU.GAL_RECEIVER", "Threads unresponsive to interrupt; notifying of termination anyway");
            }
        }
        if (this.mThreadsTerminatedCalled.compareAndSet(false, true)) {
            this.mFrameParser.onThreadsTerminated(this.mStopRequested.get());
        }
    }

    public boolean stop(final Transport.StopReason reason) {
        if (this.mReaderThread == null || this.mWriterThread == null) {
            Log.w("AHU.GAL_RECEIVER", "Asked to stop before started");
            return false;
        }
        synchronized (this.stopRequestAndFlushLock) {
            if (!this.mStopRequested.compareAndSet(null, reason)) {
                return false;
            }
            if (Transport.StopReason.MD_BYEBYE.equals(reason)) {
                this.mFlushingMessages.set(true);
            }
            this.mHandler.postDelayed(getStopTimeout(), 2500L);
            if (Transport.StopReason.MD_BYEBYE.equals(reason)) {
                this.mFrameParser.onTerminating(new Runnable() { // from class: com.google.android.projection.protocol.InputOutputThreads.1
                    @Override // java.lang.Runnable
                    public void run() {
                        InputOutputThreads.this.mReaderThread.mTransport.stopTransport(reason);
                        InputOutputThreads.this.mFlushingMessages.set(false);
                    }
                });
            } else {
                this.mFrameParser.onTerminating(null);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isStopRequestedAndNoMessageFlushRequested() {
        boolean z;
        synchronized (this.stopRequestAndFlushLock) {
            z = (this.mStopRequested.get() == null || this.mFlushingMessages.get()) ? false : true;
        }
        return z;
    }

    private void tearDownIfAllThreadsStopped() {
        if (this.mStopProgress.getCount() == 0) {
            Runnable stopTimeout = getStopTimeout();
            this.mHandler.removeCallbacks(stopTimeout);
            this.mHandler.post(stopTimeout);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void threadStopped(InputOutputThread thread) {
        Log.d("AHU.GAL_RECEIVER", thread.getName() + " is stopping");
        if (stop(Transport.StopReason.CONNECTION_TERMINATED)) {
            Log.w("AHU.GAL_RECEIVER", thread.getName() + " exiting without stop requested; stop triggered");
        }
        this.mStopProgress.countDown();
        tearDownIfAllThreadsStopped();
    }

    private String hexdumpBuffer(byte[] buf, int len) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            if (i >= len) {
                break;
            }
            sb.append(String.format(" %02x", Byte.valueOf(buf[i])));
            if (i != 32) {
                i++;
            } else {
                sb.append("...");
                break;
            }
        }
        return sb.toString();
    }

    private abstract class InputOutputThread extends Thread {
        final Transport mTransport;

        abstract void doIo();

        InputOutputThread(String name, Transport transport) {
            super(name);
            this.mTransport = transport;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                Process.setThreadPriority(-19);
                doIo();
            } finally {
                InputOutputThreads.this.threadStopped(this);
            }
        }
    }

    private class ReaderThread extends InputOutputThread {
        private static final int HEADER_READ_LENGTH = 4;

        ReaderThread(Transport transport) {
            super("ReaderThread", transport);
        }

        private int read(byte[] buf, int offset, int len) {
            int bytesRead = 0;
            while (bytesRead < len && !InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                try {
                    int nbytes = this.mTransport.read(buf, offset + bytesRead, len - bytesRead);
                    if (nbytes > 0) {
                        bytesRead += nbytes;
                    }
                } catch (IOException e) {
                    Log.e("AHU.GAL_RECEIVER", "Caught exception on read. Exiting." + e);
                    if (!InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                        InputOutputThreads.this.stop(Transport.StopReason.CONNECTION_ERROR);
                    }
                }
            }
            return bytesRead;
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.InputOutputThread
        void doIo() {
            byte[] buf = new byte[65544];
            while (!InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                int bytesRead = read(buf, 0, 4);
                if (bytesRead == 4) {
                    int len = InputOutputThreads.this.mFrameParser.getFrameSizeToRead(buf, 4);
                    int bytesRead2 = read(buf, 4, len);
                    if (bytesRead2 == len) {
                        if (!InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                            InputOutputThreads.this.mFrameParser.enqueueIncomingFrame(buf, len + 4);
                        }
                    } else {
                        Log.e("AHU.GAL_RECEIVER", "read returned " + bytesRead2 + " while expecting " + len);
                        return;
                    }
                } else {
                    Log.e("AHU.GAL_RECEIVER", "read returned " + bytesRead + " while expecting 4");
                    return;
                }
            }
        }
    }

    private class WriterThread extends InputOutputThread {
        WriterThread(Transport transport) {
            super("WriterThread", transport);
        }

        @Override // com.google.android.projection.protocol.InputOutputThreads.InputOutputThread
        void doIo() {
            int retryCount;
            byte[] buf = new byte[65544];
            do {
                try {
                    if (!InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                        int len = InputOutputThreads.this.mFrameParser.dequeueOutgoingFrame(buf);
                        if (len == 0) {
                            Log.i("AHU.GAL_RECEIVER", "Writer thread shutting down.");
                            return;
                        }
                        retryCount = 0;
                        while (retryCount < 10 && !InputOutputThreads.this.isStopRequestedAndNoMessageFlushRequested()) {
                            try {
                                this.mTransport.write(buf, 0, len);
                                break;
                            } catch (IOException e) {
                                retryCount++;
                                Log.w("AHU.GAL_RECEIVER", "IOException while writing, retryCount: " + retryCount + " " + e);
                            }
                        }
                    } else {
                        return;
                    }
                } catch (IOException e2) {
                    Log.e("AHU.GAL_RECEIVER", "Caught exception on write. Exiting." + e2);
                    InputOutputThreads.this.stop(Transport.StopReason.CONNECTION_ERROR);
                    return;
                }
            } while (retryCount < 10);
            throw new IOException("max retry reached");
        }
    }
}
