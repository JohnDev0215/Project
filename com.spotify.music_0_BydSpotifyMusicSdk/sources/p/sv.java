package p;

import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/* JADX INFO: loaded from: classes.dex */
public final class sv {
    public Random a = new Random();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public ArrayList e = new ArrayList();
    public final transient HashMap f = new HashMap();
    public final HashMap g = new HashMap();
    public final Bundle h = new Bundle();

    public final boolean a(int i, int i2, Intent intent) {
        s4 s4Var;
        String str = (String) this.b.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        w4 w4Var = (w4) this.f.get(str);
        if (w4Var == null || (s4Var = w4Var.a) == null || !this.e.contains(str)) {
            this.g.remove(str);
            this.h.putParcelable(str, new r4(intent, i2));
        } else {
            s4Var.b(w4Var.b.n(intent, i2));
            this.e.remove(str);
        }
        return true;
    }

    public final v4 b(String str, q30 q30Var, s4 s4Var) {
        int i;
        HashMap map;
        HashMap map2 = this.c;
        if (((Integer) map2.get(str)) == null) {
            int iNextInt = this.a.nextInt(2147418112);
            while (true) {
                i = iNextInt + 65536;
                map = this.b;
                if (!map.containsKey(Integer.valueOf(i))) {
                    break;
                }
                iNextInt = this.a.nextInt(2147418112);
            }
            map.put(Integer.valueOf(i), str);
            map2.put(str, Integer.valueOf(i));
        }
        this.f.put(str, new w4(s4Var, q30Var));
        HashMap map3 = this.g;
        if (map3.containsKey(str)) {
            Object obj = map3.get(str);
            map3.remove(str);
            s4Var.b(obj);
        }
        Bundle bundle = this.h;
        r4 r4Var = (r4) bundle.getParcelable(str);
        if (r4Var != null) {
            bundle.remove(str);
            s4Var.b(q30Var.n(r4Var.e, r4Var.c));
        }
        return new v4(this, str, q30Var);
    }
}
