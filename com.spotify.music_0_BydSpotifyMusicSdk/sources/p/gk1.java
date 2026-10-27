package p;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class gk1 {
    public final HashMap a = new LinkedHashMap();

    public final void a(ik1... ik1VarArr) {
        v03.j(ik1VarArr, "migrations");
        for (ik1 ik1Var : ik1VarArr) {
            int i = ik1Var.a;
            HashMap map = this.a;
            Integer numValueOf = Integer.valueOf(i);
            Object treeMap = map.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap();
                map.put(numValueOf, treeMap);
            }
            TreeMap treeMap2 = (TreeMap) treeMap;
            int i2 = ik1Var.b;
            if (treeMap2.containsKey(Integer.valueOf(i2))) {
                Objects.toString(treeMap2.get(Integer.valueOf(i2)));
                ik1Var.toString();
            }
            treeMap2.put(Integer.valueOf(i2), ik1Var);
        }
    }
}
