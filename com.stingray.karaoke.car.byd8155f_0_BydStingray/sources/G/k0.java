package G;

import android.os.Build;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final J f2598a;

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        A7.m.e(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        f2598a = lowerCase.equals("robolectric") ? new J() : null;
    }
}
