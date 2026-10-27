package io.sentry;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* JADX INFO: renamed from: io.sentry.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1524x0 implements I0, J0, S, d2, W, X, K {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1524x0 f16986m = new C1524x0(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C1524x0 f16987n = new C1524x0(1);
    public static final C1524x0 o = new C1524x0(2);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C1524x0 f16988p = new C1524x0(3);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C1524x0 f16989q = new C1524x0(4);
    public final /* synthetic */ int l;

    public /* synthetic */ C1524x0(int i7) {
        this.l = i7;
    }

    private final void t() {
    }

    private final void u() {
    }

    private final void v() {
    }

    private final void w() {
    }

    @Override // io.sentry.d2
    public void a(S1 s12) {
    }

    @Override // io.sentry.J0
    public void b() {
    }

    @Override // io.sentry.d2
    public void c(P1 p12) {
    }

    @Override // io.sentry.d2
    public void close() {
        int i7 = this.l;
    }

    @Override // io.sentry.W
    public void d(P1 p12) {
    }

    @Override // io.sentry.W
    public G0 e(P1 p12, List list, F1 f12) {
        return null;
    }

    @Override // io.sentry.S
    public boolean f() {
        return false;
    }

    @Override // io.sentry.J0
    public void g(Boolean bool) {
    }

    @Override // io.sentry.d2
    public void h(S1 s12) {
    }

    @Override // io.sentry.d2
    public List i(V v10) {
        return null;
    }

    @Override // io.sentry.W
    public boolean isRunning() {
        return false;
    }

    @Override // io.sentry.J0
    public void j() {
    }

    @Override // io.sentry.K
    public void k(EnumC1495p1 enumC1495p1, String str, Object... objArr) {
        System.out.println(enumC1495p1 + ": " + String.format(str, objArr));
    }

    @Override // io.sentry.S
    public void l(long j3) {
    }

    @Override // io.sentry.K
    public boolean m(EnumC1495p1 enumC1495p1) {
        return true;
    }

    @Override // io.sentry.I0
    public io.sentry.rrweb.b n(C1457d c1457d) {
        return null;
    }

    @Override // io.sentry.X
    public io.sentry.transport.f o(F1 f12, io.sentry.internal.debugmeta.c cVar) {
        return new io.sentry.transport.c(f12, new io.sentry.transport.m(f12), f12.getTransportGate(), cVar);
    }

    @Override // io.sentry.K
    public void p(EnumC1495p1 enumC1495p1, Throwable th, String str, Object... objArr) {
        PrintStream printStream = System.out;
        String str2 = String.format(str, objArr);
        String string = th.toString();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        printStream.println(enumC1495p1 + ": " + str2 + " \n " + string + "\n" + stringWriter.toString());
    }

    @Override // io.sentry.J0
    /* JADX INFO: renamed from: q */
    public I0 getF16325w() {
        return f16986m;
    }

    @Override // io.sentry.S
    public Future r(Runnable runnable, long j3) {
        return new FutureTask(new CallableC1526y(1));
    }

    @Override // io.sentry.K
    public void s(EnumC1495p1 enumC1495p1, String str, Throwable th) {
        if (th == null) {
            k(enumC1495p1, str, new Object[0]);
            return;
        }
        PrintStream printStream = System.out;
        String str2 = String.format(str, th.toString());
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        printStream.println(enumC1495p1 + ": " + str2 + "\n" + stringWriter.toString());
    }

    @Override // io.sentry.J0
    public void start() {
        int i7 = this.l;
    }

    @Override // io.sentry.J0
    public void stop() {
    }

    @Override // io.sentry.S
    public Future submit(Runnable runnable) {
        return new FutureTask(new CallableC1526y(1));
    }
}
