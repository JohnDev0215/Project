package g7;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes2.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static /* synthetic */ Class f5101a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e8) {
            throw new NoClassDefFoundError(e8.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = f5101a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            f5101a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
