package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.RedirectorElement;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class ExecTask extends Task {
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private File dir;
    private File error;
    private String executable;
    private File input;
    private String inputString;
    private String os;
    private File output;
    protected RedirectorElement redirectorElement;
    private String resultProperty;
    protected boolean failOnError = false;
    protected boolean newEnvironment = false;
    private Long timeout = null;
    private Environment env = new Environment();
    protected Commandline cmdl = new Commandline();
    private boolean failIfExecFails = true;
    private boolean resolveExecutable = false;
    private boolean searchPath = false;
    private boolean spawn = false;
    private boolean incompatibleWithSpawn = false;
    protected Redirector redirector = new Redirector((Task) this);
    private boolean vmLauncher = true;

    public void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

    public void setTimeout(Long value) {
        this.timeout = value;
        this.incompatibleWithSpawn = true;
    }

    public void setTimeout(Integer value) {
        setTimeout(value == null ? null : new Long(value.intValue()));
    }

    public void setExecutable(String value) {
        this.executable = value;
        this.cmdl.setExecutable(value);
    }

    public void setDir(File d) {
        this.dir = d;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setCommand(Commandline cmdl) {
        log("The command attribute is deprecated. Please use the executable attribute and nested arg elements.", 1);
        this.cmdl = cmdl;
    }

    public void setOutput(File out) {
        this.output = out;
        this.incompatibleWithSpawn = true;
    }

    public void setInput(File input) {
        if (this.inputString != null) {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
        this.input = input;
        this.incompatibleWithSpawn = true;
    }

    public void setInputString(String inputString) {
        if (this.input != null) {
            throw new BuildException("The \"input\" and \"inputstring\" attributes cannot both be specified");
        }
        this.inputString = inputString;
        this.incompatibleWithSpawn = true;
    }

    public void setLogError(boolean logError) {
        this.redirector.setLogError(logError);
        this.incompatibleWithSpawn |= logError;
    }

    public void setError(File error) {
        this.error = error;
        this.incompatibleWithSpawn = true;
    }

    public void setOutputproperty(String outputProp) {
        this.redirector.setOutputProperty(outputProp);
        this.incompatibleWithSpawn = true;
    }

    public void setErrorProperty(String errorProperty) {
        this.redirector.setErrorProperty(errorProperty);
        this.incompatibleWithSpawn = true;
    }

    public void setFailonerror(boolean fail) {
        this.failOnError = fail;
        this.incompatibleWithSpawn |= fail;
    }

    public void setNewenvironment(boolean newenv) {
        this.newEnvironment = newenv;
    }

    public void setResolveExecutable(boolean resolveExecutable) {
        this.resolveExecutable = resolveExecutable;
    }

    public void setSearchPath(boolean searchPath) {
        this.searchPath = searchPath;
    }

    public boolean getResolveExecutable() {
        return this.resolveExecutable;
    }

    public void addEnv(Environment.Variable var) {
        this.env.addVariable(var);
    }

    public Commandline.Argument createArg() {
        return this.cmdl.createArgument();
    }

    public void setResultProperty(String resultProperty) {
        this.resultProperty = resultProperty;
        this.incompatibleWithSpawn = true;
    }

    protected void maybeSetResultPropertyValue(int result) {
        if (this.resultProperty != null) {
            String res = Integer.toString(result);
            getProject().setNewProperty(this.resultProperty, res);
        }
    }

    public void setFailIfExecutionFails(boolean flag) {
        this.failIfExecFails = flag;
        this.incompatibleWithSpawn = true;
    }

    public void setAppend(boolean append) {
        this.redirector.setAppend(append);
        this.incompatibleWithSpawn = true;
    }

    public void addConfiguredRedirector(RedirectorElement redirectorElement) {
        if (this.redirectorElement != null) {
            throw new BuildException("cannot have > 1 nested <redirector>s");
        }
        this.redirectorElement = redirectorElement;
        this.incompatibleWithSpawn = true;
    }

    protected String resolveExecutable(String exec, boolean mustSearchPath) {
        if (!this.resolveExecutable) {
            return exec;
        }
        File executableFile = getProject().resolveFile(exec);
        if (executableFile.exists()) {
            return executableFile.getAbsolutePath();
        }
        File file = this.dir;
        if (file != null) {
            File executableFile2 = FILE_UTILS.resolveFile(file, exec);
            if (executableFile2.exists()) {
                return executableFile2.getAbsolutePath();
            }
        }
        if (mustSearchPath) {
            Path p = null;
            String[] environment = this.env.getVariables();
            if (environment != null) {
                int i = 0;
                while (true) {
                    if (i >= environment.length) {
                        break;
                    }
                    if (!isPath(environment[i])) {
                        i++;
                    } else {
                        p = new Path(getProject(), environment[i].substring(5));
                        break;
                    }
                }
            }
            if (p == null) {
                Vector envVars = Execute.getProcEnvironment();
                Enumeration e = envVars.elements();
                while (true) {
                    if (!e.hasMoreElements()) {
                        break;
                    }
                    String line = (String) e.nextElement();
                    if (isPath(line)) {
                        p = new Path(getProject(), line.substring(5));
                        break;
                    }
                }
            }
            if (p != null) {
                String[] dirs = p.list();
                for (String str : dirs) {
                    File executableFile3 = FILE_UTILS.resolveFile(new File(str), exec);
                    if (executableFile3.exists()) {
                        return executableFile3.getAbsolutePath();
                    }
                }
            }
        }
        return exec;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (!isValidOs()) {
            return;
        }
        File savedDir = this.dir;
        this.cmdl.setExecutable(resolveExecutable(this.executable, this.searchPath));
        checkConfiguration();
        try {
            runExec(prepareExec());
        } finally {
            this.dir = savedDir;
        }
    }

    protected void checkConfiguration() throws BuildException {
        if (this.cmdl.getExecutable() == null) {
            throw new BuildException("no executable specified", getLocation());
        }
        File file = this.dir;
        if (file != null && !file.exists()) {
            throw new BuildException("The directory you specified does not exist");
        }
        File file2 = this.dir;
        if (file2 != null && !file2.isDirectory()) {
            throw new BuildException("The directory you specified is not a directory");
        }
        if (this.spawn && this.incompatibleWithSpawn) {
            getProject().log("spawn does not allow attributes related to input, output, error, result", 0);
            getProject().log("spawn also does not allow timeout", 0);
            getProject().log("finally, spawn is not compatible with a nested I/O <redirector>", 0);
            throw new BuildException("You have used an attribute or nested element which is not compatible with spawn");
        }
        setupRedirector();
    }

    protected void setupRedirector() {
        this.redirector.setInput(this.input);
        this.redirector.setInputString(this.inputString);
        this.redirector.setOutput(this.output);
        this.redirector.setError(this.error);
    }

    protected boolean isValidOs() {
        String myos = System.getProperty("os.name");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Current OS is ");
        stringBuffer.append(myos);
        log(stringBuffer.toString(), 3);
        String str = this.os;
        if (str != null && str.indexOf(myos) < 0) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("This OS, ");
            stringBuffer2.append(myos);
            stringBuffer2.append(" was not found in the specified list of valid OSes: ");
            stringBuffer2.append(this.os);
            log(stringBuffer2.toString(), 3);
            return false;
        }
        return true;
    }

    public void setVMLauncher(boolean vmLauncher) {
        this.vmLauncher = vmLauncher;
    }

    protected Execute prepareExec() throws BuildException {
        if (this.dir == null) {
            this.dir = getProject().getBaseDir();
        }
        RedirectorElement redirectorElement = this.redirectorElement;
        if (redirectorElement != null) {
            redirectorElement.configure(this.redirector);
        }
        Execute exe = new Execute(createHandler(), createWatchdog());
        exe.setAntRun(getProject());
        exe.setWorkingDirectory(this.dir);
        exe.setVMLauncher(this.vmLauncher);
        exe.setSpawn(this.spawn);
        String[] environment = this.env.getVariables();
        if (environment != null) {
            for (String str : environment) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Setting environment variable: ");
                stringBuffer.append(str);
                log(stringBuffer.toString(), 3);
            }
        }
        exe.setNewenvironment(this.newEnvironment);
        exe.setEnvironment(environment);
        return exe;
    }

    protected final void runExecute(Execute exe) throws Throwable {
        if (!this.spawn) {
            int returnCode = exe.execute();
            if (exe.killedProcess()) {
                if (!this.failOnError) {
                    log("Timeout: killed the sub-process", 1);
                } else {
                    throw new BuildException("Timeout: killed the sub-process");
                }
            }
            maybeSetResultPropertyValue(returnCode);
            this.redirector.complete();
            if (Execute.isFailure(returnCode)) {
                if (this.failOnError) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(getTaskType());
                    stringBuffer.append(" returned: ");
                    stringBuffer.append(returnCode);
                    throw new BuildException(stringBuffer.toString(), getLocation());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Result: ");
                stringBuffer2.append(returnCode);
                log(stringBuffer2.toString(), 0);
                return;
            }
            return;
        }
        exe.spawn();
    }

    protected void runExec(Execute exe) throws Throwable {
        log(this.cmdl.describeCommand(), 3);
        exe.setCommandline(this.cmdl.getCommandline());
        try {
            runExecute(exe);
            logFlush();
        } catch (IOException e) {
            try {
                if (this.failIfExecFails) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Execute failed: ");
                    stringBuffer.append(e.toString());
                    throw new BuildException(stringBuffer.toString(), e, getLocation());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Execute failed: ");
                stringBuffer2.append(e.toString());
                log(stringBuffer2.toString(), 0);
                logFlush();
            } catch (Throwable th) {
                th = th;
                logFlush();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            logFlush();
            throw th;
        }
    }

    protected ExecuteStreamHandler createHandler() throws BuildException {
        return this.redirector.createHandler();
    }

    protected ExecuteWatchdog createWatchdog() throws BuildException {
        Long l = this.timeout;
        if (l == null) {
            return null;
        }
        return new ExecuteWatchdog(l.longValue());
    }

    protected void logFlush() {
    }

    private boolean isPath(String line) {
        return line.startsWith("PATH=") || line.startsWith("Path=");
    }
}
