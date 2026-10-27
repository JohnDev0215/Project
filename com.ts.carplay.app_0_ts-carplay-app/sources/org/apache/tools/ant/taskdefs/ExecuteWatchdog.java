package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.util.TimeoutObserver;
import org.apache.tools.ant.util.Watchdog;

/* JADX INFO: loaded from: classes.dex */
public class ExecuteWatchdog implements TimeoutObserver {
    private Exception caught;
    private boolean killedProcess;
    private Process process;
    private boolean watch;
    private Watchdog watchdog;

    public ExecuteWatchdog(long timeout) {
        this.watch = false;
        this.caught = null;
        this.killedProcess = false;
        Watchdog watchdog = new Watchdog(timeout);
        this.watchdog = watchdog;
        watchdog.addTimeoutObserver(this);
    }

    public ExecuteWatchdog(int timeout) {
        this(timeout);
    }

    public synchronized void start(Process process) {
        try {
            if (process == null) {
                throw new NullPointerException("process is null.");
            }
            if (this.process != null) {
                throw new IllegalStateException("Already running.");
            }
            this.caught = null;
            this.killedProcess = false;
            this.watch = true;
            this.process = process;
            this.watchdog.start();
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void stop() {
        this.watchdog.stop();
        this.watch = false;
        this.process = null;
    }

    @Override // org.apache.tools.ant.util.TimeoutObserver
    public void timeoutOccured(Watchdog w) throws Throwable {
        try {
            try {
                this.process.exitValue();
            } catch (Exception e) {
                try {
                    this.caught = e;
                    cleanUp();
                    return;
                } catch (Throwable th) {
                    th = th;
                    cleanUp();
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                cleanUp();
                throw th;
            }
        } catch (IllegalThreadStateException e2) {
            if (this.watch) {
                this.killedProcess = true;
                this.process.destroy();
            }
        }
        cleanUp();
    }

    protected void cleanUp() {
        this.watch = false;
        this.process = null;
    }

    public void checkException() throws BuildException {
        if (this.caught != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Exception in ExecuteWatchdog.run: ");
            stringBuffer.append(this.caught.getMessage());
            throw new BuildException(stringBuffer.toString(), this.caught);
        }
    }

    public boolean isWatching() {
        return this.watch;
    }

    public boolean killedProcess() {
        return this.killedProcess;
    }
}
