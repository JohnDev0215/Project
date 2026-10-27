package q0;

import A7.m;
import android.os.Build;
import java.util.Locale;

/* JADX INFO: renamed from: q0.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2166e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f21158a = 0;

    static {
        String lowerCase = Build.FINGERPRINT.toLowerCase(Locale.ROOT);
        m.e(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        lowerCase.equals("robolectric");
    }
}
