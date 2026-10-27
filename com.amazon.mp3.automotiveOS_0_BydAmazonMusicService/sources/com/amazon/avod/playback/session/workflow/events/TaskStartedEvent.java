package com.amazon.avod.playback.session.workflow.events;

import com.amazon.avod.playback.session.workflow.scheduler.Task;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class TaskStartedEvent extends BaseTaskEvent {
    public TaskStartedEvent(@Nonnull Task task) {
        super(task);
    }
}
