package e.a.a;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static /* synthetic */ Class f4981a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError(e2.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = f4981a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f4981a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
