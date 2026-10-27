package com.google.android.projection.protocol;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class EchoVendorExtension extends VendorExtensionBase {
    private static final int ECHO_DATA = 1;
    private static final String SERVICE_NAME = "com.example.android.projection.protocol.EchoVendorExtension";
    private static final String[] SERVICE_WHITELIST = {"com.example.echovendor"};
    private LooperThread mEchoThread;
    private Handler mHandler;

    @Override // com.google.android.projection.protocol.VendorExtensionBase, com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        LooperThread looperThread = new LooperThread("echo");
        this.mEchoThread = looperThread;
        looperThread.start();
        this.mHandler = new EchoHandler(this.mEchoThread.getLooper());
        return super.create(serviceId, nativeGalReceiver);
    }

    @Override // com.google.android.projection.protocol.VendorExtensionBase, com.google.android.projection.protocol.NativeObject
    public void destroy() {
        LooperThread looperThread = this.mEchoThread;
        Objects.requireNonNull(looperThread);
        looperThread.stopThread();
        super.destroy();
    }

    @Override // com.google.android.projection.protocol.VendorExtensionBase
    protected int onMessage(byte[] data) {
        Handler handler = this.mHandler;
        Objects.requireNonNull(handler);
        Handler localHandler = handler;
        Message echoMessage = localHandler.obtainMessage(1, data);
        localHandler.sendMessage(echoMessage);
        if (data == null) {
            return 0;
        }
        return data.length;
    }

    @Override // com.google.android.projection.protocol.VendorExtensionBase
    protected String getServiceName() {
        return SERVICE_NAME;
    }

    @Override // com.google.android.projection.protocol.VendorExtensionBase
    protected String[] getPackageWhitelist() {
        return SERVICE_WHITELIST;
    }

    class EchoHandler extends Handler {
        public EchoHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                byte[] data = (byte[]) msg.obj;
                EchoVendorExtension.this.sendData(data);
            } else {
                super.handleMessage(msg);
            }
        }
    }

    static class LooperThread extends Thread {
        private final Semaphore mInitWaitSemaphore;
        private Looper mLooper;
        private final int mThreadPriority;

        public LooperThread(String name) {
            super(name);
            this.mInitWaitSemaphore = new Semaphore(0);
            this.mThreadPriority = 0;
        }

        public LooperThread(String name, int priority) {
            super(name);
            this.mInitWaitSemaphore = new Semaphore(0);
            this.mThreadPriority = priority;
        }

        @Override // java.lang.Thread
        public void start() {
            super.start();
            try {
                if (!this.mInitWaitSemaphore.tryAcquire(5L, TimeUnit.SECONDS)) {
                    throw new RuntimeException("LooperThread init timeout");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public void stopThread() {
            Looper looper = this.mLooper;
            Objects.requireNonNull(looper);
            looper.quit();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(this.mThreadPriority);
            Looper.prepare();
            Looper looperMyLooper = Looper.myLooper();
            Objects.requireNonNull(looperMyLooper);
            this.mLooper = looperMyLooper;
            this.mInitWaitSemaphore.release();
            Looper.loop();
        }

        public Looper getLooper() {
            Looper looper = this.mLooper;
            Objects.requireNonNull(looper);
            return looper;
        }
    }
}
