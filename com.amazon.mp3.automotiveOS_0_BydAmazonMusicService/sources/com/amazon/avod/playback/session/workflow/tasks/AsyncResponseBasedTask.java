package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.session.workflow.scheduler.BaseTask;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AsyncResponseBasedTask extends BaseTask {
    private final ExceptionCallback mExceptionCallback;

    public abstract void executeAbort();

    protected AsyncResponseBasedTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback) {
        super(eventBus);
        this.mExceptionCallback = (ExceptionCallback) Preconditions.checkNotNull(exceptionCallback, "exceptionCallback");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            notifyTaskStarted();
            execute();
        } catch (MediaException e) {
            notifyFailure(e);
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.Task
    public final void abort() {
        DLog.logf("Aborting task: %s", getClass().getSimpleName());
        executeAbort();
        notifyTaskSuccess();
    }

    protected void notifyFailure(@Nonnull MediaException mediaException) {
        Preconditions.checkNotNull(mediaException, "exception");
        this.mExceptionCallback.call(mediaException);
        notifyTaskFailure();
    }
}
