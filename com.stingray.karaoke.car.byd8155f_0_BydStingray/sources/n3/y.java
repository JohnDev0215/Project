package n3;

import android.net.Uri;
import android.os.Bundle;
import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import n7.AbstractC1998m;
import u.C2459G;
import u.C2460H;

/* JADX INFO: loaded from: classes.dex */
public abstract class y {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final /* synthetic */ int f19913t = 0;
    public final String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public C1927B f19914m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f19915n;
    public final C2459G o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final LinkedHashMap f19916p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f19917q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f19918r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public m7.n f19919s;

    static {
        new LinkedHashMap();
    }

    public y(O o) {
        A7.m.f(o, "navigator");
        LinkedHashMap linkedHashMap = P.f19843b;
        this.l = h6.j.l(o.getClass());
        this.f19915n = new ArrayList();
        this.o = new C2459G(0);
        this.f19916p = new LinkedHashMap();
    }

    public final Bundle d(Bundle bundle) {
        LinkedHashMap linkedHashMap = this.f19916p;
        if (bundle == null && linkedHashMap.isEmpty()) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str = (String) entry.getKey();
            ((C1941f) entry.getValue()).getClass();
            A7.m.f(str, "name");
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                String str2 = (String) entry2.getKey();
                C1941f c1941f = (C1941f) entry2.getValue();
                c1941f.getClass();
                A7.m.f(str2, "name");
                M m10 = c1941f.f19858a;
                if (c1941f.f19859b || !bundle2.containsKey(str2) || bundle2.get(str2) != null) {
                    try {
                        m10.a(str2, bundle2);
                    } catch (ClassCastException unused) {
                    }
                }
                StringBuilder sbJ = AbstractC1347d.j("Wrong argument type for '", str2, "' in argument bundle. ");
                sbJ.append(m10.b());
                sbJ.append(" expected.");
                throw new IllegalArgumentException(sbJ.toString().toString());
            }
        }
        return bundle2;
    }

    public boolean equals(Object obj) {
        boolean z6;
        boolean z10;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof y)) {
            return false;
        }
        y yVar = (y) obj;
        boolean zA = A7.m.a(this.f19915n, yVar.f19915n);
        C2459G c2459g = this.o;
        int iF = c2459g.f();
        C2459G c2459g2 = yVar.o;
        if (iF != c2459g2.f()) {
            z6 = false;
            break;
        }
        Iterator it = ((P8.a) P8.m.X(new C2460H(c2459g))).iterator();
        while (it.hasNext()) {
            int iIntValue = ((Number) it.next()).intValue();
            if (!A7.m.a(c2459g.c(iIntValue), c2459g2.c(iIntValue))) {
                z6 = false;
                break;
            }
        }
        z6 = true;
        LinkedHashMap linkedHashMap = this.f19916p;
        int size = linkedHashMap.size();
        LinkedHashMap linkedHashMap2 = yVar.f19916p;
        if (size != linkedHashMap2.size()) {
            z10 = false;
            break;
        }
        for (Map.Entry entry : (Iterable) AbstractC1998m.S(linkedHashMap.entrySet()).f5481b) {
            if (!linkedHashMap2.containsKey(entry.getKey()) || !A7.m.a(linkedHashMap2.get(entry.getKey()), entry.getValue())) {
                z10 = false;
                break;
            }
        }
        z10 = true;
        return this.f19917q == yVar.f19917q && A7.m.a(this.f19918r, yVar.f19918r) && zA && z6 && z10;
    }

    public int hashCode() {
        int i7 = this.f19917q * 31;
        String str = this.f19918r;
        int iHashCode = i7 + (str != null ? str.hashCode() : 0);
        Iterator it = this.f19915n.iterator();
        while (it.hasNext()) {
            iHashCode = (((v) it.next()).f19899a.hashCode() + (iHashCode * 31)) * 961;
        }
        C2459G c2459g = this.o;
        A7.m.f(c2459g, "<this>");
        if (c2459g.f() > 0) {
            c2459g.g(0).getClass();
            throw new ClassCastException();
        }
        LinkedHashMap linkedHashMap = this.f19916p;
        for (String str2 : linkedHashMap.keySet()) {
            int iC = M5.b.c(str2, iHashCode * 31, 31);
            Object obj = linkedHashMap.get(str2);
            iHashCode = iC + (obj != null ? obj.hashCode() : 0);
        }
        return iHashCode;
    }

    public w l(h3.y yVar) {
        ArrayList<v> arrayList = this.f19915n;
        if (arrayList.isEmpty()) {
            return null;
        }
        w wVar = null;
        for (v vVar : arrayList) {
            LinkedHashMap linkedHashMap = this.f19916p;
            Uri uri = (Uri) yVar.f15499m;
            Bundle bundleD = uri != null ? vVar.d(uri, linkedHashMap) : null;
            int iB = vVar.b(uri);
            String str = (String) yVar.f15500n;
            boolean z6 = str != null && str.equals(null);
            if (bundleD == null) {
                if (z6) {
                    A7.m.f(linkedHashMap, "arguments");
                    Bundle bundle = new Bundle();
                    if (uri != null) {
                        Pattern pattern = (Pattern) vVar.d.getValue();
                        Matcher matcher = pattern != null ? pattern.matcher(uri.toString()) : null;
                        if (matcher != null && matcher.matches()) {
                            vVar.e(matcher, bundle, linkedHashMap);
                            if (((Boolean) vVar.f19901e.getValue()).booleanValue()) {
                                vVar.f(uri, bundle, linkedHashMap);
                            }
                        }
                    }
                    if (h6.e.p(linkedHashMap, new u(1, bundle)).isEmpty()) {
                    }
                }
            }
            w wVar2 = new w(this, bundleD, vVar.l, iB, z6);
            if (wVar == null || wVar2.compareTo(wVar) > 0) {
                wVar = wVar2;
            }
        }
        return wVar;
    }

    public final w q(String str) {
        v vVar;
        A7.m.f(str, "route");
        m7.n nVar = this.f19919s;
        if (nVar == null || (vVar = (v) nVar.getValue()) == null) {
            return null;
        }
        Uri uri = Uri.parse("android-app://androidx.navigation/".concat(str));
        A7.m.b(uri);
        Bundle bundleD = vVar.d(uri, this.f19916p);
        if (bundleD == null) {
            return null;
        }
        return new w(this, bundleD, vVar.l, vVar.b(uri), false);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(0x");
        sb.append(Integer.toHexString(this.f19917q));
        sb.append(")");
        String str = this.f19918r;
        if (str != null && !Q8.h.M0(str)) {
            sb.append(" route=");
            sb.append(this.f19918r);
        }
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }
}
