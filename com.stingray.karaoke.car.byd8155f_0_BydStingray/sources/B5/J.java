package B5;

import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class J extends L {
    public J(Map<String, Object> map) {
        super(map);
    }

    public static J i() {
        J j3 = new J(new D5.f());
        super.g(UUID.randomUUID().toString(), "anonymousId");
        return j3;
    }

    public final void j(String str) {
        super.g(str, "userId");
    }
}
