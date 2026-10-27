package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSThread {
    private static final String TAG = "@@@voOSThread";
    private long nativeContext_ = 0;
    private voRunnable runnable_;
    private Thread threadPlayback;

    protected native int nativeOnRun();

    private class voRunnable implements Runnable {
        private voOSThread thread_;

        public voRunnable(voOSThread voosthread) {
            this.thread_ = voosthread;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.thread_.playback();
        }
    }

    public voOSThread() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "Construct!", new Object[0]);
        }
    }

    public int start() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "voOSThread start", new Object[0]);
        }
        if (this.runnable_ == null) {
            this.runnable_ = new voRunnable(this);
        }
        if (this.threadPlayback == null) {
            Thread thread = new Thread(this.runnable_, "voOS Java Thread for C");
            this.threadPlayback = thread;
            thread.setPriority(8);
            this.threadPlayback.start();
        }
        return 0;
    }

    public int stop() {
        while (this.threadPlayback != null) {
            try {
                Thread.sleep(2L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "voOSThread stopped!", new Object[0]);
        }
        return 0;
    }

    public void playback() {
        if (voLog.enablePrintLog()) {
            voLog.i(TAG, "voOSThread running", new Object[0]);
        }
        try {
            nativeOnRun();
        } catch (Exception unused) {
            voLog.e(TAG, "nativeOnRun Error!", new Object[0]);
        }
        this.runnable_ = null;
        this.threadPlayback = null;
    }
}
