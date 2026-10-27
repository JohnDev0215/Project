package d2;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: d2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1065c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1066d f13335a = new C1066d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f13336b = new LinkedHashMap();
    public final LinkedHashSet c = new LinkedHashSet();
    public volatile boolean d;

    public static void a(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e10) {
                throw new RuntimeException(e10);
            }
        }
    }
}
