package defpackage;

import java.io.BufferedInputStream;

/* JADX INFO: loaded from: classes.dex */
public class ul {
    public static /* synthetic */ Class a;

    public static /* synthetic */ Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public static BufferedInputStream b(String str) {
        Class clsA = a;
        if (clsA == null) {
            clsA = a("net.sourceforge.pinyin4j.ResourceHelper");
            a = clsA;
        }
        return new BufferedInputStream(clsA.getResourceAsStream(str));
    }
}
