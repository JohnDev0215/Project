package p;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle$State;
import com.spotify.music.R;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class ox0 {
    public v4 A;
    public v4 B;
    public ArrayDeque C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public ArrayList I;
    public ArrayList J;
    public ArrayList K;
    public qx0 L;
    public final rv M;
    public boolean b;
    public ArrayList d;
    public ArrayList e;
    public androidx.activity.b g;
    public final ww1 l;
    public final CopyOnWriteArrayList m;
    public final ex0 n;
    public final ex0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ex0 f165p;
    public final ex0 q;
    public final hx0 r;
    public int s;
    public yw0 t;
    public bk0 u;
    public ww0 v;
    public ww0 w;
    public final ix0 x;
    public final p01 y;
    public v4 z;
    public final ArrayList a = new ArrayList();
    public final gh2 c = new gh2(4);
    public final dx0 f = new dx0(this);
    public final gx0 h = new gx0(this);
    public final AtomicInteger i = new AtomicInteger();
    public final Map j = Collections.synchronizedMap(new HashMap());
    public final Map k = Collections.synchronizedMap(new HashMap());

    /* JADX WARN: Type inference failed for: r0v12, types: [p.ex0] */
    /* JADX WARN: Type inference failed for: r0v13, types: [p.ex0] */
    /* JADX WARN: Type inference failed for: r0v14, types: [p.ex0] */
    /* JADX WARN: Type inference failed for: r0v15, types: [p.ex0] */
    public ox0() {
        Collections.synchronizedMap(new HashMap());
        this.l = new ww1(this);
        this.m = new CopyOnWriteArrayList();
        final int i = 0;
        this.n = new cz(this) { // from class: p.ex0
            public final /* synthetic */ ox0 b;

            {
                this.b = this;
            }

            @Override // p.cz
            public final void accept(Object obj) {
                int i2 = i;
                ox0 ox0Var = this.b;
                switch (i2) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        if (ox0Var.G()) {
                            ox0Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        if (ox0Var.G() && num.intValue() == 80) {
                            ox0Var.l(false);
                            break;
                        }
                        break;
                    case 2:
                        tl1 tl1Var = (tl1) obj;
                        if (ox0Var.G()) {
                            ox0Var.m(tl1Var.a, false);
                        }
                        break;
                    default:
                        xs1 xs1Var = (xs1) obj;
                        if (ox0Var.G()) {
                            ox0Var.r(xs1Var.a, false);
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        this.o = new cz(this) { // from class: p.ex0
            public final /* synthetic */ ox0 b;

            {
                this.b = this;
            }

            @Override // p.cz
            public final void accept(Object obj) {
                int i22 = i2;
                ox0 ox0Var = this.b;
                switch (i22) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        if (ox0Var.G()) {
                            ox0Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        if (ox0Var.G() && num.intValue() == 80) {
                            ox0Var.l(false);
                            break;
                        }
                        break;
                    case 2:
                        tl1 tl1Var = (tl1) obj;
                        if (ox0Var.G()) {
                            ox0Var.m(tl1Var.a, false);
                        }
                        break;
                    default:
                        xs1 xs1Var = (xs1) obj;
                        if (ox0Var.G()) {
                            ox0Var.r(xs1Var.a, false);
                        }
                        break;
                }
            }
        };
        final int i3 = 2;
        this.f165p = new cz(this) { // from class: p.ex0
            public final /* synthetic */ ox0 b;

            {
                this.b = this;
            }

            @Override // p.cz
            public final void accept(Object obj) {
                int i22 = i3;
                ox0 ox0Var = this.b;
                switch (i22) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        if (ox0Var.G()) {
                            ox0Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        if (ox0Var.G() && num.intValue() == 80) {
                            ox0Var.l(false);
                            break;
                        }
                        break;
                    case 2:
                        tl1 tl1Var = (tl1) obj;
                        if (ox0Var.G()) {
                            ox0Var.m(tl1Var.a, false);
                        }
                        break;
                    default:
                        xs1 xs1Var = (xs1) obj;
                        if (ox0Var.G()) {
                            ox0Var.r(xs1Var.a, false);
                        }
                        break;
                }
            }
        };
        final int i4 = 3;
        this.q = new cz(this) { // from class: p.ex0
            public final /* synthetic */ ox0 b;

            {
                this.b = this;
            }

            @Override // p.cz
            public final void accept(Object obj) {
                int i22 = i4;
                ox0 ox0Var = this.b;
                switch (i22) {
                    case 0:
                        Configuration configuration = (Configuration) obj;
                        if (ox0Var.G()) {
                            ox0Var.h(false, configuration);
                        }
                        break;
                    case 1:
                        Integer num = (Integer) obj;
                        if (ox0Var.G() && num.intValue() == 80) {
                            ox0Var.l(false);
                            break;
                        }
                        break;
                    case 2:
                        tl1 tl1Var = (tl1) obj;
                        if (ox0Var.G()) {
                            ox0Var.m(tl1Var.a, false);
                        }
                        break;
                    default:
                        xs1 xs1Var = (xs1) obj;
                        if (ox0Var.G()) {
                            ox0Var.r(xs1Var.a, false);
                        }
                        break;
                }
            }
        };
        this.r = new hx0(this);
        this.s = -1;
        this.x = new ix0(this);
        this.y = new p01(10, this);
        this.C = new ArrayDeque();
        this.M = new rv(7, this);
    }

    public static boolean F(ww0 ww0Var) {
        boolean zF = false;
        for (ww0 ww0Var2 : ww0Var.B.c.o()) {
            if (ww0Var2 != null) {
                zF = F(ww0Var2);
            }
            if (zF) {
                return true;
            }
        }
        return false;
    }

    public static boolean H(ww0 ww0Var) {
        if (ww0Var == null) {
            return true;
        }
        return ww0Var.J && (ww0Var.z == null || H(ww0Var.C));
    }

    public static boolean I(ww0 ww0Var) {
        if (ww0Var == null) {
            return true;
        }
        ox0 ox0Var = ww0Var.z;
        return ww0Var.equals(ox0Var.w) && I(ox0Var.v);
    }

    public final ww0 A(int i) {
        gh2 gh2Var = this.c;
        for (int size = ((ArrayList) gh2Var.l).size() - 1; size >= 0; size--) {
            ww0 ww0Var = (ww0) ((ArrayList) gh2Var.l).get(size);
            if (ww0Var != null && ww0Var.D == i) {
                return ww0Var;
            }
        }
        for (androidx.fragment.app.b bVar : ((HashMap) gh2Var.e).values()) {
            if (bVar != null) {
                ww0 ww0Var2 = bVar.c;
                if (ww0Var2.D == i) {
                    return ww0Var2;
                }
            }
        }
        return null;
    }

    public final ViewGroup B(ww0 ww0Var) {
        ViewGroup viewGroup = ww0Var.L;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (ww0Var.E > 0 && this.u.P()) {
            View viewO = this.u.O(ww0Var.E);
            if (viewO instanceof ViewGroup) {
                return (ViewGroup) viewO;
            }
        }
        return null;
    }

    public final ix0 C() {
        ww0 ww0Var = this.v;
        return ww0Var != null ? ww0Var.z.C() : this.x;
    }

    public final p01 D() {
        ww0 ww0Var = this.v;
        return ww0Var != null ? ww0Var.z.D() : this.y;
    }

    public final void E(ww0 ww0Var) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
        }
        if (ww0Var.G) {
            return;
        }
        ww0Var.G = true;
        ww0Var.Q = true ^ ww0Var.Q;
        W(ww0Var);
    }

    public final boolean G() {
        ww0 ww0Var = this.v;
        if (ww0Var == null) {
            return true;
        }
        return ww0Var.m() && this.v.h().G();
    }

    public final void J(int i, boolean z) {
        yw0 yw0Var;
        if (this.t == null && i != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.s) {
            this.s = i;
            gh2 gh2Var = this.c;
            Iterator it = ((ArrayList) gh2Var.l).iterator();
            while (it.hasNext()) {
                androidx.fragment.app.b bVar = (androidx.fragment.app.b) ((HashMap) gh2Var.e).get(((ww0) it.next()).m);
                if (bVar != null) {
                    bVar.k();
                }
            }
            for (androidx.fragment.app.b bVar2 : ((HashMap) gh2Var.e).values()) {
                if (bVar2 != null) {
                    bVar2.k();
                    ww0 ww0Var = bVar2.c;
                    if (ww0Var.t && !ww0Var.o()) {
                        gh2Var.u(bVar2);
                    }
                }
            }
            X();
            if (this.D && (yw0Var = this.t) != null && this.s == 7) {
                yw0Var.u.invalidateOptionsMenu();
                this.D = false;
            }
        }
    }

    public final void K() {
        if (this.t == null) {
            return;
        }
        this.E = false;
        this.F = false;
        this.L.h = false;
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null) {
                ww0Var.B.K();
            }
        }
    }

    public final boolean L(int i, int i2) {
        x(false);
        w(true);
        ww0 ww0Var = this.w;
        if (ww0Var != null && i < 0 && ww0Var.d().M()) {
            return true;
        }
        boolean zN = N(this.I, this.J, i, i2);
        if (zN) {
            this.b = true;
            try {
                P(this.I, this.J);
            } finally {
                d();
            }
        }
        a0();
        if (this.H) {
            this.H = false;
            X();
        }
        ((HashMap) this.c.e).values().removeAll(Collections.singleton(null));
        return zN;
    }

    public final boolean M() {
        return L(-1, 0);
    }

    public final boolean N(ArrayList arrayList, ArrayList arrayList2, int i, int i2) {
        boolean z = (i2 & 1) != 0;
        ArrayList arrayList3 = this.d;
        int size = -1;
        if (arrayList3 != null && !arrayList3.isEmpty()) {
            if (i < 0) {
                size = z ? 0 : this.d.size() - 1;
            } else {
                int size2 = this.d.size() - 1;
                while (size2 >= 0) {
                    sk skVar = (sk) this.d.get(size2);
                    if (i >= 0 && i == skVar.r) {
                        break;
                    }
                    size2--;
                }
                if (size2 >= 0) {
                    if (z) {
                        while (size2 > 0) {
                            sk skVar2 = (sk) this.d.get(size2 - 1);
                            if (i < 0 || i != skVar2.r) {
                                break;
                            }
                            size2--;
                        }
                    } else if (size2 != this.d.size() - 1) {
                        size2++;
                    }
                    size = size2;
                } else {
                    size = size2;
                }
            }
        }
        if (size < 0) {
            return false;
        }
        for (int size3 = this.d.size() - 1; size3 >= size; size3--) {
            arrayList.add((sk) this.d.remove(size3));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public final void O(ww0 ww0Var) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
        }
        boolean z = !ww0Var.o();
        if (!ww0Var.H || z) {
            this.c.v(ww0Var);
            if (F(ww0Var)) {
                this.D = true;
            }
            ww0Var.t = true;
            W(ww0Var);
        }
    }

    public final void P(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            if (!((sk) arrayList.get(i)).o) {
                if (i2 != i) {
                    z(arrayList, arrayList2, i2, i);
                }
                i2 = i + 1;
                if (((Boolean) arrayList2.get(i)).booleanValue()) {
                    while (i2 < size && ((Boolean) arrayList2.get(i2)).booleanValue() && !((sk) arrayList.get(i2)).o) {
                        i2++;
                    }
                }
                z(arrayList, arrayList2, i, i2);
                i = i2 - 1;
            }
            i++;
        }
        if (i2 != size) {
            z(arrayList, arrayList2, i2, size);
        }
    }

    public final void Q(Bundle bundle) {
        ww1 ww1Var;
        int i;
        androidx.fragment.app.b bVar;
        Bundle bundle2;
        Bundle bundle3;
        for (String str : bundle.keySet()) {
            if (str.startsWith("result_") && (bundle3 = bundle.getBundle(str)) != null) {
                bundle3.setClassLoader(this.t.r.getClassLoader());
                this.k.put(str.substring(7), bundle3);
            }
        }
        HashMap map = new HashMap();
        for (String str2 : bundle.keySet()) {
            if (str2.startsWith("fragment_") && (bundle2 = bundle.getBundle(str2)) != null) {
                bundle2.setClassLoader(this.t.r.getClassLoader());
                map.put(str2.substring(9), bundle2);
            }
        }
        gh2 gh2Var = this.c;
        ((HashMap) gh2Var.k).clear();
        ((HashMap) gh2Var.k).putAll(map);
        px0 px0Var = (px0) bundle.getParcelable("state");
        if (px0Var == null) {
            return;
        }
        ((HashMap) gh2Var.e).clear();
        Iterator it = px0Var.c.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            ww1Var = this.l;
            if (!zHasNext) {
                break;
            }
            Bundle bundleW = gh2Var.w(null, (String) it.next());
            if (bundleW != null) {
                ww0 ww0Var = (ww0) this.L.c.get(((sx0) bundleW.getParcelable("state")).e);
                if (ww0Var != null) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        ww0Var.toString();
                    }
                    bVar = new androidx.fragment.app.b(ww1Var, gh2Var, ww0Var, bundleW);
                } else {
                    bVar = new androidx.fragment.app.b(this.l, this.c, this.t.r.getClassLoader(), C(), bundleW);
                }
                ww0 ww0Var2 = bVar.c;
                ww0Var2.e = bundleW;
                ww0Var2.z = this;
                if (Log.isLoggable("FragmentManager", 2)) {
                    ww0Var2.toString();
                }
                bVar.m(this.t.r.getClassLoader());
                gh2Var.t(bVar);
                bVar.e = this.s;
            }
        }
        qx0 qx0Var = this.L;
        qx0Var.getClass();
        for (ww0 ww0Var3 : new ArrayList(qx0Var.c.values())) {
            if (((HashMap) gh2Var.e).get(ww0Var3.m) == null) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    ww0Var3.toString();
                    Objects.toString(px0Var.c);
                }
                this.L.d(ww0Var3);
                ww0Var3.z = this;
                androidx.fragment.app.b bVar2 = new androidx.fragment.app.b(ww1Var, gh2Var, ww0Var3);
                bVar2.e = 1;
                bVar2.k();
                ww0Var3.t = true;
                bVar2.k();
            }
        }
        ArrayList<String> arrayList = px0Var.e;
        ((ArrayList) gh2Var.l).clear();
        if (arrayList != null) {
            for (String str3 : arrayList) {
                ww0 ww0VarJ = gh2Var.j(str3);
                if (ww0VarJ == null) {
                    throw new IllegalStateException(u4.j("No instantiated fragment for (", str3, ")"));
                }
                if (Log.isLoggable("FragmentManager", 2)) {
                    ww0VarJ.toString();
                }
                gh2Var.f(ww0VarJ);
            }
        }
        if (px0Var.k != null) {
            this.d = new ArrayList(px0Var.k.length);
            int i2 = 0;
            while (true) {
                uk[] ukVarArr = px0Var.k;
                if (i2 >= ukVarArr.length) {
                    break;
                }
                uk ukVar = ukVarArr[i2];
                ukVar.getClass();
                sk skVar = new sk(this);
                int i3 = 0;
                int i4 = 0;
                while (true) {
                    int[] iArr = ukVar.c;
                    if (i3 >= iArr.length) {
                        break;
                    }
                    vx0 vx0Var = new vx0();
                    int i5 = i3 + 1;
                    vx0Var.a = iArr[i3];
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Objects.toString(skVar);
                        int i6 = iArr[i5];
                    }
                    vx0Var.h = Lifecycle$State.values()[ukVar.k[i4]];
                    vx0Var.i = Lifecycle$State.values()[ukVar.l[i4]];
                    int i7 = i3 + 2;
                    vx0Var.c = iArr[i5] != 0;
                    int i8 = iArr[i7];
                    vx0Var.d = i8;
                    int i9 = iArr[i3 + 3];
                    vx0Var.e = i9;
                    int i10 = i3 + 5;
                    int i11 = iArr[i3 + 4];
                    vx0Var.f = i11;
                    i3 += 6;
                    int i12 = iArr[i10];
                    vx0Var.g = i12;
                    skVar.b = i8;
                    skVar.c = i9;
                    skVar.d = i11;
                    skVar.e = i12;
                    skVar.b(vx0Var);
                    i4++;
                }
                skVar.f = ukVar.m;
                skVar.h = ukVar.n;
                skVar.g = true;
                skVar.i = ukVar.f196p;
                skVar.j = ukVar.q;
                skVar.k = ukVar.r;
                skVar.l = ukVar.s;
                skVar.m = ukVar.t;
                skVar.n = ukVar.u;
                skVar.o = ukVar.v;
                skVar.r = ukVar.o;
                int i13 = 0;
                while (true) {
                    ArrayList arrayList2 = ukVar.e;
                    if (i13 >= arrayList2.size()) {
                        break;
                    }
                    String str4 = (String) arrayList2.get(i13);
                    if (str4 != null) {
                        ((vx0) skVar.a.get(i13)).b = gh2Var.j(str4);
                    }
                    i13++;
                }
                skVar.c(1);
                if (Log.isLoggable("FragmentManager", 2)) {
                    skVar.toString();
                    PrintWriter printWriter = new PrintWriter(new sc1());
                    skVar.e("  ", printWriter, false);
                    printWriter.close();
                }
                this.d.add(skVar);
                i2++;
            }
            i = 0;
        } else {
            i = 0;
            this.d = null;
        }
        this.i.set(px0Var.l);
        String str5 = px0Var.m;
        if (str5 != null) {
            ww0 ww0VarJ2 = gh2Var.j(str5);
            this.w = ww0VarJ2;
            q(ww0VarJ2);
        }
        ArrayList arrayList3 = px0Var.n;
        if (arrayList3 != null) {
            for (int i14 = i; i14 < arrayList3.size(); i14++) {
                this.j.put((String) arrayList3.get(i14), (vk) px0Var.o.get(i14));
            }
        }
        this.C = new ArrayDeque(px0Var.f169p);
    }

    public final Bundle R() {
        int i;
        ArrayList arrayList;
        uk[] ukVarArr;
        int size;
        Bundle bundle = new Bundle();
        Iterator it = e().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            df2 df2Var = (df2) it.next();
            if (df2Var.e) {
                Log.isLoggable("FragmentManager", 2);
                df2Var.e = false;
                df2Var.c();
            }
        }
        Iterator it2 = e().iterator();
        while (it2.hasNext()) {
            ((df2) it2.next()).e();
        }
        x(true);
        this.E = true;
        this.L.h = true;
        gh2 gh2Var = this.c;
        gh2Var.getClass();
        ArrayList arrayList2 = new ArrayList(((HashMap) gh2Var.e).size());
        for (androidx.fragment.app.b bVar : ((HashMap) gh2Var.e).values()) {
            if (bVar != null) {
                ww0 ww0Var = bVar.c;
                gh2Var.w(bVar.o(), ww0Var.m);
                arrayList2.add(ww0Var.m);
                if (Log.isLoggable("FragmentManager", 2)) {
                    ww0Var.toString();
                    Objects.toString(ww0Var.e);
                }
            }
        }
        HashMap map = (HashMap) this.c.k;
        if (map.isEmpty()) {
            Log.isLoggable("FragmentManager", 2);
        } else {
            gh2 gh2Var2 = this.c;
            synchronized (((ArrayList) gh2Var2.l)) {
                try {
                    if (((ArrayList) gh2Var2.l).isEmpty()) {
                        arrayList = null;
                    } else {
                        arrayList = new ArrayList(((ArrayList) gh2Var2.l).size());
                        for (ww0 ww0Var2 : (ArrayList) gh2Var2.l) {
                            arrayList.add(ww0Var2.m);
                            if (Log.isLoggable("FragmentManager", 2)) {
                                ww0Var2.toString();
                            }
                        }
                    }
                } finally {
                }
            }
            ArrayList arrayList3 = this.d;
            if (arrayList3 == null || (size = arrayList3.size()) <= 0) {
                ukVarArr = null;
            } else {
                ukVarArr = new uk[size];
                for (i = 0; i < size; i++) {
                    ukVarArr[i] = new uk((sk) this.d.get(i));
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Objects.toString(this.d.get(i));
                    }
                }
            }
            px0 px0Var = new px0();
            px0Var.m = null;
            ArrayList arrayList4 = new ArrayList();
            px0Var.n = arrayList4;
            ArrayList arrayList5 = new ArrayList();
            px0Var.o = arrayList5;
            px0Var.c = arrayList2;
            px0Var.e = arrayList;
            px0Var.k = ukVarArr;
            px0Var.l = this.i.get();
            ww0 ww0Var3 = this.w;
            if (ww0Var3 != null) {
                px0Var.m = ww0Var3.m;
            }
            arrayList4.addAll(this.j.keySet());
            arrayList5.addAll(this.j.values());
            px0Var.f169p = new ArrayList(this.C);
            bundle.putParcelable("state", px0Var);
            for (String str : this.k.keySet()) {
                bundle.putBundle(u4.x("result_", str), (Bundle) this.k.get(str));
            }
            for (String str2 : map.keySet()) {
                bundle.putBundle(u4.x("fragment_", str2), (Bundle) map.get(str2));
            }
        }
        return bundle;
    }

    public final void S() {
        synchronized (this.a) {
            try {
                if (this.a.size() == 1) {
                    this.t.s.removeCallbacks(this.M);
                    this.t.s.post(this.M);
                    a0();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void T(ww0 ww0Var, boolean z) {
        ViewGroup viewGroupB = B(ww0Var);
        if (viewGroupB == null || !(viewGroupB instanceof bx0)) {
            return;
        }
        ((bx0) viewGroupB).setDrawDisappearingViewsLast(!z);
    }

    public final void U(ww0 ww0Var, Lifecycle$State lifecycle$State) {
        if (ww0Var.equals(this.c.j(ww0Var.m)) && (ww0Var.A == null || ww0Var.z == this)) {
            ww0Var.T = lifecycle$State;
            return;
        }
        throw new IllegalArgumentException("Fragment " + ww0Var + " is not an active fragment of FragmentManager " + this);
    }

    public final void V(ww0 ww0Var) {
        if (ww0Var != null) {
            if (!ww0Var.equals(this.c.j(ww0Var.m)) || (ww0Var.A != null && ww0Var.z != this)) {
                throw new IllegalArgumentException("Fragment " + ww0Var + " is not an active fragment of FragmentManager " + this);
            }
        }
        ww0 ww0Var2 = this.w;
        this.w = ww0Var;
        q(ww0Var2);
        q(this.w);
    }

    public final void W(ww0 ww0Var) {
        ViewGroup viewGroupB = B(ww0Var);
        if (viewGroupB != null) {
            uw0 uw0Var = ww0Var.P;
            if ((uw0Var == null ? 0 : uw0Var.e) + (uw0Var == null ? 0 : uw0Var.d) + (uw0Var == null ? 0 : uw0Var.c) + (uw0Var == null ? 0 : uw0Var.b) > 0) {
                if (viewGroupB.getTag(R.id.visible_removing_fragment_view_tag) == null) {
                    viewGroupB.setTag(R.id.visible_removing_fragment_view_tag, ww0Var);
                }
                ww0 ww0Var2 = (ww0) viewGroupB.getTag(R.id.visible_removing_fragment_view_tag);
                uw0 uw0Var2 = ww0Var.P;
                boolean z = uw0Var2 != null ? uw0Var2.a : false;
                if (ww0Var2.P == null) {
                    return;
                }
                ww0Var2.c().a = z;
            }
        }
    }

    public final void X() {
        for (androidx.fragment.app.b bVar : this.c.n()) {
            ww0 ww0Var = bVar.c;
            if (ww0Var.N) {
                if (this.b) {
                    this.H = true;
                } else {
                    ww0Var.N = false;
                    bVar.k();
                }
            }
        }
    }

    public final void Y(IllegalStateException illegalStateException) {
        Log.e("FragmentManager", illegalStateException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new sc1());
        yw0 yw0Var = this.t;
        if (yw0Var == null) {
            try {
                u("  ", null, printWriter, new String[0]);
                throw illegalStateException;
            } catch (Exception e) {
                Log.e("FragmentManager", "Failed dumping state", e);
                throw illegalStateException;
            }
        }
        try {
            yw0Var.u.dump("  ", null, printWriter, new String[0]);
            throw illegalStateException;
        } catch (Exception e2) {
            Log.e("FragmentManager", "Failed dumping state", e2);
            throw illegalStateException;
        }
    }

    /* JADX INFO: renamed from: Z, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        ww0 ww0Var = this.v;
        if (ww0Var != null) {
            sb.append(ww0Var.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.v)));
            sb.append("}");
        } else {
            yw0 yw0Var = this.t;
            if (yw0Var != null) {
                sb.append(yw0Var.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.t)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    public final androidx.fragment.app.b a(ww0 ww0Var) {
        String str = ww0Var.S;
        if (str != null) {
            ux0.d(ww0Var, str);
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            ww0Var.toString();
        }
        androidx.fragment.app.b bVarF = f(ww0Var);
        ww0Var.z = this;
        gh2 gh2Var = this.c;
        gh2Var.t(bVarF);
        if (!ww0Var.H) {
            gh2Var.f(ww0Var);
            ww0Var.t = false;
            if (ww0Var.M == null) {
                ww0Var.Q = false;
            }
            if (F(ww0Var)) {
                this.D = true;
            }
        }
        return bVarF;
    }

    public final void a0() {
        synchronized (this.a) {
            try {
                if (!this.a.isEmpty()) {
                    gx0 gx0Var = this.h;
                    gx0Var.a = true;
                    py0 py0Var = gx0Var.c;
                    if (py0Var != null) {
                        py0Var.invoke();
                    }
                    return;
                }
                gx0 gx0Var2 = this.h;
                ArrayList arrayList = this.d;
                gx0Var2.a = arrayList != null && arrayList.size() > 0 && I(this.v);
                py0 py0Var2 = gx0Var2.c;
                if (py0Var2 != null) {
                    py0Var2.invoke();
                }
            } finally {
            }
        }
    }

    public final void b(yw0 yw0Var, bk0 bk0Var, ww0 ww0Var) {
        if (this.t != null) {
            throw new IllegalStateException("Already attached");
        }
        this.t = yw0Var;
        this.u = bk0Var;
        this.v = ww0Var;
        CopyOnWriteArrayList copyOnWriteArrayList = this.m;
        if (ww0Var != null) {
            copyOnWriteArrayList.add(new jx0(ww0Var));
        } else if (yw0Var instanceof rx0) {
            copyOnWriteArrayList.add(yw0Var);
        }
        if (this.v != null) {
            a0();
        }
        if (yw0Var instanceof op1) {
            androidx.activity.b bVar = yw0Var.u.o;
            this.g = bVar;
            bVar.a(ww0Var != null ? ww0Var : yw0Var, this.h);
        }
        int i = 0;
        if (ww0Var != null) {
            qx0 qx0Var = ww0Var.z.L;
            HashMap map = qx0Var.d;
            qx0 qx0Var2 = (qx0) map.get(ww0Var.m);
            if (qx0Var2 == null) {
                qx0Var2 = new qx0(qx0Var.f);
                map.put(ww0Var.m, qx0Var2);
            }
            this.L = qx0Var2;
        } else if (yw0Var instanceof mu2) {
            v4 v4Var = new v4(yw0Var.u.j(), qx0.i);
            String canonicalName = qx0.class.getCanonicalName();
            if (canonicalName == null) {
                throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
            }
            this.L = (qx0) v4Var.m(qx0.class, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        } else {
            this.L = new qx0(false);
        }
        qx0 qx0Var3 = this.L;
        int i2 = 1;
        qx0Var3.h = this.E || this.F;
        this.c.m = qx0Var3;
        yw0 yw0Var2 = this.t;
        if ((yw0Var2 instanceof x62) && ww0Var == null) {
            v62 v62VarA = yw0Var2.a();
            v62VarA.b("android:support:fragments", new pv(2, this));
            Bundle bundleA = v62VarA.a("android:support:fragments");
            if (bundleA != null) {
                Q(bundleA);
            }
        }
        yw0 yw0Var3 = this.t;
        if (yw0Var3 instanceof x4) {
            sv svVar = yw0Var3.u.r;
            String strX = u4.x("FragmentManager:", ww0Var != null ? u4.m(new StringBuilder(), ww0Var.m, ":") : "");
            this.z = svVar.b(u4.h(strX, "StartActivityForResult"), new t4(), new hk0(12, this));
            this.A = svVar.b(u4.h(strX, "StartIntentSenderForResult"), new kx0(), new fx0(this, i2));
            this.B = svVar.b(u4.h(strX, "RequestPermissions"), new e2(), new fx0(this, i));
        }
        yw0 yw0Var4 = this.t;
        if (yw0Var4 instanceof qp1) {
            yw0Var4.Y(this.n);
        }
        yw0 yw0Var5 = this.t;
        if (yw0Var5 instanceof fq1) {
            yw0Var5.b0(this.o);
        }
        yw0 yw0Var6 = this.t;
        if (yw0Var6 instanceof zp1) {
            yw0Var6.Z(this.f165p);
        }
        yw0 yw0Var7 = this.t;
        if (yw0Var7 instanceof aq1) {
            yw0Var7.a0(this.q);
        }
        yw0 yw0Var8 = this.t;
        if ((yw0Var8 instanceof ui1) && ww0Var == null) {
            yw0Var8.X(this.r);
        }
    }

    public final void c(ww0 ww0Var) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
        }
        if (ww0Var.H) {
            ww0Var.H = false;
            if (ww0Var.s) {
                return;
            }
            this.c.f(ww0Var);
            if (Log.isLoggable("FragmentManager", 2)) {
                ww0Var.toString();
            }
            if (F(ww0Var)) {
                this.D = true;
            }
        }
    }

    public final void d() {
        this.b = false;
        this.J.clear();
        this.I.clear();
    }

    public final HashSet e() {
        Object aVar;
        HashSet hashSet = new HashSet();
        Iterator it = this.c.n().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((androidx.fragment.app.b) it.next()).c.L;
            if (viewGroup != null) {
                v03.j(D(), "factory");
                Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
                if (tag instanceof df2) {
                    aVar = (df2) tag;
                } else {
                    aVar = new androidx.fragment.app.a(viewGroup);
                    viewGroup.setTag(R.id.special_effects_controller_view_tag, aVar);
                }
                hashSet.add(aVar);
            }
        }
        return hashSet;
    }

    public final androidx.fragment.app.b f(ww0 ww0Var) {
        String str = ww0Var.m;
        gh2 gh2Var = this.c;
        androidx.fragment.app.b bVar = (androidx.fragment.app.b) ((HashMap) gh2Var.e).get(str);
        if (bVar != null) {
            return bVar;
        }
        androidx.fragment.app.b bVar2 = new androidx.fragment.app.b(this.l, gh2Var, ww0Var);
        bVar2.m(this.t.r.getClassLoader());
        bVar2.e = this.s;
        return bVar2;
    }

    public final void g(ww0 ww0Var) {
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
        }
        if (ww0Var.H) {
            return;
        }
        ww0Var.H = true;
        if (ww0Var.s) {
            if (Log.isLoggable("FragmentManager", 2)) {
                ww0Var.toString();
            }
            this.c.v(ww0Var);
            if (F(ww0Var)) {
                this.D = true;
            }
            W(ww0Var);
        }
    }

    public final void h(boolean z, Configuration configuration) {
        if (z && (this.t instanceof qp1)) {
            Y(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
            throw null;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null) {
                ww0Var.onConfigurationChanged(configuration);
                if (z) {
                    ww0Var.B.h(true, configuration);
                }
            }
        }
    }

    public final boolean i() {
        if (this.s < 1) {
            return false;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && !ww0Var.G && ww0Var.B.i()) {
                return true;
            }
        }
        return false;
    }

    public final boolean j() {
        if (this.s < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && H(ww0Var) && !ww0Var.G && ww0Var.B.j()) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(ww0Var);
                z = true;
            }
        }
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); i++) {
                ww0 ww0Var2 = (ww0) this.e.get(i);
                if (arrayList == null || !arrayList.contains(ww0Var2)) {
                    ww0Var2.getClass();
                }
            }
        }
        this.e = arrayList;
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ox0.k():void");
    }

    public final void l(boolean z) {
        if (z && (this.t instanceof fq1)) {
            Y(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
            throw null;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null) {
                ww0Var.onLowMemory();
                if (z) {
                    ww0Var.B.l(true);
                }
            }
        }
    }

    public final void m(boolean z, boolean z2) {
        if (z2 && (this.t instanceof zp1)) {
            Y(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
            throw null;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && z2) {
                ww0Var.B.m(z, true);
            }
        }
    }

    public final void n() {
        for (ww0 ww0Var : this.c.o()) {
            if (ww0Var != null) {
                ww0Var.n();
                ww0Var.B.n();
            }
        }
    }

    public final boolean o() {
        if (this.s < 1) {
            return false;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && !ww0Var.G && ww0Var.B.o()) {
                return true;
            }
        }
        return false;
    }

    public final void p() {
        if (this.s < 1) {
            return;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && !ww0Var.G) {
                ww0Var.B.p();
            }
        }
    }

    public final void q(ww0 ww0Var) {
        if (ww0Var != null) {
            if (ww0Var.equals(this.c.j(ww0Var.m))) {
                ww0Var.z.getClass();
                boolean zI = I(ww0Var);
                Boolean bool = ww0Var.r;
                if (bool == null || bool.booleanValue() != zI) {
                    ww0Var.r = Boolean.valueOf(zI);
                    ox0 ox0Var = ww0Var.B;
                    ox0Var.a0();
                    ox0Var.q(ox0Var.w);
                }
            }
        }
    }

    public final void r(boolean z, boolean z2) {
        if (z2 && (this.t instanceof aq1)) {
            Y(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
            throw null;
        }
        for (ww0 ww0Var : this.c.p()) {
            if (ww0Var != null && z2) {
                ww0Var.B.r(z, true);
            }
        }
    }

    public final boolean s() {
        boolean z = false;
        if (this.s >= 1) {
            for (ww0 ww0Var : this.c.p()) {
                if (ww0Var != null && H(ww0Var) && !ww0Var.G && ww0Var.B.s()) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void t(int i) {
        try {
            this.b = true;
            for (androidx.fragment.app.b bVar : ((HashMap) this.c.e).values()) {
                if (bVar != null) {
                    bVar.e = i;
                }
            }
            J(i, false);
            Iterator it = e().iterator();
            while (it.hasNext()) {
                ((df2) it.next()).e();
            }
            this.b = false;
            x(true);
        } catch (Throwable th) {
            this.b = false;
            throw th;
        }
    }

    public final void u(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2;
        String strH = u4.h(str, "    ");
        gh2 gh2Var = this.c;
        gh2Var.getClass();
        String str3 = str + "    ";
        if (!((HashMap) gh2Var.e).isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (androidx.fragment.app.b bVar : ((HashMap) gh2Var.e).values()) {
                printWriter.print(str);
                if (bVar != null) {
                    ww0 ww0Var = bVar.c;
                    printWriter.println(ww0Var);
                    ww0Var.getClass();
                    printWriter.print(str3);
                    printWriter.print("mFragmentId=#");
                    printWriter.print(Integer.toHexString(ww0Var.D));
                    printWriter.print(" mContainerId=#");
                    printWriter.print(Integer.toHexString(ww0Var.E));
                    printWriter.print(" mTag=");
                    printWriter.println(ww0Var.F);
                    printWriter.print(str3);
                    printWriter.print("mState=");
                    printWriter.print(ww0Var.c);
                    printWriter.print(" mWho=");
                    printWriter.print(ww0Var.m);
                    printWriter.print(" mBackStackNesting=");
                    printWriter.println(ww0Var.y);
                    printWriter.print(str3);
                    printWriter.print("mAdded=");
                    printWriter.print(ww0Var.s);
                    printWriter.print(" mRemoving=");
                    printWriter.print(ww0Var.t);
                    printWriter.print(" mFromLayout=");
                    printWriter.print(ww0Var.u);
                    printWriter.print(" mInLayout=");
                    printWriter.println(ww0Var.v);
                    printWriter.print(str3);
                    printWriter.print("mHidden=");
                    printWriter.print(ww0Var.G);
                    printWriter.print(" mDetached=");
                    printWriter.print(ww0Var.H);
                    printWriter.print(" mMenuVisible=");
                    printWriter.print(ww0Var.J);
                    printWriter.print(" mHasMenu=");
                    printWriter.println(false);
                    printWriter.print(str3);
                    printWriter.print("mRetainInstance=");
                    printWriter.print(ww0Var.I);
                    printWriter.print(" mUserVisibleHint=");
                    printWriter.println(ww0Var.O);
                    if (ww0Var.z != null) {
                        printWriter.print(str3);
                        printWriter.print("mFragmentManager=");
                        printWriter.println(ww0Var.z);
                    }
                    if (ww0Var.A != null) {
                        printWriter.print(str3);
                        printWriter.print("mHost=");
                        printWriter.println(ww0Var.A);
                    }
                    if (ww0Var.C != null) {
                        printWriter.print(str3);
                        printWriter.print("mParentFragment=");
                        printWriter.println(ww0Var.C);
                    }
                    if (ww0Var.n != null) {
                        printWriter.print(str3);
                        printWriter.print("mArguments=");
                        printWriter.println(ww0Var.n);
                    }
                    if (ww0Var.e != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedFragmentState=");
                        printWriter.println(ww0Var.e);
                    }
                    if (ww0Var.k != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewState=");
                        printWriter.println(ww0Var.k);
                    }
                    if (ww0Var.l != null) {
                        printWriter.print(str3);
                        printWriter.print("mSavedViewRegistryState=");
                        printWriter.println(ww0Var.l);
                    }
                    Object objJ = ww0Var.o;
                    if (objJ == null) {
                        ox0 ox0Var = ww0Var.z;
                        objJ = (ox0Var == null || (str2 = ww0Var.f205p) == null) ? null : ox0Var.c.j(str2);
                    }
                    if (objJ != null) {
                        printWriter.print(str3);
                        printWriter.print("mTarget=");
                        printWriter.print(objJ);
                        printWriter.print(" mTargetRequestCode=");
                        printWriter.println(ww0Var.q);
                    }
                    printWriter.print(str3);
                    printWriter.print("mPopDirection=");
                    uw0 uw0Var = ww0Var.P;
                    printWriter.println(uw0Var == null ? false : uw0Var.a);
                    uw0 uw0Var2 = ww0Var.P;
                    if (uw0Var2 != null && uw0Var2.b != 0) {
                        printWriter.print(str3);
                        printWriter.print("getEnterAnim=");
                        uw0 uw0Var3 = ww0Var.P;
                        printWriter.println(uw0Var3 == null ? 0 : uw0Var3.b);
                    }
                    uw0 uw0Var4 = ww0Var.P;
                    if (uw0Var4 != null && uw0Var4.c != 0) {
                        printWriter.print(str3);
                        printWriter.print("getExitAnim=");
                        uw0 uw0Var5 = ww0Var.P;
                        printWriter.println(uw0Var5 == null ? 0 : uw0Var5.c);
                    }
                    uw0 uw0Var6 = ww0Var.P;
                    if (uw0Var6 != null && uw0Var6.d != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopEnterAnim=");
                        uw0 uw0Var7 = ww0Var.P;
                        printWriter.println(uw0Var7 == null ? 0 : uw0Var7.d);
                    }
                    uw0 uw0Var8 = ww0Var.P;
                    if (uw0Var8 != null && uw0Var8.e != 0) {
                        printWriter.print(str3);
                        printWriter.print("getPopExitAnim=");
                        uw0 uw0Var9 = ww0Var.P;
                        printWriter.println(uw0Var9 == null ? 0 : uw0Var9.e);
                    }
                    if (ww0Var.L != null) {
                        printWriter.print(str3);
                        printWriter.print("mContainer=");
                        printWriter.println(ww0Var.L);
                    }
                    if (ww0Var.M != null) {
                        printWriter.print(str3);
                        printWriter.print("mView=");
                        printWriter.println(ww0Var.M);
                    }
                    if (ww0Var.e() != null) {
                        v4 v4Var = new v4(ww0Var.j(), vb1.d);
                        String canonicalName = vb1.class.getCanonicalName();
                        if (canonicalName == null) {
                            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
                        }
                        pe2 pe2Var = ((vb1) v4Var.m(vb1.class, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName))).c;
                        if (pe2Var.k > 0) {
                            printWriter.print(str3);
                            printWriter.println("Loaders:");
                            if (pe2Var.k > 0) {
                                u4.z(pe2Var.e[0]);
                                printWriter.print(str3);
                                printWriter.print("  #");
                                printWriter.print(pe2Var.c[0]);
                                printWriter.print(": ");
                                throw null;
                            }
                        }
                    }
                    printWriter.print(str3);
                    printWriter.println("Child " + ww0Var.B + ":");
                    ww0Var.B.u(u4.h(str3, "  "), fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size3 = ((ArrayList) gh2Var.l).size();
        if (size3 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size3; i++) {
                ww0 ww0Var2 = (ww0) ((ArrayList) gh2Var.l).get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(ww0Var2.toString());
            }
        }
        ArrayList arrayList = this.e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                ww0 ww0Var3 = (ww0) this.e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(ww0Var3.toString());
            }
        }
        ArrayList arrayList2 = this.d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                sk skVar = (sk) this.d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(skVar.toString());
                skVar.e(strH, printWriter, true);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.i.get());
        synchronized (this.a) {
            try {
                int size4 = this.a.size();
                if (size4 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i4 = 0; i4 < size4; i4++) {
                        Object obj = (mx0) this.a.get(i4);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i4);
                        printWriter.print(": ");
                        printWriter.println(obj);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.t);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.u);
        if (this.v != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.v);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.s);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.E);
        printWriter.print(" mStopped=");
        printWriter.print(this.F);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.G);
        if (this.D) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.D);
        }
    }

    public final void v(mx0 mx0Var, boolean z) {
        if (!z) {
            if (this.t == null) {
                if (!this.G) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            if (this.E || this.F) {
                throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
            }
        }
        synchronized (this.a) {
            try {
                if (this.t == null) {
                    if (!z) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.a.add(mx0Var);
                    S();
                }
            } finally {
            }
        }
    }

    public final void w(boolean z) {
        if (this.b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.t == null) {
            if (!this.G) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.t.s.getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z && (this.E || this.F)) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.I == null) {
            this.I = new ArrayList();
            this.J = new ArrayList();
        }
    }

    public final boolean x(boolean z) {
        w(z);
        boolean z2 = false;
        while (true) {
            ArrayList arrayList = this.I;
            ArrayList arrayList2 = this.J;
            synchronized (this.a) {
                if (this.a.isEmpty()) {
                    break;
                }
                try {
                    int size = this.a.size();
                    boolean zA = false;
                    for (int i = 0; i < size; i++) {
                        zA |= ((mx0) this.a.get(i)).a(arrayList, arrayList2);
                    }
                    if (!zA) {
                        break;
                    }
                    z2 = true;
                    this.b = true;
                    try {
                        P(this.I, this.J);
                    } finally {
                        d();
                    }
                } finally {
                    this.a.clear();
                    this.t.s.removeCallbacks(this.M);
                }
            }
        }
        a0();
        if (this.H) {
            this.H = false;
            X();
        }
        ((HashMap) this.c.e).values().removeAll(Collections.singleton(null));
        return z2;
    }

    public final void y(mx0 mx0Var, boolean z) {
        if (z && (this.t == null || this.G)) {
            return;
        }
        w(z);
        if (mx0Var.a(this.I, this.J)) {
            this.b = true;
            try {
                P(this.I, this.J);
            } finally {
                d();
            }
        }
        a0();
        if (this.H) {
            this.H = false;
            X();
        }
        ((HashMap) this.c.e).values().removeAll(Collections.singleton(null));
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x023d A[PHI: r13
  0x023d: PHI (r13v16 int) = (r13v14 int), (r13v17 int) binds: [B:110:0x022d, B:115:0x0239] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z(java.util.ArrayList r19, java.util.ArrayList r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 1290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.ox0.z(java.util.ArrayList, java.util.ArrayList, int, int):void");
    }
}
