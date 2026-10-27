package org.apache.tools.ant.taskdefs;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractCvsTask extends Task {
    private static final String DEFAULT_COMMAND = "checkout";
    public static final int DEFAULT_COMPRESSION_LEVEL = 3;
    private static final int MAXIMUM_COMRESSION_LEVEL = 9;
    private String cvsPackage;
    private String cvsRoot;
    private String cvsRsh;
    private File dest;
    private File error;
    private OutputStream errorStream;
    private ExecuteStreamHandler executeStreamHandler;
    private File output;
    private OutputStream outputStream;
    private String tag;
    private Commandline cmd = new Commandline();
    private Vector vecCommandlines = new Vector();
    private String command = null;
    private boolean quiet = false;
    private boolean reallyquiet = false;
    private int compression = 0;
    private boolean noexec = false;
    private int port = 0;
    private File passFile = null;
    private boolean append = false;
    private boolean failOnError = false;

    public void setExecuteStreamHandler(ExecuteStreamHandler handler) {
        this.executeStreamHandler = handler;
    }

    protected ExecuteStreamHandler getExecuteStreamHandler() {
        if (this.executeStreamHandler == null) {
            setExecuteStreamHandler(new PumpStreamHandler(getOutputStream(), getErrorStream()));
        }
        return this.executeStreamHandler;
    }

    protected void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    protected OutputStream getOutputStream() {
        if (this.outputStream == null) {
            if (this.output != null) {
                try {
                    setOutputStream(new PrintStream(new BufferedOutputStream(new FileOutputStream(this.output.getPath(), this.append))));
                } catch (IOException e) {
                    throw new BuildException(e, getLocation());
                }
            } else {
                setOutputStream(new LogOutputStream((Task) this, 2));
            }
        }
        return this.outputStream;
    }

    protected void setErrorStream(OutputStream errorStream) {
        this.errorStream = errorStream;
    }

    protected OutputStream getErrorStream() {
        if (this.errorStream == null) {
            if (this.error != null) {
                try {
                    setErrorStream(new PrintStream(new BufferedOutputStream(new FileOutputStream(this.error.getPath(), this.append))));
                } catch (IOException e) {
                    throw new BuildException(e, getLocation());
                }
            } else {
                setErrorStream(new LogOutputStream((Task) this, 1));
            }
        }
        return this.errorStream;
    }

    protected void runCommand(Commandline commandline) throws Throwable {
        Environment environment = new Environment();
        if (this.port > 0) {
            Environment.Variable variable = new Environment.Variable();
            variable.setKey("CVS_CLIENT_PORT");
            variable.setValue(String.valueOf(this.port));
            environment.addVariable(variable);
        }
        if (this.passFile == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(System.getProperty("cygwin.user.home", System.getProperty("user.home")));
            stringBuffer.append(File.separatorChar);
            stringBuffer.append(".cvspass");
            File file = new File(stringBuffer.toString());
            if (file.exists()) {
                setPassfile(file);
            }
        }
        File file2 = this.passFile;
        if (file2 != null) {
            if (file2.isFile() && this.passFile.canRead()) {
                Environment.Variable variable2 = new Environment.Variable();
                variable2.setKey("CVS_PASSFILE");
                variable2.setValue(String.valueOf(this.passFile));
                environment.addVariable(variable2);
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Using cvs passfile: ");
                stringBuffer2.append(String.valueOf(this.passFile));
                log(stringBuffer2.toString(), 2);
            } else if (!this.passFile.canRead()) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("cvs passfile: ");
                stringBuffer3.append(String.valueOf(this.passFile));
                stringBuffer3.append(" ignored as it is not readable");
                log(stringBuffer3.toString(), 1);
            } else {
                StringBuffer stringBuffer4 = new StringBuffer();
                stringBuffer4.append("cvs passfile: ");
                stringBuffer4.append(String.valueOf(this.passFile));
                stringBuffer4.append(" ignored as it is not a file");
                log(stringBuffer4.toString(), 1);
            }
        }
        if (this.cvsRsh != null) {
            Environment.Variable variable3 = new Environment.Variable();
            variable3.setKey("CVS_RSH");
            variable3.setValue(String.valueOf(this.cvsRsh));
            environment.addVariable(variable3);
        }
        Execute execute = new Execute(getExecuteStreamHandler(), null);
        execute.setAntRun(getProject());
        if (this.dest == null) {
            this.dest = getProject().getBaseDir();
        }
        if (!this.dest.exists()) {
            this.dest.mkdirs();
        }
        execute.setWorkingDirectory(this.dest);
        execute.setCommandline(commandline.getCommandline());
        execute.setEnvironment(environment.getVariables());
        try {
            String strExecuteToString = executeToString(execute);
            log(strExecuteToString, 3);
            int iExecute = execute.execute();
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append("retCode=");
            stringBuffer5.append(iExecute);
            log(stringBuffer5.toString(), 4);
            if (this.failOnError && Execute.isFailure(iExecute)) {
                StringBuffer stringBuffer6 = new StringBuffer();
                stringBuffer6.append("cvs exited with error code ");
                stringBuffer6.append(iExecute);
                stringBuffer6.append(StringUtils.LINE_SEP);
                stringBuffer6.append("Command line was [");
                stringBuffer6.append(strExecuteToString);
                stringBuffer6.append("]");
                throw new BuildException(stringBuffer6.toString(), getLocation());
            }
        } catch (IOException e) {
            if (this.failOnError) {
                throw new BuildException(e, getLocation());
            }
            StringBuffer stringBuffer7 = new StringBuffer();
            stringBuffer7.append("Caught exception: ");
            stringBuffer7.append(e.getMessage());
            log(stringBuffer7.toString(), 1);
        } catch (BuildException e2) {
            e = e2;
            if (this.failOnError) {
                throw e;
            }
            Throwable exception = e.getException();
            if (exception != null) {
                e = exception;
            }
            StringBuffer stringBuffer8 = new StringBuffer();
            stringBuffer8.append("Caught exception: ");
            stringBuffer8.append(e.getMessage());
            log(stringBuffer8.toString(), 1);
        } catch (Exception e3) {
            if (this.failOnError) {
                throw new BuildException(e3, getLocation());
            }
            StringBuffer stringBuffer9 = new StringBuffer();
            stringBuffer9.append("Caught exception: ");
            stringBuffer9.append(e3.getMessage());
            log(stringBuffer9.toString(), 1);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        String savedCommand = getCommand();
        if (getCommand() == null && this.vecCommandlines.size() == 0) {
            setCommand(DEFAULT_COMMAND);
        }
        String c = getCommand();
        Commandline cloned = null;
        if (c != null) {
            cloned = (Commandline) this.cmd.clone();
            cloned.createArgument(true).setLine(c);
            addConfiguredCommandline(cloned, true);
        }
        for (int i = 0; i < this.vecCommandlines.size(); i++) {
            try {
                runCommand((Commandline) this.vecCommandlines.elementAt(i));
            } finally {
            }
        }
        if (cloned != null) {
            removeCommandline(cloned);
        }
        setCommand(savedCommand);
        OutputStream outputStream = this.outputStream;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
            }
        }
        OutputStream outputStream2 = this.errorStream;
        if (outputStream2 != null) {
            try {
                outputStream2.close();
            } catch (IOException e2) {
            }
        }
    }

    private String executeToString(Execute execute) {
        StringBuffer stringBuffer = new StringBuffer(Commandline.describeCommand(execute.getCommandline()));
        String newLine = StringUtils.LINE_SEP;
        String[] variableArray = execute.getEnvironment();
        if (variableArray != null) {
            stringBuffer.append(newLine);
            stringBuffer.append(newLine);
            stringBuffer.append("environment:");
            stringBuffer.append(newLine);
            for (String str : variableArray) {
                stringBuffer.append(newLine);
                stringBuffer.append("\t");
                stringBuffer.append(str);
            }
        }
        return stringBuffer.toString();
    }

    public void setCvsRoot(String root) {
        if (root != null && root.trim().equals("")) {
            root = null;
        }
        this.cvsRoot = root;
    }

    public String getCvsRoot() {
        return this.cvsRoot;
    }

    public void setCvsRsh(String rsh) {
        if (rsh != null && rsh.trim().equals("")) {
            rsh = null;
        }
        this.cvsRsh = rsh;
    }

    public String getCvsRsh() {
        return this.cvsRsh;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public void setPassfile(File passFile) {
        this.passFile = passFile;
    }

    public File getPassFile() {
        return this.passFile;
    }

    public void setDest(File dest) {
        this.dest = dest;
    }

    public File getDest() {
        return this.dest;
    }

    public void setPackage(String p) {
        this.cvsPackage = p;
    }

    public String getPackage() {
        return this.cvsPackage;
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String p) {
        if (p != null && p.trim().length() > 0) {
            this.tag = p;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-r");
            stringBuffer.append(p);
            addCommandArgument(stringBuffer.toString());
        }
    }

    public void addCommandArgument(String arg) {
        addCommandArgument(this.cmd, arg);
    }

    public void addCommandArgument(Commandline c, String arg) {
        c.createArgument().setValue(arg);
    }

    public void setDate(String p) {
        if (p != null && p.trim().length() > 0) {
            addCommandArgument("-D");
            addCommandArgument(p);
        }
    }

    public void setCommand(String c) {
        this.command = c;
    }

    public String getCommand() {
        return this.command;
    }

    public void setQuiet(boolean q) {
        this.quiet = q;
    }

    public void setReallyquiet(boolean q) {
        this.reallyquiet = q;
    }

    public void setNoexec(boolean ne) {
        this.noexec = ne;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public void setError(File error) {
        this.error = error;
    }

    public void setAppend(boolean value) {
        this.append = value;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    protected void configureCommandline(Commandline c) {
        if (c == null) {
            return;
        }
        c.setExecutable("cvs");
        if (this.cvsPackage != null) {
            c.createArgument().setLine(this.cvsPackage);
        }
        int i = this.compression;
        if (i > 0 && i <= 9) {
            Commandline.Argument argumentCreateArgument = c.createArgument(true);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-z");
            stringBuffer.append(this.compression);
            argumentCreateArgument.setValue(stringBuffer.toString());
        }
        if (this.quiet && !this.reallyquiet) {
            c.createArgument(true).setValue("-q");
        }
        if (this.reallyquiet) {
            c.createArgument(true).setValue("-Q");
        }
        if (this.noexec) {
            c.createArgument(true).setValue("-n");
        }
        if (this.cvsRoot != null) {
            Commandline.Argument argumentCreateArgument2 = c.createArgument(true);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("-d");
            stringBuffer2.append(this.cvsRoot);
            argumentCreateArgument2.setLine(stringBuffer2.toString());
        }
    }

    protected void removeCommandline(Commandline c) {
        this.vecCommandlines.removeElement(c);
    }

    public void addConfiguredCommandline(Commandline c) {
        addConfiguredCommandline(c, false);
    }

    public void addConfiguredCommandline(Commandline c, boolean insertAtStart) {
        if (c == null) {
            return;
        }
        configureCommandline(c);
        if (insertAtStart) {
            this.vecCommandlines.insertElementAt(c, 0);
        } else {
            this.vecCommandlines.addElement(c);
        }
    }

    public void setCompressionLevel(int level) {
        this.compression = level;
    }

    public void setCompression(boolean usecomp) {
        setCompressionLevel(usecomp ? 3 : 0);
    }
}
