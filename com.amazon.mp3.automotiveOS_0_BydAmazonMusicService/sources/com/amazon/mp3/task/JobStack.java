package com.amazon.mp3.task;

import com.amazon.mp3.task.AbstractMetadata;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes4.dex */
public class JobStack<T extends AbstractMetadata> {
    private IContextQueue<T> mTaskQueue = new LBDContextQueue();
    private HashMap<String, JobContext<T>> mTaskMap = new HashMap<>();

    private interface IContextQueue<T extends AbstractMetadata> {
        void addFirst(JobContext<T> jobContext);

        void addLast(JobContext<T> jobContext);

        void clear();

        boolean remove(Object jobContext);

        int size();

        JobContext<T> take() throws InterruptedException;
    }

    private static class LBDContextQueue<T extends AbstractMetadata> extends LinkedBlockingDeque<JobContext<T>> implements IContextQueue<T> {
        private LBDContextQueue() {
        }

        @Override // com.amazon.mp3.task.JobStack.IContextQueue
        public /* bridge */ /* synthetic */ void addFirst(JobContext e) {
            super.addFirst(e);
        }

        @Override // com.amazon.mp3.task.JobStack.IContextQueue
        public /* bridge */ /* synthetic */ void addLast(JobContext e) {
            super.addLast(e);
        }

        @Override // java.util.concurrent.LinkedBlockingDeque, java.util.concurrent.BlockingDeque, java.util.concurrent.BlockingQueue, com.amazon.mp3.task.JobStack.IContextQueue
        public /* bridge */ /* synthetic */ JobContext take() throws InterruptedException {
            return (JobContext) super.take();
        }
    }

    public JobContext<T> put(T metadata, JobListener<T> listener) {
        String key = metadata.getKey();
        JobContext<T> jobContext = null;
        if (key != null) {
            synchronized (this.mTaskMap) {
                if (this.mTaskMap.containsKey(key)) {
                    JobContext<T> jobContext2 = this.mTaskMap.get(key);
                    jobContext2.addListener(listener);
                    if (!metadata.isPrecacheRequest()) {
                        jobContext2.getMetadata().setPrecacheRequest(false);
                    }
                    if (!metadata.isLowPriority()) {
                        jobContext2.getMetadata().setLowPriority(false);
                        if (this.mTaskQueue.remove(jobContext2)) {
                            this.mTaskQueue.addFirst(jobContext2);
                        }
                    }
                    jobContext = jobContext2;
                } else if (!metadata.isUpdateOnly()) {
                    jobContext = new JobContext<>(metadata);
                    jobContext.addListener(listener);
                    this.mTaskMap.put(key, jobContext);
                    if (metadata.isLowPriority()) {
                        this.mTaskQueue.addLast(jobContext);
                    } else {
                        this.mTaskQueue.addFirst(jobContext);
                    }
                }
            }
        }
        return jobContext;
    }

    public JobContext<T> take() throws InterruptedException {
        return this.mTaskQueue.take();
    }

    public void markComplete(JobContext<T> jobContext) {
        synchronized (this.mTaskMap) {
            this.mTaskMap.remove(jobContext.getMetadata().getKey());
        }
    }

    public void clearStack() {
        this.mTaskQueue.clear();
        synchronized (this.mTaskMap) {
            this.mTaskMap.clear();
        }
    }

    public int getTaskCount() {
        return this.mTaskMap.size();
    }

    public int getRemainingCount() {
        return this.mTaskQueue.size();
    }
}
