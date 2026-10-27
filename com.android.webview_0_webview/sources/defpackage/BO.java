package defpackage;

import android.content.res.Resources;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BO {
    public final Map a;

    public BO(InterfaceC1821yO... interfaceC1821yOArr) {
        HashMap map = new HashMap();
        for (InterfaceC1821yO interfaceC1821yO : interfaceC1821yOArr) {
            if (map.containsKey(interfaceC1821yO)) {
                throw new IllegalArgumentException("Duplicate key: " + interfaceC1821yO);
            }
            map.put(interfaceC1821yO, null);
        }
        this.a = map;
    }

    public JO a() {
        return new JO(this.a, null);
    }

    public BO b(EO eo, boolean z) {
        AO ao = new AO(null);
        ao.a = z;
        this.a.put(eo, ao);
        return this;
    }

    public BO c(FO fo, Resources resources, int i) {
        if (i != 0) {
            d(fo, resources.getString(i));
        }
        return this;
    }

    public BO d(FO fo, Object obj) {
        DO r0 = new DO(null);
        r0.a = obj;
        this.a.put(fo, r0);
        return this;
    }
}
