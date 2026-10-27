package com.amazon.avod.playback.session.workflow.scheduler;

import com.amazon.avod.playback.session.workflow.events.TaskCompletedEvent;
import com.amazon.avod.playback.session.workflow.events.TaskFailedEvent;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class DAGTaskScheduler implements TaskScheduler {
    private final Task mEndPlanTask;
    private final EventBus mEventBus;
    private final ExecutorService mExecutorService;
    private final int mMaxConcurrentTasks;
    private final SortedSet<Item> mPendingItems;
    private final Set<Item> mRunningItems;
    private final Set<Item> mTasksWaitingForShutdownComplete;
    private final Object mPendingTasksSetLock = new Object();
    private EngineState mCurrentEngineState = EngineState.WAITING;
    private boolean mShouldStartImmediately = false;

    public DAGTaskScheduler(@Nonnull EventBus eventBus, int i, @Nonnull ExecutorService executorService) {
        EventBus eventBus2 = (EventBus) Preconditions.checkNotNull(eventBus, "eventBus");
        this.mEventBus = eventBus2;
        this.mExecutorService = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
        this.mMaxConcurrentTasks = Preconditions2.checkPositive(i, "maxConcurrentTasks");
        this.mPendingItems = Sets.newTreeSet();
        this.mRunningItems = Sets.newHashSet();
        this.mTasksWaitingForShutdownComplete = Sets.newHashSet();
        eventBus2.register(this);
        this.mEndPlanTask = new BaseTask(eventBus2) { // from class: com.amazon.avod.playback.session.workflow.scheduler.DAGTaskScheduler.1
            @Override // com.amazon.avod.playback.session.workflow.scheduler.Task
            public void abort() {
            }

            @Override // java.lang.Runnable
            public void run() {
                notifyTaskStarted();
                execute();
                notifyTaskSuccess();
            }

            @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
            public void execute() {
                synchronized (DAGTaskScheduler.this.mPendingTasksSetLock) {
                    DLog.logf("DAGTaskScheduler - Terminate complete. Tasks waiting to be scheduled: %s", Integer.valueOf(DAGTaskScheduler.this.mTasksWaitingForShutdownComplete.size()));
                    if (DAGTaskScheduler.this.mPendingItems.isEmpty()) {
                        DAGTaskScheduler.this.mPendingItems.addAll(DAGTaskScheduler.this.mTasksWaitingForShutdownComplete);
                        if (DAGTaskScheduler.this.mShouldStartImmediately) {
                            DAGTaskScheduler.this.switchStates(EngineState.RUNNING);
                            DAGTaskScheduler.this.mShouldStartImmediately = false;
                        } else {
                            DAGTaskScheduler.this.switchStates(EngineState.WAITING);
                        }
                    }
                    DAGTaskScheduler.this.mTasksWaitingForShutdownComplete.clear();
                }
            }
        };
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.TaskScheduler
    public boolean addTask(@Nonnull Task task, @Nonnull Task... taskArr) {
        Preconditions.checkNotNull(task, "task");
        Preconditions.checkNotNull(taskArr, "dependencies");
        synchronized (this.mPendingTasksSetLock) {
            if (!this.mCurrentEngineState.isAcceptingTasks()) {
                DLog.errorf("DAGTaskScheduler - Trying to addTask while not accepting tasks. This is probably a bug.");
                return false;
            }
            return addTaskToPendingItems(task, Sets.newHashSet(taskArr));
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.session.workflow.scheduler.DAGTaskScheduler$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState;

        static {
            int[] iArr = new int[EngineState.values().length];
            $SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState = iArr;
            try {
                iArr[EngineState.WAITING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState[EngineState.STOPPING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState[EngineState.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState[EngineState.RUNNING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.TaskScheduler
    public void startProcessing() {
        synchronized (this.mPendingTasksSetLock) {
            int i = AnonymousClass2.$SwitchMap$com$amazon$avod$playback$session$workflow$scheduler$DAGTaskScheduler$EngineState[this.mCurrentEngineState.ordinal()];
            if (i == 1) {
                this.mShouldStartImmediately = true;
                switchStates(EngineState.RUNNING);
                trySchedule();
            } else if (i == 2) {
                this.mShouldStartImmediately = true;
            } else if (i == 3) {
                Preconditions.checkState(false, "EngineState.ERROR");
            } else if (i == 4) {
                DLog.warnf("DAGTaskScheduler - Calling startProcessing twice. This is probably a bug.");
            }
        }
    }

    @Override // com.amazon.avod.playback.session.workflow.scheduler.TaskScheduler
    public void stopProcessingAndExecuteTask(@Nullable Task task) {
        synchronized (this.mPendingTasksSetLock) {
            this.mShouldStartImmediately = false;
            EngineState engineState = this.mCurrentEngineState;
            EngineState engineState2 = EngineState.STOPPING;
            if (engineState == engineState2) {
                DLog.warnf("DAGTaskScheduler - Trying to stop DAG engine while stopped. Emptying task buffer and moving on.");
                this.mTasksWaitingForShutdownComplete.clear();
                if (task != null) {
                    addTaskDependingOnScheduledTasks(task);
                    this.mPendingItems.add(new Item(this.mEndPlanTask, Sets.newHashSet(task)));
                }
            } else {
                switchStates(engineState2);
                this.mPendingItems.clear();
                if (!this.mRunningItems.isEmpty()) {
                    this.mPendingItems.add(new Item(new CallAbortTask(this.mEventBus, getRunningTasks()), Collections.emptySet()));
                }
                if (task != null) {
                    addTaskDependingOnRunningTasks(task);
                    this.mPendingItems.add(new Item(this.mEndPlanTask, Sets.newHashSet(task)));
                } else {
                    addTaskDependingOnRunningTasks(this.mEndPlanTask);
                }
            }
        }
        trySchedule();
    }

    @Subscribe
    public void onTaskCompleted(@Nonnull TaskCompletedEvent taskCompletedEvent) {
        Preconditions.checkNotNull(taskCompletedEvent, "taskCompletedEvent");
        synchronized (this.mPendingTasksSetLock) {
            removeFinishedTask(taskCompletedEvent.getTask());
        }
        trySchedule();
    }

    @Subscribe
    public void onTaskFailed(@Nonnull TaskFailedEvent taskFailedEvent) {
        Preconditions.checkNotNull(taskFailedEvent, "taskFailedEvent");
        synchronized (this.mPendingTasksSetLock) {
            Task task = taskFailedEvent.getTask();
            removeFinishedTask(task);
            if (this.mCurrentEngineState != EngineState.STOPPING) {
                DLog.errorf("DAGTaskScheduler - %s FAILED. DAG is in ERROR state. Look for a STOPPING event, otherwise it's a bug", task.getClass().getSimpleName());
                switchStates(EngineState.ERROR);
            } else {
                trySchedule();
            }
        }
    }

    private void removeFinishedTask(@Nonnull Task task) {
        synchronized (this.mPendingTasksSetLock) {
            Iterator<Item> it = this.mRunningItems.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (task.equals(it.next().getTask())) {
                    markCompleted(task);
                    it.remove();
                    break;
                }
            }
        }
    }

    private boolean addTaskToPendingItems(@Nonnull Task task, @Nonnull Set<Task> set) {
        if (this.mCurrentEngineState == EngineState.STOPPING) {
            return this.mTasksWaitingForShutdownComplete.add(new Item(task, set));
        }
        return this.mPendingItems.add(new Item(task, set));
    }

    private boolean addTaskDependingOnRunningTasks(@Nonnull Task task) {
        boolean zAdd;
        Preconditions.checkNotNull(task, "task");
        synchronized (this.mPendingTasksSetLock) {
            zAdd = this.mPendingItems.add(new Item(task, getRunningTasks()));
        }
        return zAdd;
    }

    private boolean addTaskDependingOnScheduledTasks(@Nonnull Task task) {
        boolean zAdd;
        Preconditions.checkNotNull(task, "task");
        synchronized (this.mPendingTasksSetLock) {
            Set<Task> runningTasks = getRunningTasks();
            Iterator<Item> it = this.mPendingItems.iterator();
            while (it.hasNext()) {
                runningTasks.add(it.next().getTask());
            }
            zAdd = this.mPendingItems.add(new Item(task, runningTasks));
        }
        return zAdd;
    }

    private Set<Task> getRunningTasks() {
        HashSet hashSetNewHashSet;
        synchronized (this.mPendingTasksSetLock) {
            hashSetNewHashSet = Sets.newHashSet();
            Iterator<Item> it = this.mRunningItems.iterator();
            while (it.hasNext()) {
                hashSetNewHashSet.add(it.next().getTask());
            }
        }
        return hashSetNewHashSet;
    }

    private void trySchedule() {
        synchronized (this.mPendingTasksSetLock) {
            EngineState engineState = this.mCurrentEngineState;
            if (engineState != EngineState.WAITING && engineState != EngineState.ERROR) {
                while (this.mRunningItems.size() < this.mMaxConcurrentTasks) {
                    Item itemPopDependency = popDependency();
                    if (itemPopDependency == null) {
                        return;
                    }
                    this.mRunningItems.add(itemPopDependency);
                    DLog.logf("DAG submitting %s thread count %s < thread max %s", itemPopDependency.getTask().getClass(), Integer.valueOf(this.mRunningItems.size()), Integer.valueOf(this.mMaxConcurrentTasks));
                    this.mExecutorService.submit(itemPopDependency.getTask());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchStates(EngineState engineState) {
        DLog.logf("DAGTaskScheduler - Transition from %s TO %s", this.mCurrentEngineState, engineState);
        this.mCurrentEngineState = engineState;
    }

    @Nullable
    private Item popDependency() {
        synchronized (this.mPendingTasksSetLock) {
            if (this.mPendingItems.size() <= 0) {
                return null;
            }
            Item itemFirst = this.mPendingItems.first();
            if (itemFirst.getDependencySetSize() != 0) {
                return null;
            }
            this.mPendingItems.remove(itemFirst);
            return itemFirst;
        }
    }

    private void markCompleted(@Nonnull Task task) {
        synchronized (this.mPendingTasksSetLock) {
            Iterator<Item> it = this.mPendingItems.iterator();
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            while (it.hasNext()) {
                Item next = it.next();
                if (next.removeDependency(task)) {
                    arrayListNewArrayList.add(next);
                    it.remove();
                }
            }
            this.mPendingItems.addAll(arrayListNewArrayList);
        }
    }

    private static class Item implements Comparable<Item> {
        private final Set<Task> mDependencySet;
        private final Task mTask;
        private final UUID mUUID = UUID.randomUUID();

        public Item(@Nonnull Task task, @Nonnull Set<Task> set) {
            this.mTask = task;
            this.mDependencySet = set;
        }

        public Task getTask() {
            return this.mTask;
        }

        public UUID getId() {
            return this.mUUID;
        }

        public int getDependencySetSize() {
            return this.mDependencySet.size();
        }

        public boolean removeDependency(@Nonnull Task task) {
            return this.mDependencySet.remove(task);
        }

        @Override // java.lang.Comparable
        public int compareTo(@Nonnull Item item) {
            if (equals(item)) {
                return 0;
            }
            int dependencySetSize = getDependencySetSize() - item.getDependencySetSize();
            return dependencySetSize != 0 ? dependencySetSize : getId().compareTo(item.getId());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mUUID.equals(((Item) obj).mUUID);
        }

        public int hashCode() {
            return this.mUUID.hashCode();
        }
    }

    private enum EngineState {
        WAITING(true),
        RUNNING(false),
        STOPPING(true),
        ERROR(false);

        private final boolean mAcceptingTasks;

        EngineState(boolean z) {
            this.mAcceptingTasks = z;
        }

        public boolean isAcceptingTasks() {
            return this.mAcceptingTasks;
        }
    }

    private static class CallAbortTask extends BaseTask {
        private final Set<Task> mTasks;

        @Override // com.amazon.avod.playback.session.workflow.scheduler.Task
        public void abort() {
        }

        public CallAbortTask(EventBus eventBus, Set<Task> set) {
            super(eventBus);
            this.mTasks = set;
        }

        @Override // com.amazon.avod.playback.session.workflow.scheduler.BaseTask
        public void execute() {
            Iterator<Task> it = this.mTasks.iterator();
            while (it.hasNext()) {
                it.next().abort();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            notifyTaskStarted();
            execute();
            notifyTaskSuccess();
        }
    }
}
