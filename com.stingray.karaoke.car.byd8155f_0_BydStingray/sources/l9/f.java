package l9;

import org.conscrypt.Conscrypt;

/* JADX INFO: loaded from: classes.dex */
public abstract class f {
    public static boolean a() {
        Conscrypt.Version version = Conscrypt.version();
        return version.major() != 2 ? version.major() > 2 : version.minor() != 1 ? version.minor() > 1 : version.patch() >= 0;
    }

    public static boolean b() {
        return h.d;
    }
}
