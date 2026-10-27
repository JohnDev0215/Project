package p;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import com.google.protobuf.WireFormat$FieldType;
import io.reactivex.rxjava3.core.Scheduler;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
public final class gh2 implements k4, xo1 {
    public static gh2 n;
    public final /* synthetic */ int c;
    public Object e;
    public Object k;
    public Object l;
    public Object m;

    public gh2(vx1 vx1Var, xb1 xb1Var, kk0 kk0Var, Scheduler scheduler) {
        this.c = 16;
        v03.j(kk0Var, "parentIdsReloader");
        this.e = vx1Var;
        this.k = xb1Var;
        this.l = kk0Var;
        this.m = scheduler;
    }

    @Override // p.k4
    public final void a(l4 l4Var) {
        ((ActionMode.Callback) this.e).onDestroyActionMode(m(l4Var));
    }

    @Override // p.k4
    public final boolean b(l4 l4Var, si1 si1Var) {
        return ((ActionMode.Callback) this.e).onCreateActionMode(m(l4Var), q(si1Var));
    }

    @Override // p.k4
    public final boolean c(l4 l4Var, MenuItem menuItem) {
        return ((ActionMode.Callback) this.e).onActionItemClicked(m(l4Var), new dj1((Context) this.k, (mh2) menuItem));
    }

    @Override // p.k4
    public final boolean d(l4 l4Var, si1 si1Var) {
        return ((ActionMode.Callback) this.e).onPrepareActionMode(m(l4Var), q(si1Var));
    }

    public final cp2 e() {
        switch (this.c) {
            case 17:
                cp2 cp2Var = cp2.b;
                ip2 ip2Var = (ip2) this.l;
                v03.j(ip2Var, "location");
                cp2 cp2Var2 = ((zi) this.m).a;
                v03.j(cp2Var2, "parentAbsoluteLocation");
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(cp2Var2.a);
                arrayList.add(ip2Var);
                return new cp2(arrayList);
            case 18:
            case 20:
            default:
                cp2 cp2Var3 = cp2.b;
                ip2 ip2Var2 = (ip2) this.l;
                v03.j(ip2Var2, "location");
                cp2 cp2Var4 = ((ok) this.m).a;
                v03.j(cp2Var4, "parentAbsoluteLocation");
                ArrayList arrayList2 = new ArrayList();
                arrayList2.addAll(cp2Var4.a);
                arrayList2.add(ip2Var2);
                return new cp2(arrayList2);
            case 19:
                cp2 cp2Var5 = cp2.b;
                ip2 ip2Var3 = (ip2) this.l;
                v03.j(ip2Var3, "location");
                cp2 cp2Var6 = ((dj) ((gh2) this.m).m).a;
                v03.j(cp2Var6, "parentAbsoluteLocation");
                ArrayList arrayList3 = new ArrayList();
                arrayList3.addAll(cp2Var6.a);
                arrayList3.add(ip2Var3);
                return new cp2(arrayList3);
            case 21:
                cp2 cp2Var7 = cp2.b;
                ip2 ip2Var4 = (ip2) this.l;
                v03.j(ip2Var4, "location");
                cp2 cp2Var8 = ((ej) this.m).a;
                v03.j(cp2Var8, "parentAbsoluteLocation");
                ArrayList arrayList4 = new ArrayList();
                arrayList4.addAll(cp2Var8.a);
                arrayList4.add(ip2Var4);
                return new cp2(arrayList4);
            case 22:
                cp2 cp2Var9 = cp2.b;
                ip2 ip2Var5 = (ip2) this.l;
                v03.j(ip2Var5, "location");
                cp2 cp2Var10 = ((wj) this.m).a;
                v03.j(cp2Var10, "parentAbsoluteLocation");
                ArrayList arrayList5 = new ArrayList();
                arrayList5.addAll(cp2Var10.a);
                arrayList5.add(ip2Var5);
                return new cp2(arrayList5);
            case 23:
                cp2 cp2Var11 = cp2.b;
                ip2 ip2Var6 = (ip2) this.l;
                v03.j(ip2Var6, "location");
                cp2 cp2Var12 = ((xj) this.m).a;
                v03.j(cp2Var12, "parentAbsoluteLocation");
                ArrayList arrayList6 = new ArrayList();
                arrayList6.addAll(cp2Var12.a);
                arrayList6.add(ip2Var6);
                return new cp2(arrayList6);
        }
    }

