package y2;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static /* synthetic */ Class f6004a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e3) {
            throw new NoClassDefFoundError(e3.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = f6004a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f6004a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
