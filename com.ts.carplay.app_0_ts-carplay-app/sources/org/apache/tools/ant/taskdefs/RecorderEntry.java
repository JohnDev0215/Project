package org.apache.tools.ant.taskdefs;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.BuildLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.SubBuildListener;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class RecorderEntry implements BuildLogger, SubBuildListener {
    private String filename;
    private Project project;
    private long targetStartTime;
    private boolean record = true;
    private int loglevel = 2;
    private PrintStream out = null;
    private boolean emacsMode = false;

    protected RecorderEntry(String name) {
        this.filename = null;
        this.targetStartTime = 0L;
        this.targetStartTime = System.currentTimeMillis();
        this.filename = name;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setRecordState(Boolean state) {
        if (state != null) {
            flush();
            this.record = state.booleanValue();
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent event) {
        log("> BUILD STARTED", 4);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent event) {
        log("< BUILD FINISHED", 4);
        if (this.record && this.out != null) {
            Throwable error = event.getException();
            if (error == null) {
                PrintStream printStream = this.out;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(StringUtils.LINE_SEP);
                stringBuffer.append("BUILD SUCCESSFUL");
                printStream.println(stringBuffer.toString());
            } else {
                PrintStream printStream2 = this.out;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append(StringUtils.LINE_SEP);
                stringBuffer2.append("BUILD FAILED");
                stringBuffer2.append(StringUtils.LINE_SEP);
                printStream2.println(stringBuffer2.toString());
                error.printStackTrace(this.out);
            }
        }
        cleanup();
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildFinished(BuildEvent event) {
        if (event.getProject() == this.project) {
            cleanup();
        }
    }

    @Override // org.apache.tools.ant.SubBuildListener
    public void subBuildStarted(BuildEvent event) {
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(">> TARGET STARTED -- ");
        stringBuffer.append(event.getTarget());
        log(stringBuffer.toString(), 4);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(StringUtils.LINE_SEP);
        stringBuffer2.append(event.getTarget().getName());
        stringBuffer2.append(":");
        log(stringBuffer2.toString(), 2);
        this.targetStartTime = System.currentTimeMillis();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<< TARGET FINISHED -- ");
        stringBuffer.append(event.getTarget());
        log(stringBuffer.toString(), 4);
        String time = formatTime(System.currentTimeMillis() - this.targetStartTime);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(event.getTarget());
        stringBuffer2.append(":  duration ");
        stringBuffer2.append(time);
        log(stringBuffer2.toString(), 3);
        flush();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(">>> TASK STARTED -- ");
        stringBuffer.append(event.getTask());
        log(stringBuffer.toString(), 4);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent event) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<<< TASK FINISHED -- ");
        stringBuffer.append(event.getTask());
        log(stringBuffer.toString(), 4);
        flush();
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent event) {
        log("--- MESSAGE LOGGED", 4);
        StringBuffer buf = new StringBuffer();
        if (event.getTask() != null) {
            String name = event.getTask().getTaskName();
            if (!this.emacsMode) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("[");
                stringBuffer.append(name);
                stringBuffer.append("] ");
                String label = stringBuffer.toString();
                int size = 12 - label.length();
                for (int i = 0; i < size; i++) {
                    buf.append(" ");
                }
                buf.append(label);
            }
        }
        String name2 = event.getMessage();
        buf.append(name2);
        log(buf.toString(), event.getPriority());
    }

    private void log(String mesg, int level) {
        PrintStream printStream;
        if (this.record && level <= this.loglevel && (printStream = this.out) != null) {
            printStream.println(mesg);
        }
    }

    private void flush() {
        PrintStream printStream;
        if (this.record && (printStream = this.out) != null) {
            printStream.flush();
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int level) {
        if (level >= 0 && level <= 4) {
            this.loglevel = level;
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream output) {
        closeFile();
        this.out = output;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean emacsMode) {
        this.emacsMode = emacsMode;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream err) {
        setOutputPrintStream(err);
    }

    private static String formatTime(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        if (minutes > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(Long.toString(minutes));
            stringBuffer.append(" minute");
            stringBuffer.append(minutes == 1 ? " " : "s ");
            stringBuffer.append(Long.toString(seconds % 60));
            stringBuffer.append(" second");
            stringBuffer.append(seconds % 60 != 1 ? "s" : "");
            return stringBuffer.toString();
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append(Long.toString(seconds));
        stringBuffer2.append(" second");
        stringBuffer2.append(seconds % 60 != 1 ? "s" : "");
        return stringBuffer2.toString();
    }

    public void setProject(Project project) {
        this.project = project;
        if (project != null) {
            project.addBuildListener(this);
        }
    }

    public void cleanup() {
        closeFile();
        Project project = this.project;
        if (project != null) {
            project.removeBuildListener(this);
        }
        this.project = null;
    }

    void openFile(boolean append) throws BuildException {
        openFileImpl(append);
    }

    void closeFile() {
        PrintStream printStream = this.out;
        if (printStream != null) {
            printStream.close();
            this.out = null;
        }
    }

    void reopenFile() throws BuildException {
        openFileImpl(true);
    }

    private void openFileImpl(boolean append) throws BuildException {
        if (this.out == null) {
            try {
                this.out = new PrintStream(new FileOutputStream(this.filename, append));
            } catch (IOException ioe) {
                throw new BuildException("Problems opening file using a recorder entry", ioe);
            }
        }
    }
}
