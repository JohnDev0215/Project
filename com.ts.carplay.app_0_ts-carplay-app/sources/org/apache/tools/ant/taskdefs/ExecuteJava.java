package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.CommandlineJava;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Permissions;
import org.apache.tools.ant.util.TimeoutObserver;
import org.apache.tools.ant.util.Watchdog;

/* JADX INFO: loaded from: classes.dex */
public class ExecuteJava implements Runnable, TimeoutObserver {
    private Commandline javaCommand = null;
    private Path classpath = null;
    private CommandlineJava.SysProperties sysProperties = null;
    private Permissions perm = null;
    private Method main = null;
    private Long timeout = null;
    private Throwable caught = null;
    private boolean timedOut = false;
    private Thread thread = null;

    public void setJavaCommand(Commandline javaCommand) {
        this.javaCommand = javaCommand;
    }

    public void setClasspath(Path p) {
        this.classpath = p;
    }

    public void setSystemProperties(CommandlineJava.SysProperties s) {
        this.sysProperties = s;
    }

    public void setPermissions(Permissions permissions) {
        this.perm = permissions;
    }

    public void setOutput(PrintStream out) {
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void execute(org.apache.tools.ant.Project r13) throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ExecuteJava.execute(org.apache.tools.ant.Project):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x006c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() throws java.lang.Throwable {
        /*
            r6 = this;
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]
            org.apache.tools.ant.types.Commandline r1 = r6.javaCommand
            java.lang.String[] r1 = r1.getArguments()
            r2 = 0
            r0[r2] = r1
            r1 = 0
            org.apache.tools.ant.types.Permissions r2 = r6.perm     // Catch: java.lang.Throwable -> L2b java.lang.reflect.InvocationTargetException -> L40
            if (r2 == 0) goto L16
            org.apache.tools.ant.types.Permissions r2 = r6.perm     // Catch: java.lang.Throwable -> L2b java.lang.reflect.InvocationTargetException -> L40
            r2.setSecurityManager()     // Catch: java.lang.Throwable -> L2b java.lang.reflect.InvocationTargetException -> L40
        L16:
            java.lang.reflect.Method r2 = r6.main     // Catch: java.lang.Throwable -> L2b java.lang.reflect.InvocationTargetException -> L40
            r2.invoke(r1, r0)     // Catch: java.lang.Throwable -> L2b java.lang.reflect.InvocationTargetException -> L40
            org.apache.tools.ant.types.Permissions r1 = r6.perm
            if (r1 == 0) goto L22
            r1.restoreSecurityManager()
        L22:
            monitor-enter(r6)
            r6.notifyAll()     // Catch: java.lang.Throwable -> L28
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L28
            goto L57
        L28:
            r1 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L28
            throw r1
        L2b:
            r2 = move-exception
            r6.caught = r2     // Catch: java.lang.Throwable -> L3e
            org.apache.tools.ant.types.Permissions r3 = r6.perm
            if (r3 == 0) goto L35
            r3.restoreSecurityManager()
        L35:
            monitor-enter(r6)
            r6.notifyAll()     // Catch: java.lang.Throwable -> L3b
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L3b
            goto L57
        L3b:
            r3 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L3b
            throw r3
        L3e:
            r3 = move-exception
            goto L64
        L40:
            r2 = move-exception
            java.lang.Throwable r1 = r2.getTargetException()     // Catch: java.lang.Throwable -> L60
            boolean r3 = r1 instanceof java.lang.InterruptedException     // Catch: java.lang.Throwable -> L5b
            if (r3 != 0) goto L4b
            r6.caught = r1     // Catch: java.lang.Throwable -> L5b
        L4b:
            org.apache.tools.ant.types.Permissions r1 = r6.perm
            if (r1 == 0) goto L52
            r1.restoreSecurityManager()
        L52:
            monitor-enter(r6)
            r6.notifyAll()     // Catch: java.lang.Throwable -> L58
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L58
        L57:
            return
        L58:
            r1 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L58
            throw r1
        L5b:
            r3 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
            goto L64
        L60:
            r3 = move-exception
            r5 = r2
            r2 = r1
            r1 = r5
        L64:
            org.apache.tools.ant.types.Permissions r4 = r6.perm
            if (r4 == 0) goto L6b
            r4.restoreSecurityManager()
        L6b:
            monitor-enter(r6)
            r6.notifyAll()     // Catch: java.lang.Throwable -> L71
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L71
            throw r3
        L71:
            r3 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L71
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ExecuteJava.run():void");
    }

    @Override // org.apache.tools.ant.util.TimeoutObserver
    public synchronized void timeoutOccured(Watchdog w) {
        if (this.thread != null) {
            this.timedOut = true;
            this.thread.interrupt();
        }
        notifyAll();
    }

    public synchronized boolean killedProcess() {
        return this.timedOut;
    }

    public int fork(ProjectComponent pc) throws Throwable {
        CommandlineJava cmdl = new CommandlineJava();
        cmdl.setClassname(this.javaCommand.getExecutable());
        String[] args = this.javaCommand.getArguments();
        for (String str : args) {
            cmdl.createArgument().setValue(str);
        }
        if (this.classpath != null) {
            cmdl.createClasspath(pc.getProject()).append(this.classpath);
        }
        CommandlineJava.SysProperties sysProperties = this.sysProperties;
        if (sysProperties != null) {
            cmdl.addSysproperties(sysProperties);
        }
        Redirector redirector = new Redirector(pc);
        ExecuteStreamHandler executeStreamHandlerCreateHandler = redirector.createHandler();
        Long l = this.timeout;
        Execute exe = new Execute(executeStreamHandlerCreateHandler, l == null ? null : new ExecuteWatchdog(l.longValue()));
        exe.setAntRun(pc.getProject());
        exe.setCommandline(cmdl.getCommandline());
        try {
            int rc = exe.execute();
            redirector.complete();
            this.timedOut = exe.killedProcess();
            return rc;
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
}
