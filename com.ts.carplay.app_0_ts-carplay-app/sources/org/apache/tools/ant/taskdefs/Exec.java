package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;

/* JADX INFO: loaded from: classes.dex */
public class Exec extends Task {
    private String command;
    private File dir;
    private String os;
    private String out;
    protected PrintWriter fos = null;
    private boolean failOnError = false;

    public Exec() {
        System.err.println("As of Ant 1.2 released in October 2000, the Exec class");
        System.err.println("is considered to be dead code by the Ant developers and is unmaintained.");
        System.err.println("Don't use it!");
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        run(this.command);
    }

    protected int run(String command) throws BuildException {
        int err = -1;
        String myos = System.getProperty("os.name");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Myos = ");
        stringBuffer.append(myos);
        log(stringBuffer.toString(), 3);
        String str = this.os;
        if (str != null && str.indexOf(myos) < 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Not found in ");
            stringBuffer2.append(this.os);
            log(stringBuffer2.toString(), 3);
            return 0;
        }
        if (this.dir == null) {
            this.dir = getProject().getBaseDir();
        }
        String ant = null;
        if (myos.toLowerCase().indexOf("windows") >= 0) {
            if (!this.dir.equals(getProject().resolveFile("."))) {
                if (myos.toLowerCase().indexOf("nt") >= 0) {
                    StringBuffer stringBuffer3 = new StringBuffer();
                    stringBuffer3.append("cmd /c cd ");
                    stringBuffer3.append(this.dir);
                    stringBuffer3.append(" && ");
                    stringBuffer3.append(command);
                    command = stringBuffer3.toString();
                } else {
                    ant = getProject().getProperty("ant.home");
                    if (ant == null) {
                        throw new BuildException("Property 'ant.home' not found", getLocation());
                    }
                    Project project = getProject();
                    StringBuffer stringBuffer4 = new StringBuffer();
                    stringBuffer4.append(ant);
                    stringBuffer4.append("/bin/antRun.bat");
                    String antRun = project.resolveFile(stringBuffer4.toString()).toString();
                    StringBuffer stringBuffer5 = new StringBuffer();
                    stringBuffer5.append(antRun);
                    stringBuffer5.append(" ");
                    stringBuffer5.append(this.dir);
                    stringBuffer5.append(" ");
                    stringBuffer5.append(command);
                    command = stringBuffer5.toString();
                }
            }
        } else {
            ant = getProject().getProperty("ant.home");
            if (ant == null) {
                throw new BuildException("Property 'ant.home' not found", getLocation());
            }
            Project project2 = getProject();
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(ant);
            stringBuffer6.append("/bin/antRun");
            String antRun2 = project2.resolveFile(stringBuffer6.toString()).toString();
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append(antRun2);
            stringBuffer7.append(" ");
            stringBuffer7.append(this.dir);
            stringBuffer7.append(" ");
            stringBuffer7.append(command);
            command = stringBuffer7.toString();
        }
        try {
            try {
                log(command, 3);
                Process proc = Runtime.getRuntime().exec(command);
                try {
                    if (this.out != null) {
                        this.fos = new PrintWriter(new FileWriter(this.out));
                        StringBuffer stringBuffer8 = new StringBuffer();
                        stringBuffer8.append("Output redirected to ");
                        stringBuffer8.append(this.out);
                        log(stringBuffer8.toString(), 3);
                    }
                    StreamPumper inputPumper = new StreamPumper(proc.getInputStream(), 2);
                    StreamPumper errorPumper = new StreamPumper(proc.getErrorStream(), 1);
                    inputPumper.start();
                    errorPumper.start();
                    proc.waitFor();
                    inputPumper.join();
                    errorPumper.join();
                    proc.destroy();
                    logFlush();
                    err = proc.exitValue();
                    if (err != 0) {
                        if (this.failOnError) {
                            StringBuffer stringBuffer9 = new StringBuffer();
                            stringBuffer9.append("Exec returned: ");
                            stringBuffer9.append(err);
                            throw new BuildException(stringBuffer9.toString(), getLocation());
                        }
                        StringBuffer stringBuffer10 = new StringBuffer();
                        stringBuffer10.append("Result: ");
                        stringBuffer10.append(err);
                        log(stringBuffer10.toString(), 0);
                    }
                } catch (InterruptedException e) {
                }
            } catch (InterruptedException e2) {
            }
            return err;
        } catch (IOException ioe) {
            StringBuffer stringBuffer11 = new StringBuffer();
            stringBuffer11.append("Error exec: ");
            stringBuffer11.append(command);
            throw new BuildException(stringBuffer11.toString(), ioe, getLocation());
        }
    }

    public void setDir(String d) {
        this.dir = getProject().resolveFile(d);
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setOutput(String out) {
        this.out = out;
    }

    public void setFailonerror(boolean fail) {
        this.failOnError = fail;
    }

    protected void outputLog(String line, int messageLevel) {
        PrintWriter printWriter = this.fos;
        if (printWriter == null) {
            log(line, messageLevel);
        } else {
            printWriter.println(line);
        }
    }

    protected void logFlush() {
        PrintWriter printWriter = this.fos;
        if (printWriter != null) {
            printWriter.close();
        }
    }

    class StreamPumper extends Thread {
        private BufferedReader din;
        private int messageLevel;
        private boolean endOfStream = false;
        private int SLEEP_TIME = 5;

        public StreamPumper(InputStream is, int messageLevel) {
            this.din = new BufferedReader(new InputStreamReader(is));
            this.messageLevel = messageLevel;
        }

        public void pumpStream() throws IOException {
            if (!this.endOfStream) {
                String line = this.din.readLine();
                if (line != null) {
                    Exec.this.outputLog(line, this.messageLevel);
                } else {
                    this.endOfStream = true;
                }
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!this.endOfStream) {
                try {
                    try {
                        pumpStream();
                        Thread.sleep(this.SLEEP_TIME);
                    } catch (IOException e) {
                        return;
                    }
                } catch (InterruptedException e2) {
                }
            }
            this.din.close();
        }
    }
}
