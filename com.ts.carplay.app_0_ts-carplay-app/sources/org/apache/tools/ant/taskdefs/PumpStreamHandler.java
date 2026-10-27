package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class PumpStreamHandler implements ExecuteStreamHandler {
    private OutputStream err;
    private Thread errorThread;
    private InputStream input;
    private StreamPumper inputPump;
    private OutputStream out;
    private Thread outputThread;

    public PumpStreamHandler(OutputStream out, OutputStream err, InputStream input) {
        this.out = out;
        this.err = err;
        this.input = input;
    }

    public PumpStreamHandler(OutputStream out, OutputStream err) {
        this(out, err, null);
    }

    public PumpStreamHandler(OutputStream outAndErr) {
        this(outAndErr, outAndErr);
    }

    public PumpStreamHandler() {
        this(System.out, System.err);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessOutputStream(InputStream is) {
        createProcessOutputPump(is, this.out);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessErrorStream(InputStream is) {
        OutputStream outputStream = this.err;
        if (outputStream != null) {
            createProcessErrorPump(is, outputStream);
        }
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessInputStream(OutputStream os) {
        InputStream inputStream = this.input;
        if (inputStream != null) {
            this.inputPump = createInputPump(inputStream, os, true);
        } else {
            try {
                os.close();
            } catch (IOException e) {
            }
        }
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void start() {
        this.outputThread.start();
        this.errorThread.start();
        if (this.inputPump != null) {
            Thread inputThread = new Thread(this.inputPump);
            inputThread.setDaemon(true);
            inputThread.start();
        }
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void stop() {
        try {
            this.outputThread.join();
        } catch (InterruptedException e) {
        }
        try {
            this.errorThread.join();
        } catch (InterruptedException e2) {
        }
        StreamPumper streamPumper = this.inputPump;
        if (streamPumper != null) {
            streamPumper.stop();
        }
        try {
            this.err.flush();
        } catch (IOException e3) {
        }
        try {
            this.out.flush();
        } catch (IOException e4) {
        }
    }

    protected OutputStream getErr() {
        return this.err;
    }

    protected OutputStream getOut() {
        return this.out;
    }

    protected void createProcessOutputPump(InputStream is, OutputStream os) {
        this.outputThread = createPump(is, os);
    }

    protected void createProcessErrorPump(InputStream is, OutputStream os) {
        this.errorThread = createPump(is, os);
    }

    protected Thread createPump(InputStream is, OutputStream os) {
        return createPump(is, os, false);
    }

    protected Thread createPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        Thread result = new Thread(new StreamPumper(is, os, closeWhenExhausted));
        result.setDaemon(true);
        return result;
    }

    StreamPumper createInputPump(InputStream is, OutputStream os, boolean closeWhenExhausted) {
        StreamPumper pumper = new StreamPumper(is, os, closeWhenExhausted);
        pumper.setAutoflush(true);
        return pumper;
    }
}
