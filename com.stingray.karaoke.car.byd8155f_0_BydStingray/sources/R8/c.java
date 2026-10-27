package R8;

import h6.r;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f7567m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f7568n;
    public static final c o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ c[] f7569p;
    public final TimeUnit l;

    static {
        c cVar = new c("NANOSECONDS", 0, TimeUnit.NANOSECONDS);
        f7567m = cVar;
        c cVar2 = new c("MICROSECONDS", 1, TimeUnit.MICROSECONDS);
        c cVar3 = new c("MILLISECONDS", 2, TimeUnit.MILLISECONDS);
        f7568n = cVar3;
        c cVar4 = new c("SECONDS", 3, TimeUnit.SECONDS);
        o = cVar4;
        c[] cVarArr = {cVar, cVar2, cVar3, cVar4, new c("MINUTES", 4, TimeUnit.MINUTES), new c("HOURS", 5, TimeUnit.HOURS), new c("DAYS", 6, TimeUnit.DAYS)};
        f7569p = cVarArr;
        r.e(cVarArr);
    }

    public c(String str, int i7, TimeUnit timeUnit) {
        this.l = timeUnit;
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) f7569p.clone();
    }
}
