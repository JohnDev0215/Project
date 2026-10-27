package p;

import android.content.Context;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ei0 {
    public final List a;

    public ei0(Context context, wg1 wg1Var, wc1 wc1Var, g90 g90Var, v4 v4Var, List list, ij0 ij0Var) {
        v03.j(wc1Var, "logger");
        v03.j(list, "additionalEventContextProviders");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("context_monotonic_clock", new al1(new zb0(), new jv(wg1Var, new no1(wg1Var, new ay2(context, wc1Var)))));
        linkedHashMap.put("context_device_android", new k90(g90Var));
        z41 z41Var = new z41(1, ij0Var);
        linkedHashMap.put(z41Var.a(), z41Var);
        linkedHashMap.put("context_application_android", new cb(context, wc1Var));
        z41 z41Var2 = new z41(0, v4Var);
        linkedHashMap.put(z41Var2.a(), z41Var2);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            a(linkedHashMap, (di0) it.next());
        }
        this.a = ou.n0(linkedHashMap.values());
    }

    public static void a(LinkedHashMap linkedHashMap, di0 di0Var) {
        linkedHashMap.put(di0Var.a(), di0Var);
    }
}
