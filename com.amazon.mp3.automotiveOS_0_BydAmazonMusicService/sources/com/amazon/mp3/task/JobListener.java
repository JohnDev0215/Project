package com.amazon.mp3.task;

import com.amazon.mp3.task.AbstractMetadata;

/* JADX INFO: loaded from: classes4.dex */
public interface JobListener<T extends AbstractMetadata> {
    void onComplete(JobContext<T> context);

    void onFailure(JobContext<T> context);
}
