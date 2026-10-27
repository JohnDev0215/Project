package org.apache.tools.ant.taskdefs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
class ProcessDestroyer implements Runnable {
    static /* synthetic */ Class class$java$lang$IllegalStateException;
    static /* synthetic */ Class class$java$lang$Runtime;
    static /* synthetic */ Class class$java$lang$Thread;
    private Method addShutdownHookMethod;
    private Method removeShutdownHookMethod;
    private Vector processes = new Vector();
    private ProcessDestroyerImpl destroyProcessThread = null;
    private boolean added = false;
    private boolean running = false;

    private class ProcessDestroyerImpl extends Thread {
        private boolean shouldDestroy;
        private final /* synthetic */ ProcessDestroyer this$0;

        public ProcessDestroyerImpl(ProcessDestroyer this$0) {
            super("ProcessDestroyer Shutdown Hook");
            this.this$0 = this$0;
            this.shouldDestroy = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.shouldDestroy) {
                this.this$0.run();
            }
        }

        public void setShouldDestroy(boolean shouldDestroy) {
            this.shouldDestroy = shouldDestroy;
        }
    }

    public ProcessDestroyer() {
        Class<?> clsClass$;
        Class clsClass$2;
        Class clsClass$3;
        try {
            Class<?>[] clsArr = new Class[1];
            if (class$java$lang$Thread == null) {
                clsClass$ = class$("java.lang.Thread");
                class$java$lang$Thread = clsClass$;
            } else {
                clsClass$ = class$java$lang$Thread;
            }
            clsArr[0] = clsClass$;
            if (class$java$lang$Runtime == null) {
                clsClass$2 = class$("java.lang.Runtime");
                class$java$lang$Runtime = clsClass$2;
            } else {
                clsClass$2 = class$java$lang$Runtime;
            }
            this.addShutdownHookMethod = clsClass$2.getMethod("addShutdownHook", clsArr);
            if (class$java$lang$Runtime == null) {
                clsClass$3 = class$("java.lang.Runtime");
                class$java$lang$Runtime = clsClass$3;
            } else {
                clsClass$3 = class$java$lang$Runtime;
            }
            this.removeShutdownHookMethod = clsClass$3.getMethod("removeShutdownHook", clsArr);
        } catch (NoSuchMethodException e) {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    private void addShutdownHook() {
        if (this.addShutdownHookMethod != null && !this.running) {
            ProcessDestroyerImpl processDestroyerImpl = new ProcessDestroyerImpl(this);
            this.destroyProcessThread = processDestroyerImpl;
            Object[] args = {processDestroyerImpl};
            try {
                this.addShutdownHookMethod.invoke(Runtime.getRuntime(), args);
                this.added = true;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e2) {
                Throwable t = e2.getTargetException();
                if (t != null) {
                    Class<?> cls = t.getClass();
                    Class<?> clsClass$ = class$java$lang$IllegalStateException;
                    if (clsClass$ == null) {
                        clsClass$ = class$("java.lang.IllegalStateException");
                        class$java$lang$IllegalStateException = clsClass$;
                    }
                    if (cls == clsClass$) {
                        this.running = true;
                        return;
                    }
                }
                e2.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void removeShutdownHook() {
        /*
            r9 = this;
            java.lang.reflect.Method r0 = r9.removeShutdownHookMethod
            if (r0 == 0) goto L6d
            boolean r1 = r9.added
            if (r1 == 0) goto L6d
            boolean r1 = r9.running
            if (r1 != 0) goto L6d
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]
            org.apache.tools.ant.taskdefs.ProcessDestroyer$ProcessDestroyerImpl r3 = r9.destroyProcessThread
            r4 = 0
            r2[r4] = r3
            r3 = 0
            java.lang.Runtime r5 = java.lang.Runtime.getRuntime()     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L50
            java.lang.Object r0 = r0.invoke(r5, r2)     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L50
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.reflect.InvocationTargetException -> L2f java.lang.IllegalAccessException -> L50
            boolean r5 = r0.booleanValue()     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L50
            if (r5 != 0) goto L2c
            java.io.PrintStream r5 = java.lang.System.err     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L50
            java.lang.String r6 = "Could not remove shutdown hook"
            r5.println(r6)     // Catch: java.lang.reflect.InvocationTargetException -> L2d java.lang.IllegalAccessException -> L50
        L2c:
            goto L55
        L2d:
            r5 = move-exception
            goto L31
        L2f:
            r5 = move-exception
            r0 = r3
        L31:
            java.lang.Throwable r6 = r5.getTargetException()
            if (r6 == 0) goto L4c
            java.lang.Class r7 = r6.getClass()
            java.lang.Class r8 = org.apache.tools.ant.taskdefs.ProcessDestroyer.class$java$lang$IllegalStateException
            if (r8 != 0) goto L47
            java.lang.String r8 = "java.lang.IllegalStateException"
            java.lang.Class r8 = class$(r8)
            org.apache.tools.ant.taskdefs.ProcessDestroyer.class$java$lang$IllegalStateException = r8
        L47:
            if (r7 != r8) goto L4c
            r9.running = r1
            goto L4f
        L4c:
            r5.printStackTrace()
        L4f:
            goto L55
        L50:
            r0 = move-exception
            r0.printStackTrace()
        L55:
            org.apache.tools.ant.taskdefs.ProcessDestroyer$ProcessDestroyerImpl r0 = r9.destroyProcessThread
            r0.setShouldDestroy(r4)
            org.apache.tools.ant.taskdefs.ProcessDestroyer$ProcessDestroyerImpl r0 = r9.destroyProcessThread
            r0.start()
            org.apache.tools.ant.taskdefs.ProcessDestroyer$ProcessDestroyerImpl r0 = r9.destroyProcessThread     // Catch: java.lang.InterruptedException -> L67
            r5 = 20000(0x4e20, double:9.8813E-320)
            r0.join(r5)     // Catch: java.lang.InterruptedException -> L67
            goto L69
        L67:
            r0 = move-exception
        L69:
            r9.destroyProcessThread = r3
            r9.added = r4
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.ProcessDestroyer.removeShutdownHook():void");
    }

    public boolean isAddedAsShutdownHook() {
        return this.added;
    }

    public boolean add(Process process) {
        boolean zContains;
        synchronized (this.processes) {
            if (this.processes.size() == 0) {
                addShutdownHook();
            }
            this.processes.addElement(process);
            zContains = this.processes.contains(process);
        }
        return zContains;
    }

    public boolean remove(Process process) {
        boolean processRemoved;
        synchronized (this.processes) {
            processRemoved = this.processes.removeElement(process);
            if (processRemoved && this.processes.size() == 0) {
                removeShutdownHook();
            }
        }
        return processRemoved;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.processes) {
            this.running = true;
            Enumeration e = this.processes.elements();
            while (e.hasMoreElements()) {
                ((Process) e.nextElement()).destroy();
            }
        }
    }
}
