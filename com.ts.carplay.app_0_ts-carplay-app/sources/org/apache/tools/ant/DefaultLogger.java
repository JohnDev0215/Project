package org.apache.tools.ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import org.apache.tools.ant.util.DateUtils;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class DefaultLogger implements BuildLogger {
    public static final int LEFT_COLUMN_SIZE = 12;
    protected static final String lSep = StringUtils.LINE_SEP;
    protected PrintStream err;
    protected PrintStream out;
    protected int msgOutputLevel = 0;
    private long startTime = System.currentTimeMillis();
    protected boolean emacsMode = false;

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int level) {
        this.msgOutputLevel = level;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream output) {
        this.out = new PrintStream((OutputStream) output, true);
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream err) {
        this.err = new PrintStream((OutputStream) err, true);
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean emacsMode) {
        this.emacsMode = emacsMode;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent event) {
        this.startTime = System.currentTimeMillis();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent event) {
        Throwable error = event.getException();
        StringBuffer message = new StringBuffer();
        if (error == null) {
            message.append(StringUtils.LINE_SEP);
            message.append("BUILD SUCCESSFUL");
        } else {
            message.append(StringUtils.LINE_SEP);
            message.append("BUILD FAILED");
            message.append(StringUtils.LINE_SEP);
            if (3 <= this.msgOutputLevel || !(error instanceof BuildException)) {
                message.append(StringUtils.getStackTrace(error));
            } else if (error instanceof BuildException) {
                message.append(error.toString());
                message.append(lSep);
            } else {
                message.append(error.getMessage());
                message.append(lSep);
            }
        }
        message.append(StringUtils.LINE_SEP);
        message.append("Total time: ");
        message.append(formatTime(System.currentTimeMillis() - this.startTime));
        String msg = message.toString();
        if (error == null) {
            printMessage(msg, this.out, 3);
        } else {
            printMessage(msg, this.err, 0);
        }
        log(msg);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
        if (2 <= this.msgOutputLevel && !event.getTarget().getName().equals("")) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(StringUtils.LINE_SEP);
            stringBuffer.append(event.getTarget().getName());
            stringBuffer.append(":");
            String msg = stringBuffer.toString();
            printMessage(msg, this.out, event.getPriority());
            log(msg);
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) throws IOException {
        int priority = event.getPriority();
        if (priority <= this.msgOutputLevel) {
            StringBuffer message = new StringBuffer();
            if (event.getTask() != null && !this.emacsMode) {
                String name = event.getTask().getTaskName();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(name);
                stringBuffer.append("] ");
                String label = stringBuffer.toString();
                int size = 12 - label.length();
                StringBuffer tmp = new StringBuffer();
                for (int i = 0; i < size; i++) {
                    tmp.append(" ");
                }
                tmp.append(label);
                String label2 = tmp.toString();
                try {
                    BufferedReader r = new BufferedReader(new StringReader(event.getMessage()));
                    boolean first = true;
                    for (String line = r.readLine(); line != null; line = r.readLine()) {
                        if (!first) {
                            message.append(StringUtils.LINE_SEP);
                        }
                        first = false;
                        message.append(label2);
                        message.append(line);
                    }
                } catch (IOException e) {
                    message.append(label2);
                    message.append(event.getMessage());
                }
            } else {
                String name2 = event.getMessage();
                message.append(name2);
            }
            String msg = message.toString();
            if (priority != 0) {
                printMessage(msg, this.out, priority);
            } else {
                printMessage(msg, this.err, priority);
            }
            log(msg);
        }
    }

    protected static String formatTime(long millis) {
        return DateUtils.formatElapsedTime(millis);
    }

    protected void printMessage(String message, PrintStream stream, int priority) {
        stream.println(message);
    }

    protected void log(String message) {
    }
}
