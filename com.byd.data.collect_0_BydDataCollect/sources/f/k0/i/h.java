package f.k0.i;

import f.h0;
import f.z;
import javax.annotation.Nullable;
import okio.BufferedSource;

/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class h extends h0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final String f5242b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final long f5243c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final BufferedSource f5244d;

    public h(@Nullable String str, long j, BufferedSource bufferedSource) {
        this.f5242b = str;
        this.f5243c = j;
        this.f5244d = bufferedSource;
    }

    @Override // f.h0
    public BufferedSource S() {
        return this.f5244d;
    }

    @Override // f.h0
    public long j() {
        return this.f5243c;
    }

    @Override // f.h0
    public z y() {
        String str = this.f5242b;
        if (str != null) {
            return z.d(str);
        }
        return null;
    }
}
