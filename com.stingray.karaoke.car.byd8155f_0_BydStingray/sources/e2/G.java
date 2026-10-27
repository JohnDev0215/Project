package e2;

import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final HashSet f13882a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f13883b = "media3.common";

    public static synchronized void a(String str) {
        if (f13882a.add(str)) {
            f13883b += ", " + str;
        }
    }
}
