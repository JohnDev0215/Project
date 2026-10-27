package f.a.a;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static /* synthetic */ Class f4954a;

    static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    static BufferedInputStream b(String str) {
        Class clsA = f4954a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f4954a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
