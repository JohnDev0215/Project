package d.a.a;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static /* synthetic */ Class f2632a;

    static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    static BufferedInputStream b(String str) {
        Class clsA = f2632a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f2632a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
