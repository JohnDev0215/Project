package p;

import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.InvalidRegistrarException;
import com.google.firebase.components.MissingDependencyException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class gw implements xv, bw {
    public static final fw g = new fw(0);
    public final ci0 d;
    public final cw f;
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final AtomicReference e = new AtomicReference();

    public gw(List list, List list2, cw cwVar) {
        ci0 ci0Var = new ci0();
        this.d = ci0Var;
        this.f = cwVar;
        ArrayList<mv> arrayList = new ArrayList();
        int i = 1;
        arrayList.add(mv.a(ci0Var, ci0.class, sg2.class, bz1.class));
        arrayList.add(mv.a(this, bw.class, new Class[0]));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            mv mvVar = (mv) it.next();
            if (mvVar != null) {
                arrayList.add(mvVar);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList2.add(it2.next());
        }
        ArrayList arrayList3 = new ArrayList();
        synchronized (this) {
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                try {
                    ComponentRegistrar componentRegistrar = (ComponentRegistrar) ((yy1) it3.next()).get();
                    if (componentRegistrar != null) {
                        arrayList.addAll(this.f.f(componentRegistrar));
                        it3.remove();
                    }
                } catch (InvalidRegistrarException unused) {
                    it3.remove();
                }
            }
            if (this.a.isEmpty()) {
                ci1.r(arrayList);
            } else {
                ArrayList arrayList4 = new ArrayList(this.a.keySet());
                arrayList4.addAll(arrayList);
                ci1.r(arrayList4);
            }
            for (mv mvVar2 : arrayList) {
                this.a.put(mvVar2, new w81(new nu0(this, i, mvVar2)));
            }
            arrayList3.addAll(j(arrayList));
            arrayList3.addAll(k());
            i();
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            ((Runnable) it4.next()).run();
        }
        Boolean bool = (Boolean) this.e.get();
        if (bool != null) {
            h(this.a, bool.booleanValue());
        }
    }

    @Override // p.xv
    public final j80 b(dz1 dz1Var) {
        yy1 yy1VarG = g(dz1Var);
        return yy1VarG == null ? new zq1(zq1.c, zq1.d) : yy1VarG instanceof zq1 ? (zq1) yy1VarG : new zq1(null, yy1VarG);
    }

    @Override // p.xv
    public final synchronized yy1 c(dz1 dz1Var) {
        y81 y81Var = (y81) this.c.get(dz1Var);
        if (y81Var != null) {
            return y81Var;
        }
        return g;
    }

    @Override // p.xv
    public final synchronized yy1 g(dz1 dz1Var) {
        if (dz1Var == null) {
            throw new NullPointerException("Null interface requested.");
        }
        return (yy1) this.b.get(dz1Var);
    }

    public final void h(Map map, boolean z) {
        ArrayDeque arrayDeque;
        for (Map.Entry entry : map.entrySet()) {
            mv mvVar = (mv) entry.getKey();
            yy1 yy1Var = (yy1) entry.getValue();
            int i = mvVar.d;
            if (i == 1 || (i == 2 && z)) {
                yy1Var.get();
            }
        }
        ci0 ci0Var = this.d;
        synchronized (ci0Var) {
            arrayDeque = ci0Var.a;
            if (arrayDeque != null) {
                ci0Var.a = null;
            } else {
                arrayDeque = null;
            }
        }
        if (arrayDeque != null) {
            Iterator it = arrayDeque.iterator();
            if (it.hasNext()) {
                u4.z(it.next());
                throw null;
            }
        }
    }

    public final void i() {
        for (mv mvVar : this.a.keySet()) {
            for (d90 d90Var : mvVar.c) {
                boolean z = d90Var.b == 2;
                dz1 dz1Var = d90Var.a;
                if (z) {
                    HashMap map = this.c;
                    if (!map.containsKey(dz1Var)) {
                        Set setEmptySet = Collections.emptySet();
                        y81 y81Var = new y81();
                        y81Var.b = null;
                        y81Var.a = Collections.newSetFromMap(new ConcurrentHashMap());
                        y81Var.a.addAll(setEmptySet);
                        map.put(dz1Var, y81Var);
                    }
                }
                HashMap map2 = this.b;
                if (map2.containsKey(dz1Var)) {
                    continue;
                } else {
                    int i = d90Var.b;
                    if (i == 1) {
                        throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", mvVar, dz1Var));
                    }
                    if (i != 2) {
                        map2.put(dz1Var, new zq1(zq1.c, zq1.d));
                    }
                }
            }
        }
    }

    public final ArrayList j(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            mv mvVar = (mv) it.next();
            if (mvVar.e == 0) {
                yy1 yy1Var = (yy1) this.a.get(mvVar);
                for (dz1 dz1Var : mvVar.b) {
                    HashMap map = this.b;
                    if (map.containsKey(dz1Var)) {
                        arrayList2.add(new ua((zq1) ((yy1) map.get(dz1Var)), 14, yy1Var));
                    } else {
                        map.put(dz1Var, yy1Var);
                    }
                }
            }
        }
        return arrayList2;
    }

    public final ArrayList k() {
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        for (Map.Entry entry : this.a.entrySet()) {
            mv mvVar = (mv) entry.getKey();
            if (mvVar.e != 0) {
                yy1 yy1Var = (yy1) entry.getValue();
                for (dz1 dz1Var : mvVar.b) {
                    if (!map.containsKey(dz1Var)) {
                        map.put(dz1Var, new HashSet());
                    }
                    ((Set) map.get(dz1Var)).add(yy1Var);
                }
            }
        }
        for (Map.Entry entry2 : map.entrySet()) {
            Object key = entry2.getKey();
            HashMap map2 = this.c;
            if (map2.containsKey(key)) {
                y81 y81Var = (y81) map2.get(entry2.getKey());
                Iterator it = ((Set) entry2.getValue()).iterator();
                while (it.hasNext()) {
                    arrayList.add(new ua(y81Var, 15, (yy1) it.next()));
                }
            } else {
                dz1 dz1Var2 = (dz1) entry2.getKey();
                Set set = (Set) ((Collection) entry2.getValue());
                y81 y81Var2 = new y81();
                y81Var2.b = null;
                y81Var2.a = Collections.newSetFromMap(new ConcurrentHashMap());
                y81Var2.a.addAll(set);
                map2.put(dz1Var2, y81Var2);
            }
        }
        return arrayList;
    }
}
