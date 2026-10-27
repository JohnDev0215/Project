package com.amazon.avod.playback.session.workflow.events;

import com.amazon.avod.playback.session.workflow.scheduler.Task;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TaskFailedEvent extends BaseTaskEvent {
    public TaskFailedEvent(@Nonnull Task task) {
        super(task);
    }
}
