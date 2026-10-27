package defpackage;

import java.util.HashMap;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabAttributes.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ly2 implements lk3 {
    public static final Object f = new Object();
    public final HashMap e = new HashMap();

    public static ly2 a(Tab tab) {
        mk3 mk3VarB = tab.B();
        ly2 ly2Var = (ly2) mk3VarB.b(ly2.class);
        return ly2Var != null ? ly2Var : (ly2) mk3VarB.d(ly2.class, new ly2());
    }

    public final <T> T b(String str) {
        T t = (T) this.e.get(str);
        if (t != f) {
            return t;
        }
        return null;
    }

    public final void c(Object obj, String str) {
        HashMap map = this.e;
        if (obj == null) {
            obj = f;
        }
        map.put(str, obj);
    }
}
