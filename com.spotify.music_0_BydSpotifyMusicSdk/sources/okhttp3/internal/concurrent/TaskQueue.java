package okhttp3.internal.concurrent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import okhttp3.internal.Util;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class TaskQueue {
    public final TaskRunner a;
    public final String b;
    public boolean c;
    public Task d;
    public final ArrayList e;
    public boolean f;

    public static final class AwaitIdleTask extends Task {
        public final CountDownLatch e;

        public AwaitIdleTask() {
            super(u4.m(new StringBuilder(), Util.g, " awaitIdle"), false);
            this.e = new CountDownLatch(1);
        }

        @Override // okhttp3.internal.concurrent.Task
        public final long a() {
            this.e.countDown();
            return -1L;
        }
    }

    public TaskQueue(TaskRunner taskRunner, String str) {
        v03.j(taskRunner, "taskRunner");
        v03.j(str, "name");
        this.a = taskRunner;
        this.b = str;
        this.e = new ArrayList();
    }

    public final void a() {
        byte[] bArr = Util.a;
        synchronized (this.a) {
            if (b()) {
                this.a.e(this);
            }
        }
    }

    public final boolean b() {
        Task task = this.d;
        if (task != null && task.b) {
            this.f = true;
        }
        ArrayList arrayList = this.e;
        boolean z = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((Task) arrayList.get(size)).b) {
                Task task2 = (Task) arrayList.get(size);
                TaskRunner.h.getClass();
                if (TaskRunner.j.isLoggable(Level.FINE)) {
                    TaskLoggerKt.a(task2, this, "canceled");
                }
                arrayList.remove(size);
                z = true;
            }
        }
        return z;
    }

    public final void c(Task task, long j) {
        v03.j(task, "task");
        synchronized (this.a) {
            if (!this.c) {
                if (e(task, j, false)) {
                    this.a.e(this);
                }
            } else if (task.b) {
                TaskRunner.h.getClass();
                if (TaskRunner.j.isLoggable(Level.FINE)) {
                    TaskLoggerKt.a(task, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                TaskRunner.h.getClass();
                if (TaskRunner.j.isLoggable(Level.FINE)) {
                    TaskLoggerKt.a(task, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    public final boolean e(Task task, long j, boolean z) {
        v03.j(task, "task");
        TaskQueue taskQueue = task.c;
        if (taskQueue != this) {
            if (taskQueue != null) {
                throw new IllegalStateException("task is in multiple queues".toString());
            }
            task.c = this;
        }
        long jC = this.a.a.c();
        long j2 = jC + j;
        ArrayList arrayList = this.e;
        int iIndexOf = arrayList.indexOf(task);
        if (iIndexOf != -1) {
            if (task.d <= j2) {
                TaskRunner.h.getClass();
                if (TaskRunner.j.isLoggable(Level.FINE)) {
                    TaskLoggerKt.a(task, this, "already scheduled");
                }
                return false;
            }
            arrayList.remove(iIndexOf);
        }
        task.d = j2;
        TaskRunner.h.getClass();
        if (TaskRunner.j.isLoggable(Level.FINE)) {
            TaskLoggerKt.a(task, this, z ? "run again after ".concat(TaskLoggerKt.b(j2 - jC)) : "scheduled after ".concat(TaskLoggerKt.b(j2 - jC)));
        }
        Iterator it = arrayList.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (((Task) it.next()).d - jC > j) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = arrayList.size();
        }
        arrayList.add(size, task);
        return size == 0;
    }

    public final void f() {
        byte[] bArr = Util.a;
        synchronized (this.a) {
            this.c = true;
            if (b()) {
                this.a.e(this);
            }
        }
    }

    public final String toString() {
        return this.b;
    }
}
