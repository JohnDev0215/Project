package org.apache.tools.ant.taskdefs;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.util.StringUtils;

/* JADX INFO: loaded from: classes.dex */
public class Parallel extends Task implements TaskContainer {
    static /* synthetic */ Class class$java$lang$Runtime;
    private TaskList daemonTasks;
    private StringBuffer exceptionMessage;
    private boolean failOnAny;
    private Throwable firstException;
    private Location firstLocation;
    private volatile boolean stillRunning;
    private boolean timedOut;
    private long timeout;
    private Vector nestedTasks = new Vector();
    private final Object semaphore = new Object();
    private int numThreads = 0;
    private int numThreadsPerProcessor = 0;
    private int numExceptions = 0;

    public static class TaskList implements TaskContainer {
        private List tasks = new ArrayList();

        @Override // org.apache.tools.ant.TaskContainer
        public void addTask(Task nestedTask) {
            this.tasks.add(nestedTask);
        }
    }

    public void addDaemons(TaskList daemonTasks) {
        if (this.daemonTasks != null) {
            throw new BuildException("Only one daemon group is supported");
        }
        this.daemonTasks = daemonTasks;
    }

    public void setPollInterval(int pollInterval) {
    }

    public void setFailOnAny(boolean failOnAny) {
        this.failOnAny = failOnAny;
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task nestedTask) {
        this.nestedTasks.addElement(nestedTask);
    }

    public void setThreadsPerProcessor(int numThreadsPerProcessor) {
        this.numThreadsPerProcessor = numThreadsPerProcessor;
    }

    public void setThreadCount(int numThreads) {
        this.numThreads = numThreads;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws Throwable {
        updateThreadCounts();
        if (this.numThreads == 0) {
            this.numThreads = this.nestedTasks.size();
        }
        spinThreads();
    }

    private void updateThreadCounts() {
        int numProcessors;
        if (this.numThreadsPerProcessor != 0 && (numProcessors = getNumProcessors()) != 0) {
            this.numThreads = this.numThreadsPerProcessor * numProcessors;
        }
    }

    private void processExceptions(TaskRunnable[] runnables) {
        if (runnables == null) {
            return;
        }
        for (TaskRunnable taskRunnable : runnables) {
            Throwable t = taskRunnable.getException();
            if (t != null) {
                this.numExceptions++;
                if (this.firstException == null) {
                    this.firstException = t;
                }
                if ((t instanceof BuildException) && this.firstLocation == Location.UNKNOWN_LOCATION) {
                    this.firstLocation = ((BuildException) t).getLocation();
                }
                this.exceptionMessage.append(StringUtils.LINE_SEP);
                this.exceptionMessage.append(t.getMessage());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x008c A[Catch: all -> 0x0139, TryCatch #5 {all -> 0x0139, blocks: (B:30:0x0084, B:32:0x008c, B:34:0x0096, B:39:0x009f, B:43:0x00a7, B:45:0x00ab, B:54:0x00cd, B:56:0x00d1, B:75:0x011e, B:76:0x0121, B:78:0x0125, B:80:0x012d), top: B:108:0x0084 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a7 A[Catch: all -> 0x0139, TRY_ENTER, TryCatch #5 {all -> 0x0139, blocks: (B:30:0x0084, B:32:0x008c, B:34:0x0096, B:39:0x009f, B:43:0x00a7, B:45:0x00ab, B:54:0x00cd, B:56:0x00d1, B:75:0x011e, B:76:0x0121, B:78:0x0125, B:80:0x012d), top: B:108:0x0084 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x013c  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:26:0x007c
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void spinThreads() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Parallel.spinThreads():void");
    }

    private int getNumProcessors() {
        Class clsClass$;
        try {
            Class<?>[] clsArr = new Class[0];
            if (class$java$lang$Runtime == null) {
                clsClass$ = class$("java.lang.Runtime");
                class$java$lang$Runtime = clsClass$;
            } else {
                clsClass$ = class$java$lang$Runtime;
            }
            Method availableProcessors = clsClass$.getMethod("availableProcessors", clsArr);
            Object[] args = new Object[0];
            Integer ret = (Integer) availableProcessors.invoke(Runtime.getRuntime(), args);
            return ret.intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private class TaskRunnable implements Runnable {
        private Throwable exception;
        private boolean finished;
        private Task task;

        TaskRunnable(Task task) {
            this.task = task;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.task.perform();
                synchronized (Parallel.this.semaphore) {
                    this.finished = true;
                    Parallel.this.semaphore.notifyAll();
                }
            } catch (Throwable t) {
                try {
                    this.exception = t;
                    if (Parallel.this.failOnAny) {
                        Parallel.this.stillRunning = false;
                    }
                    synchronized (Parallel.this.semaphore) {
                        this.finished = true;
                        Parallel.this.semaphore.notifyAll();
                    }
                } catch (Throwable th) {
                    synchronized (Parallel.this.semaphore) {
                        this.finished = true;
                        Parallel.this.semaphore.notifyAll();
                        throw th;
                    }
                }
            }
        }

        public Throwable getException() {
            return this.exception;
        }

        boolean isFinished() {
            return this.finished;
        }
    }
}
