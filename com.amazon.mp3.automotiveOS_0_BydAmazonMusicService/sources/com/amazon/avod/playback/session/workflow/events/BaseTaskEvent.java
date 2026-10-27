package com.amazon.avod.playback.session.workflow.events;

import com.amazon.avod.playback.session.workflow.scheduler.Task;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public abstract class BaseTaskEvent {
    private final Task mTask;

    protected BaseTaskEvent(@Nonnull Task task) {
        this.mTask = (Task) Preconditions.checkNotNull(task, "task");
    }

    @Nonnull
    public final Task getTask() {
        return this.mTask;
    }
}
