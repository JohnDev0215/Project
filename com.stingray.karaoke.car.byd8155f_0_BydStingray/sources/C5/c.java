package C5;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c f1003m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final c f1004n;
    public static final /* synthetic */ c[] o;

    /* JADX INFO: Fake field, exist only in values array */
    c EF0;

    static {
        c cVar = new c("alias", 0);
        c cVar2 = new c("group", 1);
        c cVar3 = new c("identify", 2);
        l = cVar3;
        c cVar4 = new c("screen", 3);
        f1003m = cVar4;
        c cVar5 = new c("track", 4);
        f1004n = cVar5;
        o = new c[]{cVar, cVar2, cVar3, cVar4, cVar5};
    }

    public static c valueOf(String str) {
        return (c) Enum.valueOf(c.class, str);
    }

    public static c[] values() {
        return (c[]) o.clone();
    }
}
