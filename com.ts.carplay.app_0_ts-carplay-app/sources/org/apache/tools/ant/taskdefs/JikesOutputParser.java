package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class JikesOutputParser implements ExecuteStreamHandler {
    protected BufferedReader br;
    protected boolean emacsMode;
    protected int errors;
    protected Task task;
    protected int warnings;
    protected boolean errorFlag = false;
    protected boolean error = false;

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessInputStream(OutputStream os) {
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessErrorStream(InputStream is) {
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void setProcessOutputStream(InputStream is) throws IOException {
        this.br = new BufferedReader(new InputStreamReader(is));
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void start() throws IOException {
        parseOutput(this.br);
    }

    @Override // org.apache.tools.ant.taskdefs.ExecuteStreamHandler
    public void stop() {
    }

    protected JikesOutputParser(Task task, boolean emacsMode) {
        System.err.println("As of Ant 1.2 released in October 2000, the JikesOutputParser class");
        System.err.println("is considered to be dead code by the Ant developers and is unmaintained.");
        System.err.println("Don't use it!");
        this.task = task;
        this.emacsMode = emacsMode;
    }

    protected void parseOutput(BufferedReader reader) throws IOException {
        if (this.emacsMode) {
            parseEmacsOutput(reader);
        } else {
            parseStandardOutput(reader);
        }
    }

    private void parseStandardOutput(BufferedReader reader) throws IOException {
        while (true) {
            String line = reader.readLine();
            if (line != null) {
                String lower = line.toLowerCase();
                if (!line.trim().equals("")) {
                    if (lower.indexOf("error") != -1) {
                        setError(true);
                    } else if (lower.indexOf("warning") != -1) {
                        setError(false);
                    } else if (this.emacsMode) {
                        setError(true);
                    }
                    log(line);
                }
            } else {
                return;
            }
        }
    }

    private void parseEmacsOutput(BufferedReader reader) throws IOException {
        parseStandardOutput(reader);
    }

    private void setError(boolean err) {
        this.error = err;
        if (err) {
            this.errorFlag = true;
        }
    }

    private void log(String str) {
        if (!this.emacsMode) {
            this.task.log("", !this.error ? 1 : 0);
        }
        this.task.log(str, !this.error ? 1 : 0);
    }

    protected boolean getErrorFlag() {
        return this.errorFlag;
    }
}
