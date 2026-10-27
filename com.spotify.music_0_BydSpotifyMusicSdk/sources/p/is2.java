package p;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class is2 {
    public static final long b = TimeUnit.HOURS.toSeconds(1);
    public static final Pattern c = Pattern.compile("\\AA[\\w-]{38}\\z");
    public static is2 d;
    public final z90 a;

    public is2(z90 z90Var) {
        this.a = z90Var;
    }

    public static is2 a() {
        if (z90.e == null) {
            z90.e = new z90(20);
        }
        z90 z90Var = z90.e;
        if (d == null) {
            d = new is2(z90Var);
        }
        return d;
    }
}
