package com.amazon.avod.playback.session.workflow.scheduler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface TaskScheduler {
    boolean addTask(@Nonnull Task task, @Nonnull Task... taskArr) throws IllegalStateException;

    void startProcessing();

    void stopProcessingAndExecuteTask(@Nullable Task task);
}
