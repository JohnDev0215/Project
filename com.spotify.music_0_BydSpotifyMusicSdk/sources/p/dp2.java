package p;

import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class dp2 {
    public String a;
    public int b;
    public String c;
    public HashMap d;

    public final ep2 a() {
        String str = this.a;
        ci1.C(str, "action name");
        int i = this.b;
        String str2 = this.c;
        ci1.C(str2, "interaction type");
        return new ep2(str, i, str2, this.d);
    }

    public final void b(Object obj, String str) {
        HashMap map = this.d;
        String string = obj != null ? obj.toString() : null;
        if (string == null) {
            string = "";
        }
        map.put(str, string);
    }
}
