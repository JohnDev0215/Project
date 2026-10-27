package c.a.a;

import java.io.BufferedInputStream;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static /* synthetic */ Class f964a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = f964a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f964a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
