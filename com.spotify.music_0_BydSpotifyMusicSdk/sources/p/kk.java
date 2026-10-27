package p;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class kk {
    public static final int a;

    static {
        Integer.parseInt("95482");
        List listZ0 = kotlin.text.b.z0("4.4.1.0", new String[]{"."});
        if (listZ0.size() != 4) {
            throw new AssertionError("Invalid AP version code: ".concat("4.4.1.0"));
        }
        int i = Integer.parseInt((String) listZ0.get(0));
        int i2 = Integer.parseInt((String) listZ0.get(1));
        int i3 = Integer.parseInt((String) listZ0.get(2));
        int i4 = Integer.parseInt((String) listZ0.get(3));
        if (i < 20 && i2 < 10 && i3 < 100 && i4 < 100000) {
            a = (i3 * 100000) + (i2 * 10000000) + (i * 100000000) + i4;
            return;
        }
        throw new AssertionError("AP version tag overflow (some parts are mangling other parts): (" + i + ", " + i2 + ", " + i3 + ", " + i4 + ")\nOne possible reason could be that the git tags in the repo are not up to date with upstream.");
    }
}
