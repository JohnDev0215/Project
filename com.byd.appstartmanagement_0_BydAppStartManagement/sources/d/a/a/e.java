package d.a.a;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static /* synthetic */ Class f782a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = f782a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f782a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
