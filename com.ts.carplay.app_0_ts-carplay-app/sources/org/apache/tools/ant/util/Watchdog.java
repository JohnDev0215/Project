package org.apache.tools.ant.util;

import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Watchdog implements Runnable {
    private Vector observers = new Vector(1);
    private boolean stopped = false;
    private long timeout;

    public Watchdog(long timeout) {
        this.timeout = -1L;
        if (timeout < 1) {
            throw new IllegalArgumentException("timeout less than 1.");
        }
        this.timeout = timeout;
    }

    public void addTimeoutObserver(TimeoutObserver to) {
        this.observers.addElement(to);
    }

    public void removeTimeoutObserver(TimeoutObserver to) {
        this.observers.removeElement(to);
    }

    protected final void fireTimeoutOccured() {
        Enumeration e = this.observers.elements();
        while (e.hasMoreElements()) {
            ((TimeoutObserver) e.nextElement()).timeoutOccured(this);
        }
    }

    public synchronized void start() {
        this.stopped = false;
        Thread t = new Thread(this, "WATCHDOG");
        t.setDaemon(true);
        t.start();
    }

    public synchronized void stop() {
        this.stopped = true;
        notifyAll();
    }

    @Override // java.lang.Runnable
    public synchronized void run() {
        long until = System.currentTimeMillis() + this.timeout;
        long j = 0;
        while (!this.stopped) {
            long now = System.currentTimeMillis();
            if (until <= now) {
                break;
            }
            try {
                wait(until - now);
            } catch (InterruptedException e) {
            }
            j = now;
        }
        if (!this.stopped) {
            fireTimeoutOccured();
        }
    }
}
