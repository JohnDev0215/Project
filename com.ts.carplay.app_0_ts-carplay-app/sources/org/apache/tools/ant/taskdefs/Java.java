package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ExitException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Assertions;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Environment;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Permissions;
import org.apache.tools.ant.types.PropertySet;
import org.apache.tools.ant.types.RedirectorElement;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.util.KeepAliveInputStream;

/* JADX INFO: loaded from: classes.dex */
public class Java extends Task {
    private File error;
    private File input;
    private String inputString;
    private File output;
    protected RedirectorElement redirectorElement;
    private String resultProperty;
    private CommandlineJava cmdl = new CommandlineJava();
    private Environment env = new Environment();
    private boolean fork = false;
    private boolean newEnvironment = false;
    private File dir = null;
    private boolean failOnError = false;
    private Long timeout = null;
    protected Redirector redirector = new Redirector((Task) this);
    private Permissions perm = null;
    private boolean spawn = false;
    private boolean incompatibleWithSpawn = false;

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        File savedDir = this.dir;
        Permissions savedPermissions = this.perm;
        try {
            int err = executeJava();
            if (err != 0) {
                if (this.failOnError) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Java returned: ");
                    stringBuffer.append(err);
                    throw new BuildException(stringBuffer.toString(), getLocation());
                }
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Java Result: ");
                stringBuffer2.append(err);
                log(stringBuffer2.toString(), 0);
            }
            maybeSetResultPropertyValue(err);
        } finally {
            this.dir = savedDir;
            this.perm = savedPermissions;
        }
    }

    public int executeJava() throws BuildException {
        String classname = this.cmdl.getClassname();
        if (classname == null && this.cmdl.getJar() == null) {
            throw new BuildException("Classname must not be null.");
        }
        if (!this.fork && this.cmdl.getJar() != null) {
            throw new BuildException("Cannot execute a jar in non-forked mode. Please set fork='true'. ");
        }
        if (this.spawn && !this.fork) {
            throw new BuildException("Cannot spawn a java process in non-forked mode. Please set fork='true'. ");
        }
        if (this.spawn && this.incompatibleWithSpawn) {
            getProject().log("spawn does not allow attributes related to input, output, error, result", 0);
            getProject().log("spawn also does not allow timeout", 0);
            getProject().log("finally, spawn is not compatible with a nested I/O <redirector>", 0);
            throw new BuildException("You have used an attribute or nested element which is not compatible with spawn");
        }
        if (this.cmdl.getAssertions() != null && !this.fork) {
            log("Assertion statements are currently ignored in non-forked mode");
        }
        if (this.fork) {
            if (this.perm != null) {
                log("Permissions can not be set this way in forked mode.", 1);
            }
            log(this.cmdl.describeCommand(), 3);
        } else {
            if (this.cmdl.getVmCommand().size() > 1) {
                log("JVM args ignored when same JVM is used.", 1);
            }
            if (this.dir != null) {
                log("Working directory ignored when same JVM is used.", 1);
            }
            if (this.newEnvironment || this.env.getVariables() != null) {
                log("Changes to environment variables are ignored when same JVM is used.", 1);
            }
            if (this.cmdl.getBootclasspath() != null) {
                log("bootclasspath ignored when same JVM is used.", 1);
            }
            if (this.perm == null && this.failOnError) {
                this.perm = new Permissions(true);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("running ");
                stringBuffer.append(this.cmdl.getClassname());
                stringBuffer.append(" with default permissions (exit forbidden)");
                log(stringBuffer.toString(), 3);
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Running in same VM ");
            stringBuffer2.append(this.cmdl.describeJavaCommand());
            log(stringBuffer2.toString(), 3);
        }
        setupRedirector();
        try {
            if (this.fork) {
                if (!this.spawn) {
                    return fork(this.cmdl.getCommandline());
                }
                spawn(this.cmdl.getCommandline());
                return 0;
            }
            try {
                run(this.cmdl);
                return 0;
            } catch (ExitException ex) {
                return ex.getStatus();
            }
        } catch (BuildException e) {
            if (e.getLocation() == null && getLocation() != null) {
                e.setLocation(getLocation());
            }
            if (this.failOnError) {
                throw e;
            }
            log(e);
            return 0;
        } catch (Throwable t) {
            if (this.failOnError) {
                throw new BuildException(t, getLocation());
            }
            log(t);
            return 0;
        }
    }

    public void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

    public void setClasspath(Path s) {
        createClasspath().append(s);
    }

    public Path createClasspath() {
        return this.cmdl.createClasspath(getProject()).createPath();
    }

    public Path createBootclasspath() {
        return this.cmdl.createBootclasspath(getProject()).createPath();
    }

    public Permissions createPermissions() {
        if (this.perm == null) {
            this.perm = new Permissions();
        }
        return this.perm;
    }

    public void setClasspathRef(Reference r) {
        createClasspath().setRefid(r);
    }

    public void setJar(File jarfile) throws BuildException {
        if (this.cmdl.getClassname() != null) {
            throw new BuildException("Cannot use 'jar' and 'classname' attributes in same command.");
        }
        this.cmdl.setJar(jarfile.getAbsolutePath());
    }

    public void setClassname(String s) throws BuildException {
        if (this.cmdl.getJar() != null) {
            throw new BuildException("Cannot use 'jar' and 'classname' attributes in same command");
        }
        this.cmdl.setClassname(s);
    }

    public void setArgs(String s) {
        log("The args attribute is deprecated. Please use nested arg elements.", 1);
        this.cmdl.createArgument().setLine(s);
    }

    public Commandline.Argument createArg() {
        return this.cmdl.createArgument();
    }

    public void setResultProperty(String resultProperty) {
        this.resultProperty = resultProperty;
        this.incompatibleWithSpawn = true;
    }

    protected void maybeSetResultPropertyValue(int result) {
        String res = Integer.toString(result);
        if (this.resultProperty != null) {
            getProject().setNewProperty(this.resultProperty, res);
        }
    }

    public void setFork(boolean s) {
        this.fork = s;
    }

    public void setJvmargs(String s) {
        log("The jvmargs attribute is deprecated. Please use nested jvmarg elements.", 1);
        this.cmdl.createVmArgument().setLine(s);
    }

    public Commandline.Argument createJvmarg() {
        return this.cmdl.createVmArgument();
    }

    public void setJvm(String s) {
        this.cmdl.setVm(s);
    }

    public void addSysproperty(Environment.Variable sysp) {
        this.cmdl.addSysproperty(sysp);
    }

    public void addSyspropertyset(PropertySet sysp) {
        this.cmdl.addSyspropertyset(sysp);
    }

    public void setFailonerror(boolean fail) {
        this.failOnError = fail;
        this.incompatibleWithSpawn |= fail;
    }

    public void setDir(File d) {
        this.dir = d;
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

    public void setMaxmemory(String max) {
        this.cmdl.setMaxmemory(max);
    }

    public void setJVMVersion(String value) {
        this.cmdl.setVmversion(value);
    }

    public void addEnv(Environment.Variable var) {
        this.env.addVariable(var);
    }

    public void setNewenvironment(boolean newenv) {
        this.newEnvironment = newenv;
    }

    public void setAppend(boolean append) {
        this.redirector.setAppend(append);
        this.incompatibleWithSpawn = true;
    }

    public void setTimeout(Long value) {
        this.timeout = value;
        this.incompatibleWithSpawn |= value != null;
    }

    public void addAssertions(Assertions asserts) {
        if (this.cmdl.getAssertions() != null) {
            throw new BuildException("Only one assertion declaration is allowed");
        }
        this.cmdl.setAssertions(asserts);
    }

    public void addConfiguredRedirector(RedirectorElement redirectorElement) {
        if (this.redirectorElement != null) {
            throw new BuildException("cannot have > 1 nested <redirector>s");
        }
        this.redirectorElement = redirectorElement;
        this.incompatibleWithSpawn = true;
    }

    @Override // org.apache.tools.ant.Task
    protected void handleOutput(String output) {
        if (this.redirector.getOutputStream() != null) {
            this.redirector.handleOutput(output);
        } else {
            super.handleOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] buffer, int offset, int length) throws IOException {
        return this.redirector.handleInput(buffer, offset, length);
    }

    @Override // org.apache.tools.ant.Task
    protected void handleFlush(String output) {
        if (this.redirector.getOutputStream() != null) {
            this.redirector.handleFlush(output);
        } else {
            super.handleFlush(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    protected void handleErrorOutput(String output) {
        if (this.redirector.getErrorStream() != null) {
            this.redirector.handleErrorOutput(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    @Override // org.apache.tools.ant.Task
    protected void handleErrorFlush(String output) {
        if (this.redirector.getErrorStream() != null) {
            this.redirector.handleErrorFlush(output);
        } else {
            super.handleErrorOutput(output);
        }
    }

    protected void setupRedirector() {
        this.redirector.setInput(this.input);
        this.redirector.setInputString(this.inputString);
        this.redirector.setOutput(this.output);
        this.redirector.setError(this.error);
        RedirectorElement redirectorElement = this.redirectorElement;
        if (redirectorElement != null) {
            redirectorElement.configure(this.redirector);
        }
        if (!this.spawn && this.input == null && this.inputString == null) {
            this.redirector.setInputStream(new KeepAliveInputStream(getProject().getDefaultInputStream()));
        }
    }

    private void run(CommandlineJava command) throws BuildException {
        try {
            ExecuteJava exe = new ExecuteJava();
            exe.setJavaCommand(command.getJavaCommand());
            exe.setClasspath(command.getClasspath());
            exe.setSystemProperties(command.getSystemProperties());
            exe.setPermissions(this.perm);
            exe.setTimeout(this.timeout);
            this.redirector.createStreams();
            exe.execute(getProject());
            this.redirector.complete();
            if (exe.killedProcess()) {
                throw new BuildException("Timeout: killed the sub-process");
            }
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }

    private int fork(String[] command) throws Throwable {
        Execute exe = new Execute(this.redirector.createHandler(), createWatchdog());
        exe.setAntRun(getProject());
        File file = this.dir;
        if (file == null) {
            this.dir = getProject().getBaseDir();
        } else if (!file.exists() || !this.dir.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.dir.getAbsolutePath());
            stringBuffer.append(" is not a valid directory");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        exe.setWorkingDirectory(this.dir);
        String[] environment = this.env.getVariables();
        if (environment != null) {
            for (String str : environment) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Setting environment variable: ");
                stringBuffer2.append(str);
                log(stringBuffer2.toString(), 3);
            }
        }
        exe.setNewenvironment(this.newEnvironment);
        exe.setEnvironment(environment);
        exe.setCommandline(command);
        try {
            int rc = exe.execute();
            this.redirector.complete();
            if (exe.killedProcess()) {
                throw new BuildException("Timeout: killed the sub-process");
            }
            return rc;
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    private void spawn(String[] command) throws BuildException {
        Execute exe = new Execute();
        exe.setAntRun(getProject());
        File file = this.dir;
        if (file == null) {
            this.dir = getProject().getBaseDir();
        } else if (!file.exists() || !this.dir.isDirectory()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.dir.getAbsolutePath());
            stringBuffer.append(" is not a valid directory");
            throw new BuildException(stringBuffer.toString(), getLocation());
        }
        exe.setWorkingDirectory(this.dir);
        String[] environment = this.env.getVariables();
        if (environment != null) {
            for (String str : environment) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("Setting environment variable: ");
                stringBuffer2.append(str);
                log(stringBuffer2.toString(), 3);
            }
        }
        exe.setNewenvironment(this.newEnvironment);
        exe.setEnvironment(environment);
        exe.setCommandline(command);
        try {
            exe.spawn();
        } catch (IOException e) {
            throw new BuildException(e, getLocation());
        }
    }

    protected void run(String classname, Vector args) throws BuildException {
        CommandlineJava cmdj = new CommandlineJava();
        cmdj.setClassname(classname);
        for (int i = 0; i < args.size(); i++) {
            cmdj.createArgument().setValue((String) args.elementAt(i));
        }
        run(cmdj);
    }

    public void clearArgs() {
        this.cmdl.clearJavaArgs();
    }

    protected ExecuteWatchdog createWatchdog() throws BuildException {
        Long l = this.timeout;
        if (l == null) {
            return null;
        }
        return new ExecuteWatchdog(l.longValue());
    }

    private void log(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter w = new PrintWriter(sw);
        t.printStackTrace(w);
        w.close();
        log(sw.toString(), 0);
    }

    public CommandlineJava getCommandLine() {
        return this.cmdl;
    }

    public CommandlineJava.SysProperties getSysProperties() {
        return this.cmdl.getSystemProperties();
    }
}
