package org.chromium.components.background_task_scheduler;

import android.os.PersistableBundle;

/* JADX INFO: loaded from: classes2.dex */
public final class TaskInfo {
    public final int a;
    public final PersistableBundle b;
    public final int c;
    public final boolean d;
    public final boolean e;
    public final boolean f;
    public final TimingInfo g;

    public interface TimingInfo {
        void a(d dVar);
    }

    public static final class a {
        public final int a;
        public PersistableBundle b;
        public int c;
        public boolean d;
        public boolean e;
        public boolean f;
        public TimingInfo g;

        public a(int i) {
            this.a = i;
        }

        public final TaskInfo a() {
            return new TaskInfo(this);
        }
    }

    public static class b implements TimingInfo {
        public final long a;
        public final long b;
        public final boolean c;
        public final boolean d;

        public static final class a {
            public long a;
            public long b;
            public boolean c;
            public boolean d;
        }

        public b(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.TimingInfo
        public final void a(d dVar) {
            dVar.b(this);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("{");
            if (this.c) {
                sb.append("windowStartTimeMs: ");
                sb.append(this.a);
                sb.append(", ");
            }
            sb.append("windowEndTimeMs: ");
            sb.append(this.b);
            sb.append(", expiresAfterWindowEndTime (+flex): ");
            sb.append(this.d);
            sb.append("}");
            return sb.toString();
        }
    }

    public static class c implements TimingInfo {
        public final long a;
        public final long b;
        public final boolean c;
        public final boolean d;

        public static final class a {
            public long a;
            public long b;
            public boolean c;
            public boolean d;
        }

        public c(a aVar) {
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
        }

        @Override // org.chromium.components.background_task_scheduler.TaskInfo.TimingInfo
        public final void a(d dVar) {
            dVar.a(this);
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("{intervalMs: ");
            sb.append(this.a);
            sb.append(", ");
            if (this.c) {
                sb.append(", flexMs: ");
                sb.append(this.b);
                sb.append(", ");
            }
            sb.append("expiresAfterWindowEndTime (+flex): ");
            sb.append(this.d);
            sb.append("}");
            return sb.toString();
        }
    }

    public interface d {
        void a(c cVar);

        void b(b bVar);
    }

    public TaskInfo(a aVar) {
        this.a = aVar.a;
        PersistableBundle persistableBundle = aVar.b;
        this.b = persistableBundle == null ? new PersistableBundle() : persistableBundle;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
    }

    @Deprecated
    public static a a(int i, long j, long j2) {
        b.a aVar = new b.a();
        aVar.a = j;
        aVar.c = true;
        aVar.b = j2;
        b bVar = new b(aVar);
        a aVar2 = new a(i);
        aVar2.g = bVar;
        return aVar2;
    }

    public final String toString() {
        return "{taskId: " + this.a + ", extras: " + this.b + ", requiredNetworkType: " + this.c + ", requiresCharging: " + this.d + ", isPersisted: " + this.e + ", updateCurrent: " + this.f + ", timingInfo: " + this.g + "}";
    }
}
