package kotlinx.coroutines.internal;

import kotlinx.coroutines.q0;
import kotlinx.coroutines.v0;
import kotlinx.coroutines.v1;

/* JADX INFO: compiled from: MainDispatchers.kt */
/* JADX INFO: loaded from: classes.dex */
final class u extends v1 implements q0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Throwable f7682g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final String f7683h;

    public u(Throwable th, String str) {
        this.f7682g = th;
        this.f7683h = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.Void g0() {
        /*
            r4 = this;
            java.lang.Throwable r0 = r4.f7682g
            if (r0 == 0) goto L37
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Module with the Main dispatcher had failed to initialize"
            r0.append(r1)
            java.lang.String r1 = r4.f7683h
            if (r1 == 0) goto L26
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = ". "
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            if (r1 == 0) goto L26
            goto L28
        L26:
            java.lang.String r1 = ""
        L28:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.Throwable r2 = r4.f7682g
            r1.<init>(r0, r2)
            throw r1
        L37:
            kotlinx.coroutines.internal.t.c()
            g5.e r0 = new g5.e
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.u.g0():java.lang.Void");
    }

    @Override // kotlinx.coroutines.q0
    public v0 N(long j7, Runnable runnable, k5.g gVar) {
        g0();
        throw new g5.e();
    }

    @Override // kotlinx.coroutines.e0
    public boolean c0(k5.g gVar) {
        g0();
        throw new g5.e();
    }

    @Override // kotlinx.coroutines.v1
    public v1 d0() {
        return this;
    }

    @Override // kotlinx.coroutines.e0
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public Void a0(k5.g gVar, Runnable runnable) {
        g0();
        throw new g5.e();
    }

    @Override // kotlinx.coroutines.q0
    /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
    public Void A(long j7, kotlinx.coroutines.i<? super g5.r> iVar) {
        g0();
        throw new g5.e();
    }

    @Override // kotlinx.coroutines.v1, kotlinx.coroutines.e0
    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Dispatchers.Main[missing");
        if (this.f7682g != null) {
            str = ", cause=" + this.f7682g;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }
}
