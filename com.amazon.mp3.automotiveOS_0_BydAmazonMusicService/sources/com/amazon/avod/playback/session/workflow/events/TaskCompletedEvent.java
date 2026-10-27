package com.amazon.avod.playback.session.workflow.events;

import com.amazon.avod.playback.session.workflow.scheduler.Task;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class TaskCompletedEvent extends BaseTaskEvent {
    public TaskCompletedEvent(@Nonnull Task task) {
        super(task);
    }
}
