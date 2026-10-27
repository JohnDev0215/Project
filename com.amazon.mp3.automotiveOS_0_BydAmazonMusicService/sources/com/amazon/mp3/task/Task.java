package com.amazon.mp3.task;

import com.amazon.mp3.util.Log;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Task implements Runnable {
    private Object mConnection;
    private List<DependentTask> mDependentTasks;
    private ExecutionController mExecutionController;
    private boolean mIsPreemptive;
    private Object mResponseObject;
    private AtomicInteger mRunCount = new AtomicInteger(0);
    private AtomicInteger mSuccessfulRunCount = new AtomicInteger(0);
    protected String mTAG = getClass().getSimpleName();

    private static class DependentTask {
        ExecutionController mExecutionController;
        Task mTask;
    }

    public abstract void execute() throws Throwable;

    void setExecutionController(ExecutionController executionController) {
        this.mExecutionController = executionController;
    }

    public int getRunCount() {
        return this.mRunCount.get();
    }

    public int getSuccessfulRunCount() {
        return this.mSuccessfulRunCount.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void submitDependentTask(DependentTask dependentTask) {
        Task task = dependentTask.mTask;
        ExecutionController executionController = dependentTask.mExecutionController;
        if (!(task instanceof InputDataExpected) || ((InputDataExpected) task).canHandleData(this.mResponseObject)) {
            if (executionController == null) {
                this.mExecutionController.submitTask(task);
            } else {
                executionController.submitTask(task);
            }
        }
    }

    private void submitDependentTasks() {
        List<DependentTask> list = this.mDependentTasks;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.mDependentTasks.size(); i++) {
            DependentTask dependentTask = this.mDependentTasks.get(i);
            if (dependentTask != null && dependentTask.mTask != null) {
                submitDependentTask(this.mDependentTasks.get(i));
            }
        }
    }

    public boolean isPreemptive() {
        return this.mIsPreemptive;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mRunCount.incrementAndGet();
        this.mConnection = this.mExecutionController.getConnection();
        try {
            Log.verbose(this.mTAG, "Executing...");
            execute();
            submitDependentTasks();
            this.mSuccessfulRunCount.incrementAndGet();
            this.mExecutionController.handleCompletion(this);
            this.mRunCount.set(0);
        } catch (Throwable th) {
            this.mExecutionController.handleException(this, th);
        }
    }
}
