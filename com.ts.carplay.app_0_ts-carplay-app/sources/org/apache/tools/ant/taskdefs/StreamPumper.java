package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class StreamPumper implements Runnable {
    private static final int SIZE = 128;
    private boolean autoflush;
    private boolean closeWhenExhausted;
    private boolean finished;
    private InputStream is;
    private OutputStream os;

    public StreamPumper(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        this.autoflush = false;
        this.is = is;
        this.os = os;
        this.closeWhenExhausted = closeWhenExhausted;
    }

    public StreamPumper(InputStream is, OutputStream os) {
        this(is, os, false);
    }

    void setAutoflush(boolean autoflush) {
        this.autoflush = autoflush;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        synchronized (this) {
            this.finished = false;
        }
        byte[] buf = new byte[128];
        int i = 0;
        while (true) {
            try {
                int i2 = this.is.read(buf);
                if (i2 <= 0) {
                    break;
                }
                try {
                    if (this.finished) {
                        break;
                    }
                    try {
                        this.os.write(buf, 0, i2);
                        if (this.autoflush) {
                            this.os.flush();
                        }
                        i = i2;
                    } catch (Exception e) {
                        if (this.closeWhenExhausted) {
                            try {
                                this.os.close();
                            } catch (IOException e2) {
                            }
                        }
                        synchronized (this) {
                            this.finished = true;
                            notifyAll();
                        }
                        return;
                    } catch (Throwable th) {
                        th = th;
                        if (this.closeWhenExhausted) {
                            try {
                                this.os.close();
                            } catch (IOException e3) {
                            }
                        }
                        synchronized (this) {
                            this.finished = true;
                            notifyAll();
                        }
                        throw th;
                    }
                } catch (Exception e4) {
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e5) {
            } catch (Throwable th3) {
                th = th3;
            }
        }
        if (this.closeWhenExhausted) {
            try {
                this.os.close();
            } catch (IOException e6) {
            }
        }
        synchronized (this) {
            this.finished = true;
            notifyAll();
        }
    }

    public synchronized boolean isFinished() {
        return this.finished;
    }

    public synchronized void waitFor() throws InterruptedException {
        while (!isFinished()) {
            wait();
        }
    }

    synchronized void stop() {
        this.finished = true;
        notifyAll();
    }
}
