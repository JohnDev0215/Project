package com.amazon.avod.http;

import com.amazon.avod.perf.Profiler;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.bolthttp.ThreadingModel;
import com.google.firebase.perf.metrics.resource.ResourceType;
import java.util.EnumMap;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes2.dex */
public class ProfiledThreadingModel implements ThreadingModel {
    private final EnumMap<ThreadingModel.ExecutorType, ThreadPoolExecutor> mExecutors;

    public ProfiledThreadingModel() {
        EnumMap<ThreadingModel.ExecutorType, ThreadPoolExecutor> enumMap = new EnumMap<>(ThreadingModel.ExecutorType.class);
        this.mExecutors = enumMap;
        ThreadingModel.ExecutorType executorType = ThreadingModel.ExecutorType.OVERLORD;
        ExecutorBuilder executorBuilderWithFixedThreadPoolSize = ExecutorBuilder.newBuilder("Http", "Overlord").withFixedThreadPoolSize(1);
        ExecutorBuilder.WorkerQueueType workerQueueType = ExecutorBuilder.WorkerQueueType.PRIORITY_BASED_FIFO;
        ExecutorBuilder executorBuilderWithWorkerQueueType = executorBuilderWithFixedThreadPoolSize.withWorkerQueueType(workerQueueType);
        Profiler.TraceLevel traceLevel = Profiler.TraceLevel.CRITICAL;
        enumMap.put(executorType, executorBuilderWithWorkerQueueType.withProfilerTraceLevel(traceLevel).allowCoreThreadExpiry().build());
        enumMap.put(ThreadingModel.ExecutorType.NETWORK, ExecutorBuilder.newBuilder("Http", ResourceType.NETWORK).withFixedThreadPoolSize(4).withWorkerQueueType(workerQueueType).withProfilerTraceLevel(traceLevel).withRunnableNameTracing().allowCoreThreadExpiry().build());
        enumMap.put(ThreadingModel.ExecutorType.CPU, ExecutorBuilder.newBuilder("Http", "CPU").withFixedThreadPoolSize(2).withWorkerQueueType(workerQueueType).withProfilerTraceLevel(traceLevel).withRunnableNameTracing().allowCoreThreadExpiry().build());
        enumMap.put(ThreadingModel.ExecutorType.DISK, ExecutorBuilder.newBuilder("Http", "Disk").withFixedThreadPoolSize(2).withWorkerQueueType(workerQueueType).withProfilerTraceLevel(traceLevel).withRunnableNameTracing().allowCoreThreadExpiry().build());
    }

    @Override // com.amazon.bolthttp.ThreadingModel
    public ThreadPoolExecutor getExecutor(ThreadingModel.ExecutorType executorType) {
        return this.mExecutors.get(executorType);
    }
}
