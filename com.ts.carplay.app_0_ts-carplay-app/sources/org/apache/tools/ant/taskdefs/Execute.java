package org.apache.tools.ant.taskdefs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class Execute {
    public static final int INVALID = Integer.MAX_VALUE;
    static /* synthetic */ Class array$Ljava$lang$String;
    static /* synthetic */ Class class$java$io$File;
    static /* synthetic */ Class class$java$lang$Runtime;
    private static CommandLauncher shellLauncher;
    private static CommandLauncher vmLauncher;
    private String[] cmdl;
    private String[] env;
    private int exitValue;
    private boolean newEnvironment;
    private Project project;
    private boolean spawn;
    private ExecuteStreamHandler streamHandler;
    private boolean useVMLauncher;
    private ExecuteWatchdog watchdog;
    private File workingDirectory;
    private static String antWorkingDirectory = System.getProperty("user.dir");
    private static Vector procEnvironment = null;
    private static ProcessDestroyer processDestroyer = new ProcessDestroyer();

    static {
        CommandLauncher baseLauncher;
        CommandLauncher baseLauncher2;
        vmLauncher = null;
        shellLauncher = null;
        try {
            if (Os.isFamily("openvms")) {
                vmLauncher = new VmsCommandLauncher();
            } else if (!Os.isFamily("os/2")) {
                vmLauncher = new Java13CommandLauncher();
            }
            exc = null;
        } catch (NoSuchMethodException e) {
            exc = e;
        }
        if (Os.isFamily("mac") && !Os.isFamily("unix")) {
            shellLauncher = new MacCommandLauncher(new CommandLauncher());
            return;
        }
        if (Os.isFamily("os/2")) {
            shellLauncher = new OS2CommandLauncher(new CommandLauncher());
            return;
        }
        if (Os.isFamily("windows")) {
            if (System.getProperty("java.version").startsWith("1.1")) {
                baseLauncher2 = new Java11CommandLauncher();
            } else {
                baseLauncher2 = new CommandLauncher();
            }
            if (!Os.isFamily("win9x")) {
                shellLauncher = new WinNTCommandLauncher(baseLauncher2);
                return;
            } else {
                shellLauncher = new ScriptCommandLauncher("bin/antRun.bat", baseLauncher2);
                return;
            }
        }
        if (Os.isFamily("netware")) {
            if (System.getProperty("java.version").startsWith("1.1")) {
                baseLauncher = new Java11CommandLauncher();
            } else {
                baseLauncher = new CommandLauncher();
            }
            shellLauncher = new PerlScriptCommandLauncher("bin/antRun.pl", baseLauncher);
            return;
        }
        if (Os.isFamily("openvms")) {
            shellLauncher = vmLauncher;
        } else {
            shellLauncher = new ScriptCommandLauncher("bin/antRun", new CommandLauncher());
        }
    }

    public void setSpawn(boolean spawn) {
        this.spawn = spawn;
    }

    public static synchronized Vector getProcEnvironment() {
        BufferedReader in;
        if (procEnvironment != null) {
            return procEnvironment;
        }
        procEnvironment = new Vector();
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Execute exe = new Execute(new PumpStreamHandler(out));
            exe.setCommandline(getProcEnvCommand());
            exe.setNewenvironment(true);
            exe.execute();
            in = new BufferedReader(new StringReader(toString(out)));
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        if (Os.isFamily("openvms")) {
            Vector vectorAddVMSLogicals = addVMSLogicals(procEnvironment, in);
            procEnvironment = vectorAddVMSLogicals;
            return vectorAddVMSLogicals;
        }
        String var = null;
        String lineSep = System.getProperty("line.separator");
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            if (line.indexOf(61) == -1) {
                if (var == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(lineSep);
                    stringBuffer.append(line);
                    var = stringBuffer.toString();
                } else {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    stringBuffer2.append(var);
                    stringBuffer2.append(lineSep);
                    stringBuffer2.append(line);
                    var = stringBuffer2.toString();
                }
            } else {
                if (var != null) {
                    procEnvironment.addElement(var);
                }
                var = line;
            }
        }
        if (var != null) {
            procEnvironment.addElement(var);
        }
        return procEnvironment;
    }

    private static String[] getProcEnvCommand() {
        if (Os.isFamily("os/2")) {
            return new String[]{"cmd", "/c", "set"};
        }
        if (Os.isFamily("windows")) {
            if (Os.isFamily("win9x")) {
                return new String[]{"command.com", "/c", "set"};
            }
            return new String[]{"cmd", "/c", "set"};
        }
        if (Os.isFamily("z/os") || Os.isFamily("unix")) {
            String[] cmd = new String[1];
            if (new File("/bin/env").canRead()) {
                cmd[0] = "/bin/env";
            } else if (new File("/usr/bin/env").canRead()) {
                cmd[0] = "/usr/bin/env";
            } else {
                cmd[0] = "env";
            }
            return cmd;
        }
        if (Os.isFamily("netware") || Os.isFamily("os/400")) {
            return new String[]{"env"};
        }
        if (Os.isFamily("openvms")) {
            return new String[]{"show", "logical"};
        }
        return null;
    }

    public static String toString(ByteArrayOutputStream bos) {
        if (Os.isFamily("z/os")) {
            try {
                return bos.toString("Cp1047");
            } catch (UnsupportedEncodingException e) {
            }
        } else if (Os.isFamily("os/400")) {
            try {
                return bos.toString("Cp500");
            } catch (UnsupportedEncodingException e2) {
            }
        }
        return bos.toString();
    }

    public Execute() {
        this(new PumpStreamHandler(), null);
    }

    public Execute(ExecuteStreamHandler streamHandler) {
        this(streamHandler, null);
    }

    public Execute(ExecuteStreamHandler streamHandler, ExecuteWatchdog watchdog) {
        this.cmdl = null;
        this.env = null;
        this.exitValue = INVALID;
        this.workingDirectory = null;
        this.project = null;
        this.newEnvironment = false;
        this.useVMLauncher = true;
        this.spawn = false;
        setStreamHandler(streamHandler);
        this.watchdog = watchdog;
    }

    public void setStreamHandler(ExecuteStreamHandler streamHandler) {
        this.streamHandler = streamHandler;
    }

    public String[] getCommandline() {
        return this.cmdl;
    }

    public void setCommandline(String[] commandline) {
        this.cmdl = commandline;
    }

    public void setNewenvironment(boolean newenv) {
        this.newEnvironment = newenv;
    }

    public String[] getEnvironment() {
        if (this.env == null || this.newEnvironment) {
            return this.env;
        }
        return patchEnvironment();
    }

    public void setEnvironment(String[] env) {
        this.env = env;
    }

    public void setWorkingDirectory(File wd) {
        if (wd == null || wd.getAbsolutePath().equals(antWorkingDirectory)) {
            this.workingDirectory = null;
        } else {
            this.workingDirectory = wd;
        }
    }

    public void setAntRun(Project project) throws BuildException {
        this.project = project;
    }

    public void setVMLauncher(boolean useVMLauncher) {
        this.useVMLauncher = useVMLauncher;
    }

    public static Process launch(Project project, String[] command, String[] env, File dir, boolean useVM) throws IOException {
        CommandLauncher launcher = vmLauncher;
        if (launcher == null) {
            launcher = shellLauncher;
        }
        if (!useVM) {
            launcher = shellLauncher;
        }
        if (dir != null && !dir.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(dir);
            stringBuffer.append(" doesn't exist.");
            throw new BuildException(stringBuffer.toString());
        }
        return launcher.exec(project, command, env, dir);
    }

    public int execute() throws Throwable {
        File file = this.workingDirectory;
        if (file != null && !file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.workingDirectory);
            stringBuffer.append(" doesn't exist.");
            throw new BuildException(stringBuffer.toString());
        }
        Process process = launch(this.project, getCommandline(), getEnvironment(), this.workingDirectory, this.useVMLauncher);
        try {
            this.streamHandler.setProcessInputStream(process.getOutputStream());
            this.streamHandler.setProcessOutputStream(process.getInputStream());
            this.streamHandler.setProcessErrorStream(process.getErrorStream());
            this.streamHandler.start();
            try {
                processDestroyer.add(process);
                if (this.watchdog != null) {
                    this.watchdog.start(process);
                }
                waitFor(process);
                if (this.watchdog != null) {
                    this.watchdog.stop();
                }
                this.streamHandler.stop();
                closeStreams(process);
                if (this.watchdog != null) {
                    this.watchdog.checkException();
                }
                int exitValue = getExitValue();
                processDestroyer.remove(process);
                return exitValue;
            } catch (ThreadDeath t) {
                try {
                    process.destroy();
                    throw t;
                } catch (Throwable th) {
                    th = th;
                    processDestroyer.remove(process);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                processDestroyer.remove(process);
                throw th;
            }
        } catch (IOException e) {
            process.destroy();
            throw e;
        }
    }

    public void spawn() throws IOException {
        File file = this.workingDirectory;
        if (file != null && !file.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(this.workingDirectory);
            stringBuffer.append(" doesn't exist.");
            throw new BuildException(stringBuffer.toString());
        }
        Process process = launch(this.project, getCommandline(), getEnvironment(), this.workingDirectory, this.useVMLauncher);
        if (Os.isFamily("windows")) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                this.project.log("interruption in the sleep after having spawned a process", 3);
            }
        }
        OutputStream dummyOut = new OutputStream() { // from class: org.apache.tools.ant.taskdefs.Execute.1
            @Override // java.io.OutputStream
            public void write(int b) throws IOException {
            }
        };
        ExecuteStreamHandler streamHandler = new PumpStreamHandler(dummyOut);
        streamHandler.setProcessErrorStream(process.getErrorStream());
        streamHandler.setProcessOutputStream(process.getInputStream());
        streamHandler.start();
        process.getOutputStream().close();
        Project project = this.project;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("spawned process ");
        stringBuffer2.append(process.toString());
        project.log(stringBuffer2.toString(), 3);
    }

    protected void waitFor(Process process) {
        try {
            process.waitFor();
            setExitValue(process.exitValue());
        } catch (InterruptedException e) {
            process.destroy();
        }
    }

    protected void setExitValue(int value) {
        this.exitValue = value;
    }

    public int getExitValue() {
        return this.exitValue;
    }

    public static boolean isFailure(int exitValue) {
        return Os.isFamily("openvms") ? exitValue % 2 == 0 : exitValue != 0;
    }

    public boolean killedProcess() {
        ExecuteWatchdog executeWatchdog = this.watchdog;
        return executeWatchdog != null && executeWatchdog.killedProcess();
    }

    private String[] patchEnvironment() {
        if (Os.isFamily("openvms")) {
            return this.env;
        }
        Vector osEnv = (Vector) getProcEnvironment().clone();
        int i = 0;
        while (true) {
            String[] result = this.env;
            if (i < result.length) {
                int pos = result[i].indexOf(61);
                String key = this.env[i].substring(0, pos + 1);
                int size = osEnv.size();
                int j = 0;
                while (true) {
                    if (j < size) {
                        if (!((String) osEnv.elementAt(j)).startsWith(key)) {
                            j++;
                        } else {
                            osEnv.removeElementAt(j);
                            break;
                        }
                    }
                }
                osEnv.addElement(this.env[i]);
                i++;
            } else {
                String[] result2 = new String[osEnv.size()];
                osEnv.copyInto(result2);
                return result2;
            }
        }
    }

    public static void runCommand(Task task, String[] cmdline) throws Throwable {
        try {
            task.log(Commandline.describeCommand(cmdline), 3);
            Execute exe = new Execute(new LogStreamHandler(task, 2, 0));
            exe.setAntRun(task.getProject());
            exe.setCommandline(cmdline);
            int retval = exe.execute();
            if (isFailure(retval)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(cmdline[0]);
                stringBuffer.append(" failed with return code ");
                stringBuffer.append(retval);
                throw new BuildException(stringBuffer.toString(), task.getLocation());
            }
        } catch (IOException exc) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Could not launch ");
            stringBuffer2.append(cmdline[0]);
            stringBuffer2.append(": ");
            stringBuffer2.append(exc);
            throw new BuildException(stringBuffer2.toString(), task.getLocation());
        }
    }

    public static void closeStreams(Process process) {
        try {
            process.getInputStream().close();
        } catch (IOException e) {
        }
        try {
            process.getOutputStream().close();
        } catch (IOException e2) {
        }
        try {
            process.getErrorStream().close();
        } catch (IOException e3) {
        }
    }

    private static Vector addVMSLogicals(Vector environment, BufferedReader in) throws IOException {
        HashMap logicals = new HashMap();
        String logName = null;
        String logValue = null;
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            if (line.startsWith("\t=")) {
                if (logName != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(logValue);
                    stringBuffer.append(",");
                    stringBuffer.append(line.substring(4, line.length() - 1));
                    logValue = stringBuffer.toString();
                }
            } else if (line.startsWith("  \"")) {
                if (logName != null) {
                    logicals.put(logName, logValue);
                }
                int eqIndex = line.indexOf(61);
                String newLogName = line.substring(3, eqIndex - 2);
                if (logicals.containsKey(newLogName)) {
                    logName = null;
                } else {
                    logName = newLogName;
                    logValue = line.substring(eqIndex + 3, line.length() - 1);
                }
            }
        }
        if (logName != null) {
            logicals.put(logName, logValue);
        }
        for (String logical : logicals.keySet()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(logical);
            stringBuffer2.append("=");
            stringBuffer2.append(logicals.get(logical));
            environment.add(stringBuffer2.toString());
        }
        return environment;
    }

    private static class CommandLauncher {
        private CommandLauncher() {
        }

        public Process exec(Project project, String[] cmd, String[] env) throws IOException {
            if (project != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Execute:CommandLauncher: ");
                stringBuffer.append(Commandline.describeCommand(cmd));
                project.log(stringBuffer.toString(), 4);
            }
            return Runtime.getRuntime().exec(cmd, env);
        }

        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            if (workingDir == null) {
                return exec(project, cmd, env);
            }
            throw new IOException("Cannot execute a process in different directory under this JVM");
        }
    }

    private static class Java11CommandLauncher extends CommandLauncher {
        private Java11CommandLauncher() {
            super();
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env) throws IOException {
            String[] newcmd = new String[cmd.length];
            for (int i = 0; i < cmd.length; i++) {
                newcmd[i] = Commandline.quoteArgument(cmd[i]);
            }
            if (project != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("Execute:Java11CommandLauncher: ");
                stringBuffer.append(Commandline.describeCommand(newcmd));
                project.log(stringBuffer.toString(), 4);
            }
            return Runtime.getRuntime().exec(newcmd, env);
        }
    }

    private static class Java13CommandLauncher extends CommandLauncher {
        private Method myExecWithCWD;

        public Java13CommandLauncher() throws NoSuchMethodException {
            Class clsClass$;
            Class<?> clsClass$2;
            Class<?> clsClass$3;
            Class<?> clsClass$4;
            super();
            if (Execute.class$java$lang$Runtime == null) {
                clsClass$ = Execute.class$("java.lang.Runtime");
                Execute.class$java$lang$Runtime = clsClass$;
            } else {
                clsClass$ = Execute.class$java$lang$Runtime;
            }
            Class<?>[] clsArr = new Class[3];
            if (Execute.array$Ljava$lang$String == null) {
                clsClass$2 = Execute.class$("[Ljava.lang.String;");
                Execute.array$Ljava$lang$String = clsClass$2;
            } else {
                clsClass$2 = Execute.array$Ljava$lang$String;
            }
            clsArr[0] = clsClass$2;
            if (Execute.array$Ljava$lang$String == null) {
                clsClass$3 = Execute.class$("[Ljava.lang.String;");
                Execute.array$Ljava$lang$String = clsClass$3;
            } else {
                clsClass$3 = Execute.array$Ljava$lang$String;
            }
            clsArr[1] = clsClass$3;
            if (Execute.class$java$io$File == null) {
                clsClass$4 = Execute.class$("java.io.File");
                Execute.class$java$io$File = clsClass$4;
            } else {
                clsClass$4 = Execute.class$java$io$File;
            }
            clsArr[2] = clsClass$4;
            this.myExecWithCWD = clsClass$.getMethod("exec", clsArr);
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            if (project != null) {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("Execute:Java13CommandLauncher: ");
                    stringBuffer.append(Commandline.describeCommand(cmd));
                    project.log(stringBuffer.toString(), 4);
                } catch (InvocationTargetException exc) {
                    Throwable realexc = exc.getTargetException();
                    if (realexc instanceof ThreadDeath) {
                        throw ((ThreadDeath) realexc);
                    }
                    if (realexc instanceof IOException) {
                        throw ((IOException) realexc);
                    }
                    throw new BuildException("Unable to execute command", realexc);
                } catch (Exception exc2) {
                    throw new BuildException("Unable to execute command", exc2);
                }
            }
            Object[] arguments = {cmd, env, workingDir};
            return (Process) this.myExecWithCWD.invoke(Runtime.getRuntime(), arguments);
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private static class CommandLauncherProxy extends CommandLauncher {
        private CommandLauncher myLauncher;

        CommandLauncherProxy(CommandLauncher launcher) {
            super();
            this.myLauncher = launcher;
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env) throws IOException {
            return this.myLauncher.exec(project, cmd, env);
        }
    }

    private static class OS2CommandLauncher extends CommandLauncherProxy {
        OS2CommandLauncher(CommandLauncher launcher) {
            super(launcher);
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            File commandDir = workingDir;
            if (workingDir == null) {
                if (project != null) {
                    commandDir = project.getBaseDir();
                } else {
                    return exec(project, cmd, env);
                }
            }
            String cmdDir = commandDir.getAbsolutePath();
            String[] newcmd = new String[cmd.length + 7];
            newcmd[0] = "cmd";
            newcmd[1] = "/c";
            newcmd[2] = cmdDir.substring(0, 2);
            newcmd[3] = "&&";
            newcmd[4] = "cd";
            newcmd[5] = cmdDir.substring(2);
            newcmd[6] = "&&";
            System.arraycopy(cmd, 0, newcmd, 7, cmd.length);
            return exec(project, newcmd, env);
        }
    }

    private static class WinNTCommandLauncher extends CommandLauncherProxy {
        WinNTCommandLauncher(CommandLauncher launcher) {
            super(launcher);
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            File commandDir = workingDir;
            if (workingDir == null) {
                if (project != null) {
                    commandDir = project.getBaseDir();
                } else {
                    return exec(project, cmd, env);
                }
            }
            String[] newcmd = new String[cmd.length + 6];
            newcmd[0] = "cmd";
            newcmd[1] = "/c";
            newcmd[2] = "cd";
            newcmd[3] = "/d";
            newcmd[4] = commandDir.getAbsolutePath();
            newcmd[5] = "&&";
            System.arraycopy(cmd, 0, newcmd, 6, cmd.length);
            return exec(project, newcmd, env);
        }
    }

    private static class MacCommandLauncher extends CommandLauncherProxy {
        MacCommandLauncher(CommandLauncher launcher) {
            super(launcher);
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            if (workingDir == null) {
                return exec(project, cmd, env);
            }
            System.getProperties().put("user.dir", workingDir.getAbsolutePath());
            try {
                return exec(project, cmd, env);
            } finally {
                System.getProperties().put("user.dir", Execute.antWorkingDirectory);
            }
        }
    }

    private static class ScriptCommandLauncher extends CommandLauncherProxy {
        private String myScript;

        ScriptCommandLauncher(String script, CommandLauncher launcher) {
            super(launcher);
            this.myScript = script;
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            if (project == null) {
                if (workingDir == null) {
                    return exec(project, cmd, env);
                }
                throw new IOException("Cannot locate antRun script: No project provided");
            }
            String antHome = project.getProperty("ant.home");
            if (antHome == null) {
                throw new IOException("Cannot locate antRun script: Property 'ant.home' not found");
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(antHome);
            stringBuffer.append(File.separator);
            stringBuffer.append(this.myScript);
            String antRun = project.resolveFile(stringBuffer.toString()).toString();
            File commandDir = workingDir;
            if (workingDir == null && project != null) {
                commandDir = project.getBaseDir();
            }
            String[] newcmd = new String[cmd.length + 2];
            newcmd[0] = antRun;
            newcmd[1] = commandDir.getAbsolutePath();
            System.arraycopy(cmd, 0, newcmd, 2, cmd.length);
            return exec(project, newcmd, env);
        }
    }

    private static class PerlScriptCommandLauncher extends CommandLauncherProxy {
        private String myScript;

        PerlScriptCommandLauncher(String script, CommandLauncher launcher) {
            super(launcher);
            this.myScript = script;
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            if (project == null) {
                if (workingDir == null) {
                    return exec(project, cmd, env);
                }
                throw new IOException("Cannot locate antRun script: No project provided");
            }
            String antHome = project.getProperty("ant.home");
            if (antHome == null) {
                throw new IOException("Cannot locate antRun script: Property 'ant.home' not found");
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(antHome);
            stringBuffer.append(File.separator);
            stringBuffer.append(this.myScript);
            String antRun = project.resolveFile(stringBuffer.toString()).toString();
            File commandDir = workingDir;
            if (workingDir == null && project != null) {
                commandDir = project.getBaseDir();
            }
            String[] newcmd = new String[cmd.length + 3];
            newcmd[0] = "perl";
            newcmd[1] = antRun;
            newcmd[2] = commandDir.getAbsolutePath();
            System.arraycopy(cmd, 0, newcmd, 3, cmd.length);
            return exec(project, newcmd, env);
        }
    }

    private static class VmsCommandLauncher extends Java13CommandLauncher {
        @Override // org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env) throws IOException {
            String[] vmsCmd = {createCommandFile(cmd, env).getPath()};
            return super.exec(project, vmsCmd, env);
        }

        @Override // org.apache.tools.ant.taskdefs.Execute.Java13CommandLauncher, org.apache.tools.ant.taskdefs.Execute.CommandLauncher
        public Process exec(Project project, String[] cmd, String[] env, File workingDir) throws IOException {
            String[] vmsCmd = {createCommandFile(cmd, env).getPath()};
            return super.exec(project, vmsCmd, env, workingDir);
        }

        private File createCommandFile(String[] cmd, String[] env) throws IOException {
            File script = File.createTempFile("ANT", ".COM");
            script.deleteOnExit();
            PrintWriter out = null;
            try {
                out = new PrintWriter(new FileWriter(script));
                if (env != null) {
                    for (int i = 1; i < env.length; i++) {
                        int eqIndex = env[i].indexOf(61);
                        if (eqIndex != -1) {
                            out.print("$ DEFINE/NOLOG ");
                            out.print(env[i].substring(0, eqIndex));
                            out.print(" \"");
                            out.print(env[i].substring(eqIndex + 1));
                            out.println('\"');
                        }
                    }
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("$ ");
                stringBuffer.append(cmd[0]);
                out.print(stringBuffer.toString());
                for (int i2 = 1; i2 < cmd.length; i2++) {
                    out.println(" -");
                    out.print(cmd[i2]);
                }
                out.close();
                return script;
            } catch (Throwable th) {
                if (out != null) {
                    out.close();
                }
                throw th;
            }
        }
    }
}
