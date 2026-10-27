package okio;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class ForwardingTimeout extends Timeout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Timeout f5789e;

    public ForwardingTimeout(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5789e = timeout;
    }

    @Override // okio.Timeout
    public Timeout a() {
        return this.f5789e.a();
    }

    @Override // okio.Timeout
    public Timeout b() {
        return this.f5789e.b();
    }

    @Override // okio.Timeout
    public long c() {
        return this.f5789e.c();
    }

    @Override // okio.Timeout
    public Timeout d(long j) {
        return this.f5789e.d(j);
    }

    @Override // okio.Timeout
    public boolean e() {
        return this.f5789e.e();
    }

    @Override // okio.Timeout
    public void g() throws InterruptedIOException {
        this.f5789e.g();
    }

    @Override // okio.Timeout
    public Timeout h(long j, TimeUnit timeUnit) {
        return this.f5789e.h(j, timeUnit);
    }

    @Override // okio.Timeout
    public long i() {
        return this.f5789e.i();
    }

    public final Timeout k() {
        return this.f5789e;
    }

    public final ForwardingTimeout l(Timeout timeout) {
        if (timeout == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f5789e = timeout;
        return this;
    }
}
