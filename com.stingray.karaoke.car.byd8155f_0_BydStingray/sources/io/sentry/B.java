package io.sentry;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public final class B {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f15816g = TimeUnit.HOURS.toMillis(5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f15817h = TimeUnit.SECONDS.toMillis(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static B f15818i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f15819a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile String f15820b;
    public volatile long c;
    public final AtomicBoolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final CallableC1526y f15821e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ExecutorService f15822f;

    public B() {
        CallableC1526y callableC1526y = new CallableC1526y(0);
        this.d = new AtomicBoolean(false);
        this.f15822f = Executors.newSingleThreadExecutor(new A(0));
        this.f15819a = f15816g;
        this.f15821e = callableC1526y;
        a();
    }

    public final void a() {
        try {
            this.f15822f.submit(new CallableC1529z(this, 0)).get(f15817h, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            this.c = TimeUnit.SECONDS.toMillis(1L) + System.currentTimeMillis();
        } catch (RuntimeException | ExecutionException | TimeoutException unused2) {
            this.c = TimeUnit.SECONDS.toMillis(1L) + System.currentTimeMillis();
        }
    }
}
