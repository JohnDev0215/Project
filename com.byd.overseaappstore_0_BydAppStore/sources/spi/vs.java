package spi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class vs {
    public boolean a;

    @Nullable
    public ts b;

    @NotNull
    public final List<ts> c;
    public boolean d;

    @NotNull
    public final ws e;

    @NotNull
    public final String f;

    public vs(@NotNull ws taskRunner, @NotNull String name) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(name, "name");
        this.e = taskRunner;
        this.f = name;
        this.c = new ArrayList();
    }

    public final void a() {
        byte[] bArr = uu.a;
        synchronized (this.e) {
            if (b()) {
                this.e.e(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    public final boolean b() {
        ts tsVar = this.b;
        if (tsVar != null) {
            Intrinsics.checkNotNull(tsVar);
            if (tsVar.d) {
                this.d = true;
            }
        }
        boolean z = false;
        for (int size = this.c.size() - 1; size >= 0; size--) {
            if (((ts) this.c.get(size)).d) {
                ts tsVar2 = (ts) this.c.get(size);
                Objects.requireNonNull(ws.j);
                if (ws.i.isLoggable(Level.FINE)) {
                    c.b(tsVar2, this, "canceled");
                }
                this.c.remove(size);
                z = true;
            }
        }
        return z;
    }

    public final void c(@NotNull ts task, long j) {
        Intrinsics.checkNotNullParameter(task, "task");
        synchronized (this.e) {
            if (!this.a) {
                if (e(task, j, false)) {
                    this.e.e(this);
                }
                Unit unit = Unit.INSTANCE;
            } else if (task.d) {
                Objects.requireNonNull(ws.j);
                if (ws.i.isLoggable(Level.FINE)) {
                    c.b(task, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                Objects.requireNonNull(ws.j);
                if (ws.i.isLoggable(Level.FINE)) {
                    c.b(task, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r12v1, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r12v2, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r14v1, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    /* JADX WARN: Type inference failed for: r8v3, types: [java.util.ArrayList, java.util.List<spi.ts>] */
    public final boolean e(@NotNull ts task, long j, boolean z) {
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(task, "task");
        Objects.requireNonNull(task);
        Intrinsics.checkNotNullParameter(this, "queue");
        vs vsVar = task.a;
        if (vsVar != this) {
            if (!(vsVar == null)) {
                throw new IllegalStateException("task is in multiple queues".toString());
            }
            task.a = this;
        }
        long jC = this.e.g.c();
        long j2 = jC + j;
        int iIndexOf = this.c.indexOf(task);
        if (iIndexOf != -1) {
            if (task.b <= j2) {
                Objects.requireNonNull(ws.j);
                if (ws.i.isLoggable(Level.FINE)) {
                    c.b(task, this, "already scheduled");
                }
                return false;
            }
            this.c.remove(iIndexOf);
        }
        task.b = j2;
        Objects.requireNonNull(ws.j);
        if (ws.i.isLoggable(Level.FINE)) {
            if (z) {
                sb = new StringBuilder();
                str = "run again after ";
            } else {
                sb = new StringBuilder();
                str = "scheduled after ";
            }
            sb.append(str);
            sb.append(c.f(j2 - jC));
            c.b(task, this, sb.toString());
        }
        Iterator it = this.c.iterator();
        int size = 0;
        while (true) {
            if (!it.hasNext()) {
                size = -1;
                break;
            }
            if (((ts) it.next()).b - jC > j) {
                break;
            }
            size++;
        }
        if (size == -1) {
            size = this.c.size();
        }
        this.c.add(size, task);
        return size == 0;
    }

    public final void f() {
        byte[] bArr = uu.a;
        synchronized (this.e) {
            this.a = true;
            if (b()) {
                this.e.e(this);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final String toString() {
        return this.f;
    }
}
