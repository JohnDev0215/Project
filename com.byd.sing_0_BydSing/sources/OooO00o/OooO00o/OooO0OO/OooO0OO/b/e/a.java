package OooO00o.OooO00o.OooO0Oo.OooO0OO.b.e;

import com.android.volley.BuildConfig;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f86b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f88d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f85a = BuildConfig.FLAVOR;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f87c = BuildConfig.FLAVOR;

    public final String a() {
        return this.f87c;
    }

    public final void b(long j) {
        this.f88d = j;
    }

    public final void c(String str) {
        h.d(str, "<set-?>");
        this.f87c = str;
    }

    public final void d(boolean z) {
        this.f86b = z;
    }

    public final void e(String str) {
        h.d(str, "<set-?>");
        this.f85a = str;
    }

    public final boolean f() {
        return this.f86b;
    }

    public final long g() {
        return this.f88d;
    }

    public final String h() {
        return this.f85a;
    }
}