    public final void f(ww0 ww0Var) {
        if (((ArrayList) this.l).contains(ww0Var)) {
            throw new IllegalStateException("Fragment already added: " + ww0Var);
        }
        synchronized (((ArrayList) this.l)) {
            ((ArrayList) this.l).add(ww0Var);
        }
        ww0Var.s = true;
    }

    public final qh g() {
        String strH = ((Long) this.e) == null ? " baseAddress" : "";
        if (((Long) this.k) == null) {
            strH = strH.concat(" size");
        }
        if (((String) this.l) == null) {
            strH = u4.h(strH, " name");
        }
        if (strH.isEmpty()) {
            return new qh(((Long) this.e).longValue(), ((Long) this.k).longValue(), (String) this.l, (String) this.m);
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final xh h() {
        String strH = ((Integer) this.e) == null ? " platform" : "";
        if (((String) this.k) == null) {
            strH = strH.concat(" version");
        }
        if (((String) this.l) == null) {
            strH = u4.h(strH, " buildVersion");
        }
        if (((Boolean) this.m) == null) {
            strH = u4.h(strH, " jailbroken");
        }
        if (strH.isEmpty()) {
            return new xh(((Integer) this.e).intValue(), (String) this.k, (String) this.l, ((Boolean) this.m).booleanValue());
        }
        throw new IllegalStateException("Missing required properties:".concat(strH));
    }

    public final void i(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) ((kd2) this.m).get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                i(arrayList2.get(i), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    public final ww0 j(String str) {
        androidx.fragment.app.b bVar = (androidx.fragment.app.b) ((HashMap) this.e).get(str);
        if (bVar != null) {
            return bVar.c;
        }
        return null;
    }

    @Override // p.xo1
    public final Object k() {
        try {
            return ((zu) this.e).t0((Class) this.k);
        } catch (Exception e) {
            throw new RuntimeException("Unable to invoke no-args constructor for " + ((Type) this.l) + ". Registering an InstanceCreator with Gson for this type may fix this problem.", e);
        }
    }

    public final ww0 l(String str) {
        for (androidx.fragment.app.b bVar : ((HashMap) this.e).values()) {
            if (bVar != null) {
                ww0 ww0VarL = bVar.c;
                if (!str.equals(ww0VarL.m)) {
                    ww0VarL = ww0VarL.B.c.l(str);
                }
                if (ww0VarL != null) {
                    return ww0VarL;
                }
            }
        }
        return null;
    }

    public final hh2 m(l4 l4Var) {
        int size = ((ArrayList) this.l).size();
        for (int i = 0; i < size; i++) {
            hh2 hh2Var = (hh2) ((ArrayList) this.l).get(i);
            if (hh2Var != null && hh2Var.b == l4Var) {
                return hh2Var;
            }
        }
        hh2 hh2Var2 = new hh2((Context) this.k, l4Var);
        ((ArrayList) this.l).add(hh2Var2);
        return hh2Var2;
    }

    public final ArrayList n() {
        ArrayList arrayList = new ArrayList();
        for (androidx.fragment.app.b bVar : ((HashMap) this.e).values()) {
            if (bVar != null) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public final ArrayList o() {
        ArrayList arrayList = new ArrayList();
        for (androidx.fragment.app.b bVar : ((HashMap) this.e).values()) {
            if (bVar != null) {
                arrayList.add(bVar.c);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public final List p() {
        ArrayList arrayList;
        if (((ArrayList) this.l).isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (((ArrayList) this.l)) {
            arrayList = new ArrayList((ArrayList) this.l);
        }
        return arrayList;
    }

    public final Menu q(si1 si1Var) {
        Menu menu = (Menu) ((kd2) this.m).get(si1Var);
        if (menu != null) {
            return menu;
        }
        rj1 rj1Var = new rj1((Context) this.k, si1Var);
        ((kd2) this.m).put(si1Var, rj1Var);
        return rj1Var;
    }

    public final pp2 r(String str) {
        switch (this.c) {
            case 17:
                op2 op2Var = new op2();
                op2Var.c((ip2) this.l);
                op2Var.b = ((zi) this.m).a;
                ep2 ep2Var = ep2.e;
                dp2 dp2VarR = u11.r();
                dp2VarR.a = "play";
                dp2VarR.c = "hit";
                dp2VarR.b = 1;
                dp2VarR.b(str, "item_to_be_played");
                op2Var.d = dp2VarR.a();
                return (pp2) op2Var.a();
            case 18:
                op2 op2Var2 = new op2();
                op2Var2.c((ip2) this.l);
                op2Var2.b = ((cj) this.m).a;
                ep2 ep2Var2 = ep2.e;
                dp2 dp2VarR2 = u11.r();
                dp2VarR2.a = "play";
                dp2VarR2.c = "hit";
                dp2VarR2.b = 1;
                dp2VarR2.b(str, "item_to_be_played");
                op2Var2.d = dp2VarR2.a();
                return (pp2) op2Var2.a();
            case 19:
                op2 op2Var3 = new op2();
                op2Var3.c((ip2) this.l);
                op2Var3.b = ((dj) ((gh2) this.m).m).a;
                ep2 ep2Var3 = ep2.e;
                dp2 dp2VarR3 = u11.r();
                dp2VarR3.a = "play";
                dp2VarR3.c = "hit";
                dp2VarR3.b = 1;
                dp2VarR3.b(str, "item_to_be_played");
                op2Var3.d = dp2VarR3.a();
                return (pp2) op2Var3.a();
            case 20:
            case 21:
            default:
                op2 op2Var4 = new op2();
                op2Var4.c((ip2) this.l);
                op2Var4.b = ((ok) this.m).a;
                ep2 ep2Var4 = ep2.e;
                dp2 dp2VarR4 = u11.r();
                dp2VarR4.a = "play";
                dp2VarR4.c = "hit";
                dp2VarR4.b = 1;
                dp2VarR4.b(str, "item_to_be_played");
                op2Var4.d = dp2VarR4.a();
                return (pp2) op2Var4.a();
            case 22:
                op2 op2Var5 = new op2();
                op2Var5.c((ip2) this.l);
                op2Var5.b = ((wj) this.m).a;
                ep2 ep2Var5 = ep2.e;
                dp2 dp2VarR5 = u11.r();
                dp2VarR5.a = "play";
                dp2VarR5.c = "hit";
                dp2VarR5.b = 1;
                dp2VarR5.b(str, "item_to_be_played");
                op2Var5.d = dp2VarR5.a();
                return (pp2) op2Var5.a();
            case 23:
                op2 op2Var6 = new op2();
                op2Var6.c((ip2) this.l);
                op2Var6.b = ((xj) this.m).a;
                ep2 ep2Var6 = ep2.e;
                dp2 dp2VarR6 = u11.r();
                dp2VarR6.a = "play";
                dp2VarR6.c = "hit";
                dp2VarR6.b = 1;
                dp2VarR6.b(str, "item_to_be_played");
                op2Var6.d = dp2VarR6.a();
                return (pp2) op2Var6.a();
        }
    }

    public final pp2 s(String str) {
        switch (this.c) {
            case 17:
                op2 op2Var = new op2();
                op2Var.c((ip2) this.l);
                op2Var.b = ((zi) this.m).a;
                ep2 ep2Var = ep2.e;
                dp2 dp2VarR = u11.r();
                dp2VarR.a = "ui_navigate";
                dp2VarR.c = "hit";
                dp2VarR.b = 1;
                dp2VarR.b(str, "destination");
                op2Var.d = dp2VarR.a();
                return (pp2) op2Var.a();
            case 18:
            case 20:
            default:
                op2 op2Var2 = new op2();
                op2Var2.c((ip2) this.l);
                op2Var2.b = ((xj) this.m).a;
                ep2 ep2Var2 = ep2.e;
                dp2 dp2VarR2 = u11.r();
                dp2VarR2.a = "ui_navigate";
                dp2VarR2.c = "hit";
                dp2VarR2.b = 1;
                dp2VarR2.b(str, "destination");
                op2Var2.d = dp2VarR2.a();
                return (pp2) op2Var2.a();
            case 19:
                op2 op2Var3 = new op2();
                op2Var3.c((ip2) this.l);
                op2Var3.b = ((dj) ((gh2) this.m).m).a;
                ep2 ep2Var3 = ep2.e;
                dp2 dp2VarR3 = u11.r();
                dp2VarR3.a = "ui_navigate";
                dp2VarR3.c = "hit";
                dp2VarR3.b = 1;
                dp2VarR3.b(str, "destination");
                op2Var3.d = dp2VarR3.a();
                return (pp2) op2Var3.a();
            case 21:
                op2 op2Var4 = new op2();
                op2Var4.c((ip2) this.l);
                op2Var4.b = ((ej) this.m).a;
                ep2 ep2Var4 = ep2.e;
                dp2 dp2VarR4 = u11.r();
                dp2VarR4.a = "ui_navigate";
                dp2VarR4.c = "hit";
                dp2VarR4.b = 1;
                dp2VarR4.b(str, "destination");
                op2Var4.d = dp2VarR4.a();
                return (pp2) op2Var4.a();
            case 22:
                op2 op2Var5 = new op2();
                op2Var5.c((ip2) this.l);
                op2Var5.b = ((wj) this.m).a;
                ep2 ep2Var5 = ep2.e;
                dp2 dp2VarR5 = u11.r();
                dp2VarR5.a = "ui_navigate";
                dp2VarR5.c = "hit";
                dp2VarR5.b = 1;
                dp2VarR5.b(str, "destination");
                op2Var5.d = dp2VarR5.a();
                return (pp2) op2Var5.a();
        }
    }

    public final void t(androidx.fragment.app.b bVar) {
        ww0 ww0Var = bVar.c;
        if (((HashMap) this.e).get(ww0Var.m) != null) {
            return;
        }
        ((HashMap) this.e).put(ww0Var.m, bVar);
        if (Log.isLoggable("FragmentManager", 2)) {
            ww0Var.toString();
        }
    }

    public final void u(androidx.fragment.app.b bVar) {
        ww0 ww0Var = bVar.c;
        if (ww0Var.I) {
            ((qx0) this.m).d(ww0Var);
        }
        if (((HashMap) this.e).get(ww0Var.m) == bVar && ((androidx.fragment.app.b) ((HashMap) this.e).put(ww0Var.m, null)) != null && Log.isLoggable("FragmentManager", 2)) {
            ww0Var.toString();
        }
    }

    public final void v(ww0 ww0Var) {
        synchronized (((ArrayList) this.l)) {
            ((ArrayList) this.l).remove(ww0Var);
        }
        ww0Var.s = false;
    }

    public final Bundle w(Bundle bundle, String str) {
        return bundle != null ? (Bundle) ((HashMap) this.k).put(str, bundle) : (Bundle) ((HashMap) this.k).remove(str);
    }

    public final b33 x(Callable callable) {
        b33 b33VarA;
        synchronized (this.l) {
            b33VarA = ((aj2) this.k).a((Executor) this.e, new ww1(this, callable, 9));
            this.k = b33VarA.a((Executor) this.e, new oh1(19, this));
        }
        return b33VarA;
    }

    public final b33 y(Callable callable) {
        b33 b33Var;
        synchronized (this.l) {
            aj2 aj2Var = (aj2) this.k;
            Executor executor = (Executor) this.e;
            ww1 ww1Var = new ww1(this, callable, 9);
            b33 b33Var2 = (b33) aj2Var;
            b33Var2.getClass();
            b33Var = new b33();
            b33Var2.b.b(new m13(executor, ww1Var, b33Var, 1));
            b33Var2.j();
            this.k = b33Var.a((Executor) this.e, new oh1(19, this));
        }
        return b33Var;
    }

    public gh2(int i) {
        this.c = i;
        if (i == 2) {
            this.e = new nw1(10, 1);
            this.m = new kd2();
            this.l = new ArrayList();
            this.k = new HashSet();
            return;
        }
        if (i == 4) {
            this.l = new ArrayList();
            this.e = new HashMap();
            this.k = new HashMap();
            return;
        }
        if (i == 5) {
            this.e = new nb();
            this.k = new SparseArray();
            this.l = new wd1();
            this.m = new nb();
            return;
        }
        if (i == 7) {
            this.e = null;
            this.k = new ArrayList();
            this.l = null;
            this.m = "";
            return;
        }
        if (i == 8) {
            this.e = new Object();
            this.k = new Handler(Looper.getMainLooper(), new zd2(this));
            return;
        }
        if (i != 11 && i != 12) {
            this.e = new nw1(256, 0);
            this.k = new nw1(256, 0);
            this.l = new nw1(256, 0);
            this.m = new ce2[32];
        }
    }

    public gh2(Throwable th, nf2 nf2Var) {
        this.c = 13;
        this.e = th.getLocalizedMessage();
        this.k = th.getClass().getName();
        this.l = nf2Var.a(th.getStackTrace());
        Throwable cause = th.getCause();
        this.m = cause != null ? new gh2(cause, nf2Var) : null;
    }

    public gh2(WireFormat$FieldType wireFormat$FieldType, WireFormat$FieldType wireFormat$FieldType2, Object obj) {
        this.c = 15;
        this.e = wireFormat$FieldType;
        this.k = "";
        this.l = wireFormat$FieldType2;
        this.m = obj;
    }

    public gh2(ok okVar, Integer num, String str) {
        this.c = 24;
        this.m = okVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = okVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(zi ziVar, Integer num, String str) {
        this.c = 17;
        this.m = ziVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(dj djVar) {
        this.c = 20;
        this.m = djVar;
        this.e = null;
        this.k = null;
        hp2 hp2VarB = djVar.b.b();
        hp2VarB.i.add(new jp2("home_section", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(ej ejVar, Integer num, String str) {
        this.c = 21;
        this.m = ejVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = ejVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(wj wjVar, Integer num, String str) {
        this.c = 22;
        this.m = wjVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = wjVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(xj xjVar, Integer num, String str) {
        this.c = 23;
        this.m = xjVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = xjVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(cj cjVar, Integer num, String str) {
        this.c = 18;
        this.m = cjVar;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = cjVar.b.b();
        hp2VarB.i.add(new jp2("content_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(Typeface typeface, ek1 ek1Var) {
        this.c = 3;
        this.m = typeface;
        this.e = ek1Var;
        this.l = new fk1(1024);
        this.k = new char[((ek1) this.e).b() * 2];
        int iB = ((ek1) this.e).b();
        for (int i = 0; i < iB; i++) {
            uc0 uc0Var = new uc0(this, i);
            Character.toChars(uc0Var.c(), (char[]) this.k, i * 2);
            p02.c("invalid metadata codepoint length", uc0Var.b() > 0);
            ((fk1) this.l).a(uc0Var, 0, uc0Var.b() - 1);
        }
    }

    public gh2(gh2 gh2Var, Integer num, String str) {
        this.c = 19;
        this.m = gh2Var;
        this.e = num;
        this.k = str;
        hp2 hp2VarB = ((ip2) gh2Var.l).b();
        hp2VarB.i.add(new jp2("section_item", null, (String) this.k, (Integer) this.e, null));
        hp2VarB.j = false;
        this.l = hp2VarB.a();
    }

    public gh2(Context context, ActionMode.Callback callback) {
        this.c = 0;
        this.k = context;
        this.e = callback;
        this.l = new ArrayList();
        this.m = new kd2();
    }

    public gh2(x42 x42Var, Class cls, Type type) {
        Object xq2Var;
        this.c = 14;
        this.m = x42Var;
        this.k = cls;
        this.l = type;
        try {
            Class<?> cls2 = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls2.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            xq2Var = new uq2(declaredField.get(null), cls2.getMethod("allocateInstance", Class.class));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    xq2Var = new vq2(iIntValue, declaredMethod2);
                } catch (Exception unused2) {
                    xq2Var = new xq2();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                xq2Var = new wq2(declaredMethod3);
            }
        }
        this.e = xq2Var;
    }

    public gh2(Executor executor, int i) {
        this.c = i;
        if (i != 10) {
            this.k = new ArrayList();
            this.l = new ArrayList();
            this.m = cw.b;
            this.e = executor;
            return;
        }
        this.k = ci1.t(null);
        this.l = new Object();
        this.m = new ThreadLocal();
        this.e = executor;
        executor.execute(new rv(16, this));
    }
}
