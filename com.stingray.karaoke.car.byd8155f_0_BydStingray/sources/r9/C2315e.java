package r9;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: renamed from: r9.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2315e extends L {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ReentrantLock f21919h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Condition f21920i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final long f21921j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final long f21922k;
    public static C2315e l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f21923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C2315e f21924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f21925g;

    static {
        ReentrantLock reentrantLock = new ReentrantLock();
        f21919h = reentrantLock;
        Condition conditionNewCondition = reentrantLock.newCondition();
        A7.m.e(conditionNewCondition, "newCondition(...)");
        f21920i = conditionNewCondition;
        long millis = TimeUnit.SECONDS.toMillis(60L);
        f21921j = millis;
        f21922k = TimeUnit.MILLISECONDS.toNanos(millis);
    }

    public final void i() {
        long j3 = this.c;
        boolean z6 = this.f21909a;
        if (j3 != 0 || z6) {
            ReentrantLock reentrantLock = f21919h;
            reentrantLock.lock();
            try {
                if (this.f21923e != 0) {
                    throw new IllegalStateException("Unbalanced enter/exit");
                }
                this.f21923e = 1;
                l9.n.C(this, j3, z6);
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public final boolean j() {
        ReentrantLock reentrantLock = f21919h;
        reentrantLock.lock();
        try {
            int i7 = this.f21923e;
            this.f21923e = 0;
            if (i7 != 1) {
                return i7 == 2;
            }
            C2315e c2315e = l;
            while (c2315e != null) {
                C2315e c2315e2 = c2315e.f21924f;
                if (c2315e2 == this) {
                    c2315e.f21924f = this.f21924f;
                    this.f21924f = null;
                    return false;
                }
                c2315e = c2315e2;
            }
            throw new IllegalStateException("node was not found in the queue");
        } finally {
            reentrantLock.unlock();
        }
    }

    public void k() {
    }
}
