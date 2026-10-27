package n3;

import e0.C1183w;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f19920a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f19921b;
    public Object c;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f19922e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f19923f;

    public z() {
        this.f19920a = 1;
        this.f19921b = null;
        this.c = null;
        this.d = null;
        this.f19922e = null;
        this.f19923f = null;
    }

    public abstract void a(B8.t tVar);

    public void b(z zVar) {
        zVar.h();
        zVar.f(this);
        z zVar2 = (z) this.d;
        if (zVar2 == null) {
            this.c = zVar;
            this.d = zVar;
        } else {
            zVar2.f19923f = zVar;
            zVar.f19922e = zVar2;
            this.d = zVar;
        }
    }

    public y c() {
        LinkedHashMap linkedHashMap;
        y yVarE = e();
        yVarE.getClass();
        Iterator it = ((LinkedHashMap) this.d).entrySet().iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            linkedHashMap = yVarE.f19916p;
            if (!zHasNext) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            C1941f c1941f = (C1941f) entry.getValue();
            A7.m.f(str, "argumentName");
            A7.m.f(c1941f, "argument");
            linkedHashMap.put(str, c1941f);
        }
        for (v vVar : (ArrayList) this.f19923f) {
            A7.m.f(vVar, "navDeepLink");
            ArrayList arrayListP = h6.e.p(linkedHashMap, new x(vVar, 0));
            if (!arrayListP.isEmpty()) {
                throw new IllegalArgumentException(("Deep link " + vVar.f19899a + " can't be used to open destination " + yVarE + ".\nFollowing required arguments are missing: " + arrayListP).toString());
            }
            yVarE.f19915n.add(vVar);
        }
        Iterator it2 = ((LinkedHashMap) this.f19922e).entrySet().iterator();
        if (it2.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it2.next();
            ((Number) entry2.getKey()).intValue();
            if (entry2.getValue() != null) {
                throw new ClassCastException();
            }
            A7.m.f(null, "action");
            throw null;
        }
        String str2 = (String) this.c;
        if (str2 != null) {
            if (Q8.h.M0(str2)) {
                throw new IllegalArgumentException("Cannot have an empty route");
            }
            int i7 = y.f19913t;
            String strConcat = "android-app://androidx.navigation/".concat(str2);
            A7.m.f(strConcat, "uriPattern");
            ArrayList arrayListP2 = h6.e.p(linkedHashMap, new x(new v(strConcat), 1));
            if (!arrayListP2.isEmpty()) {
                throw new IllegalArgumentException(("Cannot set route \"" + str2 + "\" for destination " + yVarE + ". Following required arguments are missing: " + arrayListP2).toString());
            }
            yVarE.f19919s = h6.r.o(new C1183w(strConcat, 6));
            yVarE.f19917q = strConcat.hashCode();
            yVarE.f19918r = str2;
        }
        return yVarE;
    }

    public z d() {
        return (z) this.f19921b;
    }

    public y e() {
        return ((O) this.f19921b).a();
    }

    public void f(z zVar) {
        this.f19921b = zVar;
    }

    public String g() {
        return "";
    }

    public void h() {
        z zVar = (z) this.f19922e;
        if (zVar != null) {
            zVar.f19923f = (z) this.f19923f;
        } else {
            z zVar2 = (z) this.f19921b;
            if (zVar2 != null) {
                zVar2.c = (z) this.f19923f;
            }
        }
        z zVar3 = (z) this.f19923f;
        if (zVar3 != null) {
            zVar3.f19922e = zVar;
        } else {
            z zVar4 = (z) this.f19921b;
            if (zVar4 != null) {
                zVar4.d = zVar;
            }
        }
        this.f19921b = null;
        this.f19923f = null;
        this.f19922e = null;
    }

    public String toString() {
        switch (this.f19920a) {
            case 1:
                return getClass().getSimpleName() + "{" + g() + "}";
            default:
                return super.toString();
        }
    }

    public z(O o, String str) {
        this.f19920a = 0;
        this.f19921b = o;
        this.c = str;
        this.d = new LinkedHashMap();
        this.f19923f = new ArrayList();
        this.f19922e = new LinkedHashMap();
    }
}
