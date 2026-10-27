package com.amazon.avod.playback.session.workflow.tasks;

import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.playback.session.workflow.scheduler.BaseTask;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.eventbus.EventBus;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SimpleTask extends BaseTask {
    private final ExceptionCallback mExceptionCallback;

    protected SimpleTask(@Nonnull EventBus eventBus, @Nonnull ExceptionCallback exceptionCallback) {
        super(eventBus);
        this.mExceptionCallback = (ExceptionCallback) Preconditions.checkNotNull(exceptionCallback, "exceptionCallback");
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            notifyTaskStarted();
            execute();
            notifyTaskSuccess();
        } catch (MediaException e) {
            this.mExceptionCallback.call(e);
            notifyTaskFailure();
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.Task
    public void abort() {
        DLog.devf("Trying to abort a simple task. This can't be done.");
    }
}
